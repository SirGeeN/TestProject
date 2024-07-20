package com.misys.portal.common.actions;

import com.misys.portal.common.resources.DefaultResourceProvider;
import com.misys.portal.common.services.Audit;
import com.misys.portal.common.services.ILoginHandler;
import com.misys.portal.common.tools.ConvertTools;
import com.misys.portal.common.tracer.Auditable;
import com.misys.portal.common.tracer.GTPException;
import com.misys.portal.common.tracer.Log;
import com.misys.portal.common.tracer.LoginAuditItem;
import com.misys.portal.core.util.EngineContext;
import com.misys.portal.device.TargetDeviceUtils;
import com.misys.portal.entity.common.Entity;
import com.misys.portal.interfaces.core.Bundle;
import com.misys.portal.interfaces.services.Interfaces;
import com.misys.portal.security.GTPCompany;
import com.misys.portal.security.GTPSecurity;
import com.misys.portal.security.GTPUser;
import com.misys.portal.services.db.PoolBrokerService;
import com.misys.portal.services.security.authentication.MobileLoginException;
import java.sql.Timestamp;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.turbine.Turbine;
import org.apache.turbine.modules.Action;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.RunData;

public class GTPLoginAction
        extends Action {

    private static final String SUCCESS = "success";
    private static final String SCREEN_LOGIN = "screen.login";
    private static final String FAILURE = "failure";
    private static final String STATUS = "status";
    private static final String FAILURE_MESSAGE = "failureMessage";
    protected static final String QUERY_STRING = "queryString";
    protected static final String NEXT_SCREEN = "nextScreen";
    protected static final String SMS_BUNDLE = "SendSMSNotification";
    protected static final String EMAIL_BUNDLE = "SendEmailNotification";

    public void doPerform(RunData data) throws Exception {
        if (!LoginUtils.LOGIN_ACTION_CLASS.equals(data.getAction())) {
            return;
        }
        this.populatePreRequisits(data);
        String currentMode = this.getCurrentMode(data);
        GTPUser user = null;
        GTPCompany company = null;
        try {
            Audit.audit((RunData) data, (String) "Y");
            user = LoginUtils.getLoginHandler(currentMode).handleMyMode(data);
            boolean isLoginWithUserSelectedLangEnabled = DefaultResourceProvider.LOGIN_WITH_USER_SELECTED_LANGUAGE;
            if (isLoginWithUserSelectedLangEnabled) {
                user.setLanguage(data.getParameters().getString("userselectedlanguage"));
            }
            company = GTPSecurity.getCompany((String) user.getCompany_abbv_name());
            if (TargetDeviceUtils.getTargetDeviceService().getTargetDevice(data).isMobile()) {
                data.getTemplateInfo().setTemp("status", (Object) "success");
                data.setScreen(TargetDeviceUtils.getTargetDeviceService().getScreen(data, "screen.login"));
            }
            this.saveSelectedLanguage(user, data);
            ILoginHandler nextHandler = LoginUtils.getNextLoginHandler(data, user, currentMode);
            if (nextHandler != null) {
                nextHandler.populatePreRequisitsForMyMode(data);
                user.setTemp("mode", (Object) nextHandler.myModeName());
                return;
            }
            try {
                boolean isFirstLogin = LoginUtils.checkUserFirstLoggedIn((User) user);
                this.performPostAuthenticationCore(data, user);
                this.performPostAuthenticationCustom(data, user, company, isFirstLogin);
            } catch (MobileLoginException v0) {
                String nextMode = this.getCurrentMode(data);
                LoginUtils.getLoginHandler(nextMode).populatePreRequisitsForMyMode(data);
                Audit.updateContext((RunData) data, (Auditable) this.getLoginAuditContext("", null, data), (String) "N");
                data.setScreen(TargetDeviceUtils.getTargetDeviceService().getScreen(data, "screen.login"));
            } catch (Exception v1) {
                if (StringUtils.isBlank((String) data.getMessage())) {
                    if (company != null) {
                        LoginUtils.setErrorMessageOnScreen(data, "ACTION_USER_LOGIN_TECHNICAL_ERROR");
                    } else {
                        LoginUtils.setErrorMessageOnScreen(data, "ACTION_USER_LOGIN_NACK");
                    }
                }
                if (TargetDeviceUtils.getTargetDeviceService().getTargetDevice(data).isMobile()) {
                    data.getTemplateInfo().setTemp("failureMessage", (Object) data.getMessage());
                    if (StringUtils.isEmpty((String) ((String) data.getTemplateInfo().getTemp("status")))) {
                        data.getTemplateInfo().setTemp("status", (Object) "failure");
                    }
                    data.setScreen(TargetDeviceUtils.getTargetDeviceService().getScreen(data, "screen.login"));
                }
                String nextMode = this.getCurrentMode(data);
                LoginUtils.getLoginHandler(nextMode).populatePreRequisitsForMyMode(data);
                Audit.updateContext((RunData) data, (Auditable) this.getLoginAuditContext("", null, data), (String) "N");

            }
        } finally {
            String nextScreen = data.getParameters().getString("nextScreen", "");
            String queryString = data.getParameters().getString("queryString", "");
            if (nextScreen != null && !nextScreen.equals(LoginUtils.HOME_SCREEN_CLASS) && StringUtils.isNotBlank((String) queryString)) {
                data.getParameters().setString("queryString", queryString);
            }
            Audit.saveContext((RunData) data);

//            String url = Turbine.getConfiguration().getString("action.cib.url");
//            if (url.length() > 0) {
//
//                HttpServletResponse response = data.getResponse();
//                response.sendRedirect(url);
//    	//String message = new SignAssertion().getSAMLResponse();
//                //response.sendRedirect(url+"?samlResponse="+message);
//                //System.out.println("<<<  >>>"+new SignAssertion().getSAMLResponse());
//            }
        }
    }

    private void saveSelectedLanguage(GTPUser user, RunData data) throws Exception {
        try {
            Audit.audit((RunData) data, (String) "Y");
            user.setLanguage(LoginUtils.getUserLanguageOnLoginFlow(data));
            data.setMessage("");
            GTPSecurity.saveUser((GTPUser) user);
        } catch (Exception v0) {
            Audit.updateContext((RunData) data, (Auditable) user, (String) "C", (boolean) true);
        }
        Audit.saveContext((RunData) data);
    }

    protected String getCurrentMode(RunData data) {
        String currentMode = "credentials";
        if (data.getUser() != null) {
            currentMode = data.getUser().getTemp("mode") != null ? (String) data.getUser().getTemp("mode") : "credentials";
        }
        return currentMode;
    }

    protected Auditable getLoginAuditContext(String item1, String message, RunData data) {
        LoginAuditItem item = new LoginAuditItem();
        GTPUser logedInUser = (GTPUser) data.getUser();
        String userName = LoginUtils.getUserNameFromDataParameters(data);
        String companyName = LoginUtils.getCompanyNameFromDataParameters(data);
        if (logedInUser != null && !StringUtils.isEmpty((String) logedInUser.getUserName())) {
            userName = logedInUser.getUserName();
            companyName = logedInUser.getCompany_abbv_name();
        }
        item.getItem_id1().setValue(String.valueOf(userName) + "-" + item1);
        item.getItem_id2().setValue(companyName);
        item.getType().setValue("02");
        StringBuffer buffer = new StringBuffer();
        buffer.append("login_id".toUpperCase()).append(": ").append(item.getItem_id1().getValue()).append("\n");
        buffer.append("company_abbv_name".toUpperCase()).append(": ").append(item.getItem_id2().getValue()).append("\n");
        if (message != null) {
            buffer.append("message".toUpperCase()).append(": ").append(message).append("\n");
        } else {
            buffer.append("message".toUpperCase()).append(": ").append(data.getMessage()).append("\n");
        }
        item.getContext().setValue(buffer.toString());
        return item;
    }

    protected void performPostAuthenticationCore(RunData data, GTPUser user) throws GTPException {
        try {
            user.setLanguage(LoginUtils.getToBeStoredLanguage(user));
            user.setEntities(Entity.retrieveFromUser((String) Integer.toString(user.getCompanyId()), (String) user.getName()));
            user.updateLastLogin();
            user.setHasLoggedIn(Boolean.TRUE);
            user.setTemp("mode", (Object) "");
            user.setLoginAttempts(0);
            this.saveUserToDB(data, user);
            GTPSecurity.resetJurisdictionCache();
            Audit.updateContext((RunData) data, (Auditable) this.getLoginAuditContext("Login Success", "Login Success", data), (String) "Y");
            Audit.saveContext((RunData) data);
            data.setAction(null);
        } catch (Exception e) {
            throw new GTPException(data, "Exception in performing post authentication tasks...", (Throwable) e);
        }
    }

    protected void performPostAuthenticationCustom(RunData data, GTPUser user, GTPCompany company, boolean isFirstLogin) throws GTPException {
        if (DefaultResourceProvider.ALERT_MESSAGE_ENABLED && isFirstLogin) {
            this.sendNotifications(user, data);
        }
        if (StringUtils.isNotEmpty((String) company.getAttachmentMaxUploadSize())) {
            data.getSession().setAttribute("maxFileUploadLimit", (Object) company.getAttachmentMaxUploadSize());
        }
        if (!TargetDeviceUtils.getTargetDeviceService().getTargetDevice(data).isMobile()) {
            this.populateDeepLinkingSetUp(data);
        }
    }

    protected void populateDeepLinkingSetUp(RunData data) {
        String nextScreen = data.getParameters().getString("nextScreen", "");
        String queryString = data.getParameters().getString("queryString", "");
        if (nextScreen != null && !nextScreen.equals(LoginUtils.HOME_SCREEN_CLASS)) {
            StringBuffer redirectURL = new StringBuffer(EngineContext.getContextPath()).append(EngineContext.getServletPath()).append("/screen/").append(nextScreen);
            if (queryString != null && !"".equals(queryString)) {
                redirectURL.append("?").append(queryString);
            }
            data.setRedirectURI(redirectURL.toString());
        }
    }

    protected void populatePreRequisits(RunData data) {
        data.populate();
        this.setDefaultScreenMessage(data);
    }

    protected void saveUserToDB(RunData data, GTPUser user) {
        try {
            PoolBrokerService.beginTransaction();
            GTPSecurity.updateUser((GTPUser) user);
            PoolBrokerService.commitTransaction();
        } catch (Exception e) {
            try {
                Log.error(this.getClass(), (String) "Exception while updating/saving the user into DB", (Throwable) e);
                PoolBrokerService.rollbackTransaction();
            } catch (Exception ee) {
                Log.error(this.getClass(), (String) "Exception while rollback - due to Exception while updating/saving the user into DB", (Throwable) ee);
            }
        }
    }

    protected void sendNotifications(GTPUser user, RunData data) throws GTPException {
        try {
            Object[] args;
            Bundle emailBundle = Interfaces.getBundle((String) "SendEmailNotification");
            Bundle smsBundle = Interfaces.getBundle((String) "SendSMSNotification");
            Timestamp currentDateTime = new Timestamp(System.currentTimeMillis());
            if (emailBundle != null) {
                HashMap<String, Object> emailMap = new HashMap<String, Object>();
                emailBundle.getEnvironment().setEntry("user", (Object) Interfaces.clone((GTPUser) user));
                args = new Object[]{user.getFirstName(), user.getName(), ConvertTools.timestampToDateTimeString((Timestamp) currentDateTime, (String) user.getLanguage())};
                emailMap.put("USER", (Object) user);
                emailMap.put("ARGS", args);
                emailBundle.run(emailMap);
            }
            if (smsBundle != null) {
                HashMap<String, Object> smsMap = new HashMap<String, Object>();
                smsBundle.getEnvironment().setEntry("user", (Object) Interfaces.clone((GTPUser) user));
                args = new Object[]{user.getFirstName(), user.getName(), ConvertTools.timestampToDateTimeString((Timestamp) currentDateTime, (String) user.getLanguage())};
                smsMap.put("USER", (Object) user);
                smsMap.put("ARGS", args);
                smsBundle.run(smsMap);
            }
        } catch (Exception e) {
            Log.error(this.getClass(), (String) "An error occured during the interfaces jobs at sendNotifications call", (Throwable) e);
        }
    }

    protected void setDefaultScreenMessage(RunData data) {
        data.setMessage("");
    }
}

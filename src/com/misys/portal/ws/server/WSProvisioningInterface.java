package com.misys.portal.ws.server;

import com.misys.portal.common.binder.GTPRequestActionBinder;
import com.misys.portal.common.binder.RequestParameter;
import com.misys.portal.common.binder.RequestParametersSet;
import com.misys.portal.common.localization.Localization;
import com.misys.portal.common.resources.DefaultResourceProvider;
import com.misys.portal.common.services.Audit;
import com.misys.portal.common.tracer.AuditItem;
import com.misys.portal.common.tracer.GTPException;
import com.misys.portal.common.tracer.InvalidDataException;
import com.misys.portal.common.validation.GTPBusinessValidationException;
import com.misys.portal.interfaces.core.Bundle;
import com.misys.portal.interfaces.core.Environment;
import com.misys.portal.interfaces.core.InterfaceException;
import com.misys.portal.interfaces.services.Interfaces;
import com.misys.portal.interfaces.util.InterfaceUtil;
import com.misys.portal.provisioning.services.AuthenticationFault;
import com.misys.portal.provisioning.services.BusinessFault;
import com.misys.portal.provisioning.services.Fault;
import com.misys.portal.provisioning.services.TechnicalFault;
import com.misys.portal.security.GTPSecurity;
import com.misys.portal.security.GTPUser;
import com.misys.portal.security.util.GTPAccessControlList;
import com.misys.portal.services.rundata.GTPRunData;
import com.misys.portal.services.security.webservices.ProvisioningAuthenticationService;
import com.misys.portal.services.source.SourceValidatorService;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.services.TurbineServices;
import org.apache.turbine.util.RunData;

public class WSProvisioningInterface {

    private static final String OPERATION_TYPE = "operationType";
    private static final String COMPANY_TYPE = "companyType";
    private static final String USER_PROFILE_COMPANY_TYPE = "userProfileCompanyType";
    private static final String CREATOR_COMPANY_TYPE = "creatorCompanyType";
    private static final String WS_SCREEN = "WSScreen";
    private static final Log LOG = LogFactory.getLog(WSProvisioningInterface.class);
    private static final String SERVICE_NAME = "MPProvisioningService";

    public static void performCompanyProvisioning(String companyType, String companyName, String userName, String password, String operationType, String xmlData, String source)
            throws AuthenticationFault, TechnicalFault, BusinessFault {
        GTPRunData runData = getFakeRunDataForAudit();
        runData.setAction("CompanyProvisioning");

        authenticate(companyName, userName, password, runData);
        GTPUser wsUser = (GTPUser) runData.getUser();

        String actionCode = getActionCode(operationType, companyType);
        if (actionCode == null) {
            GTPBusinessValidationException processingException = new GTPBusinessValidationException(
                    " The operation type is not valid", "CFB_ERROR_51");

            auditInvalidActionCode(runData, processingException);
            throw getBusinessFault(processingException);
        }
        runData.setAction(actionCode);
        Audit.audit(runData, "Y");

        checkPermission(runData, wsUser, actionCode);

        validateSource(runData, source);

        Map<String, Object> envMap = new HashMap();

        envMap.put(
                "interface_processing_object_type",
                "company");
        envMap.put("interface_processing_user", wsUser);
        envMap.put("interface_processing_operation", operationType);
        envMap.put("interface_processing_company_type", companyType);
        envMap.put("rundata", runData);

        runInterface(actionCode, xmlData, envMap);
        Audit.saveContext(runData);
    }

    public static void performUserProvisioning(String userProfileCompanyType, String wsUserCompanyName, String wsUserName, String wsUserPassword, String operationType, String xmlData, String source)
            throws AuthenticationFault, TechnicalFault, BusinessFault {
        GTPRunData runData = getFakeRunDataForAudit();
        runData.setAction("UserProvisioning");

        authenticate(wsUserCompanyName, wsUserName, wsUserPassword, runData);
        GTPUser wsUser = (GTPUser) runData.getUser();

        String actionCode = getActionCode(operationType, userProfileCompanyType, wsUser.getCompanyType());
        if (actionCode == null) {
            GTPBusinessValidationException processingException = new GTPBusinessValidationException(
                    "The operation type is invalid", "UFB_ERROR_32");
            auditInvalidActionCode(runData, processingException);
            throw getBusinessFault(processingException);
        }
        runData.setAction(actionCode);
        Audit.audit(runData, "Y");

        checkPermission(runData, wsUser, actionCode);

        validateSource(runData, source);
        Map<String, Object> envMap = new HashMap();

        envMap.put(
                "interface_processing_object_type",
                "user");
        envMap.put("interface_processing_user", wsUser);
        envMap.put("interface_processing_operation", operationType);
        envMap.put("interface_processing_company_type", userProfileCompanyType);
        envMap.put("rundata", runData);

        runInterface(actionCode, xmlData, envMap);
        Audit.saveContext(runData);
    }

    private static void validateSource(GTPRunData runData, String source)
            throws BusinessFault, TechnicalFault {
        SourceValidatorService listDefService = (SourceValidatorService) TurbineServices.getInstance()
                .getService("SourceValidatorService");
        try {
            LOG.debug("Validating source configuration for the web service");

            runData.getParameters().add("service", "MPProvisioningService");
            listDefService.validateSourceConfiguration(runData, source);
        } catch (GTPBusinessValidationException be) {
            AuditItem item = new AuditItem();
            StringBuilder context = new StringBuilder();
            context.append("Error: ").append(be.getMessageFromKey(DefaultResourceProvider.LANGUAGE));
            item.getContext().setValue(context.toString());
            Audit.updateContext(runData, item, "C");
            Audit.saveContext(runData);

            Fault fault = new Fault();
            BusinessFault bFault = new BusinessFault();
            fault.setCode(be.getMessageKey());
            fault.setReason(be.getMessageFromKey(DefaultResourceProvider.LANGUAGE));
            bFault.setFaultMessage(fault);
            throw bFault;
        } catch (GTPException e) {
            AuditItem item = new AuditItem();
            StringBuilder context = new StringBuilder();
            context.append("Error: ").append(
                    Localization.getString(DefaultResourceProvider.LANGUAGE, "WST_ERROR_01"));
            item.getContext().setValue(context.toString());
            Audit.updateContext(runData, item, "C");
            Audit.saveContext(runData);
            throw getTechnicalFault(e);
        }
    }

    private static void runInterface(String actionCode, String xmlData, Map<String, Object> envMap)
            throws TechnicalFault, BusinessFault {
        Bundle bundleObject = null;
        Environment env = null;
        try {
            System.out.println("<< Stage1 >>");
            System.out.println("<< actionCode >>" + actionCode);
            bundleObject = Interfaces.getBundle(actionCode);
            System.out.println("<< bundleObject >>" + bundleObject);
            env = bundleObject.getEnvironment();
            System.out.println("<< bundleObject.getEnvironment() >>" + bundleObject.getEnvironment());
            env.setEntry("interface_processing_context", Boolean.valueOf(true));
            System.out.println("<< Stage3 >>");
            for (String key : envMap.keySet()) {
                System.out.println("<< Stage4 >>");
                env.setEntry(key, envMap.get(key));
                System.out.println("<< Stage5 >>");
            }

            if (LOG.isDebugEnabled()) {
                LOG.debug("The xml is " + xmlData);
            }
            LOG.info("Running the bundle.");

            bundleObject.run(xmlData);

            LOG.info("The bundle ran successfully.");
        } catch (InterfaceException ie) {
            LOG.error("Error during running bundle", ie);

            Object processingException = null;
            if (env != null) {
                processingException = env.getEntry("interface_processing_exception");
            }
            if (processingException != null) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("There was a processing exception " + processingException.getClass());
                }
                throw getBusinessFault(processingException);
            }
            if (LOG.isDebugEnabled()) {
                LOG.debug("Technical exception encountered while running bundle. ");
            }
            throw getTechnicalFault(ie);
        }
    }

    private static BusinessFault getBusinessFault(Object processingException) {
        Fault fault = new Fault();
        BusinessFault bFault = new BusinessFault();
        if ((processingException instanceof GTPBusinessValidationException)) {
            GTPBusinessValidationException businessException = (GTPBusinessValidationException) processingException;
            if (LOG.isDebugEnabled()) {
                LOG.debug("Business exception encountered while running bundle", businessException);
            }
            fault.setCode(businessException.getMessageKey());
            fault.setReason(businessException.getMessageFromKey(DefaultResourceProvider.LANGUAGE));
        } else if ((processingException instanceof InvalidDataException)) {
            InvalidDataException invalidDataException = (InvalidDataException) processingException;
            if (LOG.isDebugEnabled()) {
                LOG.debug(
                        "Invalid data exception encountered while running bundle",
                        invalidDataException);
            }
            fault.setCode(invalidDataException.getMessageKey());
            fault.setReason(invalidDataException.getMessageFromKey(DefaultResourceProvider.LANGUAGE));
        }
        bFault.setFaultMessage(fault);
        return bFault;
    }

    private static TechnicalFault getTechnicalFault(Exception exception)
            throws TechnicalFault {
        Fault fault = new Fault();
        TechnicalFault tFault = new TechnicalFault();
        fault.setCode("WST_ERROR_01");
        Throwable rootException = ExceptionUtils.getCause(exception);
        if (ExceptionUtils.getCause(rootException) != null) {
            fault.setReason(ExceptionUtils.getCause(rootException).getMessage());
        } else {
            fault.setReason(rootException.getMessage());
        }
        tFault.setFaultMessage(fault);
        return tFault;
    }

    private static void checkPermission(RunData runData, GTPUser wsUser, String actionCode)
            throws TechnicalFault, BusinessFault {
        Fault fault = new Fault();
        GTPAccessControlList acl = null;
        try {
            acl = GTPSecurity.getACL(wsUser);
        } catch (Exception ex) {
            LOG.error("Error during getting user ACL ", ex);
            throw getTechnicalFault(ex);
        }
        String permission = GTPRequestActionBinder.getPermissionFromAction(actionCode);
        if (!acl.hasPermission(permission)) {
            LOG.error("Missing permission in user ACL: " + permission);

            String errorMsg = Localization.getGTPString(
                    DefaultResourceProvider.LANGUAGE,
                    "WSV_ERROR_02");

            AuditItem item = new AuditItem();
            StringBuilder context = new StringBuilder();
            context.append("Error: ").append(errorMsg);
            item.getContext().setValue(context.toString());
            Audit.updateContext(runData, item, "C");
            Audit.saveContext(runData);

            BusinessFault bFault = new BusinessFault();
            fault.setCode("WSV_ERROR_02");
            fault.setReason(errorMsg);
            bFault.setFaultMessage(fault);
            throw bFault;
        }
    }

    private static void authenticate(String companyName, String userName, String password, GTPRunData runData)
            throws AuthenticationFault {
        Fault fault = new Fault();

        GTPUser authenticatedUser = null;
        try {
            ProvisioningAuthenticationService psservice = (ProvisioningAuthenticationService) TurbineServices.getInstance()
                    .getService("ProvisioningAuthenticationService");
            authenticatedUser = psservice.authenticate(companyName, userName, password);
        } catch (Exception e) {
            LOG.error("Error while authenticating user", e);

            Audit.audit(runData, "U");
            runData.getParameters().setString(
                    "company",
                    DefaultResourceProvider.AUDIT_DEFAULT_COMPANY);
            AuditItem item = new AuditItem();
            StringBuilder context = new StringBuilder();
            context.append("User Name: ").append(userName).append("\n Company: ").append(companyName);
            item.getContext().setValue(context.toString());
            Audit.updateContext(runData, item, "U");
            Audit.saveContext(runData);
            AuthenticationFault aFault = new AuthenticationFault();
            fault.setCode("WSV_ERROR_01");
            fault.setReason(MessageFormat.format(
                    Localization.getGTPString(DefaultResourceProvider.LANGUAGE, "WSV_ERROR_01"),
                    new Object[0]));
            aFault.setFaultMessage(fault);
            throw aFault;
        }
        runData.setUser(authenticatedUser);
        runData.getParameters().setString("company", companyName);
    }

    private static GTPRunData getFakeRunDataForAudit()
            throws TechnicalFault {
        GTPRunData runData = null;
        try {
            runData = InterfaceUtil.getFakeRunDataForAudit();
        } catch (Exception e) {
            LOG.error("Unable to create fake rundata.", e);
            throw getTechnicalFault(e);
        }
        return runData;
    }

    private static String getActionCode(String operationType, String companyType) {
        RequestParametersSet paramSet = new RequestParametersSet();
        RequestParameter param = new RequestParameter("operationType", operationType);
        paramSet.add(param);
        param = new RequestParameter("companyType", companyType);
        paramSet.add(param);
        String actionCode = GTPRequestActionBinder.getActionFromParameters("WSScreen", paramSet);
        return actionCode;
    }

    private static String getActionCode(String operationType, String userProfileCompanyType, String creatorCompanyType) {
        RequestParametersSet paramSet = new RequestParametersSet();
        paramSet.add(new RequestParameter("operationType", operationType));
        paramSet.add(new RequestParameter("userProfileCompanyType", userProfileCompanyType));
        paramSet.add(new RequestParameter("creatorCompanyType", creatorCompanyType));
        String actionCode = GTPRequestActionBinder.getActionFromParameters("WSScreen", paramSet);
        return actionCode;
    }

    private static void auditInvalidActionCode(GTPRunData runData, GTPBusinessValidationException processingException) {
        Audit.audit(runData, "Y");
        AuditItem item = new AuditItem();
        StringBuilder context = new StringBuilder();
        context.append("Error: ").append(
                processingException.getMessageFromKey(DefaultResourceProvider.LANGUAGE));
        item.getContext().setValue(context.toString());
        Audit.updateContext(runData, item, "V");
        Audit.saveContext(runData);
    }
}

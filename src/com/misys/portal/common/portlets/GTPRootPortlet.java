package com.misys.portal.common.portlets;

import com.misys.portal.common.binder.AnchorBuilder;
import com.misys.portal.common.binder.GTPRequestActionBinder;
import com.misys.portal.common.binder.GTPScreen;
import com.misys.portal.common.binder.GTPScreenDescriptor;
import com.misys.portal.common.binder.RequestParameter;
import com.misys.portal.common.binder.RequestParametersSet;
import com.misys.portal.common.bulk.field.StringField;
import com.misys.portal.common.localization.Localization;
import com.misys.portal.common.resources.BusinessCodesResourceProvider;
import com.misys.portal.common.resources.ColorResourceProvider;
import com.misys.portal.common.resources.ImageResourceProvider;
import com.misys.portal.common.resources.URLAliasesResourceProvider;
import com.misys.portal.common.security.GTPSecurityCheck;
import com.misys.portal.common.tools.ConvertTools;
import com.misys.portal.common.tools.RelativeURI;
import com.misys.portal.common.tools.URLListKeys;
import com.misys.portal.common.tracer.GTPException;
import com.misys.portal.common.tracer.GTPSecurityException;
import com.misys.portal.common.tracer.Profiler;
import com.misys.portal.common.widgets.DojoUtils;
import com.misys.portal.common.widgets.FormFieldRow;
import com.misys.portal.common.widgets.dijit.form.FilteringSelect;
import com.misys.portal.common.widgets.dijit.form.Form;
import com.misys.portal.common.widgets.dijit.form.TextBox;
import com.misys.portal.core.portal.Portlet;
import com.misys.portal.core.portal.PortletConfig;
import com.misys.portal.core.portal.PortletControl;
import com.misys.portal.core.portal.PortletException;
import com.misys.portal.core.portal.factory.PortletControlFactory;
import com.misys.portal.core.portal.factory.PortletFactory;
import com.misys.portal.core.portal.factory.PortletRegistry;
import com.misys.portal.core.portal.portlets.AbstractPortlet;
import com.misys.portal.core.util.EngineContext;
import com.misys.portal.core.xml.api.registrymarkup.Parameter;
import com.misys.portal.core.xml.peer.registrymarkup.Entry;
import com.misys.portal.entity.common.Entity;
import com.misys.portal.product.util.builder.ProductFactory;
import com.misys.portal.report.ListDef;
import com.misys.portal.report.viewer.ViewerFactory;
import com.misys.portal.report.viewer.ViewerHTML;
import com.misys.portal.security.GTPCompany;
import com.misys.portal.security.GTPRole;
import com.misys.portal.security.GTPSecurity;
import com.misys.portal.security.GTPUser;
import com.misys.portal.security.util.GTPAccessControlList;
import com.misys.portal.security.util.GTPRoleSet;
import com.misys.portal.services.resources.PortalResources;
import com.misys.portal.services.rundata.GTPRunData;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.Vector;
import org.apache.ecs.ClearElement;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.Document;
import org.apache.ecs.Element;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.StringElement;
import org.apache.ecs.html.A;
import org.apache.ecs.html.Body;
import org.apache.ecs.html.Font;
import org.apache.ecs.html.IMG;
import org.apache.ecs.html.Input;
import org.apache.ecs.html.Option;
import org.apache.ecs.html.Script;
import org.apache.ecs.html.Select;
import org.apache.ecs.html.Span;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.FormMessage;
import org.apache.turbine.util.FormMessages;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.util.template.TemplateInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GTPRootPortlet
        extends AbstractPortlet {

    public static final String NBSP = "&nbsp;";
    public static final String EMPTY_STRING = "";
    public static final String TD_ALIGN_RIGHT = "right";
    public static final String TD_ALIGN_LEFT = "left";
    public static final String TD_ALIGN_MIDDLE = "center";
    public static final String TD_VALIGN_CENTER = "center";
    public static final String CELLSPACING_1 = "1";
    public static final String CELLPADDING_3 = "3";
    public static final String CELLSPACING_0 = "0";
    public static final String CELLPADDING_0 = "0";
    public static final String FONT_SIZE_8 = "font-size: 8pt";
    public static final String FONT_SIZE_10 = "font-size:10pt";
    public static final String FONT_WEIGHT_BOLD = "font-weight: bold";
    public static final String BORDER_0 = "0";
    public static final String SORT_DESC = "desc";
    public static final String SORT_ASC = "asc";
    public static final String IMG_DOWN = "DOWN";
    public static final String IMG_UP = "UP";
    public static final String COLOR_BG_LIST1 = ColorResourceProvider.getBackGroundList1();
    public static final String COLOR_BG_LIST2 = ColorResourceProvider.getBackGroundList2();
    public static final String COLOR_BORDER_LIST = ColorResourceProvider.getBorderList();
    public static final String COLOR_ROW_LIST_TITLE = ColorResourceProvider.getRowListTitle();
    public static final String ACTION_USER_DELETE = "ACTION_USER_DELETE";
    public static final String IMG_TRASH = "TRASH";
    public static final String IMG_LOCK = "LOCK";
    public static final String IMG_PRINTPREVIEW = "PRINTPREVIEW";
    public static final String IMG_CLOSE_BUTTON = "CLOSE_BUTTON";
    public static final String IMG_CLOSE_BUTTON_HOVER = "CLOSE_BUTTON_HOVER";
    public static final String ACTION_USER_DISPLAY = "ACTION_USER_DISPLAY";
    public static final String IMG_DETAILS = "DETAILS";
    public static final String SUBMIT = "SUBMIT";
    public static final String BUTTON = "BUTTON";
    public static final String ACTION_USER_MODIFY = "ACTION_USER_MODIFY";
    public static final String IMG_EDIT = "EDIT";
    public static final String ACTION_USER_SEARCH = "ACTION_USER_SEARCH";
    public static final String FORMPLUS = "FORMPLUS";
    public static final String ACTION_USER_ADD = "ACTION_USER_ADD";
    public static final String LIST_HELP_SDATA = "LIST_HELP_SDATA";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String ACCOUNT_TYPE = "ACCOUNT_TYPE";
    public static final String ACTION_USER_COPY = "ACTION_USER_COPY";
    public static final String IMG_SPECIMEN = "SPECIMEN";
    public static final String ACTION_DISPLAY_SPECIMEN = "ACTION_DISPLAY_SPECIMEN";
    public static final String IMG_CHECKORREVERT = "CHECKORREVERT";
    public static final String ACTION_USER_CHECKORREVERT = "ACTION_CHECKORREVERT";
    public static final String ACTION_CANCEL = "ACTION_CANCEL";
    public static final String IMG_MC_CANCEL = "IMG_MC_CANCEL";
    public static final String IMG_MC_EDIT = "IMG_MC_EDIT";
    public static final String MAKER_CHECKER_SAME_KO = "MAKER_CHECKER_SAME_KO";
    public static final String TECHNICAL_ERROR = "TECHNICAL_ERROR";
    public static final String LIST_ERROR_MSG = "ERROR_MSG_GLOGAL_LIST";
    public static final String RETRIEVE_ERROR_MSG = "ERROR_MSG_PRODUCTFILE_RETRIEVE";
    public static final String GET_ERROR_MSG = "ERROR_MSG_PRODUCTFILE_GET";
    public static final String MSG_GLOGAL_FORM = "ERROR_MSG_GLOGAL_FORM";
    public static final String MSG_COMPANY_RETRIEVE = "ERROR_MSG_COMPANY_RETRIEVE";
    public static final String MSG_FEATURES_IDENTIFY_KO = "FEATURES_IDENTIFY_KO";
    public static final String MSG_FEATURES_ACTION_UPDATE = "FEATURES_ACTION_UPDATE";
    public static final String MSG_FEATURES_ROLE_ALREADY_EXIST = "FEATURES_ROLE_ALREADY_EXIST";
    public static final String FEATURES_USER = "FEATURES_USER";
    public static final String FEATURES_ACTION_OK = "FEATURES_ACTION_OK";
    public static final String FEATURES_ACTION_OK_PENDING = "FEATURES_ACTION_OK_PENDING";
    public static final String ERROR_ACTION_NOT_VALID = "ERROR_ACTION_NOT_VALID";
    public static final String ERROR_MSG_GLOGAL_FORM = "ERROR_MSG_GLOGAL_FORM";
    public static final String ERROR_MSG_DELETEBANK_DELETE = "ERROR_MSG_DELETEBANK_DELETE";
    public static final String ERROR_MSG_DELETEBENEFICIARIES_DELETE = "ERROR_MSG_DELETEBENEFICIARIES_DELETE";
    public static final String ERROR_MSG_DELETEPHRASES_DELETE = "ERROR_MSG_DELETEPHRASES_DELETE";
    public static final String ERROR_MSG_BANK_RETRIEVE = "ERROR_MSG_BANK_RETRIEVE";
    public static final String ERROR_MSG_BENEFICIARY_RETRIEVE = "ERROR_MSG_BENEFICIARY_RETRIEVE";
    public static final String ERROR_MSG_PHRASE_RETRIEVE = "ERROR_MSG_PHRASE_RETRIEVE";
    public static final String ERROR_MSG_SAVEBANKS_SAVE = "ERROR_MSG_SAVEBANKS_SAVE";
    public static final String ERROR_MSG_SAVEBENEFICIARIES_SAVE = "ERROR_MSG_SAVEBENEFICIARIES_SAVE";
    public static final String ERROR_MSG_SAVEPHRASES_SAVE = "ERROR_MSG_SAVEPHRASES_SAVE";
    public static final String FEATURES_ACTION_KO = "FEATURES_ACTION_KO";
    public static final String FEATURES_CUSTOMER_TRANSACTION_UNIQUE_KO = "FEATURES_CUSTOMER_TRANSACTION_UNIQUE_KO";
    public static final String FEATURES_BELONG_KO = "FEATURES_BELONG_KO";
    public static final String FEATURES_EXIST_KO = "FEATURES_EXIST_KO";
    public static final String FEATURES_XSL_NOT_EXIST = "FEATURES_XSL_NOT_EXIST";
    public static final String FEATURES_MISSING_ELEMENT_PASSWORD = "FEATURES_MISSING_ELEMENT_PASSWORD";
    public static final String FEATURES_CUSTOMER = "FEATURES_CUSTOMER";
    public static final String FEATURES_BANK = "FEATURES_BANK";
    public static final String FEATURES_ALERT = "FEATURES_ALERT";
    public static final String FEATURES_ENTITY = "FEATURES_ENTITY";
    public static final String FEATURES_PO_FORM_PARAMETER = "FEATURES_PO_FORM_PARAMETER";
    public static final String FEATURES_IN_FORM_PARAMETER = "FEATURES_IN_FORM_PARAMETER";
    public static final String FEATURES_IP_FORM_PARAMETER = "FEATURES_IP_FORM_PARAMETER";
    public static final String FEATURES_GROUPING_RULE = "FEATURES_GROUPING_RULE";
    public static final String FEATURES_SYSTEM_PARAMETERS = "FEATURES_SYSTEM_PARAMETERS";
    public static final String FEATURES_SUBSCRIPTION_PACKAGE = "FEATURES_SUBSCRIPTION_PACKAGE";
    public static final String ACTION_SAVE_STATIC_DATA_SUCCESSFUL = "ACTION_SAVE_STATIC_DATA_SUCCESSFUL";
    public static final String OPTION = "option";
    public static final String OPERATION = "operation";
    public static final String NEXT_SCREEN = "nextscreen";
    public static final String ORDER = "order";
    public static final String RUNDATA = "rundata";
    private static final int selectEntityMaxSize = 4;
    protected static final String LISTDEF_EXT = ".xml";
    protected static final String XML_ENTITIES_NODE = "entities";
    public static final String FEATURES_AUTHORISATION = "FEATURES_AUTHORISATION";
    public static final String TOKEN_KO = "TOKEN_KO";
    public static final String MAKER_CHECKER_SAME_COMPANY_KO = "MAKER_CHECKER_SAME_COMPANY_KO";
    public static final String ACTOR_ALL = "ACTOR_ALL";
    public static final String ACTOR_MAKER = "ACTOR_MAKER";
    public static final String ACTOR_CHECKER = "ACTOR_CHECKER";
    public static final String FEATURES_UPLOAD_FORMAT = "FEATURES_UPLOAD_FORMAT";
    public static final String FEATURES_ACTION_DELETE = "FEATURES_ACTION_DELETE";
    public static final String FEATURES_ACTION_SAVE = "FEATURES_ACTION_SAVE";
    public static final String FILE_FORMAT_NAME = "FILE_FORMAT_NAME";
    public static final String SCREEN_STRING = "screen";
    public static final String PRODUCTCODE_STRING = "productcode";
    protected int defaultPageSize = 25;

    public void addGridComparator(RunData data, String gridId, Map columnSortFunctions) {
        Script script = new Script();
        script.removeAttribute("language");
        script.addElement("dojo.subscribe('ready', function(){");
        String gridVarName = gridId + "Store";
        script.addElement("var "
                + gridVarName
                + " = dijit.byId('"
                + gridId
                + "').store;"
                + gridVarName
                + ".comparatorMap = {};");
        for (Iterator i = columnSortFunctions.keySet().iterator(); i.hasNext();) {
            String key = (String) i.next();
            String function = (String) columnSortFunctions.get(key);
            script.addElement(gridVarName + ".comparatorMap['" + key + "'] = " + function + ";");
        }
        script.addElement("});");
        data.getPage().getBody().addElement(script);
    }

    public A buildAnchorSort(String menuText, RunData data, String orderCol, String orderType) {
        return new A("javascript:fncSortList('" + orderCol + "','" + orderType + "')");
    }

    public Element buildCheckbox(String ref_id, String tnx_id, String product_code) {
        ArrayList keys = new ArrayList();
        keys.add(ref_id);
        keys.add(tnx_id);
        keys.add(product_code);

        Input gridCheckbox = new Input().setType("checkbox");
        gridCheckbox.addAttribute("dojoType", "dijit.form.CheckBox");
        gridCheckbox.addAttribute("onclick", "fncCheckSelectAll(this)");
        gridCheckbox.setClass("gridCheckbox");
        gridCheckbox.setName(URLListKeys.buildReference(keys));
        gridCheckbox.setID(URLListKeys.buildReference(keys));

        return gridCheckbox;
    }

    public A buildPageAnchor(Element content, String pageOffset) {
        return new A("javascript:fncPageList('" + ConvertTools.filterString(pageOffset) + "')", content);
    }

    public Form completeForm(RunData data, ElementContainer formContents) {
        ParameterParser params = data.getParameters();

        RelativeURI duri = new RelativeURI(EngineContext.getServerData());

        duri.setScreen(data.getScreen());

        Form form = new Form("TransactionSearchForm");
        form.addAttribute("method", "POST");
        form.addAttribute("action", duri.toString());
        form.addElement(formContents);

        GTPScreen gtpScreen = GTPRequestActionBinder.getGTPScreen(data.getScreen());
        Vector parameters = new Vector();

        parameters.addAll(gtpScreen.getBindingParameters());
        parameters.addAll(gtpScreen.getMethodParameters());
        Iterator ite = parameters.iterator();
        while (ite.hasNext()) {
            String key = (String) ite.next();
            String value = params.getString(key);
            if ((value != null)
                    && (!"".equals(value))
                    && (!"-".equals(value))) {
                TextBox hiddenField = new TextBox(key);
                hiddenField.setValue(value);
                hiddenField.setType("hidden");
                form.addElement(hiddenField);
            }
        }
        return form;
    }

    public Element createPageAnchors(int pageOffset, int pageCount, int pageSize) {
        TR content = new TR();
        if (pageOffset > 1) {
            A previousAnchor = this.buildPageAnchor((Element) new IMG(ImageResourceProvider.getImage((String) "PREVIOUS_MONTH_IMAGE")).setBorder(0), String.valueOf(pageOffset - pageSize));
            content.addElement((Element) new TD().addElement((Element) previousAnchor));
        }
        if (pageCount > 1) {
            int i = 0;
            while (i < pageCount) {
                double currentPageOffset = i * pageSize + 1;
                if (pageOffset == i * pageSize + 1) {
                    content.addElement((Element) new TD().addElement(String.valueOf(i + 1)));
                } else if ((double) (pageOffset - 10 * pageSize) < currentPageOffset && currentPageOffset < (double) (pageOffset + 10 * pageSize) || i >= pageCount - 10 || i < 10) {
                    A pageAnchor = this.buildPageAnchor((Element) new StringElement(String.valueOf(i + 1)), String.valueOf(i * pageSize + 1));
                    content.addElement((Element) new TD().addElement((Element) pageAnchor));
                } else if ((double) (pageOffset - 10 * pageSize) == currentPageOffset) {
                    content.addElement((Element) new TD().addElement("..."));
                } else if (currentPageOffset == (double) (pageOffset + 10 * pageSize)) {
                    content.addElement((Element) new TD().addElement("..."));
                }
                ++i;
            }
        }
        if (1 + (int) ((double) (pageOffset / pageSize) + 0.5) < pageCount) {
            A nextAnchor = this.buildPageAnchor((Element) new IMG(ImageResourceProvider.getImage((String) "NEXT_MONTH_IMAGE")).setBorder(0), String.valueOf(pageOffset + pageSize));
            content.addElement((Element) new TD().addElement((Element) nextAnchor));
        }
        return content;
    }

    public boolean getAllowEdit(RunData data) {
        return false;
    }

    public boolean getAllowMaximize(RunData data) {
        return false;
    }

    public ConcreteElement getContent(RunData data) {
        GTPUser user = (GTPUser) data.getUser();
        ElementContainer root = new ElementContainer();
        try {
            GTPScreenDescriptor screenDescriptor = GTPRequestActionBinder.getScreenDescriptor(
                    data.getScreen(),
                    data.getParameters());
            String className = screenDescriptor.getSecondClassName();
            String methodName = screenDescriptor.getSecondMethodName();

            Class[] argumentsClasses
                    = {RunData.class};

            PortletControl thePortletControl = PortletControlFactory.getInstance(PortletFactory.getPortlet(
                    className,
                    data));

            Entry regEntry = PortletRegistry.getInstance()
                    .getEntry(className);

            Dictionary<String, String> initParams = getParameters(regEntry);
            for (Enumeration<String> iter = initParams.keys(); iter.hasMoreElements();) {
                String key = (String) iter.nextElement();
                String value = (String) initParams.get(key);
                thePortletControl.getPortletConfig().setInitParameter(key, value);
            }
            Method portletMethod = thePortletControl.getPortlet()
                    .getClass()
                    .getMethod(methodName, argumentsClasses);
            Object[] arguments
                    = {data};

            Profiler profiler = Profiler.inst();
            profiler.start(getClass(), "GTP Root Portlet");

            ConcreteElement content = (ConcreteElement) portletMethod.invoke(
                    thePortletControl.getPortlet(),
                    arguments);

            profiler.stop(getClass(), "GTP Root Portlet");
            profiler.print(getClass(), "GTP Root Portlet", "Root Portlet Processing "
                    + methodName
                    + " ");
            profiler.release(getClass(), "GTP Root Portlet");

            root.addElement(content);
        } catch (IllegalAccessException iae) {
            root = new GTPException(data, "GTP Root Portlet Illegal Access Error", iae).toECS(
                    user.getLanguage(),
                    "ERROR_MSG_GLOGAL_SCREEN");
        } catch (NoSuchMethodException nsme) {
            root = new GTPException(data, "GTP Root Portlet No Such Method Error", nsme).toECS(
                    user.getLanguage(),
                    "ERROR_MSG_GLOGAL_SCREEN");
        } catch (InvocationTargetException ite) {
            if ((ite.getTargetException() instanceof GTPSecurityException)) {
                throw ((GTPSecurityException) ite.getTargetException());
            }
            root = new GTPException(data, "GTP Root Portlet Invocation Target Error", ite).toECS(
                    user.getLanguage(),
                    "ERROR_MSG_GLOGAL_SCREEN");
        } catch (Exception ite) {
            root = new GTPException(data, "GTP Root Portlet Invocation Target Error", ite).toECS(
                    user.getLanguage(),
                    "ERROR_MSG_GLOGAL_SCREEN");
        }
        return root;
    }

    public RelativeURI getCurrentURI(RunData data) {
        return new RelativeURI(EngineContext.getServerData(), data.getScreen());
    }

    public Element getTitle(RunData data) {
        GTPScreenDescriptor screenDescriptor = GTPRequestActionBinder.getScreenDescriptor(
                data.getScreen(),
                data.getParameters());
        if (screenDescriptor != null) {
            String className = screenDescriptor.getSecondClassName();
            try {
                Portlet p = PortletFactory.getPortlet(className, data);
                if ((p instanceof ActionPortlet)) {
                    PortletControl thePortletControl = PortletControlFactory.getInstance(p);

                    Class[] argumentsClasses
                            = {RunData.class};
                    Method getTitle = thePortletControl.getPortlet()
                            .getClass()
                            .getMethod("getTitle", argumentsClasses);
                    Object[] arguments
                            = {data};

                    return (Element) getTitle.invoke(thePortletControl.getPortlet(), arguments);
                }
            } catch (Exception localException1) {
                return null;
            }
        }
        if ((getPortletConfig() != null) && (getPortletConfig().getInitParameter("title_key") != null)) {
            return new ClearElement(Localization.getGTPString(
                    ((GTPUser) data.getUser()).getLanguage(),
                    getPortletConfig().getInitParameter("title_key")));
        }
        String actioncode = GTPRequestActionBinder.getScreenDescriptor(
                data.getScreen(),
                data.getParameters()) != null ? GTPRequestActionBinder.getScreenDescriptor(
                                data.getScreen(),
                                data.getParameters()).getActionCode() : null;
        if (actioncode != null) {
            try {
                return new ClearElement(Localization.getString(
                        ((GTPUser) data.getUser()).getLanguage(),
                        actioncode));
            } catch (Exception localException2) {
                return new ClearElement(actioncode);
            }
        }
        return null;
    }

    public Element getTitleFromPortletContext(RunData data) {
        return (Element) data.getTemplateInfo().getTemp(getName() + "_TITLE");
    }

    public Element getTitleWithLink(RunData data) {
        ElementContainer ec = new ElementContainer();
        if (getPortletConfig() != null) {
            String title;
            try {
                title = getPortletConfig().getInitParameter("title_key") == null ? "&nbsp;"
                        : Localization.getString(
                                ((GTPUser) data.getUser()).getLanguage(),
                                getPortletConfig().getInitParameter("title_key"));
            } catch (MissingResourceException localMissingResourceException) {
                //String title;
                title = getTitleFromPortletContext(data).toString();
            }
            if ((getPortletConfig().getInitParameter("url_screen") != null)
                    && (getPortletConfig().getInitParameter("url_param_number") != null)) {
                RequestParametersSet rps = new RequestParametersSet();
                for (int i = 0; i < Integer.parseInt(getPortletConfig().getInitParameter(
                        "url_param_number")); i++) {
                    rps.add(new RequestParameter(getPortletConfig().getInitParameter(
                            "url_param_" + i + "_name"), getPortletConfig().getInitParameter(
                                    "url_param_" + i + "_value")));
                }
                ec.addElement(new Span().addElement(title));
                IMG img = getPortletConfig().getInitParameter("url_icon") != null ? new IMG(
                        EngineContext.getContextPath() + getPortletConfig().getInitParameter("url_icon"))
                        : new IMG(ImageResourceProvider.getImage("THEAD_ARROW_RIGHT")).setAlt(" ");

                String tooltip = Localization.getGTPString(
                        ((GTPUser) data.getUser()).getLanguage(),
                        "HOMEPAGE_TASK_LIST_TOOLTIP");
                img.setAlt(tooltip);
                img.setTitle(tooltip);

                ec.addElement(new Span().addElement(
                        AnchorBuilder.newGETAnchor(
                                img,
                                getPortletConfig().getInitParameter("url_screen"),
                                rps)).setClass("portlet-title-addons"));
            } else {
                ec.addElement(new Span().addElement(title));
            }
        }
        return ec;
    }

    public void init()
            throws PortletException {
    }

    public RequestParametersSet initReqParamSet(RunData data) {
        RequestParametersSet requestParametersSet = new RequestParametersSet();

        ParameterParser params = data.getParameters();
        Object[] paramsKeys = params.getKeys();
        for (int i = 0; i < paramsKeys.length; i++) {
            String currentKey = (String) paramsKeys[i];
            String currentValue = params.getString(currentKey);
            if ((!"screen".equals(currentKey))
                    && (!"firstportletmethod".equals(currentKey))
                    && (!"secondportletmethod".equals(currentKey))
                    && (!"order_col".equals(currentKey))
                    && (!"order_type".equals(currentKey))) {
                if ((currentValue != null) && ((!"".equals(currentValue)) || ("-".equals(currentValue)))) {
                    requestParametersSet.add(new RequestParameter(currentKey, currentValue));
                }
            }
        }
        return requestParametersSet;
    }

    public ConcreteElement openGeneralModule(RunData data) {
        ElementContainer root = new ElementContainer();
        String language = ((GTPUser) data.getUser()).getLanguage();
        GTPScreen gtpScreen = GTPRequestActionBinder.getGTPScreen(data.getScreen());
        String path = data.getScreen();
        try {
            path
                    = PortalResources.getResource(gtpScreen.getDefaultStaticURL() + "_" + language + ".html").toString();
            root.addElement(URLAliasesResourceProvider.getPage(
                    path,
                    Localization.getDefaultCharSet(language)));
        } catch (Exception e) {
            String error = "Open General, Exception, Path : " + path;
            GTPException gtpException = new GTPException(data, error, e);
            return gtpException.toECS(language, "ERROR_MSG_GLOGAL_FORM");
        }
        return root;
    }

    public void setTitleToPortletContext(RunData data, ConcreteElement ce) {
        data.getTemplateInfo().setTemp(getName() + "_TITLE", ce);
    }

    public void severalDeleteAction(RunData data, ElementContainer root, String listKeys)
            throws GTPException {
        try {
            ListDef fakeList = new ListDef();
            fakeList.setListKeys(listKeys);
            fakeList.severalDeleteAction(data);

            ViewerHTML fakeViewer = (ViewerHTML) ViewerFactory.newViewer("html");
            fakeViewer.setListDef(fakeList);
            fakeViewer.severalDeleteActionResult(root);
        } catch (Exception e) {
            throw new GTPException(e);
        }
    }

    public void severalSubmitAction(RunData data, ElementContainer root, String listKeys)
            throws GTPException {
        try {
            ListDef fakeList = new ListDef();
            fakeList.setListKeys(listKeys);
            fakeList.severalSubmitAction(data);

            ViewerHTML fakeViewer = (ViewerHTML) ViewerFactory.newViewer("html");
            fakeViewer.setListDef(fakeList);

            Profiler profiler = Profiler.inst();
            profiler.start(getClass(), "GTP Root Portlet Several Submit");

            fakeViewer.setUser((GTPUser) data.getUser());
            fakeViewer.severalSubmitActionResult(data, root);

            profiler.stop(getClass(), "GTP Root Portlet Several Submit");
            profiler.print(getClass(), "GTP Root Portlet Several Submit", "Several Submit");
            profiler.release(getClass(), "GTP Root Portlet Several Submit");
        } catch (Exception e) {
            throw new GTPException(e);
        }
    }

    public ConcreteElement showMessages(RunData data, String formName)
            throws Exception {
        StringBuffer sb = new StringBuffer();

        String colorMessage = ColorResourceProvider.getMessage();
        String colorTableBackground = ColorResourceProvider.getBackGroundList2();
        String colorBackground = ColorResourceProvider.getTitleColor();

        sb.append(ImageResourceProvider.getImage("NOTICE"));

        ElementContainer ec = new ElementContainer();
        ec.addElement(new IMG(sb.toString(), 0).setWidth(32).setHeight(24).setAlign("left"));

        FormMessage[] messages = data.getMessages().getFormMessages(getName());
        if (messages == null) {
            return null;
        }
        for (int i = 0; i < messages.length; i++) {
            ec.addElement(new Font().addElement(messages[i].getMessage()).setSize("-1"));
        }
        ec.addElement(new Font().addElement(data.getMessage()).setSize("-1"));
        Table outTable = new Table().setCellPadding(1)
                .setCellSpacing(1)
                .setBorder(0)
                .setBgColor(colorBackground)
                .setAlign("center")
                .setWidth("100%");

        Table inTable = new Table().setCellPadding(1)
                .setCellSpacing(0)
                .setBgColor(colorTableBackground)
                .setWidth("100%");
        inTable.addElement(new TR().addElement(new TD().addElement(ec)).setBgColor(colorMessage));

        return outTable.addElement(new TR().addElement(new TD().setWidth("100%")
                .setVAlign("top")
                .addElement(inTable)));
    }

    private Dictionary getParameters(Entry entry) {
        Hashtable hash = new Hashtable();
        if (entry != null) {
            Parameter[] param = entry.getParameter();
            for (int i = 0; i < param.length; i++) {
                hash.put(param[i].getName(), param[i].getValue());
            }
        }
        return hash;
    }

    protected boolean addEntitiesInSelect(Select selectEntity, String[] searchDept, boolean hasSearch, RunData data)
            throws GTPException {
        Profiler profiler = Profiler.inst();
        profiler.start(getClass(), "GTP Root Portlet Build Entities List");

        selectEntity.setName("entity").setMultiple(true);

        Vector v = new Vector();
        for (int i = 0; (searchDept != null) && (i < searchDept.length); i++) {
            v.add(searchDept[i]);
        }
        if (((searchDept == null) || (searchDept.length == 0)) && (hasSearch)) {
            selectEntity.addElement(new Option().addElement("&nbsp;").setSelected(true));
        } else {
            selectEntity.addElement(new Option().addElement("&nbsp;")
                    .setSelected(v.contains(""))
                    .setValue(""));
        }
        List entities = Entity.retrieveFromUser(
                Integer.toString(((GTPUser) data.getUser()).getCompanyId()),
                data.getUser().getName());
        Iterator ite = entities.iterator();
        Vector preferredEntities = new Vector();
        Vector userEntities = new Vector();
        Entity entity = null;
        while (ite.hasNext()) {
            entity = (Entity) ite.next();
            if (entity.isPrefered()) {
                preferredEntities.add(entity.getAbbv_name().getValue());
            } else {
                userEntities.add(entity.getAbbv_name().getValue());
            }
        }
        Vector allEntities = new Vector(preferredEntities);
        allEntities.addAll(userEntities);
        for (int j = 0; j < allEntities.size(); j++) {
            Option opt = new Option().addElement((String) allEntities.get(j)).setValue(
                    (String) allEntities.get(j));
            if (((searchDept == null) || (searchDept.length == 0)) && (!hasSearch)) {
                opt.setSelected(preferredEntities.contains(allEntities.get(j)));
            } else if (((searchDept == null) || (searchDept.length == 0)) && (hasSearch)) {
                opt.setSelected(true);
            } else {
                opt.setSelected(v.contains(allEntities.get(j)));
            }
            selectEntity.addElement(opt);
        }
        if (allEntities.size() > 3) {
            selectEntity.setSize(4);
        } else {
            selectEntity.setSize(allEntities.size() + 1);
        }
        profiler.stop(getClass(), "GTP Root Portlet Build Entities List");
        profiler.print(getClass(), "GTP Root Portlet Build Entities List", "Build Entities List");
        profiler.release(getClass(), "GTP Root Portlet Build Entities List");

        return !entities.isEmpty();
    }

    protected void addProductInSelect(RunData data, Select selectProductCode, String productCode, String searchProductCode) {
        selectProductCode.addElement(new Option(
                BusinessCodesResourceProvider.getValueOfBusinessCode("N001_" + productCode)).setSelected(
                BusinessCodesResourceProvider.getValueOfBusinessCode("N001_" + productCode).equals(
                searchProductCode))
                .addElement(
                        Localization.getDecode(
                                ((GTPUser) data.getUser()).getLanguage(),
                                "N001",
                                productCode)));
    }

    protected List<FormFieldRow> buildAreaProductSubProductLinkedSelects(RunData data, ElementContainer scriptContainer, GTPAccessControlList acl, boolean useCompanyACL)
            throws JSONException {
        GTPUser user = (GTPUser) data.getUser();
        String language = user.getLanguage();
        GTPAccessControlList internalACL;

        if (acl == null) {
            internalACL = useCompanyACL ? ((GTPRunData) data).getCompanyAcl()
                    : ((GTPRunData) data).getUserAcl();
        } else {
            internalACL = acl;
        }
        boolean isCustomer = GTPSecurity.isCustomer(data);

        ArrayList<FormFieldRow> result = new ArrayList(3);

        FormFieldRow productsRow = new FormFieldRow("product_code", Localization.getGTPString(
                language,
                "PRODUCTCODE_LABEL"));
        FilteringSelect productCode = new FilteringSelect("product_code");
        productCode.addAttribute("maxlength", "20");
        productCode.addAttribute("class", "small");
        productsRow.addElement(productCode);
        result.add(productsRow);

        FormFieldRow subProductsRow = new FormFieldRow("sub_product_code", Localization.getGTPString(
                language,
                "SUBPRODUCTCODE_LABEL"));
        FilteringSelect subProductCode = new FilteringSelect("sub_product_code");
        subProductCode.addAttribute("maxlength", "20");
        subProductCode.addAttribute("class", "small");
        subProductsRow.addElement(subProductCode);
        result.add(subProductsRow);

        JSONObject productAll = new JSONObject();
        productAll.put("value", "*");
        productAll.put("name", "*");

        List<String> products = ProductFactory.getAvailableProductsList();
        JSONArray productsArray = new JSONArray();
        JSONObject productJSON =new JSONObject();
        for (String currentProductCode : products) {
            String permission = GTPSecurityCheck.getProductPermission(currentProductCode, isCustomer);
            if (internalACL.hasPermission(permission, "global")) {
                productJSON = new JSONObject();
                productJSON.put("value", currentProductCode);
                productJSON.put("name", Localization.getDecode(language, "N001", currentProductCode));
                productsArray.put(productJSON);
            }
        }
        productsArray.put(productAll);

        JSONObject subProductAll = new JSONObject();
        subProductAll.put("value", "*");
        subProductAll.put("name", "*");

        for (String currentProductCode : products) {
            String permission = GTPSecurityCheck.getProductPermission(currentProductCode, isCustomer);
            if (internalACL.hasPermission(permission, "global")) {
                List<String> subProductCodes = ProductFactory.getSubProductCode(currentProductCode);
                JSONArray subProductsArray = new JSONArray();
                for (String currentSubProductCode : subProductCodes) {
                    JSONObject subProductJSON = new JSONObject();
                    subProductJSON.put("value", currentSubProductCode);
                    subProductJSON.put(
                            "name",
                            Localization.getDecode(language, "N047", currentSubProductCode));
                    subProductsArray.put(subProductJSON);
                }
                subProductsArray.put(subProductAll);
                productJSON.put(currentProductCode, subProductsArray);
            }
        }
        productJSON.put("ALL", new JSONArray().put(subProductAll));

        String mixinFunction = "dojo.ready(function(){misys._config = misys._config || {};dojo.mixin(misys._config, { ProductsCollection : "
                + productsArray.toString(4)
                + ",SubProductsCollection : "
                + productJSON.toString(4)
                + "});});";

        String[] dojoPackages
                = {"misys.binding.system.linked_product_select"};
        Script script = DojoUtils.createImportScript(dojoPackages);
        script.addElement(mixinFunction);
        scriptContainer.addElement(script);

        return result;
    }

    protected void buildCodeList(Select selectCode, String searchCode, String language, String codePrefix) {
        List codes = BusinessCodesResourceProvider.getValues(codePrefix);
        Iterator ite = codes.iterator();
        while (ite.hasNext()) {
            String code = (String) ite.next();
            selectCode.addElement(new Option(code).setSelected(code.equals(searchCode)).addElement(
                    Localization.getDecode(language, codePrefix, code)));
        }
    }

    protected void buildProductList(RunData data, GTPAccessControlList acl, Select selectProductCode, String searchProductCode, boolean isCustomer) {
        Iterator supportedProducts = ProductFactory.getAvailableProductsIterator();
        String productCode = null;
        String permission = null;
        boolean isBank = GTPSecurity.isBank(data);
        while (supportedProducts.hasNext()) {
            productCode = (String) supportedProducts.next();
            permission = GTPSecurityCheck.getProductPermission(productCode, isCustomer);
            if ((acl.hasPermission(permission, "global")) && ((!productCode.equals("FX")) || (!isBank))) {
                addProductInSelect(data, selectProductCode, productCode, searchProductCode);
            }
        }
    }

    protected void buildProductList(RunData data, Select selectProductCode, String searchProductCode, boolean isCompany) {
        GTPAccessControlList acl = null;
        if (isCompany) {
            acl = ((GTPRunData) data).getCompanyAcl();
        } else {
            acl = ((GTPRunData) data).getUserAcl();
        }
        buildProductList(data, acl, selectProductCode, searchProductCode, GTPSecurity.isCustomer(data));
    }

    protected void buildRoleList(GTPCompany company, Select selectRole, String searchRole, boolean isCompany)
            throws GTPException {
        GTPAccessControlList companyACL = GTPSecurity.getACL(company);
        GTPRoleSet companyRoles = companyACL.getRoles();
        for (Iterator ite = companyRoles.elements(); ite.hasNext();) {
            String roleName = ((GTPRole) ite.next()).getName();
            selectRole.addElement(new Option(roleName).setSelected(roleName.equals(searchRole))
                    .addElement(roleName));
        }
    }

    protected String getTopParent(PortletRegistry myRegistry, String name) {
        String parent = myRegistry.getEntry(name).getParent();
        if (parent != null) {
            Entry entry;
            do {
                entry = myRegistry.getEntry(parent);
                parent = entry.getParent();
            } while (parent != null);
            return entry.getName();
        }
        return null;
    }
}

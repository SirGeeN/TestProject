package com.misys.portal.common.screens;

import com.misys.portal.common.binder.GTPRequestActionBinder;
import com.misys.portal.common.binder.GTPScreen;
import com.misys.portal.common.binder.GTPScreenDescriptor;
import com.misys.portal.common.localization.Localization;
import com.misys.portal.common.security.GTPSecurityCheck;
import com.misys.portal.common.services.Audit;
import com.misys.portal.common.tracer.GTPException;
import com.misys.portal.common.tracer.GTPSecurityException;
import com.misys.portal.common.tracer.Log;
import com.misys.portal.common.tracer.Profiler;
import com.misys.portal.core.om.profile.PSMLDocument;
import com.misys.portal.core.om.profile.ScreenProfile;
import com.misys.portal.core.portal.Portlet;
import com.misys.portal.core.portal.PortletException;
import com.misys.portal.core.portal.factory.PortletFactory;
import com.misys.portal.core.services.psmlmanager.PsmlManagerService;
import com.misys.portal.core.util.JetspeedResources;
import com.misys.portal.interfaces.core.Bundle;
import com.misys.portal.interfaces.core.Environment;
import com.misys.portal.interfaces.services.Interfaces;
import com.misys.portal.product.util.TransactionProductFile;
import com.misys.portal.security.GTPUser;
import com.misys.portal.services.rundata.GTPRunData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.configuration.Configuration;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.Document;
import org.apache.ecs.html.Div;
import org.apache.ecs.html.Head;
import org.apache.ecs.html.Meta;
import org.apache.turbine.Turbine;
import org.apache.turbine.modules.Screen;
import org.apache.turbine.modules.ScreenLoader;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.ServiceManager;
import org.apache.turbine.services.TurbineServices;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.util.template.TemplateInfo;

public class StandardScreen
  extends Screen
{
  public ConcreteElement doBuild(RunData data)
    throws PortletException, Exception
  {
    Profiler profiler = Profiler.inst();
    profiler.start(getClass(), "Standard Screen");
    
    data.getTemplateInfo().removeTemp("__interface_data_source");
    
    Div root = new Div();
    root.addAttribute("class", "maincontent");
    
    String language = ((GTPUser)data.getUser()).getLanguage();
    
    data.getPage()
      .getHead()
      .addElement(new Meta().setHttpEquiv("content-type").setContent("text/html"));
    
    ParameterParser parameters = data.getParameters();
    
    GTPScreenDescriptor screenDescriptor = null;
    GTPScreen gtpScreen = null;
    try
    {
      screenDescriptor = GTPRequestActionBinder.getScreenDescriptor(data.getScreen(), parameters);
      gtpScreen = GTPRequestActionBinder.getGTPScreen(data.getScreen());
      if (gtpScreen == null) {
        throw new Exception("StandardScreen, do Build, screen name is invalid. screen name=" + 
          data.getScreen());
      }
    }
    catch (Exception e)
    {
      String screen = data.getScreen();
      Object[] keys = data.getParameters().getKeys();
      StringBuffer parametersList = new StringBuffer();
      for (int i = 0; i < keys.length; i++)
      {
        String key = keys[i].toString();
        String value = parameters.getString(key);
        parametersList.append("/parameter key=").append(key).append(" and value=").append(value);
      }
      new GTPException(data, "StandardScreen, doBuild, Unable to build the screen. Screen=" + 
        screen + 
        ", parameters:" + 
        parametersList.toString(), e);
      
      data.setMessage(Localization.getString(language, "ERROR_MSG_SCREEN"));
      data.setScreen(Turbine.getConfiguration().getString("screen.homepage"));
      
      return ScreenLoader.getInstance().eval(
        data, 
        Turbine.getConfiguration().getString("screen.homepage"));
    }
    List parametersVector = new ArrayList();
    if (screenDescriptor == null) {
      return returnHomePage(data);
    }
    parametersVector.addAll(gtpScreen.getBindingParameters());
    parametersVector.addAll(gtpScreen.getMethodParameters());
    
    Audit.audit(data, "Y");
    if (!GTPSecurityCheck.sanitizeParameters(data))
    {
      Audit.audit(data, "C");
      return returnHomePage(data);
    }
    String permission = screenDescriptor.getPermissionId();
    GTPUser user = (GTPUser)data.getUser();
    if (!GTPSecurityCheck.hasPermission(
      ((GTPRunData)data).getUserAcl(), 
      permission, 
      user.getEntities()))
    {
      Log.warn(StandardScreen.class, "Security : User (" + 
        data.getUser().getName() + 
        "/" + 
        ((GTPUser)data.getUser()).getCompany_abbv_name() + 
        ") has not required permission :" + 
        permission);
      
      Audit.audit(data, "N");
      return returnHomePage(data);
    }
    if ((screenDescriptor.isCheckContextActivated()) && (!GTPSecurityCheck.checkContext(data)))
    {
      Log.warn(StandardScreen.class, "Security : User (" + 
        data.getUser().getName() + 
        "/" + 
        ((GTPUser)data.getUser()).getCompany_abbv_name() + 
        ") has no access to record.");
      
      Audit.audit(data, "C");
      return returnHomePage(data);
    }
    PsmlManagerService psmlService = (PsmlManagerService)TurbineServices.getInstance().getService(
      "PsmlScreenManager");
    ScreenProfile locator = new ScreenProfile();
    locator.setLanguage(language);
    locator.setActionCode(screenDescriptor.getActionCode());
    PSMLDocument document = psmlService.getDocument(locator);
    Portlet set = PortletFactory.getPortlets(document.getPortlets(), data, false, false);
    if (set != null) {
      try
      {
        root.setID(document.getName().substring(0, document.getName().indexOf('.')));
        root.addElement(set.getContent(data));
      }
      catch (GTPSecurityException localGTPSecurityException)
      {
        return returnHomePage(data);
      }
      catch (Exception e)
      {
        Audit.audit(data, "E");
        Audit.saveContext(data);
        throw e;
      }
    }
    try
    {
      String title = Localization.getString(language, screenDescriptor.getActionCode());
      data.setTitle(Localization.getGTPString(language, "MAIN_TITLE") + " - " + title);
    }
    catch (Exception localException1)
    {
      data.setTitle(Localization.getGTPString(language, "MAIN_TITLE"));
    }
    profiler.start(getClass(), "Standard Screen, interfaces");
    
    runInterfaceFramework(data, screenDescriptor.getActionCode());
    
    profiler.stop(getClass(), "Standard Screen, interfaces");
    profiler.print(getClass(), "Standard Screen, interfaces", "interfaces for all files ");
    profiler.release(getClass(), "Standard Screen, interfaces");
    
    profiler.stop(getClass(), "Standard Screen");
    profiler.print(getClass(), "Standard Screen", "Build screen " + data.getScreen() + " ");
    profiler.release(getClass(), "Standard Screen");
    
    Audit.saveContext(data);
    
    return root;
  }
  
  public String getLayout(RunData data)
  {
    GTPScreen gtpScreen = GTPRequestActionBinder.getGTPScreen(data.getScreen());
    if (gtpScreen != null) {
      return gtpScreen.getLayout() != null ? gtpScreen.getLayout() : JetspeedResources.getString(
        "services.GTPDefaultTemplateService.default.layout", 
        "DefaultGTPLayout");
    }
    return JetspeedResources.getString(
      "services.GTPDefaultTemplateService.default.layout", 
      "DefaultGTPLayout");
  }
  
  private ConcreteElement returnHomePage(RunData data)
    throws Exception
  {
    if (data.getMessage() == null)
    {
      String errorMessage = Localization.getGTPString(
        ((GTPUser)data.getUser()).getLanguage(), 
        "UNAUTHORISED_ACTION");
      data.setMessage(errorMessage);
    }
    data.setScreen(Turbine.getConfiguration().getString("screen.homepage", "GTPHome"));
    
    return ScreenLoader.getInstance().eval(data, data.getScreen());
  }
  
  private void runInterfaceFramework(RunData data, String actionCode)
  {
    GTPUser user = (GTPUser)data.getUser();
    
    Profiler profiler = Profiler.inst();
    profiler.start(getClass(), "Standard Screen, interfaces");
    if (data.getTemplateInfo().getTemp("__interface_data_source") != null) {
      try
      {
        Object o = data.getTemplateInfo().getTemp(
          "__interface_data_source");
        List objects;
        if (!(o instanceof List))
        {
          List tempVector = new ArrayList();
          tempVector.add(o);
          objects = tempVector;
        }
        else
        {
          objects = (List)o;
        }
        for (Iterator ite = objects.iterator(); ite.hasNext();)
        {
          Object object = ite.next();
          TransactionProductFile tnx = null;
          String tempActionCode = null;
          if ((object instanceof TransactionProductFile))
          {
            tnx = (TransactionProductFile)object;
            
            tempActionCode = actionCode + 
              "." + 
              tnx.getProduct_code() + 
              "." + 
              tnx.getTnx_type_code() + 
              "." + 
              tnx.getSub_tnx_type_code();
            
            Bundle bundle = Interfaces.getBundle(tempActionCode);
            if (bundle != null)
            {
              bundle.getEnvironment().setEntry(
                "user", 
                Interfaces.clone(user));
              bundle.run(tnx);
            }
          }
          else
          {
            Bundle bundle = Interfaces.getBundle(actionCode);
            if (bundle != null)
            {
              bundle.getEnvironment().setEntry(
                "user", 
                Interfaces.clone(user));
              bundle.run(object);
            }
          }
          profiler.stop(getClass(), "Standard Screen, interfaces (1 file)");
          if ((tnx != null) && (tempActionCode != null)) {
            profiler.print(
              getClass(), 
              "Standard Screen, interfaces (1 file)", 
              "interfaces for 1 files " + 
              tnx.getRef_id() + 
              "/" + 
              tnx.getTnx_id() + 
              "/" + 
              tempActionCode);
          } else {
            profiler.print(
              getClass(), 
              "Standard Screen, interfaces (1 file)", 
              "interfaces for 1 files ");
          }
          profiler.release(getClass(), "Standard Screen, interfaces (1 file)");
        }
      }
      catch (Exception e)
      {
        new GTPException(data, "An error occured during the interfaces jobs.", e);
      }
    }
    profiler.stop(getClass(), "Standard Screen, interfaces");
    profiler.print(getClass(), "Standard Screen, interfaces", "interfaces for all files ");
    profiler.release(getClass(), "Standard Screen, interfaces");
    
    profiler.stop(getClass(), "Standard Screen");
    profiler.print(getClass(), "Standard Screen", "Build screen " + data.getScreen() + " ");
    profiler.release(getClass(), "Standard Screen");
  }
}

package com.misys.portal.product.lc.portlets;

import com.misys.portal.common.portlets.ActionPortlet;
import com.misys.portal.common.resources.URLAliasesResourceProvider;
import com.misys.portal.common.tracer.GTPException;
import com.misys.portal.product.lc.common.LetterOfCreditFile;
import com.misys.portal.product.util.ProductTool;
import com.misys.portal.product.util.builder.IProductFileBuilder;
import com.misys.portal.product.util.builder.TransactionProductFileBuilder;
import com.misys.portal.security.GTPUser;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ecs.ElementContainer;
import org.apache.turbine.util.RunData;

public class LetterOfCreditPortletMine
  extends ActionPortlet
{
  private static final String ORG_PREVIOUS_FILE = "org_previous_file";
  private static final Log LOG = LogFactory.getLog(LetterOfCreditPortlet.class);

  //I added this
  
  public ElementContainer initiateLCFromScratchMine(RunData data)
  {
	System.out.println("<<< initiateLCFromScratchMine Called Edited >>>");
    String option = data.getParameters().getString("option");
    GTPUser user = (GTPUser)data.getUser();
    try
    {
      IProductFileBuilder productFileBuilder = TransactionProductFileBuilder.withProductCode("LC").build();     
      LetterOfCreditFile scratchFile = (LetterOfCreditFile)productFileBuilder.getProduct();
      scratchFile.defaultValues(data, "LC");
      //scratchFile.delete();
      //String stylesheet = initService.getStyleSheet(data, user, scratchFile);
      
      //String stylesheet = "/client/xsl/system/sy_register_user.xsl";
      String stylesheet = URLAliasesResourceProvider.STYLESHEET_LC_MYSCREEN_USER_URL;
      //String stylesheet = URLAliasesResourceProvider.STYLESHEET_SY_REGISTER_USER_URL;
        System.out.println("<<<<<  Create LC From Scratch Stylesheet changed >>>>" +stylesheet);
      
      Map dic = new HashMap();
      
      //CryptoUtility.populateTransactionCryptoParams(data, dic);
      
      return ProductTool.parseToECS(scratchFile, data, stylesheet, dic);
    }
    catch (GTPException gtpException)
    {
      LOG.error(gtpException.getMessage(), gtpException);
      return gtpException.toECS(user.getLanguage(), "ERROR_MSG_PRODUCTFILE_RETRIEVE");
    }
    
  }
  
}

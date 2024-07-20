package com.misys.portal.product.lc.portlets;

import com.misys.portal.common.bulk.field.AmountField;
import com.misys.portal.common.bulk.field.BulkFieldFactory;
import com.misys.portal.common.bulk.field.IntegerField;
import com.misys.portal.common.bulk.field.StringField;
import com.misys.portal.common.bulk.field.TimeField;
import com.misys.portal.common.bulk.field.XMLTextField;
import com.misys.portal.common.portlets.ActionPortlet;
import com.misys.portal.common.resources.URLAliasesResourceProvider;
import com.misys.portal.common.security.GTPAuthorisation;
import com.misys.portal.common.security.GTPSecurityCheck;
import com.misys.portal.common.services.Audit;
import com.misys.portal.common.tools.ReauthTool;
import com.misys.portal.common.tools.StaticDataUtils;
import com.misys.portal.common.tools.Utils;
import com.misys.portal.common.tracer.GTPException;
import com.misys.portal.common.tracer.GTPMessage;
import com.misys.portal.common.validation.GTPBusinessValidationException;
import com.misys.portal.entity.common.Entity;
import com.misys.portal.product.common.Bank;
import com.misys.portal.product.common.Correspondent;
import com.misys.portal.product.common.CrossReference;
import com.misys.portal.product.common.Narrative;
import com.misys.portal.product.lc.common.LetterOfCredit;
import com.misys.portal.product.lc.common.LetterOfCreditFile;
import com.misys.portal.product.lc.common.MasterLetterOfCreditFile;
import com.misys.portal.product.lc.common.TemplateLetterOfCreditFile;
import com.misys.portal.product.services.ProductInitializerService;
import com.misys.portal.product.sr.common.MasterStandbyReceivedFile;
import com.misys.portal.product.util.ProductTool;
import com.misys.portal.product.util.TransactionProductFile;
import com.misys.portal.product.util.builder.IProductFileBuilder;
import com.misys.portal.product.util.builder.ProductFactory;
import com.misys.portal.product.util.builder.TransactionProductFileBuilder;
import com.misys.portal.product.util.generator.TransactionIdGenerator;
import com.misys.portal.security.GTPSecurity;
import com.misys.portal.security.GTPUser;
import com.misys.portal.security.crypto.Crypto;
import com.misys.portal.security.crypto.CryptoUtility;
import com.misys.portal.services.resources.PortalResources;
import com.misys.portal.services.security.authentication.GTPAuthentication;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.ElementContainer;
import org.apache.turbine.services.TurbineServices;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.security.DataBackendException;
import org.apache.turbine.util.security.TurbineSecurityException;

public class LetterOfCreditPortlet
  extends ActionPortlet
{
  private static final String ORG_PREVIOUS_FILE = "org_previous_file";
  private static final Log LOG = LogFactory.getLog(LetterOfCreditPortlet.class);
  
  public ElementContainer initiateLCFromExistingEL(RunData data)
  {
    String ref_id = data.getParameters().getString("referenceid");
    String option = data.getParameters().getString("option");
    ProductInitializerService initService = (ProductInitializerService)TurbineServices.getInstance()
      .getService("ProductInitializerService");
    try
    {
      MasterStandbyReceivedFile existingFile = new MasterStandbyReceivedFile();
      existingFile.retrieveFileFromExisting(ref_id);
      
      LetterOfCreditFile newFile = new LetterOfCreditFile();
      
      newFile.getLetterOfCredit().completeWith(existingFile.getExportLetterOfCredit());
      
      newFile.defaultValues(data, "LC");
      
      String subProductCode = "LCSTD";
      if (StringUtils.isEmpty(newFile.getLetterOfCredit().getSub_product_code().getValue())) {
        newFile.getLetterOfCredit().getSub_product_code().setValue(subProductCode);
      }
      newFile.setSender(existingFile.getReceiver());
      newFile.setReceiver(new Correspondent());
      
      newFile.getLetterOfCredit().getApplicant_address_line_1().setValue(
        existingFile.getExportLetterOfCredit().getBeneficiary_address_line_1().getValue());
      newFile.getLetterOfCredit().getApplicant_address_line_2().setValue(
        existingFile.getExportLetterOfCredit().getBeneficiary_address_line_2().getValue());
      newFile.getLetterOfCredit().getApplicant_dom().setValue(
        existingFile.getExportLetterOfCredit().getBeneficiary_dom().getValue());
      if ((!existingFile.getEntity().isNull()) && 
        (existingFile.getEntity().getValue().length() != 0))
      {
        Entity entity = new Entity();
        entity.retrieve(
          newFile.getCompany_id().getValue().toString(), 
          existingFile.getEntity().getValue());
        if (entity.getProducts().contains(newFile.getProduct_code().getValue())) {
          newFile.setEntity(existingFile.getEntity());
        } else {
          newFile.setEntity(new StringField(""));
        }
      }
      newFile.setAmt(existingFile.getAmt());
      newFile.setCur_code(existingFile.getCur_code());
      newFile.setTnx_amt(existingFile.getAmt());
      
      newFile.setNarrative_goods(new Narrative(existingFile.getNarrative_goods()));
      newFile.setNarrative_additional_amount(
        new Narrative(existingFile.getNarrative_additional_amount()));
      newFile.setNarrative_additional_instructions(
        new Narrative(existingFile.getNarrative_additional_instructions()));
      newFile.setNarrative_documents_required(
        new Narrative(existingFile.getNarrative_documents_required()));
      newFile.setNarrative_period_presentation(
        new Narrative(existingFile.getNarrative_period_presentation()));
      newFile.setNarrative_shipment_period(
        new Narrative(existingFile.getNarrative_shipment_period()));
      newFile.setNarrative_full_details(new Narrative(existingFile.getNarrative_full_details()));
      
      newFile.initializeListOfMainBanks(data);
      newFile.setMain_bank(new Bank(existingFile.getAdvising_bank()));
      
      newFile.linkTnxfile(existingFile, "02");
      
      IntegerField field = (IntegerField)
        BulkFieldFactory.newAdditionalField("integer");
      field.setValue(
        Integer.valueOf(
        ((GTPUser)data.getUser()).getEntities("LC").size()));
      field.setName("entities");
      newFile.getPresentationFields().addField("entities", field);
      if ("BACK_TO_BACK".equalsIgnoreCase(option)) {
        newFile.getSub_tnx_type_code().setValue("06");
      }
      String stylesheet = initService.getStyleSheet(data, (GTPUser)data.getUser(), newFile);
      
      StaticDataUtils.addPhrasePresentationFields(newFile, data);
      Map dic = new HashMap();
      
      CryptoUtility.populateTransactionCryptoParams(data, dic);
      
      return ProductTool.parseToECS(newFile, data, stylesheet, dic);
    }
    catch (GTPException gtpException)
    {
      LOG.error(gtpException.getMessage(), gtpException);
      return gtpException.toECS(((GTPUser)data.getUser()).getLanguage(), "ERROR_MSG_PRODUCTFILE_RETRIEVE");
    }
  }
  
  public ElementContainer initiateLCFromScratch(RunData data)
  {
    String option = data.getParameters().getString("option");
    GTPUser user = (GTPUser)data.getUser();
    try
    {
      IProductFileBuilder productFileBuilder = 
        TransactionProductFileBuilder.withProductCode("LC").build();
      
      LetterOfCreditFile scratchFile = (LetterOfCreditFile)productFileBuilder.getProduct();
      
      scratchFile.defaultValues(data, "LC");
      
      ProductInitializerService initService = (ProductInitializerService)
        TurbineServices.getInstance().getService("ProductInitializerService");
      initService.initialize(data, user, scratchFile);
      
      String stylesheet = initService.getStyleSheet(data, user, scratchFile);
        System.out.println("<<<<<  Create LC From Scratch Stylesheet >>>>" +stylesheet);
      if ("SCRATCH_LCSTD".equals(option))
      {
        scratchFile.getLetterOfCredit().getLc_type().setValue("01");
      }
      else if ("UPLOAD_LCSTD".equals(option))
      {
        scratchFile.getLetterOfCredit().getLc_type().setValue("04");
        scratchFile.getLetterOfCredit()
          .getSub_tnx_type_code()
          .setValue("04");
      }
      else if ("FREEFORMAT_SCRATCH_LCSTD".equals(option))
      {
        scratchFile.getLetterOfCredit().getLc_type().setValue("02");
        scratchFile.getLetterOfCredit()
          .getSub_tnx_type_code()
          .setValue("11");
      }
      scratchFile.getDrawee_details_bank().getName().setValue("Issuing Bank");
      
      StaticDataUtils.addPhrasePresentationFields(scratchFile, data);
      
      Map dic = new HashMap();
      
      CryptoUtility.populateTransactionCryptoParams(data, dic);
      
      return ProductTool.parseToECS(scratchFile, data, stylesheet, dic);
    }
    catch (GTPException gtpException)
    {
      LOG.error(gtpException.getMessage(), gtpException);
      return gtpException.toECS(user.getLanguage(), "ERROR_MSG_PRODUCTFILE_RETRIEVE");
    }
  }
  
  public ElementContainer initiateLCFromTemplate(RunData data)
  {
    String language = ((GTPUser)data.getUser()).getLanguage();
    GTPUser user = (GTPUser)data.getUser();
    String templateId = data.getParameters().getString("templateid");
    String productCode = data.getParameters().getString("productcode");
    String subProductCode = data.getParameters()
      .getString("subproductcode");
    
    TemplateLetterOfCreditFile templateFile = new TemplateLetterOfCreditFile();
    try
    {
      templateFile.retrieveFileFromTemplate(
        templateId, 
        ((GTPUser)data.getUser()).getCompanyId(), 
        productCode, 
        subProductCode);
      
      LetterOfCreditFile newFile = new LetterOfCreditFile(templateFile);
      
      ProductInitializerService initService = (ProductInitializerService)
        TurbineServices.getInstance().getService("ProductInitializerService");
      initService.initialize(data, user, newFile);
      
      newFile.setMain_bank(new Bank(templateFile.getMain_bank()));
      
      newFile.defaultValues(data, "LC");
      
      String stylesheet = initService.getStyleSheet(data, user, newFile);
      String lcType = ((LetterOfCredit)newFile.getProduct()).getLc_type().getValue();
      if (lcType.equals("02")) {
        newFile.getLetterOfCredit().getSub_tnx_type_code().setValue("11");
      } else if (lcType.equals("04")) {
        newFile.getLetterOfCredit().getSub_tnx_type_code().setValue("04");
      }
      Map dic = new HashMap();
      
      CryptoUtility.populateTransactionCryptoParams(data, dic);
      
      StaticDataUtils.addPhrasePresentationFields(newFile, data);
      
      return ProductTool.parseToECS(newFile, data, stylesheet, dic);
    }
    catch (GTPException gtpException)
    {
      LOG.error(gtpException.getMessage(), gtpException);
      return gtpException.toECS(language, "ERROR_MSG_PRODUCTFILE_RETRIEVE");
    }
  }
  
  public ElementContainer openExistingLC(RunData data)
  {
    GTPUser user = (GTPUser)data.getUser();
    
    String referenceId = data.getParameters().getString("referenceid");
    String tnxType = data.getParameters().getString("tnxtype");
    String mode = data.getParameters().getString("mode");
    String option = data.getParameters().getString("option");
    try
    {
      MasterLetterOfCreditFile masterFile = new MasterLetterOfCreditFile();
      
      masterFile.retrieveFileFromExisting(referenceId);
      
      LetterOfCreditFile newFile = new LetterOfCreditFile(masterFile);
      
      newFile.getTnx_id().setValue(TransactionIdGenerator.generate());
      
      newFile.getTnx_type_code().setValue(tnxType);
      
      newFile.getLetterOfCredit().getLast_ship_date().setValue(null);
      newFile.getLetterOfCredit().getFee_act_no().setValue(null);
      newFile.getLetterOfCredit().getPrincipal_act_no().setValue(null);
      ProductInitializerService initService = (ProductInitializerService)
        TurbineServices.getInstance().getService("ProductInitializerService");
      if ("01".equals(tnxType))
      {
        newFile.defaultValues(data, "LC");
        
        initService.initialize(data, user, newFile);
        
        newFile.setMain_bank(new Bank(masterFile.getMain_bank()));
        newFile.setMain_bank(new Bank(masterFile.getMain_bank()));
        
        newFile.getAdvise_thru_bank().getReference().setValue(null);
        newFile.getAdvise_thru_bank().getIso_code().setValue(null);
        
        newFile.getAdvising_bank().getReference().setValue(null);
        newFile.getAdvising_bank().getIso_code().setValue(null);
        
        newFile.getDrawee_details_bank().getReference().setValue(null);
        newFile.getDrawee_details_bank().getIso_code().setValue(null);
        
        newFile.getCredit_available_with_bank().getReference().setValue(null);
        newFile.getCredit_available_with_bank().getIso_code().setValue(null);
        
        List emptyList = new ArrayList();
        newFile.setListOfLicenses(emptyList);
      }
      else if ("03".equals(tnxType))
      {
        initService.initialize(data, user, newFile);
        
        Timestamp amd_date = masterFile.getIss_date().getValue();
        if ((amd_date == null) || (Utils.getUserDate(user.getTimeZone()).after(amd_date))) {
          amd_date = Utils.getUserDate(user.getTimeZone());
        }
        newFile.getLetterOfCredit().getAmd_date().setValue(amd_date);
        newFile.getLetterOfCredit().getAmd_no().setValue(newFile.generateAmd_no());
        
        XMLTextField yfield = (XMLTextField)
          BulkFieldFactory.newAdditionalField("xml_text");
        yfield.setValue(
          Utils.cutXMLHeader(masterFile.toXML(((GTPUser)data.getUser()).getLanguage())));
        yfield.setName("org_previous_file");
        
        newFile.getPresentationFields()
          .addField("org_previous_file", yfield);
      }
      else if (("13".equals(tnxType)) && (
        ("DISCREPANT".equals(mode)) || 
        ("CLEAN".equals(mode))))
      {
        String tnxId = data.getParameters().getString("tnxid");
        TransactionProductFile father = 
          ProductFactory.newTransactionProductFile(newFile.getProduct_code().getValue());
        father.retrieveFileFromTnx(referenceId, tnxId);
        for (Iterator ite = father.getCrossReferences().iterator(); ite.hasNext();)
        {
          CrossReference crossReference = (CrossReference)ite.next();
          if (("01".equals(crossReference.getType_code().getValue())) && 
            (crossReference.getChild_tnx_id().isNull()))
          {
            crossReference.setChild_tnx_id(newFile.getTnx_id());
            newFile.getCrossReferences().add(crossReference);
            break;
          }
        }
        newFile.setTnx_amt(father.getTnx_amt());
        newFile.setTnx_cur_code(father.getTnx_cur_code());
        if (((LetterOfCredit)father.getProduct()).getMaturity_date() != null) {
          newFile.getLetterOfCredit().setMaturity_date(((LetterOfCredit)father.getProduct()).getMaturity_date());
        }
        newFile.getLetterOfCredit().setImp_bill_ref_id(((LetterOfCredit)father.getProduct()).getImp_bill_ref_id());
        
        newFile.initializeListOfMainBanks(data);
      }
      else if ("ACTION_REQUIRED".equals(option))
      {
        String tnxId = data.getParameters().getString("tnxid");
        TransactionProductFile father = 
          ProductFactory.newTransactionProductFile(newFile.getProduct_code().getValue());
        father.retrieveFileFromTnx(referenceId, tnxId);
        
        newFile.initializeListOfMainBanks(data);
        newFile.getLetterOfCredit()
          .getAction_req_code()
          .setValue(father.getActionRequired().getStringValue());
        for (Iterator ite = father.getCrossReferences().iterator(); ite.hasNext();)
        {
          CrossReference crossReference = (CrossReference)ite.next();
          if (("01".equals(crossReference.getType_code().getValue())) && 
            (crossReference.getChild_tnx_id().isNull()))
          {
            crossReference.setChild_tnx_id(newFile.getTnx_id());
            newFile.getCrossReferences().add(crossReference);
            break;
          }
        }
        XMLTextField yfield = (XMLTextField)
          BulkFieldFactory.newAdditionalField("xml_text");
        yfield.setValue(Utils.cutXMLHeader(father.toXML(user.getLanguage())));
        yfield.setName("org_previous_file");
        
        newFile.getPresentationFields()
          .addField("org_previous_file", yfield);
      }
      else if ("EXISTING".equals(option))
      {
        newFile.initializeListOfMainBanks(data);
      }
      XMLTextField field = (XMLTextField)
        BulkFieldFactory.newAdditionalField("xml_text");
      field.setValue(Utils.buildXMLforMainBankParameters(newFile).toString());
      field.setName("main_bank");
      newFile.getPresentationFields().addField("main_bank", field);
      if ("98".equals(masterFile.getProd_stat_code().getValue())) {
        newFile.getLetterOfCredit().getProvisional_status().setValue("Y");
      }
      Map dic = new HashMap();
      
      String stylesheet = initService.getStyleSheet(data, user, newFile);
      
      CryptoUtility.populateTransactionCryptoParams(data, dic);
      
      StaticDataUtils.addPhrasePresentationFields(newFile, data);
      
      boolean isMT798Enable = Utils.isMT798Enable(
        newFile.getCompany_id().getStringValue(), 
        newFile.getMain_bank_abbv_name().getStringValue(), 
        newFile.getProduct_code().getValue(), 
        newFile.getSub_product_code().getValue());
      String MT798 = null;
      if (isMT798Enable) {
        MT798 = "Y";
      } else {
        MT798 = "N";
      }
      StringField field1 = (StringField)
        BulkFieldFactory.newAdditionalField("string");
      field1.setValue(MT798);
      field1.setName("is_MT798");
      newFile.getPresentationFields().addField("is_MT798", field1);
      
      return ProductTool.parseToECS(newFile, data, stylesheet, dic);
    }
    catch (GTPException gtpException)
    {
      LOG.error(gtpException.getMessage(), gtpException);
      return gtpException.toECS(((GTPUser)data.getUser()).getLanguage(), "ERROR_MSG_PRODUCTFILE_RETRIEVE");
    }
  }
  
  public ElementContainer openPendingLC(RunData data)
  {
    GTPUser user = (GTPUser)data.getUser();
    String mode = data.getParameters().getString("mode");
    String referenceId = data.getParameters().getString("referenceid");
    String tnxId = data.getParameters().getString("tnxid");
    String tnxType = data.getParameters().getString("tnxtype");
    
    ProductInitializerService initService = (ProductInitializerService)TurbineServices.getInstance()
      .getService("ProductInitializerService");
    
    LetterOfCreditFile pendingFile = new LetterOfCreditFile();
    
    Map dic = new HashMap();
    try
    {
      pendingFile.retrieveFileFromTnx(referenceId, tnxId);
      if (("01".equals(tnxType)) && 
        ("DRAFT".equals(mode)))
      {
        initService.initialize(data, user, pendingFile);
      }
      else if ("03".equals(tnxType))
      {
        initService.initialize(data, user, pendingFile);
        
        LetterOfCreditFile previousFile = new LetterOfCreditFile();
        String previousTnxId = pendingFile.previousAcceptedTnx();
        
        previousFile.retrieveFileFromTnx(referenceId, previousTnxId);
        
        XMLTextField yfield = (XMLTextField)
          BulkFieldFactory.newAdditionalField("xml_text");
        yfield.setValue(
          Utils.cutXMLHeader(
          previousFile.toXML(((GTPUser)data.getUser()).getLanguage())));
        yfield.setName("org_previous_file");
        
        pendingFile.getPresentationFields()
          .addField("org_previous_file", yfield);
        if ("01".equals(pendingFile.getSub_tnx_type_code().getValue()))
        {
          AmountField incAmount = new AmountField("lc_cur_code");
          incAmount.setValue(
            pendingFile.getAmt().getValue().subtract(previousFile.getAmt().getValue()));
          if (incAmount.getValue().compareTo(new BigDecimal(0)) != 0) {
            pendingFile.getPresentationFields().addField("inc_amt", incAmount);
          }
        }
        else if ("02".equals(pendingFile.getSub_tnx_type_code().getValue()))
        {
          AmountField decAmount = new AmountField("lc_cur_code");
          decAmount.setValue(
            previousFile.getAmt().getValue().subtract(pendingFile.getAmt().getValue()));
          if (decAmount.getValue().compareTo(new BigDecimal(0)) != 0) {
            pendingFile.getPresentationFields().addField("dec_amt", decAmount);
          }
        }
        XMLTextField field = (XMLTextField)
          BulkFieldFactory.newAdditionalField("xml_text");
        field.setValue(Utils.buildXMLforMainBankParameters(pendingFile).toString());
        field.setName("main_bank");
        pendingFile.getPresentationFields().addField("main_bank", field);
      }
      else if ("13".equals(tnxType))
      {
        if ("DRAFT".equals(mode))
        {
          boolean isMT798Enable = false;
          String MT798 = null;
          String bankAbbvName = pendingFile.getIssuing_bank().getAbbv_name().toString();
          String companyID = String.valueOf(user.getCompanyId());
          isMT798Enable = Utils.isMT798Enable(
            companyID, 
            bankAbbvName, 
            pendingFile.getProduct_code().getValue(), 
            pendingFile.getSub_product_code().toString());
          if (isMT798Enable) {
            MT798 = "Y";
          } else {
            MT798 = "N";
          }
          StringField field1 = (StringField)
            BulkFieldFactory.newAdditionalField("string");
          field1.setValue(MT798);
          field1.setName("is_MT798");
          pendingFile.getPresentationFields().addField("is_MT798", field1);
          
          pendingFile.initializeListOfMainBanks(data);
        }
        if (!pendingFile.getCrossReferences().isEmpty())
        {
          String fatherTnxId = null;
          for (Iterator ite = pendingFile.getCrossReferences().iterator(); ite.hasNext();)
          {
            CrossReference crossReference = (CrossReference)ite.next();
            if ("01".equals(crossReference.getType_code().getValue()))
            {
              fatherTnxId = crossReference.getTnx_id().getValue();
              break;
            }
          }
          TransactionProductFile father = 
            ProductFactory.newTransactionProductFile(pendingFile.getProduct_code().getValue());
          father.retrieveFileFromTnx(referenceId, fatherTnxId);
          for (Iterator ite = father.getCrossReferences().iterator(); ite.hasNext();)
          {
            CrossReference crossReference = (CrossReference)ite.next();
            if (("01".equals(crossReference.getType_code().getValue())) && 
              (crossReference.getChild_tnx_id().isNull()))
            {
              crossReference.setChild_tnx_id(pendingFile.getTnx_id());
              pendingFile.getCrossReferences().add(crossReference);
              break;
            }
          }
          XMLTextField yfield = (XMLTextField)
            BulkFieldFactory.newAdditionalField("xml_text");
          yfield.setValue(Utils.cutXMLHeader(father.toXML(user.getLanguage())));
          yfield.setName("org_previous_file");
          
          pendingFile.getPresentationFields()
            .addField("org_previous_file", yfield);
          pendingFile.prepareCustomerReferencesPresentationFields(data);
        }
      }
      String stylesheet = initService.getStyleSheet(data, user, pendingFile);
      if ("DRAFT".equals(mode))
      {
        dic.put("displaymode", "edit");
        dic.put("collaborationmode", "none");
        dic.put("mode", "DRAFT");
      }
      else if ("UNSIGNED".equals(mode))
      {
        dic.put("displaymode", "view");
        dic.put("collaborationmode", "counterparty");
        dic.put("mode", "UNSIGNED");
        pendingFile.prepareCustomerReferencesPresentationFields(data);
      }
      CryptoUtility.populateTransactionCryptoParams(data, dic);
      
      pendingFile.buildEncryptedApplicantReferenceIntoRequest();
      
      StaticDataUtils.addPhrasePresentationFields(pendingFile, data);
      
      boolean isMT798Enable = Utils.isMT798Enable(
        pendingFile.getCompany_id().getStringValue(), 
        pendingFile.getMain_bank_abbv_name().getStringValue(), 
        pendingFile.getProduct_code().getValue(), 
        pendingFile.getSub_product_code().getValue());
      String MT798 = null;
      if (isMT798Enable) {
        MT798 = "Y";
      } else {
        MT798 = "N";
      }
      StringField field1 = (StringField)
        BulkFieldFactory.newAdditionalField("string");
      field1.setValue(MT798);
      field1.setName("is_MT798");
      pendingFile.getPresentationFields().addField("is_MT798", field1);
      
      return ProductTool.parseToECS(pendingFile, data, stylesheet, dic);
    }
    catch (GTPException gtpException)
    {
      LOG.error(gtpException.getMessage(), gtpException);
      return gtpException.toECS(((GTPUser)data.getUser()).getLanguage(), "ERROR_MSG_PRODUCTFILE_RETRIEVE");
    }
  }
  
  public ElementContainer openRejectedLC(RunData data)
  {
    String referenceId = data.getParameters().getString("referenceid");
    String tnxId = data.getParameters().getString("tnxid");
    GTPUser user = (GTPUser)data.getUser();
    try
    {
      LetterOfCreditFile rejectedFile = new LetterOfCreditFile();
      
      rejectedFile.retrieveFileFromTnx(referenceId, tnxId);
      rejectedFile.initializeListOfMainBanks(data);
      
      LetterOfCreditFile newFile = new LetterOfCreditFile();
      
      newFile.completeWith(rejectedFile);
      
      newFile.defaultValues(data, "LC");
      
      newFile.getAppl_date().setValue(Utils.getUserDate(((GTPUser)data.getUser()).getTimeZone()));
      
      ProductInitializerService initService = (ProductInitializerService)
        TurbineServices.getInstance().getService("ProductInitializerService");
      initService.initialize(data, user, newFile);
      
      String stylesheet = URLAliasesResourceProvider.STYLESHEET_LC_LCSTD_CREATE_URL;
      
      Map dic = new HashMap();
      
      CryptoUtility.populateTransactionCryptoParams(data, dic);
      
      return ProductTool.parseToECS(newFile, data, stylesheet, dic);
    }
    catch (GTPException gtpException)
    {
      LOG.error(gtpException.getMessage(), gtpException);
      return gtpException.toECS(((GTPUser)data.getUser()).getLanguage(), "ERROR_MSG_PRODUCTFILE_RETRIEVE");
    }
  }
  
  public ConcreteElement returnLC(RunData data)
  {
    GTPUser user = (GTPUser)data.getUser();
    String xmlString;
    String tnxTypeCode = data.getParameters()
      .getString("tnxtype");
    try
    {
      xmlString = Crypto.decrypt(data);
    }
    catch (DataBackendException de)
    {
      
      LOG.error(de.getMessage(), de);
      return new GTPException(de).toECS(user.getLanguage(), "ERROR_MSG_PRODUCTFILE_RETRIEVE");
    }
    catch (TurbineSecurityException te)
    {
      LOG.error(te.getMessage(), te);
      return new GTPException(te).toECS(user.getLanguage(), "ERROR_MSG_PRODUCTFILE_RETRIEVE");
    }
    
    String mode = data.getParameters().getString("mode");
    
    LetterOfCreditFile theFile = null;
    try
    {
      theFile = (LetterOfCreditFile)ProductFactory.newTransactionProductFile(
        "LC", 
        xmlString, 
        user.getLanguage(), 
        mode);
      
      Narrative returnComments = ProductTool.getReturnComments(xmlString, theFile);
      if (returnComments != null) {
        theFile.setReturn_comments(returnComments);
      }
      theFile.getCompany_id().setValue(Integer.valueOf(user.getCompanyId()));
      theFile.getCompany_name().setValue(user.getCompany_abbv_name());
      
      ProductInitializerService initService = (ProductInitializerService)
        TurbineServices.getInstance().getService("ProductInitializerService");
      String stylesheet = initService.getStyleSheet(data, user, theFile);
      if (!GTPSecurityCheck.checkProductContext(theFile, data)) {
        throw new GTPException(data, 
          "Security Exception, Save LC, ref_id=" + theFile.getRef_id());
      }
      TimeField oldCtlDate = (TimeField)theFile.getProduct()
        .getObjectData()
        .getField("old_ctl_dttm");
      if ((oldCtlDate != null) && 
        (!GTPSecurityCheck.checkTransactionContext(data, theFile, oldCtlDate.getValue()))) {
        return 
        
          new GTPException(data, "The file status has changed, you should refresh your transaction details.").toECS(((GTPUser)data.getUser()).getLanguage(), "ERROR_MSG_PRODUCTFILE_SYNCHRONISATION");
      }
      theFile.getLetterOfCredit().getSub_tnx_stat_code().setValue("18");
      if (GTPSecurityCheck.isReauthenticationEnabled(true))
      {
        String reAuthType = GTPSecurityCheck.getReAuthType(
          user, 
          "return", 
          ReauthTool.getReAuthConfigParams(theFile, tnxTypeCode));
        if (!"01".equals(reAuthType))
        {
          GTPAuthentication authentication = GTPSecurityCheck.reauthenticate(
            data, 
            user, 
            GTPSecurity.getReAuthCredentials(data, theFile), 
            reAuthType);
          if (!authentication.isAuthenticated())
          {
            if (theFile.getTnx_type_code().getValue() == null) {
              theFile.getTnx_type_code().setValue(
                data.getParameters()
                .getString("tnxtype"));
            }
            theFile.getTnx_stat_code().setValue("02");
            
            initService.initialize(data, user, theFile);
            
            return getFailedReauthenticationContent(
              data, 
              theFile, 
              stylesheet, 
              authentication.getMessage().toString());
          }
        }
      }
      theFile.saveFile(
        new GTPAuthorisation(true), 
        "LC", 
        "REJECT", 
        mode, 
        data.getParameters().getString("tnxtype"), 
        user);
      
      data.getTemplateInfo().setTemp("__interface_data_source", theFile);
      
      return new GTPMessage(data, theFile, "Y");
    }
    catch (GTPException localGTPException) {}
    return new GTPMessage(data, theFile, "E");
  }
  
  public ConcreteElement saveLC(RunData data)
  {
      String xmlString;
    GTPUser user = (GTPUser)data.getUser();
    String language = user.getLanguage();
    try
    {
      xmlString = Crypto.decrypt(data);
    }
    catch (DataBackendException de)
    {
      
      LOG.error(de.getMessage(), de);
      return new GTPException(de).toECS(language, "ERROR_MSG_PRODUCTFILE_RETRIEVE");
    }
    catch (TurbineSecurityException te)
    {
      LOG.error(te.getMessage(), te);
      return new GTPException(te).toECS(language, "ERROR_MSG_PRODUCTFILE_RETRIEVE");
    }
    
    String mode = data.getParameters().getString("mode");
    String tnxTypeCode = data.getParameters()
      .getString("tnxtype");
    LetterOfCreditFile theFile = null;
    try
    {
      theFile = new LetterOfCreditFile(xmlString, language);
      
      theFile.getCompany_id().setValue(new Integer(user.getCompanyId()));
      theFile.getCompany_name().setValue(user.getCompany_abbv_name());
      try
      {
        GTPSecurityCheck.validateFile(data, theFile);
      }
      catch (GTPBusinessValidationException e)
      {
        LOG.error(e.getMessage(), e);
        
        ElementContainer root = new ElementContainer();
        root.addElement(e.getMessageFromKey(language));
        return root;
      }
      if ((data.getParameters().get("LC_Screen") != null) && 
        ("Upload".equals(data.getParameters().get("LC_Screen")))) {
        theFile.getLetterOfCredit().getSub_product_code().setValue("LCSTD");
      }
      theFile.populateAttachments(data.getParameters().get("attids"));
      theFile.markFileActOnAttachments(data.getParameters().get("fileActIds"));
      
      ProductInitializerService initService = (ProductInitializerService)
        TurbineServices.getInstance().getService("ProductInitializerService");
      String stylesheet = initService.getStyleSheet(data, user, theFile);
      if (GTPSecurityCheck.isReauthenticationEnabled(true))
      {
        String reAuthType = GTPSecurityCheck.getReAuthType(
          user, 
          "save", 
          ReauthTool.getReAuthConfigParams(theFile, tnxTypeCode));
        if (!"01".equals(reAuthType))
        {
          GTPAuthentication authentication = GTPSecurityCheck.reauthenticate(
            data, 
            user, 
            GTPSecurity.getReAuthCredentials(data, theFile), 
            reAuthType);
          if (!authentication.isAuthenticated())
          {
            if (theFile.getTnx_type_code().getValue() == null) {
              theFile.getTnx_type_code().setValue(
                data.getParameters()
                .getString("tnxtype"));
            }
            initService.initialize(data, user, theFile);
            
            return getFailedReauthenticationContent(
              data, 
              theFile, 
              stylesheet, 
              authentication.getMessage().toString());
          }
        }
      }
      theFile.saveFile(
        new GTPAuthorisation(true), 
        "LC", 
        "SAVE", 
        mode, 
        data.getParameters().getString("tnxtype"), 
        user);
      
      data.getTemplateInfo().setTemp("__interface_data_source", theFile);
      
      return new GTPMessage(data, theFile, "Y");
    }
    catch (GTPException localGTPException) {}
    return new GTPMessage(data, theFile, "E");
  }
  
  public ConcreteElement saveTemplateLC(RunData data)
  {
      String xmlString;
    GTPUser user = (GTPUser)data.getUser();
    String language = user.getLanguage();
    String tnxTypeCode = data.getParameters()
      .getString("tnxtype");
    
    String refId = data.getParameters().getString("referenceid");
    String tnxId = data.getParameters().getString("tnxid");
    String subProductCode = "";
    try
    {
      xmlString = Crypto.decrypt(data);
    }
    catch (DataBackendException de)
    {
      
      LOG.error(de.getMessage(), de);
      return new GTPException(de).toECS(language, "ERROR_MSG_PRODUCTFILE_RETRIEVE");
    }
    catch (TurbineSecurityException te)
    {
      LOG.error(te.getMessage(), te);
      return new GTPException(te).toECS(language, "ERROR_MSG_PRODUCTFILE_RETRIEVE");
    }
    
    data.getParameters().getString("mode");
    
    TemplateLetterOfCreditFile theFile = null;
    try
    {
      theFile = new TemplateLetterOfCreditFile(xmlString, user.getCompanyType(), 
        user.getLanguage());
      
      subProductCode = theFile.getSub_product_code().getStringValue();
      
      String templateDesc = theFile.getTemplate_description().getStringValue();
      if ((StringUtils.isEmpty(refId)) && (StringUtils.isEmpty(tnxId)))
      {
        theFile.retrieveFileFromTemplate(
          theFile.getLetterOfCredit().getTemplate_id().getStringValue(), 
          ((GTPUser)data.getUser()).getCompanyId(), 
          "LC", 
          subProductCode);
        
        theFile.getTemplate_description().setValue(templateDesc);
      }
      else
      {
        TemplateLetterOfCreditFile tpf = new TemplateLetterOfCreditFile();
        tpf.retrieveFileFromTemplate(
          theFile.getLetterOfCredit().getTemplate_id().getStringValue(), 
          ((GTPUser)data.getUser()).getCompanyId(), 
          "LC", 
          "*");
        if ((tpf.getTemplate_description() != null) && 
          (StringUtils.isNotEmpty(tpf.getTemplate_description().getStringValue()))) {
          theFile.getTemplate_description().setValue(tpf.getTemplate_description().getStringValue());
        }
      }
      if (GTPSecurityCheck.isReauthenticationEnabled(true))
      {
        String reAuthType = GTPSecurityCheck.getReAuthType(
          user, 
          "template", 
          ReauthTool.getReAuthConfigParams(theFile, tnxTypeCode));
        if (!"01".equals(reAuthType))
        {
          GTPAuthentication authentication = GTPSecurityCheck.reauthenticate(
            data, 
            user, 
            GTPSecurity.getReAuthCredentials(data, theFile), 
            reAuthType);
          if (!authentication.isAuthenticated())
          {
            LetterOfCreditFile newFile = new LetterOfCreditFile(theFile);
            
            ProductInitializerService initService = (ProductInitializerService)
              TurbineServices.getInstance().getService("ProductInitializerService");
            String stylesheet = initService.getStyleSheet(data, user, newFile);
            
            initService.initialize(data, user, newFile);
            
            return getFailedReauthenticationContent(
              data, 
              newFile, 
              stylesheet, 
              authentication.getMessage().toString());
          }
        }
      }
      theFile.getCompany_id().setValue(new Integer(user.getCompanyId()));
      theFile.getCompany_name().setValue(user.getCompany_abbv_name());
      
      theFile.saveFile("LC", user.getCompanyType(), user.getCompanyId());
    }
    catch (GTPException localGTPException)
    {
      return new GTPMessage(data, theFile, "E");
    }
    return new GTPMessage(data, theFile, "Y");
  }
  
  public ConcreteElement submitLC(RunData data)
  {
    String xmlString;
    GTPUser user = (GTPUser)data.getUser();
    String language = user.getLanguage();
    String tnxTypeCode = data.getParameters().getString("tnxtype");
    try
    {
      xmlString = Crypto.decrypt(data);
    }
    catch (DataBackendException de)
    {
      
      LOG.error(de.getMessage(), de);
      return new GTPException(de).toECS(language, "ERROR_MSG_PRODUCTFILE_RETRIEVE");
    }
    catch (TurbineSecurityException te)
    {
      LOG.error(te.getMessage(), te);
      return new GTPException(te).toECS(language, "ERROR_MSG_PRODUCTFILE_RETRIEVE");
    }
    String mode = data.getParameters().getString("mode");
    
    LetterOfCreditFile theFile = null;
    try
    {
      theFile = (LetterOfCreditFile)ProductFactory.newTransactionProductFile(
        "LC", 
        xmlString, 
        user.getLanguage(), 
        mode);
      
      theFile.getCompany_id().setValue(new Integer(user.getCompanyId()));
      theFile.getCompany_name().setValue(user.getCompany_abbv_name());
      try
      {
        GTPSecurityCheck.validateFile(data, theFile);
      }
      catch (GTPBusinessValidationException e)
      {
        LOG.error(e.getMessage(), e);
        
        ElementContainer root = new ElementContainer();
        root.addElement(e.getMessageFromKey(language));
        return root;
      }
      theFile.populateAttachments(data.getParameters().get("attids"));
      if (!"UNSIGNED".equals(mode)) {
        theFile.markFileActOnAttachments(data.getParameters().get("fileActIds"));
      }
      if ((data.getParameters().get("LC_Screen") != null) && 
        ("Upload".equals(data.getParameters().get("LC_Screen"))))
      {
        theFile.getLetterOfCredit().getSub_product_code().setValue("LCSTD");
        theFile.getLetterOfCredit()
          .getApplicant_name()
          .setValue(data.getParameters().get("applicant_name"));
        theFile.getLetterOfCredit().getApplicant_address_line_1().setValue(
          data.getParameters().get("applicant_address_line_1"));
        theFile.getLetterOfCredit().getApplicant_address_line_2().setValue(
          data.getParameters().get("applicant_address_line_2"));
        theFile.getLetterOfCredit()
          .getApplicant_dom()
          .setValue(data.getParameters().get("applicant_dom"));
      }
      ProductInitializerService initService = (ProductInitializerService)
        TurbineServices.getInstance().getService("ProductInitializerService");
      String stylesheet = initService.getStyleSheet(data, user, theFile);
      
      GTPAuthorisation authorised = theFile.signProductFile(data, mode, tnxTypeCode);
      if (GTPSecurityCheck.isReauthenticationEnabled(authorised.isAuthorised()))
      {
        String reAuthType = GTPSecurityCheck.getReAuthType(
          user, 
          "submit", 
          ReauthTool.getReAuthConfigParams(theFile, tnxTypeCode));
        if (!"01".equals(reAuthType))
        {
          GTPAuthentication authentication = GTPSecurityCheck.reauthenticate(
            data, 
            user, 
            GTPSecurity.getReAuthCredentials(data, theFile), 
            reAuthType);
          if (!authentication.isAuthenticated())
          {
            if (theFile.getTnx_type_code().getValue() == null) {
              theFile.getTnx_type_code().setValue(
                data.getParameters()
                .getString("tnxtype"));
            }
            String referenceId = data.getParameters()
              .getString("referenceid");
            String tnxId = data.getParameters()
              .getString("tnxid");
            LetterOfCreditFile fileFromDB = new LetterOfCreditFile();
            fileFromDB.retrieveFileFromTnx(referenceId, tnxId);
            if (!fileFromDB.getReturn_comments().isEmpty()) {
              theFile.setReturn_comments(fileFromDB.getReturn_comments());
            }
            if (("13".equals(tnxTypeCode)) && 
              (!theFile.getCrossReferences().isEmpty()))
            {
              String fatherTnxId = null;
              for (Iterator ite = theFile.getCrossReferences().iterator(); ite.hasNext();)
              {
                CrossReference crossReference = (CrossReference)ite.next();
                if ("01".equals(crossReference.getType_code().getValue()))
                {
                  fatherTnxId = crossReference.getTnx_id().getValue();
                  break;
                }
              }
              TransactionProductFile father = 
                ProductFactory.newTransactionProductFile(theFile.getProduct_code().getValue());
              father.retrieveFileFromTnx(referenceId, fatherTnxId);
              
              XMLTextField yfield = (XMLTextField)
                BulkFieldFactory.newAdditionalField("xml_text");
              yfield.setValue(Utils.cutXMLHeader(father.toXML(language)));
              yfield.setName("org_previous_file");
              
              theFile.getPresentationFields()
                .addField("org_previous_file", yfield);
            }
            else
            {
              MasterLetterOfCreditFile masterFile = new MasterLetterOfCreditFile();
              masterFile.retrieveFileFromExisting(theFile.getRef_id().getValue());
              XMLTextField yfield = (XMLTextField)
                BulkFieldFactory.newAdditionalField("xml_text");
              yfield.setValue(
                Utils.cutXMLHeader(
                masterFile.toXML(((GTPUser)data.getUser()).getLanguage())));
              yfield.setName("org_previous_file");
              theFile.getPresentationFields()
                .addField("org_previous_file", yfield);
            }
            initService.initialize(data, user, theFile);
            
            return getFailedReauthenticationContent(
              data, 
              theFile, 
              stylesheet, 
              authentication.getMessage().toString());
          }
        }
      }
      if (theFile.getLetterOfCredit().getExp_date().getValue() == null)
      {
        MasterLetterOfCreditFile masterFile = new MasterLetterOfCreditFile();
        
        masterFile.retrieveFileFromExisting(theFile.getRef_id().getValue());
        theFile.getLetterOfCredit()
          .getExp_date()
          .setValue(masterFile.getLetterOfCredit().getExp_date().getValue());
      }
      if ("03".equals(tnxTypeCode))
      {
        MasterLetterOfCreditFile masterFile = new MasterLetterOfCreditFile();
        masterFile.retrieveFileFromExisting(theFile.getRef_id().getValue());
        
        theFile.getProduct().getObjectData().addField(
          "last_ship_date", 
          masterFile.getLetterOfCredit().getLast_ship_date());
        theFile.getProduct().getObjectData().addField(
          "narrative_shipment_period", 
          masterFile.getNarrative_shipment_period().getText());
      }
      if (authorised.isAuthorised()) {
        Audit.audit(data, "A");
      } else {
        Audit.audit(data, "F");
      }
      if ("03".equals(tnxTypeCode)) {
        if ((!"05".equals(theFile.getSub_tnx_type_code().getValue())) && 
          (theFile.getLetterOfCredit().getExp_date().getValue() == null))
        {
          MasterLetterOfCreditFile masterFile = new MasterLetterOfCreditFile();
          
          masterFile.retrieveFileFromExisting(theFile.getRef_id().getValue());
          theFile.getLetterOfCredit()
            .getExp_date()
            .setValue(masterFile.getLetterOfCredit().getExp_date().getValue());
        }
      }
      if ("Y".equals(theFile.getLetterOfCredit().getProvisional_status().getValue())) {
        theFile.getProd_stat_code().setValue("98");
      }
      theFile.saveFile(
        authorised, 
        "LC", 
        "SUBMIT", 
        mode, 
        tnxTypeCode, 
        user);
      
      data.getTemplateInfo().setTemp("__interface_data_source", theFile);
      if (authorised.isAuthorised()) {
        return new GTPMessage(data, theFile, "A");
      }
      return new GTPMessage(data, theFile, "F");
    }
    catch (GTPBusinessValidationException e)
    {
      return new GTPMessage(language, e.getMessageKey(), e.getMessageArgs());
    }
    catch (GTPException localGTPException) {}
    return new GTPMessage(data, theFile, "E");
  }
  
  
  //I added this
  
  public ElementContainer initiateLCFromScratchMine(RunData data)
  {
	  System.out.println("<<< initiateLCFromScratchMine Called >>>");
    String option = data.getParameters().getString("option");
    GTPUser user = (GTPUser)data.getUser();
    try
    {
      IProductFileBuilder productFileBuilder = 
        TransactionProductFileBuilder.withProductCode("LC").build();
      
      LetterOfCreditFile scratchFile = (LetterOfCreditFile)productFileBuilder.getProduct();
      
      scratchFile.defaultValues(data, "LC");
      
      ProductInitializerService initService = (ProductInitializerService)
        TurbineServices.getInstance().getService("ProductInitializerService");
      initService.initialize(data, user, scratchFile);
      
      String stylesheet = initService.getStyleSheet(data, user, scratchFile);
      String stylesheet2 = URLAliasesResourceProvider.STYLESHEET_LC_MYSCREEN_USER_URL;
        System.out.println("<<<<<  Create LC From Scratch Stylesheet changed >>>>" +stylesheet);
      if ("SCRATCH_LCSTD".equals(option))
      {
        scratchFile.getLetterOfCredit().getLc_type().setValue("01");
      }
      else if ("UPLOAD_LCSTD".equals(option))
      {
        scratchFile.getLetterOfCredit().getLc_type().setValue("04");
        scratchFile.getLetterOfCredit()
          .getSub_tnx_type_code()
          .setValue("04");
      }
      else if ("FREEFORMAT_SCRATCH_LCSTD".equals(option))
      {
        scratchFile.getLetterOfCredit().getLc_type().setValue("02");
        scratchFile.getLetterOfCredit()
          .getSub_tnx_type_code()
          .setValue("11");
      }
      scratchFile.getDrawee_details_bank().getName().setValue("Issuing Bank");
      
      StaticDataUtils.addPhrasePresentationFields(scratchFile, data);
      
      Map dic = new HashMap();
      
      CryptoUtility.populateTransactionCryptoParams(data, dic);
      
      return ProductTool.parseToECS(scratchFile, data, stylesheet, dic);
    }
    catch (GTPException gtpException)
    {
      LOG.error(gtpException.getMessage(), gtpException);
      return gtpException.toECS(user.getLanguage(), "ERROR_MSG_PRODUCTFILE_RETRIEVE");
    }
  }
  
    
  
}

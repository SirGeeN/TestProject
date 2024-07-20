/*
 * Decompiled with CFR 0_116.
 * 
 * Could not load the following classes:
 *  javax.mail.internet.MimeUtility
 *  org.apache.commons.lang.StringUtils
 *  org.apache.commons.logging.Log
 *  org.apache.commons.logging.LogFactory
 *  org.apache.turbine.Turbine
 *  org.apache.turbine.services.BaseService
 *  org.apache.turbine.services.InitializationException
 *  org.apache.turbine.util.RunData
 */
package com.misys.portal.common.boreference;

import com.misys.portal.cash.product.ab.common.CustomerAccountSet;
import com.misys.portal.common.boreference.BOReferenceService;
import com.misys.portal.common.boreference.BOTypeHandler;
import com.misys.portal.common.boreference.BoRefConfigurationException;
import com.misys.portal.common.boreference.BoRefLengthException;
import com.misys.portal.common.boreference.DuplicateReferenceException;
import com.misys.portal.common.bulk.BulkObject;
import com.misys.portal.common.bulk.BulkReference;
import com.misys.portal.common.bulk.BulkReferencesSet;
import com.misys.portal.common.bulk.field.CodeField;
import com.misys.portal.common.bulk.field.IdField;
import com.misys.portal.common.bulk.field.StringField;
import com.misys.portal.common.resources.DefaultResourceProvider;
import com.misys.portal.common.tools.LargeParameterData;
import com.misys.portal.common.tools.LargeParameterDataSet;
import com.misys.portal.common.tools.LargeParameterKey;
import com.misys.portal.common.tools.Utils;
import com.misys.portal.common.tracer.GTPException;
import com.misys.portal.core.util.JetspeedResources;
import com.misys.portal.entity.common.Entity;
import com.misys.portal.product.common.Attachment;
import com.misys.portal.product.common.Bank;
import com.misys.portal.product.common.Charge;
import com.misys.portal.product.common.Correspondent;
import com.misys.portal.product.common.Counterparty;
import com.misys.portal.product.common.CrossReference;
import com.misys.portal.product.common.MasterCharge;
import com.misys.portal.product.common.MasterCounterparty;
import com.misys.portal.product.common.MasterCrossReference;
import com.misys.portal.product.util.CustomerReference;
import com.misys.portal.product.util.CustomerReferenceSet;
import com.misys.portal.product.util.EntityReference;
import com.misys.portal.product.util.Product;
import com.misys.portal.product.util.TransactionProductFile;
import com.misys.portal.product.util.builder.ProductFactory;
import com.misys.portal.product.util.generator.ReferenceIdGenerator;
import com.misys.portal.product.util.generator.TransactionIdGenerator;
import com.misys.portal.security.GTPCompany;
import com.misys.portal.security.GTPSecurity;
import com.misys.portal.services.db.PoolBrokerService;
import com.misys.portal.services.resources.PortalResources;
import com.misys.portal.systemfeatures.entityreference.EntityReferenceSet;
import com.misys.portal.util.db.adapter.DBAdapter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.security.Key;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.internet.MimeUtility;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.Turbine;
import org.apache.turbine.services.BaseService;
import org.apache.turbine.services.InitializationException;
import org.apache.turbine.util.RunData;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GTPBOReferenceService
        extends BaseService
        implements BOReferenceService {

    private static final String EMPTY_STRING = "";
    protected static final String REFERENCES = "references";
    protected static final String REF_ID = "ref_id";
    protected static final String TNX_ID = "tnx_id";
    protected static final String COMPANY_ID = "company_id";
    protected static final String COMPANY_NAME = "company_name";
    protected static final String ENTITY = "entity";
    protected static final String MAIN_BANK_ABBV_NAME = "main_bank_abbv_name";
    protected static final String MAIN_BANK_NAME = "main_bank_name";
    protected static final String CUSTOMER_BANK_REFERENCE = "customer_bank_reference";
    private static final Log LOG = LogFactory.getLog(GTPBOReferenceService.class);
    protected static final String STATEMENT = "statement";
    protected static final String ACCOUNT_ID = "account_id";
    protected static final String CUSTOMER_ID = "customer_id";
    protected static final String OWNER_ID = "owner_id";
    protected static final String USER_ID = "user_id";
    protected static final String COMPANY = "company";
    protected static final String STATEMENT_ID = "statement_id";
    protected static final String BALANCE_ID = "balance_id";
    protected static final String BALANCES = "balances";
    protected static final String TYPE = "type";
    protected static final String CHARGES = "charges";
    protected static final String BO_CUST_NUMBER = "bo_cust_number";
    protected static final String CUR_CODE = "cur_code";
    private static final String ATTACHMENTS = "attachments";
    private static final String CROSS_REFERENCES = "cross_references";
    private static final String BULK_CHILD_REFERNCE_DELIMTER = ",";
    private static final String CHILD_REFERENCES = "child_references";
    private static final String CHILD_REFERENCE = "child_reference";
    private static final String COMPANY_EXISTS = "company_exists";
    private static final String ADDRESS_LINE_1 = "address_line_1";
    private static final String ADDRESS_LINE_2 = "address_line_2";
    private static final String DOM = "dom";
    private static final String ISO_CODE = "iso_code";
    private static final String ROUTING_BIC = "routing_bic";
    private static final String COUNTRY = "country";
    protected static final String COMPANIES = "companies";
    private static final String BASE_CUR_CODE = "base_cur_code";
    private static final String DEFAULT = "DEFAULT";
    private static final String ACTV_FLAG = "actv_flag";
    private static final String COMPANY_ABBV_NAME = "company_abbv_name";
    private static final String COMPANY_ADDRESS_1 = "company_adress_1";
    private static final String COMPANY_ADDRESS_2 = "company_adress_2";
    private static final String COMPANY_DOM = "company_dom";
    private static final String SUB_PROD_CODE = "sub_prod_code";
    private static final String COMPANY_INFOS_FROM_ISO_CODE_STATEMENT = "SELECT e.company_id, e.abbv_name FROM GTP_ACCOUNT act join GTP_ENTITY_ACCOUNT ea on ea.account_id = act.account_id join GTP_ENTITY  e on e.entity_id = ea.entity_id WHERE act.iso_code = ? and act.account_no = ?";
    private static final String SQL_SELECT_ACCOUNT_STATEMENT_INFO = "SELECT stmt.statement_id, bal.type, bal.balance_id, act.account_id, ca.customer_id FROM GTP_ACCOUNT act INNER JOIN GTP_CUSTOMER_ACCOUNT ca ON ca.account_id = act.account_id LEFT OUTER JOIN GTP_ACCOUNT_STATEMENT stmt ON act.account_id = stmt.account_id AND stmt.type = '02' LEFT OUTER JOIN GTP_ACCOUNT_BALANCE bal ON stmt.statement_id = bal.statement_id WHERE act.account_id = ? AND customer_id = ? ORDER BY bal.type ASC";
    private static final String SQL_SELECT_ACCOUNT_STATEMENT_INFO_ON_IDX = "SELECT stmt.statement_id, bal.type, bal.balance_id, act.account_id, ca.customer_id FROM GTP_ACCOUNT act INNER JOIN GTP_CUSTOMER_ACCOUNT ca ON ca.account_id = act.account_id LEFT OUTER JOIN GTP_ACCOUNT_STATEMENT stmt ON act.account_id = stmt.account_id AND stmt.type = '02' AND stmt.idx = ? LEFT OUTER JOIN GTP_ACCOUNT_BALANCE bal ON stmt.statement_id = bal.statement_id WHERE act.account_id = ? AND customer_id = ? ORDER BY bal.type ASC";
    private static final String SQL_SELECT_COUNTERPARTY_TNX = "select counterparty_id from GTP_PRODUCT_COUNTERPARTY_TNX  where ref_id = ? and tnx_id = ?  order by counterparty_id asc";
    private static final String SQL_SELECT_COUNTERPARTY = "select counterparty_id from GTP_PRODUCT_COUNTERPARTY  where ref_id = ? order by counterparty_id asc";
    private static final String SQL_SELECT_CHARGE_TNX = "select chrg_id from GTP_PRODUCT_CHARGE_TNX  where ref_id = ? and tnx_id = ?  order by chrg_id asc";
    private static final String SQL_SELECT_CHARGE = "select chrg_id from GTP_PRODUCT_CHARGE  where ref_id = ? order by chrg_id asc";
    private static final String SQL_SELECT_ATTACHMENT = "Select attachment_id from GTP_ATTACHMENT_TNX  where ref_id= ? and tnx_id= ? and status is null";
    private static final String SQL_SELECT_CROSS_REFERNECE = "Select cross_reference_id from GTP_PRODUCT_CROSS_REF  where ref_id= ? ";
    private static final String SQL_SELECT_CROSS_REFERENCE_TNX = "Select cross_reference_id from GTP_PRODUCT_CROSS_REF_TNX  where (ref_id= ? and tnx_id = ?) or ( child_ref_id = ? and child_tnx_id = ?)";
    private static final String SQL_SELECT_CHILD_TNX_ID_FROM_CROSS_REFERENCE = "Select child_tnx_id from GTP_PRODUCT_CROSS_REF_TNX where ref_id = ? and tnx_id = ?";
    private static final String CUSTOMER_EXISTS = "Select reference from GTP_CUSTOMER_REFERENCE where reference = ?";
    private static final String CUSTOMER_EXISTS_WITH_DEFAULT_REFERENCE = "Select reference from GTP_CUSTOMER_REFERENCE where reference = ? and default_reference = ? ";
    private static final String COMPANY_USER_ID_STATEMENT = "Select user_id from GTP_USER where login_id = ? and company_abbv_name = ?";
    private static final String BANK_ABV_NAME_FROM_BR_CODE = "Select abbv_name ,type from GTP_COMPANY where treasury_branch_reference = ? ";
    private static final String COMPANY_BASE_CURCODE_STATEMENT = "select B.BASE_CUR_CODE,B.ABBV_NAME FROM GTP_COMPANY B WHERE  B.ABBV_NAME IN ";
    private static final String ACCOUNT_INFO_FROM_ACCOUNT_NO = "SELECT a.bank_abbv_name, a.bank_name, a.account_id FROM GTP_CUSTOMER_ACCOUNT ca INNER join GTP_ACCOUNT a ON ca.account_id = a.account_id and ca.reference = a.bo_cust_number WHERE ca.customer_id = ? AND a.account_no = ?";
    private static final String COMPANY_ENTITIES_STATEMENT = "select abbv_name from GTP_ENTITY_REFERENCE entity_ref ,GTP_ENTITY entity  where entity_ref.entity_id = entity.entity_id and entity_ref.reference = ?";
    private static final String COMPANY_INFOS_FROM_REFERENCE_STATEMENT = "select D.CUSTOMER_ABBV_NAME, D.BANK_ABBV_NAME, B.NAME, D.CUSTOMER_ID, B.ADDRESS_LINE_1, B.ADDRESS_LINE_2, B.DOM, B.ISO_CODE, B.COUNTRY, C.ACTV_FLAG from GTP_CUSTOMER_REFERENCE D,GTP_COMPANY B,GTP_COMPANY C WHERE  D.BRCH_CODE = '00001' AND  D.REFERENCE = ?  AND B.COMPANY_ID = D.BANK_ID AND D.CUSTOMER_ID =  C.COMPANY_ID";
    private static final String CUSTOMER_ID_FROM_BOTYPE = "Select ref.customer_id from GTP_CUSTOMER_REFERENCE ref inner join GTP_COMPANY c on ref.customer_id = c.company_id where and c.type = '03'";
    private static final String CHECK_ACCOUNT_BASED_ON_REFERENCE = "select a.account_id from GTP_ACCOUNT a inner join GTP_CUSTOMER_ACCOUNT ca on a.account_id = ca.account_id inner join GTP_CUSTOMER_REFERENCE cr on ca.customer_id = cr.customer_id where a.account_no = ? and cr.reference = ? ";
    private static final String FETCH_ISO_CODE_BASED_ON_REF = "Select c.iso_code from GTP_COMPANY c inner join GTP_CUSTOMER_REFERENCE cr on c.company_id=cr.bank_id and cr.reference = ?";
    private String config;
    private String boTypeConfig;
    private int seqLength = 8;
    private int length = 13;
    private String initValue = "";
    private Map matrix;
    private Map boTypeMatrix;
    private Map boTypeDatabaseColummnMatrix;
    private boolean retrieve_transaction;

    private static String retrieveLNRefidFromBoTnxid(String bo_tnx_id, String company_name, String product_code, String facility, String pricingOption, String repricingFreq, String lnAmt, String riskType) throws GTPException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("select REF_ID from ").append(ProductFactory.getTransactionTableName(product_code)).append(" where BO_TNX_ID = ? ").append(" and COMPANY_NAME = ? ").append(" and BO_FACILITY_ID=? ").append(" and PRICING_OPTION=? ").append(" and LN_AMT=? ").append(" and REPRICING_FREQUENCY=? ").append(" and RISK_TYPE=? ");
            connection = PoolBrokerService.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, bo_tnx_id);
            statement.setString(2, company_name);
            statement.setString(3, facility);
            statement.setString(4, pricingOption);
            statement.setBigDecimal(5, BigDecimal.valueOf(Double.valueOf(lnAmt)));
            statement.setString(6, repricingFreq);
            statement.setString(7, riskType);
            rs = statement.executeQuery();
            String result = null;
            while (rs.next()) {
                result = rs.getString(1);
                if (!rs.next()) {
                    continue;
                }
                return null;
            }
            if (result != null) {
                LOG.info((Object) ("N30123 - An existing Reference id " + result + " has been retrieved from the company's abbv_name " + company_name + " and the bo_tnx_id " + bo_tnx_id));
            }
            String string = result;
            return string;
        } catch (GTPException e) {
            LOG.error((Object) e.getMessage(), (Throwable) ((Object) e));
            throw e;
        } catch (SQLException e) {
            LOG.error((Object) ("SQLException : " + e.getMessage()), (Throwable) e);
            throw new GTPException("SQLException : " + e.getMessage(), e);
        } catch (Exception e) {
            LOG.error((Object) e.getMessage(), (Throwable) e);
            throw new GTPException(e.getMessage(), e);
        } finally {
            Utils.closeResources(connection, statement, rs);
        }
    }

    private static String retrieveRefidFromBoTnxid(String bo_tnx_id, String company_name, String product_code) throws Exception {
        Connection connection = PoolBrokerService.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        StringBuilder query = new StringBuilder();
        query.append("select REF_ID from ").append(ProductFactory.getTransactionTableName(product_code)).append(" where BO_TNX_ID = ? ").append(" and COMPANY_NAME = ? ");
        try {
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, bo_tnx_id);
            statement.setString(2, company_name);
            rs = statement.executeQuery();
            String result = null;
            while (rs.next()) {
                result = rs.getString(1);
                if (!rs.next()) {
                    continue;
                }
                throw new Exception("E30123 - At least two different references have been retrieved from the company's abbv_name " + company_name + " and the bo_tnx_id " + bo_tnx_id);
            }
            if (result != null) {
                LOG.info((Object) ("N30123 - An existing Reference id " + result + " has been retrieved from the company's abbv_name " + company_name + " and the bo_tnx_id " + bo_tnx_id));
            }
            String string = result;
            return string;
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, rs);
        }
    }

    @Override
    public String retrieveTnxIdFromBoRefId(String bo_ref_id, String product_code, String sub_product_code) throws Exception {
        LOG.info((Object) "Started fetching ref_id from bo_ref_id :: retrieveTnxIdFromBoRefId()");
        Connection connection = PoolBrokerService.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            StringBuilder query = new StringBuilder();
            ArrayList<String> bindvalues = new ArrayList<String>();
            query.append("select distinct P.REF_ID from ").append(ProductFactory.getMasterTableName(product_code)).append(" P where P.BO_REF_ID = ?");
            bindvalues.add(bo_ref_id);
            if (StringUtils.isNotBlank((String) sub_product_code)) {
                query.append(" and P.SUB_PRODUCT_CODE = ?");
                bindvalues.add(sub_product_code);
            }
            statement = connection.prepareStatement(query.toString());
            Utils.bindValueToPreparedStatement(statement, bindvalues);
            resultSet = statement.executeQuery();
            String result = null;
            if (resultSet.next()) {
                return null;
            }
            query = new StringBuilder();
            bindvalues.clear();
            query.append("select distinct P.TNX_ID from ").append(ProductFactory.getTransactionTableName(product_code)).append(" P where P.BO_REF_ID = ?");
            bindvalues.add(bo_ref_id);
            if (StringUtils.isNotBlank((String) sub_product_code)) {
                query.append(" and P.SUB_PRODUCT_CODE = ?");
                bindvalues.add(sub_product_code);
            }
            statement.close();
            statement = connection.prepareStatement(query.toString());
            Utils.bindValueToPreparedStatement(statement, bindvalues);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getString(1);
                if (!resultSet.next()) {
                    continue;
                }
                throw new Exception("N30123 - At least two references exist in the tnx table for the  bo_ref_id: " + bo_ref_id);
            }
            if (result != null) {
                LOG.info((Object) ("N30123 - An existing transaction id: " + result + " has been retrieved from tnx table for the bo_ref_id: " + bo_ref_id));
            }
            LOG.info((Object) "Done fetching tnx_id from bo_ref_id :: retrieveTnxIdFromBoRefId().");
            String string = result;
            return string;
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    private void appendNode(String rootElementName, BulkObject bulkObject, Node root, Document doc) throws GTPException {
        if (!bulkObject.isEmpty()) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                StringBuilder xmlBuffer = new StringBuilder();
                xmlBuffer.append("<").append(rootElementName).append(">").append(bulkObject.toXML(DefaultResourceProvider.LANGUAGE)).append("</").append(rootElementName).append(">");
                InputSource inputSource = new InputSource(new StringReader(xmlBuffer.toString()));
                Document bulkDocument = builder.parse(inputSource);
                Node importedBulkObjectNode = doc.importNode(bulkDocument.getFirstChild(), true);
                root.appendChild(importedBulkObjectNode);
            } catch (Exception e) {
                throw new GTPException(e);
            }
        }
    }

    private void appendSingleNode(Document workDoc, Node nodeToAppendTo, String nodeName, String value) throws GTPException {
        if (value != null) {
            Element newNodeToAppend = workDoc.createElement(nodeName);
            nodeToAppendTo.appendChild(newNodeToAppend);
            newNodeToAppend.setTextContent(value);
        }
    }

    @Override
    public boolean checkAccountBasedOnReference(String ref, String fee_acct_no) throws GTPException {
        boolean exists;
        if (StringUtils.isEmpty((String) ref) || StringUtils.isEmpty((String) fee_acct_no)) {
            return false;
        }
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet rset = null;
        exists = false;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                statement = connection.prepareStatement("select a.account_id from GTP_ACCOUNT a inner join GTP_CUSTOMER_ACCOUNT ca on a.account_id = ca.account_id inner join GTP_CUSTOMER_REFERENCE cr on ca.customer_id = cr.customer_id where a.account_no = ? and cr.reference = ? ");
                statement.setString(1, fee_acct_no);
                statement.setString(2, ref);
                rset = statement.executeQuery();
                if (rset.next()) {
                    exists = true;
                }
            } catch (Exception e) {
                throw new GTPException("Error occured while validating Fee account no:" + fee_acct_no + " for reference: " + ref, e);
            }
        } finally {
            Utils.closeResources(connection, statement, rset);
        }
        if (!exists) {
            LOG.error((Object) ("Fee account: " + fee_acct_no + " is not related to the applicant reference: " + ref));
        }
        return exists;
    }

    @Override
    public boolean checkIsoCodeFromBankReference(String ref, String isoCode) throws GTPException {
        String result;
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet rset = null;
        result = null;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                statement = connection.prepareStatement("Select c.iso_code from GTP_COMPANY c inner join GTP_CUSTOMER_REFERENCE cr on c.company_id=cr.bank_id and cr.reference = ?");
                statement.setString(1, ref);
                rset = statement.executeQuery();
                while (rset.next()) {
                    result = rset.getString(1);
                    if (!rset.next()) {
                        continue;
                    }
                    throw new GTPException("Error while fetching iso code. More than one bank is attached to the reference " + ref);
                }
            } catch (Exception e) {
                throw new GTPException("Error occured while fetching iso code for bank reference:" + ref, e);
            }
        } finally {
            Utils.closeResources(connection, statement, rset);
        }
        if (!StringUtils.isEmpty((String) result) && isoCode.equals(result)) {
            return true;
        }
        throw new GTPException("Transaction terminated. Iso code: " + isoCode + " is not related to the reference " + ref);
    }

    private void checkReferenceId(String ref_id, String product_code) throws Exception {
        LOG.info((Object) "Started checking if ref_id exists in DB :: checkReferenceId()");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                StringBuilder query = new StringBuilder("select ref_id from ").append(ProductFactory.getMasterTableName(product_code)).append(" where ref_id = ?");
                statement = connection.prepareStatement(query.toString());
                ArrayList<String> bindvalues = new ArrayList<String>(1);
                bindvalues.add(ref_id);
                Utils.bindValueToPreparedStatement(statement, bindvalues);
                resultSet = statement.executeQuery();
                String result = null;
                while (resultSet.next()) {
                    result = resultSet.getString(1);
                }
                if (result == null || !ref_id.equals(result)) {
                    throw new Exception("E30126 - The product given by the reference ID : " + ref_id + " doesn't exist in the database");
                }
                LOG.info((Object) "Done checking if ref_id exists in DB :: checkReferenceId()");
            } catch (Exception e) {
                LOG.error((Object) e.toString(), (Throwable) e);
                throw e;
            }
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    private void checkReferenceIdAndTransactionId(String ref_id, String tnx_id, String product_code) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                StringBuilder query = new StringBuilder();
                query.append("select ref_id, tnx_id from ").append(ProductFactory.getTransactionTableName(product_code.toUpperCase())).append(" where tnx_id = ? and ref_id = ?");
                statement = connection.prepareStatement(query.toString());
                ArrayList<String> bindvalues = new ArrayList<String>(2);
                bindvalues.add(tnx_id);
                bindvalues.add(ref_id);
                Utils.bindValueToPreparedStatement(statement, bindvalues);
                resultSet = statement.executeQuery();
                String ref = null;
                String tnx = null;
                while (resultSet.next()) {
                    ref = resultSet.getString(1);
                    tnx = resultSet.getString(2);
                }
                if (ref == null || tnx == null || !ref_id.equals(ref) || !tnx_id.equals(tnx)) {
                    throw new Exception("E30127 - The transaction given by the transaction ID : " + tnx_id + " and the reference Id : " + ref_id + " doesn't exist in the database");
                }
            } catch (Exception e) {
                LOG.error((Object) e.toString(), (Throwable) e);
                throw e;
            }
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    private void checkTransactionId(String tnx_id, String product_code) throws Exception {
        LOG.info((Object) "Started checking if tnx_id exists in DB :: checkTransactionId()");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                StringBuilder query = new StringBuilder();
                query.append("select ref_id from ").append(ProductFactory.getTransactionTableName(product_code)).append(" where tnx_id = ?");
                statement = connection.prepareStatement(query.toString());
                ArrayList<String> bindvalues = new ArrayList<String>(1);
                bindvalues.add(tnx_id);
                Utils.bindValueToPreparedStatement(statement, bindvalues);
                resultSet = statement.executeQuery();
                String result = null;
                while (resultSet.next()) {
                    result = resultSet.getString(1);
                }
                if (result == null) {
                    throw new Exception("E30126 - The product given by the transaction ID : " + tnx_id + " doesn't exist in the database");
                }
                LOG.info((Object) "Done checking if tnx_id exists in DB :: checkTransactionId()");
            } catch (Exception e) {
                LOG.error((Object) e.toString(), (Throwable) e);
                throw e;
            }
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    @Override
    public String detectProductCodeFromBoRefId(String bo_ref_id, String product_codes) throws Exception {
        Connection connection = PoolBrokerService.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String[] product_array = StringUtils.split((String) product_codes, (char) ',');
            StringBuilder query = new StringBuilder();
            int boRefIdCount = 0;
            boolean firstEntry = true;
            String[] arrstring = product_array;
            int n = arrstring.length;
            int n2 = 0;
            while (n2 < n) {
                String product_code = arrstring[n2];
                if (!firstEntry) {
                    query.append(" union ");
                }
                query.append("select count(ref_id), product_code from ");
                query.append(ProductFactory.getMasterTableName(product_code));
                query.append(" where bo_ref_id = ? group by product_code ");
                firstEntry = false;
                ++boRefIdCount;
                ++n2;
            }
            statement = connection.prepareStatement(query.toString());
            int i = 1;
            while (i <= boRefIdCount) {
                statement.setString(i, bo_ref_id);
                ++i;
            }
            resultSet = statement.executeQuery();
            ArrayList<String> productsWithReference = new ArrayList<String>();
            while (resultSet.next()) {
                if (resultSet.getInt(1) <= 0) {
                    continue;
                }
                productsWithReference.add(resultSet.getString(2));
            }
            if (productsWithReference.size() == 0) {
                throw new Exception("E30128 - The given bank reference id (bo_ref_id): " + bo_ref_id + " doesn't exist for the product codes " + product_codes);
            }
            if (productsWithReference.size() > 1) {
                throw new Exception("E30129 - The given bank reference id (bo_ref_id): " + bo_ref_id + " exists for the following product codes: " + StringUtils.join(productsWithReference.iterator(), (String) ", ") + ". Can't resolve product.");
            }
            String string = (String) productsWithReference.get(0);
            return string;
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    @Override
    public String encryptForMHUB(String textToEnc) throws GTPException {
        if (textToEnc == null) {
            return null;
        }
        if (!JetspeedResources.getBoolean("security.password_encrypt_communication", false)) {
            return textToEnc;
        }
        try {
            String signingKeyFile = JetspeedResources.getString("security.password_encrypt_communication.keyfile");
            byte[] signing_key = null;
            if (signingKeyFile != null) {
                signing_key = this.loadKeyFile(signingKeyFile);
            }
            if (signing_key == null) {
                String strSigningKey = JetspeedResources.getString("security.password_encrypt_communication.key");
                signing_key = strSigningKey.getBytes(DefaultResourceProvider.DEFAULT_ENCODING);
            }
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(signing_key);
            byte[] tamperedHash = new byte[hash.length];
            System.arraycopy(hash, 0, tamperedHash, 0, hash.length);
            int i = 5;
            while (i < tamperedHash.length) {
                tamperedHash[i] = 0;
                ++i;
            }
            Cipher rc4 = Cipher.getInstance("RC4");
            rc4.init(1, new SecretKeySpec(tamperedHash, "RC4"));
            byte[] bytesResult = rc4.doFinal(textToEnc.getBytes(DefaultResourceProvider.DEFAULT_ENCODING));
            ByteArrayOutputStream bas = new ByteArrayOutputStream(bytesResult.length + bytesResult.length / 3 + 1);
            OutputStream encodedStream = MimeUtility.encode((OutputStream) bas, (String) "base64");
            encodedStream.write(bytesResult);
            ByteArrayOutputStream bash = new ByteArrayOutputStream(hash.length + hash.length / 3 + 1);
            OutputStream encodedStreamh = MimeUtility.encode((OutputStream) bash, (String) "base64");
            encodedStreamh.write(hash);
            String result = String.valueOf(bash.toString()) + bas.toString();
            return result;
        } catch (Exception e) {
            LOG.error((Object) "N00008 - BaseSecurityService : ", (Throwable) e);
            return null;
        }
    }

    @Override
    public void generateBOReferences(LargeParameterDataSet parameters)
            throws GTPException, BoRefConfigurationException, DuplicateReferenceException, BoRefLengthException {
        for (Iterator iter = parameters.iterator(); iter.hasNext();) {
            LargeParameterData element = (LargeParameterData) iter.next();
            HashSet set = new HashSet();
            if ("multiple".equals(element.getKeys().getKey("KEY_6"))) {
                String product = element.getKeys().getKey("KEY_3");
                String subProduct = element.getKeys().getKey("KEY_11");

                product = getSubProductCode(product, subProduct);
                if (StringUtils.isEmpty(product)) {
                    throw new BoRefConfigurationException("ERROR_MSG_BO_REFERENCE_CONFIGURATION");
                }
                int from = 1;
                if (!"**".equals(element.getKeys().getKey("KEY_4"))) {
                    from = Integer.parseInt(element.getKeys().getKey("KEY_4"));
                }
                int to = 0;
                if (!"**".equals(element.getKeys().getKey("KEY_5"))) {
                    to = Integer.parseInt(element.getKeys().getKey("KEY_5"));
                }
                String customerInputCenter = element.getKeys().getKey("KEY_8");
                while (from <= to) {
                    String reference = product + customerInputCenter + this.initValue;
                    reference = reference
                            .substring(0, reference.length() - String.valueOf(from).length()) + from;

                    HashMap dataValues = new HashMap();
                    dataValues.put("DATA_1", reference);
                    dataValues.put("DATA_2", "N");
                    element.putData(dataValues);

                    from++;
                }
                List<Map<String, String>> list = element.getData();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    HashMap map = (HashMap) it.next();
                    String reference = map.get("DATA_1") != null
                            ? map.get("DATA_1").toString() : null;
                    if ((reference != null)
                            && (reference.length() <= DefaultResourceProvider.BO_REFERENCE_LENGTH)) {
                        set.add(map.get("DATA_1"));
                    } else {
                        throw new BoRefLengthException("ERROR_MSG_BO_REFERENCE_LENGTH");
                    }
                }
            } else {
                String reference = element.getKeys().getKey("KEY_7");

                HashMap dataValues = new HashMap();
                dataValues.put("DATA_1", reference);
                dataValues.put("DATA_2", "N");
                element.putData(dataValues);
                set.add(reference);
            }
            if (!verifyWithExistingBORefid(element, set)) {
                throw new DuplicateReferenceException("ERROR_MSG_BO_REFERENCE_UNICITY");
            }
            element.save();
        }
    }

    private List<Attachment> getAttachmentsFrom(String refId, String tnxId) throws Exception {
        ArrayList<Attachment> attachmentsList;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        StringBuilder query = new StringBuilder();
        attachmentsList = new ArrayList<Attachment>();
        try {
            try {
                con = PoolBrokerService.getConnection();
                query.append("Select attachment_id from GTP_ATTACHMENT_TNX  where ref_id= ? and tnx_id= ? and status is null");
                stmt = con.prepareStatement(query.toString());
                stmt.setString(1, refId);
                stmt.setString(2, tnxId);
                rset = stmt.executeQuery();
                while (rset.next()) {
                    String attId = rset.getString(1);
                    Attachment tmpAtt = new Attachment();
                    tmpAtt.retrieveAttachmentsFromTnx(refId, tnxId, attId);
                    attachmentsList.add(tmpAtt);
                }
            } catch (Exception e) {
                LOG.error((Object) e.toString(), (Throwable) e);
                throw new Exception("E30114 - Unable to retrieve the attachments from the ReferenceId : " + refId + "TransactionId :" + tnxId);
            }
        } finally {
            Utils.closeResources(con, stmt, rset);
        }
        return attachmentsList;
    }

    @Override
    public String getBackOfficeType(String productCode) {
        return this.getBackOfficeType(productCode, null);
    }

    @Override
    public String getBackOfficeType(String product_code, String sub_product_code) {
        Map subProdMap = (Map) this.boTypeMatrix.get(product_code);
        if (subProdMap != null) {
            Object obj = subProdMap.get(sub_product_code);
            if (obj == null) {
                return (String) subProdMap.get("DEFAULT");
            }
            return (String) obj;
        }
        return null;
    }

    @Override
    public String getBankNameFromCustRef(String custRef, String custAbbvName) {
        String bankName;
        block6:
        {
            Connection con = null;
            PreparedStatement preparedStmt = null;
            ResultSet rset = null;
            bankName = null;
            try {
                try {
                    con = PoolBrokerService.getConnection();
                    preparedStmt = con.prepareStatement("select bank_abbv_name from gtp_customer_reference where reference = ? and customer_abbv_name = ?");
                    ArrayList<String> bindvalues = new ArrayList<String>();
                    bindvalues.add(custRef);
                    bindvalues.add(custAbbvName);
                    Utils.bindValueToPreparedStatement(preparedStmt, bindvalues);
                    rset = preparedStmt.executeQuery();
                    if (rset.next()) {
                        bankName = rset.getString(1);
                        break block6;
                    }
                    LOG.error((Object) ("No Record found for custRef: " + custRef + " and custAbbvName: " + custAbbvName));
                } catch (Exception e) {
                    LOG.error((Object) ("Error occured while retrieving bankAbbvName for custRef: " + custRef + " and custAbbvName: "), (Throwable) e);
                    Utils.closeResources(con, preparedStmt, rset);
                }
            } finally {
                Utils.closeResources(con, preparedStmt, rset);
            }
        }
        return bankName;
    }

    @Override
    public String getBORefByCustRef(String ref, String boType) throws GTPException {
        String boTypeColumnName = (String) this.boTypeDatabaseColummnMatrix.get(boType);
        CustomerReference reference = null;
        CustomerReferenceSet customerReferences = new CustomerReferenceSet();
        customerReferences.retrieveByCustomerReference(ref);
        Iterator<CustomerReference> itr = customerReferences.getListOfCustomerReferences().iterator();
        if (itr.hasNext()) {
            reference = itr.next();
        }
        boolean defaultEnabled = DefaultResourceProvider.REFERENCE_EMPTY_BACK_OFFICE_USE_REFERENCE;
        if (DefaultResourceProvider.REFERENCE_BACK_OFFICE_ENABLED) {
            String boTypeRef;
            if (reference != null && (boTypeRef = reference.getBOTypeRef(boTypeColumnName)) != null) {
                return boTypeRef;
            }
            return defaultEnabled ? ref : null;
        }
        return ref;
    }

    @Override
    public String getBOTypeCustRef(CustomerReference ref, String product_code, String sub_product_code) throws GTPException {
        String boType = this.getBackOfficeType(product_code, sub_product_code);
        String boTypeColumnName = (String) this.boTypeDatabaseColummnMatrix.get(boType);
        String custRef = ref.getReference();
        boolean defaultEnabled = DefaultResourceProvider.REFERENCE_EMPTY_BACK_OFFICE_USE_REFERENCE;
        if (DefaultResourceProvider.REFERENCE_BACK_OFFICE_ENABLED) {
            String boTypeRef = ref.getBOTypeRef(boTypeColumnName);
            if (boTypeRef != null) {
                return boTypeRef;
            }
            return defaultEnabled ? custRef : null;
        }
        return custRef;
    }

    @Override
    public boolean isValidCustomerReference(CustomerReference ref, String product_code, String sub_product_code) {
        String boType = this.getBackOfficeType(product_code, sub_product_code);
        String boTypeColumnName = (String) this.boTypeDatabaseColummnMatrix.get(boType);
        boolean returnValue = false;
        if (DefaultResourceProvider.REFERENCE_BACK_OFFICE_ENABLED) {
            String boTypeRef = ref.getBOTypeRef(boTypeColumnName);
            returnValue = boTypeRef != null && boTypeRef.length() != 0 ? true : DefaultResourceProvider.REFERENCE_EMPTY_BACK_OFFICE_USE_REFERENCE;
        }
        return returnValue;
    }

    @Override
    public boolean isValidCustomerReference(String ref, String product_code, String sub_product_code) {
        CustomerReferenceSet customerReferences = new CustomerReferenceSet();
        try {
            customerReferences.retrieveByCustomerReference(ref);
        } catch (GTPException e) {
            LOG.error((Object) "Error occured while retrieving the customer reference.", (Throwable) ((Object) e));
        }
        return this.isValidCustomerReference(customerReferences.getListOfCustomerReferences().iterator().next(), product_code, sub_product_code);
    }

    @Override
    public String getBOTypeCustRef(String custRef, String product_code, String sub_product_code) throws GTPException {
        String boType = this.getBackOfficeType(product_code, sub_product_code);
        String boTypeColumnName = (String) this.boTypeDatabaseColummnMatrix.get(boType);
        boolean defaultEnabled = DefaultResourceProvider.REFERENCE_EMPTY_BACK_OFFICE_USE_REFERENCE;
        if (DefaultResourceProvider.REFERENCE_BACK_OFFICE_ENABLED) {
            CustomerReference ref = null;
            CustomerReferenceSet customerReferences = new CustomerReferenceSet();
            customerReferences.retrieveByCustomerReference(custRef);
            Iterator<CustomerReference> itr = customerReferences.getListOfCustomerReferences().iterator();
            if (itr.hasNext()) {
                ref = itr.next();
            }
            if (ref == null) {
                return custRef;
            }
            String boTypeRef = ref.getBOTypeRef(boTypeColumnName);
            if (boTypeRef != null) {
                return boTypeRef;
            }
            return defaultEnabled ? custRef : null;
        }
        return custRef;
    }

    @Override
    public String getBOTypeCustRefColumnName(String product_code, String sub_product_code) {
        String backOfficeType = this.getBackOfficeType(product_code, sub_product_code);
        if (DefaultResourceProvider.REFERENCE_BACK_OFFICE_ENABLED) {
            return (String) this.boTypeDatabaseColummnMatrix.get(backOfficeType);
        }
        return null;
    }

    private List<BulkObject> getCounterpartyList(String refId, String tnxId) throws GTPException {
        ArrayList<BulkObject> counterparyList;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        counterparyList = new ArrayList<BulkObject>();
        StringBuilder query = new StringBuilder();
        try {
            try {
                con = PoolBrokerService.getConnection();
                ArrayList<String> bindVarList = new ArrayList<String>();
                bindVarList.add(refId);
                if (tnxId == null) {
                    query.append("select counterparty_id from GTP_PRODUCT_COUNTERPARTY  where ref_id = ? order by counterparty_id asc");
                } else {
                    query.append("select counterparty_id from GTP_PRODUCT_COUNTERPARTY_TNX  where ref_id = ? and tnx_id = ?  order by counterparty_id asc");
                    bindVarList.add(tnxId);
                }
                pstmt = con.prepareStatement(query.toString());
                Utils.bindValueToPreparedStatement(pstmt, bindVarList);
                rset = pstmt.executeQuery();
                while (rset.next()) {
                    int counterpartyId = rset.getInt(1);
                    if (tnxId == null) {
                        MasterCounterparty masterCounterparty = new MasterCounterparty();
                        masterCounterparty.retrieveCounterpartyFromProduct(refId, counterpartyId);
                        counterparyList.add(masterCounterparty);
                        continue;
                    }
                    Counterparty counterparty = new Counterparty();
                    counterparty.retrieveCounterpartyFromProductTnx(refId, tnxId, counterpartyId);
                    counterparyList.add(counterparty);
                }
            } catch (Exception e) {
                throw new GTPException(null, "Error while fetching counterparties:" + query.toString(), e);
            }
        } finally {
            Utils.closeResources(con, pstmt, rset);
        }
        return counterparyList;
    }

    private List<BulkObject> getCrossReference(String refId, String tnxId) throws GTPException {
        ArrayList<BulkObject> crossReferenceList;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        crossReferenceList = new ArrayList<BulkObject>();
        StringBuilder query = new StringBuilder();
        try {
            try {
                con = PoolBrokerService.getConnection();
                ArrayList<String> bindVarList = new ArrayList<String>();
                bindVarList.add(refId);
                if (tnxId == null) {
                    query.append("Select cross_reference_id from GTP_PRODUCT_CROSS_REF  where ref_id= ? ");
                } else {
                    query.append("Select cross_reference_id from GTP_PRODUCT_CROSS_REF_TNX  where (ref_id= ? and tnx_id = ?) or ( child_ref_id = ? and child_tnx_id = ?)");
                    bindVarList.add(tnxId);
                    bindVarList.add(refId);
                    bindVarList.add(tnxId);
                }
                pstmt = con.prepareStatement(query.toString());
                Utils.bindValueToPreparedStatement(pstmt, bindVarList);
                rset = pstmt.executeQuery();
                while (rset.next()) {
                    int crossReferenceId = rset.getInt(1);
                    if (tnxId == null) {
                        MasterCrossReference masterCrossReference = new MasterCrossReference();
                        masterCrossReference.retrieveFromDB(new Integer(crossReferenceId).toString());
                        crossReferenceList.add(masterCrossReference);
                        continue;
                    }
                    CrossReference crossReference = new CrossReference();
                    crossReference.retrieveFromDB(new Integer(crossReferenceId).toString());
                    crossReferenceList.add(crossReference);
                }
            } catch (Exception e) {
                throw new GTPException(null, "Error while fetching cross references:" + query.toString(), e);
            }
        } finally {
            Utils.closeResources(con, pstmt, rset);
        }
        return crossReferenceList;
    }

    @Override
    public Integer getCustomerIdFromCustomerReference(String backOfficeType, String back_office_reference) throws GTPException {
        Integer result;
        String boTypeDatabaseColumn = (String) this.boTypeDatabaseColummnMatrix.get(backOfficeType);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        result = 0;
        int count = 0;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                StringBuilder query = new StringBuilder();
                query.append("Select ref.customer_id from GTP_CUSTOMER_REFERENCE ref inner join GTP_COMPANY c on ref.customer_id = c.company_id where and c.type = '03'");
                query.append(" AND ").append("D.").append(boTypeDatabaseColumn).append(" = ? ");
                statement = connection.prepareStatement(query.toString());
                statement.setString(1, back_office_reference);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    result = resultSet.getInt(1);
                    ++count;
                }
                if (count > 1) {
                    String errorMsg = "Found more than one customer linked to reference " + back_office_reference;
                    LOG.error((Object) errorMsg);
                    throw new GTPException(errorMsg);
                }
                if (count <= 0) {
                    String errorMsg = "Found no customer linked to reference " + back_office_reference;
                    LOG.error((Object) errorMsg);
                    throw new GTPException(errorMsg);
                }
            } catch (Exception e) {
                LOG.error((Object) ("Error while getting Customer from Customer Reference " + back_office_reference), (Throwable) e);
                throw new GTPException(e);
            }
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
        return result;
    }

    @Override
    public String getDefCustRef(String boCustRef, List banks, String product_code, String sub_product_code) throws GTPException {
        String boType = this.getBackOfficeType(product_code, sub_product_code);
        return this.getDefCustRef(boCustRef, boType, banks);
    }

    @Override
    public String getDefCustRef(String boCustRef, String bo_type, List banks) throws GTPException {
        String boTypeColumnName = (String) this.boTypeDatabaseColummnMatrix.get(bo_type);
        if (DefaultResourceProvider.REFERENCE_BACK_OFFICE_ENABLED) {
            CustomerReference ref = null;
            CustomerReferenceSet customerReferences = new CustomerReferenceSet();
            customerReferences.retrieveByCustomerReference(boCustRef, boTypeColumnName, banks);
            Iterator<CustomerReference> itr = customerReferences.getListOfCustomerReferences().iterator();
            if (itr.hasNext()) {
                ref = itr.next();
            }
            if (ref == null) {
                return boCustRef;
            }
            return ref.getReference();
        }
        return boCustRef;
    }

    public int getLength() {
        return this.length;
    }

    @Override
    public String getNext(TransactionProductFile product) throws GTPException {
        String ret;
        ret = null;
        String param_id = null;
        Connection con = null;
        PreparedStatement stmt = null;
        String mainBankAbbvName = null;
        String customerReference = null;
        ResultSet rset = null;
        if (product.getMain_bank() != null) {
            mainBankAbbvName = product.getMain_bank().getAbbv_name().getValue();
        }
        if (mainBankAbbvName == null) {
            return null;
        }
        LargeParameterKey keys = new LargeParameterKey();
        keys.setParm_id("P225");
        keys.setCompany_id(product.getCompany_id().getValue());
        keys.setBrch_code("00001");
        keys.addKey("KEY_1", mainBankAbbvName);
        if ("EL".equals(product.getProduct_code().getValue()) || "IC".equals(product.getProduct_code().getValue()) || "IR".equals(product.getProduct_code().getValue()) || "SR".equals(product.getProduct_code().getValue()) || "BR".equals(product.getProduct_code().getValue()) || "RI".equals(product.getProduct_code().getValue())) {
            customerReference = product.getReceiver().getReference().getValue();
            keys.addKey("KEY_9", customerReference);
        } else {
            customerReference = product.getSender().getReference().getValue();
            keys.addKey("KEY_9", customerReference);
        }
        keys.addKey("KEY_2", "**");
        keys.addKey("KEY_3", product.getProduct_code().getValue());
        keys.addKey("KEY_4", "**");
        keys.addKey("KEY_5", "**");
        keys.addKey("KEY_6", "**");
        keys.addKey("KEY_7", "**");
        keys.addKey("KEY_8", "**");
        keys.addKey("KEY_10", "**");
        LargeParameterData lpd = new LargeParameterData();
        lpd.retrieveBestMatchFrom(keys);
        List<Map<String, String>> references = lpd.getData();
        StringBuilder query = null;
        try {
            try {
                con = PoolBrokerService.getConnection();
                DBAdapter dbAdapter = PoolBrokerService.getDBAdapter();
                if (references.size() > 0) {
                    query = new StringBuilder("select data.data_1, data.param_id from ");
                    query.append("GTP_LARGE_PARAM_DATA").append(" data inner join ").append("GTP_LARGE_PARAM_KEY").append(" tkey on tkey.param_id=data.param_id").append(" where tkey.company_id = ? and tkey.brch_code = ? and tkey.key_1 = ? and tkey.key_9 = ?");
                    query.append(" and tkey.key_3 = ? and tkey.parm_id = ? and data.data_2 = 'N' and data.data_1 NOT IN (select distinct ").append(dbAdapter.nullValue("bo_ref_id", "' '")).append(" from ");
                    query.append(ProductFactory.getTransactionTableName(product.getProduct_code().getValue()));
                    query.append(") order by tkey.wild_card_ind, data.data_1");
                    stmt = con.prepareStatement(query.toString());
                    ArrayList<String> bindvalues = new ArrayList<String>();
                    bindvalues.add(product.getCompany_id().getValue().toString());
                    bindvalues.add("00001");
                    bindvalues.add(product.getMain_bank().getAbbv_name().getValue());
                    bindvalues.add(customerReference);
                    bindvalues.add(product.getProduct_code().getValue());
                    bindvalues.add("P225");
                    Utils.bindValueToPreparedStatement(stmt, bindvalues);
                    rset = stmt.executeQuery();
                    if (rset.next()) {
                        ret = rset.getString(1);
                        param_id = rset.getString(2);
                    } else {
                        LOG.warn((Object) ("All pre-allocated BO References for the customer " + product.getCompany_name() + " and entity " + product.getEntity() + " and customer " + customerReference + " and product " + product.getProduct_code() + " have been used."));
                    }
                    if (StringUtils.isNotBlank((String) ret)) {
                        query = new StringBuilder();
                        query.append("update GTP_LARGE_PARAM_DATA set DATA_2 = ? where DATA_1 = ? and param_id = ? ");
                        stmt.close();
                        stmt = null;
                        stmt = con.prepareStatement(query.toString());
                        stmt.setString(1, "Y");
                        stmt.setString(2, ret);
                        stmt.setString(3, param_id);
                        stmt.executeUpdate();
                    }
                }
            } catch (Exception e) {
                throw new GTPException("BO Reference service, exception, query=" + (query == null ? "??" : query.toString()), e);
            }
        } finally {
            Utils.closeResources(con, stmt, rset);
        }
        return ret;
    }

    private List<BulkObject> getProductChargesList(String refId, String tnxId) throws GTPException {
        ArrayList<BulkObject> productChargesList;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        productChargesList = new ArrayList<BulkObject>();
        StringBuilder query = new StringBuilder();
        try {
            try {
                con = PoolBrokerService.getConnection();
                ArrayList<String> bindVarList = new ArrayList<String>();
                bindVarList.add(refId);
                if (tnxId == null) {
                    query.append("select chrg_id from GTP_PRODUCT_CHARGE  where ref_id = ? order by chrg_id asc");
                } else {
                    query.append("select chrg_id from GTP_PRODUCT_CHARGE_TNX  where ref_id = ? and tnx_id = ?  order by chrg_id asc");
                    bindVarList.add(tnxId);
                }
                pstmt = con.prepareStatement(query.toString());
                Utils.bindValueToPreparedStatement(pstmt, bindVarList);
                rset = pstmt.executeQuery();
                while (rset.next()) {
                    int chargeId = rset.getInt(1);
                    if (tnxId == null) {
                        MasterCharge masterCharge = new MasterCharge();
                        masterCharge.retrieveChargeFromProduct(refId, String.valueOf(chargeId));
                        productChargesList.add(masterCharge);
                        continue;
                    }
                    Charge charge = new Charge();
                    charge.retrieveChargeFromProductTnx(refId, tnxId, String.valueOf(chargeId));
                    productChargesList.add(charge);
                }
            } catch (Exception e) {
                throw new GTPException(null, "Error while fetching charges:" + query.toString(), e);
            }
        } finally {
            Utils.closeResources(con, pstmt, rset);
        }
        return productChargesList;
    }

    @Override
    public int getReferenceLength() {
        return this.length;
    }

    @Override
    public int getSequenceLength() {
        return this.seqLength;
    }

    public String getSubProductCode(String productCode) {
        return this.getSubProductCode(productCode, null);
    }

    public String getSubProductCode(String product_code, String sub_product_code) {
        Map subProdMap = (Map) this.matrix.get(product_code);
        if (subProdMap != null) {
            Object obj = subProdMap.get(sub_product_code);
            if (obj == null) {
                return (String) subProdMap.get("DEFAULT");
            }
            return (String) obj;
        }
        return null;
    }

    public void init() throws InitializationException {
        this.config = this.getProperties().getProperty("config", "/core/references/reference.xml");
        this.boTypeConfig = this.getProperties().getProperty("botypeconfig", "/core/references/back_office_reference.xml");
        try {
            BOReferencesHandler handler = new BOReferencesHandler();
            handler.process(this.config);
            this.matrix = handler.getMatrix();
            this.seqLength = handler.getSeq_length();
            this.length = handler.getLength();
            int i = 0;
            while (i < this.seqLength) {
                this.initValue = String.valueOf(this.initValue) + "0";
                ++i;
            }
            BOTypeHandler boTypeHandler = new BOTypeHandler();
            boTypeHandler.process(this.boTypeConfig);
            this.boTypeMatrix = boTypeHandler.getMatrix();
            this.boTypeDatabaseColummnMatrix = new HashMap();
            if (DefaultResourceProvider.REFERENCE_BACK_OFFICE_1 != null) {
                this.boTypeDatabaseColummnMatrix.put(DefaultResourceProvider.REFERENCE_BACK_OFFICE_1, "BACK_OFFICE_1");
            }
            if (DefaultResourceProvider.REFERENCE_BACK_OFFICE_2 != null) {
                this.boTypeDatabaseColummnMatrix.put(DefaultResourceProvider.REFERENCE_BACK_OFFICE_2, "BACK_OFFICE_2");
            }
            if (DefaultResourceProvider.REFERENCE_BACK_OFFICE_3 != null) {
                this.boTypeDatabaseColummnMatrix.put(DefaultResourceProvider.REFERENCE_BACK_OFFICE_3, "BACK_OFFICE_3");
            }
            if (DefaultResourceProvider.REFERENCE_BACK_OFFICE_4 != null) {
                this.boTypeDatabaseColummnMatrix.put(DefaultResourceProvider.REFERENCE_BACK_OFFICE_4, "BACK_OFFICE_4");
            }
            if (DefaultResourceProvider.REFERENCE_BACK_OFFICE_5 != null) {
                this.boTypeDatabaseColummnMatrix.put(DefaultResourceProvider.REFERENCE_BACK_OFFICE_5, "BACK_OFFICE_5");
            }
            if (DefaultResourceProvider.REFERENCE_BACK_OFFICE_6 != null) {
                this.boTypeDatabaseColummnMatrix.put(DefaultResourceProvider.REFERENCE_BACK_OFFICE_6, "BACK_OFFICE_6");
            }
            if (DefaultResourceProvider.REFERENCE_BACK_OFFICE_7 != null) {
                this.boTypeDatabaseColummnMatrix.put(DefaultResourceProvider.REFERENCE_BACK_OFFICE_7, "BACK_OFFICE_7");
            }
            if (DefaultResourceProvider.REFERENCE_BACK_OFFICE_8 != null) {
                this.boTypeDatabaseColummnMatrix.put(DefaultResourceProvider.REFERENCE_BACK_OFFICE_8, "BACK_OFFICE_8");
            }
            if (DefaultResourceProvider.REFERENCE_BACK_OFFICE_9 != null) {
                this.boTypeDatabaseColummnMatrix.put(DefaultResourceProvider.REFERENCE_BACK_OFFICE_9, "BACK_OFFICE_9");
            }
            this.retrieve_transaction = Boolean.valueOf(this.getProperties().getProperty("retrieve.transaction", "false"));
            this.setInit(true);
        } catch (Exception e) {
            this.setInit(false);
            throw new InitializationException("GTPBOReferenceService, unable to initalize service.", (Throwable) e);
        }
    }

    private boolean isCustomerExists(String customer_bank_reference, List<String> banks) throws Exception {
        boolean result;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        result = false;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                StringBuilder query = new StringBuilder();
                query.append("Select reference from GTP_CUSTOMER_REFERENCE where reference = ?");
                if (banks != null && !banks.isEmpty()) {
                    String inPlaceHolders = Utils.preparePlaceHolders(banks.size());
                    query.append(" AND BANK_ABBV_NAME IN (" + inPlaceHolders + ") ");
                }
                statement = connection.prepareStatement(query.toString());
                statement.setString(1, customer_bank_reference);
                if (banks != null && !banks.isEmpty()) {
                    Iterator<String> iterator = banks.iterator();
                    int i = 2;
                    while (iterator.hasNext()) {
                        statement.setString(i++, iterator.next());
                    }
                }
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    result = true;
                }
            } catch (Exception e) {
                LOG.error((Object) "References Manager", (Throwable) e);
                Utils.closeResources(connection, statement, resultSet);
            }
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
        return result;
    }

    @Override
    public boolean isDefaultCustomerReference(String customer_bank_reference, String defaultRefVal, List banks) throws Exception {
        boolean result;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> bindValues = new ArrayList<String>();
        result = false;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                StringBuilder query = new StringBuilder();
                query.append("Select reference from GTP_CUSTOMER_REFERENCE where reference = ? and default_reference = ? ");
                if (banks != null && !banks.isEmpty()) {
                    query.append(" AND BANK_ABBV_NAME in (  ");
                    Iterator iterator = banks.iterator();
                    boolean bFirst = true;
                    while (iterator.hasNext()) {
                        if (bFirst) {
                            bFirst = false;
                        } else {
                            query.append(" , ");
                        }
                        query.append(" ? ");
                        bindValues.add((String) iterator.next());
                    }
                    query.append(") ");
                }
                statement = connection.prepareStatement(query.toString());
                statement.setString(1, customer_bank_reference);
                statement.setString(2, defaultRefVal);
                int index = 3;
                for (String value : bindValues) {
                    statement.setString(index++, value);
                }
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    result = true;
                }
            } catch (Exception e) {
                LOG.error((Object) "References Manager", (Throwable) e);
                Utils.closeResources(connection, statement, resultSet);
            }
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
        return result;
    }

    @Override
    public boolean isDefaultCustomerReference(String customer_bank_reference, String defaultRefVal, List banks, String product_code, String sub_product_code) throws Exception {
        String default_customer_bank_reference = this.getDefCustRef(customer_bank_reference, banks, product_code, sub_product_code);
        return this.isDefaultCustomerReference(default_customer_bank_reference, defaultRefVal, banks);
    }

    @Override
    public boolean isDefaultCustomerReference(String customer_bank_reference, String defaultRefVal, String bank) throws Exception {
        ArrayList<String> banks = new ArrayList<String>();
        if (StringUtils.isEmpty((String) bank)) {
            banks.add(bank);
        }
        return this.isDefaultCustomerReference(customer_bank_reference, defaultRefVal, banks);
    }

    private byte[] loadKeyFile(String filename) {
        byte[] output = null;
        try {
            FileInputStream fis = new FileInputStream(Turbine.getRealPath((String) filename));
            output = new byte[fis.available()];
            fis.read(output, 0, fis.available());
            fis.close();
        } catch (Exception ex) {
            LOG.error((Object) ("There was an error loading the key file (" + filename + "): "), (Throwable) ex);
        }
        return output;
    }

    @Override
    public Node manageAccountStatementReferences(String isoCode, String account_no, String cur_code, String statement_type, String customerReference, List banks) throws SAXException {
        try {
            Document result = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = result.createElement("references");
            result.appendChild(root);
            String[] infos = this.retrieveAccountRelatedInfoFromAccountNo(account_no, isoCode, customerReference, banks);
            String main_bank_abbv_name = infos[0];
            String main_bank_name = infos[1];
            String customer_id = infos[2];
            String account_id = infos[3];
            this.appendSingleNode(result, root, "main_bank_abbv_name", main_bank_abbv_name);
            this.appendSingleNode(result, root, "main_bank_name", main_bank_name);
            this.appendSingleNode(result, root, "customer_id", customer_id);
            this.appendSingleNode(result, root, "account_id", account_id);
            this.appendSingleNode(result, root, "bo_cust_number", customerReference);
            if (account_id != null && customer_id != null) {
                this.retrieveAccountStatement(result, new Integer(account_id), new Integer(customer_id));
            }
            return result;
        } catch (Exception e) {
            LOG.error((Object) "References Manager", (Throwable) e);
            throw new SAXException(e);
        }
    }

    @Override
    public Node manageAccountStatementReferences(String isoCode, String account_no, String cur_code, String statement_type, String customerReference, List<String> banks, String bo_type, String idx) throws GTPException, SAXException {
        String def_cust_ref = this.getDefCustRef(customerReference, bo_type, banks);
        return this.manageAccountStatementReferences(isoCode, account_no, cur_code, statement_type, def_cust_ref, null, idx);
    }

    @Override
    public Node manageAccountStatementReferences(String isoCode, String account_no, String cur_code, String statement_type, String customerReference, String product_code, String idx) throws SAXException, GTPException {
        try {
            Document result = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = result.createElement("references");
            result.appendChild(root);
            String[] infos = this.retrieveAccountRelatedInfoFromAccountNo(account_no, isoCode, customerReference, null);
            String main_bank_abbv_name = infos[0];
            String main_bank_name = infos[1];
            String customer_id = infos[2];
            String account_id = infos[3];
            this.appendSingleNode(result, root, "main_bank_abbv_name", main_bank_abbv_name);
            this.appendSingleNode(result, root, "main_bank_name", main_bank_name);
            this.appendSingleNode(result, root, "customer_id", customer_id);
            this.appendSingleNode(result, root, "account_id", account_id);
            this.appendSingleNode(result, root, "bo_cust_number", customerReference);
            if (account_id != null && customer_id != null && StringUtils.isNotEmpty((String) idx)) {
                this.retrieveAccountStatementBasedOnIDX(result, new Integer(account_id), new Integer(customer_id), new Integer(idx));
            }
            return result;
        } catch (Exception e) {
            LOG.error((Object) "References Manager", (Throwable) e);
            throw new SAXException(e);
        }
    }

    @Override
    public Node manageAccountStatementReferences(String isoCode, String account_no, String cur_code, String statement_type, String customerReference, String product_code, String sub_product_code, List<String> banks) throws SAXException, GTPException {
        String default_customer_bank_reference = this.getDefCustRef(customerReference, banks, product_code, sub_product_code);
        return this.manageAccountStatementReferences(isoCode, account_no, cur_code, statement_type, default_customer_bank_reference, banks);
    }

    @Override
    public Node manageBankBaseCurrency(List banks) throws SAXException {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet rset = null;
        try {
            connection = PoolBrokerService.getConnection();
            Document result = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = result.createElement("companies");
            result.appendChild(root);
            StringBuilder query = new StringBuilder();
            query.append("select B.BASE_CUR_CODE,B.ABBV_NAME FROM GTP_COMPANY B WHERE  B.ABBV_NAME IN ");
            boolean isBanksParamNull = false;
            if (banks == null || banks.isEmpty()) {
                isBanksParamNull = true;
                query.append(" (? ) ");
            } else {
                String inPlaceHolders = Utils.preparePlaceHolders(banks.size());
                query.append(" (" + inPlaceHolders + ") ");
            }
            statement = connection.prepareStatement(query.toString());
            if (isBanksParamNull) {
                statement.setString(1, "global");
            } else {
                Iterator iterator = banks.iterator();
                int i = 1;
                while (iterator.hasNext()) {
                    statement.setString(i++, (String) iterator.next());
                }
            }
            rset = statement.executeQuery();
            while (rset.next()) {
                String base_curCode = rset.getString(1);
                String bankAbbvName = rset.getString(2);
                Element company_node = result.createElement("company");
                Element company_name_node = result.createElement("company_name");
                if (isBanksParamNull) {
                    company_name_node.setTextContent("*");
                } else {
                    company_name_node.setTextContent(bankAbbvName);
                }
                Element base_cur_node = result.createElement("base_cur_code");
                base_cur_node.setTextContent(base_curCode);
                company_node.appendChild(company_name_node);
                company_node.appendChild(base_cur_node);
                root.appendChild(company_node);
            }
            Document document = result;
            return document;
        } catch (Exception e) {
            LOG.error((Object) "References Manager", (Throwable) e);
            throw new SAXException(e);
        } finally {
            Utils.closeResources(connection, statement, rset);
        }
    }

    @Override
    public Node manageBankBaseCurrency(String bankValue) throws SAXException {
        ArrayList<String> bankList = null;
        if (!StringUtils.isEmpty((String) bankValue)) {
            bankList = new ArrayList<String>();
            bankList.add(bankValue);
        }
        return this.manageBankBaseCurrency(bankList);
    }

    @Override
    public Node manageBulkChildTnxReferences(String productCode, String childTnxReferences) throws SAXException {
        try {
            Document result = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = result.createElement("references");
            result.appendChild(root);
            Element childReferences = result.createElement("child_references");
            root.appendChild(childReferences);
            StringTokenizer childTnxTokens = new StringTokenizer(childTnxReferences, ",");
            while (childTnxTokens.hasMoreTokens()) {
                Element childReferenceNode = result.createElement("child_reference");
                Element refIdNode = result.createElement("ref_id");
                childReferenceNode.appendChild(refIdNode);
                Element tnxIdNode = result.createElement("tnx_id");
                childReferenceNode.appendChild(tnxIdNode);
                String childRefTnx = childTnxTokens.nextToken();
                String refId = "";
                String tnxId = "";
                if (childRefTnx.contains("-")) {
                    String[] childRefTnxArray = childRefTnx.split("-");
                    if ("000".equals(childRefTnxArray[0]) && "000".equals(childRefTnxArray[1])) {
                        refId = ReferenceIdGenerator.generate(productCode);
                        tnxId = TransactionIdGenerator.generate();
                    } else {
                        refId = childRefTnxArray[0];
                        tnxId = childRefTnxArray[1];
                    }
                }
                refIdNode.setTextContent(refId);
                tnxIdNode.setTextContent(tnxId);
                childReferences.appendChild(childReferenceNode);
            }
            LOG.info((Object) "Bulk Child References fetched from the account number: ");
            return result;
        } catch (Exception e) {
            LOG.error((Object) "References Manager", (Throwable) e);
            throw new SAXException(e);
        }
    }

    @Override
    public Node manageCompanyReferences(String customer_bank_reference, List banks) throws SAXException {
        String company_name = null;
        String company_id = null;
        String owner_id = null;
        String user_id = null;
        String main_bank_name = null;
        String main_bank_abbv_name = null;
        String addressLine1 = null;
        String addressLine2 = null;
        String dom = null;
        String isoCode = null;
        String country = null;
        String status = null;
        try {
            Document result = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = result.createElement("references");
            result.appendChild(root);
            if (customer_bank_reference != null && customer_bank_reference.length() == 0) {
                customer_bank_reference = null;
            }
            String[] companyInfos = null;
            if (customer_bank_reference != null) {
                companyInfos = this.retrieveCompanyInfosFrom(customer_bank_reference, banks);
            }
            owner_id = this.retrieveCustomerOwnerId(customer_bank_reference, banks);
            if (companyInfos != null) {
                company_name = companyInfos[0];
                company_id = companyInfos[4];
                main_bank_name = companyInfos[2];
                addressLine1 = companyInfos[5];
                addressLine2 = companyInfos[6];
                dom = companyInfos[7];
                isoCode = companyInfos[8];
                country = companyInfos[9];
                status = companyInfos[10];
                user_id = this.retrieveUserIdFromLoginId(customer_bank_reference, company_name);
                if (main_bank_name != null) {
                    Element main_bank_name_node = result.createElement("main_bank_name");
                    root.appendChild(main_bank_name_node);
                    main_bank_name_node.setTextContent(main_bank_name);
                }
                if ((main_bank_abbv_name = companyInfos[1]) == null) {
                    throw new SAXException((Exception) ((Object) new GTPException("GTPBOReferenceService class, manageCompanyReferences method, Unable to find a bank from the customer bank reference: " + customer_bank_reference)));
                }
                String match_company_id = this.retrieveCompanyIdFrom(company_name);
                if (match_company_id != null && !match_company_id.equals(company_id) && company_id != null && !"".equals(company_id)) {
                    LOG.warn((Object) ("N30121 - The input COMPANY_ID (value : " + company_id + ") does not match the COMPANY_ID (value : " + match_company_id + ") retrieved from the database from the COMPANY_NAME : " + company_name));
                }
                company_id = match_company_id;
            } else {
                company_name = customer_bank_reference;
            }
            boolean customerExists = this.isCustomerExists(customer_bank_reference, banks);
            Element company_id_node = result.createElement("company_id");
            root.appendChild(company_id_node);
            company_id_node.setTextContent(company_id);
            Element company_name_node = result.createElement("company_name");
            root.appendChild(company_name_node);
            company_name_node.setTextContent(company_name);
            Element main_bank_abbv_name_node = result.createElement("main_bank_abbv_name");
            root.appendChild(main_bank_abbv_name_node);
            main_bank_abbv_name_node.setTextContent(main_bank_abbv_name);
            Element company_owner_id_node = result.createElement("owner_id");
            root.appendChild(company_owner_id_node);
            company_owner_id_node.setTextContent(owner_id);
            Element company_user_id_node = result.createElement("user_id");
            root.appendChild(company_user_id_node);
            company_user_id_node.setTextContent(user_id);
            Element company_exists_node = result.createElement("company_exists");
            root.appendChild(company_exists_node);
            company_exists_node.setTextContent(String.valueOf(customerExists));
            Element address_line_1_node = result.createElement("address_line_1");
            root.appendChild(address_line_1_node);
            address_line_1_node.setTextContent(addressLine1);
            Element address_line_2_node = result.createElement("address_line_2");
            root.appendChild(address_line_2_node);
            address_line_2_node.setTextContent(addressLine2);
            Element dom_node = result.createElement("dom");
            root.appendChild(dom_node);
            dom_node.setTextContent(dom);
            Element iso_code_node = result.createElement("iso_code");
            root.appendChild(iso_code_node);
            iso_code_node.setTextContent(isoCode);
            Element routing_bic_node = result.createElement("routing_bic");
            root.appendChild(routing_bic_node);
            routing_bic_node.setTextContent(isoCode);
            Element country_node = result.createElement("country");
            root.appendChild(country_node);
            country_node.setTextContent(country);
            Element actv_flag_node = result.createElement("actv_flag");
            root.appendChild(actv_flag_node);
            actv_flag_node.setTextContent(status);
            return result;
        } catch (Exception e) {
            LOG.error((Object) "References Manager", (Throwable) e);
            throw new SAXException(e);
        }
    }

    @Override
    public Node manageCompanyReferences(String customer_bank_reference, List banks, String product_code, String sub_product_code) throws SAXException, GTPException {
        String default_customer_bank_reference = this.getDefCustRef(customer_bank_reference, banks, product_code, sub_product_code);
        return this.manageCompanyReferences(default_customer_bank_reference, banks);
    }

    @Override
    public Node manageLoanReferences(String product_code, String sub_product_code, String ref_id, String tnx_id, String bo_ref_id, String bo_tnx_id, String cust_ref_id, String company_id, String company_name, String customer_bank_reference, String main_bank_abbv_name, String main_bank_role_code, String facility, String pricingOption, String repricingFreq, String lnAmt, String riskType) throws SAXException {
        try {
            String entityRetrievedFromDB;
            String match_company_id;
            Document result = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = result.createElement("references");
            result.appendChild(root);
            String entity = null;
            String subProdCode = sub_product_code;
            if (StringUtils.isEmpty((String) subProdCode)) {
                subProdCode = this.retrieveSubProdCodeFromBoRefId(bo_ref_id, product_code, main_bank_abbv_name, main_bank_role_code);
            }
            String[] companyInfos = null;
            String mainBankAbbvName = main_bank_abbv_name;
            String companyName = company_name;
            String companyId = company_id;
            String customerBankReference = customer_bank_reference;
            if (StringUtils.isNotBlank((String) customerBankReference)) {
                String string = mainBankAbbvName = StringUtils.isBlank((String) main_bank_abbv_name) ? null : main_bank_abbv_name;
                companyInfos = DefaultResourceProvider.REFERENCE_BACK_OFFICE_ENABLED ? this.retrieveCompanyInfosFromBOType(this.getBackOfficeType(product_code, subProdCode), customerBankReference) : this.retrieveCompanyInfosFrom(customerBankReference);
            } else if (StringUtils.isEmpty((String) customerBankReference) && ref_id != null && !"".equals(ref_id) && tnx_id != null && !"".equals(tnx_id)) {
                companyInfos = this.retrieveCompanyInfosFrom(ref_id, tnx_id, product_code, null);
            }
            if (companyInfos != null) {
                if (StringUtils.isNotEmpty((String) companyInfos[0])) {
                    companyName = companyInfos[0];
                }
                if (StringUtils.isNotEmpty((String) companyInfos[4])) {
                    companyId = companyInfos[4];
                }
                String main_bank_name = companyInfos[2];
                customerBankReference = companyInfos[5];
                if (main_bank_name != null) {
                    Element main_bank_name_node = result.createElement("main_bank_name");
                    root.appendChild(main_bank_name_node);
                    main_bank_name_node.setTextContent(main_bank_name);
                }
                entity = companyInfos[3];
                if (StringUtils.isEmpty((String) mainBankAbbvName) && StringUtils.isEmpty((String) (mainBankAbbvName = companyInfos[1]))) {
                    throw new SAXException("GTPBOReferenceService class, manageReferences method, Unable to find a bank from the customer bank reference: " + customerBankReference);
                }
            }
            if ((match_company_id = this.retrieveCompanyIdFrom(companyName)) != null && !match_company_id.equals(companyId) && companyId != null && !"".equals(companyId)) {
                LOG.warn((Object) ("N30121 - The input COMPANY_ID (value : " + companyId + ") does not match the COMPANY_ID (value : " + match_company_id + ") retrieved from the database from the COMPANY_NAME : " + companyName));
            }
            companyId = match_company_id;
            String refId = ref_id;
            String tnxId = tnx_id;
            String boTnxId = bo_tnx_id;
            refId = StringUtils.isBlank((String) refId) ? null : refId;
            tnxId = StringUtils.isBlank((String) tnxId) ? null : tnxId;
            boTnxId = StringUtils.isBlank((String) boTnxId) ? null : boTnxId;
            String originalTnxId = tnxId;
            if (refId != null && tnxId != null) {
                this.checkReferenceIdAndTransactionId(refId, tnxId, product_code);
            } else if (refId != null && tnxId == null) {
                this.checkReferenceId(refId, product_code);
            } else if (tnxId != null) {
                this.checkTransactionId(tnxId, product_code);
            }
            if (refId == null && boTnxId != null) {
                refId = GTPBOReferenceService.retrieveLNRefidFromBoTnxid(boTnxId, companyName, product_code, facility, pricingOption, repricingFreq, lnAmt, riskType);
            }
            if (tnxId == null && boTnxId != null) {
                tnxId = this.retrieveLnTnxIdFromBoTnxId(refId, boTnxId, product_code, subProdCode, mainBankAbbvName, main_bank_role_code, facility, pricingOption, repricingFreq, lnAmt, riskType);
            }
            if ((entityRetrievedFromDB = this.retrieveEntityFromProduct(product_code, refId)) != null) {
                entity = "**".equals(entityRetrievedFromDB) ? null : entityRetrievedFromDB;
            }
            if (entity != null) {
                Element entity_node = result.createElement("entity");
                root.appendChild(entity_node);
                entity_node.setTextContent(entity);
            }
            Element ref_id_node = result.createElement("ref_id");
            root.appendChild(ref_id_node);
            ref_id_node.setTextContent(refId);
            Element tnx_id_node = result.createElement("tnx_id");
            root.appendChild(tnx_id_node);
            tnx_id_node.setTextContent(tnxId);
            Element sub_prod_code_node = result.createElement("sub_prod_code");
            root.appendChild(sub_prod_code_node);
            sub_prod_code_node.setTextContent(subProdCode);
            Element company_id_node = result.createElement("company_id");
            root.appendChild(company_id_node);
            company_id_node.setTextContent(companyId);
            Element company_name_node = result.createElement("company_name");
            root.appendChild(company_name_node);
            company_name_node.setTextContent(companyName);
            Element main_bank_abbv_name_node = result.createElement("main_bank_abbv_name");
            root.appendChild(main_bank_abbv_name_node);
            main_bank_abbv_name_node.setTextContent(mainBankAbbvName);
            Element customer_bank_reference_node = result.createElement("customer_bank_reference");
            root.appendChild(customer_bank_reference_node);
            customer_bank_reference_node.setTextContent(customerBankReference);
            if (this.retrieve_transaction) {
                List<BulkObject> counterpartyList = this.getCounterpartyList(refId, originalTnxId);
                Iterator<BulkObject> iterator = counterpartyList.iterator();
                if (iterator.hasNext()) {
                    BulkObject counterparty = iterator.next();
                    this.appendNode("counterparty", counterparty, root, result);
                }
                Element charges_node = result.createElement("charges");
                root.appendChild(charges_node);
                List<BulkObject> productChargesList = this.getProductChargesList(refId, originalTnxId);
                for (BulkObject charge : productChargesList) {
                    this.appendNode("charge", charge, charges_node, result);
                }
                Element attachments_node = result.createElement("attachments");
                root.appendChild(attachments_node);
                List<Attachment> attachmentsList = this.getAttachmentsFrom(refId, originalTnxId);
                for (Attachment attachment : attachmentsList) {
                    this.appendNode("attachment", attachment, attachments_node, result);
                }
                Element cross_references_node = result.createElement("cross_references");
                root.appendChild(cross_references_node);
                List<BulkObject> crossReferencesList = this.getCrossReference(refId, originalTnxId);
                for (BulkObject crossReference : crossReferencesList) {
                    this.appendNode("cross_reference", crossReference, cross_references_node, result);
                }
            }
            return result;
        } catch (Exception e) {
            LOG.error((Object) "References Manager", (Throwable) e);
            throw new SAXException("Error while retrieving loan references : " + e.getMessage(), e);
        }
    }

    public Node manageReferences(String masterRef, String tnxCurrency, String tnxAmount, String paymentStatus, String expiryDate) throws SAXException {
        try {
            Document result = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = result.createElement("references");
            result.appendChild(root);
            Element tnx_id_node = result.createElement("MasterRef");
            root.appendChild(tnx_id_node);
            tnx_id_node.setTextContent(masterRef);
            Element company_id_node = result.createElement("TnxCurrency");
            root.appendChild(company_id_node);
            company_id_node.setTextContent(tnxCurrency);
            Element company_name_node = result.createElement("TnxAmount");
            root.appendChild(company_name_node);
            company_name_node.setTextContent(tnxAmount);
            Element main_bank_abbv_name_node = result.createElement("PaymentStatus");
            root.appendChild(main_bank_abbv_name_node);
            main_bank_abbv_name_node.setTextContent(paymentStatus);
            Element customer_bank_reference_node = result.createElement("ExpiryDate");
            root.appendChild(customer_bank_reference_node);
            customer_bank_reference_node.setTextContent(expiryDate);
            return result;
        } catch (Exception e) {
            LOG.error((Object) "References Manager", (Throwable) e);
            throw new SAXException(e);
        }
    }

    @Override
    public Node manageReferences(String product_code, String ref_id, String tnx_id, String bo_ref_id, String cust_ref_id, String company_id, String company_name, String customer_bank_reference, String main_bank_abbv_name, String main_bank_role_code) throws SAXException {
        return this.manageReferences(product_code, ref_id, tnx_id, bo_ref_id, null, cust_ref_id, company_id, company_name, customer_bank_reference, main_bank_abbv_name, main_bank_role_code);
    }

    @Override
    public Node manageReferences(String product_code, String ref_id, String tnx_id, String bo_ref_id, String bo_tnx_id, String cust_ref_id, String company_id, String company_name, String customer_bank_reference, String main_bank_abbv_name, String main_bank_role_code) throws SAXException {
        return this.manageReferences(product_code, null, ref_id, tnx_id, bo_ref_id, bo_tnx_id, cust_ref_id, company_id, company_name, customer_bank_reference, main_bank_abbv_name, main_bank_role_code);
    }

    @Override
    public Node manageReferences(String product_code, String sub_product_code, String ref_id, String tnx_id, String bo_ref_id, String bo_tnx_id, String cust_ref_id, String company_id, String company_name, String customer_bank_reference, String main_bank_abbv_name, String main_bank_role_code) throws SAXException {
        try {
            String entityRetrievedFromDB;
            String match_company_id;
            Document result = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = result.createElement("references");
            result.appendChild(root);
            String entity = null;
            if ("BADiscounting".equals(cust_ref_id)) {
                Element tnx_id_node = result.createElement("MasterRef");
                root.appendChild(tnx_id_node);
                tnx_id_node.setTextContent(product_code);
                Element company_id_node = result.createElement("TnxCurrency");
                root.appendChild(company_id_node);
                company_id_node.setTextContent(ref_id);
                Element company_name_node = result.createElement("TnxAmount");
                root.appendChild(company_name_node);
                company_name_node.setTextContent(tnx_id);
                Element main_bank_abbv_name_node = result.createElement("PaymentStatus");
                root.appendChild(main_bank_abbv_name_node);
                main_bank_abbv_name_node.setTextContent(bo_ref_id);
                Element customer_bank_reference_node = result.createElement("ExpiryDate");
                root.appendChild(customer_bank_reference_node);
                customer_bank_reference_node.setTextContent(bo_tnx_id);
                return result;
            }
            String subProdCode = sub_product_code;
            if (StringUtils.isEmpty((String) subProdCode)) {
                subProdCode = this.retrieveSubProdCodeFromBoRefId(bo_ref_id, product_code, main_bank_abbv_name, main_bank_role_code);
            }
            String[] companyInfos = null;
            if (customer_bank_reference != null && customer_bank_reference.length() != 0) {
                String string = main_bank_abbv_name = main_bank_abbv_name == null || main_bank_abbv_name.length() == 0 ? null : main_bank_abbv_name;
                companyInfos = DefaultResourceProvider.REFERENCE_BACK_OFFICE_ENABLED ? this.retrieveCompanyInfosFromBOType(this.getBackOfficeType(product_code, subProdCode), customer_bank_reference) : this.retrieveCompanyInfosFrom(customer_bank_reference);
            } else if (StringUtils.isEmpty((String) customer_bank_reference) && ref_id != null && !"".equals(ref_id) && tnx_id != null && !"".equals(tnx_id)) {
                companyInfos = this.retrieveCompanyInfosFrom(ref_id, tnx_id, product_code, null);
            }
            if (companyInfos != null) {
                if (StringUtils.isNotEmpty((String) companyInfos[0])) {
                    company_name = companyInfos[0];
                }
                if (StringUtils.isNotEmpty((String) companyInfos[4])) {
                    company_id = companyInfos[4];
                }
                String main_bank_name = companyInfos[2];
                customer_bank_reference = companyInfos[5];
                if (main_bank_name != null) {
                    Element main_bank_name_node = result.createElement("main_bank_name");
                    root.appendChild(main_bank_name_node);
                    main_bank_name_node.setTextContent(main_bank_name);
                }
                entity = companyInfos[3];
                if (StringUtils.isEmpty((String) main_bank_abbv_name) && StringUtils.isEmpty((String) (main_bank_abbv_name = companyInfos[1]))) {
                    throw new SAXException("GTPBOReferenceService class, manageReferences method, Unable to find a bank from the customer bank reference: " + customer_bank_reference);
                }
            }
            if ((match_company_id = this.retrieveCompanyIdFrom(company_name)) != null && !match_company_id.equals(company_id) && company_id != null && !"".equals(company_id)) {
                LOG.warn((Object) ("N30121 - The input COMPANY_ID (value : " + company_id + ") does not match the COMPANY_ID (value : " + match_company_id + ") retrieved from the database from the COMPANY_NAME : " + company_name));
            }
            company_id = match_company_id;
            ref_id = ref_id == null || ref_id.length() == 0 ? null : ref_id;
            bo_ref_id = bo_ref_id == null || bo_ref_id.length() == 0 ? null : bo_ref_id;
            cust_ref_id = cust_ref_id == null || cust_ref_id.length() == 0 ? null : cust_ref_id;
            tnx_id = tnx_id == null || tnx_id.length() == 0 ? null : tnx_id;
            bo_tnx_id = bo_tnx_id == null || bo_tnx_id.length() == 0 ? null : bo_tnx_id;
            String originalTnxId = tnx_id;
            if (ref_id != null && tnx_id != null) {
                this.checkReferenceIdAndTransactionId(ref_id, tnx_id, product_code);
            } else if (ref_id != null && tnx_id == null) {
                this.checkReferenceId(ref_id, product_code);
            } else if (tnx_id != null) {
                this.checkTransactionId(tnx_id, product_code);
            }
            if (ref_id == null && bo_ref_id != null && main_bank_abbv_name != null) {
                ref_id = this.retrieveRefIdFromBoRefId(bo_ref_id, product_code, subProdCode, main_bank_abbv_name, main_bank_role_code);
            }
            if (ref_id == null && bo_tnx_id != null) {
                ref_id = GTPBOReferenceService.retrieveRefidFromBoTnxid(bo_tnx_id, company_name, product_code);
            }
            if (ref_id == null && bo_ref_id == null && cust_ref_id != null) {
                ref_id = this.retrieveRefidFromCustRefId(cust_ref_id, company_name, product_code, subProdCode);
            }
            if (ref_id == null) {
                ref_id = ReferenceIdGenerator.generate(product_code);
                tnx_id = TransactionIdGenerator.generate();
            }
            if (tnx_id == null) {
                if (bo_tnx_id != null) {
                    tnx_id = this.retrieveTnxIdFromBoTnxId(ref_id, bo_tnx_id, product_code, subProdCode, main_bank_abbv_name, main_bank_role_code);
                }
                if (tnx_id == null) {
                    tnx_id = TransactionIdGenerator.generate();
                }
            }
            if ((entityRetrievedFromDB = this.retrieveEntityFromProduct(product_code, ref_id)) != null) {
                entity = "**".equals(entityRetrievedFromDB) ? null : entityRetrievedFromDB;
            }
            if (entity != null) {
                Element entity_node = result.createElement("entity");
                root.appendChild(entity_node);
                entity_node.setTextContent(entity);
            }
            Element ref_id_node = result.createElement("ref_id");
            root.appendChild(ref_id_node);
            ref_id_node.setTextContent(ref_id);
            Element tnx_id_node = result.createElement("tnx_id");
            root.appendChild(tnx_id_node);
            tnx_id_node.setTextContent(tnx_id);
            Element sub_prod_code_node = result.createElement("sub_prod_code");
            root.appendChild(sub_prod_code_node);
            sub_prod_code_node.setTextContent(subProdCode);
            Element company_id_node = result.createElement("company_id");
            root.appendChild(company_id_node);
            company_id_node.setTextContent(company_id);
            Element company_name_node = result.createElement("company_name");
            root.appendChild(company_name_node);
            company_name_node.setTextContent(company_name);
            Element main_bank_abbv_name_node = result.createElement("main_bank_abbv_name");
            root.appendChild(main_bank_abbv_name_node);
            main_bank_abbv_name_node.setTextContent(main_bank_abbv_name);
            Element customer_bank_reference_node = result.createElement("customer_bank_reference");
            root.appendChild(customer_bank_reference_node);
            customer_bank_reference_node.setTextContent(customer_bank_reference);
            if (this.retrieve_transaction) {
                List<BulkObject> counterpartyList = this.getCounterpartyList(ref_id, originalTnxId);
                Iterator<BulkObject> iterator = counterpartyList.iterator();
                if (iterator.hasNext()) {
                    BulkObject counterparty = iterator.next();
                    this.appendNode("counterparty", counterparty, root, result);
                }
                Element charges_node = result.createElement("charges");
                root.appendChild(charges_node);
                List<BulkObject> productChargesList = this.getProductChargesList(ref_id, originalTnxId);
                for (BulkObject charge : productChargesList) {
                    this.appendNode("charge", charge, charges_node, result);
                }
                Element attachments_node = result.createElement("attachments");
                root.appendChild(attachments_node);
                List<Attachment> attachmentsList = this.getAttachmentsFrom(ref_id, originalTnxId);
                for (Attachment attachment : attachmentsList) {
                    this.appendNode("attachment", attachment, attachments_node, result);
                }
                Element cross_references_node = result.createElement("cross_references");
                root.appendChild(cross_references_node);
                List<BulkObject> crossReferencesList = this.getCrossReference(ref_id, originalTnxId);
                for (BulkObject crossReference : crossReferencesList) {
                    this.appendNode("cross_reference", crossReference, cross_references_node, result);
                }
            }
            return result;
        } catch (Exception e) {
            LOG.error((Object) "References Manager", (Throwable) e);
            throw new SAXException(e);
        }
    }

    public Node manageReferences(String product_code, String sub_product_code, String ref_id, String tnx_id, String bo_ref_id, String bo_tnx_id, String cust_ref_id, String company_id, String company_name, String customer_bank_reference, String main_bank_abbv_name, String main_bank_role_code, boolean flag) throws SAXException {
        return this.manageReferences(product_code, sub_product_code, ref_id, tnx_id, bo_ref_id, bo_tnx_id, cust_ref_id, company_id, company_name, customer_bank_reference, main_bank_abbv_name, main_bank_role_code);
    }

    @Override
    public Node manageReferences(String product_code, String sub_product_code, String ref_id, String tnx_id, String bo_ref_id, String bo_tnx_id, String cust_ref_id, String company_id, String company_name, String customer_bank_reference, String main_bank_abbv_name, String main_bank_role_code, String incomingBulkMode) throws SAXException {
        if (incomingBulkMode != null && !"".equals(incomingBulkMode)) {
            return null;
        }
        return this.manageReferences(product_code, sub_product_code, ref_id, tnx_id, bo_ref_id, bo_tnx_id, cust_ref_id, company_id, company_name, customer_bank_reference, main_bank_abbv_name, main_bank_role_code);
    }

    @Override
    public String retrieveAccountIdFromAccountNumber(String account_no) throws Exception {
        if ("".equals(account_no) || account_no == null) {
            throw new Exception("Error: Could not retrieve account_id in GTPBOReferenceService. Cause: account number is empty or null.");
        }
        Connection connection = PoolBrokerService.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("select P.ACCOUNT_ID from ").append("GTP_ACCOUNT").append(" P where P.ACCOUNT_NO = ? ");
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, account_no);
            resultSet = statement.executeQuery();
            String result = null;
            while (resultSet.next()) {
                result = resultSet.getString(1);
                if (!resultSet.next()) {
                    continue;
                }
                throw new Exception("N30123 - At least two accounts exist for the account: " + account_no);
            }
            if (result != null) {
                LOG.info((Object) ("N30123 - An existing account id: " + result + " has been retrieved from the account number: " + account_no));
            } else {
                LOG.error((Object) ("Error: No account find for the account: " + account_no));
            }
            String string = result;
            return string;
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    @Override
    public String retrieveAccountIdFromAccountNumber(String account_no, String customer_ref) throws Exception {
        if ("".equals(account_no) || account_no == null) {
            throw new Exception("Error: Could not retrieve account_id in GTPBOReferencesService. Cause: account number is empty or null.");
        }
        if ("".equals(customer_ref) || customer_ref == null) {
            throw new Exception("Error: Could not retrieve account_id in GTPBOReferencesService. Cause: customer reference is empty or null.");
        }
        String[] company_info = this.retrieveCompanyInfosFrom(customer_ref);
        String company_ref = company_info[5];
        String company_id = company_info[4];
        Connection connection = PoolBrokerService.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            ArrayList<String> bindvalues = new ArrayList<String>();
            StringBuilder query = new StringBuilder();
            query.append("select a.ACCOUNT_ID from ").append("GTP_ACCOUNT").append(" a, ").append("GTP_CUSTOMER_ACCOUNT").append(" ca ").append(" where a.ACCOUNT_NO = ? and a.BO_CUST_NUMBER = ? and ca.CUSTOMER_ID = ? and a.BO_CUST_NUMBER = ca.REFERENCE and a.ACCOUNT_ID = ca.ACCOUNT_ID ");
            bindvalues.add(account_no);
            bindvalues.add(company_ref);
            bindvalues.add(company_id);
            statement = connection.prepareStatement(query.toString());
            Utils.bindValueToPreparedStatement(statement, bindvalues);
            resultSet = statement.executeQuery();
            String result = null;
            while (resultSet.next()) {
                result = resultSet.getString(1);
                if (!resultSet.next()) {
                    continue;
                }
                throw new Exception("N30123 - At least two accounts exist for the account: " + account_no);
            }
            if (result != null) {
                LOG.info((Object) ("N30123 - An existing account id: " + result + " has been retrieved from the account number: " + account_no));
            } else {
                LOG.error((Object) ("Error: No account find for the account: " + account_no + " and for the customer: " + customer_ref));
            }
            String string = result;
            return string;
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    @Override
    public String retrieveAccountIdFromISOCode(String account_no, String isoCode) throws Exception {
        if ("".equals(account_no) || account_no == null) {
            throw new Exception("Error: Could not retrieve account_id in GTPBOReferenceService. Cause: account number is empty or null.");
        }
        if ("".equals(isoCode) || isoCode == null) {
            throw new Exception("Error: Could not retrieve account_id in GTPBOReferenceService. Cause: iso code is empty or null.");
        }
        String[] company_info = this.retrieveCompanyInfosFromISOCode(isoCode, account_no);
        String company_ref = company_info[4];
        String entity = company_info[3];
        Connection connection = PoolBrokerService.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("select P.ACCOUNT_ID from ").append("GTP_ACCOUNT").append(" P where P.ACCOUNT_NO = ? and P.COMPANY_ID = ? ");
            if (entity != null) {
                query.append("and P.ENTITY = ? ");
            } else {
                query.append("and P.ENTITY is null ");
            }
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, account_no);
            statement.setBigDecimal(2, new BigDecimal(company_ref));
            if (entity != null) {
                statement.setString(3, entity);
            }
            resultSet = statement.executeQuery();
            String result = null;
            while (resultSet.next()) {
                result = resultSet.getString(1);
                if (!resultSet.next()) {
                    continue;
                }
                throw new Exception("N30123 - At least two accounts exist for the account: " + account_no);
            }
            if (result != null) {
                LOG.info((Object) ("N30123 - An existing account id: " + result + " has been retrieved from the account number: " + account_no));
            } else {
                LOG.error((Object) ("Error: No account find for the account: " + account_no + " and for the customer: " + isoCode));
            }
            String string = result;
            return string;
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    private String[] retrieveAccountRelatedInfoFromAccountNo(String account_no, String isoCode, String customerReference, List banks) throws Exception {
        
        System.out.println("<< account_no >>" + account_no);
        System.out.println("<< isoCode >>" + isoCode);
//        if (customerReference != null && customerReference.length()>0) {
//            System.out.println("<< customerReference >>" + customerReference);
//        }
//
//        if (!banks.isEmpty()) {
//            for (int x = 0; x < banks.size(); x++) {
//                System.out.println("<< banks >>" + banks.get(x));
//            }
//        }
        

        String[] result;
        result = new String[4];
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rset = null;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                //Integer customerId = StringUtils.isEmpty((String) customerReference) ? CustomerAccountSet.getCustomerIdFromAccountNo(account_no, isoCode) : Utils.getCustomerIdFromCustomerReference(customerReference, banks);
                System.out.println("<<<< A>>>>>>");
                //Integer customerId = CustomerAccountSet.getCustomerIdFromAccountNo(account_no, isoCode);
                Integer customerId = StringUtils.isEmpty((String) customerReference) ? CustomerAccountSet.getCustomerIdFromAccountNo(account_no, isoCode) : Utils.getCustomerIdFromCustomerReference(customerReference, banks);
                System.out.println("<<<< B>>>>>>");
                System.out.println("<<<< customerId >>>>>>"+customerId);
                String mainBankAbbvName = null;
                String mainBankName = null;
                String accountId = null;
                StringBuilder query = new StringBuilder();
                query = query.append("SELECT a.bank_abbv_name, a.bank_name, a.account_id FROM GTP_CUSTOMER_ACCOUNT ca INNER join GTP_ACCOUNT a ON ca.account_id = a.account_id and ca.reference = a.bo_cust_number WHERE ca.customer_id = ? AND a.account_no = ?");
                if (isoCode != null && !"".equals(isoCode)) {
                    query.append(" and a.iso_code = ?");
                }
                System.out.println("<<<< C >>>>>>");
                statement = connection.prepareStatement(query.toString());
                System.out.println("<< query >>"+ query.toString());
                statement.setInt(1, customerId);
                System.out.println("<<<< D >>>>>>");
                statement.setString(2, account_no);
                System.out.println("<<<< E >>>>>>");
                if (isoCode != null && !"".equals(isoCode)) {
                    statement.setString(3, isoCode);
                    System.out.println("<<<< F >>>>>>");
                }
                System.out.println("<<<< G >>>>>>");
                rset = statement.executeQuery();
                System.out.println("<<<< H >>>>>>");
                while (rset.next()) {
                    mainBankAbbvName = rset.getString(1);
                    mainBankName = rset.getString(2);
                    accountId = rset.getString(3);
                    System.out.println("<<<< I >>>>>>");
                    if (!rset.next()) {
                        //continue;
                        break;
                    }
                    System.out.println("<<<< J >>>>>>");
                    
                }
                LOG.info((Object) ("References fetched from the account number: " + account_no + ",  main bank abbv_name/name: " + mainBankAbbvName + '/' + mainBankName));
                if (StringUtils.isEmpty((String) mainBankAbbvName)) {
                    String[] compInfo = this.retrieveCompanyInfosFrom(customerReference);
                    mainBankAbbvName = compInfo[1];
                    mainBankName = compInfo[2];
                }
                result[0] = mainBankAbbvName;
                result[1] = mainBankName;
                if (customerId != null) {
                    result[2] = customerId.toString();
                }
                result[3] = accountId;
            } catch (SQLException e) {
                LOG.error((Object) ("Unable to retrieve account related details from account number: " + account_no), (Throwable) e);
                Utils.closeResources(connection, statement, rset);
            } catch (Exception e) {
                LOG.error((Object) ("Unable to retrieve account related details from account number: " + account_no), (Throwable) e);
                Utils.closeResources(connection, statement, rset);
            }
        } finally {
            Utils.closeResources(connection, statement, rset);
        }
        return result;
    }

    private void retrieveAccountStatement(Document result, Integer accountId, Integer customerId) throws Exception {
        Node root = result.getFirstChild();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rset = null;
        try {
            connection = PoolBrokerService.getConnection();
            statement = connection.prepareStatement("SELECT stmt.statement_id, bal.type, bal.balance_id, act.account_id, ca.customer_id FROM GTP_ACCOUNT act INNER JOIN GTP_CUSTOMER_ACCOUNT ca ON ca.account_id = act.account_id LEFT OUTER JOIN GTP_ACCOUNT_STATEMENT stmt ON act.account_id = stmt.account_id AND stmt.type = '02' LEFT OUTER JOIN GTP_ACCOUNT_BALANCE bal ON stmt.statement_id = bal.statement_id WHERE act.account_id = ? AND customer_id = ? ORDER BY bal.type ASC");
            statement.setInt(1, accountId);
            statement.setInt(2, customerId);
            rset = statement.executeQuery();
            while (rset.next()) {
                BigDecimal statementId = rset.getBigDecimal(1);
                String type = rset.getString(2);
                BigDecimal balanceId = rset.getBigDecimal(3);
                Element account_id_node = result.createElement("account_id");
                root.appendChild(account_id_node);
                account_id_node.setTextContent(accountId.toString());
                if (statementId == null) {
                    continue;
                }
                Element statement_id_node = result.createElement("statement_id");
                statement_id_node.setTextContent(statementId.toString());
                root.appendChild(statement_id_node);
                if (balanceId == null) {
                    continue;
                }
                Element balances_node = result.createElement("balances");
                Attr balance_Type_node = result.createAttribute("type");
                balance_Type_node.setNodeValue(type);
                Element balance_id_node = result.createElement("balance_id");
                balance_id_node.setAttributeNode(balance_Type_node);
                balances_node.appendChild(balance_id_node);
                balance_id_node.setTextContent(balanceId.toString());
                balance_Type_node = result.createAttribute("type");
                balance_Type_node.setNodeValue(type);
                balance_id_node = result.createElement("balance_id");
                balance_id_node.setAttributeNode(balance_Type_node);
                balances_node.appendChild(balance_id_node);
                balance_id_node.setTextContent(balanceId.toString());
                root.appendChild(balances_node);
            }
        } finally {
            Utils.closeResources(connection, statement, rset);
        }
    }

    private void retrieveAccountStatementBasedOnIDX(Document result, Integer accountId, Integer customerId, Integer idx) throws Exception {
        Node root = result.getFirstChild();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rset = null;
        try {
            connection = PoolBrokerService.getConnection();
            statement = connection.prepareStatement("SELECT stmt.statement_id, bal.type, bal.balance_id, act.account_id, ca.customer_id FROM GTP_ACCOUNT act INNER JOIN GTP_CUSTOMER_ACCOUNT ca ON ca.account_id = act.account_id LEFT OUTER JOIN GTP_ACCOUNT_STATEMENT stmt ON act.account_id = stmt.account_id AND stmt.type = '02' AND stmt.idx = ? LEFT OUTER JOIN GTP_ACCOUNT_BALANCE bal ON stmt.statement_id = bal.statement_id WHERE act.account_id = ? AND customer_id = ? ORDER BY bal.type ASC");
            statement.setInt(1, idx);
            statement.setInt(2, accountId);
            statement.setInt(3, customerId);
            rset = statement.executeQuery();
            while (rset.next()) {
                BigDecimal statementId = rset.getBigDecimal(1);
                String type = rset.getString(2);
                BigDecimal balanceId = rset.getBigDecimal(3);
                Element account_id_node = result.createElement("account_id");
                root.appendChild(account_id_node);
                account_id_node.setTextContent(accountId.toString());
                if (statementId == null) {
                    continue;
                }
                Element statement_id_node = result.createElement("statement_id");
                statement_id_node.setTextContent(statementId.toString());
                root.appendChild(statement_id_node);
                if (balanceId == null) {
                    continue;
                }
                Element balances_node = result.createElement("balances");
                Attr balance_Type_node = result.createAttribute("type");
                balance_Type_node.setNodeValue(type);
                Element balance_id_node = result.createElement("balance_id");
                balance_id_node.setAttributeNode(balance_Type_node);
                balances_node.appendChild(balance_id_node);
                balance_id_node.setTextContent(balanceId.toString());
                balance_Type_node = result.createAttribute("type");
                balance_Type_node.setNodeValue(type);
                balance_id_node = result.createElement("balance_id");
                balance_id_node.setAttributeNode(balance_Type_node);
                balances_node.appendChild(balance_id_node);
                balance_id_node.setTextContent(balanceId.toString());
                root.appendChild(balances_node);
            }
        } finally {
            Utils.closeResources(connection, statement, rset);
        }
    }

    @Override
    public String retrieveBackOfficeReference(String backOfficeType, String customerBankReference, String companyAbbvName, String mainBankAbbvName) throws Exception {
        if (backOfficeType == null) {
            return customerBankReference;
        }
        String backOfficeReference = null;
        String databaseColumn = (String) this.boTypeDatabaseColummnMatrix.get(backOfficeType);
        GTPCompany customerCompany = GTPSecurity.getCompany(companyAbbvName);
        GTPCompany bankCompany = GTPSecurity.getCompany(mainBankAbbvName);
        CustomerReference customerReference = new CustomerReference();
        BulkReferencesSet customerReferenceKey = new BulkReferencesSet();
        customerReferenceKey.add(new BulkReference("bank_id", bankCompany.getIdAsString()));
        customerReferenceKey.add(new BulkReference("customer_id", customerCompany.getIdAsString()));
        customerReferenceKey.add(new BulkReference("reference", customerBankReference));
        customerReference.retrieveFromDB(customerReferenceKey);
        String getMethodName = "get" + databaseColumn.substring(0, 1).toUpperCase() + databaseColumn.substring(1).toLowerCase();
        Method getMethod = customerReference.getClass().getMethod(getMethodName, new Class[0]);
        backOfficeReference = (String) getMethod.invoke(customerReference, new Object[0]);
        if (backOfficeReference == null || "".equals(backOfficeReference)) {
            backOfficeReference = customerBankReference;
        }
        return backOfficeReference;
    }

    @Override
    public String retrieveBalanceIdFrom(String stmtId, String type) throws Exception {
        if ("".equals(stmtId) || stmtId == null) {
            return null;
        }
        if ("".equals(type) || type == null) {
            throw new Exception("Error: Could not retrieve balance_id in GTPBOReferenceService. Cause: type is empty or null.");
        }
        Connection connection = PoolBrokerService.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            ArrayList<String> bindvalues = new ArrayList<String>();
            StringBuilder query = new StringBuilder();
            query.append("select BALANCE_ID from ").append("GTP_ACCOUNT_BALANCE").append(" where STATEMENT_ID = ? and type = ?");
            statement = connection.prepareStatement(query.toString());
            bindvalues.add(stmtId);
            bindvalues.add(type);
            Utils.bindValueToPreparedStatement(statement, bindvalues);
            resultSet = statement.executeQuery();
            String result = null;
            while (resultSet.next()) {
                result = resultSet.getString(1);
                if (!resultSet.next()) {
                    continue;
                }
                throw new Exception("N30123 - At least two balance id exist for the statement Id: " + stmtId + " and type:" + type);
            }
            if (result != null) {
                LOG.info((Object) ("N30123 - An existing balance id: " + result + " has been retrieved from the statement Id: " + stmtId + " and type:" + type));
            } else {
                LOG.error((Object) ("Error: No balance found for the statement Id: " + stmtId + " and type:" + type));
            }
            String string = result;
            return string;
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    @Override
    public String retrieveBankabbvNameFromBranchCode(String brchcode) throws GTPException {
        String bankabbvname;
        PreparedStatement statement = null;
        Connection connection = null;
        bankabbvname = null;
        String companytype = null;
        ResultSet rset = null;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                statement = connection.prepareStatement("Select abbv_name ,type from GTP_COMPANY where treasury_branch_reference = ? ");
                statement.setString(1, brchcode);
                rset = statement.executeQuery();
                while (rset.next()) {
                    bankabbvname = rset.getString(1);
                    companytype = rset.getString(2);
                    if (!companytype.equals("02")) {
                        continue;
                    }
                    bankabbvname = "*";
                }
            } catch (Exception e) {
                throw new GTPException("Error occured while retrieving userid  for customer: " + brchcode, e);
            }
        } finally {
            Utils.closeResources(connection, statement, rset);
        }
        return bankabbvname;
    }

    private String[] retrieveCompanyAddressFromCompanyId(String Company_id) throws GTPException {
        String[] result = new String[5];
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rset = null;
        try {
            connection = PoolBrokerService.getConnection();
            String companyName = null;
            String companyAbbvName = null;
            String companyAddress1 = null;
            String companyAddress2 = null;
            String companyDom = null;
            StringBuilder query = new StringBuilder();
            query.append("select NAME, ABBV_NAME, ADDRESS_LINE_1, ADDRESS_LINE_2, DOM from GTP_COMPANY where COMPANY_ID = ? ");
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, Company_id);
            rset = statement.executeQuery();
            while (rset.next()) {
                companyName = rset.getString(1);
                companyAbbvName = rset.getString(2);
                companyAddress1 = rset.getString(3);
                companyAddress2 = rset.getString(4);
                companyDom = rset.getString(5);
            }
            result[0] = companyName;
            result[1] = companyAbbvName;
            result[2] = companyAddress1;
            result[3] = companyAddress2;
            result[4] = companyDom;
            String[] arrstring = result;
            return arrstring;
        } catch (Exception e) {
            LOG.error((Object) ("Unable to retrieve references from the customer reference: " + Company_id), (Throwable) e);
            throw new GTPException("Unable to retrieve references from the customer reference: " + Company_id, e);
        } finally {
            Utils.closeResources(connection, statement, rset);
        }
    }

    private String[] retrieveCompanyAddressFromTransaction(String boRef, String productCode, String Company_id) throws GTPException {
        String[] result = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rset = null;
        try {
            connection = PoolBrokerService.getConnection();
            String companyName = null;
            String companyAbbvName = null;
            String companyAddress1 = null;
            String companyAddress2 = null;
            String companyDom = null;
            StringBuilder query = new StringBuilder();
            query.append("select APPLICANT_NAME, APPLICANT_ABBV_NAME, APPLICANT_ADDRESS_LINE_1, APPLICANT_ADDRESS_LINE_2, APPLICANT_DOM from ").append(ProductFactory.getTransactionTableName(productCode)).append(" where COMPANY_ID = ? and BO_REF_ID = ? and TNX_TYPE_CODE = '").append("01").append("'");
            statement = connection.prepareStatement(query.toString());
            ArrayList<String> bindvalues = new ArrayList<String>();
            bindvalues.add(Company_id);
            bindvalues.add(boRef);
            Utils.bindValueToPreparedStatement(statement, bindvalues);
            rset = statement.executeQuery();
            if (rset.next()) {
                companyName = rset.getString(1);
                companyAbbvName = rset.getString(2);
                companyAddress1 = rset.getString(3);
                companyAddress2 = rset.getString(4);
                companyDom = rset.getString(5);
                if (rset.next()) {
                    LOG.error((Object) ("Multiple records found from product transaction table for companyId: " + Company_id + " and boRef:" + boRef));
                    throw new GTPException("Multiple records found from product transaction table for companyId: " + Company_id + " and boRef:" + boRef);
                }
                result = new String[]{companyName, companyAbbvName, companyAddress1, companyAddress2, companyDom};
            }
            String[] arrstring = result;
            return arrstring;
        } catch (Exception e) {
            LOG.error((Object) ("Unable to fetch company applicant address from the product transaction table for : " + Company_id), (Throwable) e);
            throw new GTPException("Unable to fetch company applicant address from the product transaction table for: " + Company_id, e);
        } finally {
            Utils.closeResources(connection, statement, rset);
        }
    }

    @Override
    public Node retrieveCompanyAddressNodesFromBORef(String boRef, String productCode, String companyId) throws GTPException {
        Document result = null;
        String[] infos = null;
        try {
            if (boRef != null) {
                infos = this.retrieveCompanyAddressFromTransaction(boRef, productCode, companyId);
            }
            if (infos == null) {
                infos = this.retrieveCompanyAddressFromCompanyId(companyId);
            }
            result = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = result.createElement("references");
            result.appendChild(root);
            Element company_node = result.createElement("company_name");
            root.appendChild(company_node);
            company_node.setTextContent(infos[0]);
            Element company_abbv_name_node = result.createElement("company_abbv_name");
            root.appendChild(company_abbv_name_node);
            company_abbv_name_node.setTextContent(infos[1]);
            Element company_address_1_node = result.createElement("company_adress_1");
            root.appendChild(company_address_1_node);
            company_address_1_node.setTextContent(infos[2]);
            Element company_address_2_node = result.createElement("company_adress_2");
            root.appendChild(company_address_2_node);
            company_address_2_node.setTextContent(infos[3]);
            Element company_dom_node = result.createElement("company_dom");
            root.appendChild(company_dom_node);
            company_dom_node.setTextContent(infos[4]);
        } catch (Exception e) {
            LOG.error((Object) "Error while retrieving Company Address Nodes");
            throw new GTPException("Error while retrieving Company Address Nodes", e);
        }
        return result;
    }

    @Override
    public Node retrieveCompanyAddressNodesFromCompanyId(String companyId) throws GTPException {
        Document result = null;
        try {
            String[] infos = this.retrieveCompanyAddressFromCompanyId(companyId);
            result = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = result.createElement("references");
            result.appendChild(root);
            Element company_node = result.createElement("company_name");
            root.appendChild(company_node);
            company_node.setTextContent(infos[0]);
            Element company_abbv_name_node = result.createElement("company_abbv_name");
            root.appendChild(company_abbv_name_node);
            company_abbv_name_node.setTextContent(infos[1]);
            Element company_address_1_node = result.createElement("company_adress_1");
            root.appendChild(company_address_1_node);
            company_address_1_node.setTextContent(infos[2]);
            Element company_address_2_node = result.createElement("company_adress_2");
            root.appendChild(company_address_2_node);
            company_address_2_node.setTextContent(infos[3]);
            Element company_dom_node = result.createElement("company_dom");
            root.appendChild(company_dom_node);
            company_dom_node.setTextContent(infos[4]);
        } catch (Exception e) {
            LOG.error((Object) "Error while retrieving Company Address Nodes");
            throw new GTPException("Error while retrieving Company Address Nodes", e);
        }
        return result;
    }

    @Override
    public String retrieveCompanyIdFrom(String abbv_name) throws Exception {
        BigDecimal result;
        LOG.info((Object) ("START getting company ID : retrieveCompanyIdFrom() " + abbv_name));
        result = null;
        Connection connection = PoolBrokerService.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            try {
                String query = "select COMPANY_ID from GTP_COMPANY where ABBV_NAME = ?";
                statement = connection.prepareStatement(query.toString());
                ArrayList<String> bindvalues = new ArrayList<String>();
                bindvalues.add(abbv_name);
                Utils.bindValueToPreparedStatement(statement, bindvalues);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    result = resultSet.getBigDecimal(1);
                }
            } catch (Exception e) {
                LOG.error((Object) e.toString(), (Throwable) e);
                throw new Exception("E30114 - Unable to retrieve the COMPANY_ID from the COMPANY_NAME : " + abbv_name);
            }
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
        if (result == null) {
            throw new Exception("E30114 - Unable to retrieve the COMPANY_ID from the COMPANY_NAME : " + abbv_name);
        }
        LOG.info((Object) ("START getting company ID : retrieveCompanyIdFrom() " + abbv_name));
        return "" + result.intValue();
    }

    @Override
    public String[] retrieveCompanyInfosFrom(String customerBankReference) throws Exception {
        String[] result = new String[6];
        String entity = null;
        CustomerReferenceSet customerReferenceSet = new CustomerReferenceSet();
        customerReferenceSet.retrieveByCustomerReference(customerBankReference);
        if (customerReferenceSet.getListOfCustomerReferences().size() > 1) {
            throw new Exception("N30123 - At least two companies exist for the  customer reference: " + customerBankReference);
        }
        if (customerReferenceSet.getListOfCustomerReferences().size() > 0) {
            CustomerReference customerReference = customerReferenceSet.getListOfCustomerReferences().iterator().next();
            GTPCompany customerCompany = GTPSecurity.getCompany(customerReference.getCustomer_id());
            GTPCompany bankCompany = GTPSecurity.getCompany(customerReference.getBank_id());
            EntityReferenceSet entityReferenceSet = new EntityReferenceSet();
            entityReferenceSet.retrieveEntityReferenceFromCustomerReference(customerBankReference);
            if (entityReferenceSet.getListEntityReference().size() > 0) {
                String entityId = entityReferenceSet.getListEntityReference().iterator().next().getEntity_id().getStringValue();
                BulkReferencesSet entityKey = new BulkReferencesSet();
                entityKey.add(new BulkReference("company_id", customerCompany.getIdAsString()));
                entityKey.add(new BulkReference("entity_id", entityId));
                Entity entityObj = new Entity();
                entityObj.retrieveFromDB(entityKey);
                entity = entityObj.getAbbv_name().getValue();
            }
            result[0] = customerCompany.getAbbv_name();
            result[1] = bankCompany.getAbbv_name();
            result[2] = bankCompany.getName();
            if (entity != null) {
                result[3] = entity;
            }
            result[4] = customerCompany.getIdAsString();
            result[5] = customerBankReference;
        }
        return result;
    }

    @Override
    public String[] retrieveCompanyInfosFrom(String customer_bank_reference, List banks) throws Exception {
        String[] result = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rset = null;
        try {
            connection = PoolBrokerService.getConnection();
            String companyName = null;
            String mainBankAbbvName = null;
            String entity = null;
            String mainBankName = null;
            String addressLine1 = null;
            String addressLine2 = null;
            String dom = null;
            String isoCode = null;
            String country = null;
            String status = null;
            BigDecimal companyId = null;
            StringBuilder query = new StringBuilder();
            query.append("select D.CUSTOMER_ABBV_NAME, D.BANK_ABBV_NAME, B.NAME, D.CUSTOMER_ID, B.ADDRESS_LINE_1, B.ADDRESS_LINE_2, B.DOM, B.ISO_CODE, B.COUNTRY, C.ACTV_FLAG from GTP_CUSTOMER_REFERENCE D,GTP_COMPANY B,GTP_COMPANY C WHERE  D.BRCH_CODE = '00001' AND  D.REFERENCE = ?  AND B.COMPANY_ID = D.BANK_ID AND D.CUSTOMER_ID =  C.COMPANY_ID");
            if (banks != null && !banks.isEmpty()) {
                String inPlaceHolders = Utils.preparePlaceHolders(banks.size());
                query.append(" AND D.BANK_ABBV_NAME IN (" + inPlaceHolders + ") ");
            }
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, customer_bank_reference);
            if (banks != null && !banks.isEmpty()) {
                Iterator iterator = banks.iterator();
                int i = 2;
                while (iterator.hasNext()) {
                    statement.setString(i++, (String) iterator.next());
                }
            }
            rset = statement.executeQuery();
            while (rset.next()) {
                companyName = rset.getString(1);
                mainBankAbbvName = rset.getString(2);
                mainBankName = rset.getString(3);
                companyId = rset.getBigDecimal(4);
                addressLine1 = rset.getString(5);
                addressLine2 = rset.getString(6);
                dom = rset.getString(7);
                isoCode = rset.getString(8);
                country = rset.getString(9);
                status = rset.getString(10);
                if (!rset.next()) {
                    continue;
                }
                throw new Exception("N30123 - At least two companies exist for the customer_bank_reference: " + customer_bank_reference);
            }
            if (companyName != null) {
                statement.close();
                statement = connection.prepareStatement("select abbv_name from GTP_ENTITY_REFERENCE entity_ref ,GTP_ENTITY entity  where entity_ref.entity_id = entity.entity_id and entity_ref.reference = ?");
                statement.setString(1, customer_bank_reference);
                rset = statement.executeQuery();
                while (rset.next()) {
                    if (entity == null) {
                        entity = rset.getString(1);
                        continue;
                    }
                    entity = null;
                    break;
                }
                LOG.info((Object) ("References fetched from the customer reference: " + customer_bank_reference + ", company_name: " + companyName + ",  main bank abbv_name/name: " + mainBankAbbvName + '/' + mainBankName + (entity != null ? new StringBuilder(", entity: ").append(entity).toString() : "")));
                result = new String[11];
                result[0] = companyName;
                result[1] = mainBankAbbvName;
                result[2] = mainBankName;
                if (entity != null) {
                    result[3] = entity;
                }
                result[4] = companyId == null ? null : "" + companyId.intValue();
                result[5] = addressLine1;
                result[6] = addressLine2;
                result[7] = dom;
                result[8] = isoCode;
                result[9] = country;
                result[10] = status;
            }
            String[] arrstring = result;
            return arrstring;
        } catch (SQLException e) {
            LOG.error((Object) ("Unable to retrieve references from the customer reference: " + customer_bank_reference), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, rset);
        }
    }

    @Override
    public Node retrieveCompanyInfosFrom(String product_code, String customer_bank_reference) throws Exception {
        return this.retrieveCompanyInfosFrom(product_code, null, customer_bank_reference);
    }

    @Override
    public Node retrieveCompanyInfosFrom(String product_code, String sub_product_code, String customer_bank_reference) throws Exception {
        String default_customer_bank_reference = this.getDefCustRef(customer_bank_reference, null, product_code, sub_product_code);
        String[] infos = this.retrieveCompanyInfosFrom(default_customer_bank_reference);
        Document result = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = result.createElement("references");
        result.appendChild(root);
        Element company_node = result.createElement("company_name");
        root.appendChild(company_node);
        company_node.setTextContent(infos[0]);
        Element main_bank_abbv_name_node = result.createElement("main_bank_abbv_name");
        root.appendChild(main_bank_abbv_name_node);
        main_bank_abbv_name_node.setTextContent(infos[1]);
        Element main_bank_name_node = result.createElement("main_bank_name");
        root.appendChild(main_bank_name_node);
        main_bank_name_node.setTextContent(infos[2]);
        if (infos[3] != null) {
            Element entity_node = result.createElement("entity");
            root.appendChild(entity_node);
            entity_node.setTextContent(infos[3]);
        }
        Element company_id_node = result.createElement("company_id");
        root.appendChild(company_id_node);
        company_id_node.setTextContent(infos[4]);
        Element customerBankReference = result.createElement("customer_bank_reference");
        root.appendChild(customerBankReference);
        customerBankReference.setTextContent(infos[5]);
        return result;
    }

    public String[] retrieveCompanyInfosFrom(String refId, String tnxId, String productCode, String subProductCode) throws Exception {
        try {
            TransactionProductFile tnxFile = ProductFactory.newTransactionProductFile(productCode);
            tnxFile.retrieveFileFromTnx(refId, tnxId);
            String[] result = new String[6];
            result[0] = tnxFile.getCompany_name().getValue();
            result[1] = tnxFile.getMain_bank_abbv_name().getValue();
            result[2] = tnxFile.getMain_bank().getName().getValue();
            result[3] = tnxFile.getEntity() != null ? tnxFile.getEntity().getValue() : null;
            result[4] = tnxFile.getCompany_id().getStringValue();
            result[5] = tnxFile.getSender().getReference().getValue();
            return result;
        } catch (GTPException e) {
            throw new Exception("E30114 - Unable to retrieve the customer details from ref_id/tnx_id: " + refId + '/' + tnxId, (Throwable) ((Object) e));
        }
    }

    private String[] retrieveCompanyInfosFromBOType(String backOfficeType, String backOfficeReference) throws Exception {
        String boTypeDatabaseColumn = (String) this.boTypeDatabaseColummnMatrix.get(backOfficeType);
        String[] result = new String[6];
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rset = null;
        try {
            connection = PoolBrokerService.getConnection();
            String companyName = null;
            String mainBankAbbvName = null;
            String entity = null;
            String mainBankName = null;
            String customerBankReference = null;
            BigDecimal companyId = null;
            BigDecimal bankId = null;
            StringBuilder query = new StringBuilder();
            query.append("select CUSTOMER_ID, BANK_ID, REFERENCE from GTP_CUSTOMER_REFERENCE where ").append(boTypeDatabaseColumn).append(" = ? OR REFERENCE = ? ");
            statement = connection.prepareStatement(query.toString());
            ArrayList<String> bindvalues = new ArrayList<String>();
            bindvalues.add(backOfficeReference);
            bindvalues.add(backOfficeReference);
            Utils.bindValueToPreparedStatement(statement, bindvalues);
            rset = statement.executeQuery();
            int i = 0;
            while (rset.next()) {
                if (i > 0) {
                    throw new Exception("N30123 - At least two companies exist for the back_office_reference: " + backOfficeReference);
                }
                ++i;
                companyId = rset.getBigDecimal(1);
                bankId = rset.getBigDecimal(2);
                customerBankReference = rset.getString(3);
            }
            if (companyId != null && bankId != null) {
                GTPCompany customerCompany = GTPSecurity.getCompany(companyId.intValue());
                GTPCompany bankCompany = GTPSecurity.getCompany(bankId.intValue());
                companyName = customerCompany.getAbbv_name();
                mainBankAbbvName = bankCompany.getAbbv_name();
                mainBankName = bankCompany.getName();
                EntityReferenceSet entityReferenceSet = new EntityReferenceSet();
                entityReferenceSet.retrieveEntityReferenceFromCustomerReference(customerBankReference);
                if (entityReferenceSet.getListEntityReference().size() > 0) {
                    String entityId = entityReferenceSet.getListEntityReference().iterator().next().getEntity_id().getStringValue();
                    BulkReferencesSet entityKey = new BulkReferencesSet();
                    entityKey.add(new BulkReference("company_id", customerCompany.getIdAsString()));
                    entityKey.add(new BulkReference("entity_id", entityId));
                    Entity entityObj = new Entity();
                    entityObj.retrieveFromDB(entityKey);
                    entity = entityObj.getAbbv_name().getValue();
                }
            }
            LOG.info((Object) ("References fetched from the backoffice reference: " + backOfficeReference + ", company_name: " + companyName + ",  main bank abbv_name/name: " + mainBankAbbvName + '/' + mainBankName + (entity != null ? new StringBuilder(", entity: ").append(entity).toString() : "")));
            result[0] = companyName;
            result[1] = mainBankAbbvName;
            result[2] = mainBankName;
            if (entity != null) {
                result[3] = entity;
            }
            result[4] = companyId == null ? null : "" + companyId.intValue();
            result[5] = customerBankReference;
            String[] arrstring = result;
            return arrstring;
        } catch (SQLException e) {
            LOG.error((Object) ("Unable to retrieve references from the backoffice reference: " + backOfficeReference), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, rset);
        }
    }

    @Override
    public String[] retrieveCompanyInfosFromISOCode(String isoCode, String account_no) throws Exception {
        String[] result = new String[5];
        Connection connection = PoolBrokerService.getConnection();
        PreparedStatement statement = null;
        ResultSet rset = null;
        try {
            String entity = null;
            BigDecimal companyId = null;
            statement = connection.prepareStatement("SELECT e.company_id, e.abbv_name FROM GTP_ACCOUNT act join GTP_ENTITY_ACCOUNT ea on ea.account_id = act.account_id join GTP_ENTITY  e on e.entity_id = ea.entity_id WHERE act.iso_code = ? and act.account_no = ?");
            statement.setString(1, isoCode);
            statement.setString(2, account_no);
            rset = statement.executeQuery();
            if (rset.next()) {
                companyId = rset.getBigDecimal(1);
                entity = rset.getString(2);
            }
            LOG.info((Object) ("References fetched from the iso code: " + isoCode + (entity != null ? new StringBuilder(", entity: ").append(entity).toString() : "")));
            String string = result[0] = companyId == null ? null : "" + companyId.intValue();
            if (entity != null) {
                result[1] = entity;
            }
            String[] arrstring = result;
            return arrstring;
        } catch (SQLException e) {
            LOG.error((Object) ("Unable to retrieve references from the iso code: " + isoCode), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, rset);
        }
    }

    @Override
    public String retrieveCounterPartyId(String tnx_id, String ref_id, String counterartyType) throws GTPException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = PoolBrokerService.getConnection();
            StringBuilder query = new StringBuilder();
            boolean first = true;
            query.append("select COUNTERPARTY_ID from ").append("GTP_PRODUCT_COUNTERPARTY_TNX");
            query.append(" where ");
            if (StringUtils.isNotBlank((String) ref_id)) {
                query.append(" REF_ID = ? ");
                first = false;
            }
            if (StringUtils.isNotBlank((String) tnx_id)) {
                if (!first) {
                    query.append(" and ");
                }
                query.append(" TNX_ID = ? ");
                first = false;
            }
            if (StringUtils.isNotBlank((String) counterartyType)) {
                if (!first) {
                    query.append(" and ");
                }
                query.append(" COUNTERPARTY_TYPE = ? ");
            }
            statement = connection.prepareStatement(query.toString());
            int i = 1;
            if (StringUtils.isNotBlank((String) ref_id)) {
                statement.setString(i++, ref_id);
            }
            if (StringUtils.isNotBlank((String) tnx_id)) {
                statement.setString(i++, tnx_id);
            }
            if (StringUtils.isNotBlank((String) counterartyType)) {
                statement.setString(i++, counterartyType);
            }
            resultSet = statement.executeQuery();
            String result = null;
            while (resultSet.next()) {
                result = resultSet.getString(1);
                if (!resultSet.next()) {
                    continue;
                }
                throw new Exception("N30123 - At least two references exist for there parameters");
            }
            if (result != null) {
                LOG.info((Object) ("N30123 - An existing reference id: " + result + " has been retrieved from the tnx id: " + tnx_id));
            }
            String string = result;
            return string;
        } catch (Exception e) {
            LOG.error((Object) "Error while retrieving Counterparty Id", (Throwable) e);
            throw new GTPException("Error while retrieving Counterparty Id", e);
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    @Override
    public String retrieveCrossReferenceChildTnxId(String ref_id, String tnx_id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rset = null;
        try {
            connection = PoolBrokerService.getConnection();
            String child_tnx_id = null;
            statement = connection.prepareStatement("Select child_tnx_id from GTP_PRODUCT_CROSS_REF_TNX where ref_id = ? and tnx_id = ?");
            statement.setString(1, ref_id);
            statement.setString(2, tnx_id);
            rset = statement.executeQuery();
            if (rset.next()) {
                child_tnx_id = rset.getString(1);
            }
            LOG.info((Object) ("Child_tnx_id fetched from the CrossReferenceTnx: " + child_tnx_id));
            String string = child_tnx_id == null ? "" : child_tnx_id;
            return string;
        } catch (SQLException e) {
            LOG.error((Object) ("Unable to retrieve Child_tnx_id fetched from the CrossReference " + ref_id), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, rset);
        }
    }

    @Override
    public String retrieveCrossRefId(String ref_id, String tnx_id, String product_code, String child_ref_id, String child_tnx_id, String child_product_code) throws GTPException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = PoolBrokerService.getConnection();
            StringBuilder query = new StringBuilder();
            boolean first = true;
            query.append("select CROSS_REFERENCE_ID from ").append("GTP_PRODUCT_CROSS_REF_TNX");
            query.append(" where ");
            if (StringUtils.isNotBlank((String) ref_id)) {
                query.append(" REF_ID = ? ");
                first = false;
            }
            if (StringUtils.isNotBlank((String) tnx_id)) {
                if (!first) {
                    query.append(" and ");
                }
                query.append(" TNX_ID = ? ");
                first = false;
            }
            if (StringUtils.isNotBlank((String) product_code)) {
                if (!first) {
                    query.append(" and ");
                }
                query.append(" PRODUCT_CODE = ? ");
                first = false;
            }
            if (StringUtils.isNotBlank((String) child_ref_id)) {
                if (!first) {
                    query.append(" and ");
                }
                query.append(" CHILD_REF_ID = ? ");
                first = false;
            }
            if (StringUtils.isNotBlank((String) child_tnx_id)) {
                if (!first) {
                    query.append(" and ");
                }
                query.append(" CHILD_TNX_ID = ? ");
                first = false;
            }
            if (StringUtils.isNotBlank((String) child_product_code)) {
                if (!first) {
                    query.append(" and ");
                }
                query.append(" CHILD_PRODUCT_CODE =  ? ");
            }
            statement = connection.prepareStatement(query.toString());
            int index = 1;
            if (StringUtils.isNotBlank((String) ref_id)) {
                statement.setString(index++, ref_id);
            }
            if (StringUtils.isNotBlank((String) tnx_id)) {
                statement.setString(index++, tnx_id);
            }
            if (StringUtils.isNotBlank((String) product_code)) {
                statement.setString(index++, product_code);
            }
            if (StringUtils.isNotBlank((String) child_ref_id)) {
                statement.setString(index++, child_ref_id);
            }
            if (StringUtils.isNotBlank((String) child_tnx_id)) {
                statement.setString(index++, child_tnx_id);
            }
            if (StringUtils.isNotBlank((String) child_product_code)) {
                statement.setString(index++, child_product_code);
            }
            resultSet = statement.executeQuery();
            String result = null;
            while (resultSet.next()) {
                result = resultSet.getString(1);
                if (!resultSet.next()) {
                    continue;
                }
                throw new Exception("N30123 - At least two references exist for there parameters");
            }
            if (result != null) {
                LOG.info((Object) ("N30123 - An existing reference id: " + result + " has been retrieved from the tnx id: " + tnx_id));
            }
            String string = result;
            return string;
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw new GTPException(e);
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    @Override
    public String retrieveCustomerBankReference(String refId, String tnxId, String productCode) throws Exception {
        try {
            TransactionProductFile file = ProductFactory.newTransactionProductFile(productCode.toUpperCase());
            file.retrieveFileFromTnx(refId, tnxId);
            Correspondent party = file.getProduct().getSender();
            if (party == null || party.getReference() == null || party.getReference().isNull()) {
                throw new Exception("N30123 - No customer bank reference found for the file: " + refId + "/" + tnxId + "/" + productCode);
            }
            return party.getReference().getValue();
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw e;
        }
    }

    @Override
    public String retrieveCustomerBankReferenceFromMaster(String ref_id, String product_code) throws Exception {
        Connection connection = PoolBrokerService.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("select ");
            if ("SR".equals(product_code) || "BR".equals(product_code) || "EL".equals(product_code)) {
                query.append(" P.BENEFICIARY_REFERENCE ");
            } else {
                query.append(" P.APPLICANT_REFERENCE ");
            }
            query.append(" from ").append(ProductFactory.getMasterTableName(product_code.toUpperCase())).append(" P where P.REF_ID = ?");
            statement = connection.prepareStatement(query.toString());
            ArrayList<String> bindvalues = new ArrayList<String>(1);
            bindvalues.add(ref_id);
            Utils.bindValueToPreparedStatement(statement, bindvalues);
            resultSet = statement.executeQuery();
            String result = null;
            while (resultSet.next()) {
                result = resultSet.getString(1);
                if (!resultSet.next()) {
                    continue;
                }
                throw new Exception("At least two applicant references exist for the tnx_id: " + ref_id);
            }
            if (result != null) {
                LOG.info((Object) ("Applicant reference : " + result + " has been retrieved from the ref_id: " + ref_id));
            }
            String string = result;
            return string;
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    @Override
    public String retrieveCustomerOwnerId(String bmCustNo, List banks) throws Exception {
        String result;
        block14:
        {
            Connection connection = null;
            PreparedStatement statement = null;
            StringBuilder query = new StringBuilder();
            ResultSet rset = null;
            result = null;
            try {
                connection = PoolBrokerService.getConnection();
                if (banks == null || banks.isEmpty()) {
                    query.append("SELECT COMPANY_ID FROM GTP_COMPANY");
                    query.append(" WHERE TYPE IN (?)");
                    if (banks != null && !banks.isEmpty()) {
                        String inPlaceHolders = Utils.preparePlaceHolders(banks.size());
                        query.append(" AND COMPANY_ID IN (" + inPlaceHolders + ") ");
                    }
                    statement = connection.prepareStatement(query.toString());
                    statement.setString(1, "01");
                    if (banks != null && !banks.isEmpty()) {
                        Iterator iterator = banks.iterator();
                        int i = 2;
                        while (iterator.hasNext()) {
                            statement.setString(i++, (String) iterator.next());
                        }
                    }
                    rset = statement.executeQuery();
                    while (rset.next()) {
                        result = rset.getString(1);
                    }
                    break block14;
                }
                if (banks.size() == 1) {
                    query.append("SELECT COMPANY_ID FROM GTP_COMPANY");
                    query.append(" WHERE ABBV_NAME = ?");
                    statement = connection.prepareStatement(query.toString());
                    statement.setString(1, (String) banks.get(0));
                    rset = statement.executeQuery();
                    while (rset.next()) {
                        result = rset.getString(1);
                    }
                    break block14;
                }
                if (banks.size() > 1) {
                    Integer customerId = Utils.getCustomerIdFromCustomerReference(bmCustNo, banks);
                    query.append("SELECT OWNER_ID FROM GTP_COMPANY");
                    query.append(" WHERE COMPANY_ID = ?");
                    statement = connection.prepareStatement(query.toString());
                    statement.setInt(1, customerId);
                    rset = statement.executeQuery();
                    while (rset.next()) {
                        result = rset.getString(1);
                    }
                    break block14;
                }
                return null;
            } catch (Exception e) {
                throw new Exception("Error occured while retrieving customer owner Id for customer: " + bmCustNo + ", error:" + e.getMessage());
            } finally {
                Utils.closeResources(connection, statement, rset);
            }
        }
        return result;
    }

    private String retrieveEntityFromProduct(String product_code, String ref_id) throws Exception {
        String entity;
        entity = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rset = null;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                Product product = ProductFactory.newTransactionProductFile(product_code).getProduct();
                StringBuilder query = new StringBuilder();
                query.append("select entity from ").append(product.getDatabaseTableName()).append(" where ref_id = ? ");
                statement = connection.prepareStatement(query.toString());
                statement.setString(1, ref_id);
                rset = statement.executeQuery();
                while (rset.next()) {
                    String string = entity = rset.getString(1) != null ? rset.getString(1) : "**";
                }
            } catch (SQLException e) {
                GTPException.log("Unable to retrieve entity from the product: ", e);
                throw e;
            }
        } finally {
            Utils.closeResources(connection, statement, rset);
        }
        return entity;
    }

    @Override
    public String retrieveLineIdFrom(String stmtId, String boRefId) throws Exception {
        if ("".equals(stmtId) || stmtId == null) {
            return null;
        }
        if ("".equals(boRefId) || boRefId == null) {
            throw new Exception("Error: Could not retrieve line_id in GTPBOReferenceService. Cause: bo_ref_id is empty or null.");
        }
        Connection connection = PoolBrokerService.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            ArrayList<String> bindvalues = new ArrayList<String>();
            StringBuilder query = new StringBuilder();
            query.append("select LINE_ID from ").append("GTP_ACCOUNT_STATEMENT_LINE").append(" where STATEMENT_ID = ? and BO_REF_ID = ?");
            statement = connection.prepareStatement(query.toString());
            bindvalues.add(stmtId);
            bindvalues.add(boRefId);
            Utils.bindValueToPreparedStatement(statement, bindvalues);
            resultSet = statement.executeQuery();
            String result = null;
            while (resultSet.next()) {
                result = resultSet.getString(1);
                if (!resultSet.next()) {
                    continue;
                }
                throw new Exception("N30123 - At least two line id exist for the statement Id: " + stmtId + " and bo_ref_id:" + boRefId);
            }
            if (result != null) {
                LOG.info((Object) ("N30123 - An existing line id: " + result + " has been retrieved from the statement Id: " + stmtId + " and bo_ref_id:" + boRefId));
            } else {
                LOG.error((Object) ("Error: No statement line found for the statement Id: " + stmtId + " and bo_ref_id:" + boRefId));
            }
            String string = result;
            return string;
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    public String retrieveLnTnxIdFromBoTnxId(String ref_id, String bo_tnx_id, String product_code, String sub_product_code, String main_bank_abbv_name, String main_bank_role_code, String facility, String pricingOption, String repricingFreq, String lnAmt, String riskType) throws GTPException {
        LOG.info((Object) "Started fetching tnx_id from bo_tnx_id :: retrieveTnxIdFromBoTnxId()");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = PoolBrokerService.getConnection();
            StringBuilder query = new StringBuilder();
            ArrayList<String> bindvalues = new ArrayList<String>();
            query.append("select P.TNX_ID from ").append(ProductFactory.getTransactionTableName(product_code)).append(" P, GTP_PRODUCT_BANK_TNX BK where P.BO_TNX_ID = ? and P.REF_ID = ? ").append(" and P.BO_FACILITY_ID=? ").append(" and P.PRICING_OPTION=? ").append(" and P.LN_AMT=? ").append(" and P.REPRICING_FREQUENCY=? ").append(" and P.RISK_TYPE=? ");
            if (sub_product_code != null) {
                query.append(" and P.SUB_PRODUCT_CODE = ?");
            }
            bindvalues.add(bo_tnx_id);
            bindvalues.add(ref_id);
            bindvalues.add(facility);
            bindvalues.add(pricingOption);
            bindvalues.add(lnAmt);
            bindvalues.add(repricingFreq);
            bindvalues.add(riskType);
            if (sub_product_code != null) {
                bindvalues.add(sub_product_code);
            }
            if (main_bank_abbv_name != null) {
                query.append(" and BK.ABBV_NAME = ? and BK.ROLE_CODE = ? and P.REF_ID = BK.REF_ID and P.TNX_ID = BK.TNX_ID");
                bindvalues.add(main_bank_abbv_name);
                bindvalues.add(main_bank_role_code);
            }
            statement = connection.prepareStatement(query.toString());
            Utils.bindValueToPreparedStatement(statement, bindvalues);
            resultSet = statement.executeQuery();
            String result = null;
            while (resultSet.next()) {
                result = resultSet.getString(1);
                if (!resultSet.next()) {
                    continue;
                }
                return null;
            }
            if (result != null) {
                LOG.info((Object) ("N30123 - An existing reference id: " + result + " has been retrieved from the bank's abbv_name: " + main_bank_abbv_name + " and the bo_tnx_id: " + bo_tnx_id));
            }
            LOG.info((Object) "Done fetching tnx_id from bo_tnx_id :: retrieveTnxIdFromBoTnxId()");
            String string = result;
            return string;
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw new GTPException(e);
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    @Override
    public String retrieveRefIdFromBoRefId(String bo_ref_id, String product_code, String main_bank_abbv_name, String main_bank_role_code) throws Exception {
        return this.retrieveRefIdFromBoRefId(bo_ref_id, product_code, null, main_bank_abbv_name, main_bank_role_code);
    }

    public String retrieveRefIdFromBoRefId(String bo_ref_id, String product_code, String sub_product_code, String main_bank_abbv_name, String main_bank_role_code) throws Exception {
        LOG.info((Object) "Started fetching ref_id from bo_ref_id :: retrieveRefIdFromBoRefId()");
        Connection connection = PoolBrokerService.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            StringBuilder query = new StringBuilder();
            ArrayList<String> bindvalues = new ArrayList<String>();
            query.append("select distinct P.REF_ID from ").append(ProductFactory.getMasterTableName(product_code)).append(" P, GTP_PRODUCT_BANK BK where P.BO_REF_ID = ?");
            if (sub_product_code != null) {
                query.append(" and P.SUB_PRODUCT_CODE = ?");
            }
            bindvalues.add(bo_ref_id);
            if (sub_product_code != null && !"".equals(sub_product_code)) {
                bindvalues.add(sub_product_code);
            }
            if (main_bank_abbv_name != null && !"".equals(main_bank_abbv_name)) {
                query.append(" and BK.ABBV_NAME = ? and BK.ROLE_CODE = ? and P.REF_ID = BK.REF_ID");
                bindvalues.add(main_bank_abbv_name);
                bindvalues.add(main_bank_role_code);
            }
            statement = connection.prepareStatement(query.toString());
            Utils.bindValueToPreparedStatement(statement, bindvalues);
            resultSet = statement.executeQuery();
            String result = null;
            while (resultSet.next()) {
                result = resultSet.getString(1);
                if (!resultSet.next()) {
                    continue;
                }
                throw new Exception("N30123 - At least two references exist for the bank's abbv_name: " + main_bank_abbv_name + " and the bo_ref_id: " + bo_ref_id);
            }
            if (result != null) {
                LOG.info((Object) ("N30123 - An existing reference id: " + result + " has been retrieved from the bank's abbv_name: " + main_bank_abbv_name + " and the bo_ref_id: " + bo_ref_id));
            } else {
                query = new StringBuilder();
                bindvalues.clear();
                query.append("select distinct P.REF_ID from ").append(ProductFactory.getTransactionTableName(product_code)).append(" P, GTP_PRODUCT_BANK_TNX BK where P.BO_REF_ID = ?");
                if (sub_product_code != null) {
                    query.append(" and P.SUB_PRODUCT_CODE = ?");
                }
                bindvalues.add(bo_ref_id);
                if (sub_product_code != null && !"".equals(sub_product_code)) {
                    bindvalues.add(sub_product_code);
                }
                if (main_bank_abbv_name != null && !"".equals(main_bank_abbv_name)) {
                    query.append(" and BK.ABBV_NAME = ? and BK.ROLE_CODE = ? and P.REF_ID = BK.REF_ID and P.TNX_ID = BK.TNX_ID");
                    bindvalues.add(main_bank_abbv_name);
                    bindvalues.add(main_bank_role_code);
                }
                statement.close();
                statement = connection.prepareStatement(query.toString());
                Utils.bindValueToPreparedStatement(statement, bindvalues);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    result = resultSet.getString(1);
                    if (!resultSet.next()) {
                        continue;
                    }
                    throw new Exception("N30123 - At least two references exist in the tnx table for the bank's abbv_name: " + main_bank_abbv_name + " and the bo_ref_id: " + bo_ref_id);
                }
                if (result != null) {
                    LOG.info((Object) ("N30123 - An existing reference id: " + result + " has been retrieved from tnx table for the bank's abbv_name: " + main_bank_abbv_name + " and the bo_ref_id: " + bo_ref_id));
                }
            }
            LOG.info((Object) "Done fetching ref_id from bo_ref_id :: retrieveRefIdFromBoRefId().");
            String string = result;
            return string;
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    @Override
    public String retrieveRefidFromCustRefId(String cust_ref_id, String company_name, String product_code) throws Exception {
        return this.retrieveRefidFromCustRefId(cust_ref_id, company_name, product_code, null);
    }

    public String retrieveRefidFromCustRefId(String cust_ref_id, String company_name, String product_code, String sub_product_code) throws Exception {
        LOG.info((Object) "Started fetching ref_id from cust_ref_id :: retrieveRefidFromCustRefId()");
        Connection connection = PoolBrokerService.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        StringBuilder query = new StringBuilder();
        query.append("select REF_ID from ").append(ProductFactory.getMasterTableName(product_code)).append(" where CUST_REF_ID = ? and COMPANY_NAME = ?");
        if (sub_product_code != null) {
            query.append(" and SUB_PRODUCT_CODE = ?");
        }
        try {
            statement = connection.prepareStatement(query.toString());
            ArrayList<String> bindvalues = new ArrayList<String>();
            bindvalues.add(cust_ref_id);
            bindvalues.add(company_name);
            if (sub_product_code != null) {
                bindvalues.add(sub_product_code);
            }
            Utils.bindValueToPreparedStatement(statement, bindvalues);
            rs = statement.executeQuery();
            String result = null;
            while (rs.next()) {
                result = rs.getString(1);
                if (!rs.next()) {
                    continue;
                }
                throw new Exception("E30123 - At least two different references have been retrieved from the company's abbv_name " + company_name + " and the cust_ref_id " + cust_ref_id);
            }
            if (result != null) {
                LOG.info((Object) ("N30123 - An existing Reference id " + result + " has been retrieved from the company's abbv_name " + company_name + " and the cust_ref_id " + cust_ref_id));
            }
            LOG.info((Object) "Done fetching ref_id from cust_ref_id :: retrieveRefidFromCustRefId()");
            String string = result;
            return string;
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, rs);
        }
    }

    @Override
    public String retrieveRefIdFromTnxId(String tnx_id, String product_code) throws Exception {
        LOG.info((Object) "Started fetching ref_id from tnx_id :: retrieveRefIdFromTnxId()");
        Connection connection = PoolBrokerService.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("select P.REF_ID from ").append(ProductFactory.getTransactionTableName(product_code.toUpperCase())).append(" P where P.TNX_ID = ?");
            statement = connection.prepareStatement(query.toString());
            ArrayList<String> bindvalues = new ArrayList<String>(1);
            bindvalues.add(tnx_id);
            Utils.bindValueToPreparedStatement(statement, bindvalues);
            resultSet = statement.executeQuery();
            String result = null;
            while (resultSet.next()) {
                result = resultSet.getString(1);
                if (!resultSet.next()) {
                    continue;
                }
                throw new Exception("N30123 - At least two references exist for the tnx_id: " + tnx_id);
            }
            if (result != null) {
                LOG.info((Object) ("N30123 - An existing reference id: " + result + " has been retrieved from the tnx id: " + tnx_id));
            }
            LOG.info((Object) "Done fetching ref_id from tnx_id :: retrieveRefIdFromTnxId()");
            String string = result;
            return string;
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    @Override
    public int retrieveSequenceIdfromAccountId(String account_id) throws GTPException {
        if ("".equals(account_id) || account_id == null) {
            throw new GTPException("Error: Could not retrieve statement_id in GTPBOReferenceService. Cause: account number is empty or null.");
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = PoolBrokerService.getConnection();
            StringBuilder query = new StringBuilder();
            query.append("select seq_idx from ").append("GTP_ACCOUNT_STATEMENT").append(" where ACCOUNT_ID = ? ");
            statement = connection.prepareStatement(query.toString());
            statement.setBigDecimal(1, new BigDecimal(account_id));
            resultSet = statement.executeQuery();
            int result = -1;
            while (resultSet.next()) {
                result = new Double(resultSet.getDouble(1)).intValue();
                if (!resultSet.next()) {
                    continue;
                }
                throw new GTPException("N30123 - At least two seq_idx exist for the account id: " + account_id);
            }
            if (result != -1) {
                LOG.info((Object) ("N30123 - An existing seq_idx: " + result + " has been retrieved from the account id: " + account_id));
            } else {
                LOG.error((Object) ("Error: No statement find for the account id: " + account_id));
            }
            int n = result;
            return n;
        } catch (Exception e) {
            LOG.error((Object) e.getMessage(), (Throwable) e);
            throw new GTPException(e.getMessage(), e);
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    @Override
    public String retrieveStatementIdFrom(String account_no) throws Exception {
        if ("".equals(account_no) || account_no == null) {
            throw new Exception("Error: Could not retrieve statement_id in GTPBOReferenceService. Cause: account number is empty or null.");
        }
        String account_id = this.retrieveAccountIdFromAccountNumber(account_no);
        Connection connection = PoolBrokerService.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("select STATEMENT_ID from ").append("GTP_ACCOUNT_STATEMENT").append(" where ACCOUNT_ID = ? ");
            statement = connection.prepareStatement(query.toString());
            statement.setBigDecimal(1, new BigDecimal(account_id));
            resultSet = statement.executeQuery();
            String result = null;
            while (resultSet.next()) {
                result = resultSet.getString(1);
                if (!resultSet.next()) {
                    continue;
                }
                throw new Exception("N30123 - At least two statements id exist for the account: " + account_no);
            }
            if (result != null) {
                LOG.info((Object) ("N30123 - An existing statement id: " + result + " has been retrieved from the account: " + account_no));
            } else {
                LOG.error((Object) ("Error: No statement find for the account: " + account_no));
            }
            String string = result;
            return string;
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    @Override
    public String retrieveStatementIdFrom(String customer_ref, String account_no) throws Exception {
        if ("".equals(account_no) || account_no == null) {
            throw new Exception("Error: Could not retrieve statement_id in GTPBOReferencesService. Cause: account number is empty or null.");
        }
        if ("".equals(customer_ref) || customer_ref == null) {
            throw new Exception("Error: Could not retrieve statement_id in GTPBOReferencesService. Cause: customer reference is empty or null.");
        }
        String account_id = this.retrieveAccountIdFromAccountNumber(account_no, customer_ref);
        Connection connection = PoolBrokerService.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("select STATEMENT_ID from ").append("GTP_ACCOUNT_STATEMENT").append(" where ACCOUNT_ID = ?");
            statement = connection.prepareStatement(query.toString());
            ArrayList<Integer> bindvalues = new ArrayList<Integer>();
            bindvalues.add(new Integer(account_id));
            Utils.bindValueToPreparedStatement(statement, bindvalues);
            resultSet = statement.executeQuery();
            String result = null;
            while (resultSet.next()) {
                result = resultSet.getString(1);
                if (!resultSet.next()) {
                    continue;
                }
                throw new Exception("N30123 - At least two statements id exist for the account: " + account_no + " and for the custumer: " + customer_ref);
            }
            if (result != null) {
                LOG.info((Object) ("N30123 - An existing statement id: " + result + " has been retrieved from the account: " + account_no + " and for the custumer: " + customer_ref));
            } else {
                LOG.error((Object) ("Error: No statement find for the account: " + account_no + " and for the customer: " + customer_ref));
            }
            String string = result;
            return string;
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    @Override
    public String retrieveSubProdCodeFromBoRefId(String bo_ref_id, String product_code, String main_bank_abbv_name, String main_bank_role_code) throws Exception {
        LOG.info((Object) "Started fetching sub_product_code from bo_ref_id :: retrieveSubProdCodeFromBoRefId()");
        Connection connection = PoolBrokerService.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            StringBuilder query = new StringBuilder();
            ArrayList<String> bindvalues = new ArrayList<String>();
            query.append("select distinct p.SUB_PRODUCT_CODE from ").append(ProductFactory.getMasterTableName(product_code)).append(" P, GTP_PRODUCT_BANK BK where P.BO_REF_ID = ?");
            bindvalues.add(bo_ref_id);
            if (main_bank_abbv_name != null && !"".equals(main_bank_abbv_name)) {
                query.append(" and BK.ABBV_NAME = ? and BK.ROLE_CODE = ? and P.REF_ID = BK.REF_ID ");
                bindvalues.add(main_bank_abbv_name);
                bindvalues.add(main_bank_role_code);
            }
            statement = connection.prepareStatement(query.toString());
            Utils.bindValueToPreparedStatement(statement, bindvalues);
            resultSet = statement.executeQuery();
            String result = null;
            while (resultSet.next()) {
                result = resultSet.getString(1);
                if (!resultSet.next()) {
                    continue;
                }
                throw new Exception("N30123 - At least two references exist for the bank's abbv_name: " + main_bank_abbv_name + " and the bo_ref_id: " + bo_ref_id);
            }
            if (result != null) {
                LOG.info((Object) ("N30123 - An existing reference id: " + result + " has been retrieved from the bank's abbv_name: " + main_bank_abbv_name + " and the bo_ref_id: " + bo_ref_id));
            } else {
                query = new StringBuilder();
                bindvalues.clear();
                query.append("select distinct P.SUB_PRODUCT_CODE from ").append(ProductFactory.getTransactionTableName(product_code)).append(" P, GTP_PRODUCT_BANK_TNX BK where P.BO_REF_ID = ?");
                bindvalues.add(bo_ref_id);
                if (main_bank_abbv_name != null && !"".equals(main_bank_abbv_name)) {
                    query.append(" and BK.ABBV_NAME = ? and BK.ROLE_CODE = ?  and P.REF_ID = BK.REF_ID and P.TNX_ID = BK.TNX_ID ");
                    bindvalues.add(main_bank_abbv_name);
                    bindvalues.add(main_bank_role_code);
                }
                statement.close();
                statement = connection.prepareStatement(query.toString());
                Utils.bindValueToPreparedStatement(statement, bindvalues);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    result = resultSet.getString(1);
                    if (!resultSet.next()) {
                        continue;
                    }
                    throw new Exception("N30123 - At least two references exist in the tnx table for the bank's abbv_name: " + main_bank_abbv_name + " and the bo_ref_id: " + bo_ref_id);
                }
                if (result != null) {
                    LOG.info((Object) ("N30123 - An existing reference id: " + result + " has been retrieved from tnx table for the bank's abbv_name: " + main_bank_abbv_name + " and the bo_ref_id: " + bo_ref_id));
                }
            }
            LOG.info((Object) "Done fetching sub_product_code from bo_ref_id :: retrieveSubProdCodeFromBoRefId()");
            String string = result;
            return string;
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    @Override
    public String retrieveTnxIdFromBoTnxId(String ref_id, String bo_tnx_id, String product_code, String main_bank_abbv_name, String main_bank_role_code) throws GTPException {
        return this.retrieveTnxIdFromBoTnxId(ref_id, bo_tnx_id, product_code, null, main_bank_abbv_name, main_bank_role_code);
    }

    @Override
    public String retrieveTnxIdFromBoTnxId(String ref_id, String bo_tnx_id, String product_code, String sub_product_code, String main_bank_abbv_name, String main_bank_role_code) throws GTPException {
        LOG.info((Object) "Started fetching tnx_id from bo_tnx_id :: retrieveTnxIdFromBoTnxId()");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = PoolBrokerService.getConnection();
            StringBuilder query = new StringBuilder();
            ArrayList<String> bindvalues = new ArrayList<String>();
            query.append("select P.TNX_ID from ").append(ProductFactory.getTransactionTableName(product_code)).append(" P, GTP_PRODUCT_BANK_TNX BK where P.BO_TNX_ID = ? and P.REF_ID = ?");
            if (sub_product_code != null) {
                query.append(" and P.SUB_PRODUCT_CODE = ?");
            }
            bindvalues.add(bo_tnx_id);
            bindvalues.add(ref_id);
            if (sub_product_code != null) {
                bindvalues.add(sub_product_code);
            }
            if (main_bank_abbv_name != null) {
                query.append(" and BK.ABBV_NAME = ? and BK.ROLE_CODE = ? and P.REF_ID = BK.REF_ID and P.TNX_ID = BK.TNX_ID");
                bindvalues.add(main_bank_abbv_name);
                bindvalues.add(main_bank_role_code);
            }
            statement = connection.prepareStatement(query.toString());
            Utils.bindValueToPreparedStatement(statement, bindvalues);
            resultSet = statement.executeQuery();
            String result = null;
            while (resultSet.next()) {
                result = resultSet.getString(1);
                if (!resultSet.next()) {
                    continue;
                }
                throw new Exception("N30123 - At least two references exist for the bank's abbv_name: " + main_bank_abbv_name + " and the bo_tnx_id: " + bo_tnx_id);
            }
            if (result != null) {
                LOG.info((Object) ("N30123 - An existing reference id: " + result + " has been retrieved from the bank's abbv_name: " + main_bank_abbv_name + " and the bo_tnx_id: " + bo_tnx_id));
            }
            LOG.info((Object) "Done fetching tnx_id from bo_tnx_id :: retrieveTnxIdFromBoTnxId()");
            String string = result;
            return string;
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw new GTPException(e);
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    @Override
    public String retrieveTnxIdFromRefId(String ref_id, String product_code, String tnx_type_code) throws Exception {
        Connection connection = PoolBrokerService.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            StringBuilder query = new StringBuilder();
            query.append("select P.TNX_ID from ").append(ProductFactory.getTransactionTableName(product_code.toUpperCase())).append(" P where P.REF_ID = ?").append(" and P.TNX_TYPE_CODE= ?").append(" and P.PROD_STAT_CODE= ?");
            statement = connection.prepareStatement(query.toString());
            ArrayList<String> bindvalues = new ArrayList<String>();
            bindvalues.add(ref_id);
            bindvalues.add(tnx_type_code);
            bindvalues.add("02");
            Utils.bindValueToPreparedStatement(statement, bindvalues);
            resultSet = statement.executeQuery();
            String result = null;
            while (resultSet.next()) {
                result = resultSet.getString(1);
                if (!resultSet.next()) {
                    continue;
                }
                throw new Exception("N30123 - At least two transactions exist for the ref id: " + ref_id + " and tnx_type code " + tnx_type_code);
            }
            if (result != null) {
                LOG.info((Object) ("N30123 - An existing tnx id: " + result + " has been retrieved from the reference id: " + ref_id + " and tnx_type_code " + tnx_type_code));
            }
            String string = result;
            return string;
        } catch (Exception e) {
            LOG.error((Object) e.toString(), (Throwable) e);
            throw e;
        } finally {
            Utils.closeResources(connection, statement, resultSet);
        }
    }

    @Override
    public String retrieveTnxIdFromTradeIdOrBoTnxId(String ref_id, String trade_id, String bo_tnx_id, String product_code, String main_bank_abbv_name, String main_bank_role_code) throws GTPException {
        String result;
        Connection connection = null;
        PreparedStatement statement = null;
        result = null;
        ResultSet resultSet = null;
        if (bo_tnx_id != null && !bo_tnx_id.equals("")) {
            result = this.retrieveTnxIdFromBoTnxId(ref_id, bo_tnx_id, product_code, main_bank_abbv_name, main_bank_role_code);
        }
        if (result == null) {
            try {
                try {
                    connection = PoolBrokerService.getConnection();
                    ArrayList<String> bindvalues = new ArrayList<String>();
                    StringBuilder query = new StringBuilder();
                    query.append("select P.TNX_ID, P.TNX_TYPE_CODE from ").append(ProductFactory.getTransactionTableName(product_code)).append(" P");
                    if (!StringUtils.isEmpty((String) main_bank_abbv_name)) {
                        query.append(", GTP_PRODUCT_BANK_TNX BK ");
                    }
                    query.append(" where P.REF_ID = ? ");
                    bindvalues.add(ref_id);
                    if (!StringUtils.isEmpty((String) trade_id)) {
                        query.append(" and P.TRADE_ID = ? ");
                        bindvalues.add(trade_id);
                    }
                    if (!StringUtils.isEmpty((String) main_bank_abbv_name)) {
                        query.append(" and BK.ABBV_NAME = ? ").append(" and BK.ROLE_CODE = ? ").append(" and P.REF_ID = BK.REF_ID and P.TNX_ID = BK.TNX_ID");
                        bindvalues.add(main_bank_abbv_name);
                        bindvalues.add(main_bank_role_code);
                    }
                    query.append(" and P.tnx_stat_code <> '02'");
                    query.append(" ORDER BY P.TNX_TYPE_CODE ASC");
                    statement = connection.prepareStatement(query.toString());
                    Utils.bindValueToPreparedStatement(statement, bindvalues);
                    resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        result = resultSet.getString(1);
                        if (resultSet.getString(2).equals("01")) {
                            break;
                        }
                    }
                    if (result != null) {
                        LOG.info((Object) ("N30123 - An existing transaction id: " + result + " has been retrieved from the bank's abbv_name: " + main_bank_abbv_name + " and the trade_id: " + trade_id));
                    }
                } catch (Exception e) {
                    LOG.error((Object) e.toString(), (Throwable) e);
                    throw new GTPException(e.getMessage(), e);
                }
            } finally {
                Utils.closeResources(connection, statement, resultSet);
            }
        }
        return result;
    }

    private String retrieveUserIdFromLoginId(String customer_bank_reference, String companyName) throws GTPException {
        String userId;
        PreparedStatement statement = null;
        Connection connection = null;
        userId = null;
        ResultSet rset = null;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                statement = connection.prepareStatement("Select user_id from GTP_USER where login_id = ? and company_abbv_name = ?");
                statement.setString(1, customer_bank_reference);
                statement.setString(2, companyName);
                rset = statement.executeQuery();
                while (rset.next()) {
                    userId = rset.getString(1);
                }
            } catch (Exception e) {
                throw new GTPException("Error occured while retrieving userid  for customer: " + companyName, e);
            }
        } finally {
            Utils.closeResources(connection, statement, rset);
        }
        return userId;
    }

    @Override
    public boolean verifyWithExistingBORefid(LargeParameterData data, HashSet set) throws GTPException {
        boolean bValid;
        bValid = true;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                StringBuilder query = new StringBuilder();
                query.append("select data.DATA_1 from GTP_LARGE_PARAM_KEY paramkey, GTP_LARGE_PARAM_DATA data where ");
                query.append("paramkey.PARM_ID = ? and ");
                query.append("paramkey.KEY_1 = ? and ");
                query.append("paramkey.BRCH_CODE = ? and ");
                query.append("data.DATA_1  in ( ");
                query.append(Utils.preparePlaceHolders(set.size()));
                query.append(") ");
                stmt = connection.prepareStatement(query.toString());
                int index = 1;
                stmt.setString(index++, "P225");
                stmt.setString(index++, data.getKeys().getKey("KEY_1"));
                stmt.setString(index++, "00001");
                Object[] arrobject = set.toArray();
                int n = arrobject.length;
                int n2 = 0;
                while (n2 < n) {
                    Object value = arrobject[n2];
                    stmt.setString(index++, (String) value);
                    ++n2;
                }
                rset = stmt.executeQuery();
                if (rset.next()) {
                    bValid = false;
                }
            } catch (Exception e) {
                throw new GTPException(e);
            }
        } finally {
            Utils.closeResources(connection, stmt, rset);
        }
        return bValid;
    }

    public class BOReferencesHandler
            extends DefaultHandler {

        private static final String DEFAULT = "DEFAULT";
        private Map matrix;
        private int seq_length;
        private int length;
        private String product_code;
        private Map subProductMatrix;
        private String sub_product_code;
        private boolean product_code_flag;
        private boolean sub_product_code_flag;
        private final StringBuilder element;
        private SAXParser parser;

        public BOReferencesHandler() throws Exception {
            this.seq_length = 8;
            this.length = 13;
            this.product_code_flag = false;
            this.sub_product_code_flag = false;
            this.element = new StringBuilder();
            this.parser = null;
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            this.parser = factory.newSAXParser();
        }

        @Override
        public void characters(char[] ch, int start, int len) throws SAXException {
            this.element.append(new String(ch, start, len));
        }

        @Override
        public void endDocument() throws SAXException {
        }

        @Override
        public void endElement(String uri, String localName, String rawName) throws SAXException {
            if (rawName.equals("length")) {
                this.length = Integer.parseInt(this.element.toString());
            } else if (rawName.equals("seq_length")) {
                this.seq_length = Integer.parseInt(this.element.toString());
            } else if (rawName.equals("prefix")) {
                if (this.product_code_flag && !this.sub_product_code_flag) {
                    this.subProductMatrix.put("DEFAULT", this.element.toString());
                }
                if (this.product_code_flag && this.sub_product_code_flag) {
                    this.subProductMatrix.put(this.sub_product_code, this.element.toString());
                }
            }
            if (rawName.equals("subproduct")) {
                this.sub_product_code_flag = false;
            }
            if (rawName.equals("product")) {
                this.product_code_flag = false;
                this.matrix.put(this.product_code, this.subProductMatrix);
            }
            this.element.setLength(0);
        }

        @Override
        public void endPrefixMapping(String arg1) throws SAXException {
        }

        public int getLength() {
            return this.length;
        }

        public Map getMatrix() {
            return this.matrix;
        }

        public int getSeq_length() {
            return this.seq_length;
        }

        @Override
        public void ignorableWhitespace(char[] arg1, int arg2, int arg3) throws SAXException {
        }

        public void process(String path) throws Exception {
            URL url = PortalResources.getResource(path);
            InputStream file = null;
            try {
                file = url.openStream();
                if (file != null) {
                    this.parser.parse(file, (DefaultHandler) this);
                }
            } catch (Exception e) {
                LOG.error((Object) "GTP BO References, unable to parse xml source.", (Throwable) e);
            }
        }

        @Override
        public void processingInstruction(String arg1, String arg2) throws SAXException {
        }

        @Override
        public void setDocumentLocator(Locator arg1) {
        }

        @Override
        public void skippedEntity(String arg1) throws SAXException {
        }

        @Override
        public void startDocument() throws SAXException {
            this.matrix = new HashMap();
        }

        @Override
        public void startElement(String uri, String localName, String rawName, Attributes atts) throws SAXException {
            this.element.setLength(0);
            if (rawName.equals("product")) {
                this.product_code_flag = true;
                this.product_code = atts.getValue(0);
                this.subProductMatrix = new HashMap();
            }
            if (rawName.equals("subproduct")) {
                this.sub_product_code_flag = true;
                this.sub_product_code = atts.getValue(0);
            }
        }

        @Override
        public void startPrefixMapping(String arg1, String arg2) throws SAXException {
        }
    }

}

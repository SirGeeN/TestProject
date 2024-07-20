/*
 * Decompiled with CFR 0_116.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 *  org.apache.commons.logging.LogFactory
 *  org.apache.turbine.om.security.User
 *  org.apache.turbine.util.RunData
 */
package com.misys.portal.common.tools;

import com.misys.portal.common.tools.ListComparator;
import com.misys.portal.common.tools.ListElement;
import com.misys.portal.common.tools.ListElementSet;
import com.misys.portal.common.tools.SecurityUtils;
import com.misys.portal.common.tools.Utils;
import com.misys.portal.common.tracer.GTPException;
import com.misys.portal.security.GTPUser;
import com.misys.portal.services.db.PoolBrokerService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.turbine.util.RunData;

public  class CodeData
implements Comparable {
    private static final String LANGUAGE_STRING = "language";
    private static final String COMPANY_ID_STRING = "company_id";
    private static final String CODE_VAL_STRING = "code_val";
    private static final String CODE_ID_STRING = "code_id";
    private static final String PRODUCT_CODE_STRING = "product_code";
    private static final String BRCH_CODE_STRING = "brch_code";
    private static final String LONG_DESC_STRING = "long_desc";
    private static final String SHORT_DESC_STRING = "short_desc";
    public static final String WILDCARD = "*";
    public static Map<String, List<String>> codes;
    private static final Log LOG;
    public boolean match;
    public boolean exactMatch;
    private String brch_code;
    private String company_id;
    private String product_code;
    private String code_id;
    private String code_val;
    private String short_desc;
    private String long_desc;
    private String language;
    private String old_brch_code;
    private String old_company_id;
    private String old_product_code;
    private String old_code_val;
    private String old_language;
    private boolean inUse;

    static {
        LOG = LogFactory.getLog(CodeData.class);
        codes = new HashMap<String, List<String>>();
    }

    public static boolean containsValue(String code, String value) throws GTPException {
        return CodeData.getCodeValues(code).contains(value);
    }

    public static ListElementSet getCodeData(String codeKey, String language) throws GTPException {
        ListComparator comparator = new ListComparator("code_val", "long_desc", true);
        ListElementSet codesSet = CodeData.retrieveCodeData(null, "00001", CodeData.getWILDCARD(), CodeData.getWILDCARD(), CodeData.getWILDCARD(), codeKey, language, comparator);
        return codesSet;
    }

    public static ListElementSet getCodeData(String branchCode, String companyId, String productCode, String subProductCode, String codeId, String codeVal, String language, ListComparator comparator) throws GTPException {
        ListElementSet result;
        if (codeId == null || branchCode == null || companyId == null || productCode == null || subProductCode == null) {
            throw new GTPException("The code parameters must not be null");
        }
        result = new ListElementSet(comparator);
        PreparedStatement stmt = null;
        ResultSet rset = null;
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("select short_desc, long_desc ,").append(" brch_code, product_code, code_val, company_id, in_use_flag, language ").append(" from gtp_code_data ").append(" where code_id = ?").append(" and code_val = ?").append(" and brch_code in (?,'*')").append(" and company_id in (?,'*')").append(" and product_code in (?,'*')").append(" and sub_product_code in (?,'*')").append(" and language in (?,'*')").append(" order by code_val, wild_card_ind desc");
        ArrayList<String> bindValues = new ArrayList<String>();
        bindValues.add(codeId);
        bindValues.add(codeVal);
        bindValues.add(branchCode);
        bindValues.add(companyId);
        bindValues.add(productCode);
        bindValues.add(subProductCode);
        bindValues.add(language);
        Connection connection = null;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                stmt = connection.prepareStatement(strBuilder.toString());
                Utils.bindValueToPreparedStatement(stmt, bindValues);
                rset = stmt.executeQuery();
                String prevCodeValue = null;
                String codeValue = null;
                ListElement tempCodeData = null;
                while (rset.next()) {
                    prevCodeValue = codeValue;
                    codeValue = rset.getString(5);
                    if (prevCodeValue != null && prevCodeValue.equals(codeValue) || !"Y".equals(rset.getString(7))) continue;
                    tempCodeData = new ListElement();
                    tempCodeData.setData("short_desc", rset.getString(1));
                    tempCodeData.setData("long_desc", rset.getString(2));
                    tempCodeData.setData("brch_code", rset.getString(3));
                    tempCodeData.setData("product_code", rset.getString(4));
                    tempCodeData.setData("code_id", codeId);
                    tempCodeData.setData("code_val", codeValue);
                    tempCodeData.setData("company_id", rset.getString(6));
                    tempCodeData.setData("language", rset.getString(8));
                    result.add(tempCodeData);
                }
            }
            catch (Exception e) {
                LOG.error((Object)e.getMessage(), (Throwable)e);
                throw new GTPException(e.getMessage());
            }
        }
        finally {
            Utils.closeResources(connection, stmt, rset);
        }
        return result;
    }

    public static List<String> getCodeValues(String codeKey) throws GTPException {
        if (codes.containsKey(codeKey)) {
            return codes.get(codeKey);
        }
        ListComparator comparator = new ListComparator("code_val", "long_desc", true);
        ListElementSet codesSet = CodeData.retrieveCodeData(null, "00001", CodeData.getWILDCARD(), CodeData.getWILDCARD(), CodeData.getWILDCARD(), codeKey, CodeData.getWILDCARD(), comparator);
        ArrayList<String> values = new ArrayList<String>();
        List<ListElement> le1= codesSet.getListElementVector();
        for (ListElement le : le1) {
            values.add(le.getData("code_val").toString());
        }
        codes.put(codeKey, values);
        return values;
    }

    public static String getWILDCARD() {
        return "*";
    }

    public static ListElementSet retrieveCodeData(RunData data, String branchCode, String companyId, String productCode, String subProductCode, String codeId, String language, ListComparator comparator) throws GTPException {
        return CodeData.retrieveCodeData(branchCode, companyId, productCode, subProductCode, codeId, language, comparator, true);
    }

    public static ListElementSet retrieveCodeData(String branchCode, String companyId, String productCode, String subProductCode, String codeId, String language, ListComparator comparator, boolean considerProduct) throws GTPException {
        return CodeData.retrieveCodeData(branchCode, companyId, productCode, subProductCode, codeId, language, comparator, considerProduct, false);
    }

    public static ListElementSet retrieveCodeData(String branchCode, String companyId, String productCode, String subProductCode, String codeId, String language, ListComparator comparator, boolean considerProduct, boolean specificOrderRequired) throws GTPException {
        ListElementSet result;
        if (codeId == null || branchCode == null || companyId == null || productCode == null || subProductCode == null) {
            throw new GTPException("The code parameters must not be null");
        }
        result = new ListElementSet(comparator);
        PreparedStatement stmt = null;
        ResultSet rset = null;
        ArrayList<String> bindValues = new ArrayList<String>();
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("select short_desc, long_desc ,").append(" brch_code, product_code, code_val, company_id, in_use_flag, language, preferred_order ").append(" from gtp_code_data ").append(" where code_id = ?").append(" and brch_code in (?,'*')").append(" and company_id in (?,'*')");
        bindValues.add(codeId);
        bindValues.add(branchCode);
        bindValues.add(companyId);
        if (considerProduct) {
            strBuilder.append(" and product_code in (?,'*')").append(" and sub_product_code in (?,'*')");
            bindValues.add(productCode);
            bindValues.add(subProductCode);
        }
        strBuilder.append(" and language in (?,'*')");
        bindValues.add(language);
        if (specificOrderRequired) {
            strBuilder.append(" order by preferred_order asc");
            result = new ListElementSet(new ListComparator("preferred_order", "long_desc", true));
        } else if (considerProduct) {
            strBuilder.append(" order by code_val, wild_card_ind, language desc");
        } else {
            strBuilder.append(" order by product_code, code_val, wild_card_ind, language desc");
        }
        Connection connection = null;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                stmt = connection.prepareStatement(strBuilder.toString());
                Utils.bindValueToPreparedStatement(stmt, bindValues);
                rset = stmt.executeQuery();
                String prevCodeValue = null;
                String codeValue = null;
                ListElement tempCodeData = null;
                while (rset.next()) {
                    prevCodeValue = codeValue;
                    codeValue = rset.getString(5);
                    if (considerProduct && prevCodeValue != null && prevCodeValue.equals(codeValue) || !"Y".equals(rset.getString(7))) continue;
                    tempCodeData = new ListElement();
                    tempCodeData.setData("short_desc", rset.getString(1));
                    tempCodeData.setData("long_desc", rset.getString(2));
                    tempCodeData.setData("brch_code", rset.getString(3));
                    tempCodeData.setData("product_code", rset.getString(4));
                    tempCodeData.setData("code_id", codeId);
                    tempCodeData.setData("code_val", codeValue);
                    tempCodeData.setData("company_id", rset.getString(6));
                    tempCodeData.setData("language", rset.getString(8));
                    tempCodeData.setData("preferred_order", rset.getInt(9));
                    result.add(tempCodeData);
                }
            }
            catch (Exception e) {
                LOG.error((Object)e.getMessage(), (Throwable)e);
                throw new GTPException(e);
            }
        }
        finally {
            Utils.closeResources(connection, stmt, rset);
        }
        return result;
    }

    public CodeData() {
        this.match = false;
        this.exactMatch = false;
        this.brch_code = null;
        this.company_id = null;
        this.product_code = null;
        this.code_id = null;
        this.code_val = null;
        this.short_desc = null;
        this.long_desc = null;
        this.language = "*";
        this.old_brch_code = null;
        this.old_company_id = null;
        this.old_product_code = null;
        this.old_code_val = null;
        this.old_language = null;
        this.inUse = false;
    }

    public CodeData(RunData data, String branchCode, String companyId, String productCode, String language, String codeId, String codeValue) throws GTPException {
        this.match = false;
        this.exactMatch = false;
        this.brch_code = null;
        this.company_id = null;
        this.product_code = null;
        this.code_id = null;
        this.code_val = null;
        this.short_desc = null;
        this.long_desc = null;
        this.language = "*";
        this.old_brch_code = null;
        this.old_company_id = null;
        this.old_product_code = null;
        this.old_code_val = null;
        this.old_language = null;
        this.inUse = false;
        if (branchCode == null || productCode == null || codeId == null || codeValue == null) {
            throw new GTPException(data, "The code parameters must not be null");
        }
        Statement stmt = null;
        ResultSet rset = null;
        StringBuilder buffer = new StringBuilder();
        buffer.append("select short_desc, long_desc ,").append(" brch_code, product_code, in_use_flag, company_id, ").append(" code_id, code_val, language  ").append(" from gtp_code_data ").append(" where code_id = '").append(codeId).append("'").append(" and code_val = '").append(codeValue).append("'").append(" and brch_code in ('").append(branchCode).append("', '*')").append(" and company_id in ('").append(companyId).append("', '*')").append(" and product_code in ('").append(productCode).append("', '*')").append(" and language in ('").append(language).append("', '*')").append(" order by wild_card_ind desc");
        Connection connection = null;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                stmt = connection.createStatement();
                rset = stmt.executeQuery(buffer.toString());
                if (rset.next()) {
                    this.match = true;
                    this.setShort_desc(rset.getString(1));
                    this.setLong_desc(rset.getString(2));
                    this.setBrch_code(rset.getString(3));
                    this.setProduct_code(rset.getString(4));
                    this.setCompany_id(rset.getString(6));
                    this.setCode_id(rset.getString(7));
                    this.setCode_val(rset.getString(8));
                    this.setLanguage(rset.getString(9));
                    if ("Y".equals(rset.getString(5))) {
                        this.inUse = true;
                    }
                    this.exactMatch = this.brch_code.equals(this.getBrch_code()) && this.company_id.equals(this.getCompany_id()) && productCode.equals(this.getProduct_code()) && language.equals(this.getLanguage());
                }
            }
            catch (Exception e) {
                LOG.error((Object)("Unable to execute SQL query" + e.getMessage()), (Throwable)e);
                throw new GTPException(data, e.getMessage());
            }
        }
        finally {
            Utils.closeResources(connection, stmt, rset);
        }
    }

    public int compareTo(Object object) {
        if (object == null || !object.getClass().getName().equals(this.getClass().getName())) {
            throw new ClassCastException();
        }
        CodeData secondData = (CodeData)object;
        if (!this.getBrch_code().equals(secondData.getBrch_code())) {
            return this.getBrch_code().compareTo(secondData.getBrch_code());
        }
        if (!this.getCompany_id().equals(secondData.getCompany_id())) {
            return this.getCompany_id().compareTo(secondData.getCompany_id());
        }
        if (!this.getCode_id().equals(secondData.getCode_id())) {
            return this.getCode_id().compareTo(secondData.getCode_id());
        }
        if (!this.getCode_val().equals(secondData.getCode_val())) {
            return this.getCode_val().compareTo(secondData.getCode_val());
        }
        if (!this.getProduct_code().equals(secondData.getProduct_code())) {
            return this.getProduct_code().compareTo(secondData.getProduct_code());
        }
        if (!this.getShort_desc().equals(secondData.getShort_desc())) {
            return this.getShort_desc().compareTo(secondData.getShort_desc());
        }
        if (!this.getLong_desc().equals(secondData.getLong_desc())) {
            return this.getLong_desc().compareTo(secondData.getLong_desc());
        }
        return 0;
    }

    public void delete(RunData data) throws GTPException {
        GTPUser user = (GTPUser)data.getUser();
        if (this.brch_code == null || this.product_code == null || this.code_id == null || this.code_val == null) {
            throw new GTPException(data, "The code keys must not be null");
        }
        Statement stmt = null;
        StringBuilder buffer = new StringBuilder();
        Connection connection = null;
        buffer.append("delete from gtp_code_data  ").append(" where brch_code = '").append(this.getBrch_code()).append("' ").append(" and company_id = '").append("" + user.getCompanyId()).append("' ").append(" and product_code = '").append(this.getProduct_code()).append("' ").append(" and code_id = '").append(this.getCode_id()).append("' ").append(" and code_val = '").append(this.getCode_val()).append("' ").append(" and language = '").append(this.getLanguage()).append("'");
        try {
            try {
                connection = PoolBrokerService.getConnection();
                stmt = connection.createStatement();
                stmt.executeUpdate(buffer.toString());
            }
            catch (Exception e) {
                LOG.error((Object)e.getMessage(), (Throwable)e);
                throw new GTPException(data, e.getMessage());
            }
        }
        finally {
            Utils.closeResources(connection, stmt);
        }
    }

    public boolean equals(Object object) {
        if (this.getClass() != object.getClass()) {
            return false;
        }
        if (this.compareTo(object) == 0) {
            return true;
        }
        return false;
    }

    public String getBrch_code() {
        return this.brch_code;
    }

    public String getCode_id() {
        return this.code_id;
    }

    public String getCode_val() {
        return this.code_val;
    }

    public String getCompany_id() {
        return this.company_id;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getLong_desc() {
        return this.long_desc;
    }

    public String getOld_brch_code() {
        return this.old_brch_code;
    }

    public String getOld_code_val() {
        return this.old_code_val;
    }

    public String getOld_company_id() {
        return this.old_company_id;
    }

    public String getOld_language() {
        return this.old_language;
    }

    public String getOld_product_code() {
        return this.old_product_code;
    }

    public String getProduct_code() {
        return this.product_code;
    }

    public String getShort_desc() {
        return this.short_desc;
    }

    public int hashCode() {
        return this.getBrch_code().hashCode() * this.getCompany_id().hashCode() * this.getCode_id().hashCode() * this.getProduct_code().hashCode() * this.getCode_val().hashCode();
    }

    public boolean isInUse() {
        return this.inUse;
    }

    public void save(RunData data) throws GTPException {
        block14 : {
            if (this.brch_code == null || this.product_code == null || this.company_id == null || this.language == null || this.code_id == null || this.code_val == null) {
                throw new GTPException(data, "The code keys must not be null");
            }
            if (this.getOld_code_val() != null) {
                CodeData deleteCodeData = new CodeData(data, this.getOld_brch_code(), this.getOld_company_id(), this.getOld_product_code(), this.getOld_language(), this.getCode_id(), this.getOld_code_val());
                if (deleteCodeData.exactMatch) {
                    deleteCodeData.delete(data);
                }
            }
            CodeData codeData = new CodeData(data, this.getBrch_code(), this.getCompany_id(), this.getProduct_code(), this.getLanguage(), this.getCode_id(), this.getCode_val());
            Statement stmt = null;
            StringBuilder buffer = new StringBuilder();
            Connection connection = null;
            String wildcardIndicator = "";
            wildcardIndicator = "*".equals(this.getBrch_code()) ? String.valueOf(wildcardIndicator) + '0' : String.valueOf(wildcardIndicator) + '1';
            wildcardIndicator = "*".equals(this.getCompany_id()) ? String.valueOf(wildcardIndicator) + '0' : String.valueOf(wildcardIndicator) + '1';
            wildcardIndicator = "*".equals(this.getProduct_code()) ? String.valueOf(wildcardIndicator) + '0' : String.valueOf(wildcardIndicator) + '1';
            wildcardIndicator = "*".equals(this.getLanguage()) ? String.valueOf(wildcardIndicator) + '0' : String.valueOf(wildcardIndicator) + '1';
            if (codeData.exactMatch) {
                buffer.append("update gtp_code_data  ");
                buffer.append(" set short_desc = '").append(this.getShort_desc()).append("', ");
                buffer.append(" long_desc = '").append(this.getLong_desc()).append("', ");
                buffer.append(" in_use_flag = '").append(this.inUse ? "Y" : "N").append("', ");
                buffer.append(" where brch_code = '").append(this.getBrch_code()).append("', ");
                buffer.append(" and company_id = '").append(this.getCompany_id()).append("', ");
                buffer.append(" and product_code = '").append(this.getProduct_code()).append("', ");
                buffer.append(" and code_id = '").append(this.getCode_id()).append("', ");
                buffer.append(" and code_val = '").append(this.getCode_val()).append("', ");
                buffer.append(" and language = '").append(this.getLanguage()).append("'");
                try {
                    try {
                        connection = PoolBrokerService.getConnection();
                        stmt = connection.createStatement();
                        stmt.executeUpdate(buffer.toString());
                        break block14;
                    }
                    catch (Exception e) {
                        LOG.error((Object)e.getMessage(), (Throwable)e);
                        throw new GTPException(data, e.getMessage());
                    }
                }
                finally {
                    Utils.closeResources(connection, stmt);
                }
            }
            buffer.append("insert into gtp_code_data ( ").append(" brch_code, company_id, product_code, language, code_id, code_val, ").append(" wild_card_ind, short_desc, long_desc, in_use_flag) ").append(" values (").append("'").append(this.getBrch_code()).append("', ").append("'").append(this.getCompany_id()).append("', ").append("'").append(this.getProduct_code()).append("', ").append("'").append(this.getLanguage()).append("', ").append("'").append(this.getCode_id()).append("', ").append("'").append(this.getCode_val()).append("', ").append("'").append(wildcardIndicator).append("', ").append("'").append(this.getShort_desc()).append("', ").append("'").append(this.getLong_desc()).append("', ").append("'").append(this.inUse ? "Y" : "N").append("'").append(")");
            try {
                try {
                    connection = PoolBrokerService.getConnection();
                    stmt = connection.createStatement();
                    stmt.executeUpdate(buffer.toString());
                }
                catch (Exception e) {
                    LOG.error((Object)e.getMessage(), (Throwable)e);
                    throw new GTPException(data, e.getMessage());
                }
            }
            finally {
                Utils.closeResources(connection, stmt);
            }
        }
    }

    public void setBrch_code(String brch_code) {
        this.brch_code = brch_code;
    }

    public void setCode_id(String code_id) {
        this.code_id = code_id;
    }

    public void setCode_val(String code_val) {
        this.code_val = code_val;
    }

    public void setCompany_id(int company_id) {
        this.company_id = "" + company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setLong_desc(String long_desc) {
        this.long_desc = long_desc;
    }

    public void setOld_brch_code(String old_brch_code) {
        this.old_brch_code = old_brch_code;
    }

    public void setOld_code_val(String old_code_val) {
        this.old_code_val = old_code_val;
    }

    public void setOld_company_id(int old_company_id) {
        this.old_company_id = "" + old_company_id;
    }

    public void setOld_company_id(String old_company_id) {
        this.old_company_id = old_company_id;
    }

    public void setOld_language(String old_language) {
        this.old_language = old_language;
    }

    public void setOld_product_code(String old_product_code) {
        this.old_product_code = old_product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    public String toXML(RunData data, String rootNodeNameValue) {
        StringBuilder xmlString = new StringBuilder();
        String rootNodeName = rootNodeNameValue != null ? rootNodeNameValue : "code_data";
        xmlString.append("<").append(rootNodeName).append(">");
        xmlString.append("<brch_code>").append(SecurityUtils.encodeHTML("00001")).append("</brch_code>");
        if (this.getCode_id() != null) {
            xmlString.append("<code_id>").append(SecurityUtils.encodeHTML(this.getCode_id())).append("</code_id>");
        } else {
            xmlString.append("<code_id/>");
        }
        if (this.getCompany_id() != null) {
            xmlString.append("<company_id>").append(SecurityUtils.encodeHTML(this.getCompany_id())).append("</company_id>");
        } else {
            xmlString.append("<company_id/>");
        }
        if (this.getCode_val() != null) {
            xmlString.append("<code_val>").append(SecurityUtils.encodeHTML(this.getCode_val())).append("</code_val>");
        } else {
            xmlString.append("<code_val/>");
        }
        if (this.getLong_desc() != null) {
            xmlString.append("<long_desc>").append(SecurityUtils.encodeHTML(this.getLong_desc())).append("</long_desc>");
        } else {
            xmlString.append("<long_desc/>");
        }
        if (this.getShort_desc() != null) {
            xmlString.append("<short_desc>").append(SecurityUtils.encodeHTML(this.getShort_desc())).append("</short_desc>");
        } else {
            xmlString.append("<short_desc/>");
        }
        if (this.getProduct_code() != null) {
            xmlString.append("<product_code>").append(SecurityUtils.encodeHTML(this.getProduct_code())).append("</product_code>");
        } else {
            xmlString.append("<product_code/>");
        }
        if (this.getLanguage() != null) {
            xmlString.append("<language>").append(SecurityUtils.encodeHTML(this.getLanguage())).append("</language>");
        } else {
            xmlString.append("<language/>");
        }
        if (this.inUse) {
            xmlString.append("<in_use>").append("Y").append("</in_use>");
        } else {
            xmlString.append("<in_use>").append("N").append("</in_use>");
        }
        xmlString.append("</").append(rootNodeName).append(">");
        return xmlString.toString();
    }
}


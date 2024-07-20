/*
 * Decompiled with CFR 0_116.
 * 
 * Could not load the following classes:
 *  org.apache.turbine.om.security.User
 *  org.apache.turbine.util.RunData
 */
package com.misys.portal.common.tools;

import com.misys.portal.common.tools.ParameterKey;
import com.misys.portal.common.tools.SecurityUtils;
import com.misys.portal.common.tools.Utils;
import com.misys.portal.common.tracer.Auditable;
import com.misys.portal.common.tracer.GTPException;
import com.misys.portal.security.GTPCompany;
import com.misys.portal.security.GTPSecurity;
import com.misys.portal.security.GTPUser;
import com.misys.portal.security.util.CompanySet;
import com.misys.portal.services.db.PoolBrokerService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.RunData;

public class ParameterData
implements Auditable {
    public static final String PARAMETER_DATA_1 = "DATA_1";
    public static final String PARAMETER_DATA_2 = "DATA_2";
    public static final String PARAMETER_DATA_3 = "DATA_3";
    public static final String PARAMETER_DATA_4 = "DATA_4";
    public static final String PARAMETER_DATA_5 = "DATA_5";
    public static final String PARAMETER_DATA_6 = "DATA_6";
    public static final String PARAMETER_DATA_7 = "DATA_7";
    public static final String PARAMETER_DATA_8 = "DATA_8";
    public static final String PARAMETER_DATA_9 = "DATA_9";
    public static final String PARAMETER_DATA_10 = "DATA_10";
    public static final String PARAMETER_DATA_11 = "DATA_11";
    public static final String PARAMETER_DATA_12 = "DATA_12";
    public static final String PARAMETER_DATA_13 = "DATA_13";
    public static final String PARAMETER_DATA_14 = "DATA_14";
    public static final String PARAMETER_DATA_15 = "DATA_15";
    public static final String PARAMETER_DATA_16 = "DATA_16";
    public static final String PARAMETER_DATA_17 = "DATA_17";
    public static final String PARAMETER_DATA_18 = "DATA_18";
    public static final String PARAMETER_DATA_19 = "DATA_19";
    public static final String PARAMETER_DATA_20 = "DATA_20";
    public static final String DATA_NOT_SET = "**";
    public boolean match = false;
    public boolean exactMatch = false;
    private ParameterKey keys = new ParameterKey();
    private ParameterKey oldKeys = null;
    private final Hashtable parameters = new Hashtable();

    public static void delete(ParameterKey keys) throws GTPException {
        ParameterData.delete(null, keys);
    }

    public static void delete(RunData data, ParameterKey keys) throws GTPException {
        PreparedStatement pstmt = null;
        ArrayList<String> bindValues = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        builder.append("delete ");
        builder.append(" from gtp_param_data ");
        builder.append(" where parm_id = ? ");
        builder.append(" and brch_code = ? ");
        builder.append(" and company_id = ? ");
        bindValues.add(keys.getParm_id());
        bindValues.add(keys.getBrch_code());
        bindValues.add(keys.getCompany_id());
        if (!"**".equals(keys.getKey("KEY_1"))) {
            builder.append(" and key_1 = ? ");
            bindValues.add(keys.getKey("KEY_1"));
        }
        if (!"**".equals(keys.getKey("KEY_2"))) {
            builder.append(" and key_2 = ? ");
            bindValues.add(keys.getKey("KEY_2"));
        }
        if (!"**".equals(keys.getKey("KEY_3"))) {
            builder.append(" and key_3 = ? ");
            bindValues.add(keys.getKey("KEY_3"));
        }
        if (!"**".equals(keys.getKey("KEY_4"))) {
            builder.append(" and key_4 = ? ");
            bindValues.add(keys.getKey("KEY_4"));
        }
        if (!"**".equals(keys.getKey("KEY_5"))) {
            builder.append(" and key_5 = ? ");
            bindValues.add(keys.getKey("KEY_5"));
        }
        if (!"**".equals(keys.getKey("KEY_6"))) {
            builder.append(" and key_6 = ? ");
            bindValues.add(keys.getKey("KEY_6"));
        }
        if (!"**".equals(keys.getKey("KEY_7"))) {
            builder.append(" and key_7 = ? ");
            bindValues.add(keys.getKey("KEY_7"));
        }
        if (!"**".equals(keys.getKey("KEY_8"))) {
            builder.append(" and key_8 = ? ");
            bindValues.add(keys.getKey("KEY_8"));
        }
        if (!"**".equals(keys.getKey("KEY_9"))) {
            builder.append(" and key_9 = ? ");
            bindValues.add(keys.getKey("KEY_9"));
        }
        if (!"**".equals(keys.getKey("KEY_10"))) {
            builder.append(" and key_10 = ? ");
            bindValues.add(keys.getKey("KEY_10"));
        }
        if (!"**".equals(keys.getKey("KEY_11"))) {
            builder.append(" and key_11 = ? ");
            bindValues.add(keys.getKey("KEY_11"));
        }
        if (!"**".equals(keys.getKey("KEY_12"))) {
            builder.append(" and key_12 = ? ");
            bindValues.add(keys.getKey("KEY_12"));
        }
        if (!"**".equals(keys.getKey("KEY_13"))) {
            builder.append(" and key_13 = ? ");
            bindValues.add(keys.getKey("KEY_13"));
        }
        if (!"**".equals(keys.getKey("KEY_14"))) {
            builder.append(" and key_14 = ? ");
            bindValues.add(keys.getKey("KEY_14"));
        }
        if (!"**".equals(keys.getKey("KEY_15"))) {
            builder.append(" and key_15 = ? ");
            bindValues.add(keys.getKey("KEY_15"));
        }
        if (!"**".equals(keys.getKey("KEY_16"))) {
            builder.append(" and key_16 = ? ");
            bindValues.add(keys.getKey("KEY_16"));
        }
        if (!"**".equals(keys.getKey("KEY_17"))) {
            builder.append(" and key_17 = ? ");
            bindValues.add(keys.getKey("KEY_17"));
        }
        if (!"**".equals(keys.getKey("KEY_18"))) {
            builder.append(" and key_18 = ? ");
            bindValues.add(keys.getKey("KEY_18"));
        }
        if (!"**".equals(keys.getKey("KEY_19"))) {
            builder.append(" and key_19 = ? ");
            bindValues.add(keys.getKey("KEY_19"));
        }
        if (!"**".equals(keys.getKey("KEY_20"))) {
            builder.append(" and key_20 = ? ");
            bindValues.add(keys.getKey("KEY_20"));
        }
        Connection connection = null;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                pstmt = connection.prepareStatement(builder.toString());
                Utils.bindValueToPreparedStatement((PreparedStatement)pstmt, bindValues);
                pstmt.executeUpdate();
            }
            catch (Exception e) {
                throw new GTPException(null, e.getMessage());
            }
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception exc) {
                    GTPException.log("Error in closing resource", exc);
                }
            }
            try {
                PoolBrokerService.releaseConnection(connection);
            }
            catch (Exception exc) {
                GTPException.log("Error in closing resource", exc);
            }
        }
    }

    public static List<ParameterData> retrieveAllParameterSet(ParameterKey keys) throws GTPException {
        Vector<ParameterData> result;
        result = new Vector<ParameterData>();
        ParameterData tempParamData = null;
        PreparedStatement pstmt = null;
        ArrayList<String> bindValues = new ArrayList<String>();
        ResultSet rset = null;
        StringBuilder builder = new StringBuilder();
        builder.append("select data_1, data_2, data_3, data_4, data_5, data_6,");
        builder.append(" data_7, data_8, data_9, data_10, data_11,");
        builder.append(" data_12, data_13, data_14, data_15, data_16,");
        builder.append(" data_17, data_18, data_19, data_20, wild_card_ind,");
        builder.append(" key_1, key_2, key_3, key_4, key_5,");
        builder.append(" key_6, key_7, key_8, key_9, key_10, ");
        builder.append(" key_11, key_12, key_13, key_14, key_15,");
        builder.append(" key_16, key_17, key_18, key_19, key_20, ");
        builder.append(" brch_code, company_id, parm_id ");
        builder.append(" from ");
        builder.append("GTP_PARAM_DATA");
        builder.append(" where parm_id = ?");
        builder.append(" and brch_code in (?,'*')");
        builder.append(" and company_id in (?,'*')");
        bindValues.add(keys.getParm_id());
        bindValues.add(keys.getBrch_code());
        bindValues.add(keys.getCompany_id());
        if (!"**".equals(keys.getKey("KEY_1"))) {
            builder.append(" and key_1 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_1"));
        }
        if (!"**".equals(keys.getKey("KEY_2"))) {
            builder.append(" and key_2 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_2"));
        }
        if (!"**".equals(keys.getKey("KEY_3"))) {
            builder.append(" and key_3 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_3"));
        }
        if (!"**".equals(keys.getKey("KEY_4"))) {
            builder.append(" and key_4 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_4"));
        }
        if (!"**".equals(keys.getKey("KEY_5"))) {
            builder.append(" and key_5 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_5"));
        }
        if (!"**".equals(keys.getKey("KEY_6"))) {
            builder.append(" and key_6 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_6"));
        }
        if (!"**".equals(keys.getKey("KEY_7"))) {
            builder.append(" and key_7 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_7"));
        }
        if (!"**".equals(keys.getKey("KEY_8"))) {
            builder.append(" and key_8 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_8"));
        }
        if (!"**".equals(keys.getKey("KEY_9"))) {
            builder.append(" and key_9 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_9"));
        }
        if (!"**".equals(keys.getKey("KEY_10"))) {
            builder.append(" and key_10 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_10"));
        }
        if (!"**".equals(keys.getKey("KEY_11"))) {
            builder.append(" and key_11 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_11"));
        }
        if (!"**".equals(keys.getKey("KEY_12"))) {
            builder.append(" and key_12 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_12"));
        }
        if (!"**".equals(keys.getKey("KEY_13"))) {
            builder.append(" and key_13 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_13"));
        }
        if (!"**".equals(keys.getKey("KEY_14"))) {
            builder.append(" and key_14 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_14"));
        }
        if (!"**".equals(keys.getKey("KEY_15"))) {
            builder.append(" and key_15 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_15"));
        }
        if (!"**".equals(keys.getKey("KEY_16"))) {
            builder.append(" and key_16 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_16"));
        }
        if (!"**".equals(keys.getKey("KEY_17"))) {
            builder.append(" and key_17 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_17"));
        }
        if (!"**".equals(keys.getKey("KEY_18"))) {
            builder.append(" and key_18 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_18"));
        }
        if (!"**".equals(keys.getKey("KEY_19"))) {
            builder.append(" and key_19 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_19"));
        }
        if (!"**".equals(keys.getKey("KEY_20"))) {
            builder.append(" and key_20 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_20"));
        }
        builder.append(" order by wild_card_ind desc");
        Connection connection = null;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                pstmt = connection.prepareStatement(builder.toString());
                Utils.bindValueToPreparedStatement((PreparedStatement)pstmt, bindValues);
                rset = pstmt.executeQuery();
                while (rset.next()) {
                    tempParamData = new ParameterData();
                    tempParamData.match = true;
                    tempParamData.putData("DATA_1", rset.getString("DATA_1"));
                    tempParamData.putData("DATA_2", rset.getString("DATA_2"));
                    tempParamData.putData("DATA_3", rset.getString("DATA_3"));
                    tempParamData.putData("DATA_4", rset.getString("DATA_4"));
                    tempParamData.putData("DATA_5", rset.getString("DATA_5"));
                    tempParamData.putData("DATA_6", rset.getString("DATA_6"));
                    tempParamData.putData("DATA_7", rset.getString("DATA_7"));
                    tempParamData.putData("DATA_8", rset.getString("DATA_8"));
                    tempParamData.putData("DATA_9", rset.getString("DATA_9"));
                    tempParamData.putData("DATA_10", rset.getString("DATA_10"));
                    tempParamData.putData("DATA_11", rset.getString("DATA_11"));
                    tempParamData.putData("DATA_12", rset.getString("DATA_12"));
                    tempParamData.putData("DATA_13", rset.getString("DATA_13"));
                    tempParamData.putData("DATA_14", rset.getString("DATA_14"));
                    tempParamData.putData("DATA_15", rset.getString("DATA_15"));
                    tempParamData.putData("DATA_16", rset.getString("DATA_16"));
                    tempParamData.putData("DATA_17", rset.getString("DATA_17"));
                    tempParamData.putData("DATA_18", rset.getString("DATA_18"));
                    tempParamData.putData("DATA_19", rset.getString("DATA_19"));
                    tempParamData.putData("DATA_20", rset.getString("DATA_20"));
                    tempParamData.getKeys().addKey("KEY_1", rset.getString("KEY_1"));
                    tempParamData.getKeys().addKey("KEY_2", rset.getString("KEY_2"));
                    tempParamData.getKeys().addKey("KEY_3", rset.getString("KEY_3"));
                    tempParamData.getKeys().addKey("KEY_4", rset.getString("KEY_4"));
                    tempParamData.getKeys().addKey("KEY_5", rset.getString("KEY_5"));
                    tempParamData.getKeys().addKey("KEY_6", rset.getString("KEY_6"));
                    tempParamData.getKeys().addKey("KEY_7", rset.getString("KEY_7"));
                    tempParamData.getKeys().addKey("KEY_8", rset.getString("KEY_8"));
                    tempParamData.getKeys().addKey("KEY_9", rset.getString("KEY_9"));
                    tempParamData.getKeys().addKey("KEY_10", rset.getString("KEY_10"));
                    tempParamData.getKeys().addKey("KEY_11", rset.getString("KEY_11"));
                    tempParamData.getKeys().addKey("KEY_12", rset.getString("KEY_12"));
                    tempParamData.getKeys().addKey("KEY_13", rset.getString("KEY_13"));
                    tempParamData.getKeys().addKey("KEY_14", rset.getString("KEY_14"));
                    tempParamData.getKeys().addKey("KEY_15", rset.getString("KEY_15"));
                    tempParamData.getKeys().addKey("KEY_16", rset.getString("KEY_16"));
                    tempParamData.getKeys().addKey("KEY_17", rset.getString("KEY_17"));
                    tempParamData.getKeys().addKey("KEY_18", rset.getString("KEY_18"));
                    tempParamData.getKeys().addKey("KEY_19", rset.getString("KEY_19"));
                    tempParamData.getKeys().addKey("KEY_20", rset.getString("KEY_20"));
                    tempParamData.getKeys().setBrch_code(rset.getString("BRCH_CODE"));
                    tempParamData.getKeys().setCompany_id(rset.getString("COMPANY_ID"));
                    tempParamData.getKeys().setParm_id(rset.getString("PARM_ID"));
                    result.add(tempParamData);
                }
                rset.close();
            }
            catch (Exception e) {
                throw new GTPException(null, e.getMessage());
            }
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception exc) {
                    GTPException.log("Error in closing resource", exc);
                }
            }
            try {
                PoolBrokerService.releaseConnection(connection);
            }
            catch (Exception exc) {
                GTPException.log("Error in closing resource", exc);
            }
        }
        return result;
    }

    public static Vector retrieveAllParameterSet(RunData data, ParameterKey keys) throws GTPException {
        Vector<ParameterData> result;
        result = new Vector<ParameterData>();
        ParameterData tempParamData = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        ArrayList<String> bindValues = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        builder.append("select data_1, data_2, data_3, data_4, data_5, data_6,");
        builder.append(" data_7, data_8, data_9, data_10, data_11,");
        builder.append(" data_12, data_13, data_14, data_15, data_16,");
        builder.append(" data_17, data_18, data_19, data_20, wild_card_ind,");
        builder.append(" key_1, key_2, key_3, key_4, key_5,");
        builder.append(" key_6, key_7, key_8, key_9, key_10, ");
        builder.append(" key_11, key_12, key_13, key_14, key_15,");
        builder.append(" key_16, key_17, key_18, key_19, key_20, ");
        builder.append(" brch_code, company_id, parm_id ");
        builder.append(" from ");
        builder.append("GTP_PARAM_DATA");
        builder.append(" where parm_id = ?");
        builder.append(" and brch_code in (?,'*')");
        builder.append(" and company_id in (?,'*')");
        bindValues.add(keys.getParm_id());
        bindValues.add(keys.getBrch_code());
        bindValues.add(keys.getCompany_id());
        if (!"**".equals(keys.getKey("KEY_1"))) {
            builder.append(" and key_1 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_1"));
        }
        if (!"**".equals(keys.getKey("KEY_2"))) {
            builder.append(" and key_2 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_2"));
        }
        if (!"**".equals(keys.getKey("KEY_3"))) {
            builder.append(" and key_3 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_3"));
        }
        if (!"**".equals(keys.getKey("KEY_4"))) {
            builder.append(" and key_4 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_4"));
        }
        if (!"**".equals(keys.getKey("KEY_5"))) {
            builder.append(" and key_5 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_5"));
        }
        if (!"**".equals(keys.getKey("KEY_6"))) {
            builder.append(" and key_6 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_6"));
        }
        if (!"**".equals(keys.getKey("KEY_7"))) {
            builder.append(" and key_7 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_7"));
        }
        if (!"**".equals(keys.getKey("KEY_8"))) {
            builder.append(" and key_8 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_8"));
        }
        if (!"**".equals(keys.getKey("KEY_9"))) {
            builder.append(" and key_9 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_9"));
        }
        if (!"**".equals(keys.getKey("KEY_10"))) {
            builder.append(" and key_10 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_10"));
        }
        if (!"**".equals(keys.getKey("KEY_11"))) {
            builder.append(" and key_11 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_11"));
        }
        if (!"**".equals(keys.getKey("KEY_12"))) {
            builder.append(" and key_12 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_12"));
        }
        if (!"**".equals(keys.getKey("KEY_13"))) {
            builder.append(" and key_13 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_13"));
        }
        if (!"**".equals(keys.getKey("KEY_14"))) {
            builder.append(" and key_14 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_14"));
        }
        if (!"**".equals(keys.getKey("KEY_15"))) {
            builder.append(" and key_15 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_15"));
        }
        if (!"**".equals(keys.getKey("KEY_16"))) {
            builder.append(" and key_16 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_16"));
        }
        if (!"**".equals(keys.getKey("KEY_17"))) {
            builder.append(" and key_17 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_17"));
        }
        if (!"**".equals(keys.getKey("KEY_18"))) {
            builder.append(" and key_18 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_18"));
        }
        if (!"**".equals(keys.getKey("KEY_19"))) {
            builder.append(" and key_19 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_19"));
        }
        if (!"**".equals(keys.getKey("KEY_20"))) {
            builder.append(" and key_20 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_20"));
        }
        builder.append(" order by wild_card_ind desc");
        Connection connection = null;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                pstmt = connection.prepareStatement(builder.toString());
                Utils.bindValueToPreparedStatement((PreparedStatement)pstmt, bindValues);
                rset = pstmt.executeQuery();
                while (rset.next()) {
                    tempParamData = new ParameterData();
                    tempParamData.match = true;
                    tempParamData.putData("DATA_1", rset.getString("DATA_1"));
                    tempParamData.putData("DATA_2", rset.getString("DATA_2"));
                    tempParamData.putData("DATA_3", rset.getString("DATA_3"));
                    tempParamData.putData("DATA_4", rset.getString("DATA_4"));
                    tempParamData.putData("DATA_5", rset.getString("DATA_5"));
                    tempParamData.putData("DATA_6", rset.getString("DATA_6"));
                    tempParamData.putData("DATA_7", rset.getString("DATA_7"));
                    tempParamData.putData("DATA_8", rset.getString("DATA_8"));
                    tempParamData.putData("DATA_9", rset.getString("DATA_9"));
                    tempParamData.putData("DATA_10", rset.getString("DATA_10"));
                    tempParamData.putData("DATA_11", rset.getString("DATA_11"));
                    tempParamData.putData("DATA_12", rset.getString("DATA_12"));
                    tempParamData.putData("DATA_13", rset.getString("DATA_13"));
                    tempParamData.putData("DATA_14", rset.getString("DATA_14"));
                    tempParamData.putData("DATA_15", rset.getString("DATA_15"));
                    tempParamData.putData("DATA_16", rset.getString("DATA_16"));
                    tempParamData.putData("DATA_17", rset.getString("DATA_17"));
                    tempParamData.putData("DATA_18", rset.getString("DATA_18"));
                    tempParamData.putData("DATA_19", rset.getString("DATA_19"));
                    tempParamData.putData("DATA_20", rset.getString("DATA_20"));
                    tempParamData.getKeys().addKey("KEY_1", rset.getString("KEY_1"));
                    tempParamData.getKeys().addKey("KEY_2", rset.getString("KEY_2"));
                    tempParamData.getKeys().addKey("KEY_3", rset.getString("KEY_3"));
                    tempParamData.getKeys().addKey("KEY_4", rset.getString("KEY_4"));
                    tempParamData.getKeys().addKey("KEY_5", rset.getString("KEY_5"));
                    tempParamData.getKeys().addKey("KEY_6", rset.getString("KEY_6"));
                    tempParamData.getKeys().addKey("KEY_7", rset.getString("KEY_7"));
                    tempParamData.getKeys().addKey("KEY_8", rset.getString("KEY_8"));
                    tempParamData.getKeys().addKey("KEY_9", rset.getString("KEY_9"));
                    tempParamData.getKeys().addKey("KEY_10", rset.getString("KEY_10"));
                    tempParamData.getKeys().addKey("KEY_11", rset.getString("KEY_11"));
                    tempParamData.getKeys().addKey("KEY_12", rset.getString("KEY_12"));
                    tempParamData.getKeys().addKey("KEY_13", rset.getString("KEY_13"));
                    tempParamData.getKeys().addKey("KEY_14", rset.getString("KEY_14"));
                    tempParamData.getKeys().addKey("KEY_15", rset.getString("KEY_15"));
                    tempParamData.getKeys().addKey("KEY_16", rset.getString("KEY_16"));
                    tempParamData.getKeys().addKey("KEY_17", rset.getString("KEY_17"));
                    tempParamData.getKeys().addKey("KEY_18", rset.getString("KEY_18"));
                    tempParamData.getKeys().addKey("KEY_19", rset.getString("KEY_19"));
                    tempParamData.getKeys().addKey("KEY_20", rset.getString("KEY_20"));
                    tempParamData.getKeys().setBrch_code(rset.getString("BRCH_CODE"));
                    tempParamData.getKeys().setCompany_id(rset.getString("COMPANY_ID"));
                    tempParamData.getKeys().setParm_id(rset.getString("PARM_ID"));
                    result.add(tempParamData);
                }
                rset.close();
            }
            catch (Exception e) {
                throw new GTPException(data, e.getMessage());
            }
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception exc) {
                    GTPException.log("Error in closing resource", exc);
                }
            }
            try {
                PoolBrokerService.releaseConnection(connection);
            }
            catch (Exception exc) {
                GTPException.log("Error in closing resource", exc);
            }
        }
        return result;
    }

    public static Vector retrieveAllParameterSet(RunData data, ParameterKey keys, String orderColumn) throws GTPException {
        Vector<ParameterData> result;
        result = new Vector<ParameterData>();
        ParameterData tempParamData = null;
        PreparedStatement pstmt = null;
        ArrayList<String> bindValues = new ArrayList<String>();
        ResultSet rset = null;
        StringBuilder builder = new StringBuilder();
        builder.append("select data_1, data_2, data_3, data_4, data_5, data_6,");
        builder.append(" data_7, data_8, data_9, data_10, data_11,");
        builder.append(" data_12, data_13, data_14, data_15, data_16,");
        builder.append(" data_17, data_18, data_19, data_20, wild_card_ind,");
        builder.append(" key_1, key_2, key_3, key_4, key_5,");
        builder.append(" key_6, key_7, key_8, key_9, key_10, ");
        builder.append(" key_11, key_12, key_13, key_14, key_15,");
        builder.append(" key_16, key_17, key_18, key_19, key_20, ");
        builder.append(" brch_code, company_id ");
        builder.append(" from ");
        builder.append("GTP_PARAM_DATA");
        builder.append(" where parm_id = ?");
        builder.append(" and brch_code in (?,'*')");
        builder.append(" and company_id in (?,'*')");
        bindValues.add(keys.getParm_id());
        bindValues.add(keys.getBrch_code());
        bindValues.add(keys.getCompany_id());
        if (!"**".equals(keys.getKey("KEY_1"))) {
            builder.append(" and key_1 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_1"));
        }
        if (!"**".equals(keys.getKey("KEY_2"))) {
            builder.append(" and key_2 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_2"));
        }
        if (!"**".equals(keys.getKey("KEY_3"))) {
            builder.append(" and key_3 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_3"));
        }
        if (!"**".equals(keys.getKey("KEY_4"))) {
            builder.append(" and key_4 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_4"));
        }
        if (!"**".equals(keys.getKey("KEY_5"))) {
            builder.append(" and key_5 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_5"));
        }
        if (!"**".equals(keys.getKey("KEY_6"))) {
            builder.append(" and key_6 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_6"));
        }
        if (!"**".equals(keys.getKey("KEY_7"))) {
            builder.append(" and key_7 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_7"));
        }
        if (!"**".equals(keys.getKey("KEY_8"))) {
            builder.append(" and key_8 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_8"));
        }
        if (!"**".equals(keys.getKey("KEY_9"))) {
            builder.append(" and key_9 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_9"));
        }
        if (!"**".equals(keys.getKey("KEY_10"))) {
            builder.append(" and key_10 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_10"));
        }
        if (!"**".equals(keys.getKey("KEY_11"))) {
            builder.append(" and key_11 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_11"));
        }
        if (!"**".equals(keys.getKey("KEY_12"))) {
            builder.append(" and key_12 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_12"));
        }
        if (!"**".equals(keys.getKey("KEY_13"))) {
            builder.append(" and key_13 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_13"));
        }
        if (!"**".equals(keys.getKey("KEY_14"))) {
            builder.append(" and key_14 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_14"));
        }
        if (!"**".equals(keys.getKey("KEY_15"))) {
            builder.append(" and key_15 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_15"));
        }
        if (!"**".equals(keys.getKey("KEY_16"))) {
            builder.append(" and key_16 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_16"));
        }
        if (!"**".equals(keys.getKey("KEY_17"))) {
            builder.append(" and key_17 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_17"));
        }
        if (!"**".equals(keys.getKey("KEY_18"))) {
            builder.append(" and key_18 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_18"));
        }
        if (!"**".equals(keys.getKey("KEY_19"))) {
            builder.append(" and key_19 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_19"));
        }
        if (!"**".equals(keys.getKey("KEY_20"))) {
            builder.append(" and key_20 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_20"));
        }
        builder.append(" order by ").append(orderColumn);
        Connection connection = null;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                pstmt = connection.prepareStatement(builder.toString());
                Utils.bindValueToPreparedStatement((PreparedStatement)pstmt, bindValues);
                rset = pstmt.executeQuery();
                while (rset.next()) {
                    tempParamData = new ParameterData();
                    tempParamData.match = true;
                    tempParamData.putData("DATA_1", rset.getString("DATA_1"));
                    tempParamData.putData("DATA_2", rset.getString("DATA_2"));
                    tempParamData.putData("DATA_3", rset.getString("DATA_3"));
                    tempParamData.putData("DATA_4", rset.getString("DATA_4"));
                    tempParamData.putData("DATA_5", rset.getString("DATA_5"));
                    tempParamData.putData("DATA_6", rset.getString("DATA_6"));
                    tempParamData.putData("DATA_7", rset.getString("DATA_7"));
                    tempParamData.putData("DATA_8", rset.getString("DATA_8"));
                    tempParamData.putData("DATA_9", rset.getString("DATA_9"));
                    tempParamData.putData("DATA_10", rset.getString("DATA_10"));
                    tempParamData.putData("DATA_11", rset.getString("DATA_11"));
                    tempParamData.putData("DATA_12", rset.getString("DATA_12"));
                    tempParamData.putData("DATA_13", rset.getString("DATA_13"));
                    tempParamData.putData("DATA_14", rset.getString("DATA_14"));
                    tempParamData.putData("DATA_15", rset.getString("DATA_15"));
                    tempParamData.putData("DATA_16", rset.getString("DATA_16"));
                    tempParamData.putData("DATA_17", rset.getString("DATA_17"));
                    tempParamData.putData("DATA_18", rset.getString("DATA_18"));
                    tempParamData.putData("DATA_19", rset.getString("DATA_19"));
                    tempParamData.putData("DATA_20", rset.getString("DATA_20"));
                    tempParamData.getKeys().addKey("KEY_1", rset.getString("KEY_1"));
                    tempParamData.getKeys().addKey("KEY_2", rset.getString("KEY_2"));
                    tempParamData.getKeys().addKey("KEY_3", rset.getString("KEY_3"));
                    tempParamData.getKeys().addKey("KEY_4", rset.getString("KEY_4"));
                    tempParamData.getKeys().addKey("KEY_5", rset.getString("KEY_5"));
                    tempParamData.getKeys().addKey("KEY_6", rset.getString("KEY_6"));
                    tempParamData.getKeys().addKey("KEY_7", rset.getString("KEY_7"));
                    tempParamData.getKeys().addKey("KEY_8", rset.getString("KEY_8"));
                    tempParamData.getKeys().addKey("KEY_9", rset.getString("KEY_9"));
                    tempParamData.getKeys().addKey("KEY_10", rset.getString("KEY_10"));
                    tempParamData.getKeys().setBrch_code(rset.getString("BRCH_CODE"));
                    tempParamData.getKeys().setCompany_id(rset.getString("COMPANY_ID"));
                    result.add(tempParamData);
                }
                rset.close();
            }
            catch (Exception e) {
                throw new GTPException(data, e.getMessage());
            }
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception exc) {
                    GTPException.log("Error in closing resource", exc);
                }
            }
            try {
                PoolBrokerService.releaseConnection(connection);
            }
            catch (Exception exc) {
                GTPException.log("Error in closing resource", exc);
            }
        }
        return result;
    }

    public static List<ParameterData> retrieveAnyColumnMatchParameterSet(RunData data, ParameterKey keys, String keyValue, String productCode) throws GTPException {
        ArrayList<ParameterData> result;
        result = new ArrayList<ParameterData>();
        ParameterData tempParamData = null;
        ParameterKey tmpKey = null;
        boolean tmpMatch = false;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        StringBuilder builder = new StringBuilder();
        builder.append("select data_1, data_2, data_3, data_4, data_5, data_6,");
        builder.append(" data_7, data_8, data_9, data_10, data_11,");
        builder.append(" data_12, data_13, data_14, data_15, data_16,");
        builder.append(" data_17, data_18, data_19, data_20, wild_card_ind,");
        builder.append(" key_1, key_2, key_3, key_4, key_5,");
        builder.append(" key_6, key_7, key_8, key_9, key_10, ");
        builder.append(" key_11, key_12, key_13, key_14, key_15,");
        builder.append(" key_16, key_17, key_18, key_19, key_20, ");
        builder.append(" brch_code, company_id ");
        builder.append(" from ");
        builder.append("GTP_PARAM_DATA");
        builder.append(" where parm_id = ?");
        builder.append(" and brch_code in (?,'*')");
        builder.append(" and company_id in (?,'*') and key_1 = ? and (");
        StringBuilder key = null;
        int i = 2;
        while (i <= 20) {
            key = new StringBuilder("key_").append(i);
            if (i != 2) {
                builder.append(" or ");
            }
            builder.append(key.toString()).append(" = ?");
            ++i;
        }
        builder.append(") order by wild_card_ind desc");
        Connection connection = null;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                pstmt = connection.prepareStatement(builder.toString());
                pstmt.setString(1, keys.getParm_id());
                pstmt.setString(2, keys.getBrch_code());
                pstmt.setString(3, keys.getCompany_id());
                pstmt.setString(4, productCode);
                int i2 = 5;
                while (i2 <= 23) {
                    pstmt.setString(i2, keyValue);
                    ++i2;
                }
                rset = pstmt.executeQuery();
                while (rset.next()) {
                    tempParamData = new ParameterData();
                    tempParamData.match = true;
                    tempParamData.putData("DATA_1", rset.getString("DATA_1"));
                    tempParamData.putData("DATA_2", rset.getString("DATA_2"));
                    tempParamData.putData("DATA_3", rset.getString("DATA_3"));
                    tempParamData.putData("DATA_4", rset.getString("DATA_4"));
                    tempParamData.putData("DATA_5", rset.getString("DATA_5"));
                    tempParamData.putData("DATA_6", rset.getString("DATA_6"));
                    tempParamData.putData("DATA_7", rset.getString("DATA_7"));
                    tempParamData.putData("DATA_8", rset.getString("DATA_8"));
                    tempParamData.putData("DATA_9", rset.getString("DATA_9"));
                    tempParamData.putData("DATA_10", rset.getString("DATA_10"));
                    tempParamData.putData("DATA_11", rset.getString("DATA_11"));
                    tempParamData.putData("DATA_12", rset.getString("DATA_12"));
                    tempParamData.putData("DATA_13", rset.getString("DATA_13"));
                    tempParamData.putData("DATA_14", rset.getString("DATA_14"));
                    tempParamData.putData("DATA_15", rset.getString("DATA_15"));
                    tempParamData.putData("DATA_16", rset.getString("DATA_16"));
                    tempParamData.putData("DATA_17", rset.getString("DATA_17"));
                    tempParamData.putData("DATA_18", rset.getString("DATA_18"));
                    tempParamData.putData("DATA_19", rset.getString("DATA_19"));
                    tempParamData.putData("DATA_20", rset.getString("DATA_20"));
                    tempParamData.getKeys().addKey("KEY_1", rset.getString("KEY_1"));
                    tempParamData.getKeys().addKey("KEY_2", rset.getString("KEY_2"));
                    tempParamData.getKeys().addKey("KEY_3", rset.getString("KEY_3"));
                    tempParamData.getKeys().addKey("KEY_4", rset.getString("KEY_4"));
                    tempParamData.getKeys().addKey("KEY_5", rset.getString("KEY_5"));
                    tempParamData.getKeys().addKey("KEY_6", rset.getString("KEY_6"));
                    tempParamData.getKeys().addKey("KEY_7", rset.getString("KEY_7"));
                    tempParamData.getKeys().addKey("KEY_8", rset.getString("KEY_8"));
                    tempParamData.getKeys().addKey("KEY_9", rset.getString("KEY_9"));
                    tempParamData.getKeys().addKey("KEY_10", rset.getString("KEY_10"));
                    tempParamData.getKeys().setBrch_code(rset.getString("BRCH_CODE"));
                    tempParamData.getKeys().setCompany_id(rset.getString("COMPANY_ID"));
                    tempParamData.getKeys().setParm_id(keys.getParm_id());
                    tmpMatch = false;
                    if (tempParamData.getKeys().hasWildcard()) {
                        Iterator<ParameterData> iter = result.iterator();
                        while (iter.hasNext()) {
                            tmpKey = iter.next().getKeys();
                            if (!tempParamData.getKeys().matches(tmpKey)) continue;
                            tmpMatch = true;
                        }
                    }
                    if (tmpMatch) continue;
                    result.add(tempParamData);
                }
                rset.close();
            }
            catch (Exception e) {
                throw new GTPException(data, e.getMessage());
            }
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception exc) {
                    GTPException.log("Error in closing resource", exc);
                }
            }
            try {
                PoolBrokerService.releaseConnection(connection);
            }
            catch (Exception exc) {
                GTPException.log("Error in closing resource", exc);
            }
        }
        return result;
    }

    public static List retrieveBestMatchParameterSet(ParameterKey keys) throws GTPException {
        return ParameterData.retrieveBestMatchParameterSet(null, keys);
    }

    public static List retrieveBestMatchParameterSet(RunData data, ParameterKey keys) throws GTPException {
        ArrayList<ParameterData> result;
        result = new ArrayList<ParameterData>();
        ParameterData tempParamData = null;
        ParameterKey tmpKey = null;
        boolean tmpMatch = false;
        PreparedStatement pstmt = null;
        ArrayList<String> bindValues = new ArrayList<String>();
        ResultSet rset = null;
        StringBuilder builder = new StringBuilder();
        builder.append("select data_1, data_2, data_3, data_4, data_5, data_6,");
        builder.append(" data_7, data_8, data_9, data_10, data_11,");
        builder.append(" data_12, data_13, data_14, data_15, data_16,");
        builder.append(" data_17, data_18, data_19, data_20, wild_card_ind,");
        builder.append(" key_1, key_2, key_3, key_4, key_5,");
        builder.append(" key_6, key_7, key_8, key_9, key_10, ");
        builder.append(" key_11, key_12, key_13, key_14, key_15,");
        builder.append(" key_16, key_17, key_18, key_19, key_20, ");
        builder.append(" brch_code, company_id ");
        builder.append(" from ");
        builder.append("GTP_PARAM_DATA");
        builder.append(" where parm_id = ?");
        builder.append(" and brch_code in (?,'*')");
        builder.append(" and company_id in (?,'*')");
        bindValues.add(keys.getParm_id());
        bindValues.add(keys.getBrch_code());
        bindValues.add(keys.getCompany_id());
        if (!"**".equals(keys.getKey("KEY_1"))) {
            builder.append(" and key_1 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_1"));
        }
        if (!"**".equals(keys.getKey("KEY_2"))) {
            builder.append(" and key_2 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_2"));
        }
        if (!"**".equals(keys.getKey("KEY_3"))) {
            builder.append(" and key_3 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_3"));
        }
        if (!"**".equals(keys.getKey("KEY_4"))) {
            builder.append(" and key_4 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_4"));
        }
        if (!"**".equals(keys.getKey("KEY_5"))) {
            builder.append(" and key_5 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_5"));
        }
        if (!"**".equals(keys.getKey("KEY_6"))) {
            builder.append(" and key_6 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_6"));
        }
        if (!"**".equals(keys.getKey("KEY_7"))) {
            builder.append(" and key_7 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_7"));
        }
        if (!"**".equals(keys.getKey("KEY_8"))) {
            builder.append(" and key_8 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_8"));
        }
        if (!"**".equals(keys.getKey("KEY_9"))) {
            builder.append(" and key_9 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_9"));
        }
        if (!"**".equals(keys.getKey("KEY_10"))) {
            builder.append(" and key_10 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_10"));
        }
        if (!"**".equals(keys.getKey("KEY_11"))) {
            builder.append(" and key_11 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_11"));
        }
        if (!"**".equals(keys.getKey("KEY_12"))) {
            builder.append(" and key_12 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_12"));
        }
        if (!"**".equals(keys.getKey("KEY_13"))) {
            builder.append(" and key_13 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_13"));
        }
        if (!"**".equals(keys.getKey("KEY_14"))) {
            builder.append(" and key_14 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_14"));
        }
        if (!"**".equals(keys.getKey("KEY_15"))) {
            builder.append(" and key_15 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_15"));
        }
        if (!"**".equals(keys.getKey("KEY_16"))) {
            builder.append(" and key_16 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_16"));
        }
        if (!"**".equals(keys.getKey("KEY_17"))) {
            builder.append(" and key_17 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_17"));
        }
        if (!"**".equals(keys.getKey("KEY_18"))) {
            builder.append(" and key_18 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_18"));
        }
        if (!"**".equals(keys.getKey("KEY_19"))) {
            builder.append(" and key_19 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_19"));
        }
        if (!"**".equals(keys.getKey("KEY_20"))) {
            builder.append(" and key_20 in (?,'*','**')");
            bindValues.add(keys.getKey("KEY_20"));
        }
        builder.append(" order by wild_card_ind desc");
        Connection connection = null;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                pstmt = connection.prepareStatement(builder.toString());
                Utils.bindValueToPreparedStatement((PreparedStatement)pstmt, bindValues);
                rset = pstmt.executeQuery();
                while (rset.next()) {
                    tempParamData = new ParameterData();
                    tempParamData.match = true;
                    tempParamData.putData("DATA_1", rset.getString("DATA_1"));
                    tempParamData.putData("DATA_2", rset.getString("DATA_2"));
                    tempParamData.putData("DATA_3", rset.getString("DATA_3"));
                    tempParamData.putData("DATA_4", rset.getString("DATA_4"));
                    tempParamData.putData("DATA_5", rset.getString("DATA_5"));
                    tempParamData.putData("DATA_6", rset.getString("DATA_6"));
                    tempParamData.putData("DATA_7", rset.getString("DATA_7"));
                    tempParamData.putData("DATA_8", rset.getString("DATA_8"));
                    tempParamData.putData("DATA_9", rset.getString("DATA_9"));
                    tempParamData.putData("DATA_10", rset.getString("DATA_10"));
                    tempParamData.putData("DATA_11", rset.getString("DATA_11"));
                    tempParamData.putData("DATA_12", rset.getString("DATA_12"));
                    tempParamData.putData("DATA_13", rset.getString("DATA_13"));
                    tempParamData.putData("DATA_14", rset.getString("DATA_14"));
                    tempParamData.putData("DATA_15", rset.getString("DATA_15"));
                    tempParamData.putData("DATA_16", rset.getString("DATA_16"));
                    tempParamData.putData("DATA_17", rset.getString("DATA_17"));
                    tempParamData.putData("DATA_18", rset.getString("DATA_18"));
                    tempParamData.putData("DATA_19", rset.getString("DATA_19"));
                    tempParamData.putData("DATA_20", rset.getString("DATA_20"));
                    tempParamData.getKeys().addKey("KEY_1", rset.getString("KEY_1"));
                    tempParamData.getKeys().addKey("KEY_2", rset.getString("KEY_2"));
                    tempParamData.getKeys().addKey("KEY_3", rset.getString("KEY_3"));
                    tempParamData.getKeys().addKey("KEY_4", rset.getString("KEY_4"));
                    tempParamData.getKeys().addKey("KEY_5", rset.getString("KEY_5"));
                    tempParamData.getKeys().addKey("KEY_6", rset.getString("KEY_6"));
                    tempParamData.getKeys().addKey("KEY_7", rset.getString("KEY_7"));
                    tempParamData.getKeys().addKey("KEY_8", rset.getString("KEY_8"));
                    tempParamData.getKeys().addKey("KEY_9", rset.getString("KEY_9"));
                    tempParamData.getKeys().addKey("KEY_10", rset.getString("KEY_10"));
                    tempParamData.getKeys().setBrch_code(rset.getString("BRCH_CODE"));
                    tempParamData.getKeys().setCompany_id(rset.getString("COMPANY_ID"));
                    tempParamData.getKeys().setParm_id(keys.getParm_id());
                    tmpMatch = false;
                    if (tempParamData.getKeys().hasWildcard()) {
                        Iterator iter = result.iterator();
                        while (iter.hasNext()) {
                            tmpKey = ((ParameterData)iter.next()).getKeys();
                            if (!tempParamData.getKeys().matches(tmpKey)) continue;
                            tmpMatch = true;
                        }
                    }
                    if (tmpMatch) continue;
                    result.add(tempParamData);
                }
                rset.close();
            }
            catch (Exception e) {
                throw new GTPException(data, e.getMessage());
            }
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception exc) {
                    GTPException.log("Error in closing resource", exc);
                }
            }
            try {
                PoolBrokerService.releaseConnection(connection);
            }
            catch (Exception exc) {
                GTPException.log("Error in closing resource", exc);
            }
        }
        return result;
    }

    public static List<ParameterData> retrieveSpecificColumnMatchParameterSet(ParameterKey keys, CompanySet compSet, RunData data) throws GTPException {
        ArrayList<ParameterData> result;
        result = new ArrayList<ParameterData>();
        ParameterData tempParamData = null;
        PreparedStatement pstmt = null;
        ArrayList<String> bindValues = new ArrayList<String>();
        ResultSet rset = null;
        StringBuilder builder = new StringBuilder();
        builder.append("select data_1, data_2, data_3, data_4, data_5, data_6,");
        builder.append(" data_7, data_8, data_9, data_10, data_11,");
        builder.append(" data_12, data_13, data_14, data_15, data_16,");
        builder.append(" data_17, data_18, data_19, data_20, wild_card_ind,");
        builder.append(" key_1, key_2, key_3, key_4, key_5,");
        builder.append(" key_6, key_7, key_8, key_9, key_10, ");
        builder.append(" key_11, key_12, key_13, key_14, key_15,");
        builder.append(" key_16, key_17, key_18, key_19, key_20, ");
        builder.append(" brch_code, company_id, parm_id ");
        builder.append(" from ");
        builder.append("GTP_PARAM_DATA");
        builder.append(" where parm_id = ?");
        builder.append(" and brch_code in (?,'*')");
        builder.append(" and company_id in (?,'*')");
        bindValues.add(keys.getParm_id());
        bindValues.add(keys.getBrch_code());
        bindValues.add(keys.getCompany_id());
        if (!"**".equals(keys.getKey("KEY_1"))) {
            builder.append(" and key_1 =?");
            bindValues.add(keys.getKey("KEY_1"));
        }
        if (!"**".equals(keys.getKey("KEY_2"))) {
            List customerBanks = compSet.getCompanies();
            if (!customerBanks.isEmpty()) {
                builder.append(" and key_2 in ( ");
                Iterator iter = compSet.elements();
                while (iter.hasNext()) {
                    GTPCompany comp = (GTPCompany)iter.next();
                    builder.append("'" + comp.getAbbv_name() + "'");
                    if (!iter.hasNext()) continue;
                    builder.append(",");
                }
                if (!GTPSecurity.isGroupCustomer((GTPUser)data.getUser()) && !GTPSecurity.isBank(data)) {
                    builder.append(",'*'");
                }
                builder.append(" )");
            } else {
                builder.append(" and key_2 in ('**',?)");
                bindValues.add(keys.getKey("KEY_2"));
            }
        }
        if (!"**".equals(keys.getKey("KEY_3"))) {
            builder.append(" and key_3 =?");
            bindValues.add(keys.getKey("KEY_3"));
        }
        if (!"**".equals(keys.getKey("KEY_4"))) {
            builder.append(" and key_4 =?");
            bindValues.add(keys.getKey("KEY_4"));
        }
        if (!"**".equals(keys.getKey("KEY_5"))) {
            builder.append(" and key_5 =?");
            bindValues.add(keys.getKey("KEY_5"));
        }
        if (!"**".equals(keys.getKey("KEY_6"))) {
            builder.append(" and key_6 =?");
            bindValues.add(keys.getKey("KEY_6"));
        }
        if (!"**".equals(keys.getKey("KEY_7"))) {
            builder.append(" and key_7 =?");
            bindValues.add(keys.getKey("KEY_7"));
        }
        if (!"**".equals(keys.getKey("KEY_8"))) {
            builder.append(" and key_8 =?");
            bindValues.add(keys.getKey("KEY_8"));
        }
        if (!"**".equals(keys.getKey("KEY_9"))) {
            builder.append(" and key_9 =?");
            bindValues.add(keys.getKey("KEY_9"));
        }
        if (!"**".equals(keys.getKey("KEY_10"))) {
            builder.append(" and key_10 =?");
            bindValues.add(keys.getKey("KEY_10"));
        }
        if (!"**".equals(keys.getKey("KEY_11"))) {
            builder.append(" and key_11 =?");
            bindValues.add(keys.getKey("KEY_11"));
        }
        if (!"**".equals(keys.getKey("KEY_12"))) {
            builder.append(" and key_12 =?");
            bindValues.add(keys.getKey("KEY_12"));
        }
        if (!"**".equals(keys.getKey("KEY_13"))) {
            builder.append(" and key_13 =?");
            bindValues.add(keys.getKey("KEY_13"));
        }
        if (!"**".equals(keys.getKey("KEY_14"))) {
            builder.append(" and key_14 =?");
            bindValues.add(keys.getKey("KEY_14"));
        }
        if (!"**".equals(keys.getKey("KEY_15"))) {
            builder.append(" and key_15 =?");
            bindValues.add(keys.getKey("KEY_15"));
        }
        if (!"**".equals(keys.getKey("KEY_16"))) {
            builder.append(" and key_16 =?");
            bindValues.add(keys.getKey("KEY_16"));
        }
        if (!"**".equals(keys.getKey("KEY_17"))) {
            builder.append(" and key_17 =?");
            bindValues.add(keys.getKey("KEY_17"));
        }
        if (!"**".equals(keys.getKey("KEY_18"))) {
            builder.append(" and key_18 =?");
            bindValues.add(keys.getKey("KEY_18"));
        }
        if (!"**".equals(keys.getKey("KEY_19"))) {
            builder.append(" and key_19 =?");
            bindValues.add(keys.getKey("KEY_19"));
        }
        if (!"**".equals(keys.getKey("KEY_20"))) {
            builder.append(" and key_20 =?");
            bindValues.add(keys.getKey("KEY_20"));
        }
        builder.append(" order by wild_card_ind desc");
        Connection connection = null;
        try {
            try {
                connection = PoolBrokerService.getConnection();
                pstmt = connection.prepareStatement(builder.toString());
                Utils.bindValueToPreparedStatement((PreparedStatement)pstmt, bindValues);
                rset = pstmt.executeQuery();
                while (rset.next()) {
                    tempParamData = new ParameterData();
                    tempParamData.match = true;
                    tempParamData.putData("DATA_1", rset.getString("DATA_1"));
                    tempParamData.putData("DATA_2", rset.getString("DATA_2"));
                    tempParamData.putData("DATA_3", rset.getString("DATA_3"));
                    tempParamData.putData("DATA_4", rset.getString("DATA_4"));
                    tempParamData.putData("DATA_5", rset.getString("DATA_5"));
                    tempParamData.putData("DATA_6", rset.getString("DATA_6"));
                    tempParamData.putData("DATA_7", rset.getString("DATA_7"));
                    tempParamData.putData("DATA_8", rset.getString("DATA_8"));
                    tempParamData.putData("DATA_9", rset.getString("DATA_9"));
                    tempParamData.putData("DATA_10", rset.getString("DATA_10"));
                    tempParamData.putData("DATA_11", rset.getString("DATA_11"));
                    tempParamData.putData("DATA_12", rset.getString("DATA_12"));
                    tempParamData.putData("DATA_13", rset.getString("DATA_13"));
                    tempParamData.putData("DATA_14", rset.getString("DATA_14"));
                    tempParamData.putData("DATA_15", rset.getString("DATA_15"));
                    tempParamData.putData("DATA_16", rset.getString("DATA_16"));
                    tempParamData.putData("DATA_17", rset.getString("DATA_17"));
                    tempParamData.putData("DATA_18", rset.getString("DATA_18"));
                    tempParamData.putData("DATA_19", rset.getString("DATA_19"));
                    tempParamData.putData("DATA_20", rset.getString("DATA_20"));
                    tempParamData.getKeys().addKey("KEY_1", rset.getString("KEY_1"));
                    tempParamData.getKeys().addKey("KEY_2", rset.getString("KEY_2"));
                    tempParamData.getKeys().addKey("KEY_3", rset.getString("KEY_3"));
                    tempParamData.getKeys().addKey("KEY_4", rset.getString("KEY_4"));
                    tempParamData.getKeys().addKey("KEY_5", rset.getString("KEY_5"));
                    tempParamData.getKeys().addKey("KEY_6", rset.getString("KEY_6"));
                    tempParamData.getKeys().addKey("KEY_7", rset.getString("KEY_7"));
                    tempParamData.getKeys().addKey("KEY_8", rset.getString("KEY_8"));
                    tempParamData.getKeys().addKey("KEY_9", rset.getString("KEY_9"));
                    tempParamData.getKeys().addKey("KEY_10", rset.getString("KEY_10"));
                    tempParamData.getKeys().addKey("KEY_11", rset.getString("KEY_11"));
                    tempParamData.getKeys().addKey("KEY_12", rset.getString("KEY_12"));
                    tempParamData.getKeys().addKey("KEY_13", rset.getString("KEY_13"));
                    tempParamData.getKeys().addKey("KEY_14", rset.getString("KEY_14"));
                    tempParamData.getKeys().addKey("KEY_15", rset.getString("KEY_15"));
                    tempParamData.getKeys().addKey("KEY_16", rset.getString("KEY_16"));
                    tempParamData.getKeys().addKey("KEY_17", rset.getString("KEY_17"));
                    tempParamData.getKeys().addKey("KEY_18", rset.getString("KEY_18"));
                    tempParamData.getKeys().addKey("KEY_19", rset.getString("KEY_19"));
                    tempParamData.getKeys().addKey("KEY_20", rset.getString("KEY_20"));
                    tempParamData.getKeys().setBrch_code(rset.getString("BRCH_CODE"));
                    tempParamData.getKeys().setCompany_id(rset.getString("COMPANY_ID"));
                    tempParamData.getKeys().setParm_id(rset.getString("PARM_ID"));
                    result.add(tempParamData);
                }
                rset.close();
            }
            catch (Exception e) {
                GTPException.log("Error in connecting resource", e);
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    }
                    catch (Exception exc) {
                        GTPException.log("Error in closing resource", exc);
                    }
                }
                try {
                    PoolBrokerService.releaseConnection(connection);
                }
                catch (Exception exc) {
                    GTPException.log("Error in closing resource", exc);
                }
            }
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception exc) {
                    GTPException.log("Error in closing resource", exc);
                }
            }
            try {
                PoolBrokerService.releaseConnection(connection);
            }
            catch (Exception exc) {
                GTPException.log("Error in closing resource", exc);
            }
        }
        return result;
    }

    public ParameterData() {
    }

    public ParameterData(ParameterKey keys) throws GTPException {
        this(null, keys);
    }

    public ParameterData(RunData data, ParameterKey keys) throws GTPException {
        PreparedStatement pstmt = null;
        ArrayList<String> bindValues = new ArrayList<String>();
        ResultSet rset = null;
        StringBuilder buffer = new StringBuilder();
        this.getKeys().setParm_id(keys.getParm_id());
        buffer.append("select data_1, data_2, data_3, data_4, data_5, data_6,");
        buffer.append(" data_7, data_8, data_9, data_10, data_11,");
        buffer.append(" data_12, data_13, data_14, data_15, data_16,");
        buffer.append(" data_17, data_18, data_19, data_20, wild_card_ind,");
        buffer.append(" key_1, key_2, key_3, key_4, key_5,");
        buffer.append(" key_6, key_7, key_8, key_9, key_10, ");
        buffer.append(" key_11, key_12, key_13, key_14, key_15,");
        buffer.append(" key_16, key_17, key_18, key_19, key_20, ");
        buffer.append(" brch_code, company_id ");
        buffer.append(" from ");
        buffer.append("GTP_PARAM_DATA");
        buffer.append(" where parm_id = ?");
        buffer.append(" and brch_code in (?,'*')");
        buffer.append(" and company_id in (?,'*')");
        buffer.append(" and key_1 in (?,'*','**')");
        buffer.append(" and key_2 in (?,'*','**')");
        buffer.append(" and key_3 in (?,'*','**')");
        buffer.append(" and key_4 in (?,'*','**')");
        buffer.append(" and key_5 in (?,'*','**')");
        buffer.append(" and key_6 in (?,'*','**')");
        buffer.append(" and key_7 in (?,'*','**')");
        buffer.append(" and key_8 in (?,'*','**')");
        buffer.append(" and key_9 in (?,'*','**')");
        buffer.append(" and key_10 in (?,'*','**')");
        buffer.append(" and key_11 in (?,'*','**')");
        buffer.append(" and key_12 in (?,'*','**')");
        buffer.append(" and key_13 in (?,'*','**')");
        buffer.append(" and key_14 in (?,'*','**')");
        buffer.append(" and key_15 in (?,'*','**')");
        buffer.append(" and key_16 in (?,'*','**')");
        buffer.append(" and key_17 in (?,'*','**')");
        buffer.append(" and key_18 in (?,'*','**')");
        buffer.append(" and key_19 in (?,'*','**')");
        buffer.append(" and key_20 in (?,'*','**')");
        buffer.append(" order by wild_card_ind desc");
        Connection connection = null;
        try {
            connection = PoolBrokerService.getConnection();
            pstmt = connection.prepareStatement(buffer.toString());
            bindValues.add(keys.getParm_id());
            bindValues.add(keys.getBrch_code());
            bindValues.add(keys.getCompany_id());
            bindValues.add(keys.getKey("KEY_1"));
            bindValues.add(keys.getKey("KEY_2"));
            bindValues.add(keys.getKey("KEY_3"));
            bindValues.add(keys.getKey("KEY_4"));
            bindValues.add(keys.getKey("KEY_5"));
            bindValues.add(keys.getKey("KEY_6"));
            bindValues.add(keys.getKey("KEY_7"));
            bindValues.add(keys.getKey("KEY_8"));
            bindValues.add(keys.getKey("KEY_9"));
            bindValues.add(keys.getKey("KEY_10"));
            bindValues.add(keys.getKey("KEY_11"));
            bindValues.add(keys.getKey("KEY_12"));
            bindValues.add(keys.getKey("KEY_13"));
            bindValues.add(keys.getKey("KEY_14"));
            bindValues.add(keys.getKey("KEY_15"));
            bindValues.add(keys.getKey("KEY_16"));
            bindValues.add(keys.getKey("KEY_17"));
            bindValues.add(keys.getKey("KEY_18"));
            bindValues.add(keys.getKey("KEY_19"));
            bindValues.add(keys.getKey("KEY_20"));
            Utils.bindValueToPreparedStatement(pstmt, bindValues);
            rset = pstmt.executeQuery();
            if (rset.next()) {
                this.match = true;
                this.putData("DATA_1", rset.getString("DATA_1"));
                this.putData("DATA_2", rset.getString("DATA_2"));
                this.putData("DATA_3", rset.getString("DATA_3"));
                this.putData("DATA_4", rset.getString("DATA_4"));
                this.putData("DATA_5", rset.getString("DATA_5"));
                this.putData("DATA_6", rset.getString("DATA_6"));
                this.putData("DATA_7", rset.getString("DATA_7"));
                this.putData("DATA_8", rset.getString("DATA_8"));
                this.putData("DATA_9", rset.getString("DATA_9"));
                this.putData("DATA_10", rset.getString("DATA_10"));
                this.putData("DATA_11", rset.getString("DATA_11"));
                this.putData("DATA_12", rset.getString("DATA_12"));
                this.putData("DATA_13", rset.getString("DATA_13"));
                this.putData("DATA_14", rset.getString("DATA_14"));
                this.putData("DATA_15", rset.getString("DATA_15"));
                this.putData("DATA_16", rset.getString("DATA_16"));
                this.putData("DATA_17", rset.getString("DATA_17"));
                this.putData("DATA_18", rset.getString("DATA_18"));
                this.putData("DATA_19", rset.getString("DATA_19"));
                this.putData("DATA_20", rset.getString("DATA_20"));
                this.getKeys().addKey("KEY_1", rset.getString("KEY_1"));
                this.getKeys().addKey("KEY_2", rset.getString("KEY_2"));
                this.getKeys().addKey("KEY_3", rset.getString("KEY_3"));
                this.getKeys().addKey("KEY_4", rset.getString("KEY_4"));
                this.getKeys().addKey("KEY_5", rset.getString("KEY_5"));
                this.getKeys().addKey("KEY_6", rset.getString("KEY_6"));
                this.getKeys().addKey("KEY_7", rset.getString("KEY_7"));
                this.getKeys().addKey("KEY_8", rset.getString("KEY_8"));
                this.getKeys().addKey("KEY_9", rset.getString("KEY_9"));
                this.getKeys().addKey("KEY_10", rset.getString("KEY_10"));
                this.getKeys().addKey("KEY_11", rset.getString("KEY_11"));
                this.getKeys().addKey("KEY_12", rset.getString("KEY_12"));
                this.getKeys().addKey("KEY_13", rset.getString("KEY_13"));
                this.getKeys().addKey("KEY_14", rset.getString("KEY_14"));
                this.getKeys().addKey("KEY_15", rset.getString("KEY_15"));
                this.getKeys().addKey("KEY_16", rset.getString("KEY_16"));
                this.getKeys().addKey("KEY_17", rset.getString("KEY_17"));
                this.getKeys().addKey("KEY_18", rset.getString("KEY_18"));
                this.getKeys().addKey("KEY_19", rset.getString("KEY_19"));
                this.getKeys().addKey("KEY_20", rset.getString("KEY_20"));
                this.getKeys().setBrch_code(rset.getString("BRCH_CODE"));
                this.getKeys().setCompany_id(rset.getString("COMPANY_ID"));
            }
            this.exactMatch = keys.getBrch_code().equals(this.getKeys().getBrch_code()) && keys.getCompany_id().equals(this.getKeys().getCompany_id()) && keys.getKey("KEY_1").equals(this.getKeys().getKey("KEY_1")) && keys.getKey("KEY_2").equals(this.getKeys().getKey("KEY_2")) && keys.getKey("KEY_3").equals(this.getKeys().getKey("KEY_3")) && keys.getKey("KEY_4").equals(this.getKeys().getKey("KEY_4")) && keys.getKey("KEY_5").equals(this.getKeys().getKey("KEY_5")) && keys.getKey("KEY_6").equals(this.getKeys().getKey("KEY_6")) && keys.getKey("KEY_7").equals(this.getKeys().getKey("KEY_7")) && keys.getKey("KEY_8").equals(this.getKeys().getKey("KEY_8")) && keys.getKey("KEY_9").equals(this.getKeys().getKey("KEY_9")) && keys.getKey("KEY_10").equals(this.getKeys().getKey("KEY_10"));
            rset.close();
            pstmt.close();
            PoolBrokerService.releaseConnection(connection);
        }
        catch (Exception e) {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                PoolBrokerService.releaseConnection(connection);
            }
            catch (Exception exc) {
                throw new GTPException(data, exc.getMessage(), e);
            }
            throw new GTPException(data, e.getMessage(), e);
        }
    }

    @Override
    public String getAuditItem_Id1() {
        StringBuilder buffer = new StringBuilder(" Parm id: ").append(this.keys.getParm_id()).append(" Keys : \t ");
        for (Object ite : this.keys.keySet()) {
            Object key = this.keys.get(ite);
            if ("**".equals(key)) continue;
            buffer.append(ite).append(":").append(key).append("\t");
        }
        return buffer.toString();
    }

    @Override
    public String getAuditItem_Id2() {
        return null;
    }

    @Override
    public String getAuditProduct_code() {
        return null;
    }

    @Override
    public String getAuditText() {
        StringBuilder buffer = new StringBuilder(" Parm id: ").append(this.keys.getParm_id()).append(" ");
        for (Object ite2 : this.keys.keySet()) {
            Object key = this.keys.get(ite2);
            if ("**".equals(key) || "*".equals(key)) continue;
            buffer.append(ite2).append(":").append(key).append(" ");
        }
        buffer.append("\n");
        for (Object ite2 : this.parameters.keySet()) {
            Object data = this.parameters.get(ite2);
            if ("**".equals(data)) continue;
            buffer.append(ite2).append(":").append(data).append(" \n ");
        }
        buffer.append("\n");
        return buffer.toString();
    }

    @Override
    public String getAuditType() {
        return "09";
    }

    public Hashtable getData() {
        return this.parameters;
    }

    public String getData(String key) {
        String result = (String)this.parameters.get(key);
        if (result == null) {
            return "**";
        }
        return result;
    }

    public ParameterKey getKeys() {
        return this.keys;
    }

    public ParameterKey getOldKeys() {
        return this.oldKeys;
    }

    public void mergeData(ParameterData param) throws GTPException {
        int pos = 1;
        int i = 1;
        while (i < 21) {
            if ("**".equals(this.getData("DATA_" + i))) {
                if ("**".equals(param.getData("DATA_" + pos))) {
                    return;
                }
                this.putData("DATA_" + i, param.getData("DATA_" + pos));
                ++pos;
            }
            ++i;
        }
        if (pos < 21 && !"**".equals(param.getData("DATA_" + pos))) {
            throw new GTPException("Error while merging Parameter Data: too many data.");
        }
    }

    public void putData(String key, Object value) {
        if (key != null && value != null) {
            if (value instanceof String && "**".equals(value)) {
                return;
            }
            this.parameters.put(key, value);
        }
    }

    public void save() throws GTPException {
        this.save(null);
    }

    public void save(RunData data) throws GTPException {
        block30 : {
            if (this.oldKeys != null) {
                ParameterData.delete(data, this.oldKeys);
            }
            ParameterData paramData = new ParameterData(data, this.getKeys());
            PreparedStatement pstmt = null;
            ArrayList<String> bindValues = new ArrayList<String>();
            StringBuilder builder = new StringBuilder();
            Connection connection = null;
            String wildcardIndicator = "";
            wildcardIndicator = "*".equals(this.getKeys().getBrch_code()) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getCompany_id()) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_1")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_2")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_3")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_4")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_5")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_6")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_7")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_8")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_9")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_10")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_11")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_12")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_13")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_14")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_15")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_16")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_17")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_18")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_19")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            wildcardIndicator = "*".equals(this.getKeys().getKey("KEY_20")) ? String.valueOf(wildcardIndicator) + "0" : String.valueOf(wildcardIndicator) + "1";
            if (paramData.exactMatch) {
                builder.append("update ");
                builder.append("GTP_PARAM_DATA");
                builder.append(" set data_1 = ? , ");
                builder.append(" data_2 = ? , ");
                builder.append(" data_3 = ? , ");
                builder.append(" data_4 = ? , ");
                builder.append(" data_5 = ? , ");
                builder.append(" data_6 = ? , ");
                builder.append(" data_7 = ? , ");
                builder.append(" data_8 = ? , ");
                builder.append(" data_9 = ? , ");
                builder.append(" data_10 = ? , ");
                builder.append(" data_11 = ? , ");
                builder.append(" data_12 = ? , ");
                builder.append(" data_13 = ? , ");
                builder.append(" data_14 = ? , ");
                builder.append(" data_15 = ? , ");
                builder.append(" data_16 = ? , ");
                builder.append(" data_17 = ? , ");
                builder.append(" data_18 = ? , ");
                builder.append(" data_19 = ? , ");
                builder.append(" data_20 = ? ");
                builder.append(" where parm_id = ? ");
                builder.append(" and brch_code = ? ");
                builder.append(" and company_id = ? ");
                builder.append(" and key_1 = ? ");
                builder.append(" and key_2 = ? ");
                builder.append(" and key_3 = ? ");
                builder.append(" and key_4 = ? ");
                builder.append(" and key_5 = ? ");
                builder.append(" and key_6 = ? ");
                builder.append(" and key_7 = ? ");
                builder.append(" and key_8 = ? ");
                builder.append(" and key_9 = ? ");
                builder.append(" and key_10 = ? ");
                builder.append(" and key_11 = ? ");
                builder.append(" and key_12 = ? ");
                builder.append(" and key_13 = ? ");
                builder.append(" and key_14 = ? ");
                builder.append(" and key_15 = ? ");
                builder.append(" and key_16 = ? ");
                builder.append(" and key_17 = ? ");
                builder.append(" and key_18 = ? ");
                builder.append(" and key_19 = ? ");
                builder.append(" and key_20 = ? ");
                try {
                    try {
                        connection = PoolBrokerService.getConnection();
                        pstmt = connection.prepareStatement(builder.toString());
                        bindValues.add(this.getData("DATA_1"));
                        bindValues.add(this.getData("DATA_2"));
                        bindValues.add(this.getData("DATA_3"));
                        bindValues.add(this.getData("DATA_4"));
                        bindValues.add(this.getData("DATA_5"));
                        bindValues.add(this.getData("DATA_6"));
                        bindValues.add(this.getData("DATA_7"));
                        bindValues.add(this.getData("DATA_8"));
                        bindValues.add(this.getData("DATA_9"));
                        bindValues.add(this.getData("DATA_10"));
                        bindValues.add(this.getData("DATA_11"));
                        bindValues.add(this.getData("DATA_12"));
                        bindValues.add(this.getData("DATA_13"));
                        bindValues.add(this.getData("DATA_14"));
                        bindValues.add(this.getData("DATA_15"));
                        bindValues.add(this.getData("DATA_16"));
                        bindValues.add(this.getData("DATA_17"));
                        bindValues.add(this.getData("DATA_18"));
                        bindValues.add(this.getData("DATA_19"));
                        bindValues.add(this.getData("DATA_20"));
                        bindValues.add(this.keys.getParm_id());
                        bindValues.add(this.keys.getBrch_code());
                        bindValues.add(this.keys.getCompany_id());
                        bindValues.add(this.keys.getKey("KEY_1"));
                        bindValues.add(this.keys.getKey("KEY_2"));
                        bindValues.add(this.keys.getKey("KEY_3"));
                        bindValues.add(this.keys.getKey("KEY_4"));
                        bindValues.add(this.keys.getKey("KEY_5"));
                        bindValues.add(this.keys.getKey("KEY_6"));
                        bindValues.add(this.keys.getKey("KEY_7"));
                        bindValues.add(this.keys.getKey("KEY_8"));
                        bindValues.add(this.keys.getKey("KEY_9"));
                        bindValues.add(this.keys.getKey("KEY_10"));
                        bindValues.add(this.keys.getKey("KEY_11"));
                        bindValues.add(this.keys.getKey("KEY_12"));
                        bindValues.add(this.keys.getKey("KEY_13"));
                        bindValues.add(this.keys.getKey("KEY_14"));
                        bindValues.add(this.keys.getKey("KEY_15"));
                        bindValues.add(this.keys.getKey("KEY_16"));
                        bindValues.add(this.keys.getKey("KEY_17"));
                        bindValues.add(this.keys.getKey("KEY_18"));
                        bindValues.add(this.keys.getKey("KEY_19"));
                        bindValues.add(this.keys.getKey("KEY_20"));
                        Utils.bindValueToPreparedStatement((PreparedStatement)pstmt, bindValues);
                        pstmt.executeUpdate();
                        break block30;
                    }
                    catch (Exception e) {
                        throw new GTPException(data, e.getMessage());
                    }
                }
                finally {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        }
                        catch (Exception exc) {
                            GTPException.log("Error in closing resource", exc);
                        }
                    }
                    try {
                        PoolBrokerService.releaseConnection(connection);
                    }
                    catch (Exception exc) {
                        GTPException.log("Error in closing resource", exc);
                    }
                }
            }
            builder.append("insert into ");
            builder.append("GTP_PARAM_DATA");
            builder.append(" ( ");
            builder.append(" brch_code, company_id, parm_id, wild_card_ind, ");
            builder.append(" key_1, key_2, key_3, key_4, key_5, ");
            builder.append(" key_6, key_7, key_8, key_9, key_10 , ");
            builder.append(" key_11, key_12, key_13, key_14, key_15, ");
            builder.append(" key_16, key_17, key_18, key_19, key_20 , ");
            builder.append(" data_1, data_2, data_3, data_4, data_5, ");
            builder.append(" data_6, data_7, data_8, data_9, data_10, ");
            builder.append(" data_11, data_12, data_13, data_14, data_15, ");
            builder.append(" data_16, data_17, data_18, data_19, data_20) ");
            builder.append(" values ( ?, ?, ?, ?, ");
            builder.append(" ?, ?, ?, ?, ?, ");
            builder.append(" ?, ?, ?, ?, ?, ");
            builder.append(" ?, ?, ?, ?, ?, ");
            builder.append(" ?, ?, ?, ?, ?, ");
            builder.append(" ?, ?, ?, ?, ?, ");
            builder.append(" ?, ?, ?, ?, ?, ");
            builder.append(" ?, ?, ?, ?, ?, ");
            builder.append(" ?, ?, ?, ?, ?) ");
            try {
                try {
                    connection = PoolBrokerService.getConnection();
                    pstmt = connection.prepareStatement(builder.toString());
                    bindValues.add(this.keys.getBrch_code());
                    bindValues.add(this.keys.getCompany_id());
                    bindValues.add(this.keys.getParm_id());
                    bindValues.add(wildcardIndicator);
                    bindValues.add(this.keys.getKey("KEY_1"));
                    bindValues.add(this.keys.getKey("KEY_2"));
                    bindValues.add(this.keys.getKey("KEY_3"));
                    bindValues.add(this.keys.getKey("KEY_4"));
                    bindValues.add(this.keys.getKey("KEY_5"));
                    bindValues.add(this.keys.getKey("KEY_6"));
                    bindValues.add(this.keys.getKey("KEY_7"));
                    bindValues.add(this.keys.getKey("KEY_8"));
                    bindValues.add(this.keys.getKey("KEY_9"));
                    bindValues.add(this.keys.getKey("KEY_10"));
                    bindValues.add(this.keys.getKey("KEY_11"));
                    bindValues.add(this.keys.getKey("KEY_12"));
                    bindValues.add(this.keys.getKey("KEY_13"));
                    bindValues.add(this.keys.getKey("KEY_14"));
                    bindValues.add(this.keys.getKey("KEY_15"));
                    bindValues.add(this.keys.getKey("KEY_16"));
                    bindValues.add(this.keys.getKey("KEY_17"));
                    bindValues.add(this.keys.getKey("KEY_18"));
                    bindValues.add(this.keys.getKey("KEY_19"));
                    bindValues.add(this.keys.getKey("KEY_20"));
                    bindValues.add(this.getData("DATA_1"));
                    bindValues.add(this.getData("DATA_2"));
                    bindValues.add(this.getData("DATA_3"));
                    bindValues.add(this.getData("DATA_4"));
                    bindValues.add(this.getData("DATA_5"));
                    bindValues.add(this.getData("DATA_6"));
                    bindValues.add(this.getData("DATA_7"));
                    bindValues.add(this.getData("DATA_8"));
                    bindValues.add(this.getData("DATA_9"));
                    bindValues.add(this.getData("DATA_10"));
                    bindValues.add(this.getData("DATA_11"));
                    bindValues.add(this.getData("DATA_12"));
                    bindValues.add(this.getData("DATA_13"));
                    bindValues.add(this.getData("DATA_14"));
                    bindValues.add(this.getData("DATA_15"));
                    bindValues.add(this.getData("DATA_16"));
                    bindValues.add(this.getData("DATA_17"));
                    bindValues.add(this.getData("DATA_18"));
                    bindValues.add(this.getData("DATA_19"));
                    bindValues.add(this.getData("DATA_20"));
                    Utils.bindValueToPreparedStatement((PreparedStatement)pstmt, bindValues);
                    pstmt.executeUpdate();
                }
                catch (Exception e) {
                    throw new GTPException(data, e.getMessage());
                }
            }
            finally {
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    }
                    catch (Exception exc) {
                        GTPException.log("Error in closing resource", exc);
                    }
                }
                try {
                    PoolBrokerService.releaseConnection(connection);
                }
                catch (Exception exc) {
                    GTPException.log("Error in closing resource", exc);
                }
            }
        }
    }

    public void setData(Hashtable parameters) {
        this.parameters.clear();
        this.parameters.putAll(parameters);
    }

    public void setKeys(ParameterKey keys) {
        this.keys = keys;
    }

    public void setOldKeys(ParameterKey oldKeys) {
        this.oldKeys = oldKeys;
    }

    public String toExported() {
        StringBuilder xmlString = new StringBuilder();
        xmlString.append("<parameter>");
        xmlString.append("<brch_code>").append(this.getKeys().getBrch_code()).append("</brch_code>");
        xmlString.append("<company_id>").append(this.getKeys().getCompany_id()).append("</company_id>");
        xmlString.append("<parm_id>").append(this.getKeys().getParm_id()).append("</parm_id>");
        xmlString.append("<key_1>").append(this.getKeys().getKey("KEY_1")).append("</key_1>");
        xmlString.append("<key_2>").append(this.getKeys().getKey("KEY_2")).append("</key_2>");
        xmlString.append("<key_3>").append(this.getKeys().getKey("KEY_3")).append("</key_3>");
        xmlString.append("<key_4>").append(this.getKeys().getKey("KEY_4")).append("</key_4>");
        xmlString.append("<key_5>").append(this.getKeys().getKey("KEY_5")).append("</key_5>");
        xmlString.append("<key_6>").append(this.getKeys().getKey("KEY_6")).append("</key_6>");
        xmlString.append("<key_7>").append(this.getKeys().getKey("KEY_7")).append("</key_7>");
        xmlString.append("<key_8>").append(this.getKeys().getKey("KEY_8")).append("</key_8>");
        xmlString.append("<key_9>").append(this.getKeys().getKey("KEY_9")).append("</key_9>");
        xmlString.append("<key_10>").append(this.getKeys().getKey("KEY_10")).append("</key_10>");
        xmlString.append("<key_11>").append(this.getKeys().getKey("KEY_11")).append("</key_11>");
        xmlString.append("<key_12>").append(this.getKeys().getKey("KEY_12")).append("</key_12>");
        xmlString.append("<key_13>").append(this.getKeys().getKey("KEY_13")).append("</key_13>");
        xmlString.append("<key_14>").append(this.getKeys().getKey("KEY_14")).append("</key_14>");
        xmlString.append("<key_15>").append(this.getKeys().getKey("KEY_15")).append("</key_15>");
        xmlString.append("<key_16>").append(this.getKeys().getKey("KEY_16")).append("</key_16>");
        xmlString.append("<key_17>").append(this.getKeys().getKey("KEY_17")).append("</key_17>");
        xmlString.append("<key_18>").append(this.getKeys().getKey("KEY_18")).append("</key_18>");
        xmlString.append("<key_19>").append(this.getKeys().getKey("KEY_19")).append("</key_19>");
        xmlString.append("<key_20>").append(this.getKeys().getKey("KEY_20")).append("</key_20>");
        xmlString.append("<data_1>").append(this.getData("DATA_1")).append("</data_1>");
        xmlString.append("<data_2>").append(this.getData("DATA_2")).append("</data_2>");
        xmlString.append("<data_3>").append(this.getData("DATA_3")).append("</data_3>");
        xmlString.append("<data_4>").append(this.getData("DATA_4")).append("</data_4>");
        xmlString.append("<data_5>").append(this.getData("DATA_5")).append("</data_5>");
        xmlString.append("<data_6>").append(this.getData("DATA_6")).append("</data_6>");
        xmlString.append("<data_7>").append(this.getData("DATA_7")).append("</data_7>");
        xmlString.append("<data_8>").append(this.getData("DATA_8")).append("</data_8>");
        xmlString.append("<data_9>").append(this.getData("DATA_9")).append("</data_9>");
        xmlString.append("<data_10>").append(this.getData("DATA_10")).append("</data_10>");
        xmlString.append("<data_11>").append(this.getData("DATA_11")).append("</data_11>");
        xmlString.append("<data_12>").append(this.getData("DATA_12")).append("</data_12>");
        xmlString.append("<data_13>").append(this.getData("DATA_13")).append("</data_13>");
        xmlString.append("<data_14>").append(this.getData("DATA_14")).append("</data_14>");
        xmlString.append("<data_15>").append(this.getData("DATA_15")).append("</data_15>");
        xmlString.append("<data_16>").append(this.getData("DATA_16")).append("</data_16>");
        xmlString.append("<data_17>").append(this.getData("DATA_17")).append("</data_17>");
        xmlString.append("<data_18>").append(this.getData("DATA_18")).append("</data_18>");
        xmlString.append("<data_19>").append(this.getData("DATA_19")).append("</data_19>");
        xmlString.append("<data_20>").append(this.getData("DATA_20")).append("</data_20>");
        xmlString.append("</parameter>");
        return xmlString.toString();
    }

    public String toXML(String language) {
        return this.toXML(language, true, false, null);
    }

    public String toXML(String language, boolean withHeader) {
        return this.toXML(language, withHeader, false, null);
    }

    public String toXML(String language, boolean withHeader, boolean isSelected, String rootNodeNameValue) {
        StringBuilder xmlString = new StringBuilder();
        if (withHeader) {
            xmlString.append("<?xml version=\"1.0\"?>");
        }
        String rootNodeName = rootNodeNameValue != null ? rootNodeNameValue : "parameter_data";
        xmlString.append("<").append(rootNodeName);
        if (isSelected) {
            xmlString.append(" selected=\"true\"");
        }
        xmlString.append(">");
        xmlString.append("<brch_code>").append(SecurityUtils.encodeHTML("00001")).append("</brch_code>");
        if (!"**".equals(this.getKeys().getCompany_id())) {
            xmlString.append("<company_id>").append(SecurityUtils.encodeHTML(this.getKeys().getCompany_id())).append("</company_id>");
        } else {
            xmlString.append("<company_id>").append("</company_id>");
        }
        if (!"**".equals(this.getKeys().getParm_id())) {
            xmlString.append("<parm_id>").append(SecurityUtils.encodeHTML(this.getKeys().getParm_id())).append("</parm_id>");
        } else {
            xmlString.append("<parm_id>").append("</parm_id>");
        }
        if (!"**".equals(this.keys.getKey("KEY_1"))) {
            xmlString.append("<key_1>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_1"))).append("</key_1>");
        } else {
            xmlString.append("<key_1>").append("</key_1>");
        }
        if (!"**".equals(this.keys.getKey("KEY_2"))) {
            xmlString.append("<key_2>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_2"))).append("</key_2>");
        } else {
            xmlString.append("<key_2>").append("</key_2>");
        }
        if (!"**".equals(this.keys.getKey("KEY_3"))) {
            xmlString.append("<key_3>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_3"))).append("</key_3>");
        } else {
            xmlString.append("<key_3>").append("</key_3>");
        }
        if (!"**".equals(this.keys.getKey("KEY_4"))) {
            xmlString.append("<key_4>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_4"))).append("</key_4>");
        } else {
            xmlString.append("<key_4>").append("</key_4>");
        }
        if (!"**".equals(this.keys.getKey("KEY_5"))) {
            xmlString.append("<key_5>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_5"))).append("</key_5>");
        } else {
            xmlString.append("<key_5>").append("</key_5>");
        }
        if (!"**".equals(this.keys.getKey("KEY_6"))) {
            xmlString.append("<key_6>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_6"))).append("</key_6>");
        } else {
            xmlString.append("<key_6>").append("</key_6>");
        }
        if (!"**".equals(this.keys.getKey("KEY_7"))) {
            xmlString.append("<key_7>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_7"))).append("</key_7>");
        } else {
            xmlString.append("<key_7>").append("</key_7>");
        }
        if (!"**".equals(this.keys.getKey("KEY_8"))) {
            xmlString.append("<key_8>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_8"))).append("</key_8>");
        } else {
            xmlString.append("<key_8>").append("</key_8>");
        }
        if (!"**".equals(this.keys.getKey("KEY_9"))) {
            xmlString.append("<key_9>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_9"))).append("</key_9>");
        } else {
            xmlString.append("<key_9>").append("</key_9>");
        }
        if (!"**".equals(this.keys.getKey("KEY_10"))) {
            xmlString.append("<key_10>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_10"))).append("</key_10>");
        } else {
            xmlString.append("<key_10>").append("</key_10>");
        }
        if (!"**".equals(this.keys.getKey("KEY_11"))) {
            xmlString.append("<key_11>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_11"))).append("</key_11>");
        } else {
            xmlString.append("<key_11>").append("</key_11>");
        }
        if (!"**".equals(this.keys.getKey("KEY_12"))) {
            xmlString.append("<key_12>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_12"))).append("</key_12>");
        } else {
            xmlString.append("<key_12>").append("</key_12>");
        }
        if (!"**".equals(this.keys.getKey("KEY_13"))) {
            xmlString.append("<key_13>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_13"))).append("</key_13>");
        } else {
            xmlString.append("<key_13>").append("</key_13>");
        }
        if (!"**".equals(this.keys.getKey("KEY_14"))) {
            xmlString.append("<key_14>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_14"))).append("</key_14>");
        } else {
            xmlString.append("<key_14>").append("</key_14>");
        }
        if (!"**".equals(this.keys.getKey("KEY_15"))) {
            xmlString.append("<key_15>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_15"))).append("</key_15>");
        } else {
            xmlString.append("<key_15>").append("</key_15>");
        }
        if (!"**".equals(this.keys.getKey("KEY_16"))) {
            xmlString.append("<key_16>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_16"))).append("</key_16>");
        } else {
            xmlString.append("<key_16>").append("</key_16>");
        }
        if (!"**".equals(this.keys.getKey("KEY_17"))) {
            xmlString.append("<key_17>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_17"))).append("</key_17>");
        } else {
            xmlString.append("<key_17>").append("</key_17>");
        }
        if (!"**".equals(this.keys.getKey("KEY_18"))) {
            xmlString.append("<key_18>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_18"))).append("</key_18>");
        } else {
            xmlString.append("<key_18>").append("</key_18>");
        }
        if (!"**".equals(this.keys.getKey("KEY_19"))) {
            xmlString.append("<key_19>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_19"))).append("</key_19>");
        } else {
            xmlString.append("<key_19>").append("</key_19>");
        }
        if (!"**".equals(this.keys.getKey("KEY_20"))) {
            xmlString.append("<key_20>").append(SecurityUtils.encodeHTML(this.keys.getKey("KEY_20"))).append("</key_20>");
        } else {
            xmlString.append("<key_20>").append("</key_20>");
        }
        if (!"**".equals(this.keys.getKey("*"))) {
            xmlString.append("<key_wildcard>").append(SecurityUtils.encodeHTML(this.keys.getKey("*"))).append("<key_wildcard>");
        } else {
            xmlString.append("<key_wildcard>").append("</key_wildcard>");
        }
        if (!"**".equals(this.getData("DATA_1"))) {
            xmlString.append("<data_1>").append(SecurityUtils.encodeHTML(this.getData("DATA_1"))).append("</data_1>");
        } else {
            xmlString.append("<data_1>").append("</data_1>");
        }
        if (!"**".equals(this.getData("DATA_2"))) {
            xmlString.append("<data_2>").append(SecurityUtils.encodeHTML(this.getData("DATA_2"))).append("</data_2>");
        } else {
            xmlString.append("<data_2>").append("</data_2>");
        }
        if (!"**".equals(this.getData("DATA_3"))) {
            xmlString.append("<data_3>").append(SecurityUtils.encodeHTML(this.getData("DATA_3"))).append("</data_3>");
        } else {
            xmlString.append("<data_3>").append("</data_3>");
        }
        if (!"**".equals(this.getData("DATA_4"))) {
            xmlString.append("<data_4>").append(SecurityUtils.encodeHTML(this.getData("DATA_4"))).append("</data_4>");
        } else {
            xmlString.append("<data_4>").append("</data_4>");
        }
        if (!"**".equals(this.getData("DATA_5"))) {
            xmlString.append("<data_5>").append(SecurityUtils.encodeHTML(this.getData("DATA_5"))).append("</data_5>");
        } else {
            xmlString.append("<data_5>").append("</data_5>");
        }
        if (!"**".equals(this.getData("DATA_6"))) {
            xmlString.append("<data_6>").append(SecurityUtils.encodeHTML(this.getData("DATA_6"))).append("</data_6>");
        } else {
            xmlString.append("<data_6>").append("</data_6>");
        }
        if (!"**".equals(this.getData("DATA_7"))) {
            xmlString.append("<data_7>").append(SecurityUtils.encodeHTML(this.getData("DATA_7"))).append("</data_7>");
        } else {
            xmlString.append("<data_7>").append("</data_7>");
        }
        if (!"**".equals(this.getData("DATA_8"))) {
            xmlString.append("<data_8>").append(SecurityUtils.encodeHTML(this.getData("DATA_8"))).append("</data_8>");
        } else {
            xmlString.append("<data_8>").append("</data_8>");
        }
        if (!"**".equals(this.getData("DATA_9"))) {
            xmlString.append("<data_9>").append(SecurityUtils.encodeHTML(this.getData("DATA_9"))).append("</data_9>");
        } else {
            xmlString.append("<data_9>").append("</data_9>");
        }
        if (!"**".equals(this.getData("DATA_10"))) {
            xmlString.append("<data_10>").append(SecurityUtils.encodeHTML(this.getData("DATA_10"))).append("</data_10>");
        } else {
            xmlString.append("<data_10>").append("</data_10>");
        }
        if (!"**".equals(this.getData("DATA_11"))) {
            xmlString.append("<data_11>").append(SecurityUtils.encodeHTML(this.getData("DATA_11"))).append("</data_11>");
        } else {
            xmlString.append("<data_11>").append("</data_11>");
        }
        if (!"**".equals(this.getData("DATA_12"))) {
            xmlString.append("<data_12>").append(SecurityUtils.encodeHTML(this.getData("DATA_12"))).append("</data_12>");
        } else {
            xmlString.append("<data_12>").append("</data_12>");
        }
        if (!"**".equals(this.getData("DATA_13"))) {
            xmlString.append("<data_13>").append(SecurityUtils.encodeHTML(this.getData("DATA_13"))).append("</data_13>");
        } else {
            xmlString.append("<data_13>").append("</data_13>");
        }
        if (!"**".equals(this.getData("DATA_14"))) {
            xmlString.append("<data_14>").append(SecurityUtils.encodeHTML(this.getData("DATA_14"))).append("</data_14>");
        } else {
            xmlString.append("<data_14>").append("</data_14>");
        }
        if (!"**".equals(this.getData("DATA_15"))) {
            xmlString.append("<data_15>").append(SecurityUtils.encodeHTML(this.getData("DATA_15"))).append("</data_15>");
        } else {
            xmlString.append("<data_15>").append("</data_15>");
        }
        if (!"**".equals(this.getData("DATA_16"))) {
            xmlString.append("<data_16>").append(SecurityUtils.encodeHTML(this.getData("DATA_16"))).append("</data_16>");
        } else {
            xmlString.append("<data_16>").append("</data_16>");
        }
        if (!"**".equals(this.getData("DATA_17"))) {
            xmlString.append("<data_17>").append(SecurityUtils.encodeHTML(this.getData("DATA_17"))).append("</data_17>");
        } else {
            xmlString.append("<data_17>").append("</data_17>");
        }
        if (!"**".equals(this.getData("DATA_18"))) {
            xmlString.append("<data_18>").append(SecurityUtils.encodeHTML(this.getData("DATA_18"))).append("</data_18>");
        } else {
            xmlString.append("<data_18>").append("</data_18>");
        }
        if (!"**".equals(this.getData("DATA_19"))) {
            xmlString.append("<data_19>").append(SecurityUtils.encodeHTML(this.getData("DATA_19"))).append("</data_19>");
        } else {
            xmlString.append("<data_19>").append("</data_19>");
        }
        if (!"**".equals(this.getData("DATA_20"))) {
            xmlString.append("<data_20>").append(SecurityUtils.encodeHTML(this.getData("DATA_20"))).append("</data_20>");
        } else {
            xmlString.append("<data_20>").append("</data_20>");
        }
        xmlString.append("</").append(rootNodeName).append(">");
        return xmlString.toString();
    }
}


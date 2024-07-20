/*
 * Decompiled with CFR 0_116.
 * 
 * Could not load the following classes:
 *  com.workingdogs.village.Record
 *  com.workingdogs.village.Value
 *  org.apache.commons.configuration.Configuration
 *  org.apache.commons.lang.StringUtils
 *  org.apache.torque.util.BasePeer
 *  org.apache.torque.util.Criteria
 *  org.apache.turbine.services.cache.CachedObject
 *  org.apache.turbine.util.RunData
 */
package com.misys.portal.services.security.db;

import com.misys.portal.common.pagination.PaginationParams;
import com.misys.portal.common.pagination.PaginationResult;
import com.misys.portal.common.resources.DefaultResourceProvider;
import com.misys.portal.common.tools.Utils;
import com.misys.portal.common.tracer.CompanyNotFoundException;
import com.misys.portal.common.tracer.GTPException;
import com.misys.portal.common.tracer.Log;
import com.misys.portal.core.cache.ApplicationCache;
import com.misys.portal.security.GTPCompany;
import com.misys.portal.security.GTPCompanyTnx;
import com.misys.portal.security.GTPSecurity;
import com.misys.portal.security.util.CompanyComparator;
import com.misys.portal.security.util.CompanySet;
import com.misys.portal.services.db.PoolBrokerService;
import com.misys.portal.services.security.CompanyManager;
import com.misys.portal.util.db.SearchCriteria;
import com.misys.portal.util.db.adapter.DBAdapter;
import com.workingdogs.village.Record;
import com.workingdogs.village.Value;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.torque.util.BasePeer;
import org.apache.torque.util.Criteria;
import org.apache.turbine.services.cache.CachedObject;
import org.apache.turbine.util.RunData;

public class DBCompanyManager
implements CompanyManager {
    private static final String SQL_UPDATE_GTP_COMPANY_TNX = "update GTP_COMPANY_TNX set abbv_name = ?, company_id = ?, company_group = ?, name = ?, address_line_1 = ?, address_line_2 = ?, dom = ?,  country = ?, contact_name = ?, phone = ?, fax = ?, telex = ?, iso_code = ?, reference = ?, email = ?, web_address = ?, type = ?,  owner_id = ?, base_cur_code = ?, language = ?, tnx_type_code = ?, tnx_stat_code = ?,checker_id = ?, checker_dttm = ?, return_comments = ?,   charge_account_address_line_1 = ?, charge_account_address_line_2 = ?, charge_account_address_line_3 = ?, charge_account_address_line_4 = ?, charge_account = ?,  actv_flag = ?, dual_control = ?, password_expiry = ?, attachment_max_upload_size = ?, retention_period = ?, bei = ?, street_name = ?, post_code = ?, town_name = ?,  country_sub_div = ?, crm_email = ?, legal_id_type = ?, legal_id_no = ?, country_legalid = ?, authorize_own_transaction = ?, bulk_authorize_limit = ?,  auto_fwd_date = ?, check_file_hash_value = ?, check_duplicate_file = ?, check_duplicate_cust_ref = ?,  file_encryption_method = ?, reject_file_on_error = ?, merge_demerge_allowed = ?, bulk_draft_on_error = ?, county = ?, country_name = ?, treasury_branch_reference = ?, psml_template = ?, liquidity_frequency = ?, liquidity_balance_type = ?, liquidity_ccy_cur_code = ?, time_zone = ?, rmGroup=? where company_id = ?  and tnx_stat_code in (?,?,?)";
    private static final String SQL_UPDATE_GTP_COMPANY = "update GTP_COMPANY set abbv_name = ?, company_id = ?, company_group = ?,  name = ?, address_line_1 = ?, address_line_2 = ?, dom = ?, charge_account_address_line_1 = ?, charge_account_address_line_2 = ?, charge_account_address_line_3 = ?, charge_account_address_line_4 = ?, charge_account = ?, country = ?, contact_name = ?, phone = ?, fax = ?, telex = ?,  iso_code = ?, reference = ?, email = ?, web_address = ?, type = ?, owner_id = ?, base_cur_code = ?, language = ?,  actv_flag = ?, dual_control = ?, password_expiry = ?, attachment_max_upload_size = ?, retention_period = ?, bei = ?, street_name = ?, post_code = ?, town_name = ?,  country_sub_div = ?, crm_email = ?, legal_id_type = ?, legal_id_no = ?, country_legalid = ?, authorize_own_transaction = ?, bulk_authorize_limit = ?,  auto_fwd_date = ?, check_file_hash_value = ?, check_duplicate_file = ?, check_duplicate_cust_ref = ?,  file_encryption_method = ?, reject_file_on_error = ?, merge_demerge_allowed = ?, bulk_draft_on_error = ?, county = ?, country_name = ?, treasury_branch_reference = ?, psml_template = ?,  liquidity_frequency = ?, liquidity_balance_type = ?, liquidity_ccy_cur_code = ?, time_zone = ?, rmGroup=? where company_id = ?";
    private static final String SQL_INSERT_GTP_COMPANY_TNX = "insert into GTP_COMPANY_TNX (abbv_name, company_id, company_group, name, address_line_1, address_line_2, dom, country, contact_name, phone,  fax, telex, iso_code, reference, email, web_address, type, owner_id, base_cur_code, language, tnx_id, tnx_type_code, tnx_stat_code, maker_id,   checker_id, maker_dttm, return_comments, charge_account_address_line_1, charge_account_address_line_2, charge_account_address_line_3, charge_account_address_line_4, charge_account,  actv_flag, dual_control, password_expiry, attachment_max_upload_size, retention_period, bei, street_name, post_code, town_name, country_sub_div, crm_email, legal_id_type,  legal_id_no, country_legalid, authorize_own_transaction, bulk_authorize_limit, auto_fwd_date, check_file_hash_value, check_duplicate_file, check_duplicate_cust_ref,  file_encryption_method, reject_file_on_error, merge_demerge_allowed, bulk_draft_on_error, county, country_name, treasury_branch_reference, psml_template,liquidity_frequency, liquidity_balance_type, liquidity_ccy_cur_code, time_zone,rmGroup)  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_INSERT_GTP_COMPANY = "insert into GTP_COMPANY (abbv_name, company_id, company_group, name, address_line_1, address_line_2, dom,  country, contact_name, phone, fax, telex, iso_code, reference, email, web_address, type,  owner_id, base_cur_code, language, charge_account_address_line_1, charge_account_address_line_2, charge_account_address_line_3, charge_account_address_line_4, charge_account,  actv_flag, dual_control, password_expiry, attachment_max_upload_size, retention_period, bei, street_name, post_code, town_name, country_sub_div, crm_email, legal_id_type,  legal_id_no, country_legalid, authorize_own_transaction, bulk_authorize_limit, auto_fwd_date, check_file_hash_value, check_duplicate_file, check_duplicate_cust_ref,  file_encryption_method, reject_file_on_error, merge_demerge_allowed, bulk_draft_on_error, county, country_name, treasury_branch_reference, created, psml_template,liquidity_frequency, liquidity_balance_type, liquidity_ccy_cur_code, time_zone,rmGroup)  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_MASTER_COLUMNS = "company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone";
    private static final String SQL_TRANSACTION_COLUMNS = "company.tnx_id, company.tnx_type_code, company.tnx_stat_code, company.maker_id, company.checker_id, company.maker_dttm, company.checker_dttm, maker_user.login_id";
    private static final String SQL_SELECT_CUSTOMERS_ACK = "select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, null, null, null, null, null, null, null, null, 'MASTER' as record_type ,company.rmGroup\tfrom GTP_COMPANY company where 1=1 and company.company_id not in (select company_tnx.company_id from GTP_COMPANY_TNX company_tnx where company_tnx.tnx_stat_code in (? ,? ,?) and company_tnx.tnx_type_code in (? ,?))";
    private static final String SQL_SELECT_CUSTOMERS_NEW = "select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, company.tnx_id, company.tnx_type_code, company.tnx_stat_code, company.maker_id, company.checker_id, company.maker_dttm, company.checker_dttm, maker_user.login_id, null,company.rmGroup from GTP_COMPANY_TNX company left join GTP_USER maker_user on company.maker_id = maker_user.user_id where 1=1 and company.tnx_type_code = ? and company.tnx_stat_code = ?";
    private static final String SQL_SELECT_CUSTOMERS_UPDATE_OR_DELETE = "select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, company.tnx_id, company.tnx_type_code, company.tnx_stat_code, company.maker_id, company.checker_id, company.maker_dttm, company.checker_dttm, maker_user.login_id, null,company.rmGroup from GTP_COMPANY_TNX company left join GTP_USER maker_user on company.maker_id = maker_user.user_id where 1=1 and company.tnx_stat_code = ? and company.tnx_type_code = ? ";
    private static final String SQL_SELECT_CUSTOMERS_DRAFT_RETURN = "select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, company.tnx_id, company.tnx_type_code, company.tnx_stat_code, company.maker_id, company.checker_id, company.maker_dttm, company.checker_dttm, maker_user.login_id, null,company.rmGroup from GTP_COMPANY_TNX company left join GTP_USER maker_user on company.maker_id = maker_user.user_id where 1=1 and company.tnx_stat_code in (?,?)";
    private static final String SQL_SELECT_ATTACHED_CUSTOMERS_FOR_BANK = "select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, null, null, null, null, null, null, null, null, 'MASTER' as record_type ,company.rmGroup\tfrom GTP_COMPANY company where 1=1 and company.owner_id <> ?  and company.company_id in (select customer.customer_id from GTP_CUSTOMER_BANK customer where bank_id = ? )";
    private static final String SQL_SELECT_ATTACHED_CUSTOMERS_FOR_BANK_MC = "select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, null, null, null, null, null, null, null, null, 'MASTER' as record_type ,company.rmGroup from GTP_COMPANY company where 1=1 and company.owner_id <> ?  and company.company_id in (select customer.customer_id from GTP_CUSTOMER_BANK customer where bank_id = ? ) and company.company_id NOT IN  (SELECT company_tnx.company_id FROM GTP_COMPANY_TNX company_tnx where company_tnx.tnx_stat_code IN (?,?) and company_tnx.tnx_type_code IN (? ,?))";
    private static final String SQL_SELECT_ATTACHED_CUSTOMERS_FOR_BANK_UPDATE_OR_DELETE = "select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone ,  tnx.tnx_id, tnx.tnx_type_code, tnx.tnx_stat_code, tnx.maker_id, tnx.checker_id, tnx.maker_dttm, tnx.checker_dttm, null, 'MASTER' as record_type ,company.rmGroup from GTP_COMPANY company left join GTP_COMPANY_TNX tnx  on company.company_id = tnx.company_id  where 1=1 and company.owner_id <> ?  and company.company_id in (select customer.customer_id from GTP_CUSTOMER_BANK customer where bank_id = ? ) and tnx.tnx_stat_code IN (?,?)";
    private static final String SQL_SELECT_BANK_ACK = "select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, null, null, null, null, null, null, null, null, 'MASTER' as record_type ,company.rmGroup\tfrom GTP_COMPANY company where 1=1 and company.company_id not in (select company_tnx.company_id from GTP_COMPANY_TNX company_tnx where company_tnx.tnx_stat_code in (? ,? ,?) and company_tnx.tnx_type_code in (? ,?,?,?))";
    private static final String SQL_SELECT_BANK_UPDATE_OR_DELETE = "select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, company.tnx_id, company.tnx_type_code, company.tnx_stat_code, company.maker_id, company.checker_id, company.maker_dttm, company.checker_dttm, maker_user.login_id, 'TRANSACTION' as record_type ,company.rmGroup from GTP_COMPANY_TNX company left join GTP_USER maker_user on company.maker_id = maker_user.user_id where 1=1 and company.tnx_stat_code = ? and company.tnx_type_code in (?,?) ";
    public static final String COMPANY_SEQUENCE_NAME = "GTP_COMPANY_SEQ";

    @Override
    public void addCompany(GTPCompany company) throws GTPException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            try {
                con = PoolBrokerService.getConnection();
                if (company.getOwner_id() == null) {
                    throw new GTPException(null, "Missing owner id, can't save company");
                }
                if (company.getIdAsObject() == null) {
                    company.setId(Utils.getNextId("GTP_COMPANY_SEQ"));
                }
                if (company.getCreated() == null) {
                    company.setCreated(new Date());
                }
                ArrayList<Object> bindValues = new ArrayList<Object>();
                bindValues.add(company.getAbbv_name());
                bindValues.add(company.getIdAsInt());
                bindValues.add(company.getCompany_group());
                bindValues.add(company.getName());
                bindValues.add(company.getAddress_line_1());
                bindValues.add(company.getAddress_line_2());
                bindValues.add(company.getDom());
                bindValues.add(company.getCountry());
                bindValues.add(company.getContact_name());
                bindValues.add(company.getPhone());
                bindValues.add(company.getFax());
                bindValues.add(company.getTelex());
                bindValues.add(company.getIso_code());
                bindValues.add(company.getReference());
                bindValues.add(company.getEmail());
                bindValues.add(company.getWeb_address());
                bindValues.add(company.getType());
                bindValues.add(company.getOwner_id());
                bindValues.add(company.getBase_cur_code());
                bindValues.add(company.getLanguage());
                bindValues.add(company.getCharge_account_address_line_1());
                bindValues.add(company.getCharge_account_address_line_2());
                bindValues.add(company.getCharge_account_address_line_3());
                bindValues.add(company.getCharge_account_address_line_4());
                bindValues.add(company.getCharge_account());
                bindValues.add(company.getActvFlag());
                bindValues.add(company.getDualControl());
                bindValues.add(company.getPasswordExpiry());
                bindValues.add(company.getAttachmentMaxUploadSize());
                bindValues.add(company.getRetentionPeriod());
                bindValues.add(company.getBei());
                bindValues.add(company.getStreet_name());
                bindValues.add(company.getPost_code());
                bindValues.add(company.getTown_name());
                bindValues.add(company.getCountry_sub_div());
                bindValues.add(company.getCRMEmail());
                bindValues.add(company.getLegalIdType());
                bindValues.add(company.getLegalIdNo());
                bindValues.add(company.getCountryLegalId());
                bindValues.add(company.getAuthorizeOwnTransaction());
                bindValues.add(company.getBulkAuthorizeLimit());
                bindValues.add(company.getAutoFwdDate());
                bindValues.add(company.getCheckFileHashValue());
                bindValues.add(company.getCheckDuplicateFile());
                bindValues.add(company.getCheckDuplicateCustRef());
                bindValues.add(company.getFileEncryptionMethod());
                bindValues.add(company.getRejectFileOnError());
                bindValues.add(company.getMergeDemergeAllowed());
                bindValues.add(company.getBulkDraftOnError());
                bindValues.add(company.getCounty());
                bindValues.add(company.getCountry_name());
                bindValues.add(company.getTreasury_branch_reference());
                bindValues.add(company.getCreated());
                bindValues.add(company.getPSMLTemplate());
                bindValues.add(company.getLiquidityfrequency());
                bindValues.add(company.getLiquiditybalancetype());
                bindValues.add(company.getLiquiditycurrency());
                bindValues.add(company.getTimeZone());
                bindValues.add(company.getRmGroup());
                stmt = con.prepareStatement("insert into GTP_COMPANY (abbv_name, company_id, company_group, name, address_line_1, address_line_2, dom,  country, contact_name, phone, fax, telex, iso_code, reference, email, web_address, type,  owner_id, base_cur_code, language, charge_account_address_line_1, charge_account_address_line_2, charge_account_address_line_3, charge_account_address_line_4, charge_account,  actv_flag, dual_control, password_expiry, attachment_max_upload_size, retention_period, bei, street_name, post_code, town_name, country_sub_div, crm_email, legal_id_type,  legal_id_no, country_legalid, authorize_own_transaction, bulk_authorize_limit, auto_fwd_date, check_file_hash_value, check_duplicate_file, check_duplicate_cust_ref,  file_encryption_method, reject_file_on_error, merge_demerge_allowed, bulk_draft_on_error, county, country_name, treasury_branch_reference, created, psml_template,liquidity_frequency, liquidity_balance_type, liquidity_ccy_cur_code, time_zone,rmGroup)  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                int i = 1;
                for (Object object : bindValues) {
                    stmt.setObject(i++, object);
                }
                stmt.executeUpdate();
            }
            catch (Exception e) {
                throw new GTPException(null, "Error saving gtpCompany: ", e);
            }
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (Exception exc) {
                    GTPException.log("Error in closing resource", exc);
                }
            }
            try {
                PoolBrokerService.releaseConnection(con);
            }
            catch (Exception exc) {
                GTPException.log("Error in closing resource", exc);
            }
        }
    }

    @Override
    public void addCompanyTnx(GTPCompanyTnx companyTnx) throws GTPException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            try {
                con = PoolBrokerService.getConnection();
                if (companyTnx.getOwner_id() == null) {
                    throw new GTPException(null, "Missing owner id, can't save companyTnx");
                }
                if (companyTnx.getIdAsObject() == null) {
                    companyTnx.setId(Utils.getNextId("GTP_COMPANY_SEQ"));
                }
                ArrayList<Object> bindValues = new ArrayList<Object>();
                bindValues.add(companyTnx.getAbbv_name());
                bindValues.add(companyTnx.getIdAsInt());
                bindValues.add(companyTnx.getCompany_group());
                bindValues.add(companyTnx.getName());
                bindValues.add(companyTnx.getAddress_line_1());
                bindValues.add(companyTnx.getAddress_line_2());
                bindValues.add(companyTnx.getDom());
                bindValues.add(companyTnx.getCountry());
                bindValues.add(companyTnx.getContact_name());
                bindValues.add(companyTnx.getPhone());
                bindValues.add(companyTnx.getFax());
                bindValues.add(companyTnx.getTelex());
                bindValues.add(companyTnx.getIso_code());
                bindValues.add(companyTnx.getReference());
                bindValues.add(companyTnx.getEmail());
                bindValues.add(companyTnx.getWeb_address());
                bindValues.add(companyTnx.getType());
                bindValues.add(companyTnx.getOwner_id());
                bindValues.add(companyTnx.getBase_cur_code());
                bindValues.add(companyTnx.getLanguage());
                bindValues.add(companyTnx.getTnxId());
                bindValues.add(companyTnx.getTnxTypeCode());
                bindValues.add(companyTnx.getTnxStatCode());
                bindValues.add(companyTnx.getMakerId());
                bindValues.add(companyTnx.getCheckerId() == null ? 0 : companyTnx.getCheckerId());
                bindValues.add(companyTnx.getMakerDateTime());
                bindValues.add(companyTnx.getReturnComments());
                bindValues.add(companyTnx.getCharge_account_address_line_1());
                bindValues.add(companyTnx.getCharge_account_address_line_2());
                bindValues.add(companyTnx.getCharge_account_address_line_3());
                bindValues.add(companyTnx.getCharge_account_address_line_4());
                bindValues.add(companyTnx.getCharge_account());
                bindValues.add(companyTnx.getActvFlag());
                bindValues.add(companyTnx.getDualControl());
                bindValues.add(companyTnx.getPasswordExpiry());
                bindValues.add(companyTnx.getAttachmentMaxUploadSize());
                bindValues.add(companyTnx.getRetentionPeriod());
                bindValues.add(companyTnx.getBei());
                bindValues.add(companyTnx.getStreet_name());
                bindValues.add(companyTnx.getPost_code());
                bindValues.add(companyTnx.getTown_name());
                bindValues.add(companyTnx.getCountry_sub_div());
                bindValues.add(companyTnx.getCRMEmail());
                bindValues.add(companyTnx.getLegalIdType());
                bindValues.add(companyTnx.getLegalIdNo());
                bindValues.add(companyTnx.getCountryLegalId());
                bindValues.add(companyTnx.getAuthorizeOwnTransaction());
                bindValues.add(companyTnx.getBulkAuthorizeLimit());
                bindValues.add(companyTnx.getAutoFwdDate());
                bindValues.add(companyTnx.getCheckFileHashValue());
                bindValues.add(companyTnx.getCheckDuplicateFile());
                bindValues.add(companyTnx.getCheckDuplicateCustRef());
                bindValues.add(companyTnx.getFileEncryptionMethod());
                bindValues.add(companyTnx.getRejectFileOnError());
                bindValues.add(companyTnx.getMergeDemergeAllowed());
                bindValues.add(companyTnx.getBulkDraftOnError());
                bindValues.add(companyTnx.getCounty());
                bindValues.add(companyTnx.getCountry_name());
                bindValues.add(companyTnx.getTreasury_branch_reference());
                bindValues.add(companyTnx.getPSMLTemplate());
                bindValues.add(companyTnx.getLiquidityfrequency());
                bindValues.add(companyTnx.getLiquiditybalancetype());
                bindValues.add(companyTnx.getLiquiditycurrency());
                bindValues.add(companyTnx.getTimeZone());
                bindValues.add(companyTnx.getRmGroup());
                stmt = con.prepareStatement("insert into GTP_COMPANY_TNX (abbv_name, company_id, company_group, name, address_line_1, address_line_2, dom, country, contact_name, phone,  fax, telex, iso_code, reference, email, web_address, type, owner_id, base_cur_code, language, tnx_id, tnx_type_code, tnx_stat_code, maker_id,   checker_id, maker_dttm, return_comments, charge_account_address_line_1, charge_account_address_line_2, charge_account_address_line_3, charge_account_address_line_4, charge_account,  actv_flag, dual_control, password_expiry, attachment_max_upload_size, retention_period, bei, street_name, post_code, town_name, country_sub_div, crm_email, legal_id_type,  legal_id_no, country_legalid, authorize_own_transaction, bulk_authorize_limit, auto_fwd_date, check_file_hash_value, check_duplicate_file, check_duplicate_cust_ref,  file_encryption_method, reject_file_on_error, merge_demerge_allowed, bulk_draft_on_error, county, country_name, treasury_branch_reference, psml_template,liquidity_frequency, liquidity_balance_type, liquidity_ccy_cur_code, time_zone,rmGroup)  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                int i = 1;
                for (Object object : bindValues) {
                    stmt.setObject(i++, object);
                }
                stmt.executeUpdate();
                stmt.close();
                stmt = null;
            }
            catch (Exception e) {
                throw new GTPException(null, "Error saving gtpCompanyTnx: ", e);
            }
        }
        finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (Exception e) {
                GTPException.log("DBCompanyManager : Failed to release the resource while executing DBCompanyManager.addCompanyTnx.", e);
            }
            try {
                PoolBrokerService.releaseConnection(con);
            }
            catch (Exception e) {
                GTPException.log("DBCompanyManager : Failed to release the connection while executing DBCompanyManager.addCompanyTnx.", e);
            }
        }
    }

    @Override
    public boolean companyExists(GTPCompany company) throws GTPException {
        return this.companyExists(company.getAbbv_name());
    }

    @Override
    public boolean companyExists(String companyAbbvName) throws GTPException {
        boolean exists;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        exists = false;
        try {
            try {
                con = PoolBrokerService.getConnection();
                StringBuilder query = new StringBuilder();
                query.append("select abbv_name from gtp_company ");
                query.append("where abbv_name = ? ");
                stmt = con.prepareStatement(query.toString());
                stmt.setString(1, companyAbbvName);
                rset = stmt.executeQuery();
                if (rset.next()) {
                    exists = true;
                }
            }
            catch (Exception e) {
                throw new GTPException(null, "E10003 - SQL Error while processing CompanyManager.exists(companyAbbvName):", e);
            }
        }
        finally {
            Utils.closeResources(con, stmt, rset);
        }
        return exists;
    }

    @Override
    public boolean companyTnxExists(GTPCompanyTnx companyTnx) throws GTPException {
        return this.companyTnxExists(companyTnx.getAbbv_name());
    }

    @Override
    public boolean companyTnxExists(String companyAbbvName) throws GTPException {
        boolean exists;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        exists = false;
        try {
            try {
                con = PoolBrokerService.getConnection();
                StringBuilder query = new StringBuilder();
                query.append("select abbv_name from gtp_company_tnx ");
                query.append("where abbv_name = ? ").append(" and tnx_stat_code in (").append("01").append(",").append("02").append(",").append("54").append(")");
                stmt = con.prepareStatement(query.toString());
                stmt.setString(1, companyAbbvName);
                rset = stmt.executeQuery();
                if (rset.next()) {
                    exists = true;
                }
                stmt.close();
                stmt = null;
            }
            catch (Exception e) {
                throw new GTPException(null, "E10003 - SQL Error while processing CompanyManager.exists(companyAbbvName):", e);
            }
        }
        finally {
            Utils.closeResources(con, stmt, rset);
        }
        return exists;
    }

    protected void fillAdditionalFields(GTPCompany company) throws GTPException {
    }

    @Override
    public void init(Configuration conf) {
    }

    private void populateCompany(GTPCompany company, ResultSet rset) throws SQLException {
        company.setId(rset.getInt("COMPANY_ID"));
        company.setCompany_group(rset.getBigDecimal("COMPANY_GROUP"));
        company.setName(rset.getString("NAME"));
        company.setAddress_line_1(rset.getString("ADDRESS_LINE_1"));
        company.setAddress_line_2(rset.getString("ADDRESS_LINE_2"));
        company.setDom(rset.getString("DOM"));
        company.setCountry(rset.getString("COUNTRY"));
        company.setContact_name(rset.getString("CONTACT_NAME"));
        company.setPhone(rset.getString("PHONE"));
        company.setFax(rset.getString("FAX"));
        company.setTelex(rset.getString("TELEX"));
        company.setIso_code(rset.getString("ISO_CODE"));
        company.setReference(rset.getString("REFERENCE"));
        company.setEmail(rset.getString("EMAIL"));
        company.setWeb_address(rset.getString("WEB_ADDRESS"));
        company.setType(rset.getString("TYPE"));
        company.setOwner_id(new Integer(rset.getInt("OWNER_ID")));
        company.setBase_cur_code(rset.getString("BASE_CUR_CODE"));
        company.setLanguage(rset.getString("LANGUAGE"));
        company.setAbbv_name(rset.getString("ABBV_NAME"));
        company.setCharge_account_address_line_1(rset.getString("CHARGE_ACCOUNT_ADDRESS_LINE_1"));
        company.setCharge_account_address_line_2(rset.getString("CHARGE_ACCOUNT_ADDRESS_LINE_2"));
        company.setCharge_account_address_line_3(rset.getString("CHARGE_ACCOUNT_ADDRESS_LINE_3"));
        company.setCharge_account_address_line_4(rset.getString("CHARGE_ACCOUNT_ADDRESS_LINE_4"));
        company.setCharge_account(rset.getString("CHARGE_ACCOUNT"));
        company.setActvFlag(rset.getString("actv_flag"));
        company.setDualControl(rset.getString("dual_control"));
        company.setPasswordExpiry(rset.getString("password_expiry"));
        company.setAttachmentMaxUploadSize(rset.getString("attachment_max_upload_size"));
        company.setRetentionPeriod(rset.getString("retention_period"));
        company.setBei(rset.getString("bei"));
        company.setStreet_name(rset.getString("street_name"));
        company.setPost_code(rset.getString("post_code"));
        company.setTown_name(rset.getString("town_name"));
        company.setCountry_sub_div(rset.getString("country_sub_div"));
        company.setCRMEmail(rset.getString("crm_email"));
        company.setLegalIdType(rset.getString("legal_id_type"));
        company.setLegalIdNo(rset.getString("legal_id_no"));
        company.setCountryLegalId(rset.getString("country_legalid"));
        company.setAuthorizeOwnTransaction(rset.getString("authorize_own_transaction"));
        company.setBulkAuthorizeLimit(rset.getString("bulk_authorize_limit"));
        company.setAutoFwdDate(rset.getString("auto_fwd_date"));
        company.setCheckFileHashValue(rset.getString("check_file_hash_value"));
        company.setCheckDuplicateFile(rset.getString("check_duplicate_file"));
        company.setCheckDuplicateCustRef(rset.getString("check_duplicate_cust_ref"));
        company.setFileEncryptionMethod(rset.getString("file_encryption_method"));
        company.setRejectFileOnError(rset.getString("reject_file_on_error"));
        company.setMergeDemergeAllowed(rset.getString("merge_demerge_allowed"));
        company.setBulkDraftOnError(rset.getString("bulk_draft_on_error"));
        company.setCounty(rset.getString("county"));
        company.setCountry_name(rset.getString("country_name"));
        company.setTreasury_branch_reference(rset.getString("treasury_branch_reference"));
        company.setCreated(rset.getTimestamp("created"));
        company.setPSMLTemplate(rset.getString("psml_template"));
        company.setLiquidityfrequency(rset.getString("liquidity_frequency"));
        company.setLiquiditybalancetype(rset.getString("liquidity_balance_type"));
        company.setLiquiditycurrency(rset.getString("liquidity_ccy_cur_code"));
        company.setTimeZone(rset.getString("TIME_ZONE"));
        company.setRmGroup(rset.getString("rmGroup"));
    }

    private void populateCompanyTnx(GTPCompanyTnx companyTnx, ResultSet rset) throws SQLException {
        companyTnx.setTnxId(rset.getString("TNX_ID"));
        companyTnx.setTnxTypeCode(rset.getString("TNX_TYPE_CODE"));
        companyTnx.setTnxStatCode(rset.getString("TNX_STAT_CODE"));
        companyTnx.setMakerId(rset.getInt("MAKER_ID"));
        companyTnx.setCheckerId(rset.getInt("CHECKER_ID"));
        companyTnx.setMakerDateTime(rset.getTimestamp("MAKER_DTTM"));
        companyTnx.setCheckerDateTime(rset.getTimestamp("CHECKER_DTTM"));
        companyTnx.setMakerUserName(rset.getString("LOGIN_ID"));
    }

    @Override
    public void remove(GTPCompany company) throws GTPException {
        if (company.getIdAsObject() != null) {
            Connection con = null;
            PreparedStatement stmt = null;
            try {
                try {
                    con = PoolBrokerService.getConnection();
                    StringBuilder query = new StringBuilder();
                    query.append("Delete from gtp_company ");
                    query.append("where company_id = ? ");
                    stmt = con.prepareStatement(query.toString());
                    stmt.setInt(1, company.getIdAsInt());
                    stmt.executeUpdate();
                }
                catch (Exception e) {
                    throw new GTPException(null, "E10003 - Error while processing GTPCustomer.delete.", e);
                }
            }
            finally {
                try {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (Exception e) {
                            new com.misys.portal.common.tracer.GTPException("Delete company, problem while closing the statment.", e);
                        }
                    }
                    PoolBrokerService.releaseConnection(con);
                }
                catch (Exception e) {
                    new com.misys.portal.common.tracer.GTPException("Delete company, problem while releasing the connection.", e);
                }
            }
        }
    }

    @Override
    public GTPCompany retrieve(int companyId1) throws GTPException {
        int companyId =401;
        Connection con = null;
        ResultSet rset = null;
        PreparedStatement stmt = null;
        StringBuilder query = new StringBuilder();
        GTPCompany company = null;
        try {
            con = PoolBrokerService.getConnection();
            query.append("select ").append("company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone").append(", company.rmGroup").append(" from gtp_company company").append(" where company_id = ?");
            stmt = con.prepareStatement(query.toString());
            stmt.setInt(1, companyId);
            rset = stmt.executeQuery();
            if (rset.next()) {
                company = GTPSecurity.getCompanyInstance();
                this.populateCompany(company, rset);
            }
            GTPCompany gTPCompany = company;
            return gTPCompany;
        }
        catch (Exception e) {
            throw new GTPException(null, "E10003 - Error while retrieving company : ", e);
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (Exception exc) {
                    GTPException.log("Error in closing resource", exc);
                }
            }
            try {
                PoolBrokerService.releaseConnection(con);
            }
            catch (Exception exc) {
                GTPException.log("Error in closing resource", exc);
            }
        }
    }

    @Override
    public GTPCompany retrieve(String companyAbbvName) throws GTPException, CompanyNotFoundException {
        System.out.println("<<< Company Sent >>>"+companyAbbvName);
        //String companyAbbvName="DANGOTEBH";
        Connection con = null;
        ResultSet rset = null;
        PreparedStatement stmt = null;
        StringBuilder query = new StringBuilder();
        GTPCompany company = null;
        try {
            con = PoolBrokerService.getConnection();
            query.append("select ").append("company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone").append(", company.rmGroup").append(" from gtp_company company").append(" where abbv_name = ?");
            stmt = con.prepareStatement(query.toString());
            stmt.setString(1, companyAbbvName);
            rset = stmt.executeQuery();
            if (!rset.next()) {
                Log.warn(DBCompanyManager.class, "Company " + companyAbbvName + " not found");
                throw new CompanyNotFoundException("Company " + companyAbbvName + " not found");
            }
            company = GTPSecurity.getCompanyInstance();
            this.populateCompany(company, rset);
            stmt.close();
            GTPCompany gTPCompany = company;
            return gTPCompany;
        }
        catch (GTPException gtpException) {
            throw gtpException;
        }
        catch (Exception se) {
            throw new GTPException("E10002 - SQL Error while retrieving company : ", se);
        }
        finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                PoolBrokerService.releaseConnection(con);
            }
            catch (Exception exc) {
                Log.error(DBCompanyManager.class, "Can't close connection.", exc);
            }
        }
    }

    @Override
    public GTPCompany retrieve(String companyAbbvName, List<String> types) throws GTPException, CompanyNotFoundException {
        GTPCompany company = this.retrieve(companyAbbvName);
        if (!types.contains(company.getType())) {
            throw new GTPException("The company " + companyAbbvName + " is not of the types specified.");
        }
        return company;
    }

    @Override
    public GTPCompany retrieve(String companyAbbvName, String type) throws GTPException, CompanyNotFoundException {
        GTPCompany company = this.retrieve(companyAbbvName);
        if (StringUtils.isNotEmpty((String)type) && !type.equals(company.getType())) {
            throw new GTPException("The company " + companyAbbvName + " is not of type: " + type);
        }
        return company;
    }

    @Override
    public CompanySet retrieveBranches(GTPCompany company) throws GTPException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        CompanySet result = new CompanySet();
        StringBuilder query = new StringBuilder();
        StringBuilder typeWhereClause = new StringBuilder();
        if (DefaultResourceProvider.CUSTOMER_COUNTERPARTY_TYPE_LIST.contains(company.getType())) {
            typeWhereClause.append("where (type in ('").append("03").append("', '").append("06").append("')");
        } else {
            typeWhereClause.append("where (type = '").append("01").append("' ").append("or type = '").append("02").append("') ");
        }
        try {
            con = PoolBrokerService.getConnection();
            query.append("select ").append("company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone").append(", company.rmGroup from gtp_company company ");
            query.append(typeWhereClause.toString());
            query.append("and (owner_id = ? ");
            query.append("or company_id = ? ) ");
            stmt = con.prepareStatement(query.toString());
            stmt.setInt(1, company.getIdAsInt());
            stmt.setInt(2, company.getIdAsInt());
            rset = stmt.executeQuery();
            GTPCompany partner = null;
            while (rset.next()) {
                partner = GTPSecurity.getCompanyInstance();
                this.populateCompany(partner, rset);
                result.add(partner);
            }
            CompanySet companySet = result;
            return companySet;
        }
        catch (Exception e) {
            if (query.length() == 0) {
                query = new StringBuilder("No Statement");
            }
            throw new GTPException("E20444 - initializeListOfBanksWithPotential, Exception : " + query.toString(), e);
        }
        finally {
            Utils.closeResources(con, stmt, rset);
        }
    }

    @Override
    public PaginationResult retrieveBranches(GTPCompany company, List<SearchCriteria> searchCriteriaList, PaginationParams paginationParameters) throws GTPException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        ArrayList<GTPCompany> companyList = new ArrayList<GTPCompany>();
        PaginationResult paginationResult = new PaginationResult();
        StringBuilder query = new StringBuilder();
        StringBuilder queryWithOutLimitRows = new StringBuilder();
        ArrayList<Integer> bindVarList = new ArrayList<Integer>();
        int counter = 0;
        int startIndex = paginationParameters.getStartIndex();
        int endIndex = paginationParameters.getEndIndex();
        String orderClause = "order by " + paginationParameters.getSortColumnNames().get(0) + " " + paginationParameters.getOrderBy();
        try {
            boolean isScrollableResultSet;
            con = PoolBrokerService.getConnection();
            DBAdapter dbAdapter = PoolBrokerService.getDBAdapter();
            query.append("select resultTable.* from (");
            query.append("\tselect company.company_id, company_group, name, address_line_1, address_line_2, dom,");
            query.append("\tcountry, contact_name, phone, fax, telex, iso_code, reference, email, web_address, type,");
            query.append("\towner_id, base_cur_code, language, abbv_name");
            query.append("\tfrom ").append("GTP_COMPANY").append(" company");
            query.append(" where 1=1 ");
            Utils.filterCriteriaBind(searchCriteriaList, query);
            query.append(") resultTable");
            queryWithOutLimitRows.append(query);
            if (DefaultResourceProvider.LIMIT_ROWS_FROM_RESULTSET) {
                query = new StringBuilder(dbAdapter.getLimitedRowsQuery(query.toString(), orderClause));
            }
            pstmt = con.prepareStatement(query.toString(), 1004, 1007);
            int indexValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, 1);
            if (DefaultResourceProvider.LIMIT_ROWS_FROM_RESULTSET) {
                bindVarList.add(endIndex);
                bindVarList.add(startIndex);
                indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                Log.info(DBCompanyManager.class, "DBCompanyManager.retrieveCustomers() >>>>>>>>>>>>>>>>>> Requesting Range :" + startIndex + "-" + endIndex);
            }
            isScrollableResultSet = true;
            try {
                rset = pstmt.executeQuery();
            }
            catch (SQLException v0) {
                isScrollableResultSet = false;
                pstmt.close();
                pstmt = con.prepareStatement(query.toString(), 1003, 1007);
                indexValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, 1);
                if (DefaultResourceProvider.LIMIT_ROWS_FROM_RESULTSET) {
                    indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                }
                rset = pstmt.executeQuery();
            }
            if (DefaultResourceProvider.LIMIT_ROWS_FROM_RESULTSET) {
                if (rset != null && !rset.next() && startIndex > 0) {
                    rset.close();
                    pstmt.close();
                    startIndex = 0;
                    query = new StringBuilder(dbAdapter.getLimitedRowsQuery(queryWithOutLimitRows.toString(), orderClause));
                    pstmt = con.prepareStatement(query.toString(), 1004, 1007);
                    indexValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, 1);
                    bindVarList.remove(bindVarList.size() - 1);
                    bindVarList.remove(bindVarList.size() - 1);
                    bindVarList.add(startIndex + paginationParameters.getRowsPerPage());
                    bindVarList.add(startIndex);
                    Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                    try {
                        rset = pstmt.executeQuery();
                    }
                    catch (SQLException e) {
                        Log.error(this.getClass(), "Error occured while re-querying : " + e);
                    }
                } else {
                    rset.beforeFirst();
                }
                GTPCompany partner = null;
                while (rset.next()) {
                    if (paginationResult.getNumRows() == 0) {
                        paginationResult.setNumRows(rset.getInt("count"));
                        Log.info(DBCompanyManager.class, "DBCompanyManager.retrieveCustomers() >>>>>>>>>>>>>>>>>> rows matched :" + rset.getInt("count"));
                    }
                    partner = GTPSecurity.getCompanyInstance();
                    partner.setId(rset.getInt(1));
                    partner.setCompany_group(new BigDecimal(rset.getInt(2)));
                    partner.setName(rset.getString(3));
                    partner.setAddress_line_1(rset.getString(4));
                    partner.setAddress_line_2(rset.getString(5));
                    partner.setDom(rset.getString(6));
                    partner.setCountry(rset.getString(7));
                    partner.setContact_name(rset.getString(8));
                    partner.setPhone(rset.getString(9));
                    partner.setFax(rset.getString(10));
                    partner.setTelex(rset.getString(11));
                    partner.setIso_code(rset.getString(12));
                    partner.setReference(rset.getString(13));
                    partner.setEmail(rset.getString(14));
                    partner.setWeb_address(rset.getString(15));
                    partner.setType(rset.getString(16));
                    partner.setOwner_id(new Integer(rset.getInt(17)));
                    partner.setBase_cur_code(rset.getString(18));
                    partner.setLanguage(rset.getString(19));
                    partner.setAbbv_name(rset.getString(20));
                    partner.setPerm("actv_flag", rset.getString(21));
                    this.fillAdditionalFields(partner);
                    companyList.add(partner);
                }
            } else {
                if (isScrollableResultSet && rset.last()) {
                    paginationResult.setNumRows(rset.getRow());
                    Log.debug(DBCompanyManager.class, "DBCompanyMange.retrieveCustomers(company,searchCriteriaList,paginationParameters): " + paginationResult.getNumRows() + " row(s) available in the Result Set");
                    if (paginationResult.getNumRows() < startIndex) {
                        startIndex = 0;
                        endIndex = startIndex + paginationParameters.getRowsPerPage();
                        paginationParameters.setStartIndex(startIndex);
                    }
                    if (paginationResult.getNumRows() < endIndex) {
                        endIndex = paginationResult.getNumRows();
                        paginationParameters.setEndIndex(endIndex);
                    }
                    rset.beforeFirst();
                }
                GTPCompany partner = null;
                while (rset.next()) {
                    if (counter >= startIndex && counter <= endIndex) {
                        partner = GTPSecurity.getCompanyInstance();
                        partner.setId(rset.getInt(1));
                        partner.setCompany_group(new BigDecimal(rset.getInt(2)));
                        partner.setName(rset.getString(3));
                        partner.setAddress_line_1(rset.getString(4));
                        partner.setAddress_line_2(rset.getString(5));
                        partner.setDom(rset.getString(6));
                        partner.setCountry(rset.getString(7));
                        partner.setContact_name(rset.getString(8));
                        partner.setPhone(rset.getString(9));
                        partner.setFax(rset.getString(10));
                        partner.setTelex(rset.getString(11));
                        partner.setIso_code(rset.getString(12));
                        partner.setReference(rset.getString(13));
                        partner.setEmail(rset.getString(14));
                        partner.setWeb_address(rset.getString(15));
                        partner.setType(rset.getString(16));
                        partner.setOwner_id(new Integer(rset.getInt(17)));
                        partner.setBase_cur_code(rset.getString(18));
                        partner.setLanguage(rset.getString(19));
                        partner.setAbbv_name(rset.getString(20));
                        this.fillAdditionalFields(partner);
                        companyList.add(partner);
                    }
                    if (counter < endIndex || !isScrollableResultSet) {
                        ++counter;
                        continue;
                    }
                    break;
                }
            }
            paginationResult.setResult(companyList);
            if (!isScrollableResultSet) {
                paginationResult.setNumRows(counter);
            }
            PaginationResult paginationResult2 = paginationResult;
            return paginationResult2;
        }
        catch (Exception e) {
            if (query.length() == 0) {
                query = new StringBuilder("No Statement");
            }
            throw new GTPException(null, "E20444 - getCustomers, Exception : " + query.toString(), e);
        }
        finally {
            Utils.closeResources(con, pstmt, rset);
        }
    }

    @Override
    public PaginationResult retrieveBranchesMC(List<SearchCriteria> searchCriteriaList, PaginationParams paginationParameters) throws GTPException {
        PaginationResult paginationResult;
        CompanySet result = new CompanySet();
        ArrayList<GTPCompanyTnx> customerList = new ArrayList<GTPCompanyTnx>();
        Connection con = null;
        StringBuilder query = new StringBuilder();
        StringBuilder unionQuery = new StringBuilder();
        StringBuilder queryWithOutLimitRows = new StringBuilder();
        DBAdapter dbAdapter = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        paginationResult = new PaginationResult();
        int counter = 0;
        int startIndex = paginationParameters.getStartIndex();
        int endIndex = paginationParameters.getEndIndex();
        String orderClause = "order by " + paginationParameters.getSortColumnNames().get(0) + " " + paginationParameters.getOrderBy();
        try {
            try {
                boolean isScrollableResultSet;
                con = PoolBrokerService.getConnection();
                dbAdapter = PoolBrokerService.getDBAdapter();
                ArrayList<Object> bindVarList = new ArrayList<Object>();
                query.append("select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, company.tnx_id, company.tnx_type_code, company.tnx_stat_code, company.maker_id, company.checker_id, company.maker_dttm, company.checker_dttm, maker_user.login_id, null,company.rmGroup from GTP_COMPANY_TNX company left join GTP_USER maker_user on company.maker_id = maker_user.user_id where 1=1 and company.tnx_type_code = ? and company.tnx_stat_code = ?");
                Utils.filterCriteriaBind(searchCriteriaList, query);
                unionQuery.append(query);
                query.setLength(0);
                query.append("select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, company.tnx_id, company.tnx_type_code, company.tnx_stat_code, company.maker_id, company.checker_id, company.maker_dttm, company.checker_dttm, maker_user.login_id, 'TRANSACTION' as record_type ,company.rmGroup from GTP_COMPANY_TNX company left join GTP_USER maker_user on company.maker_id = maker_user.user_id where 1=1 and company.tnx_stat_code = ? and company.tnx_type_code in (?,?) ");
                Utils.filterCriteriaBind(searchCriteriaList, query);
                unionQuery.append(" union ");
                unionQuery.append(query);
                query.setLength(0);
                query.append("select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, company.tnx_id, company.tnx_type_code, company.tnx_stat_code, company.maker_id, company.checker_id, company.maker_dttm, company.checker_dttm, maker_user.login_id, 'TRANSACTION' as record_type ,company.rmGroup from GTP_COMPANY_TNX company left join GTP_USER maker_user on company.maker_id = maker_user.user_id where 1=1 and company.tnx_stat_code = ? and company.tnx_type_code in (?,?) ");
                Utils.filterCriteriaBind(searchCriteriaList, query);
                unionQuery.append(" union ");
                unionQuery.append(query);
                query.setLength(0);
                query.append("select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, company.tnx_id, company.tnx_type_code, company.tnx_stat_code, company.maker_id, company.checker_id, company.maker_dttm, company.checker_dttm, maker_user.login_id, null,company.rmGroup from GTP_COMPANY_TNX company left join GTP_USER maker_user on company.maker_id = maker_user.user_id where 1=1 and company.tnx_stat_code in (?,?)");
                Utils.filterCriteriaBind(searchCriteriaList, query);
                unionQuery.append(" union ");
                unionQuery.append(query);
                boolean containsStatusorActorFilter = false;
                ArrayList<SearchCriteria> searchCriteriaListExcludeTnxColumns = new ArrayList<SearchCriteria>();
                for (SearchCriteria searchCriteria : searchCriteriaList) {
                    if (!searchCriteria.getColumnName().contains("tnx_stat_code") && !searchCriteria.getColumnName().contains("tnx_type_code")) {
                        searchCriteriaListExcludeTnxColumns.add(searchCriteria);
                        continue;
                    }
                    containsStatusorActorFilter = true;
                }
                query.setLength(0);
                if (!containsStatusorActorFilter) {
                    query.append("select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, null, null, null, null, null, null, null, null, 'MASTER' as record_type ,company.rmGroup\tfrom GTP_COMPANY company where 1=1 and company.company_id not in (select company_tnx.company_id from GTP_COMPANY_TNX company_tnx where company_tnx.tnx_stat_code in (? ,? ,?) and company_tnx.tnx_type_code in (? ,?,?,?))");
                    Utils.filterCriteriaBind(searchCriteriaListExcludeTnxColumns, query);
                    unionQuery.append(" union ");
                    unionQuery.append(query);
                }
                queryWithOutLimitRows.append(unionQuery);
                pstmt = DefaultResourceProvider.LIMIT_ROWS_FROM_RESULTSET ? con.prepareStatement(dbAdapter.getLimitedRowsQuery(unionQuery.toString(), orderClause), 1004, 1007) : con.prepareStatement(unionQuery.toString(), 1004, 1007);
                bindVarList.clear();
                bindVarList.add("01");
                bindVarList.add("01");
                int indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList);
                indexValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indexValue);
                bindVarList.clear();
                bindVarList.add("01");
                bindVarList.add("02");
                bindVarList.add("80");
                indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                indexValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indexValue);
                bindVarList.clear();
                bindVarList.add("01");
                bindVarList.add("12");
                bindVarList.add("80");
                indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                indexValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indexValue);
                bindVarList.clear();
                bindVarList.add("54");
                bindVarList.add("02");
                indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                indexValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indexValue);
                if (!containsStatusorActorFilter) {
                    bindVarList.clear();
                    bindVarList.add("01");
                    bindVarList.add("54");
                    bindVarList.add("02");
                    bindVarList.add("02");
                    bindVarList.add("12");
                    bindVarList.add("38");
                    bindVarList.add("80");
                    indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                    indexValue = Utils.filterCriteriaBindVariable(searchCriteriaListExcludeTnxColumns, pstmt, indexValue);
                }
                if (DefaultResourceProvider.LIMIT_ROWS_FROM_RESULTSET) {
                    bindVarList.clear();
                    bindVarList.add(endIndex);
                    bindVarList.add(startIndex);
                    indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                    Log.info(DBCompanyManager.class, "DBCompanyManager.retrieveCustomersMC() >>>>>>>>>>>>>>>>>> Requesting Range :" + startIndex + "-" + endIndex);
                }
                isScrollableResultSet = true;
                try {
                    rset = pstmt.executeQuery();
                }
                catch (SQLException v0) {
                    isScrollableResultSet = false;
                    pstmt.close();
                    pstmt = con.prepareStatement(query.toString(), 1003, 1007);
                    bindVarList.clear();
                    bindVarList.add("01");
                    bindVarList.add("01");
                    indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList);
                    indexValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indexValue);
                    bindVarList.clear();
                    bindVarList.add("01");
                    bindVarList.add("02");
                    indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                    indexValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indexValue);
                    bindVarList.clear();
                    bindVarList.add("01");
                    bindVarList.add("12");
                    indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                    indexValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indexValue);
                    bindVarList.clear();
                    bindVarList.add("54");
                    bindVarList.add("02");
                    indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                    indexValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indexValue);
                    if (!containsStatusorActorFilter) {
                        bindVarList.clear();
                        bindVarList.add("01");
                        bindVarList.add("54");
                        bindVarList.add("02");
                        bindVarList.add("02");
                        bindVarList.add("12");
                        bindVarList.add("38");
                        indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                        indexValue = Utils.filterCriteriaBindVariable(searchCriteriaListExcludeTnxColumns, pstmt, indexValue);
                    }
                    if (DefaultResourceProvider.LIMIT_ROWS_FROM_RESULTSET) {
                        bindVarList.clear();
                        bindVarList.add(endIndex);
                        bindVarList.add(startIndex);
                        indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                    }
                    rset = pstmt.executeQuery();
                }
                if (DefaultResourceProvider.LIMIT_ROWS_FROM_RESULTSET) {
                    if (rset != null && !rset.next() && startIndex > 0) {
                        rset.close();
                        pstmt.close();
                        startIndex = 0;
                        query = new StringBuilder(dbAdapter.getLimitedRowsQuery(queryWithOutLimitRows.toString(), orderClause));
                        pstmt = con.prepareStatement(query.toString(), 1004, 1007);
                        bindVarList.clear();
                        bindVarList.add("01");
                        bindVarList.add("01");
                        indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList);
                        indexValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indexValue);
                        bindVarList.clear();
                        bindVarList.add("01");
                        bindVarList.add("02");
                        indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                        indexValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indexValue);
                        bindVarList.clear();
                        bindVarList.add("01");
                        bindVarList.add("12");
                        indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                        indexValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indexValue);
                        bindVarList.clear();
                        bindVarList.add("54");
                        bindVarList.add("02");
                        indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                        indexValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indexValue);
                        if (!containsStatusorActorFilter) {
                            bindVarList.clear();
                            bindVarList.add("01");
                            bindVarList.add("54");
                            bindVarList.add("02");
                            bindVarList.add("02");
                            bindVarList.add("12");
                            indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                            indexValue = Utils.filterCriteriaBindVariable(searchCriteriaListExcludeTnxColumns, pstmt, indexValue);
                        }
                        bindVarList.clear();
                        bindVarList.add(endIndex);
                        bindVarList.add(startIndex);
                        indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                        try {
                            rset = pstmt.executeQuery();
                        }
                        catch (SQLException e) {
                            Log.error(this.getClass(), "Error occured while re-querying : " + e);
                        }
                    } else {
                        rset.beforeFirst();
                    }
                    GTPCompanyTnx partnerTnx = null;
                    while (rset.next()) {
                        if (paginationResult.getNumRows() == 0) {
                            paginationResult.setNumRows(rset.getInt("count"));
                            Log.info(DBCompanyManager.class, "DBCompanyManager.retrieveCustomersMC() >>>>>>>>>>>>>>>>>> rows matched :" + rset.getInt("count"));
                        }
                        partnerTnx = GTPSecurity.getCompanyTnxInstance();
                        partnerTnx.setId(rset.getInt("COMPANY_ID"));
                        partnerTnx.setCompany_group(rset.getBigDecimal("COMPANY_GROUP"));
                        partnerTnx.setName(rset.getString("NAME"));
                        partnerTnx.setAddress_line_1(rset.getString("ADDRESS_LINE_1"));
                        partnerTnx.setAddress_line_2(rset.getString("ADDRESS_LINE_2"));
                        partnerTnx.setDom(rset.getString("DOM"));
                        partnerTnx.setCountry(rset.getString("COUNTRY"));
                        partnerTnx.setContact_name(rset.getString("CONTACT_NAME"));
                        partnerTnx.setPhone(rset.getString("PHONE"));
                        partnerTnx.setFax(rset.getString("FAX"));
                        partnerTnx.setTelex(rset.getString("TELEX"));
                        partnerTnx.setIso_code(rset.getString("ISO_CODE"));
                        partnerTnx.setReference(rset.getString("REFERENCE"));
                        partnerTnx.setEmail(rset.getString("EMAIL"));
                        partnerTnx.setWeb_address(rset.getString("WEB_ADDRESS"));
                        partnerTnx.setType(rset.getString("TYPE"));
                        partnerTnx.setOwner_id(rset.getInt("OWNER_ID"));
                        partnerTnx.setBase_cur_code(rset.getString("BASE_CUR_CODE"));
                        partnerTnx.setLanguage(rset.getString("LANGUAGE"));
                        partnerTnx.setAbbv_name(rset.getString("ABBV_NAME"));
                        if ("MASTER".equals(rset.getString(67))) {
                            partnerTnx.setTnxStatCode("04");
                            partnerTnx.setTnxTypeCode("05");
                        } else {
                            partnerTnx.setTnxTypeCode(rset.getString("TNX_TYPE_CODE"));
                            partnerTnx.setTnxStatCode(rset.getString("TNX_STAT_CODE"));
                        }
                        partnerTnx.setTnxId(rset.getString("TNX_ID"));
                        partnerTnx.setMakerId(rset.getInt("MAKER_ID"));
                        partnerTnx.setCheckerId(rset.getInt("CHECKER_ID"));
                        partnerTnx.setMakerDateTime(rset.getTimestamp("MAKER_DTTM"));
                        partnerTnx.setCheckerDateTime(rset.getTimestamp("CHECKER_DTTM"));
                        partnerTnx.setMakerUserName(rset.getString("LOGIN_ID"));
                        result.add(partnerTnx);
                        customerList.add(partnerTnx);
                    }
                } else {
                    if (isScrollableResultSet && rset.last()) {
                        paginationResult.setNumRows(rset.getRow());
                        Log.debug(DBCompanyManager.class, "DBCompanyMange.retrieveBranches(company,searchCriteriaList,paginationParameters): " + paginationResult.getNumRows() + " row(s) available in the Result Set");
                        if (paginationResult.getNumRows() < startIndex) {
                            startIndex = 0;
                            endIndex = startIndex + paginationParameters.getRowsPerPage();
                            paginationParameters.setStartIndex(startIndex);
                        }
                        if (paginationResult.getNumRows() < endIndex) {
                            endIndex = paginationResult.getNumRows();
                            paginationParameters.setEndIndex(endIndex);
                        }
                        rset.beforeFirst();
                    }
                    GTPCompanyTnx partnerTnx = null;
                    while (rset.next()) {
                        if (counter >= startIndex && counter <= endIndex) {
                            partnerTnx = GTPSecurity.getCompanyTnxInstance();
                            partnerTnx.setId(rset.getInt("COMPANY_ID"));
                            partnerTnx.setCompany_group(rset.getBigDecimal("COMPANY_GROUP"));
                            partnerTnx.setName(rset.getString("NAME"));
                            partnerTnx.setAddress_line_1(rset.getString("ADDRESS_LINE_1"));
                            partnerTnx.setAddress_line_2(rset.getString("ADDRESS_LINE_2"));
                            partnerTnx.setDom(rset.getString("DOM"));
                            partnerTnx.setCountry(rset.getString("COUNTRY"));
                            partnerTnx.setContact_name(rset.getString("CONTACT_NAME"));
                            partnerTnx.setPhone(rset.getString("PHONE"));
                            partnerTnx.setFax(rset.getString("FAX"));
                            partnerTnx.setTelex(rset.getString("TELEX"));
                            partnerTnx.setIso_code(rset.getString("ISO_CODE"));
                            partnerTnx.setReference(rset.getString("REFERENCE"));
                            partnerTnx.setEmail(rset.getString("EMAIL"));
                            partnerTnx.setWeb_address(rset.getString("WEB_ADDRESS"));
                            partnerTnx.setType(rset.getString("TYPE"));
                            partnerTnx.setOwner_id(rset.getInt("OWNER_ID"));
                            partnerTnx.setBase_cur_code(rset.getString("BASE_CUR_CODE"));
                            partnerTnx.setLanguage(rset.getString("LANGUAGE"));
                            partnerTnx.setAbbv_name(rset.getString("ABBV_NAME"));
                            if ("MASTER".equals(rset.getString(67))) {
                                partnerTnx.setTnxStatCode("04");
                                partnerTnx.setTnxTypeCode("05");
                            } else {
                                partnerTnx.setTnxTypeCode(rset.getString("TNX_TYPE_CODE"));
                                partnerTnx.setTnxStatCode(rset.getString("TNX_STAT_CODE"));
                            }
                            partnerTnx.setTnxId(rset.getString("TNX_ID"));
                            partnerTnx.setMakerId(rset.getInt("MAKER_ID"));
                            partnerTnx.setCheckerId(rset.getInt("CHECKER_ID"));
                            partnerTnx.setMakerDateTime(rset.getTimestamp("MAKER_DTTM"));
                            partnerTnx.setCheckerDateTime(rset.getTimestamp("CHECKER_DTTM"));
                            partnerTnx.setMakerUserName(rset.getString("LOGIN_ID"));
                            result.add(partnerTnx);
                            customerList.add(partnerTnx);
                        }
                        if (counter < endIndex || !isScrollableResultSet) {
                            ++counter;
                            continue;
                        }
                        break;
                    }
                }
                paginationResult.setResult(customerList);
                if (!isScrollableResultSet) {
                    paginationResult.setNumRows(counter);
                }
                rset = null;
            }
            catch (Exception e) {
                throw new GTPException(null, "DBSecurityService : Failed to release the connection while processing SubscriptionPackageFileTnx.revokeTnx.", e);
            }
        }
        finally {
            Utils.closeResources(con, pstmt, rset);
        }
        return paginationResult;
    }

    @Override
    public CompanySet retrieveCustomers(GTPCompany company) throws GTPException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        CompanySet result = new CompanySet();
        StringBuilder query = new StringBuilder();
        try {
            con = PoolBrokerService.getConnection();
            query.append("select ").append("company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone").append(", company.rmGroup from gtp_company company ");
            query.append("where type = '");
            query.append("03").append("' ");
            query.append("and owner_id = ? ");
            stmt = con.prepareStatement(query.toString());
            stmt.setInt(1, company.getIdAsInt());
            rset = stmt.executeQuery();
            GTPCompany partner = null;
            while (rset.next()) {
                partner = GTPSecurity.getCompanyInstance();
                this.populateCompany(partner, rset);
                result.add(partner);
            }
            CompanySet companySet = result;
            return companySet;
        }
        catch (Exception e) {
            if (query.length() == 0) {
                query = new StringBuilder("No Statement");
            }
            throw new GTPException(null, "E20444 - getCustomers, Exception : " + query.toString(), e);
        }
        finally {
            Utils.closeResources(con, stmt, rset);
        }
    }

    @Override
    public PaginationResult retrieveCustomers(GTPCompany company, PaginationParams paginationParameters, List<SearchCriteria> searchCriteriaList, CompanyComparator comparator, boolean getRelatedCustomer) throws GTPException {
        Connection con = null;
        PreparedStatement stmt = null;
        CompanySet result = new CompanySet(comparator);
        PaginationResult paginationResult = new PaginationResult();
        StringBuilder query = new StringBuilder();
        int counter = 0;
        int startIndex = paginationParameters.getStartIndex();
        int endIndex = paginationParameters.getEndIndex();
        ResultSet rset = null;
        try {
            Integer index;
            con = PoolBrokerService.getConnection();
            query.append("select  ").append("company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone").append(", company.rmGroup from gtp_company company ");
            query.append("where type in ('");
            query.append("06").append("', '");
            query.append("03").append("') ");
            if ("01".equals(company.getType())) {
                query.append("and ( owner_id =  ? ");
                if (getRelatedCustomer) {
                    query.append(" or company_id in ").append("( select customer_id from ").append("GTP_CUSTOMER_BANK").append(" where bank_id = ?").append(" )");
                }
                query.append(" )");
            } else {
                query.append("and owner_id =  ? ");
            }
            Utils.filterCriteriaBind(searchCriteriaList, query);
            if (!StringUtils.isEmpty((String)paginationParameters.getSortOrder())) {
                query.append(" order by ");
                query.append(paginationParameters.getSortOrder());
            }
            stmt = con.prepareStatement(query.toString(), 1004, 1007);
            Integer n = index = Integer.valueOf(1);
            index = n + 1;
            stmt.setInt(n, company.getIdAsInt());
            if ("01".equals(company.getType()) && getRelatedCustomer) {
                Integer n2 = index;
                index = n2 + 1;
                stmt.setInt(n2, company.getIdAsInt());
            }
            Utils.filterCriteriaBindVariable(searchCriteriaList, stmt, index);
            try {
                rset = stmt.executeQuery();
            }
            catch (SQLException v2) {
                Integer index1;
                paginationParameters.setCountIndex(0);
                stmt.close();
                stmt = con.prepareStatement(query.toString(), 1003, 1007);
                Integer n3 = index1 = Integer.valueOf(1);
                index1 = n3 + 1;
                stmt.setInt(n3, company.getIdAsInt());
                Utils.filterCriteriaBindVariable(searchCriteriaList, stmt, index1);
                rset = stmt.executeQuery();
            }
            if (paginationParameters.getCountIndex() != 0 && rset.last()) {
                paginationResult.setNumRows(rset.getRow());
                Log.debug(DBCompanyManager.class, String.valueOf(paginationResult.getNumRows()) + " row(s) available in the Result Set, ");
                if (paginationResult.getNumRows() < startIndex) {
                    startIndex = 0;
                    endIndex = startIndex + paginationParameters.getCountIndex();
                    paginationParameters.setStartIndex(startIndex);
                }
                if (paginationResult.getNumRows() < endIndex) {
                    endIndex = paginationResult.getNumRows();
                    paginationParameters.setEndIndex(endIndex);
                }
                rset.beforeFirst();
            }
            GTPCompany partner = null;
            while (rset.next()) {
                if (counter >= startIndex && counter < endIndex) {
                    partner = GTPSecurity.getCompanyInstance();
                    this.populateCompany(partner, rset);
                    result.add(partner);
                }
                if (counter >= endIndex && paginationParameters.getCountIndex() != 0) break;
                ++counter;
            }
            paginationResult.setResult(result);
            if (paginationParameters.getCountIndex() == 0) {
                paginationResult.setNumRows(counter);
            }
            PaginationResult paginationResult2 = paginationResult;
            return paginationResult2;
        }
        catch (Exception e) {
            if (query.length() == 0) {
                query = new StringBuilder("No Statement");
            }
            throw new GTPException(null, "E20444 - getCustomers, Exception : " + query.toString(), e);
        }
        finally {
            Utils.closeResources(con, stmt, rset);
        }
    }

    @Override
    public CompanySet retrieveCustomers(GTPCompany company, String customerStatus) throws GTPException {
        List ackRecords = null;
        List updateRecords = null;
        List newCustomersRecords = null;
        List deleteRecords = null;
        List returnRecords = null;
        HashMap<String, List> reportMap = new HashMap<String, List>();
        Connection con = null;
        CompanySet result = new CompanySet();
        try {
            con = PoolBrokerService.getConnection();
            Criteria ackCriteria = new Criteria();
            ackCriteria.addSelectColumn("GTP_COMPANY.COMPANY_ID");
            ackCriteria.addSelectColumn("GTP_COMPANY.COMPANY_GROUP");
            ackCriteria.addSelectColumn("GTP_COMPANY.NAME");
            ackCriteria.addSelectColumn("GTP_COMPANY.ADDRESS_LINE_1");
            ackCriteria.addSelectColumn("GTP_COMPANY.ADDRESS_LINE_2");
            ackCriteria.addSelectColumn("GTP_COMPANY.DOM");
            ackCriteria.addSelectColumn("GTP_COMPANY.COUNTRY");
            ackCriteria.addSelectColumn("GTP_COMPANY.CONTACT_NAME");
            ackCriteria.addSelectColumn("GTP_COMPANY.PHONE");
            ackCriteria.addSelectColumn("GTP_COMPANY.FAX");
            ackCriteria.addSelectColumn("GTP_COMPANY.TELEX");
            ackCriteria.addSelectColumn("GTP_COMPANY.ISO_CODE");
            ackCriteria.addSelectColumn("GTP_COMPANY.REFERENCE");
            ackCriteria.addSelectColumn("GTP_COMPANY.EMAIL");
            ackCriteria.addSelectColumn("GTP_COMPANY.WEB_ADDRESS");
            ackCriteria.addSelectColumn("GTP_COMPANY.TYPE");
            ackCriteria.addSelectColumn("GTP_COMPANY.OWNER_ID");
            ackCriteria.addSelectColumn("GTP_COMPANY.BASE_CUR_CODE");
            ackCriteria.addSelectColumn("GTP_COMPANY.LANGUAGE");
            ackCriteria.addSelectColumn("GTP_COMPANY.ABBV_NAME");
            ackCriteria.add("GTP_COMPANY.TYPE", (Object)"03");
            ackCriteria.add("GTP_COMPANY.OWNER_ID", company.getIdAsInt());
            Criteria pendingCustomersCriteria = new Criteria();
            pendingCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.COMPANY_ID");
            ArrayList<String> tnxTypeCodesList = new ArrayList<String>();
            tnxTypeCodesList.add("02");
            tnxTypeCodesList.add("12");
            pendingCustomersCriteria.addIn("GTP_COMPANY_TNX.TNX_TYPE_CODE", tnxTypeCodesList);
            ArrayList<String> tnxStatCodesList = new ArrayList<String>();
            tnxStatCodesList.add("01");
            tnxStatCodesList.add("54");
            tnxStatCodesList.add("02");
            pendingCustomersCriteria.addIn("GTP_COMPANY_TNX.TNX_STAT_CODE", tnxStatCodesList);
            List<Record> pendingCustomersRecordList = BasePeer.doSelect((Criteria)pendingCustomersCriteria, (Connection)con);
            ArrayList<String> pendingCustomersNamesList = new ArrayList<String>();
            if (pendingCustomersRecordList.size() > 0) {
                for (Record record : pendingCustomersRecordList) {
                    pendingCustomersNamesList.add(record.getValue("COMPANY_ID").asString());
                }
            }
            if (pendingCustomersNamesList.size() > 0) {
                ackCriteria.addNotIn("GTP_COMPANY.COMPANY_ID", pendingCustomersNamesList);
            }
            ackRecords = BasePeer.doSelect((Criteria)ackCriteria, (Connection)con);
            reportMap.put("RECORD_ACK", ackRecords);
            tnxStatCodesList = new ArrayList();
            tnxStatCodesList.add("01");
            tnxStatCodesList.add("02");
            Criteria newCustomersCriteria = new Criteria();
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.COMPANY_ID");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.COMPANY_GROUP");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.NAME");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.ADDRESS_LINE_1");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.ADDRESS_LINE_2");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.DOM");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.COUNTRY");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.CONTACT_NAME");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.PHONE");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.FAX");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.TELEX");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.ISO_CODE");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.REFERENCE");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.EMAIL");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.WEB_ADDRESS");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.TYPE");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.OWNER_ID");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.BASE_CUR_CODE");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.LANGUAGE");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.ABBV_NAME");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.MAKER_ID");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.TNX_ID");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.TNX_STAT_CODE");
            newCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.TNX_TYPE_CODE");
            newCustomersCriteria.addSelectColumn("GTP_USER.LOGIN_ID");
            newCustomersCriteria.addJoin("GTP_COMPANY_TNX.MAKER_ID", "GTP_USER.USER_ID");
            newCustomersCriteria.add("GTP_COMPANY_TNX.TYPE", (Object)"03");
            newCustomersCriteria.add("GTP_COMPANY_TNX.OWNER_ID", company.getIdAsInt());
            newCustomersCriteria.addIn("GTP_COMPANY_TNX.TNX_STAT_CODE", tnxStatCodesList);
            newCustomersCriteria.add("GTP_COMPANY_TNX.TNX_TYPE_CODE", (Object)"01");
            newCustomersRecords = BasePeer.doSelect((Criteria)newCustomersCriteria, (Connection)con);
            reportMap.put("RECORD_NEW", newCustomersRecords);
            Criteria updateCustomersCriteria = new Criteria();
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.COMPANY_ID");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.COMPANY_GROUP");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.NAME");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.ADDRESS_LINE_1");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.ADDRESS_LINE_2");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.DOM");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.COUNTRY");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.CONTACT_NAME");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.PHONE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.FAX");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.TELEX");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.ISO_CODE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.REFERENCE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.EMAIL");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.WEB_ADDRESS");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.TYPE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.OWNER_ID");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.BASE_CUR_CODE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.LANGUAGE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY.ABBV_NAME");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.MAKER_ID");
            updateCustomersCriteria.addSelectColumn("GTP_USER.LOGIN_ID");
            updateCustomersCriteria.add("GTP_COMPANY.TYPE", (Object)"03");
            updateCustomersCriteria.add("GTP_COMPANY.OWNER_ID", company.getIdAsInt());
            updateCustomersCriteria.add("GTP_COMPANY_TNX.TNX_STAT_CODE", (Object)"01");
            updateCustomersCriteria.add("GTP_COMPANY_TNX.TNX_TYPE_CODE", (Object)"02");
            updateCustomersCriteria.addJoin("GTP_COMPANY_TNX.MAKER_ID", "GTP_USER.USER_ID");
            Criteria updatePendingCustomersCriteria = new Criteria();
            updatePendingCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.COMPANY_ID");
            updatePendingCustomersCriteria.add("GTP_COMPANY_TNX.TNX_STAT_CODE", (Object)"01");
            updatePendingCustomersCriteria.add("GTP_COMPANY_TNX.TNX_TYPE_CODE", (Object)"02");
            List<Record> updatePendingCustomersRecordList = BasePeer.doSelect((Criteria)updatePendingCustomersCriteria, (Connection)con);
            ArrayList<String> updatePendingCustomerNamesList = new ArrayList<String>();
            if (updatePendingCustomersRecordList.size() > 0) {
                for (Record record : updatePendingCustomersRecordList) {
                    updatePendingCustomerNamesList.add(record.getValue("COMPANY_ID").asString());
                }
            }
            if (updatePendingCustomerNamesList.size() > 0) {
                updateCustomersCriteria.addIn("GTP_COMPANY_TNX.COMPANY_ID", updatePendingCustomerNamesList);
                updateRecords = BasePeer.doSelect((Criteria)updateCustomersCriteria, (Connection)con);
                reportMap.put("RECORD_UPDATE", updateRecords);
            }
            Criteria deleteCustomersCriteria = new Criteria();
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.COMPANY_ID");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.COMPANY_GROUP");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.NAME");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.ADDRESS_LINE_1");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.ADDRESS_LINE_2");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.DOM");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.COUNTRY");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.CONTACT_NAME");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.PHONE");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.FAX");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.TELEX");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.ISO_CODE");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.REFERENCE");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.EMAIL");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.WEB_ADDRESS");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.TYPE");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.OWNER_ID");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.BASE_CUR_CODE");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.LANGUAGE");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY.ABBV_NAME");
            deleteCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.MAKER_ID");
            deleteCustomersCriteria.addSelectColumn("GTP_USER.LOGIN_ID");
            deleteCustomersCriteria.add("GTP_COMPANY.TYPE", (Object)"03");
            deleteCustomersCriteria.add("GTP_COMPANY.OWNER_ID", company.getIdAsInt());
            deleteCustomersCriteria.add("GTP_COMPANY_TNX.TNX_STAT_CODE", (Object)"01");
            deleteCustomersCriteria.add("GTP_COMPANY_TNX.TNX_TYPE_CODE", (Object)"12");
            deleteCustomersCriteria.addJoin("GTP_COMPANY_TNX.MAKER_ID", "GTP_USER.USER_ID");
            Criteria deletePendingCustomersCriteria = new Criteria();
            deletePendingCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.COMPANY_ID");
            deletePendingCustomersCriteria.add("GTP_COMPANY_TNX.TNX_STAT_CODE", (Object)"01");
            deletePendingCustomersCriteria.add("GTP_COMPANY_TNX.TNX_TYPE_CODE", (Object)"12");
            List<Record> deletePendingCustomersRecordList = BasePeer.doSelect((Criteria)deletePendingCustomersCriteria, (Connection)con);
            ArrayList<String> deletePendingCustomersNamesList = new ArrayList<String>();
            if (deletePendingCustomersRecordList.size() > 0) {
                for (Record record : deletePendingCustomersRecordList) {
                    deletePendingCustomersNamesList.add(record.getValue("COMPANY_ID").asString());
                }
            }
            if (deletePendingCustomersNamesList.size() > 0) {
                deleteCustomersCriteria.addIn("GTP_COMPANY.COMPANY_ID", deletePendingCustomersNamesList);
                deleteRecords = BasePeer.doSelect((Criteria)deleteCustomersCriteria, (Connection)con);
                reportMap.put("RECORD_DELETE", deleteRecords);
            }
            Criteria returnCustomersCriteria = new Criteria();
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.COMPANY_ID");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.COMPANY_GROUP");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.NAME");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.ADDRESS_LINE_1");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.ADDRESS_LINE_2");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.DOM");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.COUNTRY");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.CONTACT_NAME");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.PHONE");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.FAX");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.TELEX");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.ISO_CODE");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.REFERENCE");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.EMAIL");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.WEB_ADDRESS");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.TYPE");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.OWNER_ID");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.BASE_CUR_CODE");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.LANGUAGE");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.ABBV_NAME");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.MAKER_ID");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.CHECKER_ID");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.TNX_TYPE_CODE");
            returnCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.TNX_STAT_CODE");
            returnCustomersCriteria.addSelectColumn("GTP_USER.LOGIN_ID");
            returnCustomersCriteria.addJoin("GTP_COMPANY_TNX.MAKER_ID", "GTP_USER.USER_ID");
            ArrayList<String> tnxStatCodeList = new ArrayList<String>();
            tnxStatCodeList.add("54");
            tnxStatCodeList.add("02");
            returnCustomersCriteria.addIn("GTP_COMPANY_TNX.TNX_STAT_CODE", tnxStatCodeList);
            returnRecords = BasePeer.doSelect((Criteria)returnCustomersCriteria, (Connection)con);
            reportMap.put("RECORD_RETURN", returnRecords);
            GTPCompanyTnx partnerTnx = null;
            for (String customerType : reportMap.keySet()) {
                List customersList = (List)reportMap.get(customerType);
                int i = 0;
                while (i < customersList.size()) {
                    Record row = (Record)customersList.get(i);
                    partnerTnx = GTPSecurity.getCompanyTnxInstance();
                    partnerTnx.setId(row.getValue(1).asInt());
                    partnerTnx.setCompany_group(row.getValue(2).asBigDecimal());
                    partnerTnx.setName(row.getValue(3).asString());
                    partnerTnx.setAddress_line_1(row.getValue(4).asString());
                    partnerTnx.setAddress_line_2(row.getValue(5).asString());
                    partnerTnx.setDom(row.getValue(6).asString());
                    partnerTnx.setCountry(row.getValue(7).asString());
                    partnerTnx.setContact_name(row.getValue(8).asString());
                    partnerTnx.setPhone(row.getValue(9).asString());
                    partnerTnx.setFax(row.getValue(10).asString());
                    partnerTnx.setTelex(row.getValue(11).asString());
                    partnerTnx.setIso_code(row.getValue(12).asString());
                    partnerTnx.setReference(row.getValue(13).asString());
                    partnerTnx.setEmail(row.getValue(14).asString());
                    partnerTnx.setWeb_address(row.getValue(15).asString());
                    partnerTnx.setType(row.getValue(16).asString());
                    partnerTnx.setOwner_id(row.getValue(17).asInt());
                    partnerTnx.setBase_cur_code(row.getValue(18).asString());
                    partnerTnx.setLanguage(row.getValue(19).asString());
                    partnerTnx.setAbbv_name(row.getValue(20).asString());
                    partnerTnx.setTnxStatCode("04");
                    partnerTnx.setTnxTypeCode("05");
                    if (customerType.equals("RECORD_ACK")) {
                        partnerTnx.setTnxStatCode("04");
                        partnerTnx.setTnxTypeCode("05");
                    } else if (customerType.equals("RECORD_NEW")) {
                        partnerTnx.setTnxStatCode("01");
                        partnerTnx.setTnxTypeCode("01");
                        partnerTnx.setMakerId(row.getValue(21).asInt());
                        partnerTnx.setMakerUserName(row.getValue(25).asString());
                    } else if (customerType.equals("RECORD_UPDATE")) {
                        partnerTnx.setTnxStatCode("01");
                        partnerTnx.setTnxTypeCode("02");
                        partnerTnx.setMakerId(row.getValue(21).asInt());
                        partnerTnx.setMakerUserName(row.getValue(22).asString());
                    } else if (customerType.equals("RECORD_DELETE")) {
                        partnerTnx.setTnxStatCode("01");
                        partnerTnx.setTnxTypeCode("12");
                        partnerTnx.setMakerId(row.getValue(21).asInt());
                        partnerTnx.setMakerUserName(row.getValue(22).asString());
                    } else if (customerType.equals("RECORD_RETURN")) {
                        partnerTnx.setTnxStatCode(row.getValue("TNX_STAT_CODE").asString());
                        partnerTnx.setTnxTypeCode(row.getValue(23).asString());
                        partnerTnx.setMakerId(row.getValue(21).asInt());
                        partnerTnx.setCheckerId(row.getValue(22).asInt());
                        partnerTnx.setMakerUserName(row.getValue(25).asString());
                    }
                    result.add(partnerTnx);
                    ++i;
                }
            }
            PoolBrokerService.releaseConnection(con);
            CompanySet companySet = result;
            return companySet;
        }
        catch (Exception e) {
            throw new GTPException(null, "E20444 - getCustomers, Exception : ", e);
        }
        finally {
            try {
                PoolBrokerService.releaseConnection(con);
            }
            catch (Exception e) {
                new com.misys.portal.common.tracer.GTPException(null, "DBCompanyManager : Failed to release the connection  in retrieveCustomers method", e);
            }
        }
    }

    @Override
    public CompanySet retrieveCustomers(List<SearchCriteria> searchCriteriaList) throws GTPException {
        CompanySet result;
        result = new CompanySet();
        Connection con = null;
        StringBuilder query = new StringBuilder();
        PreparedStatement ackPstmt = null;
        PreparedStatement newPstmt = null;
        PreparedStatement returnedPstmt = null;
        PreparedStatement updatePstmt = null;
        PreparedStatement deletePstmt = null;
        ResultSet draftReturnRecords = null;
        ResultSet rs = null;
        ResultSet ackRecords = null;
        ResultSet newRecords = null;
        ResultSet updateRecords = null;
        ResultSet deleteRecords = null;
        try {
            try {
                con = PoolBrokerService.getConnection();
                HashMap<String, ResultSet> reportMap = new HashMap<String, ResultSet>();
                query.append("select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, null, null, null, null, null, null, null, null, 'MASTER' as record_type ,company.rmGroup\tfrom GTP_COMPANY company where 1=1 and company.company_id not in (select company_tnx.company_id from GTP_COMPANY_TNX company_tnx where company_tnx.tnx_stat_code in (? ,? ,?) and company_tnx.tnx_type_code in (? ,?))");
                Utils.filterCriteriaBind(searchCriteriaList, query);
                ackPstmt = con.prepareStatement(query.toString());
                ArrayList<String> bindVarList = new ArrayList<String>();
                bindVarList.add("01");
                bindVarList.add("54");
                bindVarList.add("02");
                bindVarList.add("02");
                bindVarList.add("12");
                int indexValue = Utils.bindValueToPreparedStatement(ackPstmt, bindVarList);
                Utils.filterCriteriaBindVariable(searchCriteriaList, ackPstmt, indexValue);
                ackRecords = ackPstmt.executeQuery();
                reportMap.put("RECORD_ACK", ackRecords);
                query.setLength(0);
                query.append("select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, company.tnx_id, company.tnx_type_code, company.tnx_stat_code, company.maker_id, company.checker_id, company.maker_dttm, company.checker_dttm, maker_user.login_id, null,company.rmGroup from GTP_COMPANY_TNX company left join GTP_USER maker_user on company.maker_id = maker_user.user_id where 1=1 and company.tnx_type_code = ? and company.tnx_stat_code = ?");
                Utils.filterCriteriaBind(searchCriteriaList, query);
                newPstmt = con.prepareStatement(query.toString());
                bindVarList.clear();
                bindVarList.add("01");
                bindVarList.add("01");
                indexValue = Utils.bindValueToPreparedStatement(newPstmt, bindVarList);
                Utils.filterCriteriaBindVariable(searchCriteriaList, newPstmt, indexValue);
                newRecords = newPstmt.executeQuery();
                reportMap.put("RECORD_NEW", newRecords);
                query.setLength(0);
                query.append("select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, company.tnx_id, company.tnx_type_code, company.tnx_stat_code, company.maker_id, company.checker_id, company.maker_dttm, company.checker_dttm, maker_user.login_id, null,company.rmGroup from GTP_COMPANY_TNX company left join GTP_USER maker_user on company.maker_id = maker_user.user_id where 1=1 and company.tnx_stat_code = ? and company.tnx_type_code = ? ");
                Utils.filterCriteriaBind(searchCriteriaList, query);
                updatePstmt = con.prepareStatement(query.toString());
                bindVarList.clear();
                bindVarList.add("01");
                bindVarList.add("02");
                indexValue = Utils.bindValueToPreparedStatement(updatePstmt, bindVarList);
                Utils.filterCriteriaBindVariable(searchCriteriaList, updatePstmt, indexValue);
                updateRecords = updatePstmt.executeQuery();
                reportMap.put("RECORD_UPDATE", updateRecords);
                query.setLength(0);
                query.append("select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, company.tnx_id, company.tnx_type_code, company.tnx_stat_code, company.maker_id, company.checker_id, company.maker_dttm, company.checker_dttm, maker_user.login_id, null,company.rmGroup from GTP_COMPANY_TNX company left join GTP_USER maker_user on company.maker_id = maker_user.user_id where 1=1 and company.tnx_stat_code = ? and company.tnx_type_code = ? ");
                Utils.filterCriteriaBind(searchCriteriaList, query);
                deletePstmt = con.prepareStatement(query.toString());
                bindVarList.clear();
                bindVarList.add("01");
                bindVarList.add("12");
                indexValue = Utils.bindValueToPreparedStatement(deletePstmt, bindVarList);
                Utils.filterCriteriaBindVariable(searchCriteriaList, deletePstmt, indexValue);
                deleteRecords = deletePstmt.executeQuery();
                reportMap.put("RECORD_DELETE", deleteRecords);
                query.setLength(0);
                query.append("select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, company.tnx_id, company.tnx_type_code, company.tnx_stat_code, company.maker_id, company.checker_id, company.maker_dttm, company.checker_dttm, maker_user.login_id, null,company.rmGroup from GTP_COMPANY_TNX company left join GTP_USER maker_user on company.maker_id = maker_user.user_id where 1=1 and company.tnx_stat_code in (?,?)");
                Utils.filterCriteriaBind(searchCriteriaList, query);
                returnedPstmt = con.prepareStatement(query.toString());
                bindVarList.clear();
                bindVarList.add("54");
                bindVarList.add("02");
                indexValue = Utils.bindValueToPreparedStatement(returnedPstmt, bindVarList);
                Utils.filterCriteriaBindVariable(searchCriteriaList, returnedPstmt, indexValue);
                draftReturnRecords = returnedPstmt.executeQuery();
                reportMap.put("RECORD_RETURN", draftReturnRecords);
                Iterator itr = reportMap.keySet().iterator();
                GTPCompanyTnx partnerTnx = null;
                while (itr.hasNext()) {
                    String roleType = (String)itr.next();
                    rs = (ResultSet)reportMap.get(roleType);
                    while (rs.next()) {
                        partnerTnx = GTPSecurity.getCompanyTnxInstance();
                        this.populateCompany(partnerTnx, rs);
                        if (roleType.equals("RECORD_ACK")) {
                            partnerTnx.setTnxStatCode("04");
                            partnerTnx.setTnxTypeCode("05");
                        } else {
                            this.populateCompanyTnx(partnerTnx, rs);
                        }
                        result.add(partnerTnx);
                    }
                }
            }
            catch (Exception e) {
                new com.misys.portal.common.tracer.GTPException(null, "DBSecurityService : Failed to release the connection while processing SubscriptionPackageFileTnx.revokeTnx.", e);
                Utils.closeResources(con, null, rs);
                Utils.closeResources(null, ackPstmt, ackRecords);
                Utils.closeResources(null, newPstmt, newRecords);
                Utils.closeResources(null, updatePstmt, updateRecords);
                Utils.closeResources(null, deletePstmt, deleteRecords);
                Utils.closeResources(null, returnedPstmt, draftReturnRecords);
            }
        }
        finally {
            Utils.closeResources(con, null, rs);
            Utils.closeResources(null, ackPstmt, ackRecords);
            Utils.closeResources(null, newPstmt, newRecords);
            Utils.closeResources(null, updatePstmt, updateRecords);
            Utils.closeResources(null, deletePstmt, deleteRecords);
            Utils.closeResources(null, returnedPstmt, draftReturnRecords);
        }
        return result;
    }

    @Override
    public PaginationResult retrieveCustomersMC(GTPCompany company, List<SearchCriteria> searchCriteriaList, PaginationParams paginationParameters, CompanyComparator comparator) throws GTPException {
        PaginationResult paginationResult;
        CompanySet result = new CompanySet(comparator);
        new java.util.ArrayList();
        Connection con = null;
        StringBuilder query = new StringBuilder();
        StringBuilder unionQuery = new StringBuilder();
        PreparedStatement pstmt = null;
        paginationResult = new PaginationResult();
        new com.misys.portal.common.pagination.PaginationResult();
        int counter = 0;
        int startIndex = paginationParameters.getStartIndex();
        int endIndex = paginationParameters.getEndIndex();
        ResultSet rset = null;
        try {
            try {
                con = PoolBrokerService.getConnection();
                new java.util.HashMap();
                ArrayList<Object> bindVarList = new ArrayList<Object>();
                query.append("select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, company.tnx_id, company.tnx_type_code, company.tnx_stat_code, company.maker_id, company.checker_id, company.maker_dttm, company.checker_dttm, maker_user.login_id, null,company.rmGroup from GTP_COMPANY_TNX company left join GTP_USER maker_user on company.maker_id = maker_user.user_id where 1=1 and company.tnx_type_code = ? and company.tnx_stat_code = ?");
                Utils.filterCriteriaBind(searchCriteriaList, query);
                unionQuery.append(query);
                query.setLength(0);
                query.append("select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, company.tnx_id, company.tnx_type_code, company.tnx_stat_code, company.maker_id, company.checker_id, company.maker_dttm, company.checker_dttm, maker_user.login_id, null,company.rmGroup from GTP_COMPANY_TNX company left join GTP_USER maker_user on company.maker_id = maker_user.user_id where 1=1 and company.tnx_stat_code = ? and company.tnx_type_code = ? ");
                Utils.filterCriteriaBind(searchCriteriaList, query);
                unionQuery.append(" union ");
                unionQuery.append(query);
                query.setLength(0);
                query.append("select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, company.tnx_id, company.tnx_type_code, company.tnx_stat_code, company.maker_id, company.checker_id, company.maker_dttm, company.checker_dttm, maker_user.login_id, null,company.rmGroup from GTP_COMPANY_TNX company left join GTP_USER maker_user on company.maker_id = maker_user.user_id where 1=1 and company.tnx_stat_code = ? and company.tnx_type_code = ? ");
                Utils.filterCriteriaBind(searchCriteriaList, query);
                unionQuery.append(" union ");
                unionQuery.append(query);
                query.setLength(0);
                query.append("select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, company.tnx_id, company.tnx_type_code, company.tnx_stat_code, company.maker_id, company.checker_id, company.maker_dttm, company.checker_dttm, maker_user.login_id, null,company.rmGroup from GTP_COMPANY_TNX company left join GTP_USER maker_user on company.maker_id = maker_user.user_id where 1=1 and company.tnx_stat_code in (?,?)");
                Utils.filterCriteriaBind(searchCriteriaList, query);
                unionQuery.append(" union ");
                unionQuery.append(query);
                boolean containsStatusorActorFilter = false;
                ArrayList<SearchCriteria> searchCriteriaListExcludeTnxColumns = new ArrayList<SearchCriteria>();
                ArrayList<SearchCriteria> attachedCustomersSearch = new ArrayList<SearchCriteria>();
                for (SearchCriteria searchCriteria : searchCriteriaList) {
                    if (!searchCriteria.getColumnName().contains("tnx_stat_code") && !searchCriteria.getColumnName().contains("tnx_type_code")) {
                        searchCriteriaListExcludeTnxColumns.add(searchCriteria);
                    } else {
                        containsStatusorActorFilter = true;
                    }
                    if (company == null || !"01".equals(company.getType()) || searchCriteria.getColumnName().toLowerCase().indexOf("owner_id") != -1) continue;
                    attachedCustomersSearch.add(searchCriteria);
                }
                query.setLength(0);
                if (!containsStatusorActorFilter) {
                    query.append("select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, null, null, null, null, null, null, null, null, 'MASTER' as record_type ,company.rmGroup\tfrom GTP_COMPANY company where 1=1 and company.company_id not in (select company_tnx.company_id from GTP_COMPANY_TNX company_tnx where company_tnx.tnx_stat_code in (? ,? ,?) and company_tnx.tnx_type_code in (? ,?))");
                    Utils.filterCriteriaBind(searchCriteriaListExcludeTnxColumns, query);
                    unionQuery.append(" union ");
                    unionQuery.append(query);
                }
                query.setLength(0);
                if (company != null && "01".equals(company.getType())) {
                    query.append("select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone, null, null, null, null, null, null, null, null, 'MASTER' as record_type ,company.rmGroup from GTP_COMPANY company where 1=1 and company.owner_id <> ?  and company.company_id in (select customer.customer_id from GTP_CUSTOMER_BANK customer where bank_id = ? ) and company.company_id NOT IN  (SELECT company_tnx.company_id FROM GTP_COMPANY_TNX company_tnx where company_tnx.tnx_stat_code IN (?,?) and company_tnx.tnx_type_code IN (? ,?))");
                    Utils.filterCriteriaBind(attachedCustomersSearch, query);
                    unionQuery.append(" union ");
                    unionQuery.append(query);
                    query.setLength(0);
                    query.append("select company.company_id, company.company_group, company.name, company.address_line_1, company.address_line_2, company.dom, company.country, company.contact_name, company.phone, company.fax, company.telex, company.iso_code, company.reference, company.email, company.web_address, company.type, company.owner_id,\tcompany.base_cur_code, company.language, company.abbv_name,  company.charge_account_address_line_1,company.charge_account_address_line_2, company.charge_account_address_line_3, company.charge_account_address_line_4, company.charge_account,company.actv_flag, company.dual_control, company.password_expiry,  company.attachment_max_upload_size, company.retention_period, company.bei, company.street_name, company.post_code, company.town_name, company.country_sub_div, company.crm_email,  company.legal_id_type, company.legal_id_no, company.country_legalid, company.authorize_own_transaction, company.bulk_authorize_limit, company.auto_fwd_date,  company.check_file_hash_value, company.check_duplicate_file, company.check_duplicate_cust_ref, company.file_encryption_method, company.reject_file_on_error,  company.merge_demerge_allowed, company.bulk_draft_on_error, company.county, company.country_name, company.treasury_branch_reference, company.created, company.psml_template,company.liquidity_frequency, company.liquidity_balance_type, company.liquidity_ccy_cur_code, company.time_zone ,  tnx.tnx_id, tnx.tnx_type_code, tnx.tnx_stat_code, tnx.maker_id, tnx.checker_id, tnx.maker_dttm, tnx.checker_dttm, null, 'MASTER' as record_type ,company.rmGroup from GTP_COMPANY company left join GTP_COMPANY_TNX tnx  on company.company_id = tnx.company_id  where 1=1 and company.owner_id <> ?  and company.company_id in (select customer.customer_id from GTP_CUSTOMER_BANK customer where bank_id = ? ) and tnx.tnx_stat_code IN (?,?)");
                    Utils.filterCriteriaBind(attachedCustomersSearch, query);
                    unionQuery.append(" union ");
                    unionQuery.append(query);
                }
                unionQuery.append("\torder by ");
                unionQuery.append("ABBV_NAME");
                unionQuery.append(" ASC");
                pstmt = con.prepareStatement(unionQuery.toString(), 1004, 1007);
                bindVarList.clear();
                bindVarList.add("01");
                bindVarList.add("01");
                int indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList);
                indexValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indexValue);
                bindVarList.clear();
                bindVarList.add("01");
                bindVarList.add("02");
                indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                indexValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indexValue);
                bindVarList.clear();
                bindVarList.add("01");
                bindVarList.add("12");
                indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                indexValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indexValue);
                bindVarList.clear();
                bindVarList.add("54");
                bindVarList.add("02");
                indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                indexValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indexValue);
                if (!containsStatusorActorFilter) {
                    bindVarList.clear();
                    bindVarList.add("01");
                    bindVarList.add("54");
                    bindVarList.add("02");
                    bindVarList.add("02");
                    bindVarList.add("12");
                    indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                    indexValue = Utils.filterCriteriaBindVariable(searchCriteriaListExcludeTnxColumns, pstmt, indexValue);
                }
                if (company != null && "01".equals(company.getType())) {
                    bindVarList.clear();
                    bindVarList.add(company.getIdAsInt());
                    bindVarList.add(company.getIdAsInt());
                    bindVarList.add("01");
                    bindVarList.add("02");
                    bindVarList.add("02");
                    bindVarList.add("12");
                    indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                    ArrayList<SearchCriteria> tempCriteriaList = new ArrayList<SearchCriteria>();
                    for (SearchCriteria criteria : searchCriteriaListExcludeTnxColumns) {
                        if ("company.owner_id".equals(criteria.getColumnName())) continue;
                        tempCriteriaList.add(criteria);
                    }
                    indexValue = Utils.filterCriteriaBindVariable(tempCriteriaList, pstmt, indexValue);
                    bindVarList.clear();
                    bindVarList.add(company.getIdAsInt());
                    bindVarList.add(company.getIdAsInt());
                    bindVarList.add("01");
                    bindVarList.add("02");
                    indexValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indexValue);
                    indexValue = Utils.filterCriteriaBindVariable(tempCriteriaList, pstmt, indexValue);
                }
                boolean isScrollableResultSet = true;
                try {
                    rset = pstmt.executeQuery();
                }
                catch (SQLException v0) {
                    pstmt.close();
                    pstmt = con.prepareStatement(unionQuery.toString(), 1004, 1007);
                    bindVarList.clear();
                    bindVarList.add("01");
                    bindVarList.add("01");
                    int indValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList);
                    indValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indValue);
                    bindVarList.clear();
                    bindVarList.add("01");
                    bindVarList.add("02");
                    indValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indValue);
                    indValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indValue);
                    bindVarList.clear();
                    bindVarList.add("01");
                    bindVarList.add("12");
                    indValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indValue);
                    indValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indValue);
                    bindVarList.clear();
                    bindVarList.add("54");
                    bindVarList.add("02");
                    indValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indValue);
                    indValue = Utils.filterCriteriaBindVariable(searchCriteriaList, pstmt, indValue);
                    if (!containsStatusorActorFilter) {
                        bindVarList.clear();
                        bindVarList.add("01");
                        bindVarList.add("54");
                        bindVarList.add("02");
                        bindVarList.add("02");
                        bindVarList.add("12");
                        indValue = Utils.bindValueToPreparedStatement(pstmt, bindVarList, indValue);
                        indValue = Utils.filterCriteriaBindVariable(searchCriteriaListExcludeTnxColumns, pstmt, indValue);
                    }
                    rset = pstmt.executeQuery();
                }
                if (isScrollableResultSet && rset.last()) {
                    paginationResult.setNumRows(rset.getRow());
                    Log.debug(DBCompanyManager.class, "DBCompanyMange.retrieveCustomers(company,searchCriteriaList,paginationParameters): " + paginationResult.getNumRows() + " row(s) available in the Result Set");
                    if (paginationResult.getNumRows() < startIndex) {
                        startIndex = 0;
                        endIndex = startIndex + paginationResult.getNumRows();
                        paginationParameters.setStartIndex(startIndex);
                    }
                    if (paginationResult.getNumRows() < endIndex) {
                        endIndex = paginationResult.getNumRows();
                        paginationParameters.setEndIndex(endIndex);
                    }
                    rset.beforeFirst();
                }
                GTPCompanyTnx partnerTnx = null;
                while (rset.next()) {
                    if (counter >= startIndex && counter <= rset.getRow()) {
                        partnerTnx = GTPSecurity.getCompanyTnxInstance();
                        this.populateCompany(partnerTnx, rset);
                        if ("MASTER".equals(rset.getString(61))) {
                            partnerTnx.setTnxStatCode("04");
                            partnerTnx.setTnxTypeCode("05");
                        } else {
                            this.populateCompanyTnx(partnerTnx, rset);
                        }
                        result.add(partnerTnx);
                    }
                    if (counter >= rset.getRow() && isScrollableResultSet) break;
                    ++counter;
                }
                paginationResult.setResult(result);
                if (!isScrollableResultSet) {
                    paginationResult.setNumRows(counter);
                }
            }
            catch (Exception e) {
                new com.misys.portal.common.tracer.GTPException(null, "DBSecurityService : Failed to release the connection while processing SubscriptionPackageFileTnx.revokeTnx.", e);
                Utils.closeResources(con, pstmt, rset);
            }
        }
        finally {
            Utils.closeResources(con, pstmt, rset);
        }
        return paginationResult;
    }

    public GTPCompanyTnx retrieveTnx(String companyAbbvName) throws GTPException, CompanyNotFoundException {
        Connection con = null;
        GTPCompanyTnx companyTnx = null;
        try {
            con = PoolBrokerService.getConnection();
            Criteria updateCustomersCriteria = new Criteria();
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.COMPANY_ID");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.COMPANY_GROUP");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.NAME");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.ADDRESS_LINE_1");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.ADDRESS_LINE_2");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.DOM");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.COUNTRY");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.CONTACT_NAME");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.PHONE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.FAX");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.TELEX");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.ISO_CODE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.REFERENCE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.EMAIL");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.WEB_ADDRESS");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.TYPE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.OWNER_ID");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.BASE_CUR_CODE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.LANGUAGE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.CHECKER_ID");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.MAKER_ID");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.TNX_TYPE_CODE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.TNX_STAT_CODE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.TNX_ID");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.RETURN_COMMENTS");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.MAKER_DTTM");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.CHECKER_DTTM");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.CHARGE_ACCOUNT_ADDRESS_LINE_1");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.CHARGE_ACCOUNT_ADDRESS_LINE_2");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.CHARGE_ACCOUNT_ADDRESS_LINE_3");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.CHARGE_ACCOUNT_ADDRESS_LINE_4");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.CHARGE_ACCOUNT");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.ACTV_FLAG");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.DUAL_CONTROL");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.PASSWORD_EXPIRY");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.ATTACHMENT_MAX_UPLOAD_SIZE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.RETENTION_PERIOD");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.BEI");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.STREET_NAME");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.POST_CODE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.TOWN_NAME");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.COUNTRY_SUB_DIV");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.CRM_EMAIL");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.LEGAL_ID_TYPE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.LEGAL_ID_NO");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.COUNTRY_LEGALID");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.AUTHORIZE_OWN_TRANSACTION");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.BULK_AUTHORIZE_LIMIT");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.AUTO_FWD_DATE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.CHECK_FILE_HASH_VALUE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.CHECK_DUPLICATE_FILE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.CHECK_DUPLICATE_CUST_REF");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.FILE_ENCRYPTION_METHOD");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.REJECT_FILE_ON_ERROR");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.MERGE_DEMERGE_ALLOWED");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.BULK_DRAFT_ON_ERROR");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.COUNTY");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.COUNTRY_NAME");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.PSML_TEMPLATE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.LIQUIDITY_FREQUENCY");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.LIQUIDITY_BALANCE_TYPE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.LIQUIDITY_CCY_CUR_CODE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.TIME_ZONE");
            updateCustomersCriteria.addSelectColumn("GTP_COMPANY_TNX.RMGROUP");
            updateCustomersCriteria.add("GTP_COMPANY_TNX.ABBV_NAME", (Object)companyAbbvName);
            ArrayList<String> tnxStatCodesList = new ArrayList<String>();
            tnxStatCodesList.add("01");
            tnxStatCodesList.add("54");
            tnxStatCodesList.add("02");
            updateCustomersCriteria.addIn("GTP_COMPANY_TNX.TNX_STAT_CODE", tnxStatCodesList);
            List pendingCustomersList = BasePeer.doSelect((Criteria)updateCustomersCriteria, (Connection)con);
            if (pendingCustomersList.size() > 0) {
                int i = 0;
                while (i < pendingCustomersList.size()) {
                    Record row = (Record)pendingCustomersList.get(i);
                    companyTnx = GTPSecurity.getCompanyTnxInstance();
                    companyTnx.setId(row.getValue(1).asInt());
                    companyTnx.setCompany_group(row.getValue(2).asBigDecimal());
                    companyTnx.setName(row.getValue(3).asString());
                    companyTnx.setAddress_line_1(row.getValue(4).asString());
                    companyTnx.setAddress_line_2(row.getValue(5).asString());
                    companyTnx.setDom(row.getValue(6).asString());
                    companyTnx.setCountry(row.getValue(7).asString());
                    companyTnx.setContact_name(row.getValue(8).asString());
                    companyTnx.setPhone(row.getValue(9).asString());
                    companyTnx.setFax(row.getValue(10).asString());
                    companyTnx.setTelex(row.getValue(11).asString());
                    companyTnx.setIso_code(row.getValue(12).asString());
                    companyTnx.setReference(row.getValue(13).asString());
                    companyTnx.setEmail(row.getValue(14).asString());
                    companyTnx.setWeb_address(row.getValue(15).asString());
                    companyTnx.setType(row.getValue(16).asString());
                    companyTnx.setOwner_id(row.getValue(17).asInt());
                    companyTnx.setBase_cur_code(row.getValue(18).asString());
                    companyTnx.setLanguage(row.getValue(19).asString());
                    companyTnx.setAbbv_name(companyAbbvName);
                    companyTnx.setCheckerId(row.getValue(20).asInt());
                    companyTnx.setMakerId(row.getValue(21).asInt());
                    companyTnx.setTnxTypeCode(row.getValue(22).asString());
                    companyTnx.setTnxStatCode(row.getValue(23).asString());
                    companyTnx.setTnxId(row.getValue(24).asString());
                    companyTnx.setReturnComments(row.getValue(25).asString());
                    companyTnx.setMakerDateTime(row.getValue(26).asTimestamp());
                    companyTnx.setCheckerDateTime(row.getValue(27).asTimestamp());
                    companyTnx.setCharge_account_address_line_1(row.getValue(28).asString());
                    companyTnx.setCharge_account_address_line_2(row.getValue(29).asString());
                    companyTnx.setCharge_account_address_line_3(row.getValue(30).asString());
                    companyTnx.setCharge_account_address_line_4(row.getValue(31).asString());
                    companyTnx.setCharge_account(row.getValue(32).asString());
                    companyTnx.setActvFlag(row.getValue(33).asString());
                    companyTnx.setDualControl(row.getValue(34).asString());
                    companyTnx.setPasswordExpiry(row.getValue(35).asString());
                    companyTnx.setAttachmentMaxUploadSize(row.getValue(36).asString());
                    companyTnx.setRetentionPeriod(row.getValue(37).asString());
                    companyTnx.setBei(row.getValue(38).asString());
                    companyTnx.setStreet_name(row.getValue(39).asString());
                    companyTnx.setPost_code(row.getValue(40).asString());
                    companyTnx.setTown_name(row.getValue(41).asString());
                    companyTnx.setCountry_sub_div(row.getValue(42).asString());
                    companyTnx.setCRMEmail(row.getValue(43).asString());
                    companyTnx.setLegalIdType(row.getValue(44).asString());
                    companyTnx.setLegalIdNo(row.getValue(45).asString());
                    companyTnx.setCountryLegalId(row.getValue(46).asString());
                    companyTnx.setAuthorizeOwnTransaction(row.getValue(47).asString());
                    companyTnx.setBulkAuthorizeLimit(row.getValue(48).asString());
                    companyTnx.setAutoFwdDate(row.getValue(49).asString());
                    companyTnx.setCheckFileHashValue(row.getValue(50).asString());
                    companyTnx.setCheckDuplicateFile(row.getValue(51).asString());
                    companyTnx.setCheckDuplicateCustRef(row.getValue(52).asString());
                    companyTnx.setFileEncryptionMethod(row.getValue(53).asString());
                    companyTnx.setRejectFileOnError(row.getValue(54).asString());
                    companyTnx.setMergeDemergeAllowed(row.getValue(55).asString());
                    companyTnx.setBulkDraftOnError(row.getValue(56).asString());
                    companyTnx.setCounty(row.getValue(57).asString());
                    companyTnx.setCountry_name(row.getValue(58).asString());
                    companyTnx.setPSMLTemplate(row.getValue(59).asString());
                    companyTnx.setLiquidityfrequency(row.getValue(60).asString());
                    companyTnx.setLiquiditybalancetype(row.getValue(61).asString());
                    companyTnx.setLiquiditycurrency(row.getValue(62).asString());
                    companyTnx.setTimeZone(row.getValue("TIME_ZONE").asString());
                    companyTnx.setRmGroup(row.getValue(64).asString());
                    ++i;
                }
            } else {
                Log.warn(DBCompanyManager.class, "Company " + companyAbbvName + " not found");
                throw new CompanyNotFoundException("Company " + companyAbbvName + " not found");
            }
            GTPCompanyTnx gTPCompanyTnx = companyTnx;
            return gTPCompanyTnx;
        }
        catch (GTPException gtpException) {
            throw gtpException;
        }
        catch (Exception se) {
            throw new GTPException("E10002 - SQL Error while retrieving company : ", se);
        }
        finally {
            try {
                PoolBrokerService.releaseConnection(con);
            }
            catch (Exception exc) {
                Log.error(DBCompanyManager.class, "Can't close connection.", exc);
            }
        }
    }

    @Override
    public GTPCompanyTnx retrieveTnx(String companyAbbvName, List<String> types) throws GTPException, CompanyNotFoundException {
        GTPCompanyTnx company = this.retrieveTnx(companyAbbvName);
        if (!types.contains(company.getType())) {
            throw new GTPException("The company " + companyAbbvName + " is not of the specified types. ");
        }
        return company;
    }

    @Override
    public GTPCompanyTnx retrieveTnx(String companyAbbvName, String type) throws GTPException, CompanyNotFoundException {
        GTPCompanyTnx company = this.retrieveTnx(companyAbbvName);
        if (StringUtils.isNotEmpty((String)type) && !type.equals(company.getType())) {
            throw new GTPException("The company " + companyAbbvName + " is not of type: " + type);
        }
        return company;
    }

    @Override
    public void update(GTPCompany company) throws GTPException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            try {
                con = PoolBrokerService.getConnection();
                if (company.getIdAsObject() == null) {
                    String error = "Missing company id : can't save company";
                    throw new GTPException(null, error);
                }
                stmt = con.prepareStatement("update GTP_COMPANY set abbv_name = ?, company_id = ?, company_group = ?,  name = ?, address_line_1 = ?, address_line_2 = ?, dom = ?, charge_account_address_line_1 = ?, charge_account_address_line_2 = ?, charge_account_address_line_3 = ?, charge_account_address_line_4 = ?, charge_account = ?, country = ?, contact_name = ?, phone = ?, fax = ?, telex = ?,  iso_code = ?, reference = ?, email = ?, web_address = ?, type = ?, owner_id = ?, base_cur_code = ?, language = ?,  actv_flag = ?, dual_control = ?, password_expiry = ?, attachment_max_upload_size = ?, retention_period = ?, bei = ?, street_name = ?, post_code = ?, town_name = ?,  country_sub_div = ?, crm_email = ?, legal_id_type = ?, legal_id_no = ?, country_legalid = ?, authorize_own_transaction = ?, bulk_authorize_limit = ?,  auto_fwd_date = ?, check_file_hash_value = ?, check_duplicate_file = ?, check_duplicate_cust_ref = ?,  file_encryption_method = ?, reject_file_on_error = ?, merge_demerge_allowed = ?, bulk_draft_on_error = ?, county = ?, country_name = ?, treasury_branch_reference = ?, psml_template = ?,  liquidity_frequency = ?, liquidity_balance_type = ?, liquidity_ccy_cur_code = ?, time_zone = ?, rmGroup=? where company_id = ?");
                ArrayList<Object> bindValues = new ArrayList<Object>();
                bindValues.add(company.getAbbv_name());
                bindValues.add(company.getIdAsInt());
                bindValues.add(company.getCompany_group());
                bindValues.add(company.getName());
                bindValues.add(company.getAddress_line_1());
                bindValues.add(company.getAddress_line_2());
                bindValues.add(company.getDom());
                bindValues.add(company.getCharge_account_address_line_1());
                bindValues.add(company.getCharge_account_address_line_2());
                bindValues.add(company.getCharge_account_address_line_3());
                bindValues.add(company.getCharge_account_address_line_4());
                bindValues.add(company.getCharge_account());
                bindValues.add(company.getCountry());
                bindValues.add(company.getContact_name());
                bindValues.add(company.getPhone());
                bindValues.add(company.getFax());
                bindValues.add(company.getTelex());
                bindValues.add(company.getIso_code());
                bindValues.add(company.getReference());
                bindValues.add(company.getEmail());
                bindValues.add(company.getWeb_address());
                bindValues.add(company.getType());
                bindValues.add(company.getOwner_id());
                bindValues.add(company.getBase_cur_code());
                bindValues.add(company.getLanguage());
                bindValues.add(company.getActvFlag());
                bindValues.add(company.getDualControl());
                bindValues.add(company.getPasswordExpiry());
                bindValues.add(company.getAttachmentMaxUploadSize());
                bindValues.add(company.getRetentionPeriod());
                bindValues.add(company.getBei());
                bindValues.add(company.getStreet_name());
                bindValues.add(company.getPost_code());
                bindValues.add(company.getTown_name());
                bindValues.add(company.getCountry_sub_div());
                bindValues.add(company.getCRMEmail());
                bindValues.add(company.getLegalIdType());
                bindValues.add(company.getLegalIdNo());
                bindValues.add(company.getCountryLegalId());
                bindValues.add(company.getAuthorizeOwnTransaction());
                bindValues.add(company.getBulkAuthorizeLimit());
                bindValues.add(company.getAutoFwdDate());
                bindValues.add(company.getCheckFileHashValue());
                bindValues.add(company.getCheckDuplicateFile());
                bindValues.add(company.getCheckDuplicateCustRef());
                bindValues.add(company.getFileEncryptionMethod());
                bindValues.add(company.getRejectFileOnError());
                bindValues.add(company.getMergeDemergeAllowed());
                bindValues.add(company.getBulkDraftOnError());
                bindValues.add(company.getCounty());
                bindValues.add(company.getCountry_name());
                bindValues.add(company.getTreasury_branch_reference());
                bindValues.add(company.getPSMLTemplate());
                bindValues.add(company.getLiquidityfrequency());
                bindValues.add(company.getLiquiditybalancetype());
                bindValues.add(company.getLiquiditycurrency());
                bindValues.add(company.getTimeZone());
                bindValues.add(company.getRmGroup());
                bindValues.add(company.getIdAsInt());
                int i = 1;
                for (Object object : bindValues) {
                    stmt.setObject(i++, object);
                }
                stmt.executeUpdate();
            }
            catch (SQLException se) {
                String error = "SQL Error while processing GTPCustomer updateIntoDB";
                throw new GTPException(null, error, se);
            }
            catch (Exception e) {
                String error = "Error while processing GTPCustomer updateIntoDB";
                throw new GTPException(null, error, e);
            }
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (Exception exc) {
                    GTPException.log("Error in closing resource", exc);
                }
            }
            try {
                PoolBrokerService.releaseConnection(con);
            }
            catch (Exception exc) {
                GTPException.log("Error in closing resource", exc);
            }
        }
    }

    @Override
    public void updateTnx(GTPCompanyTnx companyTnx) throws GTPException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            try {
                con = PoolBrokerService.getConnection();
                if (companyTnx.getIdAsObject() == null) {
                    String error = "Missing company id : can't save company";
                    throw new GTPException(null, error);
                }
                if (companyTnx.getTnxStatCode().equals("56")) {
                    String[] tablesToDelete = new String[]{"GTP_COMPANY_ROLE_TNX", "GTP_COMPANY_TNX", "GTP_CUSTOMER_BANK_TNX", "GTP_CUSTOMER_REFERENCE_TNX", "GTP_ACCOUNT_TNX", "GTP_CUSTOMER_ACCOUNT_TNX"};
                    StringBuilder query = new StringBuilder();
                    int i = 0;
                    while (i < tablesToDelete.length) {
                        query.setLength(0);
                        query.append("delete from ").append(tablesToDelete[i]).append(" where tnx_id = ?");
                        stmt = con.prepareStatement(query.toString());
                        stmt.setString(1, companyTnx.getTnxId());
                        stmt.executeUpdate();
                        ++i;
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                } else {
                    stmt = con.prepareStatement("update GTP_COMPANY_TNX set abbv_name = ?, company_id = ?, company_group = ?, name = ?, address_line_1 = ?, address_line_2 = ?, dom = ?,  country = ?, contact_name = ?, phone = ?, fax = ?, telex = ?, iso_code = ?, reference = ?, email = ?, web_address = ?, type = ?,  owner_id = ?, base_cur_code = ?, language = ?, tnx_type_code = ?, tnx_stat_code = ?,checker_id = ?, checker_dttm = ?, return_comments = ?,   charge_account_address_line_1 = ?, charge_account_address_line_2 = ?, charge_account_address_line_3 = ?, charge_account_address_line_4 = ?, charge_account = ?,  actv_flag = ?, dual_control = ?, password_expiry = ?, attachment_max_upload_size = ?, retention_period = ?, bei = ?, street_name = ?, post_code = ?, town_name = ?,  country_sub_div = ?, crm_email = ?, legal_id_type = ?, legal_id_no = ?, country_legalid = ?, authorize_own_transaction = ?, bulk_authorize_limit = ?,  auto_fwd_date = ?, check_file_hash_value = ?, check_duplicate_file = ?, check_duplicate_cust_ref = ?,  file_encryption_method = ?, reject_file_on_error = ?, merge_demerge_allowed = ?, bulk_draft_on_error = ?, county = ?, country_name = ?, treasury_branch_reference = ?, psml_template = ?, liquidity_frequency = ?, liquidity_balance_type = ?, liquidity_ccy_cur_code = ?, time_zone = ?, rmGroup=? where company_id = ?  and tnx_stat_code in (?,?,?)");
                    ArrayList<Object> bindValues = new ArrayList<Object>();
                    bindValues.add(companyTnx.getAbbv_name());
                    bindValues.add(companyTnx.getIdAsInt());
                    bindValues.add(companyTnx.getCompany_group());
                    bindValues.add(companyTnx.getName());
                    bindValues.add(companyTnx.getAddress_line_1());
                    bindValues.add(companyTnx.getAddress_line_2());
                    bindValues.add(companyTnx.getDom());
                    bindValues.add(companyTnx.getCountry());
                    bindValues.add(companyTnx.getContact_name());
                    bindValues.add(companyTnx.getPhone());
                    bindValues.add(companyTnx.getFax());
                    bindValues.add(companyTnx.getTelex());
                    bindValues.add(companyTnx.getIso_code());
                    bindValues.add(companyTnx.getReference());
                    bindValues.add(companyTnx.getEmail());
                    bindValues.add(companyTnx.getWeb_address());
                    bindValues.add(companyTnx.getType());
                    bindValues.add(companyTnx.getOwner_id());
                    bindValues.add(companyTnx.getBase_cur_code());
                    bindValues.add(companyTnx.getLanguage());
                    bindValues.add(companyTnx.getTnxTypeCode());
                    bindValues.add(companyTnx.getTnxStatCode());
                    bindValues.add(companyTnx.getCheckerId());
                    bindValues.add(companyTnx.getCheckerDateTime());
                    bindValues.add(companyTnx.getReturnComments());
                    bindValues.add(companyTnx.getCharge_account_address_line_1());
                    bindValues.add(companyTnx.getCharge_account_address_line_2());
                    bindValues.add(companyTnx.getCharge_account_address_line_3());
                    bindValues.add(companyTnx.getCharge_account_address_line_4());
                    bindValues.add(companyTnx.getCharge_account());
                    bindValues.add(companyTnx.getActvFlag());
                    bindValues.add(companyTnx.getDualControl());
                    bindValues.add(companyTnx.getPasswordExpiry());
                    bindValues.add(companyTnx.getAttachmentMaxUploadSize());
                    bindValues.add(companyTnx.getRetentionPeriod());
                    bindValues.add(companyTnx.getBei());
                    bindValues.add(companyTnx.getStreet_name());
                    bindValues.add(companyTnx.getPost_code());
                    bindValues.add(companyTnx.getTown_name());
                    bindValues.add(companyTnx.getCountry_sub_div());
                    bindValues.add(companyTnx.getCRMEmail());
                    bindValues.add(companyTnx.getLegalIdType());
                    bindValues.add(companyTnx.getLegalIdNo());
                    bindValues.add(companyTnx.getCountryLegalId());
                    bindValues.add(companyTnx.getAuthorizeOwnTransaction());
                    bindValues.add(companyTnx.getBulkAuthorizeLimit());
                    bindValues.add(companyTnx.getAutoFwdDate());
                    bindValues.add(companyTnx.getCheckFileHashValue());
                    bindValues.add(companyTnx.getCheckDuplicateFile());
                    bindValues.add(companyTnx.getCheckDuplicateCustRef());
                    bindValues.add(companyTnx.getFileEncryptionMethod());
                    bindValues.add(companyTnx.getRejectFileOnError());
                    bindValues.add(companyTnx.getMergeDemergeAllowed());
                    bindValues.add(companyTnx.getBulkDraftOnError());
                    bindValues.add(companyTnx.getCounty());
                    bindValues.add(companyTnx.getCountry_name());
                    bindValues.add(companyTnx.getTreasury_branch_reference());
                    bindValues.add(companyTnx.getPSMLTemplate());
                    bindValues.add(companyTnx.getLiquidityfrequency());
                    bindValues.add(companyTnx.getLiquiditybalancetype());
                    bindValues.add(companyTnx.getLiquiditycurrency());
                    bindValues.add(companyTnx.getTimeZone());
                    bindValues.add(companyTnx.getRmGroup());
                    bindValues.add(companyTnx.getIdAsInt());
                    bindValues.add("01");
                    bindValues.add("54");
                    bindValues.add("02");
                    int i = 1;
                    for (Object object : bindValues) {
                        stmt.setObject(i++, object);
                    }
                    stmt.executeUpdate();
                    stmt.close();
                    stmt = null;
                }
            }
            catch (SQLException se) {
                String error = "SQL Error while processing updateTnx(GTPCompanyTnx companyTnx)";
                throw new GTPException(null, error, se);
            }
            catch (Exception e) {
                String error = "Error while processing updateTnx(GTPCompanyTnx companyTnx)";
                throw new GTPException(null, error, e);
            }
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (Exception exc) {
                    GTPException.log("Error in closing resource", exc);
                }
            }
            try {
                PoolBrokerService.releaseConnection(con);
            }
            catch (Exception exc) {
                GTPException.log("Error in closing resource", exc);
            }
        }
    }

    public void addCompanyObjectToCache(String key, GTPCompany result) {
        ApplicationCache applicationCache = ApplicationCache.getInstance();
        CachedObject cachedObject = new CachedObject((Object)result);
        applicationCache.updateCacheData("companyDetails", key, cachedObject);
    }

    public void clearCompanyObjectCache(GTPCompany result) {
        String companyIdKey = result.getIdAsString();
        String abbvnameKey = result.getAbbv_name();
        ApplicationCache applicationCache = ApplicationCache.getInstance();
        applicationCache.removeFromCache("companyDetails", companyIdKey);
        applicationCache.removeFromCache("companyDetails", abbvnameKey);
    }
}


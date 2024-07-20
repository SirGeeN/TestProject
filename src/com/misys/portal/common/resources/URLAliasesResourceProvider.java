
/**
* This class is auto generated (date: 2019-02-14T15:35:59+04:00)
* DO NOT WRITE IN THIS FILE
**/
package com.misys.portal.common.resources;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.turbine.services.servlet.TurbineServlet;

import com.misys.portal.common.tracer.Log;
import com.misys.portal.core.util.EngineContext;

/**
 * This class is the repository of URLs used by Portal. These URLs point to XSL and CSS resources. By
 * generating a different class, one can change which resources are read by the application.
 * <p>
 * The intern() call is a technique to ensure that the constants are not inlined by the compiler.
 * <p>
 * This class is auto generated. <br>
 * <b>DO NOT WRITE IN THIS FILE</b>
 */
public class URLAliasesResourceProvider extends StaticResourceProvider
{
    /*****************/
    /* STANDARD KEYS */
    /*****************/
    public static final String STYLESHEET_SY_LOGIN_CUSTOMER_WITH_OUT_TOKEN_URL = "/client/core/xsl/system/sy_login_customer_with_out_token_client.xsl".intern();
	public static final String STYLESHEET_SY_LOGIN_CUSTOMER_URL = "/client/core/xsl/system/sy_login_customer_client.xsl".intern();
	public final static String CSS_SRC_URL ="/misys/themes".intern();
		
	public final static String CSS_DOJO_GRID_DEFAULT ="/content/js-src/dojox/grid/resources/Grid.css".intern();
		
	public final static String CSS_DOJO_DEFAULT ="/dojo/resources/dojo.css".intern();
		
	public final static String CSS_DEFAULT_URL ="/content/css/themes/misys/misys.css".intern();
		
	public final static String CSS_CALENDAR_URL ="/content/css/calendar.css".intern();
		
	public final static String CSS_TOOLTIP_URL ="/content/css/tooltip.css".intern();
		
	public final static String STYLESHEET_SUMMARY_URL ="/core/xsl/common/report_trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_DISPLAY_AUTO_GEN_ATTACHMENT ="/core/xsl/common/display_auto_gen_attachment.xsl".intern();
		
	public final static String STYLESHEET_DISPLAY_COLLABORATION ="/core/xsl/common/display_collaboration.xsl".intern();
		
	public final static String STYLESHEET_MAINTAIN_ENTITY_URL ="/core/xsl/trade_maintain_entity.xsl".intern();
		
	public final static String STYLESHEET_MAINTAIN_CUSTOMER_REF_URL ="/core/xsl/trade_maintain_customer_ref.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_CREATE_URL ="/trade/xsl/trade_create_lc.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_UPLOAD_URL ="/trade/xsl/trade_upload_lc.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_AMEND_URL ="/trade/xsl/trade_amend_lc.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_MESSAGE_URL ="/core/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_MESSAGE_DISCREPANT_URL ="/trade/xsl/trade_message_discrepant.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_MESSAGE_CLEAN_URL ="/trade/xsl/trade_message_clean.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_DETAILS_URL ="/trade/xsl/trade_create_lc.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_BANK_DETAILS_URL ="/trade/xsl/bank_lc_reporting.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_DISCREPANT_URL ="/trade/xsl/trade_discrepant_rep_lc.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_SAVE_URL ="/trade/xsl/products/lc.xsl".intern();
		
	public final static String STYLESHEET_NEWS_SAVE_URL ="/core/xsl/admin/news/news.xsl".intern();
		
	public final static String STYLESHEET_CHANNEL_SAVE_URL ="/core/xsl/admin/news/news_channel.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_TEMPLATE_SAVE_URL ="/trade/xsl/products/lc_template.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_TEMPLATE_MODIFY_URL ="/trade/xsl/trade_template_lc.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_BANK_URL ="/trade/xsl/bank_lc_reporting.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_BANK_FORWARD_URL ="/trade/xsl/bank_lc_reporting_forward.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_AMEND_BANK_URL ="/trade/xsl/bank_lc_amend.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_BANK_SWIFT_70x_URL ="/trade/xsl/bank_lc_swift_70x.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_BANK_SWIFT_799_URL ="/trade/xsl/bank_lc_swift_799.xsl".intern();
		
	public final static String STYLESHEET_LC_FO_URL ="/trade/xsl/fo/lc_fo.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_FREEFORMAT_URL ="/trade/xsl/trade_freeformat_lc.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_BANK_FREEFORMAT_URL ="/trade/xsl/bank_lc_reporting_freeformat.xsl".intern();
		
	public final static String STYLESHEET_RE_CREATE_URL ="/cash/xsl/cash_create_remittance.xsl".intern();
		
	public final static String STYLESHEET_FT_MT101_DETAILS_URL ="/cash/xsl/cash_create_remittance.xsl".intern();
		
	public final static String STYLESHEET_FT_MT103_DETAILS_URL ="/cash/xsl/cash_create_remittance.xsl".intern();
		
	public final static String STYLESHEET_FT_FI103_DETAILS_URL ="/cash/xsl/cash_create_remittance.xsl".intern();
		
	public final static String STYLESHEET_FT_FI202_DETAILS_URL ="/cash/xsl/cash_create_remittance.xsl".intern();
		
	public final static String STYLESHEET_FT_MT101_BANK_URL ="/cash/xsl/bank_ft_remittance_reporting.xsl".intern();
		
	public final static String STYLESHEET_FT_MT103_BANK_URL ="/cash/xsl/bank_ft_remittance_reporting.xsl".intern();
		
	public final static String STYLESHEET_FT_FI103_BANK_URL ="/cash/xsl/bank_ft_remittance_reporting.xsl".intern();
		
	public final static String STYLESHEET_FT_FI202_BANK_URL ="/cash/xsl/bank_ft_remittance_reporting.xsl".intern();
		
	public final static String STYLESHEET_BP_DDA_CREATE_URL ="/cash/xsl/cash_create_billpaymentDDA.xsl".intern();
		
	public final static String STYLESHEET_FT_BILLP_DETAILS_URL ="/cash/xsl/cash_create_billpaymentDDA.xsl".intern();
		
	public final static String STYLESHEET_FT_BILLS_DETAILS_URL ="/cash/xsl/cash_create_billpaymentDDA.xsl".intern();
		
	public final static String STYLESHEET_FT_DDA_DETAILS_URL ="/cash/xsl/cash_create_billpaymentDDA.xsl".intern();
		
	public final static String STYLESHEET_FT_BILLP_BANK_URL ="/cash/xsl/bank_ft_bp_dda_reporting.xsl".intern();
		
	public final static String STYLESHEET_FT_BILLS_BANK_URL ="/cash/xsl/bank_ft_bp_dda_reporting.xsl".intern();
		
	public final static String STYLESHEET_FT_DDA_BANK_URL ="/cash/xsl/bank_ft_bp_dda_reporting.xsl".intern();
		
	public final static String STYLESHEET_SELECT_PAYEE_SERVICE_URL ="/cash/xsl/cash_select_payee_service.xsl".intern();
		
	public final static String STYLESHEET_DDA_UPDATE_URL ="/cash/xsl/cash_update_dda.xsl".intern();
		
	public final static String STYLESHEET_FT_BP_DDA_BANK_DDA_UPDATE_URL ="/cash/xsl/bank_ft_update_dda_reporting.xsl".intern();
		
	public final static String STYLESHEET_TERMINATE_RECURRING_URL ="/cash/xsl/cash_terminate_recurring.xsl".intern();
		
	public final static String STYLESHEET_CANCEL_POST_DATED_URL ="/cash/xsl/cash_cancel_post_dated.xsl".intern();
		
	public final static String STYLESHEET_FT_PI_CREATE_URL ="/cash/xsl/cash_create_pi.xsl".intern();
		
	public final static String STYLESHEET_FT_PICO_DETAILS_URL ="/cash/xsl/cash_create_pi.xsl".intern();
		
	public final static String STYLESHEET_FT_PIDD_DETAILS_URL ="/cash/xsl/cash_create_pi.xsl".intern();
		
	public final static String STYLESHEET_FT_PI_BANK_URL ="/cash/xsl/bank_ft_paperinstruments_reporting.xsl".intern();
		
	public final static String STYLESHEET_SE_CHEQUESERVICES_CREATE_URL ="/cash/xsl/cash_create_se_chequeservices.xsl".intern();
		
	public final static String STYLESHEET_SE_COCQS_DETAILS_URL ="/cash/xsl/cash_create_se_chequeservices.xsl".intern();
		
	public final static String STYLESHEET_SE_CQBKR_DETAILS_URL ="/cash/xsl/cash_create_se_chequeservices.xsl".intern();
		
	public final static String STYLESHEET_SE_CS_BANK_URL ="/cash/xsl/bank_se_chequeservices_reporting.xsl".intern();
		
	public final static String STYLESHEET_SE_CREATE_URL ="/trade/xsl/trade_create_se.xsl".intern();
		
	public final static String STYLESHEET_SE_SAVE_URL ="/trade/xsl/products/se.xsl".intern();
		
	public final static String STYLESHEET_SE_BANK_DETAILS_URL ="/trade/xsl/trade_create_se.xsl".intern();
		
	public final static String STYLESHEET_SE_DETAILS_URL ="/trade/xsl/trade_create_se.xsl".intern();
		
	public final static String STYLESHEET_SE_ADVNO_DETAILS_URL ="/trade/xsl/trade_create_se.xsl".intern();
		
	public final static String STYLESHEET_SE_OTHER_DETAILS_URL ="/trade/xsl/trade_create_se.xsl".intern();
		
	public final static String STYLESHEET_SE_ADVNO_BANK_URL ="/core/xsl/bank_secure_email_reporting.xsl".intern();
		
	public final static String STYLESHEET_SE_BANK_URL ="/trade/xsl/bank_se_reporting.xsl".intern();
		
	public final static String STYLESHEET_SE_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_SE_BANK_FORWARD_URL ="/trade/xsl/bank_se_reporting_forward.xsl".intern();
		
	public final static String STYLESHEET_SE_FILE_UPLOAD_URL ="/trade/xsl/trade_create_se_file_upload.xsl".intern();
		
	public final static String STYLESHEET_SE_ULOAD_DETAILS_URL ="/trade/xsl/trade_create_se_file_upload.xsl".intern();
		
	public final static String STYLESHEET_SE_FILE_UPLOAD_INQUIRY_URL ="/trade/xsl/trade_inquiry_se_file_upload.xsl".intern();
		
	public final static String STYLESHEET_SE_REPORTING_FO_URL ="/trade/xsl/fo/se_reporting_fo.xsl".intern();
		
	public final static String STYLESHEET_SE_DT_DETAILS_URL ="/loan/xsl/loan_create_se_document_tracking.xsl".intern();
		
	public final static String STYLESHEET_SG_CREATE_URL ="/trade/xsl/trade_create_sg.xsl".intern();
		
	public final static String STYLESHEET_SG_AMEND_URL ="/trade/xsl/trade_amend_sg.xsl".intern();
		
	public final static String STYLESHEET_SG_MESSAGE_URL ="/core/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_SG_DETAILS_URL ="/trade/xsl/trade_create_sg.xsl".intern();
		
	public final static String STYLESHEET_SG_BANK_DETAILS_URL ="/trade/xsl/trade_create_sg.xsl".intern();
		
	public final static String STYLESHEET_SG_SAVE_URL ="/trade/xsl/products/sg.xsl".intern();
		
	public final static String STYLESHEET_SG_BANK_URL ="/trade/xsl/bank_sg_reporting.xsl".intern();
		
	public final static String STYLESHEET_SG_FO_URL ="/trade/xsl/fo/sg_fo.xsl".intern();
		
	public final static String STYLESHEET_SG_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_SG_BANK_FORWARD_URL ="/trade/xsl/bank_sg_reporting_forward.xsl".intern();
		
	public final static String STYLESHEET_TF_CREATE_URL ="/trade/xsl/trade_create_tf.xsl".intern();
		
	public final static String STYLESHEET_TF_AMEND_URL ="/trade/xsl/trade_amend_tf.xsl".intern();
		
	public final static String STYLESHEET_TF_MESSAGE_URL ="/trade/xsl/trade_tf_message.xsl".intern();
		
	public final static String STYLESHEET_TF_DETAILS_URL ="/trade/xsl/trade_create_tf.xsl".intern();
		
	public final static String STYLESHEET_TF_BANK_DETAILS_URL ="/trade/xsl/trade_create_tf.xsl".intern();
		
	public final static String STYLESHEET_TF_OTHER_BANK_DETAILS_URL ="/trade/xsl/trade_create_tf.xsl".intern();
		
	public final static String STYLESHEET_TF_SAVE_URL ="/trade/xsl/products/tf.xsl".intern();
		
	public final static String STYLESHEET_TF_BANK_URL ="/trade/xsl/bank_tf_reporting.xsl".intern();
		
	public final static String STYLESHEET_TF_FO_URL ="/trade/xsl/fo/tf_fo.xsl".intern();
		
	public final static String STYLESHEET_TF_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_TF_BANK_FORWARD_URL ="/trade/xsl/bank_tf_reporting_forward.xsl".intern();
		
	public final static String STYLESHEET_TF_SELECT_URL ="/trade/xsl/trade_select_tf.xsl".intern();
		
	public final static String STYLESHEET_BG_CREATE_URL ="/trade/xsl/trade_create_bg.xsl".intern();
		
	public final static String STYLESHEET_BG_AMEND_URL ="/trade/xsl/trade_amend_bg.xsl".intern();
		
	public final static String STYLESHEET_BG_RELEASE_URL ="/trade/xsl/trade_release_bg.xsl".intern();
		
	public final static String STYLESHEET_BG_MESSAGE_URL ="/trade/xsl/trade_message_bg.xsl".intern();
		
	public final static String STYLESHEET_BG_DETAILS_URL ="/trade/xsl/trade_create_bg.xsl".intern();
		
	public final static String STYLESHEET_BG_BANK_DETAILS_URL ="/trade/xsl/bank_bg_reporting.xsl".intern();
		
	public final static String STYLESHEET_BG_SAVE_URL ="/trade/xsl/products/bg.xsl".intern();
		
	public final static String STYLESHEET_BG_TEMPLATE_SAVE_URL ="/trade/xsl/products/bg_template.xsl".intern();
		
	public final static String STYLESHEET_BG_TEMPLATE_MODIFY_URL ="/trade/xsl/trade_template_bg.xsl".intern();
		
	public final static String STYLESHEET_BG_AMEND_BANK_URL ="/trade/xsl/bank_bg_amend.xsl".intern();
		
	public final static String STYLESHEET_BG_BANK_URL ="/trade/xsl/bank_bg_reporting.xsl".intern();
		
	public final static String STYLESHEET_BG_FO_URL ="/trade/xsl/fo/bg_fo.xsl".intern();
		
	public final static String STYLESHEET_BG_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_BG_BANK_FORWARD_URL ="/trade/xsl/bank_bg_reporting_forward.xsl".intern();
		
	public final static String STYLESHEET_XHTML_TO_XSLFO ="/core/xsl/xhtml2xslfo.xsl".intern();
		
	public final static String STYLESHEET_HTML_TO_XSL ="/core/xsl/html2xsl.xsl".intern();
		
	public final static String STYLESHEET_BG_UPDATE_URL ="/trade/xsl/report/trade_changeset_bg.xsl".intern();
		
	public final static String STYLESHEET_SI_UPDATE_URL ="/trade/xsl/report/trade_changeset_si.xsl".intern();
		
	public final static String STYLESHEET_LC_LCSTD_UPDATE_URL ="/trade/xsl/report/trade_changeset_lc.xsl".intern();
		
	public final static String STYLESHEET_EC_CREATE_URL ="/trade/xsl/trade_create_ec.xsl".intern();
		
	public final static String STYLESHEET_EC_AMEND_URL ="/trade/xsl/trade_amend_ec.xsl".intern();
		
	public final static String STYLESHEET_EC_MESSAGE_URL ="/trade/xsl/trade_message_ec.xsl".intern();
		
	public final static String STYLESHEET_EC_DETAILS_URL ="/trade/xsl/trade_create_ec.xsl".intern();
		
	public final static String STYLESHEET_EC_BANK_DETAILS_URL ="/trade/xsl/bank_ec_reporting.xsl".intern();
		
	public final static String STYLESHEET_EC_SAVE_URL ="/trade/xsl/products/ec.xsl".intern();
		
	public final static String STYLESHEET_EC_TEMPLATE_SAVE_URL ="/trade/xsl/products/ec_template.xsl".intern();
		
	public final static String STYLESHEET_EC_TEMPLATE_MODIFY_URL ="/trade/xsl/trade_template_ec.xsl".intern();
		
	public final static String STYLESHEET_EC_BANK_URL ="/trade/xsl/bank_ec_reporting.xsl".intern();
		
	public final static String STYLESHEET_EC_FO_URL ="/trade/xsl/fo/ec_fo.xsl".intern();
		
	public final static String STYLESHEET_EC_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_EC_BANK_FORWARD_URL ="/trade/xsl/bank_ec_reporting_forward.xsl".intern();
		
	public final static String STYLESHEET_IC_CREATE_URL ="/trade/xsl/trade_create_ic.xsl".intern();
		
	public final static String STYLESHEET_IC_MESSAGE_URL ="/core/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_IC_DETAILS_URL ="/trade/xsl/bank_ic_reporting.xsl".intern();
		
	public final static String STYLESHEET_IC_BANK_DETAILS_URL ="/trade/xsl/bank_ic_reporting.xsl".intern();
		
	public final static String STYLESHEET_IC_SAVE_URL ="/trade/xsl/products/ic.xsl".intern();
		
	public final static String STYLESHEET_IC_BANK_URL ="/trade/xsl/bank_ic_reporting.xsl".intern();
		
	public final static String STYLESHEET_IC_FO_URL ="/trade/xsl/fo/ic_fo.xsl".intern();
		
	public final static String STYLESHEET_IC_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_IC_BANK_FORWARD_URL ="/trade/xsl/bank_ic_reporting_forward.xsl".intern();
		
	public final static String STYLESHEET_ACCOUNT_NAME_XSL ="/core/xsl/account_name.xsl".intern();
		
	public final static String STYLESHEET_RI_CREATE_URL ="/trade/xsl/trade_create_ri.xsl".intern();
		
	public final static String STYLESHEET_RI_MESSAGE_URL ="/core/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_RI_DETAILS_URL ="/trade/xsl/bank_ri_reporting.xsl".intern();
		
	public final static String STYLESHEET_RI_BANK_DETAILS_URL ="/trade/xsl/bank_ri_reporting.xsl".intern();
		
	public final static String STYLESHEET_RI_SAVE_URL ="/trade/xsl/products/ri.xsl".intern();
		
	public final static String STYLESHEET_RI_BANK_URL ="/trade/xsl/bank_ri_reporting.xsl".intern();
		
	public final static String STYLESHEET_RI_FO_URL ="/trade/xsl/fo/ri_fo.xsl".intern();
		
	public final static String STYLESHEET_RI_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_RI_BANK_FORWARD_URL ="/trade/xsl/bank_ri_reporting_forward.xsl".intern();
		
	public final static String STYLESHEET_IR_CREATE_URL ="/trade/xsl/trade_create_ir.xsl".intern();
		
	public final static String STYLESHEET_IR_MESSAGE_URL ="/core/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_IR_DETAILS_URL ="/trade/xsl/bank_ir_reporting.xsl".intern();
		
	public final static String STYLESHEET_IR_BANK_DETAILS_URL ="/trade/xsl/bank_ir_reporting.xsl".intern();
		
	public final static String STYLESHEET_IR_SAVE_URL ="/trade/xsl/products/ir.xsl".intern();
		
	public final static String STYLESHEET_IR_BANK_URL ="/trade/xsl/bank_ir_reporting.xsl".intern();
		
	public final static String STYLESHEET_IR_FO_URL ="/trade/xsl/fo/ir_fo.xsl".intern();
		
	public final static String STYLESHEET_IR_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_IR_BANK_FORWARD_URL ="/trade/xsl/bank_ir_reporting_forward.xsl".intern();
		
	public final static String STYLESHEET_SI_CREATE_URL ="/trade/xsl/trade_create_si.xsl".intern();
		
	public final static String STYLESHEET_SI_AMEND_URL ="/trade/xsl/trade_amend_si.xsl".intern();
		
	public final static String STYLESHEET_SI_RELEASE_URL ="/trade/xsl/trade_release_si.xsl".intern();
		
	public final static String STYLESHEET_SI_FREEFORMAT_URL ="/trade/xsl/trade_freeformat_si.xsl".intern();
		
	public final static String STYLESHEET_SI_MESSAGE_URL ="/trade/xsl/trade_message_si.xsl".intern();
		
	public final static String STYLESHEET_SI_MESSAGE_DISCREPANT_URL ="/trade/xsl/trade_message_discrepant.xsl".intern();
		
	public final static String STYLESHEET_SI_DETAILS_URL ="/trade/xsl/trade_create_si.xsl".intern();
		
	public final static String STYLESHEET_SI_BANK_DETAILS_URL ="/trade/xsl/bank_si_reporting.xsl".intern();
		
	public final static String STYLESHEET_SI_DISCREPANT_URL ="/trade/xsl/trade_discrepant_rep_si.xsl".intern();
		
	public final static String STYLESHEET_SI_TEMPLATE_SAVE_URL ="/trade/xsl/si_template.xsl".intern();
		
	public final static String STYLESHEET_SI_TEMPLATE_MODIFY_URL ="/trade/xsl/trade_template_si.xsl".intern();
		
	public final static String STYLESHEET_SI_SAVE_URL ="/trade/xsl/products/si.xsl".intern();
		
	public final static String STYLESHEET_SI_BANK_URL ="/trade/xsl/bank_si_reporting.xsl".intern();
		
	public final static String STYLESHEET_SI_FO_URL ="/trade/xsl/fo/si_fo.xsl".intern();
		
	public final static String STYLESHEET_SI_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_SI_BANK_FORWARD_URL ="/trade/xsl/bank_si_reporting_forward.xsl".intern();
		
	public final static String STYLESHEET_SI_BANK_FREEFORMAT_URL ="/trade/xsl/bank_si_reporting_freeformat.xsl".intern();
		
	public final static String STYLESHEET_SR_CREATE_URL ="/trade/xsl/trade_create_sr.xsl".intern();
		
	public final static String STYLESHEET_SR_MESSAGE_URL ="/core/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_SR_MESSAGE_TRANSFER_URL ="/trade/xsl/trade_message_transfer.xsl".intern();
		
	public final static String STYLESHEET_SR_MESSAGE_ASSIGNEE_URL ="/trade/xsl/trade_message_transfer.xsl".intern();
		
	public final static String STYLESHEET_SR_DETAILS_URL ="/trade/xsl/bank_sr_reporting.xsl".intern();
		
	public final static String STYLESHEET_SR_BANK_DETAILS_URL ="/trade/xsl/bank_sr_reporting.xsl".intern();
		
	public final static String STYLESHEET_SR_SAVE_URL ="/trade/xsl/products/sr.xsl".intern();
		
	public final static String STYLESHEET_SR_BANK_URL ="/trade/xsl/bank_sr_reporting.xsl".intern();
		
	public final static String STYLESHEET_SR_BANK_FREEFORMAT_URL ="/trade/xsl/bank_sr_reporting.xsl".intern();
		
	public final static String STYLESHEET_SR_FO_URL ="/trade/xsl/fo/sr_fo.xsl".intern();
		
	public final static String STYLESHEET_SR_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_SR_BANK_FORWARD_URL ="/trade/xsl/bank_sr_reporting_forward.xsl".intern();
		
	public final static String STYLESHEET_BR_CREATE_URL ="/trade/xsl/trade_create_br.xsl".intern();
		
	public final static String STYLESHEET_BR_MESSAGE_URL ="/core/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_BR_MESSAGE_CLAIM_URL ="/trade/xsl/trade_message_claim.xsl".intern();
		
	public final static String STYLESHEET_BR_MESSAGE_TRANSFER_URL ="/trade/xsl/trade_message_transfer.xsl".intern();
		
	public final static String STYLESHEET_BR_MESSAGE_ASSIGNEE_URL ="/trade/xsl/trade_message_transfer.xsl".intern();
		
	public final static String STYLESHEET_BR_DETAILS_URL ="/trade/xsl/bank_br_reporting.xsl".intern();
		
	public final static String STYLESHEET_BR_BANK_DETAILS_URL ="/trade/xsl/bank_br_reporting.xsl".intern();
		
	public final static String STYLESHEET_BR_SAVE_URL ="/trade/xsl/products/br.xsl".intern();
		
	public final static String STYLESHEET_BR_BANK_URL ="/trade/xsl/bank_br_reporting.xsl".intern();
		
	public final static String STYLESHEET_BR_BANK_FREEFORMAT_URL ="/trade/xsl/bank_br_reporting.xsl".intern();
		
	public final static String STYLESHEET_BR_FO_URL ="/trade/xsl/fo/br_fo.xsl".intern();
		
	public final static String STYLESHEET_BR_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_BR_BANK_FORWARD_URL ="/trade/xsl/bank_br_reporting_forward.xsl".intern();
		
	public final static String STYLESHEET_EL_CREATE_URL ="/trade/xsl/trade_create_el.xsl".intern();
		
	public final static String STYLESHEET_EL_UPLOAD_MT700_URL ="/trade/xsl/trade_upload_mt700.xsl".intern();
		
	public final static String STYLESHEET_EL_UPDATE_URL ="/trade/xsl/trade_update_el.xsl".intern();
		
	public final static String STYLESHEET_EL_MESSAGE_URL ="/core/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_EL_MESSAGE_REMITTANCE_URL ="/trade/xsl/trade_message_remittance.xsl".intern();
		
	public final static String STYLESHEET_EL_MESSAGE_TRANSFER_URL ="/trade/xsl/trade_message_transfer.xsl".intern();
		
	public final static String STYLESHEET_EL_MESSAGE_ASSIGNEE_URL ="/trade/xsl/trade_message_transfer.xsl".intern();
		
	public final static String STYLESHEET_EL_DETAILS_URL ="/trade/xsl/bank_el_reporting.xsl".intern();
		
	public final static String STYLESHEET_EL_BANK_DETAILS_URL ="/trade/xsl/bank_el_reporting.xsl".intern();
		
	public final static String STYLESHEET_EL_SAVE_URL ="/trade/xsl/products/el.xsl".intern();
		
	public final static String STYLESHEET_EL_BANK_URL ="/trade/xsl/bank_el_reporting.xsl".intern();
		
	public final static String STYLESHEET_EL_BANK_UPLOAD_MT700_URL ="/trade/xsl/bank_upload_mt700.xsl".intern();
		
	public final static String STYLESHEET_EL_BANK_UPLOAD_MT720_URL ="/trade/xsl/bank_upload_mt720.xsl".intern();
		
	public final static String STYLESHEET_EL_BANK_FREEFORMAT_URL ="/trade/xsl/bank_el_reporting.xsl".intern();
		
	public final static String STYLESHEET_EL_FO_URL ="/trade/xsl/fo/el_fo.xsl".intern();
		
	public final static String STYLESHEET_EL_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_EL_BANK_FORWARD_URL ="/trade/xsl/bank_el_reporting_forward.xsl".intern();
		
	public final static String STYLESHEET_FT_CREATE_URL ="/cash/xsl/trade_create_ft.xsl".intern();
		
	public final static String STYLESHEET_FT_UPLOAD_URL ="/cash/xsl/trade_upload_ft.xsl".intern();
		
	public final static String STYLESHEET_FT_DETAILS_URL ="/cash/xsl/trade_create_ft.xsl".intern();
		
	public final static String STYLESHEET_FT_BANK_DETAILS_URL ="/cash/xsl/trade_create_ft.xsl".intern();
		
	public final static String STYLESHEET_FT_SAVE_URL ="/cash/xsl/products/ft.xsl".intern();
		
	public final static String STYLESHEET_FT_TEMPLATE_SAVE_URL ="/cash/xsl/products/ft_template.xsl".intern();
		
	public final static String STYLESHEET_FT_TEMPLATE_MODIFY_URL ="/cash/xsl/trade_template_ft.xsl".intern();
		
	public final static String STYLESHEET_FT_BANK_URL ="/cash/xsl/bank_ft_remittance_reporting.xsl".intern();
		
	public final static String STYLESHEET_FT_FO_URL ="/cash/xsl/fo/ft_fo.xsl".intern();
		
	public final static String STYLESHEET_FT_SUMMARY_URL ="/cash/xsl/cash_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_FT_INT_CREATE_URL ="/cash/xsl/cash_create_ft_int.xsl".intern();
		
	public final static String STYLESHEET_FT_INT_BANK_URL ="/cash/xsl/bank_ft_int_reporting.xsl".intern();
		
	public final static String STYLESHEET_FT_TPT_CREATE_URL ="/cash/xsl/cash_create_ft_tpt.xsl".intern();
		
	public final static String STYLESHEET_FT_TPT_BANK_URL ="/cash/xsl/bank_ft_tpt_reporting.xsl".intern();
		
	public final static String STYLESHEET_FT_DOM_CREATE_URL ="/cash/xsl/cash_create_ft_dom.xsl".intern();
		
	public final static String STYLESHEET_FT_DOM_BANK_URL ="/cash/xsl/bank_ft_dom_reporting.xsl".intern();
		
	public final static String STYLESHEET_FT_INT_DETAILS_URL ="/cash/xsl/cash_create_ft_int.xsl".intern();
		
	public final static String STYLESHEET_FT_TPT_DETAILS_URL ="/cash/xsl/cash_create_ft_tpt.xsl".intern();
		
	public final static String STYLESHEET_FT_DOM_DETAILS_URL ="/cash/xsl/cash_create_ft_dom.xsl".intern();
		
	public final static String STYLESHEET_SE_FO_URL ="/trade/xsl/fo/se_fo.xsl".intern();
		
	public final static String STYLESHEET_SE_DT_FO_URL ="/loan/xsl/fo/se_dt_fo.xsl".intern();
		
	public final static String STYLESHEET_LI_CREATE_URL ="/trade/xsl/trade_create_li.xsl".intern();
		
	public final static String STYLESHEET_LI_MESSAGE_URL ="/core/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_LI_DETAILS_URL ="/trade/xsl/trade_create_li.xsl".intern();
		
	public final static String STYLESHEET_LI_BANK_DETAILS_URL ="/trade/xsl/bank_li_reporting.xsl".intern();
		
	public final static String STYLESHEET_LI_SAVE_URL ="/trade/xsl/products/li.xsl".intern();
		
	public final static String STYLESHEET_LI_BANK_URL ="/trade/xsl/bank_li_reporting.xsl".intern();
		
	public final static String STYLESHEET_LI_FO_URL ="/trade/xsl/fo/li_fo.xsl".intern();
		
	public final static String STYLESHEET_LI_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_LI_BANK_FORWARD_URL ="/trade/xsl/bank_li_reporting_forward.xsl".intern();
		
	public final static String STYLESHEET_LN_CREATE_URL ="/loan/xsl/loan_create_ln.xsl".intern();
		
	public final static String STYLESHEET_LN_CREATE_REP_URL ="/loan/xsl/loan_create_ln.xsl".intern();
		
	public final static String STYLESHEET_LN_AMEND_URL ="/loan/xsl/loan_amend_ln.xsl".intern();
		
	public final static String STYLESHEET_LN_AMEND_REP_URL ="/loan/xsl/loan_amend_ln.xsl".intern();
		
	public final static String STYLESHEET_LN_MESSAGE_URL ="/core/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_LN_PAYMENT_URL ="/loan/xsl/loan_payment_ln.xsl".intern();
		
	public final static String STYLESHEET_LN_PAYMENT_REP_URL ="/loan/xsl/loan_payment_ln.xsl".intern();
		
	public final static String STYLESHEET_LN_DETAILS_URL ="/loan/xsl/loan_create_ln.xsl".intern();
		
	public final static String STYLESHEET_LN_DISCREPANT_URL ="/loan/xsl/trade_discrepant_rep_ln.xsl".intern();
		
	public final static String STYLESHEET_LN_SAVE_URL ="/loan/xsl/products/ln.xsl".intern();
		
	public final static String STYLESHEET_LN_BANK_URL ="/loan/xsl/bank_ln_reporting.xsl".intern();
		
	public final static String STYLESHEET_LN_BANK_DETAILS_URL ="/loan/xsl/bank_ln_reporting.xsl".intern();
		
	public final static String STYLESHEET_LN_AMEND_BANK_URL ="/loan/xsl/bank_ln_amend.xsl".intern();
		
	public final static String STYLESHEET_LN_AMEND_BANK_REP_URL ="/loan/xsl/bank_ln_amend.xsl".intern();
		
	public final static String STYLESHEET_LN_FO_URL ="/loan/xsl/fo/ln_fo.xsl".intern();
		
	public final static String STYLESHEET_LN_DEALS_LIST ="/loan/xsl/deals_list.xsl".intern();
		
	public final static String STYLESHEET_LN_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String LOAN_IQ_FOLER_URL ="/config/loaniq".intern();
		
	public final static String STYLESHEET_LN_REPRICE_URL ="/loan/xsl/loan_reprice_ln.xsl".intern();
		
	public final static String STYLESHEET_LN_REPRICE_REP_URL ="/loan/xsl/loan_reprice_ln.xsl".intern();
		
	public final static String STYLESHEET_LN_BILL_DETAILS_URL ="/loan/xsl/loan_bill_details_ln.xsl".intern();
		
	public final static String STYLESHEET_LN_DEMAND_BILL_FO_URL ="/loan/xsl/fo/ln_demand_bill_fo.xsl".intern();
		
	public final static String STYLESHEET_LN_BATCH_BILL_FO_URL ="/loan/xsl/fo/ln_batch_bill_fo.xsl".intern();
		
	public final static String STYLESHEET_LS_SAVE_URL ="/trade/xsl/products/ls.xsl".intern();
		
	public final static String STYLESHEET_LS_CREATE_URL ="/trade/xsl/trade_create_ls.xsl".intern();
		
	public final static String STYLESHEET_LS_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_LS_DETAILS_URL ="/trade/xsl/trade_create_ls.xsl".intern();
		
	public final static String STYLESHEET_LS_BANK_DETAILS_URL ="/trade/xsl/bank_ls_reporting.xsl".intern();
		
	public final static String STYLESHEET_LS_FO_URL ="/trade/xsl/fo/ls_fo.xsl".intern();
		
	public final static String STYLESHEET_LS_BANK_URL ="/trade/xsl/bank_ls_reporting.xsl".intern();
		
	public final static String STYLESHEET_LS_AMEND_URL ="/trade/xsl/trade_amend_ls.xsl".intern();
		
	public final static String STYLESHEET_LS_MESSAGE_URL ="/trade/xsl/trade_message_ls.xsl".intern();
		
	public final static String STYLESHEET_LS_TEMPLATE_SAVE_URL ="/trade/xsl/products/ls_template.xsl".intern();
		
	public final static String STYLESHEET_LS_TEMPLATE_MODIFY_URL ="/trade/xsl/trade_template_ls.xsl".intern();
		
	public final static String STYLESHEET_SW_CREATE_URL ="/summit/xsl/trade_create_sw.xsl".intern();
		
	public final static String STYLESHEET_SW_CREATE_REP_URL ="/summit/xsl/trade_create_rep_sw.xsl".intern();
		
	public final static String STYLESHEET_SW_AMEND_REP_URL ="/summit/xsl/trade_amend_rep_sw.xsl".intern();
		
	public final static String STYLESHEET_SW_MESSAGE_URL ="/summit/xsl/trade_message_sw.xsl".intern();
		
	public final static String STYLESHEET_SW_ERROR_URL ="/summit/xsl/trade_error_sw.xsl".intern();
		
	public final static String STYLESHEET_SW_MESSAGE_REP_URL ="/summit/xsl/trade_message_sw_rep.xsl".intern();
		
	public final static String STYLESHEET_SW_DETAILS_URL ="/summit/xsl/trade_create_sw.xsl".intern();
		
	public final static String STYLESHEET_SW_DISCREPANT_URL ="/summit/xsl/trade_discrepant_rep_sw.xsl".intern();
		
	public final static String STYLESHEET_SW_SAVE_URL ="/summit/xsl/products/sw.xsl".intern();
		
	public final static String STYLESHEET_SW_TEMPLATE_SAVE_URL ="/summit/xsl/sw_template.xsl".intern();
		
	public final static String STYLESHEET_SW_TEMPLATE_MODIFY_URL ="/summit/xsl/trade_template_sw.xsl".intern();
		
	public final static String STYLESHEET_SW_BANK_URL ="/summit/xsl/bank_sw_reporting.xsl".intern();
		
	public final static String STYLESHEET_SW_BANK_REP_URL ="/summit/xsl/bank_sw_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_SW_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_CASH_FLOW_SW_JSON_URL ="/summit/xsl/trade_cash_flow_json.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_CASH_FLOW_CDS_JSON_URL ="/summit/xsl/trade_cash_flow_cds_json.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_CASH_FLOW_CF_JSON_URL ="/summit/xsl/trade_cash_flow_cf_json.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_ASSET_SCHEDULE_SW_JSON_URL ="/summit/xsl/trade_asset_schedule_json.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_ASSET_SCHEDULE_CF_JSON_URL ="/summit/xsl/trade_asset_schedule_cf_json.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_CASH_FLOW_TRS_JSON_URL ="/summit/xsl/trade_cash_flow_trs_json.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_ASSET_SCHEDULE_TRS_FUNDING_JSON_URL ="/summit/xsl/trade_asset_schedule_trs_funding_json.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_ASSET_SCHEDULE_TRS_RETURN_JSON_URL ="/summit/xsl/trade_asset_schedule_trs_return_json.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_CASH_FLOW_ES_JSON_URL ="/summit/xsl/trade_cash_flow_es_json.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_CASH_FLOW_ES_PERFORMANCE_LEG_JSON_URL ="/summit/xsl/trade_cash_flow_performance_leg_es_json.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_ASSET_SCHEDULE_ES_JSON_URL ="/summit/xsl/trade_asset_schedule_es_json.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_FUTURE_DELIVERY_DATES_JSON_URL ="/summit/xsl/future_delivery_dates_json.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_LISTED_OPTION_DELIVERY_DATES_JSON_URL ="/summit/xsl/listed_option_delivery_dates_json.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_LISTED_OPTION_EXPIRY_DATES_JSON_URL ="/summit/xsl/listed_option_expiry_dates_json.xsl".intern();
		
	public final static String STYLESHEET_SW_FO_URL ="/summit/xsl/fo/sw_fo.xsl".intern();
		
	public final static String STYLESHEET_TS_CREATE_URL ="/summit/xsl/trade_create_ts.xsl".intern();
		
	public final static String STYLESHEET_TS_CREATE_REP_URL ="/summit/xsl/trade_create_rep_ts.xsl".intern();
		
	public final static String STYLESHEET_TS_AMEND_URL ="/summit/xsl/trade_amend_ts.xsl".intern();
		
	public final static String STYLESHEET_TS_AMEND_REP_URL ="/summit/xsl/trade_amend_rep_ts.xsl".intern();
		
	public final static String STYLESHEET_TS_MESSAGE_URL ="/summit/xsl/trade_message_ts.xsl".intern();
		
	public final static String STYLESHEET_TS_MESSAGE_REP_URL ="/summit/xsl/trade_message_ts_rep.xsl".intern();
		
	public final static String STYLESHEET_TS_DETAILS_URL ="/summit/xsl/trade_create_ts.xsl".intern();
		
	public final static String STYLESHEET_TS_DISCREPANT_URL ="/summit/xsl/trade_discrepant_rep_ts.xsl".intern();
		
	public final static String STYLESHEET_TS_SAVE_URL ="/summit/xsl/products/ts.xsl".intern();
		
	public final static String STYLESHEET_TS_TEMPLATE_SAVE_URL ="/summit/xsl/ts_template.xsl".intern();
		
	public final static String STYLESHEET_TS_TEMPLATE_MODIFY_URL ="/summit/xsl/trade_template_ts.xsl".intern();
		
	public final static String STYLESHEET_TS_BANK_URL ="/summit/xsl/bank_ts_reporting.xsl".intern();
		
	public final static String STYLESHEET_TS_BANK_REP_URL ="/summit/xsl/bank_ts_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_TS_FO_URL ="/summit/xsl/fo/ts_fo.xsl".intern();
		
	public final static String STYLESHEET_TS_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_ST_CREATE_URL ="/summit/xsl/trade_create_st.xsl".intern();
		
	public final static String STYLESHEET_ST_CREATE_REP_URL ="/summit/xsl/trade_create_rep_st.xsl".intern();
		
	public final static String STYLESHEET_ST_AMEND_URL ="/summit/xsl/trade_amend_st.xsl".intern();
		
	public final static String STYLESHEET_ST_AMEND_REP_URL ="/summit/xsl/trade_amend_rep_st.xsl".intern();
		
	public final static String STYLESHEET_ST_MESSAGE_URL ="/summit/xsl/trade_message_st.xsl".intern();
		
	public final static String STYLESHEET_ST_MESSAGE_REP_URL ="/summit/xsl/trade_message_st_rep.xsl".intern();
		
	public final static String STYLESHEET_ST_DETAILS_URL ="/summit/xsl/trade_create_st.xsl".intern();
		
	public final static String STYLESHEET_ST_DISCREPANT_URL ="/summit/xsl/trade_discrepant_rep_st.xsl".intern();
		
	public final static String STYLESHEET_ST_SAVE_URL ="/summit/xsl/products/st.xsl".intern();
		
	public final static String STYLESHEET_ST_TEMPLATE_SAVE_URL ="/summit/xsl/st_template.xsl".intern();
		
	public final static String STYLESHEET_ST_TEMPLATE_MODIFY_URL ="/summit/xsl/trade_template_st.xsl".intern();
		
	public final static String STYLESHEET_ST_BANK_URL ="/summit/xsl/bank_st_reporting.xsl".intern();
		
	public final static String STYLESHEET_ST_BANK_REP_URL ="/summit/xsl/bank_st_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_ST_FO_URL ="/summit/xsl/fo/st_fo.xsl".intern();
		
	public final static String STYLESHEET_ST_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_CASH_FLOW_ST_JSON_URL ="/summit/xsl/trade_cash_flow_st_json.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_ASSET_SCHEDULE_ST_JSON_URL ="/summit/xsl/trade_asset_schedule_st_json.xsl".intern();
		
	public final static String STYLESHEET_CS_CREATE_URL ="/summit/xsl/trade_create_cs.xsl".intern();
		
	public final static String STYLESHEET_CS_CREATE_REP_URL ="/summit/xsl/trade_create_rep_cs.xsl".intern();
		
	public final static String STYLESHEET_CS_AMEND_URL ="/summit/xsl/trade_amend_cs.xsl".intern();
		
	public final static String STYLESHEET_CS_AMEND_REP_URL ="/summit/xsl/trade_amend_rep_cs.xsl".intern();
		
	public final static String STYLESHEET_CS_MESSAGE_URL ="/summit/xsl/trade_message_cs.xsl".intern();
		
	public final static String STYLESHEET_CS_MESSAGE_REP_URL ="/summit/xsl/trade_message_cs_rep.xsl".intern();
		
	public final static String STYLESHEET_CS_DETAILS_URL ="/summit/xsl/trade_create_cs.xsl".intern();
		
	public final static String STYLESHEET_CS_DISCREPANT_URL ="/summit/xsl/trade_discrepant_rep_cs.xsl".intern();
		
	public final static String STYLESHEET_CS_SAVE_URL ="/summit/xsl/products/cs.xsl".intern();
		
	public final static String STYLESHEET_CS_TEMPLATE_SAVE_URL ="/summit/xsl/cs_template.xsl".intern();
		
	public final static String STYLESHEET_CS_TEMPLATE_MODIFY_URL ="/summit/xsl/trade_template_cs.xsl".intern();
		
	public final static String STYLESHEET_CS_BANK_URL ="/summit/xsl/bank_cs_reporting.xsl".intern();
		
	public final static String STYLESHEET_CS_BANK_REP_URL ="/summit/xsl/bank_cs_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_CS_FO_URL ="/summit/xsl/fo/cs_fo.xsl".intern();
		
	public final static String STYLESHEET_CS_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_ASSET_SCHEDULE_CDS_JSON_URL ="/summit/xsl/trade_asset_schedule_cds_json.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_ASSET_SCHEDULE_CT_JSON_URL ="/summit/xsl/trade_asset_schedule_ct_json.xsl".intern();
		
	public final static String STYLESHEET_CX_CREATE_URL ="/summit/xsl/trade_create_cx.xsl".intern();
		
	public final static String STYLESHEET_CX_CREATE_REP_URL ="/summit/xsl/trade_create_rep_cx.xsl".intern();
		
	public final static String STYLESHEET_CX_AMEND_URL ="/summit/xsl/trade_amend_cx.xsl".intern();
		
	public final static String STYLESHEET_CX_AMEND_REP_URL ="/summit/xsl/trade_amend_rep_cx.xsl".intern();
		
	public final static String STYLESHEET_CX_MESSAGE_URL ="/summit/xsl/trade_message_cx.xsl".intern();
		
	public final static String STYLESHEET_CX_MESSAGE_REP_URL ="/summit/xsl/trade_message_cx_rep.xsl".intern();
		
	public final static String STYLESHEET_CX_DETAILS_URL ="/summit/xsl/trade_create_cx.xsl".intern();
		
	public final static String STYLESHEET_CX_DISCREPANT_URL ="/summit/xsl/trade_discrepant_rep_cx.xsl".intern();
		
	public final static String STYLESHEET_CX_SAVE_URL ="/summit/xsl/products/cx.xsl".intern();
		
	public final static String STYLESHEET_CX_TEMPLATE_SAVE_URL ="/summit/xsl/cx_template.xsl".intern();
		
	public final static String STYLESHEET_CX_TEMPLATE_MODIFY_URL ="/summit/xsl/trade_template_cx.xsl".intern();
		
	public final static String STYLESHEET_CX_BANK_URL ="/summit/xsl/bank_cx_reporting.xsl".intern();
		
	public final static String STYLESHEET_CX_BANK_REP_URL ="/summit/xsl/bank_cx_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_CX_FO_URL ="/summit/xsl/fo/cx_fo.xsl".intern();
		
	public final static String STYLESHEET_CX_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_CASH_FLOW_CDX_JSON_URL ="/summit/xsl/trade_cash_flow_cdx_json.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_CASH_FLOW_CT_JSON_URL ="/summit/xsl/trade_cash_flow_ct_json.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_CASH_FLOW_FP_JSON_URL ="/summit/xsl/trade_cash_flow_cdx_json.xsl".intern();
		
	public final static String STYLESHEET_FP_CREATE_URL ="/summit/xsl/trade_create_fp.xsl".intern();
		
	public final static String STYLESHEET_FP_CREATE_REP_URL ="/summit/xsl/trade_create_rep_fp.xsl".intern();
		
	public final static String STYLESHEET_FP_AMEND_URL ="/summit/xsl/trade_amend_fp.xsl".intern();
		
	public final static String STYLESHEET_FP_AMEND_REP_URL ="/summit/xsl/trade_amend_rep_fp.xsl".intern();
		
	public final static String STYLESHEET_FP_MESSAGE_URL ="/summit/xsl/trade_message_fp.xsl".intern();
		
	public final static String STYLESHEET_FP_MESSAGE_REP_URL ="/summit/xsl/trade_message_fp_rep.xsl".intern();
		
	public final static String STYLESHEET_FP_DETAILS_URL ="/summit/xsl/trade_create_fp.xsl".intern();
		
	public final static String STYLESHEET_FP_DISCREPANT_URL ="/summit/xsl/trade_discrepant_rep_fp.xsl".intern();
		
	public final static String STYLESHEET_FP_SAVE_URL ="/summit/xsl/products/fp.xsl".intern();
		
	public final static String STYLESHEET_FP_TEMPLATE_SAVE_URL ="/summit/xsl/fp_template.xsl".intern();
		
	public final static String STYLESHEET_FP_TEMPLATE_MODIFY_URL ="/summit/xsl/trade_template_fp.xsl".intern();
		
	public final static String STYLESHEET_FP_BANK_URL ="/summit/xsl/bank_fp_reporting.xsl".intern();
		
	public final static String STYLESHEET_FP_BANK_REP_URL ="/summit/xsl/bank_fp_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_FP_FO_URL ="/summit/xsl/fo/fp_fo.xsl".intern();
		
	public final static String STYLESHEET_FP_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_FR_CREATE_URL ="/summit/xsl/trade_create_fr.xsl".intern();
		
	public final static String STYLESHEET_FR_CREATE_REP_URL ="/summit/xsl/trade_create_rep_fr.xsl".intern();
		
	public final static String STYLESHEET_FR_AMEND_URL ="/summit/xsl/trade_amend_fr.xsl".intern();
		
	public final static String STYLESHEET_FR_AMEND_REP_URL ="/summit/xsl/trade_amend_rep_fr.xsl".intern();
		
	public final static String STYLESHEET_FR_MESSAGE_URL ="/summit/xsl/trade_message_fr.xsl".intern();
		
	public final static String STYLESHEET_FR_MESSAGE_REP_URL ="/summit/xsl/trade_message_fr_rep.xsl".intern();
		
	public final static String STYLESHEET_FR_DETAILS_URL ="/summit/xsl/trade_create_fr.xsl".intern();
		
	public final static String STYLESHEET_FR_DISCREPANT_URL ="/summit/xsl/trade_discrepant_rep_fr.xsl".intern();
		
	public final static String STYLESHEET_FR_SAVE_URL ="/summit/xsl/products/fr.xsl".intern();
		
	public final static String STYLESHEET_FR_TEMPLATE_SAVE_URL ="/summit/xsl/fr_template.xsl".intern();
		
	public final static String STYLESHEET_FR_TEMPLATE_MODIFY_URL ="/summit/xsl/trade_template_fr.xsl".intern();
		
	public final static String STYLESHEET_FR_BANK_URL ="/summit/xsl/bank_fr_reporting.xsl".intern();
		
	public final static String STYLESHEET_FR_BANK_REP_URL ="/summit/xsl/bank_fr_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_FR_FO_URL ="/summit/xsl/fo/fr_fo.xsl".intern();
		
	public final static String STYLESHEET_FR_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_CASH_FLOW_FR_JSON_URL ="/summit/xsl/trade_cash_flow_fr_json.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_ASSET_SCHEDULE_FR_JSON_URL ="/summit/xsl/trade_asset_schedule_fr_json.xsl".intern();
		
	public final static String STYLESHEET_FU_CREATE_URL ="/summit/xsl/trade_create_fu.xsl".intern();
		
	public final static String STYLESHEET_FU_CREATE_REP_URL ="/summit/xsl/trade_create_rep_fu.xsl".intern();
		
	public final static String STYLESHEET_FU_AMEND_URL ="/summit/xsl/trade_amend_fu.xsl".intern();
		
	public final static String STYLESHEET_FU_AMEND_REP_URL ="/summit/xsl/trade_amend_rep_fu.xsl".intern();
		
	public final static String STYLESHEET_FU_MESSAGE_URL ="/summit/xsl/trade_message_fu.xsl".intern();
		
	public final static String STYLESHEET_FU_MESSAGE_REP_URL ="/summit/xsl/trade_message_fu_rep.xsl".intern();
		
	public final static String STYLESHEET_FU_DETAILS_URL ="/summit/xsl/trade_create_fu.xsl".intern();
		
	public final static String STYLESHEET_FU_DISCREPANT_URL ="/summit/xsl/trade_discrepant_rep_fu.xsl".intern();
		
	public final static String STYLESHEET_FU_SAVE_URL ="/summit/xsl/products/fu.xsl".intern();
		
	public final static String STYLESHEET_FU_TEMPLATE_SAVE_URL ="/summit/xsl/fu_template.xsl".intern();
		
	public final static String STYLESHEET_FU_TEMPLATE_MODIFY_URL ="/summit/xsl/trade_template_fu.xsl".intern();
		
	public final static String STYLESHEET_FU_BANK_URL ="/summit/xsl/bank_fu_reporting.xsl".intern();
		
	public final static String STYLESHEET_FU_BANK_REP_URL ="/summit/xsl/bank_fu_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_FU_FO_URL ="/summit/xsl/fo/fu_fo.xsl".intern();
		
	public final static String STYLESHEET_FU_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_LO_CREATE_URL ="/summit/xsl/trade_create_lo.xsl".intern();
		
	public final static String STYLESHEET_LO_CREATE_REP_URL ="/summit/xsl/trade_create_rep_lo.xsl".intern();
		
	public final static String STYLESHEET_LO_AMEND_URL ="/summit/xsl/trade_amend_lo.xsl".intern();
		
	public final static String STYLESHEET_LO_AMEND_REP_URL ="/summit/xsl/trade_amend_rep_lo.xsl".intern();
		
	public final static String STYLESHEET_LO_MESSAGE_URL ="/summit/xsl/trade_message_lo.xsl".intern();
		
	public final static String STYLESHEET_LO_MESSAGE_REP_URL ="/summit/xsl/trade_message_lo_rep.xsl".intern();
		
	public final static String STYLESHEET_LO_DETAILS_URL ="/summit/xsl/trade_create_lo.xsl".intern();
		
	public final static String STYLESHEET_LO_DISCREPANT_URL ="/summit/xsl/trade_discrepant_rep_lo.xsl".intern();
		
	public final static String STYLESHEET_LO_SAVE_URL ="/summit/xsl/products/lo.xsl".intern();
		
	public final static String STYLESHEET_LO_TEMPLATE_SAVE_URL ="/summit/xsl/lo_template.xsl".intern();
		
	public final static String STYLESHEET_LO_TEMPLATE_MODIFY_URL ="/summit/xsl/trade_template_lo.xsl".intern();
		
	public final static String STYLESHEET_LO_BANK_URL ="/summit/xsl/bank_lo_reporting.xsl".intern();
		
	public final static String STYLESHEET_LO_BANK_REP_URL ="/summit/xsl/bank_lo_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_LO_FO_URL ="/summit/xsl/fo/lo_fo.xsl".intern();
		
	public final static String STYLESHEET_LO_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_CT_CREATE_URL ="/summit/xsl/trade_create_ct.xsl".intern();
		
	public final static String STYLESHEET_CT_CREATE_REP_URL ="/summit/xsl/trade_create_rep_ct.xsl".intern();
		
	public final static String STYLESHEET_CT_AMEND_URL ="/summit/xsl/trade_amend_ct.xsl".intern();
		
	public final static String STYLESHEET_CT_AMEND_REP_URL ="/summit/xsl/trade_amend_rep_ct.xsl".intern();
		
	public final static String STYLESHEET_CT_MESSAGE_URL ="/summit/xsl/trade_message_ct.xsl".intern();
		
	public final static String STYLESHEET_CT_MESSAGE_REP_URL ="/summit/xsl/trade_message_ct_rep.xsl".intern();
		
	public final static String STYLESHEET_CT_DETAILS_URL ="/summit/xsl/trade_create_ct.xsl".intern();
		
	public final static String STYLESHEET_CT_DISCREPANT_URL ="/summit/xsl/trade_discrepant_rep_ct.xsl".intern();
		
	public final static String STYLESHEET_CT_SAVE_URL ="/summit/xsl/products/ct.xsl".intern();
		
	public final static String STYLESHEET_CT_TEMPLATE_SAVE_URL ="/summit/xsl/ct_template.xsl".intern();
		
	public final static String STYLESHEET_CT_TEMPLATE_MODIFY_URL ="/summit/xsl/trade_template_ct.xsl".intern();
		
	public final static String STYLESHEET_CT_BANK_URL ="/summit/xsl/bank_ct_reporting.xsl".intern();
		
	public final static String STYLESHEET_CT_BANK_REP_URL ="/summit/xsl/bank_ct_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_CT_FO_URL ="/summit/xsl/fo/ct_fo.xsl".intern();
		
	public final static String STYLESHEET_CT_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_EO_CREATE_URL ="/summit/xsl/trade_create_eo.xsl".intern();
		
	public final static String STYLESHEET_EO_CREATE_REP_URL ="/summit/xsl/trade_create_rep_eo.xsl".intern();
		
	public final static String STYLESHEET_EO_AMEND_URL ="/summit/xsl/trade_amend_eo.xsl".intern();
		
	public final static String STYLESHEET_EO_AMEND_REP_URL ="/summit/xsl/trade_amend_rep_eo.xsl".intern();
		
	public final static String STYLESHEET_EO_MESSAGE_URL ="/summit/xsl/trade_message_eo.xsl".intern();
		
	public final static String STYLESHEET_EO_MESSAGE_REP_URL ="/summit/xsl/trade_message_eo_rep.xsl".intern();
		
	public final static String STYLESHEET_EO_DISCREPANT_URL ="/summit/xsl/trade_discrepant_rep_eo.xsl".intern();
		
	public final static String STYLESHEET_EO_SAVE_URL ="/summit/xsl/products/eo.xsl".intern();
		
	public final static String STYLESHEET_EO_TEMPLATE_SAVE_URL ="/summit/xsl/eo_template.xsl".intern();
		
	public final static String STYLESHEET_EO_TEMPLATE_MODIFY_URL ="/summit/xsl/trade_template_eo.xsl".intern();
		
	public final static String STYLESHEET_EO_DETAILS_URL ="/summit/xsl/trade_create_eo.xsl".intern();
		
	public final static String STYLESHEET_EO_BANK_URL ="/summit/xsl/bank_eo_reporting.xsl".intern();
		
	public final static String STYLESHEET_EO_BANK_REP_URL ="/summit/xsl/bank_eo_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_EO_FO_URL ="/summit/xsl/fo/eo_fo.xsl".intern();
		
	public final static String STYLESHEET_EO_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_CF_CREATE_URL ="/summit/xsl/trade_create_cf.xsl".intern();
		
	public final static String STYLESHEET_CF_CREATE_REP_URL ="/summit/xsl/trade_create_rep_cf.xsl".intern();
		
	public final static String STYLESHEET_CF_AMEND_URL ="/summit/xsl/trade_amend_cf.xsl".intern();
		
	public final static String STYLESHEET_CF_AMEND_REP_URL ="/summit/xsl/trade_amend_rep_cf.xsl".intern();
		
	public final static String STYLESHEET_CF_MESSAGE_URL ="/summit/xsl/trade_message_cf.xsl".intern();
		
	public final static String STYLESHEET_CF_MESSAGE_REP_URL ="/summit/xsl/trade_message_cf_rep.xsl".intern();
		
	public final static String STYLESHEET_CF_SAVE_URL ="/summit/xsl/products/cf.xsl".intern();
		
	public final static String STYLESHEET_CF_TEMPLATE_SAVE_URL ="/summit/xsl/cf_template.xsl".intern();
		
	public final static String STYLESHEET_CF_TEMPLATE_MODIFY_URL ="/summit/xsl/trade_template_cf.xsl".intern();
		
	public final static String STYLESHEET_CF_DETAILS_URL ="/summit/xsl/trade_create_cf.xsl".intern();
		
	public final static String STYLESHEET_CF_BANK_URL ="/summit/xsl/bank_cf_reporting.xsl".intern();
		
	public final static String STYLESHEET_CF_BANK_REP_URL ="/summit/xsl/bank_cf_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_CF_FO_URL ="/summit/xsl/fo/cf_fo.xsl".intern();
		
	public final static String STYLESHEET_CF_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_ES_CREATE_URL ="/summit/xsl/trade_create_es.xsl".intern();
		
	public final static String STYLESHEET_ES_CREATE_REP_URL ="/summit/xsl/trade_create_rep_es.xsl".intern();
		
	public final static String STYLESHEET_ES_AMEND_URL ="/summit/xsl/trade_amend_es.xsl".intern();
		
	public final static String STYLESHEET_ES_AMEND_REP_URL ="/summit/xsl/trade_amend_rep_es.xsl".intern();
		
	public final static String STYLESHEET_ES_MESSAGE_URL ="/summit/xsl/trade_message_es.xsl".intern();
		
	public final static String STYLESHEET_ES_MESSAGE_REP_URL ="/summit/xsl/trade_message_es_rep.xsl".intern();
		
	public final static String STYLESHEET_ES_DETAILS_URL ="/summit/xsl/trade_create_es.xsl".intern();
		
	public final static String STYLESHEET_ES_DISCREPANT_URL ="/summit/xsl/trade_discrepant_rep_es.xsl".intern();
		
	public final static String STYLESHEET_ES_SAVE_URL ="/summit/xsl/products/es.xsl".intern();
		
	public final static String STYLESHEET_ES_TEMPLATE_SAVE_URL ="/summit/xsl/es_template.xsl".intern();
		
	public final static String STYLESHEET_ES_TEMPLATE_MODIFY_URL ="/summit/xsl/trade_template_es.xsl".intern();
		
	public final static String STYLESHEET_ES_BANK_URL ="/summit/xsl/bank_es_reporting.xsl".intern();
		
	public final static String STYLESHEET_ES_BANK_REP_URL ="/summit/xsl/bank_es_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_ES_FO_URL ="/summit/xsl/fo/es_fo.xsl".intern();
		
	public final static String STYLESHEET_ES_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_CALENDAR_STATIC_DATA ="/summit/xsl/static/calendar.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_CURRENCY_STATIC_DATA ="/summit/xsl/static/currency.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_CURRENCY_PAIRS_STATIC_DATA ="/summit/xsl/static/currency_pairs.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_INDICES_STATIC_DATA ="/summit/xsl/static/indices.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_CUSTOMER_STATIC_DATA ="/summit/xsl/static/customer.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_PORTFOLIO_STATIC_DATA ="/summit/xsl/static/portfolio.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_SECURITY_STATIC_DATA ="/summit/xsl/static/security.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_STOCK_STATIC_DATA ="/summit/xsl/static/stock.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_CREDIT_ENTITY_STATIC_DATA ="/summit/xsl/static/creditentity.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_SENIORITY_STATIC_DATA ="/summit/xsl/static/seniority.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_DOCUMENT_CLAUSE_STATIC_DATA ="/summit/xsl/static/document_clause.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_CREDIT_INDEX_STATIC_DATA ="/summit/xsl/static/creditindex.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_MATURITIES_STATIC_DATA ="/summit/xsl/static/maturity.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_TRANCHES_STATIC_DATA ="/summit/xsl/static/tranche.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_CONTRACT_STATIC_DATA ="/summit/xsl/static/contract.xsl".intern();
		
	public final static String STYLESHEET_SUMMIT_BROKER_STATIC_DATA ="/summit/xsl/static/broker.xsl".intern();
		
	public final static String STYLESHEET_FX_SAVE_URL ="/treasury/xsl/products/fx.xsl".intern();
		
	public final static String STYLESHEET_FX_CREATE_URL ="/cash/xsl/trade_create_fx.xsl".intern();
		
	public final static String STYLESHEET_FX_DETAILS_URL ="/cash/xsl/trade_create_fx.xsl".intern();
		
	public final static String STYLESHEET_FX_MESSAGE_URL ="/cash/xsl/trade_message_fx.xsl".intern();
		
	public final static String STYLESHEET_FX_SI_MESSAGE_URL ="/cash/xsl/trade_fx_si_message.xsl".intern();
		
	public final static String STYLESHEET_FX_BANK_URL ="/cash/xsl/bank_fx_reporting.xsl".intern();
		
	public final static String STYLESHEET_FX_FO_URL ="/cash/xsl/fo/fx_fo.xsl".intern();
		
	public final static String STYLESHEET_FX_SUMMARY_URL ="/cash/xsl/cash_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_XO_CREATE_URL ="/cash/xsl/trade_create_xo.xsl".intern();
		
	public final static String STYLESHEET_XO_SAVE_URL ="/cash/xsl/products/xo.xsl".intern();
		
	public final static String STYLESHEET_XO_DETAILS_URL ="/cash/xsl/trade_create_xo.xsl".intern();
		
	public final static String STYLESHEET_XO_BANK_URL ="/cash/xsl/bank_xo_reporting.xsl".intern();
		
	public final static String STYLESHEET_MESSAGE_XO_URL ="/cash/xsl/trade_message_xo.xsl".intern();
		
	public final static String STYLESHEET_XO_FO_URL ="/cash/xsl/fo/xo_fo.xsl".intern();
		
	public final static String STYLESHEET_XO_SUMMARY_URL ="/cash/xsl/cash_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_TD_SAVE_URL ="/cash/xsl/products/td.xsl".intern();
		
	public final static String STYLESHEET_TD_CREATE_URL ="/cash/xsl/cash_create_td_cstd.xsl".intern();
		
	public final static String STYLESHEET_TD_DETAILS_URL ="/cash/xsl/cash_create_td_cstd.xsl".intern();
		
	public final static String STYLESHEET_MESSAGE_TD_URL ="/cash/xsl/trade_message_td.xsl".intern();
		
	public final static String STYLESHEET_TD_SI_MESSAGE_URL ="/cash/xsl/trade_td_si_message.xsl".intern();
		
	public final static String STYLESHEET_TD_BANK_URL ="/cash/xsl/bank_td_reporting.xsl".intern();
		
	public final static String STYLESHEET_TD_FO_URL ="/cash/xsl/fo/td_fo.xsl".intern();
		
	public final static String STYLESHEET_TD_SUMMARY_URL ="/cash/xsl/cash_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_TD_BANK_DETAILS_URL ="/cash/xsl/cash_create_td_cstd.xsl".intern();
		
	public final static String STYLESHEET_LA_CREATE_URL ="/cash/xsl/trade_create_la.xsl".intern();
		
	public final static String STYLESHEET_LA_DETAILS_URL ="/cash/xsl/trade_create_la.xsl".intern();
		
	public final static String STYLESHEET_LA_SAVE_URL ="/cash/xsl/products/la.xsl".intern();
		
	public final static String STYLESHEET_LA_SI_MESSAGE_URL ="/cash/xsl/trade_la_si_message.xsl".intern();
		
	public final static String STYLESHEET_LA_FO_URL ="/cash/xsl/fo/la_fo.xsl".intern();
		
	public final static String STYLESHEET_LA_SUMMARY_URL ="/cash/xsl/cash_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_SP_CREATE_URL ="/cash/xsl/trade_create_sp.xsl".intern();
		
	public final static String STYLESHEET_SP_DETAILS_URL ="/cash/xsl/trade_create_sp.xsl".intern();
		
	public final static String STYLESHEET_SP_SAVE_URL ="/cash/xsl/products/sp.xsl".intern();
		
	public final static String STYLESHEET_SP_FO_URL ="/cash/xsl/fo/sp_fo.xsl".intern();
		
	public final static String STYLESHEET_SP_SUMMARY_URL ="/cash/xsl/cash_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_TO_CREATE_URL ="/cash/xsl/trade_create_to.xsl".intern();
		
	public final static String STYLESHEET_TO_DETAILS_URL ="/cash/xsl/trade_create_to.xsl".intern();
		
	public final static String STYLESHEET_TO_SAVE_URL ="/cash/xsl/products/to.xsl".intern();
		
	public final static String STYLESHEET_TO_FO_URL ="/cash/xsl/fo/to_fo.xsl".intern();
		
	public final static String STYLESHEET_TO_SUMMARY_URL ="/cash/xsl/cash_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_FORM_PARAMETERS_SAVE_URL ="/openaccount/xsl/form_parameters.xsl".intern();
		
	public final static String STYLESHEET_BASELINE_UPLOAD_TEMPLATE_URL ="/openaccount/xsl/baseline_upload_template.xsl".intern();
		
	public final static String STYLESHEET_SAVE_BASELINE_UPLOAD_TEMPLATE_URL ="/openaccount/xsl/baseline_upload_template_save.xsl".intern();
		
	public final static String STYLESHEET_CN_UPLOAD_TEMPLATE_URL ="/openaccount/xsl/credit_note_upload_template.xsl".intern();
		
	public final static String STYLESHEET_PO_FOLDER_URL ="/content/xsl/openaccount/po".intern();
		
	public final static String STYLESHEET_PO_CREATE_URL ="/openaccount/xsl/trade_create_po.xsl".intern();
		
	public final static String STYLESHEET_PO_CREATE_REP_URL ="/openaccount/xsl/trade_create_po.xsl".intern();
		
	public final static String STYLESHEET_PO_UPLOAD_URL ="/openaccount/xsl/trade_upload_po.xsl".intern();
		
	public final static String STYLESHEET_PO_UPLOAD_REP_URL ="/openaccount/xsl/trade_upload_rep_po.xsl".intern();
		
	public final static String STYLESHEET_PO_AMEND_URL ="/openaccount/xsl/trade_create_po.xsl".intern();
		
	public final static String STYLESHEET_PO_AMEND_REP_URL ="/openaccount/xsl/trade_create_rep_po.xsl".intern();
		
	public final static String STYLESHEET_PO_MESSAGE_URL ="/core/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_PO_MESSAGE_REP_URL ="/core/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_PO_MESSAGE_DISCREPANT_URL ="/openaccount/xsl/trade_message_utilize.xsl".intern();
		
	public final static String STYLESHEET_PO_MESSAGE_DISCREPANT_REP_URL ="/openaccount/xsl/trade_message_utilize_rep.xsl".intern();
		
	public final static String STYLESHEET_PO_MESSAGE_CLOSE_URL ="/openaccount/xsl/trade_message_close.xsl".intern();
		
	public final static String STYLESHEET_PO_MESSAGE_CLOSE_REP_URL ="/openaccount/xsl/trade_message_close_rep.xsl".intern();
		
	public final static String STYLESHEET_PO_DETAILS_URL ="/openaccount/xsl/trade_create_po.xsl".intern();
		
	public final static String STYLESHEET_PO_BANK_DETAILS_URL ="/openaccount/xsl/bank_po_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_PO_DISCREPANT_URL ="/openaccount/xsl/trade_discrepant_rep_po.xsl".intern();
		
	public final static String STYLESHEET_PO_SAVE_URL ="/openaccount/xsl/products/po.xsl".intern();
		
	public final static String STYLESHEET_PO_TEMPLATE_SAVE_URL ="/openaccount/xsl/products/po_template.xsl".intern();
		
	public final static String STYLESHEET_PO_TEMPLATE_MODIFY_URL ="/openaccount/xsl/trade_template_po.xsl".intern();
		
	public final static String STYLESHEET_PO_BANK_URL ="/openaccount/xsl/bank_po_reporting.xsl".intern();
		
	public final static String STYLESHEET_PO_BANK_REP_URL ="/openaccount/xsl/bank_po_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_PO_FO_URL ="/openaccount/xsl/fo/po_fo.xsl".intern();
		
	public final static String STYLESHEET_PO_FORM_PARAMETERS_URL ="/openaccount/xsl/sy_maintain_po_form_parameters.xsl".intern();
		
	public final static String STYLESHEET_PO_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_IO_CREATE_URL ="/openaccount/xsl/trade_create_io.xsl".intern();
		
	public final static String STYLESHEET_IO_CREATE_REP_URL ="/openaccount/xsl/trade_create_io.xsl".intern();
		
	public final static String STYLESHEET_IO_UPLOAD_URL ="/openaccount/xsl/trade_upload_io.xsl".intern();
		
	public final static String STYLESHEET_IO_UPLOAD_REP_URL ="/openaccount/xsl/trade_upload_rep_io.xsl".intern();
		
	public final static String STYLESHEET_IO_AMEND_URL ="/openaccount/xsl/trade_amend_io.xsl".intern();
		
	public final static String STYLESHEET_IO_AMEND_REP_URL ="/openaccount/xsl/trade_amend_io.xsl".intern();
		
	public final static String STYLESHEET_IO_PAYMENT_URL ="/openaccount/xsl/trade_payment_io.xsl".intern();
		
	public final static String STYLESHEET_IO_PAYMENT_REP_URL ="/openaccount/xsl/trade_payment_io.xsl".intern();
		
	public final static String STYLESHEET_IO_MESSAGE_URL ="/core/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_IO_MESSAGE_REP_URL ="/core/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_IO_MESSAGE_DISCREPANT_URL ="/openaccount/xsl/trade_message_utilize.xsl".intern();
		
	public final static String STYLESHEET_IO_MESSAGE_DISCREPANT_REP_URL ="/openaccount/xsl/trade_message_utilize_rep.xsl".intern();
		
	public final static String STYLESHEET_IO_MESSAGE_CLOSE_URL ="/openaccount/xsl/trade_message_close.xsl".intern();
		
	public final static String STYLESHEET_IO_MESSAGE_CLOSE_REP_URL ="/openaccount/xsl/trade_message_close_rep.xsl".intern();
		
	public final static String STYLESHEET_IO_DETAILS_URL ="/openaccount/xsl/trade_create_io.xsl".intern();
		
	public final static String STYLESHEET_IO_BANK_DETAILS_URL ="/openaccount/xsl/bank_io_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_IO_DISCREPANT_URL ="/openaccount/xsl/trade_discrepant_rep_io.xsl".intern();
		
	public final static String STYLESHEET_IO_SAVE_URL ="/openaccount/xsl/products/io.xsl".intern();
		
	public final static String STYLESHEET_IO_TEMPLATE_SAVE_URL ="/openaccount/xsl/products/io_template.xsl".intern();
		
	public final static String STYLESHEET_IO_TEMPLATE_MODIFY_URL ="/openaccount/xsl/trade_template_io.xsl".intern();
		
	public final static String STYLESHEET_IO_BANK_URL ="/openaccount/xsl/bank_io_reporting.xsl".intern();
		
	public final static String STYLESHEET_IO_BANK_REP_URL ="/openaccount/xsl/bank_io_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_IO_FO_URL ="/openaccount/xsl/fo/io_fo.xsl".intern();
		
	public final static String STYLESHEET_IO_FORM_PARAMETERS_URL ="/openaccount/xsl/sy_maintain_io_form_parameters.xsl".intern();
		
	public final static String STYLESHEET_IO_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_RS_SAVE_URL ="/openaccount/xsl/products/rs.xsl".intern();
		
	public final static String STYLESHEET_SO_FOLDER_URL ="/content/xsl/openaccount/so".intern();
		
	public final static String STYLESHEET_SO_CREATE_URL ="/openaccount/xsl/trade_create_so.xsl".intern();
		
	public final static String STYLESHEET_SO_RESUBMIT_URL ="/openaccount/xsl/trade_resubmit_so.xsl".intern();
		
	public final static String STYLESHEET_SO_CREATE_REP_URL ="/openaccount/xsl/trade_create_so.xsl".intern();
		
	public final static String STYLESHEET_SO_MESSAGE_URL ="/core/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_SO_MESSAGE_REP_URL ="/core/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_SO_DETAILS_URL ="/openaccount/xsl/trade_create_so.xsl".intern();
		
	public final static String STYLESHEET_SO_SAVE_URL ="/openaccount/xsl/products/so.xsl".intern();
		
	public final static String STYLESHEET_SO_BANK_URL ="/openaccount/xsl/bank_so_reporting.xsl".intern();
		
	public final static String STYLESHEET_SO_BANK_FROM_TSU_URL ="/openaccount/xsl/bank_so_reporting_from_tsu.xsl".intern();
		
	public final static String STYLESHEET_SO_BANK_REP_URL ="/openaccount/xsl/bank_so_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_SO_FO_URL ="/openaccount/xsl/fo/so_fo.xsl".intern();
		
	public final static String STYLESHEET_SO_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_IN_FOLDER_URL ="/content/xsl/openaccount/in".intern();
		
	public final static String STYLESHEET_IN_CREATE_URL ="/openaccount/xsl/trade_create_in.xsl".intern();
		
	public final static String STYLESHEET_IN_CREATE_REP_URL ="/openaccount/xsl/trade_create_in.xsl".intern();
		
	public final static String STYLESHEET_IN_UPLOAD_URL ="/openaccount/xsl/trade_upload_in.xsl".intern();
		
	public final static String STYLESHEET_IN_PO_BASED_EARLY_PAYMENT_PRESENT_URL ="/openaccount/xsl/trade_po_based_early_payment_present.xsl".intern();
		
	public final static String STYLESHEET_IN_UPLOAD_REP_URL ="/openaccount/xsl/trade_upload_rep_in.xsl".intern();
		
	public final static String STYLESHEET_IN_AMEND_URL ="/openaccount/xsl/trade_amend_in.xsl".intern();
		
	public final static String STYLESHEET_IN_AMEND_REP_URL ="/openaccount/xsl/trade_amend_rep_in.xsl".intern();
		
	public final static String STYLESHEET_IN_PRESENT_URL ="/openaccount/xsl/trade_present_in.xsl".intern();
		
	public final static String STYLESHEET_IN_PRESENT_REP_URL ="/openaccount/xsl/trade_present_rep_in.xsl".intern();
		
	public final static String STYLESHEET_IN_MESSAGE_URL ="/openaccount/xsl/trade_message_baseline.xsl".intern();
		
	public final static String STYLESHEET_IN_MESSAGE_REP_URL ="/core/xsl/trade_message_rep.xsl".intern();
		
	public final static String STYLESHEET_IN_MESSAGE_DISCREPANT_URL ="/openaccount/xsl/trade_message_discrepant_in.xsl".intern();
		
	public final static String STYLESHEET_IN_MESSAGE_DISCREPANT_REP_URL ="/openaccount/xsl/trade_message_discrepant_in_rep.xsl".intern();
		
	public final static String STYLESHEET_IN_DETAILS_URL ="/openaccount/xsl/trade_create_in.xsl".intern();
		
	public final static String STYLESHEET_IN_BANK_DETAILS_URL ="/openaccount/xsl/bank_in_reporting.xsl".intern();
		
	public final static String STYLESHEET_IN_SAVE_URL ="/openaccount/xsl/products/in.xsl".intern();
		
	public final static String STYLESHEET_IN_TEMPLATE_SAVE_URL ="/openaccount/xsl/products/in_template.xsl".intern();
		
	public final static String STYLESHEET_IN_TEMPLATE_MODIFY_URL ="/openaccount/xsl/trade_template_in.xsl".intern();
		
	public final static String STYLESHEET_IN_BANK_URL ="/openaccount/xsl/bank_in_reporting.xsl".intern();
		
	public final static String STYLESHEET_IN_BANK_REP_URL ="/openaccount/xsl/bank_in_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_IN_FO_URL ="/openaccount/xsl/fo/in_fo.xsl".intern();
		
	public final static String STYLESHEET_IN_FO_FOLDER_URL ="/content/xsl/openaccount/fo".intern();
		
	public final static String STYLESHEET_IN_UPLOAD_TEMPLATE_URL ="/openaccount/xsl/in_upload_template.xsl".intern();
		
	public final static String STYLESHEET_IN_SUMMARY_URL ="/openaccount/xsl/trade_in_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_IN_FORM_PARAMETERS_URL ="/openaccount/xsl/sy_maintain_in_form_parameters.xsl".intern();
		
	public final static String JAVASCRIPT_TRADE_CREATE_IN_URL ="/content/OLD/javascript/openaccount/trade_create_in.js".intern();
		
	public final static String JAVASCRIPT_TRADE_UPLOAD_IN_URL ="/content/OLD/javascript/openaccount/trade_upload_in.js".intern();
		
	public final static String STYLESHEET_LT_SAVE_URL ="/openaccount/xsl/products/lt.xsl".intern();
		
	public final static String STYLESHEET_LT_TEMPLATE_SAVE_URL ="/openaccount/xsl/products/lt_template.xsl".intern();
		
	public final static String STYLESHEET_TSU_MESSAGE_SAVE_URL ="/tsu/xsl/tsu.xsl".intern();
		
	public final static String STYLESHEET_PDF_SUMMARY_URL ="/core/xsl/fo/export_pdf_summary.xsl".intern();
		
	public final static String STYLESHEET_BK_CREATE_URL ="/core/xsl/core_create_bk.xsl".intern();
		
	public final static String STYLESHEET_BK_MERGE_URL ="/core/xsl/core_bk_merge.xsl".intern();
		
	public final static String STYLESHEET_BK_UPLOAD_URL ="/core/xsl/bk_file_upload.xsl".intern();
		
	public final static String STYLESHEET_BK_FILE_UPLOAD_DETAILS ="/core/xsl/bk_file_upload_details.xsl".intern();
		
	public final static String STYLESHEET_BK_DETAILS_URL ="/core/xsl/core_create_bk.xsl".intern();
		
	public final static String STYLESHEET_BK_BANK_DETAILS_URL ="/core/xsl/core_create_bk.xsl".intern();
		
	public final static String STYLESHEET_BK_SAVE_URL ="/core/xsl/products/bk.xsl".intern();
		
	public final static String STYLESHEET_BK_TEMPLATE_SAVE_URL ="/core/xsl/products/bk_template.xsl".intern();
		
	public final static String STYLESHEET_BK_TEMPLATE_MODIFY_URL ="/core/xsl/core_template_bk.xsl".intern();
		
	public final static String STYLESHEET_BK_BANK_URL ="/core/xsl/bank_bk_reporting.xsl".intern();
		
	public final static String STYLESHEET_BK_FO_URL ="/core/xsl/fo/bk_fo.xsl".intern();
		
	public final static String STYLESHEET_BK_UPLOAD_FO_URL ="/core/xsl/fo/bk_file_upload_fo.xsl".intern();
		
	public final static String STYLESHEET_BK_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_SY_ROLEPERMISSION_URL ="/core/xsl/system/sy_roleperm.xsl".intern();
		
	public final static String STYLESHEET_SY_DISPLAY_ROLEPERMISSION_URL ="/core/xsl/system/sy_roleperm.xsl".intern();
		
	public final static String STYLESHEET_SY_BANKCOMPANY_URL ="/core/xsl/system/sy_bankcompany.xsl".intern();
		
	public final static String STYLESHEET_SY_DISPLAY_BANKCOMPANY_URL ="/core/xsl/system/sy_bankcompany.xsl".intern();
		
	public final static String STYLESHEET_SY_CUSTOMERREFERENCE_URL ="/core/xsl/system/sy_customerreference.xsl".intern();
		
	public final static String STYLESHEET_SY_DISPLAY_CUSTOMERREFERENCE_URL ="/core/xsl/system/sy_customerreference.xsl".intern();
		
	public final static String STYLESHEET_SY_COMPANY_URL ="/core/xsl/system/sy_company.xsl".intern();
		
	public final static String STYLESHEET_SY_DISPLAY_COMPANY_URL ="/core/xsl/system/sy_company.xsl".intern();
		
	public final static String STYLESHEET_SY_BANK_URL ="/core/xsl/system/sy_bank.xsl".intern();
		
	public final static String STYLESHEET_SY_PSML_URL ="/core/xsl/system/sy_psml.xsl".intern();
		
	public final static String STYLESHEET_SY_RATE_URL ="/core/xsl/system/sy_rate.xsl".intern();
		
	public final static String STYLESHEET_SY_BOREFERENCE_URL ="/core/xsl/system/sy_borefid.xsl".intern();
		
	public final static String STYLESHEET_SY_GROUPING_RULE_URL ="/openaccount/xsl/sy_grouping_rule.xsl".intern();
		
	public final static String STYLESHEET_GUARANTEE_URL ="/core/xsl/system/sy_guarantee.xsl".intern();
		
	public final static String STYLESHEET_GUARANTEE_REP_URL ="/core/xsl/system/sy_guarantee_rep.xsl".intern();
		
	public final static String STYLESHEET_SAVE_GUARANTEE_DOC_URL ="/core/xsl/system/guarantee.xsl".intern();
		
	public final static String STYLESHEET_SAVE_CUSTOMERS_GUARANTEE_DOC_URL ="/core/xsl/system/guarantee_customers.xsl".intern();
		
	public final static String STYLESHEET_SELECT_GTEE_FOR_INIT_URL ="/trade/xsl/trade_select_bg.xsl".intern();
		
	public final static String STYLESHEET_SY_ACCOUNT_URL ="/core/xsl/system/sy_account.xsl".intern();
		
	public final static String STYLESHEET_SY_ACCOUNTS_NICK_NAME_URL ="/core/xsl/system/sy_accounts_nick_name.xsl".intern();
		
	public final static String STYLESHEET_SY_ACCOUNT_DETAILS_DISPLAY_URL ="/cash/xsl/account_details_display.xsl".intern();
		
	public final static String STYLESHEET_SY_ACCOUNT_POPUP_URL ="/core/xsl/popups/sy_account.xsl".intern();
		
	public final static String STYLESHEET_SY_ENTITY_URL ="/core/xsl/system/sy_entity.xsl".intern();
		
	public final static String STYLESHEET_SY_ENTITY_MC_URL ="/core/xsl/system/sy_entity_mc.xsl".intern();
		
	public final static String STYLESHEET_SY_ENTITY_MC_INTERFACE_URL ="/core/xsl/system/sy_entity_mc_interface.xsl".intern();
		
	public final static String STYLESHEET_SY_BANKBENEFICIARY_URL ="/core/xsl/system/sy_bankbeneficiary.xsl".intern();
		
	public final static String STYLESHEET_SY_BANKBENEFICIARY_POPUP_URL ="/core/xsl/popups/sy_bankbeneficiary.xsl".intern();
		
	public final static String STYLESHEET_SY_STATIC_BANK_URL ="/core/xsl/system/sy_static_bank.xsl".intern();
		
	public final static String STYLESHEET_SY_PHRASE_URL ="/core/xsl/system/sy_phrase.xsl".intern();
		
	public final static String STYLESHEET_SY_PHRASE_POPUP_URL ="/core/xsl/popups/sy_phrase.xsl".intern();
		
	public final static String STYLESHEET_SY_PRODUCT_URL ="/core/xsl/system/sy_product.xsl".intern();
		
	public final static String STYLESHEET_SY_PRODUCT_POPUP_URL ="/core/xsl/popups/sy_product.xsl".intern();
		
	public final static String STYLESHEET_SY_ROLE_URL ="/core/xsl/system/sy_role.xsl".intern();
		
	public final static String STYLESHEET_SY_ROLE_DESCRIPTION_URL ="/core/xsl/system/sy_roledesc.xsl".intern();
		
	public final static String STYLESHEET_SY_DISPLAY_ROLE_URL ="/core/xsl/system/sy_role.xsl".intern();
		
	public final static String STYLESHEET_SY_DISPLAY_USER_URL ="/core/xsl/system/sy_user.xsl".intern();
		
	public final static String STYLESHEET_SY_USER_URL ="/core/xsl/system/sy_user.xsl".intern();
		
	public final static String STYLESHEET_SY_PASSWORD_URL ="/core/xsl/system/sy_authentication.xsl".intern();
		
	public final static String STYLESHEET_SY_LOGIN_URL ="/core/xsl/system/sy_login.xsl".intern();
		
	public final static String STYLESHEET_SY_LOGIN_CHANGE_PASSWORD_URL ="/core/xsl/system/sy_login_change_password.xsl".intern();
		
	public final static String STYLESHEET_SY_LOGIN_CHANGE_PASSWORD_QA_URL ="core/xsl/system/sy_login_change_password_qa.xsl".intern();
		
	public final static String STYLESHEET_SY_CHANGE_PASSWORD ="/core/xsl/system/sy_change_password.xsl".intern();
		
	public final static String STYLESHEET_SY_RESET_PASSWORD ="/core/xsl/system/sy_reset_password.xsl".intern();
		
	public final static String STYLESHEET_SY_ANSWER_QUESTION ="/core/xsl/system/sy_answer_question.xsl".intern();
		
	public final static String STYLESHEET_SY_ACCEPT_TERMS_CONDITION_URL ="core/xsl/system/sy_accept_terms.xsl".intern();
		
	public final static String STYLESHEET_SY_CONTINUE_NEW_SESSION_URL ="core/xsl/system/sy_continue_new_session.xsl".intern();
		
	public final static String STYLESHEET_SY_LOGOUT_URL ="/core/xsl/system/sy_logout.xsl".intern();
		
	public final static String STYLESHEET_SY_USERPROFILE_URL ="/core/xsl/system/sy_userprofile.xsl".intern();
		
	public final static String STYLESHEET_SY_DISPLAY_USER_ENTITY_URL ="/core/xsl/system/sy_user_entity.xsl".intern();
		
	public final static String STYLESHEET_SY_USER_ENTITY_URL ="/core/xsl/system/sy_user_entity.xsl".intern();
		
	public final static String STYLESHEET_SY_ALERT_URL ="/core/xsl/system/sy_alert.xsl".intern();
		
	public final static String STYLESHEET_SY_ALERT_CALENDAR_URL ="/core/xsl/system/sy_alert_calendar.xsl".intern();
		
	public final static String STYLESHEET_SY_ALERT_BALANCE_URL ="/core/xsl/system/sy_alert_balance.xsl".intern();
		
	public final static String STYLESHEET_SY_ALERT_TRANSACTION_URL ="/core/xsl/system/sy_alert_transaction.xsl".intern();
		
	public final static String STYLESHEET_SY_SYSTEM_PARAMETERS_URL ="/core/xsl/system/sy_system_parameter.xsl".intern();
		
	public final static String STYLESHEET_SY_GENERIC_PARAMETERS_LIST_URL ="/core/xsl/system/sy_generic_parameters_list.xsl".intern();
		
	public final static String STYLESHEET_SY_GENERIC_PARAMETER_URL ="/core/xsl/system/sy_generic_parameter.xsl".intern();
		
	public final static String STYLESHEET_GENERIC_PARAMETER_SAVE_URL ="/core/xsl/products/parameter_save.xsl".intern();
		
	public final static String STYLESHEET_LICENSE_SAVE_URL ="/core/xsl/system/license.xsl".intern();
		
	public final static String STYLESHEET_SAVE_CORRESPONDENT_BANK_EC_URL ="/core/xsl/system/correspondent_bank_ec.xsl".intern();
		
	public final static String STYLESHEET_SY_AM_ACCOUNT_URL ="/core/xsl/system/sy_am_account.xsl".intern();
		
	public final static String STYLESHEET_SY_AM_ACCOUNT_SAVE_URL ="/core/xsl/products/sy_am_account_save.xsl".intern();
		
	public final static String STYLESHEET_SY_AM_BILL_URL ="/core/xsl/system/sy_am_bill.xsl".intern();
		
	public final static String STYLESHEET_SY_AM_UPLOAD_ACCOUNT_URL ="/core/xsl/system/sy_am_upload_account.xsl".intern();
		
	public final static String STYLESHEET_SY_AM_BILL_SAVE_URL ="/core/xsl/products/sy_am_bill_save.xsl".intern();
		
	public final static String STYLESHEET_MT940 ="/core/xsl/swift/MT940.xsl".intern();
		
	public final static String STYLESHEET_GROUPING_RULE_SAVE_URL ="/openaccount/xsl/grouping_rule.xsl".intern();
		
	public final static String STYLESHEET_STATICBENEFICIARY_SAVE_URL ="/core/xsl/products/beneficiary_save.xsl".intern();
		
	public final static String STYLESHEET_STATICBANK_SAVE_URL ="/core/xsl/products/bank_save.xsl".intern();
		
	public final static String STYLESHEET_STATICACCOUNT_SAVE_URL ="/core/xsl/products/account_save.xsl".intern();
		
	public final static String STYLESHEET_STATIC_ACCOUNT_SAVE_URL ="/core/xsl/products/static_account_save.xsl".intern();
		
	public final static String STYLESHEET_STATICACCOUNT_TNX_SAVE_URL ="/core/xsl/products/account_tnx_save.xsl".intern();
		
	public final static String STYLESHEET_STATIC_ENTITY_SAVE_URL ="/core/xsl/products/entity_save.xsl".intern();
		
	public final static String STYLESHEET_ADDONS_ENTITY_SAVE_URL ="/core/xsl/entity_addons_save.xsl".intern();
		
	public final static String STYLESHEET_STATICPHRASE_SAVE_URL ="/core/xsl/products/phrase_save.xsl".intern();
		
	public final static String STYLESHEET_STATICPRODUCT_SAVE_URL ="/core/xsl/products/product_save.xsl".intern();
		
	public final static String STYLESHEET_RATE_SAVE_URL ="/core/xsl/products/rate_save.xsl".intern();
		
	public final static String STYLESHEET_RATE_SAVE_TNX_URL ="/core/xsl/products/rate_tnx_save.xsl".intern();
		
	public final static String STYLESHEET_SY_JURISDICTION_URL ="core/xsl/system/sy_matrix.xsl".intern();
		
	public final static String STYLESHEET_MATRIX_SAVE_URL ="core/xsl/products/matrix.xsl".intern();
		
	public final static String STYLESHEET_USER_SAVE_URL ="/core/xsl/products/user.xsl".intern();
		
	public final static String STYLESHEET_COMPANY_SAVE_URL ="/core/xsl/products/company.xsl".intern();
		
	public final static String STYLESHEET_EXTENDED_COMPANY_SAVE_URL ="/core/xsl/products/extended_company.xsl".intern();
		
	public final static String STYLESHEET_EXTENDED_USER_SAVE_URL = "/client/xsl/products/extended_user.xsl".intern();
		
	public final static String STYLESHEET_CUSTOMER_REFERENCES_SAVE_URL ="/core/xsl/customer_references.xsl".intern();
		
	public final static String STYLESHEET_ALERT_SAVE_URL ="/core/xsl/products/alert.xsl".intern();
		
	public final static String STYLESHEET_ALERT_CASH_SAVE_URL ="/core/xsl/products/alert_cash.xsl".intern();
		
	public final static String STYLESHEET_BOREFERENCE_SAVE_URL ="/core/xsl/borefid.xsl".intern();
		
	public final static String STYLESHEET_USER_ENTITY_SAVE_URL ="/core/xsl/user_entity.xsl".intern();
		
	public final static String STYLESHEET_CREATE_REPORT_URL ="/core/xsl/report/report.xsl".intern();
		
	public final static String STYLESHEET_SAVE_REPORT_URL ="/core/xsl/report/report_save.xsl".intern();
		
	public final static String STYLESHEET_SAVE_REPORT_ENTITIES_URL ="/core/xsl/report/report_entity.xsl".intern();
		
	public final static String STYLESHEET_SCHEDULE_REPORT_URL ="/core/xsl/system/sy_schedule_report.xsl".intern();
		
	public final static String STYLESHEET_SAVE_SCHEDULE_REPORT_URL ="/core/xsl/schedule_report_save.xsl".intern();
		
	public final static String STYLESHEET_PARAMETER_REPORT_URL ="/core/xsl/report/report_parameters.xsl".intern();
		
	public final static String STYLESHEET_SAVE_PARAMETER_REPORT_URL ="/core/xsl/report/report_parameters_save.xsl".intern();
		
	public final static String STYLESHEET_SAVE_NEWS_LAYOUT ="/core/xsl/admin/news/default_layout_save.xsl".intern();
		
	public final static String STYLESHEET_SAVE_EVENT_OCCURRENCE ="/core/xsl/event_occurrence_save.xsl".intern();
		
	public final static String STYLESHEET_CAL_EVENT_URL ="/core/xsl/cal_event.xsl".intern();
		
	public final static String JAVASCRIPT_CAL_EVENT_URL ="/content/OLD/javascript/cal_event.js".intern();
		
	public final static String STYLESHEET_EVENT_SAVE_URL ="/core/xsl/products/event_save.xsl".intern();
		
	public final static String STYLESHEET_DELETE_NEWS ="/core/xsl/news-delete.xsl".intern();
		
	public final static String STYLESHEET_MAINTAIN_NEWS ="/core/xsl/news-maintain.xsl".intern();
		
	public final static String STYLESHEET_MAINTAIN_CHANNEL ="/core/xsl/channel-maintain.xsl".intern();
		
	public final static String STYLESHEET_MAINTAIN_LIST_OF_NEWS ="/core/xsl/news-list-maintain.xsl".intern();
		
	public final static String STYLESHEET_DISPLAY_NEWS ="/core/xsl/news-full.xsl".intern();
		
	public final static String STYLESHEET_DM_CREATE_URL ="/core/xsl/trade_create_dm.xsl".intern();
		
	public final static String STYLESHEET_DM_CREATE_REP_URL ="/core/xsl/trade_create_rep_dm.xsl".intern();
		
	public final static String STYLESHEET_DM_OPEN_URL ="/core/xsl/trade_open_dm.xsl".intern();
		
	public final static String STYLESHEET_DM_VERSION_URL ="/core/xsl/trade_version_dm.xsl".intern();
		
	public final static String STYLESHEET_DM_AMEND_URL ="/core/xsl/trade_amend_dm.xsl".intern();
		
	public final static String STYLESHEET_DM_AMEND_REP_URL ="/core/xsl/trade_amend_rep_dm.xsl".intern();
		
	public final static String STYLESHEET_DM_SAVE_URL ="/core/xsl/products/dm.xsl".intern();
		
	public final static String STYLESHEET_DM_FROM_EL_URL ="/core/xsl/products/dm_el_to_template.xsl".intern();
		
	public final static String STYLESHEET_DM_PURGE_DOCUMENTS_URL ="/core/xsl/trade_purge_dm.xsl".intern();
		
	public final static String STYLESHEET_DM_SUMMARY_URL ="/core/xsl/trade_summary_rep_dm.xsl".intern();
		
	public final static String STYLESHEET_DM_DETAILS_URL ="/core/xsl/trade_details_rep_dm.xsl".intern();
		
	public final static String ADVICE_LOGO_URL ="/content/images/advices".intern();
		
	public final static String STYLESHEET_DM_PARAMETERS_URL ="/trade/xsl/dm_parameters.xsl".intern();
		
	public final static String STYLESHEET_DM_PARAMETERS_SAVE_URL ="/trade/xsl/dm_parameters_save.xsl".intern();
		
	public final static String STYLESHEET_DM_ADVICE_LIST ="/trade/xsl/trade_advice_rep_dm.xsl".intern();
		
	public final static String STYLESHEET_DM_PRESENT_URL ="/trade/xsl/trade_present_dm.xsl".intern();
		
	public final static String STYLESHEET_DM_PRESENT_REP_URL ="/trade/xsl/trade_present_rep_dm.xsl".intern();
		
	public final static String STYLESHEET_DM_MESSAGE_REP_URL ="/trade/xsl/trade_message_rep_dm.xsl".intern();
		
	public final static String STYLESHEET_DM_BANK_URL ="/trade/xsl/bank_dm_reporting.xsl".intern();
		
	public final static String STYLESHEET_DM_BANK_REP_URL ="/trade/xsl/bank_dm_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_HELP_HELPSECTION_URL ="/core/xsl/help_section.xsl".intern();
		
	public final static String STYLESHEET_HELP_HELPSECTION_SAVE_URL ="/core/xsl/products/help_section_save.xsl".intern();
		
	public final static String STYLESHEET_HELP_HELPTEXT_URL ="/core/xsl/help_text.xsl".intern();
		
	public final static String STYLESHEET_HELP_HELPTEXT_SAVE_URL ="/core/xsl/products/help_text_save.xsl".intern();
		
	public final static String STYLESHEET_HELP_HELPSECTION_REP_URL ="/core/xsl/help_section_rep.xsl".intern();
		
	public final static String STYLESHEET_HELP_SERACH_RESULTS ="/core/xsl/help_search_results.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_NOTIFICATIONS ="/collaboration/xsl/counterparty_notifications.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_LC_LCSTD_CREATE_REP_URL ="/trade/xsl/trade_create_lc.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_LC_LCSTD_AMEND_REP_URL ="/trade/xsl/trade_amend_lc.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_LC_LCSTD_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_SI_CREATE_REP_URL ="/trade/xsl/trade_create_si.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_SI_AMEND_REP_URL ="/trade/xsl/trade_amend_si.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_SI_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_EL_MESSAGE_REP_URL ="/trade/xsl/bank_el_reporting.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_SR_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_BG_CREATE_REP_URL ="/trade/xsl/trade_create_bg.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_BG_AMEND_REP_URL ="/trade/xsl/trade_amend_bg.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_BG_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_SG_CREATE_REP_URL ="/trade/xsl/trade_create_sg.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_SG_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_EC_CREATE_REP_URL ="/trade/xsl/trade_create_ec.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_EC_AMEND_REP_URL ="/trade/xsl/trade_amend_ec.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_EC_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_IC_MESSAGE_REP_URL ="/trade/xsl/trade_create_ic.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_TF_CREATE_REP_URL ="/trade/xsl/trade_create_tf.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_TF_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_FT_INT_CREATE_REP_URL ="/cash/xsl/cash_create_ft_int.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_FT_TPT_CREATE_REP_URL ="/cash/xsl/cash_create_ft_tpt.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_FT_DOM_CREATE_REP_URL ="/cash/xsl/cash_create_ft_dom.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_FT_MT103_CREATE_REP_URL ="/cash/xsl/cash_create_remittance.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_FT_PICO_CREATE_REP_URL ="/cash/xsl/cash_create_pi.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_FT_PIDD_CREATE_REP_URL ="/cash/xsl/cash_create_pi.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_FT_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_PO_CREATE_REP_URL ="/openaccount/xsl/trade_create_po.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_PO_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_SO_CREATE_REP_URL ="/trade/xsl/trade_create_so.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_SO_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_IN_CREATE_REP_URL ="/openaccount/xsl/trade_create_in.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_IN_MESSAGE_REP_URL ="/openaccount/xsl/trade_message_baseline.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_IP_CREATE_REP_URL ="/openaccount/xsl/trade_create_ip.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_IP_MESSAGE_REP_URL ="/openaccount/xsl/trade_message_baseline.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_TD_CREATE_REP_URL ="/cash/xsl/cash_create_td_cstd.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_TD_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_BK_CREATE_REP_URL ="/core/xsl/core_create_bk.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_BK_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_LI_CREATE_REP_URL ="/trade/xsl/trade_create_li.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_LI_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_LC_LCSTD_CREATE_REP_URL ="/trade/xsl/trade_create_lc.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_LC_LCSTD_AMEND_REP_URL ="/trade/xsl/trade_amend_lc.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_LC_LCSTD_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_SI_CREATE_REP_URL ="/trade/xsl/trade_create_si.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_SI_AMEND_REP_URL ="/trade/xsl/trade_amend_si.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_SI_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_EL_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_SR_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_BG_CREATE_REP_URL ="/trade/xsl/trade_create_bg.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_BG_AMEND_REP_URL ="/trade/xsl/trade_amend_bg.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_BG_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_SG_CREATE_REP_URL ="/trade/xsl/trade_create_sg.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_SG_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_EC_CREATE_REP_URL ="/trade/xsl/trade_create_ec.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_EC_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_IC_MESSAGE_REP_URL ="/trade/xsl/trade_create_ic.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_TF_CREATE_REP_URL ="/trade/xsl/trade_create_tf.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_TF_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_FT_CREATE_REP_URL ="/trade/xsl/trade_create_ft.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_FT_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_PO_CREATE_REP_URL ="/openaccount/xsl/trade_create_po.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_PO_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_SO_CREATE_REP_URL ="/trade/xsl/trade_create_so.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_SO_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_IN_CREATE_REP_URL ="/openaccount/xsl/trade_create_in.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_IN_MESSAGE_REP_URL ="/core/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_SY_ALERT_URL ="/core/xsl/system/sy_alert.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_ALERT_SAVE_URL ="/core/xsl/system/sy_alert.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_IP_CREATE_REP_URL ="/openaccount/xsl/trade_create_ip.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_IP_MESSAGE_REP_URL ="/openaccount/xsl/trade_message_baseline.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_LI_CREATE_REP_URL ="/trade/xsl/trade_create_li.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_LI_MESSAGE_REP_URL ="/trade/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_COUNTERPARTY_CN_CREATE_REP_URL ="/openaccount/xsl/trade_create_cn.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_CN_CREATE_REP_URL ="/openaccount/xsl/trade_create_cn.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_LS_CREATE_REP_URL ="/trade/xsl/trade_create_ls.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_LS_AMEND_REP_URL ="/trade/xsl/trade_amend_ls.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_LS_MESSAGE_REP_URL ="/trade/xsl/trade_message_ls.xsl".intern();
		
	public final static String STYLESHEET_LICENSE_URL ="/trade/xsl/system/sy_license.xsl".intern();
		
	public final static String JAVASCRIPT_SRC_URL ="/content/js".intern();
		
	public final static String JAVASCRIPT_DEBUG_SRC_URL ="/content/js-src".intern();
		
	public final static String JAVASCRIPT_DOJO_BASE_URL ="/dojo/dojo.js".intern();
		
	public final static String JAVASCRIPT_DOJO_REPORTFORM_LAYER_URL ="/dojo/misys-dojo-report.js".intern();
		
	public final static String JAVASCRIPT_DOJO_FORM_LAYER_URL ="/dojo/misys-dojo-form.js".intern();
		
	public final static String JAVASCRIPT_DOJO_HOME_LAYER_URL ="/dojo/misys-dojo-home.js".intern();
		
	public final static String JAVASCRIPT_DOJO_BASE_LAYER_URL ="/dojo/misys-dojo-base.js".intern();
		
	public final static String JAVASCRIPT_TRADE_DETAILS_URL ="/misys/trade_details.js".intern();
		
	public final static String JAVASCRIPT_STATIC_POPUP_URL ="/misys/com_static_data_popup.js".intern();
		
	public final static String JAVASCRIPT_STATIC_POPUP_FT_URL ="/com_static_data_popup_ft.js".intern();
		
	public final static String JAVASCRIPT_TOOLTIP_URL ="/misys/tooltip.js".intern();
		
	public final static String JAVASCRIPT_COM_ERROR_URL ="/content/OLD/javascript/com_error.js".intern();
		
	public final static String JAVASCRIPT_COM_DATE_URL ="/content/OLD/javascript/com_date.js".intern();
		
	public final static String JAVASCRIPT_NEWS_URL ="/content/OLD/javascript/news.js".intern();
		
	public final static String JAVASCRIPT_RICHTEXT_URL ="/content/OLD/javascript/richtext.js".intern();
		
	public final static String JAVASCRIPT_TRADE_CREATE_PO_URL ="/content/OLD/javascript/openaccount/trade_create_po.js".intern();
		
	public final static String JAVASCRIPT_TRADE_PO_FOLDER_URL ="/content/OLD/javascript/openaccount/trade_po_folder.js".intern();
		
	public final static String JAVASCRIPT_TRADE_UPLODAD_PO_URL ="/content/OLD/javascript/openaccount/trade_upload_po.js".intern();
		
	public final static String JAVASCRIPT_COM_FUNCTIONS_PO_URL ="/content/OLD/javascript/openaccount/com_functions_po.js".intern();
		
	public final static String TEMPLATE_PSML_FOLDER_URL ="/content/psml/templates".intern();
		
	public final static String LOCALIZATION_FOLDER_URL ="/config/localization/localization.txt".intern();
		
	public final static String BUSINESS_CODE_URL ="core/business_codes.xml".intern();
		
	public final static String DEFAULT_RSS_URL ="/core/xsl/rss2.xsl".intern();
		
	public final static String STYLESHEET_CSV ="/core/xsl/csv.xsl".intern();
		
	public final static String STYLESHEET_XLS ="/core/xsl/xls.xsl".intern();
		
	public final static String GUARANTEES_SPECIMEN_FOLDER ="/content/specimen/bg".intern();
		
	public final static String SBLC_GUARANTEES_SPECIMEN_FOLDER ="/content/specimen/si".intern();
		
	public final static String IMAGES_FOLDER_URL ="/content/images".intern();
		
	public final static String FO_FOLDER_URL ="/trade/xsl/fo".intern();
		
	public final static String GUARANTEES_FO_FOLDER ="/trade/xsl/fo/bg".intern();
		
	public final static String SBLC_GUARANTEES_FO_FOLDER ="/trade/xsl/fo/si".intern();
		
	public final static String TEXT_FOLDER_URL ="/trade/xsl/text".intern();
		
	public final static String NEWS_FOLDER_URL ="/core/xsl/news".intern();
		
	public final static String SUMMIT_FO_FOLDER_URL ="/summit/xsl/fo".intern();
		
	public final static String STYLESHEET_DM_FOLDER_URL ="/content/xsl/dm".intern();
		
	public final static String STYLESHEET_DM_FO_FOLDER_URL ="/content/xsl/dm".intern();
		
	public final static String STYLESHEET_DM_FO_URL ="/content/xsl/dm".intern();
		
	public final static String STYLESHEET_DM_DTD_FOLDER_URL ="/content/xsl/dm/dtd".intern();
		
	public final static String STYLESHEET_LIST_FRAMEWORK_FO_URL ="/trade/xsl/fo/print_fo.xsl".intern();
		
	public final static String STYLESHEET_MT700 ="/core/xsl/swift/MT700.xsl".intern();
		
	public final static String STYLESHEET_MT720 ="/core/xsl/swift/MT720.xsl".intern();
		
	public final static String SWIFT_MESSAGING_CONFIG_URL ="/swift/mt798/mt798-config.xml".intern();
		
	public final static String SWIFT_FILEACT_DATAPDU_URL ="/swift/fileact/xsl/DataPDU.xsl".intern();
		
	public final static String TSU_MESSAGE_TSMT_019_URL ="/tsu/text/tsmt.019.001.01.xml".intern();
		
	public final static String TSU_MESSAGE_TSMT_003_URL ="/tsu/text/tsmt.003.001.02.xml".intern();
		
	public final static String TSU_MESSAGE_TSMT_004_URL ="/tsu/texttsmt.004.001.01.xml".intern();
		
	public final static String TSU_MESSAGE_TSMT_038_URL ="/tsu/text/tsmt.038.001.02.xml".intern();
		
	public final static String TSU_MESSAGE_TSMT_042_URL ="/tsu/text/tsmt.042.001.02.xml".intern();
		
	public final static String TSU_V2_MESSAGE_TSMT_019_URL ="/tsu/text/v2/tsmt.019.001.03.xml".intern();
		
	public final static String TSU_V2_MESSAGE_TSMT_003_URL ="/tsu/text/v2/tsmt.003.001.03.xml".intern();
		
	public final static String TSU_V2_MESSAGE_TSMT_004_URL ="/tsu/text/v2/tsmt.004.001.02.xml".intern();
		
	public final static String TSU_V2_MESSAGE_TSMT_038_URL ="/tsu/text/v2/tsmt.038.001.03.xml".intern();
		
	public final static String TSU_V2_MESSAGE_TSMT_042_URL ="/tsu/text/v2/tsmt.042.001.03.xml".intern();
		
	public final static String STYLESHEET_TSU_TSMT_001_URL ="/tsu/xsl/$tsmt.001.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_001_REP_URL ="/tsu/xsl/$tsmt.001.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_002_URL ="/tsu/xsl/$tsmt.002.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_002_REP_URL ="/tsu/xsl/$tsmt.002.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_003_URL ="/tsu/xsl/$tsmt.003.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_003_REP_URL ="/tsu/xsl/$tsmt.003.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_004_URL ="/tsu/xsl/$tsmt.004.001.01_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_004_REP_URL ="/tsu/xsl/$tsmt.004.001.01_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_005_URL ="/tsu/xsl/$tsmt.005.001.01_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_005_REP_URL ="/tsu/xsl/$tsmt.005.001.01_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_006_URL ="/tsu/xsl/$tsmt.006.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_006_REP_URL ="/tsu/xsl/$tsmt.006.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_007_URL ="/tsu/xsl/$tsmt.007.001.01_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_007_REP_URL ="/tsu/xsl/$tsmt.007.001.01_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_008_URL ="/tsu/xsl/$tsmt.008.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_008_REP_URL ="/tsu/xsl/$tsmt.008.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_009_URL ="/tsu/xsl/$tsmt.009.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_009_REP_URL ="/tsu/xsl/$tsmt.009.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_010_URL ="/tsu/xsl/$tsmt.010.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_010_REP_URL ="/tsu/xsl/$tsmt.010.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_011_URL ="/tsu/xsl/$tsmt.011.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_011_REP_URL ="/tsu/xsl/$tsmt.011.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_012_URL ="/tsu/xsl/$tsmt.012.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_012_REP_URL ="/tsu/xsl/$tsmt.012.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_013_URL ="/tsu/xsl/$tsmt.013.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_013_REP_URL ="/tsu/xsl/$tsmt.013.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_014_URL ="/tsu/xsl/$tsmt.014.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_014_REP_URL ="/tsu/xsl/$tsmt.014.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_015_URL ="/tsu/xsl/$tsmt.015.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_015_REP_URL ="/tsu/xsl/$tsmt.015.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_016_URL ="/tsu/xsl/$tsmt.016.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_016_REP_URL ="/tsu/xsl/$tsmt.016.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_017_URL ="/tsu/xsl/$tsmt.017.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_017_REP_URL ="/tsu/xsl/$tsmt.017.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_018_URL ="/tsu/xsl/$tsmt.018.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_018_REP_URL ="/tsu/xsl/$tsmt.018.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_019_URL ="/tsu/xsl/$tsmt.019.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_019_REP_URL ="/tsu/xsl/$tsmt.019.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_020_URL ="/tsu/xsl/$tsmt.020.001.01_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_020_REP_URL ="/tsu/xsl/$tsmt.020.001.01_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_021_URL ="/tsu/xsl/$tsmt.021.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_021_REP_URL ="/tsu/xsl/$tsmt.021.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_022_URL ="/tsu/xsl/$tsmt.022.001.01_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_022_REP_URL ="/tsu/xsl/$tsmt.022.001.01_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_023_URL ="/tsu/xsl/$tsmt.023.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_023_REP_URL ="/tsu/xsl/$tsmt.023.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_024_URL ="/tsu/xsl/$tsmt.024.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_024_REP_URL ="/tsu/xsl/$tsmt.024.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_025_URL ="/tsu/xsl/$tsmt.025.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_025_REP_URL ="/tsu/xsl/$tsmt.025.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_026_URL ="/tsu/xsl/$tsmt.026.001.01_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_026_REP_URL ="/tsu/xsl/$tsmt.026.001.01_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_027_URL ="/tsu/xsl/$tsmt.027.001.01_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_027_REP_URL ="/tsu/xsl/$tsmt.027.001.01_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_028_URL ="/tsu/xsl/$tsmt.028.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_028_REP_URL ="/tsu/xsl/$tsmt.028.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_029_URL ="/tsu/xsl/$tsmt.029.001.01_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_029_REP_URL ="/tsu/xsl/$tsmt.029.001.01_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_030_URL ="/tsu/xsl/$tsmt.030.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_030_REP_URL ="/tsu/xsl/$tsmt.030.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_031_URL ="/tsu/xsl/$tsmt.031.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_031_REP_URL ="/tsu/xsl/$tsmt.031.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_032_URL ="/tsu/xsl/$tsmt.032.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_032_REP_URL ="/tsu/xsl/$tsmt.032.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_033_URL ="/tsu/xsl/$tsmt.033.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_033_REP_URL ="/tsu/xsl/$tsmt.033.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_034_URL ="/tsu/xsl/$tsmt.034.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_034_REP_URL ="/tsu/xsl/$tsmt.034.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_035_URL ="/tsu/xsl/$tsmt.035.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_035_REP_URL ="/tsu/xsl/$tsmt.035.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_036_URL ="/tsu/xsl/$tsmt.036.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_036_REP_URL ="/tsu/xsl/$tsmt.036.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_037_URL ="/tsu/xsl/$tsmt.037.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_037_REP_URL ="/tsu/xsl/$tsmt.037.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_038_URL ="/tsu/xsl/$tsmt.038.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_038_REP_URL ="/tsu/xsl/$tsmt.038.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_039_URL ="/tsu/xsl/$tsmt.039.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_039_REP_URL ="/tsu/xsl/$tsmt.039.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_040_URL ="/tsu/xsl/$tsmt.040.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_040_REP_URL ="/tsu/xsl/$tsmt.040.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_041_URL ="/tsu/xsl/$tsmt.041.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_041_REP_URL ="/tsu/xsl/$tsmt.041.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_042_URL ="/tsu/xsl/$tsmt.042.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_042_REP_URL ="/tsu/xsl/$tsmt.042.001.02_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_TSU_BASELINE_REP_URL ="/tsu/xsl/baseline_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_BASELINE_TO_TSU_TSMT_009_URL ="/tsu/xsl/baseline_to_tsmt.009.001.02.xsl".intern();
		
	public final static String STYLESHEET_BASELINE_TO_TSU_TSMT_012_URL ="/tsu/xsl/baseline_to_tsmt.012.001.02.xsl".intern();
		
	public final static String STYLESHEET_BASELINE_TO_TSU_TSMT_014_URL ="/tsu/xsl/baseline_to_tsmt.014.001.02.xsl".intern();
		
	public final static String STYLESHEET_BASELINE_TO_TSU_TSMT_019_URL ="/tsu/xsl/baseline_to_tsmt.019.001.02.xsl".intern();
		
	public final static String STYLESHEET_BASELINE_TO_TSU_TSMT_039_URL ="/tsu/xsl/baseline_to_tsmt.039.001.02.xsl".intern();
		
	public final static String STYLESHEET_BASELINE_TO_TSU_TSMT_026_URL ="/tsu/xsl/baseline_to_tsmt.026.001.01.xsl".intern();
		
	public final static String STYLESHEET_BASELINE_TO_TSU_TSMT_035_URL ="/tsu/xsl/baseline_to_tsmt.035.001.02.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_018_TO_TSU_TSMT_005_URL ="/tsu/xsl/tsmt.018.001.02_to_tsmt.005.001.01.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_018_TO_TSU_TSMT_007_URL ="/tsu/xsl/tsmt.018.001.02_to_tsmt.007.001.01.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_015_TO_TSU_TSMT_005_URL ="/tsu/xsl/tsmt.015.001.02_to_tsmt.005.001.01.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_015_TO_TSU_TSMT_007_URL ="/tsu/xsl/tsmt.015.001.02_to_tsmt.007.001.01.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_013_TO_TSU_TSMT_020_URL ="/tsu/xsl/tsmt.013.001.02_to_tsmt.020.001.01.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_013_TO_TSU_TSMT_022_URL ="/tsu/xsl/tsmt.013.001.02_to_tsmt.022.001.01.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_028_TO_TSU_TSMT_027_URL ="/tsu/xsl/tsmt.028.001.02_to_tsmt.027.001.01.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_028_TO_TSU_TSMT_029_URL ="/tsu/xsl/tsmt.028.001.02_to_tsmt.029.001.01.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_032_TO_TSU_TSMT_031_URL ="/tsu/xsl/tsmt.032.001.02_to_tsmt.031.001.02.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_032_TO_TSU_TSMT_033_URL ="/tsu/xsl/tsmt.032.001.02_to_tsmt.033.001.02.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_036_TO_TSU_TSMT_031_URL ="/tsu/xsl/tsmt.036.001.02_to_tsmt.031.001.02.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_036_TO_TSU_TSMT_033_URL ="/tsu/xsl/tsmt.036.001.02_to_tsmt.033.001.02.xsl".intern();
		
	public final static String STYLESHEET_TSU_TSMT_018_TO_TSU_TSMT_012_URL ="/tsu/xsl/tsmt.018.001.02_to_tsmt.012.001.02.xsl".intern();
		
	public final static String STYLESHEET_TSMT_019_TO_BASELINE_URL ="/tsu/xsl/tsmt.019.001.02_to_baseline.xsl".intern();
		
	public final static String STYLESHEET_TSU_ADD_PREFIX_URL ="/tsu/xsl/tsu_add_prefix.xsl".intern();
		
	public final static String JAVASCRIPT_TSU_FUNCTIONS ="/content/OLD/javascript/tsu/tsu.js".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_001_URL ="/tsu/xsl/v2/tsmt.001.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_002_URL ="/tsu/xsl/v2/tsmt.002.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_003_URL ="/tsu/xsl/v2/tsmt.003.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_004_URL ="/tsu/xsl/v2/tsmt.004.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_005_URL ="/tsu/xsl/v2/tsmt.005.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_006_URL ="/tsu/xsl/v2/tsmt.006.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_007_URL ="/tsu/xsl/v2/tsmt.007.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_008_URL ="/tsu/xsl/v2/tsmt.008.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_009_URL ="/tsu/xsl/v2/tsmt.009.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_010_URL ="/tsu/xsl/v2/tsmt.010.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_011_URL ="/tsu/xsl/v2/tsmt.011.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_012_URL ="/tsu/xsl/v2/tsmt.012.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_013_URL ="/tsu/xsl/v2/tsmt.013.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_014_URL ="/tsu/xsl/v2/tsmt.014.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_015_URL ="/tsu/xsl/v2/tsmt.015.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_016_URL ="/tsu/xsl/v2/tsmt.016.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_017_URL ="/tsu/xsl/v2/tsmt.017.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_018_URL ="/tsu/xsl/v2/tsmt.018.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_019_URL ="/tsu/xsl/v2/tsmt.019.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_020_URL ="/tsu/xsl/v2/tsmt.020.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_021_URL ="/tsu/xsl/v2/tsmt.021.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_022_URL ="/tsu/xsl/v2/tsmt.022.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_023_URL ="/tsu/xsl/v2/tsmt.023.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_024_URL ="/tsu/xsl/v2/tsmt.024.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_025_URL ="/tsu/xsl/v2/tsmt.025.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_026_URL ="/tsu/xsl/v2/tsmt.026.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_027_URL ="/tsu/xsl/v2/tsmt.027.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_028_URL ="/tsu/xsl/v2/tsmt.028.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_029_URL ="/tsu/xsl/v2/tsmt.029.001.02_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_030_URL ="/tsu/xsl/v2/tsmt.030.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_031_URL ="/tsu/xsl/v2/tsmt.031.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_032_URL ="/tsu/xsl/v2/tsmt.032.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_033_URL ="/tsu/xsl/v2/tsmt.033.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_034_URL ="/tsu/xsl/v2/tsmt.034.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_035_URL ="/tsu/xsl/v2/tsmt.035.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_036_URL ="/tsu/xsl/v2/tsmt.036.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_037_URL ="/tsu/xsl/v2/tsmt.037.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_038_URL ="/tsu/xsl/v2/tsmt.038.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_039_URL ="/tsu/xsl/v2/tsmt.039.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_040_URL ="/tsu/xsl/v2/tsmt.040.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_041_URL ="/tsu/xsl/v2/tsmt.041.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_042_URL ="/tsu/xsl/v2/tsmt.042.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_043_URL ="/tsu/xsl/v2/tsmt.043.001.03_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_044_URL ="/tsu/xsl/v2/tsmt.044.001.01_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_045_URL ="/tsu/xsl/v2/tsmt.045.001.01_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_046_URL ="/tsu/xsl/v2/tsmt.046.001.01_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_047_URL ="/tsu/xsl/v2/tsmt.047.001.01_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_048_URL ="/tsu/xsl/v2/tsmt.048.001.01_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_049_URL ="/tsu/xsl/v2/tsmt.049.001.01_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_050_URL ="/tsu/xsl/v2/tsmt.050.001.01_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_051_URL ="/tsu/xsl/v2/tsmt.051.001.01_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_052_URL ="/tsu/xsl/v2/tsmt.052.001.01_xslgui.xsl".intern();
		
	public final static String STYLESHEET_TSU_INITIATE_STATUS_REPORT_URL ="/tsu/xsl/v2/bank_create_tma_status_report_request.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_001_FO_URL ="/tsu/xsl/v2/fo/tsmt.001.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_002_FO_URL ="/tsu/xsl/v2/fo/tsmt.002.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_003_FO_URL ="/tsu/xsl/v2/fo/tsmt.003.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_004_FO_URL ="/tsu/xsl/v2/fo/tsmt.004.001.02_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_005_FO_URL ="/tsu/xsl/v2/fo/tsmt.005.001.02_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_006_FO_URL ="/tsu/xsl/v2/fo/tsmt.006.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_007_FO_URL ="/tsu/xsl/v2/fo/tsmt.007.001.02_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_008_FO_URL ="/tsu/xsl/v2/fo/tsmt.008.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_009_FO_URL ="/tsu/xsl/v2/fo/tsmt.009.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_010_FO_URL ="/tsu/xsl/v2/fo/tsmt.010.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_011_FO_URL ="/tsu/xsl/v2/fo/tsmt.011.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_012_FO_URL ="/tsu/xsl/v2/fo/tsmt.012.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_013_FO_URL ="/tsu/xsl/v2/fo/tsmt.013.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_014_FO_URL ="/tsu/xsl/v2/fo/tsmt.014.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_015_FO_URL ="/tsu/xsl/v2/fo/tsmt.015.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_016_FO_URL ="/tsu/xsl/v2/fo/tsmt.016.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_017_FO_URL ="/tsu/xsl/v2/fo/tsmt.017.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_018_FO_URL ="/tsu/xsl/v2/fo/tsmt.018.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_019_FO_URL ="/tsu/xsl/v2/fo/tsmt.019.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_020_FO_URL ="/tsu/xsl/v2/fo/tsmt.020.001.02_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_021_FO_URL ="/tsu/xsl/v2/fo/tsmt.021.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_022_FO_URL ="/tsu/xsl/v2/fo/tsmt.022.001.02_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_023_FO_URL ="/tsu/xsl/v2/fo/tsmt.023.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_024_FO_URL ="/tsu/xsl/v2/fo/tsmt.024.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_025_FO_URL ="/tsu/xsl/v2/fo/tsmt.025.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_026_FO_URL ="/tsu/xsl/v2/fo/tsmt.026.001.02_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_027_FO_URL ="/tsu/xsl/v2/fo/tsmt.027.001.02_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_028_FO_URL ="/tsu/xsl/v2/fo/tsmt.028.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_029_FO_URL ="/tsu/xsl/v2/fo/tsmt.029.001.02_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_030_FO_URL ="/tsu/xsl/v2/fo/tsmt.030.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_031_FO_URL ="/tsu/xsl/v2/fo/tsmt.031.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_032_FO_URL ="/tsu/xsl/v2/fo/tsmt.032.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_033_FO_URL ="/tsu/xsl/v2/fo/tsmt.033.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_034_FO_URL ="/tsu/xsl/v2/fo/tsmt.034.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_035_FO_URL ="/tsu/xsl/v2/fo/tsmt.035.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_036_FO_URL ="/tsu/xsl/v2/fo/tsmt.036.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_037_FO_URL ="/tsu/xsl/v2/fo/tsmt.037.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_038_FO_URL ="/tsu/xsl/v2/fo/tsmt.038.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_039_FO_URL ="/tsu/xsl/v2/fo/tsmt.039.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_040_FO_URL ="/tsu/xsl/v2/fo/tsmt.040.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_041_FO_URL ="/tsu/xsl/v2/fo/tsmt.041.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_042_FO_URL ="/tsu/xsl/v2/fo/tsmt.042.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_043_FO_URL ="/tsu/xsl/v2/fo/tsmt.043.001.03_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_044_FO_URL ="/tsu/xsl/v2/fo/tsmt.044.001.01_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_045_FO_URL ="/tsu/xsl/v2/fo/tsmt.045.001.01_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_046_FO_URL ="/tsu/xsl/v2/fo/tsmt.046.001.01_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_047_FO_URL ="/tsu/xsl/v2/fo/tsmt.047.001.01_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_048_FO_URL ="/tsu/xsl/v2/fo/tsmt.048.001.01_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_049_FO_URL ="/tsu/xsl/v2/fo/tsmt.049.001.01_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_050_FO_URL ="/tsu/xsl/v2/fo/tsmt.050.001.01_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_051_FO_URL ="/tsu/xsl/v2/fo/tsmt.051.001.01_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_052_FO_URL ="/tsu/xsl/v2/fo/tsmt.052.001.01_xslfo.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_BASELINE_REP_URL ="/tsu/xsl/v2/baseline_xslgui_rep.xsl".intern();
		
	public final static String STYLESHEET_BASELINE_TO_TSU_V2_TSMT_009_URL ="/tsu/xsl/v2/baseline_to_tsmt.009.001.03.xsl".intern();
		
	public final static String STYLESHEET_BASELINE_TO_TSU_V2_TSMT_012_URL ="/tsu/xsl/v2/baseline_to_tsmt.012.001.03.xsl".intern();
		
	public final static String STYLESHEET_BASELINE_TO_TSU_V2_TSMT_014_URL ="/tsu/xsl/v2/baseline_to_tsmt.014.001.03.xsl".intern();
		
	public final static String STYLESHEET_BASELINE_TO_TSU_V2_TSMT_019_URL ="/tsu/xsl/v2/baseline_to_tsmt.019.001.03.xsl".intern();
		
	public final static String STYLESHEET_BASELINE_TO_TSU_V2_TSMT_026_URL ="/tsu/xsl/v2/baseline_to_tsmt.026.001.02.xsl".intern();
		
	public final static String STYLESHEET_BASELINE_TO_TSU_V2_TSMT_035_URL ="/tsu/xsl/v2/baseline_to_tsmt.035.001.03.xsl".intern();
		
	public final static String STYLESHEET_BASELINE_TO_TSU_V2_TSMT_044_URL ="/tsu/xsl/v2/baseline_to_tsmt.044.001.01.xsl".intern();
		
	public final static String STYLESHEET_BASELINE_TO_TSU_V2_TSMT_047_URL ="/tsu/xsl/v2/baseline_to_tsmt.047.001.01.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_018_TO_TSU_V2_TSMT_005_URL ="/tsu/xsl/v2/tsmt.018.001.03_to_tsmt.005.001.02.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_018_TO_TSU_V2_TSMT_007_URL ="/tsu/xsl/v2/tsmt.018.001.03_to_tsmt.007.001.02.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_015_TO_TSU_V2_TSMT_005_URL ="/tsu/xsl/v2/tsmt.015.001.03_to_tsmt.005.001.02.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_015_TO_TSU_V2_TSMT_007_URL ="/tsu/xsl/v2/tsmt.015.001.03_to_tsmt.007.001.02.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_013_TO_TSU_V2_TSMT_020_URL ="/tsu/xsl/v2/tsmt.013.001.03_to_tsmt.020.001.02.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_013_TO_TSU_V2_TSMT_022_URL ="/tsu/xsl/v2/tsmt.013.001.03_to_tsmt.022.001.02.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_013_TO_TSU_V2_TSMT_049_URL ="/tsu/xsl/v2/tsmt.013.001.03_to_tsmt.049.001.01.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_013_TO_TSU_V2_TSMT_050_URL ="/tsu/xsl/v2/tsmt.013.001.03_to_tsmt.050.001.01.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_028_TO_TSU_V2_TSMT_027_URL ="/tsu/xsl/v2/tsmt.028.001.03_to_tsmt.027.001.02.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_028_TO_TSU_V2_TSMT_029_URL ="/tsu/xsl/v2/tsmt.028.001.03_to_tsmt.029.001.02.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_032_TO_TSU_V2_TSMT_031_URL ="/tsu/xsl/v2/tsmt.032.001.03_to_tsmt.031.001.03.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_032_TO_TSU_V2_TSMT_033_URL ="/tsu/xsl/v2/tsmt.032.001.03_to_tsmt.033.001.03.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_036_TO_TSU_V2_TSMT_031_URL ="/tsu/xsl/v2/tsmt.036.001.03_to_tsmt.031.001.03.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_036_TO_TSU_V2_TSMT_033_URL ="/tsu/xsl/v2/tsmt.036.001.03_to_tsmt.033.001.03.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_018_TO_TSU_V2_TSMT_012_URL ="/tsu/xsl/v2/tsmt.018.001.03_to_tsmt.012.001.03.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_018_TO_TSU_V2_TSMT_049_URL ="/tsu/xsl/v2/tsmt.018.001.03_to_tsmt.049.001.01.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_018_TO_TSU_V2_TSMT_050_URL ="/tsu/xsl/v2/tsmt.018.001.03_to_tsmt.050.001.01.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_021_TO_TSU_V2_TSMT_049_URL ="/tsu/xsl/v2/tsmt.021.001.03_to_tsmt.049.001.01.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_021_TO_TSU_V2_TSMT_050_URL ="/tsu/xsl/v2/tsmt.021.001.03_to_tsmt.050.001.01.xsl".intern();
		
	public final static String STYLESHEET_TSU_V2_TSMT_019_TO_BASELINE_URL ="/tsu/xsl/v2/tsmt.019.001.03_to_baseline.xsl".intern();
		
	public final static String STYLESHEET_PURCHASE_ORDER_TO_INITIAL_BASELINE_SUBMISSION ="/config/swift/PO_Initiation_2_InitialBaseLine.xsl".intern();
		
	public final static String JAVASCRIPT_TIMER_URL ="/content/OLD/javascript/timer.js".intern();
		
	public final static String STYLESHEET_SY_DYNAMIC_PHRASE_URL ="/core/xsl/system/sy_dynamic_phrase.xsl".intern();
		
	public final static String STYLESHEET_SY_DYNAMIC_PHRASE_REP_URL ="/core/xsl/system/sy_dynamic_phrase_rep.xsl".intern();
		
	public final static String STYLESHEET_DYNAMIC_PHRASE_SAVE_URL ="/core/xsl/dynamic_phrase_save.xsl".intern();
		
	public final static String XCL_LOCATION ="/config/xcl".intern();
		
	public final static String COMPASS_CONFIG_URL ="/core/compass/compass.cfg.xml".intern();
		
	public final static String STYLESHEET_HELPDESK_CRM_URL ="/core/xsl/helpdesk_crm.xsl".intern();
		
	public final static String STYLESHEET_AWB_TRACKING_URL ="/trade/xsl/trade_awb_tracking.xsl".intern();
		
	public final static String STYLESHEET_FA_CREATE_URL ="/trade/xsl/trade_create_fa.xsl".intern();
		
	public final static String STYLESHEET_FA_DETAILS_URL ="/trade/xsl/trade_create_fa.xsl".intern();
		
	public final static String STYLESHEET_FA_SAVE_URL ="/trade/xsl/products/fa.xsl".intern();
		
	public final static String STYLESHEET_FA_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_FA_FO_URL ="/trade/xsl/fo/fa_fo.xsl".intern();
		
	public final static String STYLESHEET_FA_AMEND_URL ="/trade/xsl/trade_amend_fa.xsl".intern();
		
	public final static String STYLESHEET_FA_MESSAGE_URL ="/core/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_FA_BANK_URL ="/trade/xsl/admin_bank_fa_reporting.xsl".intern();
		
	public final static String STYLESHEET_FA_BANK_DETAILS_URL ="/trade/xsl/bank_fa_reporting.xsl".intern();
		
	public final static String STYLESHEET_FA_FACTOR_PRO_URL ="/core/xsl/factorpro.xsl".intern();
		
	public final static String STYLESHEET_FACTOR_PRO_AVAIL_INFO ="/trade/xsl/trade_factor_pro_client_info.xsl".intern();
		
	public final static String STYLESHEET_FACTOR_PRO_DEBTOR_PAYMENT ="/trade/xsl/trade_debtor_payment_details.xsl".intern();
		
	public final static String STYLESHEET_FACTOR_PRO_INVOICE_DETAILS ="/trade/xsl/trade_invoice_enquiry_details.xsl".intern();
		
	public final static String STYLESHEET_FACTOR_PRO_OVERDUE_DETAILS ="/trade/xsl/trade_overdue_analysis_details.xsl".intern();
		
	public final static String STYLESHEET_FACTOR_PRO_TRANSACTION_DETAILS ="/trade/xsl/trade_transaction_details.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_FA_CREATE_REP_URL ="/trade/xsl/trade_create_fa.xsl".intern();
		
	public final static String STYLESHEET_SY_SUBSCRIPTION_PACKAGE_URL ="/core/xsl/system/sy_subscription_package.xsl".intern();
		
	public final static String STYLESHEET_SUBSCRIPTION_PACKAGE_SAVE_URL ="/core/xsl/products/subscription_package_save.xsl".intern();
		
	public final static String STYLESHEET_SY_COMPANY_MC_URL ="/core/xsl/system/sy_company_mc.xsl".intern();
		
	public final static String STYLESHEET_SY_ROLEPERMISSION_MC_URL ="/core/xsl/system/sy_roleperm_mc.xsl".intern();
		
	public final static String STYLESHEET_SY_ROLE_DESCRIPTION_MC_URL ="/core/xsl/system/sy_roledesc_mc.xsl".intern();
		
	public final static String STYLESHEET_SY_USER_MC_URL ="/core/xsl/system/sy_user_mc.xsl".intern();
		
	public final static String STYLESHEET_MATRIX_MC_SAVE_URL ="core/xsl/products/matrix_mc.xsl".intern();
		
	public final static String STYLESHEET_SY_JURISDICTION_MC_URL ="core/xsl/system/sy_matrix_mc.xsl".intern();
		
	public final static String STYLESHEET_SY_SUBSCRIPTION_PACKAGE_MC_URL ="/core/xsl/system/sy_subscription_package_mc.xsl".intern();
		
	public final static String STYLESHEET_SY_USER_ACCOUNTS_MC_URL ="/core/xsl/system/sy_user_accounts_mc.xsl".intern();
		
	public final static String STYLESHEET_SY_USER_ACCOUNTS_MC_SAVE_URL ="/core/xsl/system/user_account_save.xsl".intern();
		
	public final static String STYLESHEET_SY_USER_ACCOUNTS_TNX_MC_SAVE_URL ="/core/xsl/system/user_account_tnx_save.xsl".intern();
		
	public final static String STYLESHEET_SY_CUSTOMER_BENEFICIARY_MASTER_MC_URL ="/core/xsl/system/sy_customer_beneficiary_master_mc.xsl".intern();
		
	public final static String STYLESHEET_SY_PASSWORD_MC_URL ="/core/xsl/system/sy_authentication_mc.xsl".intern();
		
	public final static String STYLESHEET_SY_CUSTOMER_PAYEE_URL ="/core/xsl/system/sy_customer_payee.xsl".intern();
		
	public final static String STYLESHEET_SY_MASTER_PAYEE_URL ="/core/xsl/system/sy_master_payee_mc.xsl".intern();
		
	public final static String STYLESHEET_MASTER_PAYEE_SAVE_URL ="/core/xsl/products/master_payee_save.xsl".intern();
		
	public final static String STYLESHEET_MASTER_PAYEE_SAVE_MC_URL ="/core/xsl/products/master_payee_save_mc.xsl".intern();
		
	public final static String STYLESHEET_SY_EXTERNAL_ACCOUNT_URL ="/core/xsl/system/sy_external_account_mc.xsl".intern();
		
	public final static String STYLESHEET_EXTERNAL_ACCOUNT_SAVE_MC_URL ="/core/xsl/products/external_account_save_mc.xsl".intern();
		
	public final static String STYLESHEET_EXTERNAL_ACCOUNT_SAVE_URL ="/core/xsl/products/external_account_save.xsl".intern();
		
	public final static String STYLESHEET_ENTITY_SAVE_MC_URL ="/core/xsl/products/entity_save_mc.xsl".intern();
		
	public final static String STYLESHEET_BENEFICIARY_ADVICE_URL ="/core/xsl/system/sy_beneficiary_advice.xsl".intern();
		
	public final static String STYLESHEET_SAVE_BENEFICIARY_ADVICE_TEMPLATE_URL ="/core/xsl/system/beneficiary_advices_template_save.xsl".intern();
		
	public final static String STYLESHEET_SAVE_CUSTOMERS_BENEFICIARY_ADVICE_URL ="/core/xsl/system/beneficiary_advice_customers.xsl".intern();
		
	public final static String STYLESHEET_IP_FOLDER_URL ="/content/xsl/openaccount/po".intern();
		
	public final static String STYLESHEET_IP_CREATE_URL ="/openaccount/xsl/trade_create_ip.xsl".intern();
		
	public final static String STYLESHEET_IP_CREATE_REP_URL ="/openaccount/xsl/trade_create_ip.xsl".intern();
		
	public final static String STYLESHEET_IP_UPLOAD_URL ="/openaccount/xsl/trade_upload_ip.xsl".intern();
		
	public final static String STYLESHEET_IP_UPLOAD_REP_URL ="/openaccount/xsl/trade_upload_rep_ip.xsl".intern();
		
	public final static String STYLESHEET_IP_AMEND_URL ="/openaccount/xsl/trade_create_ip.xsl".intern();
		
	public final static String STYLESHEET_IP_AMEND_REP_URL ="/openaccount/xsl/trade_create_rep_ip.xsl".intern();
		
	public final static String STYLESHEET_IP_MESSAGE_URL ="/openaccount/xsl/trade_message_baseline.xsl".intern();
		
	public final static String STYLESHEET_IP_MESSAGE_REP_URL ="/core/xsl/trade_message.xsl".intern();
		
	public final static String STYLESHEET_IP_MESSAGE_DISCREPANT_URL ="/openaccount/xsl/trade_message_utilize.xsl".intern();
		
	public final static String STYLESHEET_IP_MESSAGE_DISCREPANT_REP_URL ="/openaccount/xsl/trade_message_utilize_rep.xsl".intern();
		
	public final static String STYLESHEET_IP_MESSAGE_CLOSE_URL ="/openaccount/xsl/trade_message_close.xsl".intern();
		
	public final static String STYLESHEET_IP_MESSAGE_CLOSE_REP_URL ="/openaccount/xsl/trade_message_close_rep.xsl".intern();
		
	public final static String STYLESHEET_IP_DETAILS_URL ="/openaccount/xsl/trade_create_ip.xsl".intern();
		
	public final static String STYLESHEET_IP_BANK_DETAILS_URL ="/openaccount/xsl/bank_ip_reporting.xsl".intern();
		
	public final static String STYLESHEET_IP_DISCREPANT_URL ="/openaccount/xsl/trade_discrepant_rep_ip.xsl".intern();
		
	public final static String STYLESHEET_IP_SAVE_URL ="/openaccount/xsl/products/ip.xsl".intern();
		
	public final static String STYLESHEET_IP_TEMPLATE_SAVE_URL ="/openaccount/xsl/products/ip_template.xsl".intern();
		
	public final static String STYLESHEET_IP_TEMPLATE_MODIFY_URL ="/openaccount/xsl/trade_template_ip.xsl".intern();
		
	public final static String STYLESHEET_IP_BANK_URL ="/openaccount/xsl/bank_ip_reporting.xsl".intern();
		
	public final static String STYLESHEET_IP_BANK_REP_URL ="/openaccount/xsl/bank_ip_reporting_rep.xsl".intern();
		
	public final static String STYLESHEET_IP_FO_URL ="/openaccount/xsl/fo/ip_fo.xsl".intern();
		
	public final static String STYLESHEET_IP_FORM_PARAMETERS_URL ="/openaccount/xsl/sy_maintain_ip_form_parameters.xsl".intern();
		
	public final static String STYLESHEET_IP_SUMMARY_URL ="/openaccount/xsl/trade_ip_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_FT_TINT_CREATE_URL ="/trade/xsl/trade_create_ft_tint.xsl".intern();
		
	public final static String STYLESHEET_FT_TTPT_CREATE_URL ="/trade/xsl/trade_create_ft_ttpt.xsl".intern();
		
	public final static String STYLESHEET_FT_TINT_DETAILS_URL ="/trade/xsl/trade_create_ft_tint.xsl".intern();
		
	public final static String STYLESHEET_FT_TTPT_DETAILS_URL ="/trade/xsl/trade_create_ft_ttpt.xsl".intern();
		
	public final static String STYLESHEET_FT_TINT_BANK_URL ="/trade/xsl/bank_ft_tint_reporting.xsl".intern();
		
	public final static String STYLESHEET_FT_TTPT_BANK_URL ="/trade/xsl/bank_ft_ttpt_reporting.xsl".intern();
		
	public final static String STYLESHEET_FT_TINT_TEMPLATE_MODIFY_URL ="/trade/xsl/trade_template_ft.xsl".intern();
		
	public final static String STYLESHEET_FT_TTPT_TEMPLATE_MODIFY_URL ="/trade/xsl/trade_template_ft.xsl".intern();
		
	public final static String STYLESHEET_FT_TRADE_REPORTING_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_FT_TTPT_CREATE_REP_URL ="/trade/xsl/trade_create_ft_ttpt.xsl".intern();
		
	public final static String STYLESHEET_COLLABORATION_BANK_FT_TINT_CREATE_REP_URL ="/trade/xsl/trade_create_ft_tint.xsl".intern();
		
	public final static String STYLESHEET_FT_COLLECTION_CREATE_URL ="/cash/xsl/cash_create_collection_payer.xsl".intern();
		
	public final static String STYLESHEET_CUSTOMER_ENTITY_TOKEN_SAVE_MC_URL ="/core/xsl/system/customer_entity_token_mc.xsl".intern();
		
	public final static String STYLESHEET_SY_REAUTHENTICATIONCONTENT ="/core/xsl/system/sy_reauthenticationcontent.xsl".intern();
		
	public final static String STYLESHEET_SY_REAUTHENTICATION_DIALOG_LISTSCREEN ="/core/xsl/system/sy_reauthenticationdialog_listscreen.xsl".intern();
		
	public final static String STYLESHEET_SY_TRANSACTION_CONFIRMATION_CONTENT ="/core/xsl/system/sy_transaction_confirmation_content.xsl".intern();
		
	public final static String LISTDEF_LIST_OF_SWIFT_BANKS_DIALOG ="core/listdef/systemfeatures/swiftBanks.xml".intern();
		
	public final static String LISTDEF_LIST_OF_SWIFT_BANKS_NO_COUNTRY_DIALOG ="core/listdef/systemfeatures/swiftBanksNoCountry.xml".intern();
		
	public final static String STYLESHEET_BATCH_INFO_URL ="/core/xsl/common/mc_batch_info.xsl".intern();
		
	public final static String STYLESHEET_REMOTE_AUTHORISATION_URL ="/core/xsl/common/remote_authorisation.xsl".intern();
		
	public final static String STYLESHEET_ACCOUNT_DETAIL_POPUP_URL ="/cash/xsl/popups/cash_account_details.xsl".intern();
		
	public final static String STYLESHEET_ACCOUNT_SUMMARY_FO_URL ="/cash/xsl/fo/account_summary_fo.xsl".intern();
		
	public final static String STYLESHEET_ACCOUNT_STATEMENT_FO_URL ="/cash/xsl/fo/account_statement_fo.xsl".intern();
		
	public final static String STYLESHEET_CONTACT_HELP_DESK ="/trade/xsl/trade_create_contact_helpdesk.xsl".intern();
		
	public final static String STYLESHEET_SE_CTCHP_DETAILS_URL ="/trade/xsl/trade_create_contact_helpdesk.xsl".intern();
		
	public final static String STYLESHEET_MOBI_CIX_OUT_URL ="/cash/xsl/mobi/CIX_OUT.xsl".intern();
		
	public final static String STYLESHEET_MOBI_PAY_OUT_URL ="/cash/xsl/mobi/PAY_OUT.xsl".intern();
		
	public final static String STYLESHEET_MOBI_SWF_OUT_URL ="/cash/xsl/mobi/SWF_OUT.xsl".intern();
		
	public final static String STYLESHEET_MOBI_CUSDISREQ_OUT_URL ="/cash/xsl/mobi/cusdisreq_out.xsl".intern();
		
	public final static String STYLESHEET_MOBI_CUSENAREQ_OUT_URL ="/cash/xsl/mobi/cusenareq_out.xsl".intern();
		
	public final static String STYLESHEET_MOBI_LOGINID_OUT_URL ="/cash/xsl/mobi/loginid_out.xsl".intern();
		
	public final static String STYLESHEET_MOBI_PASSWORD_OUT_URL ="/cash/xsl/mobi/password_out.xsl".intern();
		
	public final static String STYLESHEET_MOBI_PASSWORDRESET_OUT_URL ="/cash/xsl/mobi/passwordreset_out.xsl".intern();
		
	public final static String STYLESHEET_MOBI_RPREG_OUT_URL ="/cash/xsl/mobi/RPREG_OUT.xsl".intern();
		
	public final static String STYLESHEET_MOBI_CHEQUE_BOOK_REQUEST_URL ="/cash/xsl/mobi/CHEQUE_BOOK_REQUEST.xsl".intern();
		
	public final static String STYLESHEET_FT_BENEFICIARY_NOTIFICATION_URL ="/cash/xsl/cash_notify_ft_beneficiary.xsl".intern();
		
	public final static String STYLESHEET_ADDITIONAL_POSTING_DETAILS_URL ="/cash/xsl/additional_posting_details.xsl".intern();
		
	public final static String STYLESHEET_BENEFICIARYUPLOAD_SAVE_URL ="/core/xsl/system/beneficiary_upload_save.xsl".intern();
		
	public final static String STYLESHEET_SY_CUSTOMER_BENEFICIARY_UPLOAD_MC_URL ="/core/xsl/system/sy_customer_beneficiary_file_upload_mc.xsl".intern();
		
	public final static String STYLESHEET_BENEFICIARYUPLOAD_SAVE_TNX_URL ="/core/xsl/system/beneficiary_upload_tnx_save.xsl".intern();
		
	public final static String STYLESHEET_STATEMENT_UPLOAD_SAVE_URL ="/core/xsl/system/external_statement_upload_save.xsl".intern();
		
	public final static String STYLESHEET_STATEMENT_UPLOAD_SAVE_TNX_URL ="/core/xsl/system/external_statement_upload_tnx_save.xsl".intern();
		
	public final static String STYLESHEET_TEMPLATE_MERGE ="/core/xsl/ws/merge.xsl".intern();
		
	public final static String STYLESHEET_SBLC_URL ="/trade/xsl/system/sy_standbyLC.xsl".intern();
		
	public final static String STYLESHEET_SAVE_CUSTOMERS_STANDBYLC_DOC_URL ="/trade/xsl/system/standbylc_customers.xsl".intern();
		
	public final static String STYLESHEET_CN_CREATE_URL ="/openaccount/xsl/trade_create_cn.xsl".intern();
		
	public final static String STYLESHEET_CN_UPLOAD_URL ="/openaccount/xsl/trade_upload_cn.xsl".intern();
		
	public final static String STYLESHEET_CN_SAVE_URL ="/openaccount/xsl/products/cn.xsl".intern();
		
	public final static String STYLESHEET_CN_CREATE_REP_URL ="/openaccount/xsl/trade_create_cn.xsl".intern();
		
	public final static String STYLESHEET_CN_SUMMARY_URL ="/openaccount/xsl/trade_cn_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_CN_DETAILS_URL ="/openaccount/xsl/trade_create_cn.xsl".intern();
		
	public final static String STYLESHEET_CN_FO_URL ="/openaccount/xsl/fo/cn_fo.xsl".intern();
		
	public final static String STYLESHEET_CN_TEMPLATE_SAVE_URL ="/openaccount/xsl/products/cn_template.xsl".intern();
		
	public final static String STYLESHEET_CN_BANK_URL ="/openaccount/xsl/bank_cn_reporting.xsl".intern();
		
	public final static String STYLESHEET_CN_BANK_DETAILS_URL ="/openaccount/xsl/bank_cn_reporting.xsl".intern();
		
	public final static String STYLESHEET_SY_EXTERNAL_ACCOUNTS_UPLOAD_MC_URL ="/core/xsl/system/sy_external_accounts_file_upload_mc.xsl".intern();
		
	public final static String STYLESHEET_CN_TEMPLATE_MODIFY_URL ="/trade/xsl/trade_template_cn.xsl".intern();
		
	public final static String STYLESHEET_CR_TEMPLATE_SAVE_URL ="/openaccount/xsl/products/cr_template.xsl".intern();
		
	public final static String STYLESHEET_CR_TEMPLATE_MODIFY_URL ="/trade/xsl/trade_template_cr.xsl".intern();
		
	public final static String STYLESHEET_CR_CREATE_URL ="/openaccount/xsl/trade_create_cr.xsl".intern();
		
	public final static String STYLESHEET_CR_UPLOAD_URL ="/openaccount/xsl/trade_upload_cr.xsl".intern();
		
	public final static String STYLESHEET_CR_CREATE_REP_URL ="/openaccount/xsl/trade_create_cr.xsl".intern();
		
	public final static String STYLESHEET_CR_SUMMARY_URL ="/openaccount/xsl/trade_cr_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_CR_DETAILS_URL ="/openaccount/xsl/trade_create_cr.xsl".intern();
		
	public final static String STYLESHEET_CR_SAVE_URL ="/openaccount/xsl/products/cr.xsl".intern();
		
	public final static String STYLESHEET_CR_FO_URL ="/openaccount/xsl/fo/cr_fo.xsl".intern();
		
	public final static String STYLESHEET_FACILITY_MASTER_SAVE_URL ="/core/xsl/products/facility_save.xsl".intern();
		
	public final static String STYLESHEET_SY_FACILITY_URL ="/core/xsl/system/sy_facility_mc.xsl".intern();
		
	public final static String STYLESHEET_FT_TRINT_CREATE_URL ="/treasury/xsl/treasury_create_ft_trint.xsl".intern();
		
	public final static String STYLESHEET_FT_TRINT_DETAILS_URL ="/treasury/xsl/treasury_create_ft_trint.xsl".intern();
		
	public final static String STYLESHEET_FT_TRTPT_CREATE_URL ="/treasury/xsl/treasury_create_ft_trtpt.xsl".intern();
		
	public final static String STYLESHEET_FT_TRTPT_DETAILS_URL ="/treasury/xsl/treasury_create_ft_trtpt.xsl".intern();
		
	public final static String STYLESHEET_EOA_SAVE_URL ="/openaccount/xsl/products/ea.xsl".intern();
		
	public final static String STYLESHEET_TSU_TO_EOA_URL ="/tsu/xsl/v2/tsmt2ea/tsmt_to_ea.xsl".intern();
		
	public final static String STYLESHEET_TSU_TO_EOA_AMEND_URL ="/tsu/xsl/v2/tsmt2ea/tsmt_to_ea_amend.xsl".intern();
		
	public final static String STYLESHEET_EA_DETAILS_URL ="/openaccount/xsl/trade_create_ea.xsl".intern();
		
	public final static String STYLESHEET_EA_SUMMARY_URL ="/core/xsl/common/trade_summary_rep.xsl".intern();
		
	public final static String STYLESHEET_EA_FO_URL ="/openaccount/xsl/fo/ea_fo.xsl".intern();
		
	public final static String STYLESHEET_EA_TO_TSU_V2_TSMT_019_URL ="/tsu/xsl/v2/ea2tsmt/ea_to_tsmt.019.001.04.xsl".intern();
		
	public final static String STYLESHEET_EA_TO_TSU_V2_TSMT_012_URL ="/tsu/xsl/v2/ea2tsmt/ea_to_tsmt.012.001.03.xsl".intern();
		
	public final static String STYLESHEET_EA_TO_TSU_V2_TSMT_026_URL ="/tsu/xsl/v2/ea2tsmt/ea_to_tsmt.026.001.02.xsl".intern();
		
	public final static String STYLESHEET_EA_TO_TSU_V2_TSMT_017_URL ="/tsu/xsl/v2/ea2tsmt/ea_to_tsmt.017.001.03.xsl".intern();
		
	public final static String STYLESHEET_EA_TO_TSU_V2_TSMT_005_URL ="/tsu/xsl/v2/ea2tsmt/ea_to_tsmt.005.001.03.xsl".intern();
		
	public final static String STYLESHEET_EA_TO_TSU_V2_TSMT_007_URL ="/tsu/xsl/v2/ea2tsmt/ea_to_tsmt.007.001.03.xsl".intern();
		
	public final static String STYLESHEET_TSU_TO_EOA_INTENT_TO_PAY_045_URL ="/tsu/xsl/v2/tsmt_to_ea_intent_to_pay.045.xsl".intern();
		
	public final static String STYLESHEET_EA_INITIATE_PAYMENT_URL ="/openaccount/xsl/trade_create_ea_initiate_payment.xsl".intern();
		
	public final static String STYLESHEET_TSU_TO_NEW_IOA_URL ="/tsu/xsl/v2/tsmt2io/tsmt_to_new_io.018.001.03.xsl".intern();
		
	public final static String STYLESHEET_TSU_TO_EXISTING_IOA_URL ="/tsu/xsl/v2/tsmt2io/tsmt_to_existing_io.018.001.03.xsl".intern();
		
	public final static String STYLESHEET_TSU_TO_IOA_PAYMENT_URL ="/tsu/xsl/v2/tsmt2io/tsmt_to_io.017.001.03.xsl".intern();
		
	public final static String STYLESHEET_IO_TO_TSU_V2_TSMT_019_URL ="/tsu/xsl/v2/io2tsmt/io_to_tsmt.019.001.04.xsl".intern();
		
	public final static String STYLESHEET_IO_TO_TSU_V2_TSMT_012_URL ="/tsu/xsl/v2/io2tsmt/io_to_tsmt.012.001.03.xsl".intern();
		
	public final static String STYLESHEET_IO_TO_TSU_V2_TSMT_026_URL ="/tsu/xsl/v2/io2tsmt/io_to_tsmt.026.001.02.xsl".intern();
		
	public final static String STYLESHEET_IO_TO_TSU_V2_TSMT_044_URL ="/tsu/xsl/v2/io2tsmt/io_to_tsmt.044.001.01.xsl".intern();
		
	public final static String STYLESHEET_IO_TO_TSU_V2_TSMT_009_URL ="/tsu/xsl/v2/io2tsmt/io_to_tsmt.009.001.03.xsl".intern();
		
	public final static String STYLESHEET_IO_TO_TSU_V2_TSMT_005_URL ="/tsu/xsl/v2/io2tsmt/io_to_tsmt.005.001.03.xsl".intern();
		
	public final static String STYLESHEET_IO_TO_TSU_V2_TSMT_007_URL ="/tsu/xsl/v2/io2tsmt/io_to_tsmt.007.001.03.xsl".intern();
		
	public final static String STYLESHEET_TD_TRTD_CREATE_URL ="/treasury/xsl/treasury_create_td_trtd.xsl".intern();
		
	public final static String STYLESHEET_TD_TRTD_DETAILS_URL ="/treasury/xsl/treasury_create_td_trtd.xsl".intern();
		
	public final static String STYLESHEET_TD_TRTD_SI_MESSAGE_URL ="/treasury/xsl/treasury_td_trtd_si_message.xsl".intern();
		
	public final static String STYLESHEET_TRUST_RELATIONSHIP_URL ="/core/xsl/system/sy_trust_relationship_mc.xsl".intern();
		
	public final static String STYLESHEET_TRUST_RELATIONSHIP_USER_URL ="/core/xsl/system/sy_trust_relationship_user_mc.xsl".intern();
		
	public final static String STYLESHEET_TRUST_SAVE_MC_URL ="/core/xsl/products/trust_relationship_save_mc.xsl".intern();
		
	public final static String STYLESHEET_TRUST_SAVE_URL ="/core/xsl/products/trust_relationship_save.xsl".intern();
		
	public final static String STYLESHEET_TRUST_USER_SAVE_MC_URL ="/core/xsl/products/trust_relation_user_save_mc.xsl".intern();
		
	public final static String STYLESHEET_TRUST_USER_SAVE_URL ="/core/xsl/products/trust_relation_user_save.xsl".intern();
		
	public final static String STYLESHEET_SY_BANK_MC_URL ="/core/xsl/system/bsf_sy_bank_mc.xsl".intern();
		
	public final static String STYLESHEET_SY_ROLE_MC_URL ="/core/xsl/system/sy_role_mc.xsl".intern();
		
	public final static String STYLESHEET_SY_DISPLAY_BANK_URL ="/core/xsl/system/sy_bank.xsl".intern();
		
	public final static String STYLESHEET_EA_CREATE_URL ="/openaccount/xsl/trade_create_from_scratch_ea.xsl".intern();
		
	public final static String STYLESHEET_EA_CREATE_REP_URL ="/openaccount/xsl/trade_create_from_scratch_ea.xsl".intern();
		
	public final static String STYLESHEET_EA_AMEND_URL ="/openaccount/xsl/trade_amend_ea.xsl".intern();
		
	public final static String STYLESHEET_EA_AMEND_REP_URL ="/openaccount/xsl/trade_amend_ea.xsl".intern();
		
	public final static String STYLESHEET_POOLING_STRUCTURE_SAVE_URL ="/core/xsl/products/pooling_structure_save.xsl".intern();
		
	public final static String STYLESHEET_COPY_POOLING_STRUCTURE_URL ="/cash/xsl/cash_copy_pooling_structure.xsl".intern();
		
	public final static String STYLESHEET_CREATE_POOLING_STRUCTURE_URL ="/cash/xsl/cash_create_pooling_structure.xsl".intern();
		
	public final static String STYLESHEET_POOLING_STRUCTURE_TNX_SAVE_URL ="/core/xsl/products/pooling_structure_tnx_save.xsl".intern();
		
    
	/*****************/
    /* SPECIFIC KEYS */
    /*****************/
    
	public final static String STYLESHEET_SY_REGISTER_USER_URL ="/client/xsl/system/sy_register_user.xsl".intern();
	
	public final static String STYLESHEET_LC_MYSCREEN_USER_URL ="/client/core/xsl/common/lc_myscreen.xsl".intern();
	
			
	/*
	 * The following empty strings are deprecated, and are temporarily added to ensure that the report
	 * project compiles
	 */
	/* TODO Delete the following */

	/** @deprecated */
	public final static String JAVASCRIPT_COM_CURRENCY_URL = "";

	/** @deprecated */
	public final static String JAVASCRIPT_COM_AMOUNT_URL = "";

	/** @deprecated */
	public final static String JAVASCRIPT_COM_FORM_URL = "";

	/** @deprecated * */
	public final static String JAVASCRIPT_COM_FUNCTIONS_URL = "";

	/**
	 * Return page pointed by an URL.
	 *
	 * @param name URL alias
	 * @return URL
	 */
	public static String getPage(String shortPath) throws Exception
	{
		return getURL(shortPath);
	}

	/**
	 * Return page pointed by an URL, in specified language encoding.
	 *
	 * @param name URL alias
	 * @return URL
	 */
	public static String getPage(String shortPath, String encoding) throws Exception
	{
		if (encoding != null)
		{
			return getURL(shortPath, encoding);
		}

		return getURL(shortPath);
	}

	/**
	 * GTPB3855
	 *
	 * @param webResource
	 * @return a static resource of the doc_root relative to the context path
	 */
	public static String getRelativeLink(String webResource)
	{
		String contextPath = EngineContext.getContextPath();
		if ("/".equals(contextPath))
		{
			return webResource;
		}
		else
		{
			return contextPath + webResource;
		}
	}

	/**
	 * Method used to read the content of a file and return its content in the output string.
	 *
	 * @author Olivier
	 */
	private static String getURL(String url) throws IOException
	{
		int capacity = 1024;

		InputStream is = new URL(url).openStream();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		// now process the InputStream...
		byte[] bytes = new byte[capacity];

		int readCount = 0;
		while ((readCount = is.read(bytes)) > 0)
		{

			buffer.write(bytes, 0, readCount);
		}
		is.close();
		return buffer.toString();
	}

	/**
	 * Method used to read the content of a file and return its content in the output string. The charset
	 * is fetched from the language resources.
	 *
	 * @author Olivier
	 */
	private static String getURL(String url, String encoding) throws IOException
	{
		int capacity = 1024;

		InputStream is = new URL(url).openStream();
		InputStreamReader reader = new InputStreamReader(is, encoding);

		// now process the InputStream...
		char[] chars = new char[capacity];
		StringWriter bos = new StringWriter();

		int readCount = 0;
		while ((readCount = reader.read(chars)) > 0)
		{
			bos.write(chars, 0, readCount);
		}

		is.close();
		reader.close();

		return bos.toString();
	}

	/**
	 * @param codeName
	 * @return a code value from its code name
	 */
	public static String getURLFromCode(String codeName)
	{
		try
		{
			Field field = URLAliasesResourceProvider.class.getDeclaredField(codeName);
			if (field.getType() == String.class)
			{
				return (String) field.get(null);
			}
		}
		catch (Exception e)
		{
			Log.error(URLAliasesResourceProvider.class, "Unkown url: " + codeName);
		}
		return null;
	}

	/**
	 * @param constant a value of a property above
	 * @return url computed for the client
	 */
	public static String getWebSource(String constant)
	{
		// return constant;
		return getRelativeLink(constant);
	}

	/**
	 * @param constant a value of a property above
	 * @return url computed for the client
	 */
	public static String getWebSource(String constant, String language)
	{

		StringBuffer result = new StringBuffer();
		int indexOfextension = constant.indexOf(".");
		// get the file name without extension
		result.append(constant.substring(0, indexOfextension));
		// file name + "_"+ language + file extension
		result.append("_")
				.append(language)
				.append(constant.substring(indexOfextension, constant.length()));

		return getRelativeLink(result.toString());
	}

	/**
	 * Return the URL (without the language extension).
	 *
	 * @param name URL alias
	 * @return URL
	 */
	public static URL resolveURL(String name)
	{
		try
		{
			if ((name != null) && name.startsWith("/"))
			{
				return TurbineServlet.getResource(name);
			}
			return new URL(name);
		}
		catch (MalformedURLException e)
		{
			Log.error(URLAliasesResourceProvider.class, "Unable to resolve URL: " + name, e);
			return null;
		}
	}

	/**
	 * Return URL.
	 *
	 * @param name URL alias
	 * @return URL
	 */
	public static String resolveURLFromLanguage(String name, String userLanguage)
	{
		StringBuffer result = new StringBuffer();
		String language = (userLanguage != null) ? userLanguage : DefaultResourceProvider.LANGUAGE;

		int indexOfextension = name.indexOf(".");
		if (indexOfextension != -1)
		{
			// get the file name without extension
			result.append(name.substring(0, indexOfextension));
			// file name + "_"+ language + file extension
			result.append("_").append(language).append(name.substring(indexOfextension));
			return resolveURL(result.toString()).toString();
		}

		return resolveURL(name).toString();
	}

	/**
	 */
	public java.lang.String getDomain()
	{
		return "URLAliasesResourceProvider";
	}

	/**
	 *
	 */
	public void initialize()
	{}
}
	
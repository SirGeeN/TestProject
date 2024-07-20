package com.misys.portal.client.interfaces.components;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.misys.portal.common.localization.Localization;
import com.misys.portal.common.tools.ConvertTools;
import com.misys.portal.interfaces.components.IEMailConstants;
import com.misys.portal.interfaces.core.Configuration;
import com.misys.portal.interfaces.core.InterfaceException;
import com.misys.portal.interfaces.core.event.AbstractObjectProducer;
import com.misys.portal.interfaces.core.event.ObjectConsumer;
import com.misys.portal.systemfeatures.common.GTPCompanyFile;
import com.misys.portal.services.provisioning.ProvisioningService;
/**
 * Format an email from a company file.
 */
public class FixedAddressMailFormatter extends AbstractObjectProducer implements ObjectConsumer
{
    private static final String OPERATION_CODE = "N002";
    private static final String ALERT_BODY = "BANKGROUP_MODIFIED_ALERT_EMAIL_BODY";
    private static final String ALERT_TITLE = "BANKGROUP_MODIFIED_ALERT_EMAIL_TITLE";
    private static final Log LOG = LogFactory.getLog(FixedAddressMailFormatter.class);
    private String destinationEmail = null;
    private String fromAddress = null;
    private String fromPersonal = null;
    /**
     * @see ObjectConsumer#handle(Object)
     */
    public void handle(Object object) throws InterfaceException
    {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<1>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        if (object instanceof GTPCompanyFile)
        {
            try
            {
                process((GTPCompanyFile) object);
            }
            catch (InterfaceException ie)
            {
                throw ie;
            }
            catch (Exception e)
            {
                throw new InterfaceException("An error occured.", e);
            }
        }
        else
        {
            throw new InterfaceException(
                    "A company file is expected. However a different object has been passed");
        }
    }
    /*
     * (non-Javadoc)
     * @see com.misys.portal.interfaces.core.Component#init()
     */
    public void init()
    {
        Configuration configuration = getConfiguration();
        destinationEmail = configuration.getProperty(IEMailConstants.RECIPIENT_TO);
        fromAddress = getConfiguration().getProperty(IEMailConstants.FROM_ADDRESS);
        fromPersonal = getConfiguration().getProperty(IEMailConstants.FROM_PERSONAL);
    }
    private void process(Object object) throws Exception
    {
        GTPCompanyFile companyFile = (GTPCompanyFile) object;
        // Get type of operation from file
        String operation = (String) companyFile.getTemp(ProvisioningService.OPERATION_TYPE);
        // Get language of the company
        String language = companyFile.getCompany().getLanguage();
        if (destinationEmail != null && destinationEmail.length() > 0)
        {
            Object[] args = new Object[1];
            // Get localization for the operation type
            args[0] = Localization.getDecode(language, OPERATION_CODE, operation);
            // Get email subject
            String subject = ConvertTools.formatMessage(
                    Localization.getGTPString(language, ALERT_TITLE),
                        args);
            // Get email message
            String message = ConvertTools.formatMessage(
                    Localization.getGTPString(language, ALERT_BODY),
                        args);
            // Build the XML for email
            StringBuffer emailXMl = new StringBuffer();
            emailXMl.append("<alert_record>");
            emailXMl.append("<message_from_address>")
                    .append(fromAddress)
                    .append("</message_from_address>");
            emailXMl.append("<message_from_personal>")
                    .append(fromPersonal)
                    .append("</message_from_personal>");
            emailXMl.append("<message_to>").append(destinationEmail).append("</message_to>");
            emailXMl.append("<message_subject>").append(subject).append("</message_subject>");
            emailXMl.append("<message_body>").append(message).append("</message_body>");
            emailXMl.append("</alert_record>");
            LOG.info("<<<< Reached Here2 >>>>");
            LOG.info("A mail message has been prepared, subject: " + subject);
            if (LOG.isDebugEnabled())
            {
                LOG.debug("message body: " + message);
            }
            // Publish values to the environment
            getEnvironment().setEntry(IEMailConstants.SUBJECT, subject);
            getEnvironment().setEntry(IEMailConstants.RECIPIENT_TO, destinationEmail);
            // Fire the XML string as object
            fire(emailXMl.toString().trim());
        }
        else
        {
            throw new InterfaceException("The destination email address is not defined");
        }
    }
}

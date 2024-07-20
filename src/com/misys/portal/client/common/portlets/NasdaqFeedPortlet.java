package com.misys.portal.client.common.portlets;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.net.URL;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ecs.ClearElement;
import org.apache.ecs.ConcreteElement;
import org.apache.turbine.services.TurbineServices;
import org.apache.turbine.util.RunData;
import com.misys.portal.common.services.XSLTService;
import com.misys.portal.core.portal.PortletException;
import com.misys.portal.core.portal.portlets.AbstractPortlet;
import com.misys.portal.security.GTPUser;
import com.misys.portal.services.resources.PortalResourcesService;
public class NasdaqFeedPortlet extends AbstractPortlet {
    private static final String PERMISSION = "permission";
    private static final String URL = "url";
    private static final String STYLESHEET = "stylesheet";
    private static final Log LOG = LogFactory.getLog(NasdaqFeedPortlet.class);
    private String permission;
    private String url;
    private String stylesheet;
     
    /*
     * This method processes the external third party content and returns an
     * HTML markup
     *
     * @see
     * com.misys.portal.core.portal.portlets.AbstractPortlet#getContent(org.
     * apache.turbine.util.RunData)
     */
    @Override
    public ConcreteElement getContent(final RunData data) {
        // Check User permission
        if (!checkPortletPermission(data)) {
            return null;
        }
        // Process external data
        return fetchNasdaqFeed(data);
    }
     
    /*
     * Initialization code
     *
     * @see com.misys.portal.core.portal.portlets.AbstractPortlet#init()
     */
    @Override
    public void init() throws PortletException {
        permission = this.getPortletConfig().getInitParameter(PERMISSION);
        url = this.getPortletConfig().getInitParameter(URL);
        stylesheet = this.getPortletConfig().getInitParameter(STYLESHEET);
    }
 
    /*
     * Fetch the RSS 2.0 feed from the link and transform into an HTML fragment
     */
    private ConcreteElement fetchNasdaqFeed(RunData data) {
        try {
            Dictionary params = this.getPortletConfig().getInitParameters();
            HashMap dic = new HashMap();
            for (Enumeration keys = params.keys(); keys.hasMoreElements();) {
                String key = (String) keys.nextElement();
                dic.put(key, params.get(key));
            }
            URL feedUrl = new URL(url);
            URL stylesheetUrl = ((PortalResourcesService) TurbineServices
                    .getInstance().getService(
                            PortalResourcesService.SERVICE_NAME))
                    .getResource(stylesheet);
            String contentOfPortlet = ((XSLTService) TurbineServices
                    .getInstance().getService(XSLTService.SERVICE_NAME))
                    .transform(
                            new StreamSource(new InputStreamReader(feedUrl
                                    .openStream())), stylesheetUrl, dic);
            return new ClearElement(contentOfPortlet);
        } catch (Exception e) {
            LOG.error("Error in fetching Nasdaq feeds", e);
        }
        // Custom processing logic
        return null;
    }
}
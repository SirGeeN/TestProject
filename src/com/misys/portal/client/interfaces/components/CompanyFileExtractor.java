package com.misys.portal.client.interfaces.components;
import java.util.HashMap;

import com.misys.portal.interfaces.core.InterfaceException;
import com.misys.portal.interfaces.core.event.AbstractObjectProducer;
import com.misys.portal.interfaces.core.event.ObjectConsumer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class CompanyFileExtractor extends AbstractObjectProducer implements ObjectConsumer
{
    private  static final String COMPANY_FILE = "COMPANY_FILE";
    private static final Log LOG = LogFactory.getLog(CompanyFileExtractor.class);
    public void handle(Object object) throws InterfaceException
    {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<2>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // Check that the incoming object is a Hashmap
        if (object instanceof HashMap)
        {
            HashMap<String,Object> map = ((HashMap<String,Object>) object);
            // Check that the Hashmap has the company file object
            if (map.get(COMPANY_FILE) != null)
            {
                try
                {
                    // Propogate the company file as event for the next component
                	LOG.info("<<<< Reached Here1 >>>>");
                    fire(map.get(COMPANY_FILE));
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
                throw new InterfaceException("The hashmap doesnot contain a company file.");
            }
        }
        else
        {
            throw  new InterfaceException("An hashmap containing the company file is expected. However a different object has been passed");
        }
    }
}

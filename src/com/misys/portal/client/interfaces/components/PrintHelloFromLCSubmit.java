package com.misys.portal.client.interfaces.components;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.misys.portal.interfaces.core.InterfaceException;
import com.misys.portal.interfaces.core.event.AbstractObjectProducer;
import com.misys.portal.interfaces.core.event.ObjectConsumer;
import com.misys.portal.product.lc.common.LetterOfCreditFile;

public class PrintHelloFromLCSubmit extends AbstractObjectProducer implements ObjectConsumer
{
	
	private static final Log LOG = LogFactory.getLog(PrintHelloFromLCSubmit.class);

	public void handle(Object arg0) throws InterfaceException
	{
            if(arg0 instanceof LetterOfCreditFile)
            {
                com.misys.portal.product.lc.common.LetterOfCreditFile lc = (LetterOfCreditFile)arg0;
                lc.getAccount();
                System.out.println("<<<<< lc.getAccount() >>>> "+ lc.getAccount());
                System.out.println("<<<<< lc.getAmtName() >>>> "+ lc.getAmtName());
                System.out.println("<<<<< llc.getCompany_name() >>>> "+ lc.getCompany_name());
                
                
		LOG.info("<<<<<<<<<< object class is >>>>>>>>>>>>>" + arg0.getClass());
		System.out.println("<<<<<<< object class is >>>>>>>>>>>>>>" + arg0.getClass());
		
		System.out.println("<<<<<<<<<<<<<<<<<<< Printling Hello World When LC Transaction Is Submitted >>>>>>>>>>>>>>");
		LOG.info("<<<<<<<<<<<<<<<<<<< Printling Hello World When LC Transaction Is Submitted >>>>>>>>>>>>>>");
		
		
		System.out.println("<<<<<<<<<<<<<<<<<<< Printling Hello World When LC Transaction Is Submitted >>>>>>>>>>>>>>");
		LOG.info("<<<<<<<<<<<<<<<<<<< Printling Hello World When LC Transaction Is Submitted >>>>>>>>>>>>>>");
		
		
		// TODO Auto-generated method stub
		fire(arg0);
            }
            
	}

}

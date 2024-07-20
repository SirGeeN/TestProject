/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaius.fbccComponent;


import com.misys.portal.interfaces.core.Environment;
import com.misys.portal.interfaces.core.InitializationException;
import com.misys.portal.interfaces.core.Interface;
import com.misys.portal.interfaces.core.InterfaceException;
import com.misys.portal.interfaces.services.Interfaces;
import com.misys.portal.systemfeatures.common.GTPCompanyFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.misys.portal.common.localization.Localization;
import com.misys.portal.common.tools.ConvertTools;
import com.misys.portal.interfaces.components.IEMailConstants;
import com.misys.portal.interfaces.core.Configuration;
import com.misys.portal.interfaces.core.InterfaceException;
import com.misys.portal.interfaces.core.event.AbstractObjectProducer;
import com.misys.portal.interfaces.core.event.ObjectConsumer;

import com.misys.portal.services.provisioning.ProvisioningService;

/**
 *
 * @author GAIUS
 */
public class MyInterface {

    public void runInterface() throws InitializationException {
        // Get the interface chain
        Interface intface = Interfaces.getInterface("interfaceName");

// Set environment values
        Environment environment = intface.getEnvironment();
// Setting language as English
        environment.setEntry("language", "en");
        
// Run the interface
        //intface.run(obj);
    }
    
    

}

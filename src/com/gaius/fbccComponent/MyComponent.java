/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaius.fbccComponent;

import com.misys.portal.interfaces.components.IEMailConstants;
import com.misys.portal.interfaces.core.Configuration;
import com.misys.portal.interfaces.core.InterfaceException;
import com.misys.portal.interfaces.core.event.AbstractObjectProducer;
import com.misys.portal.interfaces.core.event.ObjectConsumer;
import com.misys.portal.product.lc.common.LetterOfCreditFile;
import org.jfree.util.Log;

/**
 *
 * @author GAIUS
 */
public class MyComponent extends AbstractObjectProducer implements ObjectConsumer {

    /*
     * (non-Javadoc)
     * @see  ...interfaces.core.event.ObjectConsumer#handle(java.lang.Object)
     */
    @Override
    public void handle(Object object) throws InterfaceException {
        if (object instanceof LetterOfCreditFile) {
            // do something with the Import Letter of Credit 
            Log.info("LC consumed!");
        }
// send event to the next Component 
        fire(object);
    }

    public void readNWriteEnvObject() {
        //Read/ Write Environment Objects
// Get user name from the environment
        String user_name = (String) getEnvironment().getEntry("user_name");

// Publish product code as LC to the environment
        getEnvironment().setEntry("product_code", "LC");// this set published property. see below
        getConfiguration().getProperty("propertyName");// this gets the components property. see below

    }

    /*
    <component name="MyTestComponent"> 
        <description>This describes the components</description>
        <classname>com.gaius.fbccComponent.MyComponent</classname>
        <properties>
                <property name="propertyName" description="propertyDescrition"> value1</property>
                <property name="propertyName" description="propertyDescrition"> value1</property>
        </properties>
        <publish>
                <property name="product_code" description="description"/>
        </publish>
        <prerequisite>
                <property name="" description=""/>
        </prerequisite>
    </component>
     */
}

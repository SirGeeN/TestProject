/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hubberspot.ejb;
 
import java.rmi.RemoteException;
import javax.ejb.EJBObject;
 
//The remote interface defines the business methods that are specific to the bean.
public interface HelloRemote extends EJBObject {
     
    public String sayHello(String name) throws RemoteException;
 
}
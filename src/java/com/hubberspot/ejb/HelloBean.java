/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hubberspot.ejb;
 
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/* To build an enterprise bean that allows local access, you must code the local
interface and the local home interface. The local interface defines the bean’s
business methods, and the local home interface defines its life-cycle and finder
methods.
 */

/*The client initiates the life cycle by invoking the create method. The
EJB container instantiates the bean and then invokes the setSessionContext
and ejbCreate methods in the session bean. The bean is now ready to have its
business methods invoked.
*/

public class HelloBean implements SessionBean {
 
    @Override
    public void ejbActivate() throws EJBException, RemoteException {
        System.out.println("<< ejbActivate() >>");
    }
 
    @Override
    public void ejbPassivate() throws EJBException, RemoteException {
         System.out.println("<< ejbPassivate() >>");
    }
 
    @Override
    public void ejbRemove() throws EJBException, RemoteException {
        System.out.println("<< ejbRemove() >>");
    }
 
    @Override
    public void setSessionContext(SessionContext arg0) throws EJBException,RemoteException {
        
        System.out.println("<< setSessionContext >>" + arg0);
    }
 
    // used for creating a reference to RemoteInterface
    public void ejbCreate () throws CreateException{
        System.out.println("<< ejb - create >>");
    }
 
    // Our business method which client will call
    public String sayHello(String name) throws RemoteException, CreateException {
        return "<< My name is >>>>..."+ name ;
    }
 
}

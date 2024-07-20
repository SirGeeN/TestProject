/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.misys.portletsClass;

import com.misys.portal.common.bulk.field.CodeField;
import com.misys.portal.common.portlets.ActionPortlet;
import com.misys.portal.common.tracer.GTPException;
import com.misys.portal.common.widgets.FormFieldRow;
import com.misys.portal.product.util.MasterProductFile;
import com.misys.portal.product.util.TransactionProductFile;
import com.misys.portal.security.GTPUser;
import com.misys.portal.systemfeatures.common.AbstractStaticData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.Element;
import org.apache.ecs.ElementContainer;
import org.apache.turbine.util.RunData;

/**
 *
 * @author GAIUS
 */
public class Portlets extends ActionPortlet{

    @Override
    protected ConcreteElement getFailedReauthenticationContentForBeneficiary(RunData data, AbstractStaticData theFile, String stylesheet, Map dic, StringBuffer xmlResult) throws GTPException {
        return super.getFailedReauthenticationContentForBeneficiary(data, theFile, stylesheet, dic, xmlResult); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConcreteElement saveEntity(RunData data) {
        return super.saveEntity(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConcreteElement saveCustomerRef(RunData data) {
        return super.saveCustomerRef(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ElementContainer maintainTemplateModify(RunData data) {
        return super.maintainTemplateModify(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ElementContainer maintainTemplateDelete(RunData data) {
        return super.maintainTemplateDelete(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConcreteElement maintainEntity(RunData data) {
        return super.maintainEntity(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConcreteElement maintainCustomerRef(RunData data) {
        return super.maintainCustomerRef(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean isConfirmationDetailsEnabled(CodeField productCode, CodeField subProductCode, String mode, GTPUser user) throws GTPException {
        return super.isConfirmationDetailsEnabled(productCode, subProductCode, mode, user); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Element getTitle(RunData data) {
        return super.getTitle(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ElementContainer getSearchForm(List<FormFieldRow> fields, String ajaxoption, RunData data) {
        return super.getSearchForm(fields, ajaxoption, data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ElementContainer getSearchForm(FormFieldRow[] fields, String ajaxoption, RunData data, boolean isExpanded, String helpText) {
        return super.getSearchForm(fields, ajaxoption, data, isExpanded, helpText); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ElementContainer getSearchForm(FormFieldRow[] fields, String ajaxoption, RunData data, boolean isExpanded) {
        return super.getSearchForm(fields, ajaxoption, data, isExpanded); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ElementContainer getFailedReauthenticationContent(RunData data, TransactionProductFile theFile, String stylesheet, String errorMessageKey, HashMap inputDic) {
        return super.getFailedReauthenticationContent(data, theFile, stylesheet, errorMessageKey, inputDic); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ElementContainer getFailedReauthenticationContent(RunData data, TransactionProductFile theFile, String stylesheet, String errorMessageKey) {
        return super.getFailedReauthenticationContent(data, theFile, stylesheet, errorMessageKey); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ConcreteElement getFailedReauthenticationContent(RunData data, MasterProductFile theFile, String stylesheet, String errorMessageKey) throws GTPException {
        return super.getFailedReauthenticationContent(data, theFile, stylesheet, errorMessageKey); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ElementContainer getFailedContent(RunData data, TransactionProductFile theFile, String stylesheet, String errorMessageValue) {
        return super.getFailedContent(data, theFile, stylesheet, errorMessageValue); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

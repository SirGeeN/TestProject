/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.misys.portal.product.common.portlets;

import com.misys.portal.common.binder.RequestParametersSet;
import com.misys.portal.common.portlets.GTPRootPortlet;
import com.misys.portal.common.tools.RelativeURI;
import com.misys.portal.common.tracer.GTPException;
import com.misys.portal.common.widgets.FormFieldRow;
import com.misys.portal.common.widgets.dijit.form.Form;
import com.misys.portal.core.portal.PortletException;
import com.misys.portal.core.portal.factory.PortletRegistry;
import com.misys.portal.security.GTPCompany;
import com.misys.portal.security.util.GTPAccessControlList;
import java.util.List;
import java.util.Map;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.Element;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.html.A;
import org.apache.ecs.html.Select;
import org.apache.turbine.util.RunData;
import org.json.JSONException;

/**
 *
 * @author GAIUS
 */
public class OutstandingPortlet extends GTPRootPortlet{

    @Override
    protected String getTopParent(PortletRegistry myRegistry, String name) {
        return super.getTopParent(myRegistry, name); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void buildRoleList(GTPCompany company, Select selectRole, String searchRole, boolean isCompany) throws GTPException {
        super.buildRoleList(company, selectRole, searchRole, isCompany); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void buildProductList(RunData data, Select selectProductCode, String searchProductCode, boolean isCompany) {
        super.buildProductList(data, selectProductCode, searchProductCode, isCompany); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void buildProductList(RunData data, GTPAccessControlList acl, Select selectProductCode, String searchProductCode, boolean isCustomer) {
        super.buildProductList(data, acl, selectProductCode, searchProductCode, isCustomer); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void buildCodeList(Select selectCode, String searchCode, String language, String codePrefix) {
        super.buildCodeList(selectCode, searchCode, language, codePrefix); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<FormFieldRow> buildAreaProductSubProductLinkedSelects(RunData data, ElementContainer scriptContainer, GTPAccessControlList acl, boolean useCompanyACL) throws JSONException {
        return super.buildAreaProductSubProductLinkedSelects(data, scriptContainer, acl, useCompanyACL); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void addProductInSelect(RunData data, Select selectProductCode, String productCode, String searchProductCode) {
        super.addProductInSelect(data, selectProductCode, productCode, searchProductCode); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean addEntitiesInSelect(Select selectEntity, String[] searchDept, boolean hasSearch, RunData data) throws GTPException {
        return super.addEntitiesInSelect(selectEntity, searchDept, hasSearch, data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConcreteElement showMessages(RunData data, String formName) throws Exception {
        return super.showMessages(data, formName); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void severalSubmitAction(RunData data, ElementContainer root, String listKeys) throws GTPException {
        super.severalSubmitAction(data, root, listKeys); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void severalDeleteAction(RunData data, ElementContainer root, String listKeys) throws GTPException {
        super.severalDeleteAction(data, root, listKeys); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTitleToPortletContext(RunData data, ConcreteElement ce) {
        super.setTitleToPortletContext(data, ce); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConcreteElement openGeneralModule(RunData data) {
        return super.openGeneralModule(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RequestParametersSet initReqParamSet(RunData data) {
        return super.initReqParamSet(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() throws PortletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Element getTitleWithLink(RunData data) {
        return super.getTitleWithLink(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Element getTitleFromPortletContext(RunData data) {
        return super.getTitleFromPortletContext(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Element getTitle(RunData data) {
        return super.getTitle(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RelativeURI getCurrentURI(RunData data) {
        return super.getCurrentURI(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConcreteElement getContent(RunData data) {
        return super.getContent(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getAllowMaximize(RunData data) {
        return super.getAllowMaximize(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getAllowEdit(RunData data) {
        return super.getAllowEdit(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Element createPageAnchors(int pageOffset, int pageCount, int pageSize) {
        return super.createPageAnchors(pageOffset, pageCount, pageSize); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Form completeForm(RunData data, ElementContainer formContents) {
        return super.completeForm(data, formContents); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public A buildPageAnchor(Element content, String pageOffset) {
        return super.buildPageAnchor(content, pageOffset); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Element buildCheckbox(String ref_id, String tnx_id, String product_code) {
        return super.buildCheckbox(ref_id, tnx_id, product_code); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public A buildAnchorSort(String menuText, RunData data, String orderCol, String orderType) {
        return super.buildAnchorSort(menuText, data, orderCol, orderType); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addGridComparator(RunData data, String gridId, Map columnSortFunctions) {
        super.addGridComparator(data, gridId, columnSortFunctions); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

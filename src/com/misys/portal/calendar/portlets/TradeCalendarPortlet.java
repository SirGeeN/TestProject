/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.misys.portal.calendar.portlets;

import com.misys.portal.core.portal.PortletConfig;
import com.misys.portal.core.portal.PortletException;
import com.misys.portal.core.portal.portlets.AbstractPortlet;
import org.apache.ecs.ConcreteElement;
import org.apache.ecs.Element;
import org.apache.turbine.services.cache.CachedObject;
import org.apache.turbine.util.RunData;

/**
 *
 * @author GAIUS
 */
public class TradeCalendarPortlet extends AbstractPortlet{

    @Override
    public void setExpirationMillis(long expirationMillis) {
        super.setExpirationMillis(expirationMillis); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getExpirationMillis() {
        return super.getExpirationMillis(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCachedObject(CachedObject cachedObject) {
        super.setCachedObject(cachedObject); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPortletConfig(PortletConfig newPortletConfig) {
        super.setPortletConfig(newPortletConfig); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setName(String name) {
        super.setName(name); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCreationTime(long creationTime) {
        super.setCreationTime(creationTime); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void setContent(ConcreteElement content) {
        super.setContent(content); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void setContent(String content) {
        super.setContent(content); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCacheable(boolean cacheable) {
        super.setCacheable(cacheable); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCacheable() {
        return super.isCacheable(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() throws PortletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Element getTitle(RunData data) {
        return super.getTitle(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PortletConfig getPortletConfig() {
        return super.getPortletConfig(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        return super.getName(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescription() {
        return super.getDescription(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getCreationTime() {
        return super.getCreationTime(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkPortletPermission(RunData data) {
        return super.checkPortletPermission(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConcreteElement getContent(RunData data) {
        return super.getContent(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getAllowPDFExport(RunData data) {
        return super.getAllowPDFExport(data); //To change body of generated methods, choose Tools | Templates.
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
    public boolean expire() {
        return super.expire(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConcreteElement getExport(RunData data) {
        return super.getExport(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCharted() {
        return super.isCharted(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

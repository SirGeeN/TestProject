package com.misys.portal.services.security.db;

import com.misys.portal.common.tracer.CompanyNotFoundException;
import com.misys.portal.common.tracer.GTPException;
import com.misys.portal.core.cache.FAOApplicationCacheUtils;
import com.misys.portal.security.GTPCompany;
import com.misys.portal.security.GTPCompanyTnx;
import com.misys.portal.security.GTPExtendedCompany;
import com.misys.portal.security.GTPExtendedCompanyFile;
import com.misys.portal.security.GTPExtendedCompanyFileTnx;
import com.misys.portal.security.GTPExtendedCompanyTnx;

public class DBCompanyManagerWithObjectData
  extends DBCompanyManager
{
  public void addCompany(GTPCompany company)
    throws GTPException
  {
    super.addCompany(company);
    updateObjectData(company);
  }
  
  public void addCompanyTnx(GTPCompanyTnx companyTnx)
    throws GTPException
  {
    super.addCompanyTnx(companyTnx);
    updateObjectDataTnx(companyTnx);
  }
  
  public void remove(GTPCompany company)
    throws GTPException
  {
    super.remove(company);
    removeObjectDataFields(company);
    
    clearCompanyObjectCache(company);
  }
  
  public GTPCompany retrieve(int companyId)
    throws GTPException
  {
    GTPCompany result = FAOApplicationCacheUtils.getCompanyDetails(companyId);
    if (result == null)
    {
      result = super.retrieve(companyId);
      fillObjectDataFields(result);
      addCompanyObjectToCache(Integer.toString(companyId), result);
    }
    return result;
  }
  
  public GTPCompany retrieve(String companyAbbvName)
    throws GTPException, CompanyNotFoundException
  {
    GTPCompany result = FAOApplicationCacheUtils.getCompanyDetails(companyAbbvName);
    if (result == null)
    {
      result = super.retrieve(companyAbbvName);
      fillObjectDataFields(result);
      addCompanyObjectToCache(companyAbbvName, result);
    }
    return result;
  }
  
  public GTPCompanyTnx retrieveTnx(String companyAbbvName)
    throws GTPException, CompanyNotFoundException
  {
    GTPCompanyTnx result = super.retrieveTnx(companyAbbvName);
    
    fillObjectDataFieldsTnx(result);
    return result;
  }
  
  public void update(GTPCompany company)
    throws GTPException
  {
    super.update(company);
    updateObjectData(company);
    
    clearCompanyObjectCache(company);
  }
  
  private void fillObjectDataFields(GTPCompany result)
    throws GTPException
  {
    GTPExtendedCompany extendedCompany = new GTPExtendedCompany();
    extendedCompany.populateDetails(result);
  }
  
  private void fillObjectDataFieldsTnx(GTPCompanyTnx result)
    throws GTPException
  {
    GTPExtendedCompanyTnx extendedCompanyTnx = new GTPExtendedCompanyTnx();
    extendedCompanyTnx.populateDetails(result);
  }
  
  private void removeObjectDataFields(GTPCompany company)
    throws GTPException
  {
    GTPExtendedCompanyFile extendedCompanyHolder = new GTPExtendedCompanyFile();
    extendedCompanyHolder.completeWith(company);
    extendedCompanyHolder.delete();
  }
  
  private void updateObjectData(GTPCompany company)
    throws GTPException
  {
    GTPExtendedCompanyFile extendedCompanyHolder = new GTPExtendedCompanyFile();
    extendedCompanyHolder.completeWith(company);
    extendedCompanyHolder.save();
  }
  
  private void updateObjectDataTnx(GTPCompanyTnx companyTnx)
    throws GTPException
  {
    GTPExtendedCompanyFileTnx extendedCompanyHolder = new GTPExtendedCompanyFileTnx();
    extendedCompanyHolder.completeWith(companyTnx);
    extendedCompanyHolder.save();
  }
}

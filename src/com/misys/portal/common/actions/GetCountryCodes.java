package com.misys.portal.common.actions;

import com.misys.portal.common.ajax.actions.AjaxAction;
import com.misys.portal.common.tools.CodeData;
import com.misys.portal.common.tools.ListComparator;
import com.misys.portal.common.tools.ListElement;
import com.misys.portal.common.tools.ListElementSet;
import com.misys.portal.security.GTPUser;
import java.util.Arrays;
import java.util.Iterator;
import javax.servlet.http.HttpServletResponse;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.template.TemplateInfo;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetCountryCodes
extends AjaxAction {
    @Override
    public void doPerform(RunData data) throws Exception {
        JSONObject json = new JSONObject();
        JSONArray countryCodesJsonArray = new JSONArray();
        ListComparator tmpComparator = new ListComparator("code_val", "long_desc", true);
        tmpComparator.setAscending(true);
        ListElementSet tmpSet = CodeData.retrieveCodeData((RunData)data, (String)"00001", (String)("" + ((GTPUser)data.getUser()).getCompanyId()), (String)"*", (String)"*", (String)"C006", (String)((GTPUser)data.getUser()).getLanguage(), (ListComparator)tmpComparator);
        Iterator itr = tmpSet.elements();
        while (itr.hasNext()) {
            ListElement element = (ListElement)itr.next();
            countryCodesJsonArray.put(element.getData("code_val", (Object)""));
        }
        json.put("countryCodesJsonArray", (Object)countryCodesJsonArray);
        System.out.println("<< JSON For CountryCode >>"+ Arrays.toString(json.toString().getBytes("UTF-8")));
        System.out.println("<< JSON For CountryCode >>"+ json.toString());
        data.getResponse().setHeader("Cache-Control", "max-age=0,no-cache,no-store,post-check=0,pre-check=0");
        data.getResponse().setHeader("Expires", "Mon, 26 Jul 1997 05:00:00 GMT");
        data.getTemplateInfo().setTemp("xmlHttpRequestResponseContentType", (Object)"application/json; charset=utf-8");
        data.getTemplateInfo().setTemp("xmlHttpRequestResponse", (Object)json.toString().getBytes("UTF-8"));
    }
}

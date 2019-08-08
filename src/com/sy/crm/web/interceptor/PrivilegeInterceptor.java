package com.sy.crm.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.sy.crm.domain.User;

public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//ÅÐ¶ÏÓÃ»§ÊÇ·ñµÇÂ¼
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existuser");
		if(user==null){
			ActionSupport actionSupport = (ActionSupport) invocation.getAction();
			actionSupport.addActionError("ÇëÏÈµÇÂ¼");
			return actionSupport.LOGIN;
		}
		else{
			return invocation.invoke();
		}
	}

}

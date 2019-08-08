package com.sy.crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sy.crm.Utils.MD5Utils;
import com.sy.crm.domain.PageBean;
import com.sy.crm.domain.User;
import com.sy.crm.service.UserService;
/*
 * ע�⿪��
 * @Controller("userAction")
 * @Scope("prototype")
 * @ParentPackge("struts-default")
 * @namespace("/")
 */

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private Integer currentpage=1;
	public void setCurrentpage(Integer currentpage) {
		if(currentpage==null){
			currentpage=3;
		}
		else{
		this.currentpage = currentpage;
		}
	}
	//ÿҳ��ʾ��¼��
	private Integer pagesize=3;
	public void setPagesize(Integer pagesize) {
		if(pagesize==null){
			pagesize=3;
		}
		else{
		this.pagesize = pagesize;
		}
	}
	
	/*
	 * @Action(value=""user_registe)
	 */
	public String registe(){
		userService.registe(user);
		return "login";
	}
	
	public String login( ){
		User existuser = userService.login(user);
		if(existuser == null){
			this.addActionError("�û������������");
			return "login";
		}else{
			//ServletActionContext.getRequest().getSession().setAttribute(arg0, arg1);
			ActionContext.getContext().getSession().put("existuser", existuser);
			return "success";
		}
	}
	public String findAllUser(){
		List<User> list = userService.findAll();
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(User.class, "user_id","user_name");
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(JSONObject.toJSONString(list,filter));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String exit(){
		ActionContext.getContext().getSession().remove("existuser");
		return "login";
	}
	
	public String findAll(){
		//��ҳ��ѯ�ͻ��б�;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		if(user.getUser_code()!=null){
			if(user.getUser_code()!=null&&!"".equals(user.getUser_code()))
			detachedCriteria.add(Restrictions.like("user_code","%"+ user.getUser_code()+"%"));
		}
		if(user.getUser_name()!=null){
		if(user.getUser_name()!=null&&!"".equals(user.getUser_name())){
			detachedCriteria.add(Restrictions.like("user_name", user.getUser_name()+"%"));
		}
		}
		//����ҵ���
		PageBean<User> pageBean=  userService.findByPage(detachedCriteria,currentpage,pagesize);
		//��ŵ�ֵջ
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	public String edit(){
		//����id��ѯ����������
		user = userService.findById(user.getUser_id());
		//1.�ֶ�ѹ��ֵջ <s:property value="cust_name"/>
		//2.ģ������user�������ֵջ��<s:property value="model.cust_name"/>
		ActionContext.getContext().getValueStack().push(user);
		return "editSuccess";
	}
	public String update(){
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		userService.update(user);
		return "updateSuccess";
	}
	
	public String delete(){
		user = userService.findById(user.getUser_id());
		userService.delete(user);
		return "deleteSuccess";
	}
	
}

package com.sy.crm.web.action;


import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sy.crm.domain.Customer;
import com.sy.crm.domain.PageBean;
import com.sy.crm.domain.SaleVisit;
import com.sy.crm.domain.User;
import com.sy.crm.service.CustomerService;
import com.sy.crm.service.SaleVisitService;
import com.sy.crm.service.UserService;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
	private SaleVisit saleVisit = new SaleVisit();
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}
	//注入saleVisitService
	@Resource(name="saleVisitService")
	private SaleVisitService saleVisitService;
	//注入linkManService
	@Resource(name="customerService")
	private CustomerService customerService;
	//注入userService
	@Resource(name="userService")
	private UserService userService;
	
	private Integer pagesize = 3;
	private Integer currentpage = 1;
	public Integer getPagesize() {
		return pagesize;
	}
	public Integer getCurrentpage() {
		return currentpage;
	}
	public void setPagesize(Integer pagesize) {
		if(pagesize==null){
			this.pagesize=3;
		}
		else {
			this.pagesize=pagesize;
		}
		this.pagesize = pagesize;
	}
	public void setCurrentpage(Integer currentpage) {
		if(currentpage==null){
			this.currentpage = 1;
		}
		else{
			this.currentpage = currentpage;
		}
	}
	
	private Date visit_endtime;
	public Date getVisit_endtime() {
		return visit_endtime;
	}
	public void setVisit_endtime(Date visit_endtime) {
		this.visit_endtime = visit_endtime;
	}
	public String findAll() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		if(saleVisit.getVisit_time()!=null){
			detachedCriteria.add(Restrictions.gt("visit_time", saleVisit.getVisit_time()));
		}
		if(visit_endtime!=null){
			detachedCriteria.add(Restrictions.le("visit_time", visit_endtime));
		}
		PageBean<SaleVisit> pageBean = saleVisitService.findAll(detachedCriteria,currentpage,pagesize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	public String saveUI(){
		return "saveUI";
	}
	public String save(){
		saleVisitService.save(saleVisit);
		return "saveSuccess";
	}
	public String edit(){
		//查询所有的客户
		List<Customer> custlist = customerService.findAll();
		List<User> userslist = userService.findAll();
		ActionContext.getContext().getValueStack().set("custlist", custlist);
		ActionContext.getContext().getValueStack().set("userlist", userslist);
		saleVisit = saleVisitService.findById(saleVisit.getVisit_id());
		ActionContext.getContext().getValueStack().push(saleVisit);
		return "editSuccess";
	}
	public String update(){
		saleVisitService.update(saleVisit);
		return "updateSuccess";
	}
	public String delete(){
		saleVisit = saleVisitService.findById(saleVisit.getVisit_id());
		saleVisitService.delete(saleVisit);
		return "deleteSuccess";
	}
	public String searchUI(){
		return "searchUI";
	}
}

package com.sy.crm.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.Update;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.xml.internal.ws.api.model.wsdl.editable.EditableWSDLBoundFault;
import com.sy.crm.domain.Customer;
import com.sy.crm.domain.LinkMan;
import com.sy.crm.domain.PageBean;
import com.sy.crm.service.CustomerService;
import com.sy.crm.service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	
	private LinkMan linkMan = new LinkMan();
	public void setLinkMan(LinkMan linkMan) {
		this.linkMan = linkMan;
	}
	@Override
	public LinkMan getModel() {
		return linkMan;
	}
	//注入service
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	private CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	private Integer pagesize=3;
	private Integer currentpage=1;
	
	
	public void setPagesize(Integer pagesize) {
		if(pagesize==null){
			pagesize=3;
		}
		else{
			this.pagesize = pagesize;
		}
	}
	public void setCurrentpage(Integer currentpage) {
		if(currentpage==null){
			currentpage=3;
		}
		else{
			this.currentpage = currentpage;
		}
	}

	public String findAll(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class) ;
		if(linkMan.getLkm_name()!=null){
			detachedCriteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		if(linkMan.getLkm_gender()!=null&&!"".equals(linkMan.getLkm_gender())){
			detachedCriteria.add(Restrictions.eq("lkm_gender",linkMan.getLkm_gender()));
		}
		PageBean<LinkMan> pageBean = linkManService.findByPage(detachedCriteria,currentpage,pagesize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	public String add(){
		List<Customer> list = customerService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "add";
	}
	public String save(){
		linkManService.save(linkMan);
		return "saveSuccess";
	}
	public String edit(){
		//查询所有客户
		List<Customer> list = customerService.findAll();
		linkMan = linkManService.findById(linkMan.getLkm_id());
		ActionContext.getContext().getValueStack().set("list", list);
		ActionContext.getContext().getValueStack().push(linkMan);
		return "edit";
	}
	public String update(){
		linkManService.update(linkMan);
		return "updateSuccess";
	}
	public String delete(){
		linkMan = linkManService.findById(linkMan.getLkm_id());
		linkManService.delete(linkMan);
		return "deleteSuccess";
	}
	public String searchUI(){
		return "searchUI";
	}
}

package com.sy.crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sy.crm.domain.BaseDict;
import com.sy.crm.domain.PageBean;
import com.sy.crm.service.BaseDictService;


public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {
	private BaseDict basic = new BaseDict();
	@Override
	public BaseDict getModel() {
		return basic;
	}
	private BaseDictService baseDictService;
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
}
	private Integer pagesize = 3;
	private Integer currentpage =1;
	public void setPagesize(Integer pagesize) {
		if(pagesize==null){
			pagesize = 3;
		}
		else{
			this.pagesize = pagesize;
		}
	}
	public void setCurrentpage(Integer currentpage) {
		if(currentpage==null){
			this.currentpage = 1;
		}else{
		this.currentpage = currentpage;
		}
	}
	public String findByTypeCode() {
		List<BaseDict> list = baseDictService.findByTypeCode(basic.getDict_type_code());
		System.out.println(JSON.toJSON(list).toString());
//      jsonlib将list转为json
//		JsonConfig jsonConfig = new JsonConfig();
//		jsonConfig.setExcludes(new String[]{"dict_sort","dict_enable","dict_memo"});
//		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
//		System.out.println(jsonArray.toString());
//		System.out.println(list.toString());
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
//          使用fastjson将list转为json
//			JSONObject jsonObject=JSON.parseObject(list.toString());
//			jsonObject.remove("dict_item_code");
//			jsonObject.remove("dict_sort");
//			jsonObject.remove("dict_enable");
//			jsonObject.remove("dict_memo");
			ServletActionContext.getResponse().getWriter().println(JSON.toJSON(list).toString());
			//JSON.toJSON(list).toString()
		} catch (IOException e) {
			e.printStackTrace();
		}
     	return NONE;
	}
	public String findAll(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BaseDict.class);
		if(basic.getDict_type_code()!=null&&!"".equals(basic.getDict_type_code())){
			detachedCriteria.add(Restrictions.eq("dict_type_code)", basic.getDict_type_code()));
		}
		if(basic.getDict_item_name()!=null&&!"".equals(basic.getDict_item_name())){
			detachedCriteria.add(Restrictions.like("dict_item_name", "%"+basic.getDict_item_name()+"%"));
		}
		//调用业务层
		PageBean<BaseDict> pageBean=  baseDictService.findByPage(detachedCriteria,currentpage,pagesize);
		//存放到值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	public String edit(){
		basic = baseDictService.findById(basic.getDict_id());
		//1.手动压入值栈 <s:property value="cust_name"/>
		//2.模型驱动user本身就在值栈中<s:property value="model.cust_name"/>
		ActionContext.getContext().getValueStack().push(basic);
		return "editSuccess";
	}
	public String update(){
		baseDictService.update(basic);
		return "updateSuccess";
	}
	public String delete(){
		basic = baseDictService.findById(basic.getDict_id());
		baseDictService.delete(basic);
		return "deleteSuccess";
	}
}
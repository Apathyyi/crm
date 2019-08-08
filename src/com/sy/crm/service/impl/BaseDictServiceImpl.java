package com.sy.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.sy.crm.dao.BaseDictDao;
import com.sy.crm.domain.BaseDict;
import com.sy.crm.domain.PageBean;
import com.sy.crm.service.BaseDictService;
@Transactional
public class BaseDictServiceImpl implements BaseDictService{
	private BaseDictDao baseDictDao;
	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return baseDictDao.findByTypeCode(dict_type_code);
	}
	@Override
	public PageBean<BaseDict> findByPage(DetachedCriteria detachedCriteria, Integer currentpage, Integer pagesize) {
		PageBean<BaseDict> pageBean = new PageBean<BaseDict>();
		//封装当前页数
		pageBean.setCurrentpage(currentpage);
		//封装当前记录数
		pageBean.setPagesize(pagesize);
		//封装总记录数，调用DAO
		Integer totalcount = baseDictDao.findCount(detachedCriteria);
		pageBean.setTotalcount(totalcount);
		//封装总页数
	    Double tDouble = Math.ceil(totalcount.doubleValue()/pagesize);
		pageBean.setTotalpage(tDouble.intValue());
		//封装每页显示数据的集合
		Integer begin = (currentpage-1)*pagesize;
		List<BaseDict> list =baseDictDao.findByPage(detachedCriteria,begin,pagesize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public BaseDict findById(String dict_id) {
		return baseDictDao.findById(dict_id);
	}

	@Override
	public void update(BaseDict basic) {
		baseDictDao.update(basic);
		
	}
	@Override
	public void delete(BaseDict basic) {
		baseDictDao.delete(basic);
	}
}

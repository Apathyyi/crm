package com.sy.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import com.sy.crm.dao.SaleVisitDao;
import com.sy.crm.domain.PageBean;
import com.sy.crm.domain.SaleVisit;
import com.sy.crm.service.SaleVisitService;
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {
	@Resource(name="saleVisitDao")
	private SaleVisitDao saleVisitDao;
	@Override
	public PageBean<SaleVisit> findAll(DetachedCriteria detachedCriteria, Integer currentpage, Integer pagesize) {
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
		pageBean.setCurrentpage(currentpage);
		pageBean.setPagesize(pagesize);
		Integer totalcount = saleVisitDao.findCount(detachedCriteria);
		pageBean.setTotalcount(totalcount);
		Double tDouble = Math.ceil(totalcount.doubleValue()/pagesize);
		pageBean.setTotalpage(tDouble.intValue());
		Integer begin = (currentpage-1)*pagesize;
		List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, begin, pagesize);
		pageBean.setList(list);
		return pageBean;
	}
	@Override
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
	}
	@Override
	public SaleVisit findById(Long visit_id) {
		return saleVisitDao.findById(visit_id);
	}
	@Override
	public void update(SaleVisit saleVisit) {
		saleVisitDao.update(saleVisit);
	}
	@Override
	public void delete(SaleVisit saleVisit) {
		saleVisitDao.delete(saleVisit);
		
	}
}

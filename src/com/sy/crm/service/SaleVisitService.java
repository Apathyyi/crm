package com.sy.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.sy.crm.domain.PageBean;
import com.sy.crm.domain.SaleVisit;

public interface SaleVisitService {

	PageBean<SaleVisit> findAll(DetachedCriteria detachedCriteria, Integer currentpage, Integer pagesize);

	void save(SaleVisit saleVisit);

	SaleVisit findById(Long visit_id);

	void update(SaleVisit saleVisit);

	void delete(SaleVisit saleVisit);
}

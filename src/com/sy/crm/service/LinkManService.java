package com.sy.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.sy.crm.domain.LinkMan;
import com.sy.crm.domain.PageBean;

public interface LinkManService {

	PageBean<com.sy.crm.domain.LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer pagesize,Integer currentpage);

	void save(LinkMan linkMan);

	LinkMan findById(Long long1);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);
}

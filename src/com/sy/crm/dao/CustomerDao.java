package com.sy.crm.dao;

import java.sql.ResultSet;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.sun.javafx.collections.MappingChange.Map;
import com.sy.crm.domain.Customer;
import com.sy.crm.domain.SourceBean;

public interface CustomerDao extends BaseDao<Customer>{

	List<SourceBean> findCountBysource();
//	Integer findCount(DetachedCriteria detachedCriteria);
//
//	List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pagesize);
//
//	Customer findById(Long cust_id);
//
//	List<Customer> findAll();
}

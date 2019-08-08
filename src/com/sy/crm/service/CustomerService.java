package com.sy.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.sy.crm.domain.Customer;
import com.sy.crm.domain.PageBean;
import com.sy.crm.domain.SourceBean;

public interface CustomerService {

	void save(Customer customer);

	PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currentPage, Integer pagesize);

	Customer findById(Long cust_id);

	void delete(Customer customer);

	void update(Customer customer);

	List<Customer> findAll();

	List<SourceBean> findCountBysource( );
}

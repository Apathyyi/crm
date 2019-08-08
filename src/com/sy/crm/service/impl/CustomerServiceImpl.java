package com.sy.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.sy.crm.dao.CustomerDao;
import com.sy.crm.domain.Customer;
import com.sy.crm.domain.PageBean;
import com.sy.crm.domain.SourceBean;
import com.sy.crm.service.CustomerService;

@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}
	@Override
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currentpage,Integer pagesize) {
		PageBean<Customer> pageBean = new PageBean<Customer>();
		//��װ��ǰҳ��
		pageBean.setCurrentpage(currentpage);
		//��װ��ǰ��¼��
		pageBean.setPagesize(pagesize);
		//��װ�ܼ�¼��������DAO
		Integer totalcount = customerDao.findCount(detachedCriteria);
		pageBean.setTotalcount(totalcount);
		//��װ��ҳ��
	    Double tDouble = Math.ceil(totalcount.doubleValue()/pagesize);
		pageBean.setTotalpage(tDouble.intValue());
		//��װÿҳ��ʾ���ݵļ���
		Integer begin = (currentpage-1)*pagesize;
		List<Customer> list =customerDao.findByPage(detachedCriteria,begin,pagesize);
		pageBean.setList(list);
		return pageBean;
	}
	@Override
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}
	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}
	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}
	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
	@Override
	public List<SourceBean> findCountBysource() {
		System.out.println("����serviceimpl");
		List<SourceBean> list = customerDao.findCountBysource();
		return list;
	}
	
}

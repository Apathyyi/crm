package com.sy.crm.test;


import java.util.HashSet;
import java.util.List;

import com.sy.crm.dao.CustomerDao;
import com.sy.crm.dao.impl.CustomerDaoImpl;
import com.sy.crm.domain.Customer;
import com.sy.crm.domain.LinkMan;

public class Test {
	
	@org.junit.Test
	public void test(){
		Customer customer = new Customer();
		customer.setCust_name("王五");
		customer.setCust_id(1l);
		LinkMan linkMan1 = new LinkMan();
		LinkMan linkMan2 = new LinkMan();
		LinkMan linkMan3 = new LinkMan();
		linkMan1.setLkm_name("张一");
		linkMan1.setCustomer(customer);
		linkMan2.setLkm_name("张二");
		linkMan2.setCustomer(customer);
		linkMan3.setLkm_name("张三");
		linkMan3.setCustomer(customer);
		HashSet<LinkMan> hSet = new HashSet<LinkMan>();
		hSet.add(linkMan1);
		hSet.add(linkMan2);
		hSet.add(linkMan3);
		customer.setLinkMans(hSet);
		System.out.println(customer.toString());
	}
	@org.junit.Test
	public void t(){
		CustomerDao customerDao = new CustomerDaoImpl();
		List list = customerDao.findCountBysource();
		System.out.println(list.isEmpty());
	}
}

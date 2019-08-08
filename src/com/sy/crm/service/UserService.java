package com.sy.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.sy.crm.domain.PageBean;
import com.sy.crm.domain.User;

public interface UserService {

	public User login( User user);
	public void registe(User user) ;
	public List<User> findAll();
	public PageBean<User> findByPage(DetachedCriteria detachedCriteria, Integer currentpage, Integer pagesize);
	public User findById(Long user_id);
	public void update(User user);
	public void delete(User user);
}

package com.sy.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.sy.crm.Utils.MD5Utils;
import com.sy.crm.dao.UserDao;
import com.sy.crm.domain.PageBean;
import com.sy.crm.domain.User;
import com.sy.crm.service.UserService;

@Transactional

/*
 * @Service("userService")
 */
public class UserServiceImpl implements UserService {
	/*
	 * @Resource
	 */
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User login(User user ) {
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		return userDao.login(user);
	}

	@Override
	public void registe(User user) {
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_status("1");
		userDao.save(user);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public PageBean<User> findByPage(DetachedCriteria detachedCriteria, Integer currentpage, Integer pagesize) {
		System.out.println(currentpage);
		PageBean<User> pageBean = new PageBean<User>();
		//封装当前页数
		pageBean.setCurrentpage(currentpage);
		//封装当前记录数
		pageBean.setPagesize(pagesize);
		//封装总记录数，调用DAO
		Integer totalcount = userDao.findCount(detachedCriteria);
		pageBean.setTotalcount(totalcount);
		//封装总页数
	    Double tDouble = Math.ceil(totalcount.doubleValue()/pagesize);
		pageBean.setTotalpage(tDouble.intValue());
		//封装每页显示数据的集合
		Integer begin = (currentpage-1)*pagesize;
		List<User> list =userDao.findByPage(detachedCriteria,begin,pagesize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public User findById(Long user_id) {
		return userDao.findById(user_id);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		userDao.delete(user);
	}
	
}

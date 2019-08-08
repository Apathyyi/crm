package com.sy.crm.dao.impl;

import java.util.List;

import com.sy.crm.dao.UserDao;
import com.sy.crm.domain.User;

/*
 * @Repository(name="userDao")
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
//    public UserDaoImpl() {
//		super(User.class);
//	}
	@Override
	public User login(User user) {
		List<User> list =  (List<User>) this.getHibernateTemplate().find("from User where user_code=?0 and user_password=?1",user.getUser_code(),user.getUser_password());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
//	@Override
//	public void save(User user) {
//		// TODO Auto-generated method stub
//		
//	}
	
}

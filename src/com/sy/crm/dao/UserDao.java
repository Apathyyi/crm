package com.sy.crm.dao;

import com.sy.crm.domain.User;

public interface UserDao extends BaseDao<User>{
	User login(User user);
}

package com.sy.crm.dao.impl;

import java.util.List;

import com.sy.crm.dao.BaseDictDao;
import com.sy.crm.domain.BaseDict;

public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {
//
//	public BaseDictDaoImpl() {
//		super(BaseDict.class);
//	}

	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		List<BaseDict> list = (List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dict_type_code=?0", dict_type_code);
		return list;	
		}
	
}
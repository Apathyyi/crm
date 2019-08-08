package com.sy.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.sy.crm.domain.BaseDict;
import com.sy.crm.domain.PageBean;

public interface BaseDictService {

	List<BaseDict> findByTypeCode(String dict_type_code);

	PageBean<BaseDict> findByPage(DetachedCriteria detachedCriteria, Integer currentpage, Integer pagesize);

	BaseDict findById(String dict_id);

	void update(BaseDict basic);

	void delete(BaseDict basic);

}

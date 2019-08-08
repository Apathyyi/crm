package com.sy.crm.dao.impl;

import com.sy.crm.dao.LinkManDao;
import com.sy.crm.domain.LinkMan;

public class LinkManDaoImpl extends BaseDaoImpl<LinkMan> implements LinkManDao{

//	public LinkManDaoImpl() {
//		super(LinkMan.class);
//	}
//	@Override
//	public Integer findcount(DetachedCriteria detachedCriteria) {
//		detachedCriteria.setProjection(Projections.rowCount());
//		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
//		if(list.size()>0){
//			return list.get(0).intValue();
//		}
//		return null;
//	}
//	@Override
//	public List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pagesize) {
//		detachedCriteria.setProjection(null);
//		List<LinkMan> list =  (List<LinkMan>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin, pagesize);
//		return list;
//	}
//	@Override
//	public LinkMan findById(Long id) {
//		return this.getSessionFactory().getCurrentSession().find(LinkMan.class, id);
//	}
}

package com.sy.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.sy.crm.dao.LinkManDao;
import com.sy.crm.domain.LinkMan;
import com.sy.crm.domain.PageBean;
import com.sy.crm.service.LinkManService;

@Transactional
public class LinkManServiceImpl implements LinkManService{
	private LinkManDao linkManDao;
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}
	@Override
	public PageBean<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer currentpage , Integer pagesize) {
		PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
		pageBean.setPagesize(pagesize);
		pageBean.setCurrentpage(currentpage);
		Integer totalcount = linkManDao.findCount(detachedCriteria);
		System.out.println(totalcount);
		pageBean.setTotalcount(totalcount);
		//封装总页数
	    Double tDouble = Math.ceil(totalcount.doubleValue()/pagesize);
		pageBean.setTotalpage(tDouble.intValue());
		//封装每页显示数据的集合
		Integer begin = (currentpage-1)*pagesize;
		List<LinkMan> list = linkManDao.findByPage(detachedCriteria,begin,pagesize);
		System.out.println(list.toString());
		pageBean.setList(list);
		return pageBean;
	}
	@Override
	public void save(LinkMan linkMan) {
		linkManDao.save(linkMan);
	}
	@Override
	public LinkMan findById(Long id) {
		return linkManDao.findById(id);
	}
	@Override
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}
	@Override
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
		
	}
}

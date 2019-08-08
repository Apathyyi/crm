package com.sy.crm.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import com.sy.crm.dao.CustomerDao;
import com.sy.crm.domain.Customer;
import com.sy.crm.domain.SourceBean;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao{

	@Override
	public List<SourceBean> findCountBysource() {
		System.out.println("进入daoimpl");
		String sql = "SELECT b.dict_item_name ,COUNT(*) FROM t_customer a,base_dict b WHERE a.cust_source=b.dict_id GROUP BY b.dict_item_name";
		SQLQuery sqlQuery  =  this.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(SourceBean.class);
		System.out.println("query 接口");
		List<SourceBean> list = sqlQuery.list();
		return list;
	}
//	public CustomerDaoImpl() {
//		super(Customer.class);
//	}
//	@Override
//	public void save(Customer customer){
//		this.getSessionFactory().getCurrentSession().persist(customer);
//	}
//	@Override
//	public Integer findCount(DetachedCriteria detachedCriteria) {
//		System.out.println("进入DAO的findCount");
//		detachedCriteria.setProjection(Projections.rowCount());
//		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
//		if(list.size()>0){
//			return list.get(0).intValue();
//		}
//		 return null;
//	}
//	@Override
//	public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pagesize) {
//		System.out.println("进入DAo的findBypage");
//		detachedCriteria.setProjection(null);
//        List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pagesize);
//        //System.out.println(list.toString());
//        return list;
//	}
//	@Override
//	public Customer findById(Long cust_id) {
//		return this.getSessionFactory().getCurrentSession().find(Customer.class, cust_id);
//		//return this.getHibernateTemplate().get(Customer.class, cust_id);
//	}
//
//	@Override
//	public List<Customer> findAll() {
//		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
//	}
}

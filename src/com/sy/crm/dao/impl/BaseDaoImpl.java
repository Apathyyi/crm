package com.sy.crm.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.sy.crm.dao.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
	//2.泛型反射抽取得到具体类型
	//得到子类继承时候的实际参数类型,获得子类对象的class,再得到该子类上的实际参数类型
	//Type[] 
	private Class clazz;
	public BaseDaoImpl(){
		//1得到子类的class
		Class calz = this.getClass();
		//得到子类上的参数化类型，<Cusertomer>/<LinkMan>
		Type type = calz.getGenericSuperclass();
		//将type强转成参数化的类型
		ParameterizedType pType = (ParameterizedType) type;
		//得到实际类型参数数组？map<String,String>
		Type[] types = pType.getActualTypeArguments();
		//得到type
		 this.clazz= (Class) types[0];
	}
	//1提供构造方法得到具体类型
//	private Class clazz;
//	public BaseDaoImpl(Class clazz) {
//		this.clazz = clazz;
//	}
	//增加
	@Override
	public void save(T t) {
			this.getSessionFactory().getCurrentSession().persist(t);
	}
	//删除
	@Override
	public void delete(T t) {
		this.getSessionFactory().getCurrentSession().delete(t);
	}
	//修改
	@Override
	public void update(T t) {
		this.getSessionFactory().getCurrentSession().update(t);
	}
	//查询
	@Override
	public T findById(Serializable id) {
		return (T) this.getSessionFactory().getCurrentSession().get(clazz, id);
	}
	//查询所有
	@Override
	public List<T> findAll() {	
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}
	//统计个数
	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size()>0){
			return list.get(0).intValue();
		}
		return null ;
	}
	//分页查询
	@Override
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer beginn, Integer pagesize) {
		detachedCriteria.setProjection(null);
		return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria,beginn,pagesize);
	}

}

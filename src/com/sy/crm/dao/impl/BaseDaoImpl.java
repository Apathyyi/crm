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
	//2.���ͷ����ȡ�õ���������
	//�õ�����̳�ʱ���ʵ�ʲ�������,�����������class,�ٵõ��������ϵ�ʵ�ʲ�������
	//Type[] 
	private Class clazz;
	public BaseDaoImpl(){
		//1�õ������class
		Class calz = this.getClass();
		//�õ������ϵĲ��������ͣ�<Cusertomer>/<LinkMan>
		Type type = calz.getGenericSuperclass();
		//��typeǿת�ɲ�����������
		ParameterizedType pType = (ParameterizedType) type;
		//�õ�ʵ�����Ͳ������飿map<String,String>
		Type[] types = pType.getActualTypeArguments();
		//�õ�type
		 this.clazz= (Class) types[0];
	}
	//1�ṩ���췽���õ���������
//	private Class clazz;
//	public BaseDaoImpl(Class clazz) {
//		this.clazz = clazz;
//	}
	//����
	@Override
	public void save(T t) {
			this.getSessionFactory().getCurrentSession().persist(t);
	}
	//ɾ��
	@Override
	public void delete(T t) {
		this.getSessionFactory().getCurrentSession().delete(t);
	}
	//�޸�
	@Override
	public void update(T t) {
		this.getSessionFactory().getCurrentSession().update(t);
	}
	//��ѯ
	@Override
	public T findById(Serializable id) {
		return (T) this.getSessionFactory().getCurrentSession().get(clazz, id);
	}
	//��ѯ����
	@Override
	public List<T> findAll() {	
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}
	//ͳ�Ƹ���
	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size()>0){
			return list.get(0).intValue();
		}
		return null ;
	}
	//��ҳ��ѯ
	@Override
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer beginn, Integer pagesize) {
		detachedCriteria.setProjection(null);
		return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria,beginn,pagesize);
	}

}

package com.rowdyruff.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public abstract class AbstractHibernateDao<T extends Serializable> {
	
	protected Class<T> clazz;
	  
    @Autowired
    protected SessionFactory sessionFactory;
    
    protected CriteriaBuilder cb;
  
    public void setClazz(Class< T > clazzToSet){
       this.clazz = clazzToSet; 
    }
  
    public T findOne(long id){
      return (T) getCurrentSession().get(clazz, id);
    }
 
    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }
 
    public T create(T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }
 
    public T update(T entity) {
        return (T) getCurrentSession().merge(entity);
    }
 
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }
 
    public void deleteById(long entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }
 
    protected Session getCurrentSession() {
    	Session session = null; 
    	try {
    		session = sessionFactory.getCurrentSession();
    	} catch (HibernateException ex) {
    		session = sessionFactory.openSession();
    	}
    	
        return session;
    }
}

package com.rowdyruff.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

@Transactional
public abstract class AbstractHibernateDao<T extends Serializable> {
	
	protected Class<T> clazz;
	  
	@PersistenceContext
	protected EntityManager entityManager;
    
    protected CriteriaBuilder cb;
  
    public void setClazz(Class< T > clazzToSet){
       this.clazz = clazzToSet; 
    }
  
    public T findOne(long id){
      return entityManager.find(clazz, id);
    }
 
    @SuppressWarnings("unchecked")
	public List<T> findAll() {
    	return entityManager.createQuery( "from " + clazz.getName() )
    		       .getResultList();
    }
 
    public T create(T entity) {
    	entityManager.persist(entity);
        return entity;
    }
 
    public T update(T entity) {
    	return entityManager.merge(entity);
    }
 
    public void delete(T entity) {
    	entityManager.remove(entity);
    }
 
    public void deleteById(long entityId) {
    	T entity = findOne(entityId);
        delete(entity);
    }
}

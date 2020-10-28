package com.rowdyruff.repository.hibernate;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.rowdyruff.repository.AbstractHibernateDao;
import com.rowdyruff.repository.GenericRepository;

@Repository
public class GenericRepositoryImpl <T extends Serializable> extends AbstractHibernateDao<T>
	implements GenericRepository<T> {

}

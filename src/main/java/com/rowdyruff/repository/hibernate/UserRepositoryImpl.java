package com.rowdyruff.repository.hibernate;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.domain.User;
import com.rowdyruff.repository.UserRepository;

@Repository
@Transactional(readOnly = false)
public class UserRepositoryImpl extends GenericRepositoryImpl<User> implements UserRepository {
	
	public UserRepositoryImpl() {
		super.setClazz(User.class);
	}

	@Override
	public User findByUsername(String username) {
		if (cb == null)
			cb = sessionFactory.getCriteriaBuilder();
		CriteriaQuery<User> crit = cb.createQuery(clazz);
		Root<User> root = crit.from(clazz);
		crit.where(cb.equal(root.get("username"), username));
		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(crit);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

//	public User getByPhoneNumber(String phoneNumber) {
//		if (cb == null)
//			cb = sessionFactory.getCriteriaBuilder();
//		CriteriaQuery<User> crit = cb.createQuery(clazz);
//		Root<User> root = crit.from(clazz);
//		crit.where(cb.equal(root.get("phoneNumber"), phoneNumber));
//		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(crit);
//		try {
//			return query.getSingleResult();
//		} catch (NoResultException e) {
//			return null;
//		}
//	}
}

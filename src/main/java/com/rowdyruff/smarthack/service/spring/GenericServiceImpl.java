package com.rowdyruff.smarthack.service.spring;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.repository.GenericRepository;
import com.rowdyruff.smarthack.service.GenericService;

@Transactional
public abstract class GenericServiceImpl<T extends Serializable> implements GenericService<T>{

	protected volatile GenericRepository<T> genericRepository;
	
	public T getItem(final long id) {
		return genericRepository.findOne(id);
	}

	public List<T> getItems() {
		return genericRepository.findAll();
	}

	public T create(T entity) {
		return genericRepository.create(entity);
	}

	public T update(T entity) {
		return genericRepository.update(entity);
	}

	public void delete(T entity) {
		genericRepository.delete(entity);
	}

	public void deleteById(long entityId) {
		genericRepository.deleteById(entityId);
	}
	
	public void setRepository(GenericRepository<T> repository) {
		this.genericRepository = repository;
	}
	
}

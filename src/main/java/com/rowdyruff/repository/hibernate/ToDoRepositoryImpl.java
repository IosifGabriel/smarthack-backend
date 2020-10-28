package com.rowdyruff.repository.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.domain.ToDo;
import com.rowdyruff.repository.ToDoRepository;

@Repository
@Transactional(readOnly = false)
public class ToDoRepositoryImpl extends GenericRepositoryImpl<ToDo> implements ToDoRepository {
	
	public ToDoRepositoryImpl() {
		super.setClazz(ToDo.class);
	}

}

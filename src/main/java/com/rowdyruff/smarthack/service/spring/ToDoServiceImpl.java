package com.rowdyruff.smarthack.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.domain.ToDo;
import com.rowdyruff.repository.ToDoRepository;
import com.rowdyruff.smarthack.service.ToDoService;

@Service
@Transactional
public class ToDoServiceImpl extends GenericServiceImpl<ToDo> implements ToDoService {
	
	ToDoRepository toDoRepository;

	@Autowired
	public ToDoServiceImpl(ToDoRepository toDoRepository) {
		super.setRepository(toDoRepository);
		this.toDoRepository = toDoRepository;
	}
	
}

package com.rowdyruff.smarthack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rowdyruff.domain.ToDo;
import com.rowdyruff.smarthack.service.ToDoService;

@RestController
@RequestMapping("/todos")
public class ToDoController extends GenericController<ToDo> {

	private static final long serialVersionUID = -3557705151780494494L;
	
	@Autowired
	ToDoService toDoService;
	
	public ToDoController(ToDoService toDoService) {
		super(toDoService);
	}

	@Override
	protected ToDo getEmptyItem() {
		return new ToDo();
	}

	@Override
	protected Boolean isNew(ToDo item) {
		return (item.getId() == null) || item.getId() < 1;
	}
	
	@PostMapping
	public ResponseEntity<?> addTodo(@RequestBody String title) {
		return ResponseEntity.ok(saveItem(new ToDo(title)));
	}
}

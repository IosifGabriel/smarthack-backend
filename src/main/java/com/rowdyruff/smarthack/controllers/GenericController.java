package com.rowdyruff.smarthack.controllers;


import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rowdyruff.smarthack.service.GenericService;

public abstract class GenericController<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = -4903440076552953219L;

    protected GenericService<T> service;

    public GenericController(GenericService<T> service) {
        this.service = service;
    }

    public List<T> listItems() {
        List<T> items = null;
        try {
            items = service.getItems();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return items;
    }
    
    @GetMapping
	@ResponseBody
	public ResponseEntity<?> getItems() {
    	List<T> items = null;
    	try {
    		items = listItems();
    	} catch (Exception ex) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex);
    	}
    
		return ResponseEntity.ok(items);
	}
    
    @GetMapping(value = "/{id}")
	public ResponseEntity<?> getItem(@PathVariable("id") Integer id) {
    	T item = null;
    	try {
    		item = service.getItem(id);
    	} catch (NoResultException ex) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    	}
    
		return ResponseEntity.ok(item);
	}
    
    @DeleteMapping
	public ResponseEntity<?> delete(@RequestBody Integer id) {
    	String msg = null;
		try {
			msg = deleteItem(id);
			return ResponseEntity.ok(msg);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
		}
	}

    public void editItem(Integer id) {
        T item = null;

        try {
            if (id != null) {
                item = service.getItem(id);
            } else {
                item = getEmptyItem();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String saveItem(T item) {
        Boolean isNew = isNew(item);

        try {
        	if (isNew)
        		service.create(item);
        	else
        		service.update(item);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Obiectul de tipul " + item.getClass().getName() + " nu a putut fi salvat.";
        }

        return "Obiectul de tipul " + item.getClass().getName() + " a fost salvat.";
    }

    public String deleteItem(Integer id) {
        T item = service.getItem(id);
        try {
            service.delete(item);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "There was a problem deleting the item with id " + id + " of class " + item.getClass().getName();
        }

        return "Item with id " + id + " of class " + item.getClass().getName() + " was deleted.";
    }

    protected abstract T getEmptyItem();

    protected abstract Boolean isNew(T item);
}
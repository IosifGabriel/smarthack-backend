package com.rowdyruff.smarthack.service;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T extends Serializable> {
	
	/**
	 * Returns the persisted item with the given id.
	 * @param id
	 * @return
	 */
	public T getItem(final long id);
	
	/**
	 * Returns all of the items of class T.
	 * @return
	 */
	List<T> getItems();
	
	/**
	 * Creates a new persisted entity. 
	 * @param entity
	 * @return
	 */
    T create(final T entity);
 
    /**
     * Updates the existing object with the one given as parameter.
     * @param entity
     * @return
     */
    T update(final T entity);
 
    /**
     * Deletes the given item.
     * @param entity
     */
    void delete(final T entity);
 
    /**
     * Deletes the item with the given id.
     * @param entityId
     */
    void deleteById(final long entityId);

}

package com.benscribner.beercraft.dao;

import java.util.List;

import com.benscribner.beercraft.model.Identifiable;

/**
 * A generic DAO to handle the persistence of Identifiable objects
 */
public interface IdentifiableDao<T extends Identifiable> {

    /**
     * Get all objects of type T
     * 
     * @return a list of all objects of type T
     */
    List<T> getAll();

    /**
     * Get the object specified by the given id
     * 
     * @param id
     *            the identifier of the object to get
     * 
     * @return the object requested
     */
    T get(String id);

    /**
     * Checks to see if an object exists
     * 
     * @param id
     *            the identifier of the entity
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(String id);

    /**
     * Save an object
     * 
     * @param object
     *            the object to save
     * @return the persisted object
     */
    T save(T object);

    /**
     * Delete an object based on class and id
     * 
     * @param id
     *            the identifier of the object to delete
     */
    void delete(String id);

    /**
     * Delete an object
     * 
     * @param object
     *            the object to delete
     */
    void delete(T object);
}
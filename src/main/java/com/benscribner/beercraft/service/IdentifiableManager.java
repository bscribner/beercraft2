package com.benscribner.beercraft.service;

import java.util.List;

import com.benscribner.beercraft.dao.IdentifiableDao;
import com.benscribner.beercraft.model.Identifiable;

/**
 * Manager class that talks to {@link IdentifiableDao}.
 */
public interface IdentifiableManager<T extends Identifiable> {

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
    void remove(String id);

    /**
     * Delete an object
     * 
     * @param object
     *            the object to delete
     */
    void remove(T object);
}
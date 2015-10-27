package com.benscribner.beercraft.dao;

import com.benscribner.beercraft.model.Identifiable;
import com.benscribner.beercraft.model.Named;

/**
 * DAO for persisting Named objects
 */
public interface NamedObjectDao<T extends Identifiable & Named> extends IdentifiableDao<T> {

    /**
     * Get a Named object by name
     * 
     * @param name
     *            the name of the object to find
     * @return populated object
     */
    T getByName(String name);
}
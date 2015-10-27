package com.benscribner.beercraft.service;

import com.benscribner.beercraft.dao.NamedObjectDao;
import com.benscribner.beercraft.model.Identifiable;
import com.benscribner.beercraft.model.Named;

/**
 * Manager class that talks to {@link NamedObjectDao}.
 */
public interface NamedObjectManager<T extends Identifiable & Named> extends IdentifiableManager<T> {

    /**
     * Get a Named object by name
     * 
     * @param name
     *            the name of the object to find
     * @return populated object
     */
    T getByName(String name);
}

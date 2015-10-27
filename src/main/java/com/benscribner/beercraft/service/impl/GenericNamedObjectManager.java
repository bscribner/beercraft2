package com.benscribner.beercraft.service.impl;

import com.benscribner.beercraft.dao.NamedObjectDao;
import com.benscribner.beercraft.model.Identifiable;
import com.benscribner.beercraft.model.Named;
import com.benscribner.beercraft.service.NamedObjectManager;

/**
 * @see com.benscribner.beercraft.service.NamedObjectManager
 */
public class GenericNamedObjectManager<T extends Identifiable & Named, DAO extends NamedObjectDao<T>> extends GenericIdentifiableManager<T, DAO>
        implements NamedObjectManager<T> {
    
    /**
     * Default constructor
     */
    public GenericNamedObjectManager() {
        
    }

    /**
     * Constructor that sets the dao
     * 
     * @param dao
     *            the dao to set
     */
    public GenericNamedObjectManager(final DAO dao) {
        super(dao);
    }

    /**
     * @see com.benscribner.beercraft.service.NamedObjectManager#getByName(java.lang.String)
     */
    @Override
    public T getByName(final String name) {
        return dao.getByName(name);
    }
}
package com.benscribner.beercraft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benscribner.beercraft.dao.NamedObjectDao;
import com.benscribner.beercraft.model.Brewery;

/**
 * Manager class that talks to {@link NamedObjectDao} to manage {@link Brewery}
 * objects.
 */
@Service("breweryManager")
public class BreweryManager extends GenericNamedObjectManager<Brewery, NamedObjectDao<Brewery>> {
    
    /**
     * Default constructor
     */
    public BreweryManager() {
        super();
    }

    /**
     * Constructor that sets the breweryDao
     * 
     * @param beerDao
     *            the breweryDao to set
     */
    @Autowired
    public BreweryManager(NamedObjectDao<Brewery> breweryDao) {
        super(breweryDao);
    }
}
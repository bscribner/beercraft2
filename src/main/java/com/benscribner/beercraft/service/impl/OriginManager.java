package com.benscribner.beercraft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benscribner.beercraft.dao.NamedObjectDao;
import com.benscribner.beercraft.model.Origin;

/**
 * Manager class that talks to {@link NamedObjectDao} to manage {@link Origin}
 * objects.
 */
@Service("originManager")
public class OriginManager extends GenericNamedObjectManager<Origin, NamedObjectDao<Origin>> {
    
    /**
     * Default constructor
     */
    public OriginManager() {
        super();
    }

    /**
     * Constructor that sets the originDao
     * 
     * @param originDao
     *            the originDao to set
     */
    @Autowired
    public OriginManager(NamedObjectDao<Origin> originDao) {
        super(originDao);
    }
}
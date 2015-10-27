package com.benscribner.beercraft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benscribner.beercraft.dao.NamedObjectDao;
import com.benscribner.beercraft.model.Role;

/**
 * Manager class that talks to {@link NamedObjectDao} to manage {@link Role}
 * objects.
 */
@Service("roleManager")
public class RoleManager extends GenericNamedObjectManager<Role, NamedObjectDao<Role>> {
    
    /**
     * Default constructor
     */
    public RoleManager() {
        super();
    }

    /**
     * Constructor that sets the roleDao
     * 
     * @param roleDao
     *            the roleDao to set
     */
    @Autowired
    public RoleManager(NamedObjectDao<Role> roleDao) {
        super(roleDao);
    }
}
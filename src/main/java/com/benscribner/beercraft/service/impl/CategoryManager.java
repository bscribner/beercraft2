package com.benscribner.beercraft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benscribner.beercraft.dao.NamedObjectDao;
import com.benscribner.beercraft.model.Category;

/**
 * Manager class that talks to {@link NamedObjectDao} to manage {@link Category}
 * objects.
 */
@Service("categoryManager")
public class CategoryManager extends GenericNamedObjectManager<Category, NamedObjectDao<Category>> {
    
    /**
     * Default constructor
     */
    public CategoryManager() {
        super();
    }

    /**
     * Constructor that sets the categoryDao
     * 
     * @param categoryDao
     *            the categoryDao to set
     */
    @Autowired
    public CategoryManager(NamedObjectDao<Category> categoryDao) {
        super(categoryDao);
    }
}
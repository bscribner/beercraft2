package com.benscribner.beercraft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benscribner.beercraft.dao.IdentifiableDao;
import com.benscribner.beercraft.model.LineItem;

/**
 * Manager class that talks to {@link IdentifiableDao} to manage {@link LineItem}
 * objects.
 */
@Service("lineItemManager")
public class LineItemManager extends GenericIdentifiableManager<LineItem, IdentifiableDao<LineItem>> {

    /**
     * Default constructor
     */
    public LineItemManager() {

    }

    /**
     * Constructor that sets the dao
     * 
     * @param lineItemDao
     *            the cart dao to set
     */
    @Autowired
    public LineItemManager(final IdentifiableDao<LineItem> lineItemDao) {
        super(lineItemDao);
    }
}
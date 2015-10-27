package com.benscribner.beercraft.model.impl;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.benscribner.beercraft.model.Category;

/**
 *  File:       CategoryImpl
 *  Project:    BeerCraft
 *  Author:     @author Kristin Caldwell
 *  Version:    @version 1.0
 *  Date:
 *  Purpose:    @see com.benscribner.beercraft.model.Category
 */
@Entity
@Table(name = "categories")
public class CategoryImpl extends AbstractNamedObject implements Category {
    private static final long serialVersionUID = 139857350864493555L;

    /**
     * Default constructor
     */
    public CategoryImpl() {

    }

    /**
     * Create a new instance and set the name and category
     * 
     * @param category
     *            The category of the beer
     * 
     */
    public CategoryImpl(final String category, final String description) {
        setName(category);
        setDescription(description);
    }
}
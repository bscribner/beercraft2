package com.benscribner.beercraft.model.impl;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.benscribner.beercraft.model.Origin;

/**
 *  File:       OriginImpl
 *  Project:    BeerCraft
 *  Author:     @author Kristin Caldwell
 *  Version:    @version 1.0
 *  Date:
 *  Purpose:    @see com.benscribner.beercraft.model.Origin
 */
@Entity
@Table(name = "origins")
public class OriginImpl extends AbstractNamedObject implements Origin {
    private static final long serialVersionUID = 3236474926948517622L;

    /**
     * Default constructor
     */
    public OriginImpl() {

    }

    /**
     * Create a new instance and set the name and origin
     * 
     * @param origin
     *            The origin of the beer
     */
    public OriginImpl(final String origin, final String description) {
        setName(origin);
        setDescription(description);
    }
}
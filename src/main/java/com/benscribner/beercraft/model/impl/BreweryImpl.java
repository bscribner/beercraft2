package com.benscribner.beercraft.model.impl;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.benscribner.beercraft.model.Brewery;

/**
 *  File:       BreweryImpl
 *  Project:    BeerCraft
 *  Author:     @author Kristin Caldwell
 *  Version:    @version 1.0
 *  Date:
 *  Purpose:    @see com.benscribner.beercraft.model.Brewery
 */
@Entity
@Table(name = "breweries")
public class BreweryImpl extends AbstractNamedObject implements Brewery {
    private static final long serialVersionUID = 1657692334111966597L;

    /**
     * Default constructor
     */
    public BreweryImpl() {

    }

    /**
     * Create a new instance and set the name and brewery
     * 
     * @param brewery
     *            The brewery of the beer
     */
    public BreweryImpl(final String brewery, final String description) {
        setName(brewery);
        setDescription(description);
    }
}
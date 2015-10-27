package com.benscribner.beercraft.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.benscribner.beercraft.model.Brewery;
import com.benscribner.beercraft.model.impl.BreweryImpl;

/**
 * A DAO to persist Breweries in Hibernate
 */
@Repository("breweryDao")
public class BreweryDaoHibernate extends GenericNamedDaoHibernate<Brewery> {

    /**
     * Constructor
     */
    public BreweryDaoHibernate() {
        super(BreweryImpl.class);
    }
}
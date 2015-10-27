package com.benscribner.beercraft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benscribner.beercraft.dao.BeerDao;
import com.benscribner.beercraft.dao.NamedObjectDao;
import com.benscribner.beercraft.model.Beer;
import com.benscribner.beercraft.model.Brewery;
import com.benscribner.beercraft.model.Category;
import com.benscribner.beercraft.model.Origin;
import com.benscribner.beercraft.service.BeerManager;

/**
 * Manager class that talks to {@link NamedObjectDao} to manage {@link Beer}
 * objects.
 */
@Service("beerManager")
public class BeerManagerImpl extends GenericNamedObjectManager<Beer, BeerDao> implements BeerManager {

    /**
     * Default constructor
     */
    public BeerManagerImpl() {
        super();
    }

    /**
     * Constructor that sets the beerDao
     * 
     * @param beerDao
     *            the beerDao to set
     */
    @Autowired
    public BeerManagerImpl(BeerDao beerDao) {
        super(beerDao);
    }

    /**
     * @see com.benscribner.beercraft.service.BeerManager#getBeerByCategory(com.benscribner.beercraft.model.Category)
     */
    @Override
    public List<Beer> getBeerByCategory(Category category) {
        return dao.getBeerByCategory(category);
    }

    /**
     * @see com.benscribner.beercraft.service.BeerManager#getBeerByOrigin(com.benscribner.beercraft.model.Origin)
     */
    @Override
    public List<Beer> getBeerByOrigin(Origin origin) {
        return dao.getBeerByOrigin(origin);
    }

    /**
     * @see com.benscribner.beercraft.service.BeerManager#getBeerByBrewery(com.benscribner.beercraft.model.Brewery)
     */
    @Override
    public List<Beer> getBeerByBrewery(Brewery brewery) {
        return dao.getBeerByBrewery(brewery);
    }
}
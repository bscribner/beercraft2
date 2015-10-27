package com.benscribner.beercraft.service;

import java.util.List;

import com.benscribner.beercraft.dao.BeerDao;
import com.benscribner.beercraft.model.Beer;
import com.benscribner.beercraft.model.Brewery;
import com.benscribner.beercraft.model.Category;
import com.benscribner.beercraft.model.Origin;

/**
 * Manager class that talks to {@link BeerDao}.
 */
public interface BeerManager extends NamedObjectManager<Beer> {

    /**
     * Gets Beer based on category
     * 
     * @param category
     *            the category to search by
     * @return a list of Beer
     */
    List<Beer> getBeerByCategory(Category category);

    /**
     * Gets Beer based on origin
     * 
     * @param origin
     *            the origin to search by
     * @return a list of Beer
     */
    List<Beer> getBeerByOrigin(Origin origin);

    /**
     * Gets Beer based on brewery
     * 
     * @param brewery
     *            the brewery to search by
     * @return a list of Beer
     */
    List<Beer> getBeerByBrewery(Brewery brewery);
}

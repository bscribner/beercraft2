package com.benscribner.beercraft.service;

import com.benscribner.beercraft.dao.CartDao;
import com.benscribner.beercraft.model.Cart;
import com.benscribner.beercraft.model.Person;

/**
 * Manager class that talks to {@link CartDao}.
 */
public interface CartManager extends IdentifiableManager<Cart> {

    /**
     * Gets a Cart based on a person
     * 
     * @param person
     *            the person to search by
     * @return a cart
     */
    Cart getForPerson(Person person);
}
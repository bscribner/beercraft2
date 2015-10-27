package com.benscribner.beercraft.dao;

import com.benscribner.beercraft.model.Cart;
import com.benscribner.beercraft.model.Person;

/**
 * DAO for persisting a Cart object
 */
public interface CartDao extends IdentifiableDao<Cart> {

    /**
     * Gets a cart for a person
     * 
     * @param person
     *            the person to search by
     * @return a cart
     */
    Cart getForPerson(Person person);
}
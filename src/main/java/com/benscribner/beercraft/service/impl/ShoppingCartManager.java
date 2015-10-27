package com.benscribner.beercraft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benscribner.beercraft.dao.CartDao;
import com.benscribner.beercraft.dao.IdentifiableDao;
import com.benscribner.beercraft.model.Cart;
import com.benscribner.beercraft.model.Person;
import com.benscribner.beercraft.service.CartManager;

/**
 * Manager class that talks to {@link IdentifiableDao} to manage {@link Cart}
 * objects.
 */
@Service("cartManager")
public class ShoppingCartManager extends GenericIdentifiableManager<Cart, CartDao> implements CartManager {

    /**
     * Default constructor
     */
    public ShoppingCartManager() {

    }

    /**
     * Constructor that sets the dao
     * 
     * @param cartDao
     *            the cart dao to set
     */
    @Autowired
    public ShoppingCartManager(final CartDao cartDao) {
        super(cartDao);
    }

    /**
     * @see com.benscribner.beercraft.service.CartManager#getForPerson(com.benscribner.beercraft.model.Person)
     */
    @Override
    public Cart getForPerson(final Person person) {
        return dao.getForPerson(person);
    }
}
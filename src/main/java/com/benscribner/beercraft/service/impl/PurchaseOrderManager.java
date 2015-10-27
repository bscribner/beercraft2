package com.benscribner.beercraft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benscribner.beercraft.dao.IdentifiableDao;
import com.benscribner.beercraft.dao.OrderDao;
import com.benscribner.beercraft.model.Order;
import com.benscribner.beercraft.model.Person;
import com.benscribner.beercraft.service.OrderManager;

/**
 * Manager class that talks to {@link IdentifiableDao} to manage {@link Order}
 * objects.
 */
@Service("orderManager")
public class PurchaseOrderManager extends GenericIdentifiableManager<Order, OrderDao> implements OrderManager {

    /**
     * Default constructor
     */
    public PurchaseOrderManager() {

    }

    /**
     * Constructor that sets the dao
     * 
     * @param orderDao
     *            the order dao to set
     */
    @Autowired
    public PurchaseOrderManager(final OrderDao orderDao) {
        super(orderDao);
    }

    /**
     * @see com.benscribner.beercraft.service.OrderManager#getForPerson(com.benscribner.beercraft.model.Person)
     */
    @Override
    public List<Order> getForPerson(final Person person) {
        return dao.getForPerson(person);
    }
}
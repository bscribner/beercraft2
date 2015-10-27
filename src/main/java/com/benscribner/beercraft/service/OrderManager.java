package com.benscribner.beercraft.service;

import java.util.List;

import com.benscribner.beercraft.dao.OrderDao;
import com.benscribner.beercraft.model.Order;
import com.benscribner.beercraft.model.Person;

/**
 * Manager class that talks to {@link OrderDao}.
 */
public interface OrderManager extends IdentifiableManager<Order> {

    /**
     * Gets Orders based on a person
     * 
     * @param person
     *            the person to search by
     * @return a list of Orders
     */
    List<Order> getForPerson(Person person);
}
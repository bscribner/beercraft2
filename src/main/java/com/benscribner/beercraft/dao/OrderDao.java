package com.benscribner.beercraft.dao;

import java.util.List;

import com.benscribner.beercraft.model.Order;
import com.benscribner.beercraft.model.Person;

/**
 * DAO for persisting a Order object
 */
public interface OrderDao extends IdentifiableDao<Order> {

    /**
     * Gets orders for a person
     * 
     * @param person
     *            the person to search by
     * @return a list of orders
     */
    List<Order> getForPerson(Person person);
}
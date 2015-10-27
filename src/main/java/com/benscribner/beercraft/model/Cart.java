package com.benscribner.beercraft.model;

import java.util.List;

/**
 * A class to represent a Shopping Cart
 */
public interface Cart extends Identifiable {

    /**
     * The owner of the cart
     * 
     * @return the owner of the cart
     */
    Person getPerson();

    /**
     * The items in the cart
     * 
     * @return a List of items in the cart
     */
    List<LineItem> getLineItems();
    
    /**
     * The total cost of the items in the cart
     * 
     * @return the total cost of the items in the cart
     */
    Double getTotalCost();
}
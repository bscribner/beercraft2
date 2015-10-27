package com.benscribner.beercraft.model;

import java.util.List;

/**
 * A class to represent an order
 */
public interface Order extends Identifiable {

    /**
     * The order number
     * 
     * @return the order number
     */
    Long getOrderNumber();

    /**
     * The owner of the order
     * 
     * @return the owner of the order
     */
    Person getPerson();

    /**
     * The shipping address
     * 
     * @return the shipping address
     */
    Address getShipping();

    /**
     * The credit card
     * 
     * @return the credit card
     */
    CreditCard getCardNumber();

    /**
     * The items in the order
     * 
     * @return a List of items in the order
     */
    List<LineItem> getLineItems();
    
    /**
     * The total cost of the order
     * 
     * @return the total cost of the order
     */
    Double getTotalCost();
}
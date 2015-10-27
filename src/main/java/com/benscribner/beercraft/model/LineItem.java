package com.benscribner.beercraft.model;

/**
 * A class to represent an item in the Shopping Cart
 */
public interface LineItem extends Identifiable {

    /**
     * The Beer added to the cart
     * 
     * @return the beer
     */
    Beer getBeer();

    /**
     * The quantity of beer
     * 
     * @return the quantity of beer
     */
    Integer getQuantity();
    
    /**
     * The total cost of the line item
     * 
     * @return the total cost of the line item
     */
    Double getTotalCost();
}
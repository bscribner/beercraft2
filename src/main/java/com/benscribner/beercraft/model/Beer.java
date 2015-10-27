package com.benscribner.beercraft.model;

/**
 * A class to represent Beer
 */
public interface Beer extends Identifiable, Named {

    /**
     * The Icon
     * 
     * @return the icon
     */
    String getIcon();

    /**
     * The ABV
     * 
     * @return The ABV
     */
    Double getAlcoholContent();

    /**
     * The Price
     * 
     * @return the price
     */
    Double getPrice();

    /**
     * The Brewery
     * 
     * @return The Brewery
     */
    Brewery getBrewery();

    /**
     * The origin
     * 
     * @return the origin
     */
    Origin getOrigin();

    /**
     * The category
     * 
     * @return the category
     */
    Category getCategory();
}
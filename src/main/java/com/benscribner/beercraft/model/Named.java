package com.benscribner.beercraft.model;

/**
 * An object with a name and description
 */
public interface Named {

    /**
     * The name
     * 
     * @return the name
     */
    String getName();

    /**
     * The description
     * 
     * @return the description
     */
    String getDescription();
}
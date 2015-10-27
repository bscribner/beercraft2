package com.benscribner.beercraft.model;

import java.io.Serializable;

/**
 * An object with an identifier
 */
public interface Identifiable extends Serializable {

    /**
     * The id of the object
     * 
     * @return the id
     */
    String getId();
}
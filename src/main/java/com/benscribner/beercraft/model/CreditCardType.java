package com.benscribner.beercraft.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Credit card type
 */
public enum CreditCardType {
    AMERICAN_EXPRESS("American Express"),
    DISCOVER("Discover"),
    MASTER_CARD("Master Card"),
    VISA("Visa");

    private static final Map<String, CreditCardType> lookup = new HashMap<String, CreditCardType>();

    /**
     * Add the types to the reverse lookup
     */
    static {
        for (final CreditCardType type : EnumSet.allOf(CreditCardType.class)) {
            lookup.put(type.getName(), type);
        }
    }

    private String name;

    /**
     * Constructor which sets the type name
     * 
     * @param name the name of the type
     */
    private CreditCardType(final String name) {
        this.name = name;
    }

    /**
     * Retrieve the card type by name
     * 
     * @param name the name of the type
     * @return the card type
     */
    public static CreditCardType get(final String name) {
        return lookup.get(name);
    }

    /**
     * Retrieve the name of the type
     * 
     * @return the name of the type
     */
    public String getName() {
        return name;
    }
}
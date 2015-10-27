package com.benscribner.beercraft.model;

import java.util.Date;

/**
 * A class to represent a Credit Card
 */
public interface CreditCard extends Identifiable {

    /**
     * The expiration date
     * 
     * @return the expiration date
     */
    Date getExpirationDate();

    /**
     * The card number
     * 
     * @return the card number
     */
    String getNumber();

    /**
     * The card type
     * 
     * @return the card type
     */
    CreditCardType getType();

    /**
     * The billing address
     * 
     * @return the billing address
     */
    Address getBillingAddress();

    /**
     * Retrieve the last four digits of the card
     * 
     * @return the last four digist of the card
     */
    String getLastFourDigits();
}
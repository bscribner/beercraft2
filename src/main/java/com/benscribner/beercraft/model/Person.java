package com.benscribner.beercraft.model;

/**
 * A class to represent a person
 */
public interface Person extends Identifiable {

    /**
     * The Person's First Name
     * 
     * @return the person's first name
     */
    String getFirstName();

    /**
     * The Person's Last Name
     * 
     * @return the person's last name
     */
    String getLastName();

    /**
     * The Person's Email Address
     * 
     * @return the person's email
     */
    String getEmail();

    /**
     * The Phone Number
     * 
     * @return the phone number
     */
    String getPhoneNumber();

    /**
     * The Person's Address
     * 
     * @return the address
     */
    Address getAddress();

    /**
     * Convenience method that returns the full name.
     * 
     * @return firstName + ' ' + lastName
     */
    String getFullName();
}
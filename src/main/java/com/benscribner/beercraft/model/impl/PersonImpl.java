package com.benscribner.beercraft.model.impl;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.benscribner.beercraft.model.Address;
import com.benscribner.beercraft.model.Person;

/**
 *  File:       PersonImpl
 *  Project:    BeerCraft
 *  Author:     @author Ben Scribner
 *  Version:    @version 1.0
 *  Date:
 *  Purpose:    @see com.benscribner.beercraft.model.Person
 */
@Entity
@Table(name = "person")
public class PersonImpl extends AbstractIdentifiableObject implements Person {
    private static final long serialVersionUID = 2199566914658745407L;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private Address address = new Address();

    /**
     * Default constructor
     */
    public PersonImpl() {

    }

    /**
     * Constructor that takes a username
     * 
     * @param username
     */
    public PersonImpl(final String firstname, final String lastname) {
        this.firstName = firstname;
        this.lastName = lastname;
    }

    /**
     * @see com.benscribner.beercraft.model.Person#getFirstName()
     */
    @Override
    @NotEmpty(message = "First Name is required")
    @Length(max = 50, message = "Max length is 50")
    @Column(name = "first_name", nullable = false, length = 50)
    public String getFirstName() {
        return firstName;
    }

    /**
     * @see com.benscribner.beercraft.model.Person#getLastName()
     */
    @Override
    @NotEmpty(message = "Last Name is required")
    @Length(max = 50, message = "Max length is 50")
    @Column(name = "last_name", nullable = false, length = 50)
    public String getLastName() {
        return lastName;
    }

    /**
     * @see com.benscribner.beercraft.model.Person#getEmail()
     */
    @Override
    @NotEmpty(message = "E-mail is required")
    @Email(message = "Not a valid E-mail")
    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    /**
     * @see com.benscribner.beercraft.model.Person#getDaytimePhoneNumber()
     */
    @Override
    @NotEmpty(message = "Phone Number is required")
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @see com.benscribner.beercraft.model.Person#getAddress()
     */
    @Override
    @Embedded
    public Address getAddress() {
        return address;
    }

    /**
     * @see com.benscribner.beercraft.model.Person#getFullName()
     */
    @Override
    @Transient
    public String getFullName() {
        return lastName + ", " + firstName;
    }

    /**
     * @param firstname
     *            the firstname to set
     */
    public void setFirstName(final String firstname) {
        this.firstName = firstname;
    }

    /**
     * @param lastname
     *            the lastname to set
     */
    public void setLastName(final String lastname) {
        this.lastName = lastname;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @param phone
     *            the phone to set
     */
    public void setPhoneNumber(final String phone) {
        this.phoneNumber = phone;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(final Address address) {
        this.address = address;
    }
}
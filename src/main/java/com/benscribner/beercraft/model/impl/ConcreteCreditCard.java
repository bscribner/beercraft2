package com.benscribner.beercraft.model.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.benscribner.beercraft.model.Address;
import com.benscribner.beercraft.model.CreditCard;
import com.benscribner.beercraft.model.CreditCardType;

/**
 *  File:       ConcreteCreditCard
 *  Project:    BeerCraft
 *  Author:     @author Kristin Caldwell
 *  Version:    @version 1.0
 *  Date:
 *  Purpose:    @see com.benscribner.beercraft.model.CreditCard
 */
@Entity
@Table(name = "credit_cards")
public class ConcreteCreditCard extends AbstractIdentifiableObject implements CreditCard {
    private static final long serialVersionUID = -5980047899739494622L;

    private Date expDate;
    private String creditNum;
    private CreditCardType type;

    private Address billingAddress = new Address();

    /**
     * Default constructor
     */
    public ConcreteCreditCard() {

    }

    /**
     * Constructor which sets the expiration date, the card number, and the card type
     * 
     * @param expDate the expiration date
     * @param creditNum the card number
     * @param ccType the card type
     */
    public ConcreteCreditCard(final Date expDate, final String creditNum, final CreditCardType ccType) {
        setExpirationDate(expDate);
        setNumber(creditNum);
        setType(ccType);
    }

    /**
     * @see com.benscribner.beercraft.model.CreditCard#getExpirationDate()
     */
    @Override
    @NotNull(message = "The Expiration Date is required")
    @Future(message = "The Expiration Date must be in the future")
    @Column(name = "expiration_date", nullable = false)
    public Date getExpirationDate() {
        return expDate;
    }

    /**
     * @see com.benscribner.beercraft.model.CreditCard#getNumber()
     */
    @Override
    @NumberFormat(style = Style.NUMBER)
    @Length(min = 15, max = 16, message = "Enter a valid Card Number")
    @CreditCardNumber(message = "Enter a valid Card Number")
    @Column(name = "card_number", nullable = false, length = 50)
    public String getNumber() {
        return creditNum;
    }

    /**
     * @see com.benscribner.beercraft.model.CreditCard#getType()
     */
    @Override
    @NotNull(message = "The Card Type is required")
    @Column(name = "type", nullable = false)
    public CreditCardType getType() {
        return type;
    }

    /**
     * @see com.benscribner.beercraft.model.Person#getAddress()
     */
    @Override
    @Embedded
    public Address getBillingAddress() {
        return billingAddress;
    }

    /**
     * @see com.benscribner.beercraft.model.CreditCard#getLastFourDigits()
     */
    @Override
    @Transient
    public String getLastFourDigits() {
        String lastFour = "";
        if (creditNum.length() > 4) {
            lastFour = creditNum.substring(creditNum.length() - 4);
        }

        return lastFour;
    }

    /**
     * @param expDate the expiration date to set
     */
    public void setExpirationDate(final Date expDate) {
        this.expDate = expDate;
    }

    /**
     * @param creditNumber the card number to set
     */
    public void setNumber(final String creditNumber) {
        creditNum = creditNumber;
    }

    /**
     * @param type the card type to set
     */
    public void setType(final CreditCardType type) {
        this.type = type;
    }

    /**
     * @param billingAddress
     *            the billingAddress to set
     */
    public void setBillingAddress(final Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * Convenience method for setting the billing address
     * 
     * @param add1 the address line 1
     * @param add2 the address line 2
     * @param add3 the address line 3
     * @param city the city
     * @param state the state
     * @param zip the zip code
     * @param country the country
     */
    public void setBillingAddress(final String add1, final String add2, final String add3, final String city,
            final String state, final String zip, final String country) {
        billingAddress = new Address();
        billingAddress.setAddress1(add1);
        billingAddress.setAddress2(add2);
        billingAddress.setAddress3(add3);
        billingAddress.setCity(city);
        billingAddress.setState(state);
        billingAddress.setPostalCode(zip);
        billingAddress.setCountry(country);
    }
}
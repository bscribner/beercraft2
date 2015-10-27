package com.benscribner.beercraft.model.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.benscribner.beercraft.model.Address;
import com.benscribner.beercraft.model.CreditCard;
import com.benscribner.beercraft.model.CreditCardType;
import com.benscribner.beercraft.model.LineItem;
import com.benscribner.beercraft.model.Order;
import com.benscribner.beercraft.model.Person;

/**
 *  File:       PurchaseOrder
 *  Project:    BeerCraft
 *  Author:     @author Kristin Caldwell
 *  Version:    @version 1.0
 *  Date:
 *  Purpose:    @see com.benscribner.beercraft.model.Order
 */
@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder extends AbstractIdentifiableObject implements Order {
    private static final long serialVersionUID = 3828571463301838881L;

    private Long orderNumber;
    private Person user;
    private CreditCard credit;
    private List<LineItem> lineItems = new ArrayList<LineItem>();

    private Address shipping = new Address();

    /**
     * @see com.benscribner.beercraft.model.Order#getOrderNumber()
     */
    @Override
    @Column(name = "order_number")
    public Long getOrderNumber() {
        return orderNumber;
    }

    /**
     * @see com.benscribner.beercraft.model.Order#getPerson()
     */
    @Override
    @ManyToOne(cascade = { CascadeType.REFRESH }, targetEntity = PersonImpl.class)
    @JoinColumn(name = "person_id")
    public Person getPerson() {
        return user;
    }

    /**
     * @see com.benscribner.beercraft.model.Order#getShipping()
     */
    @Override
    @Embedded
    public Address getShipping() {
        return shipping;
    }

    /**
     * @see com.benscribner.beercraft.model.Order#getCardNumber()
     */
    @Override
    @Valid
    @OneToOne(cascade = { CascadeType.ALL }, targetEntity = ConcreteCreditCard.class)
    @JoinColumn(name = "credit_card_id")
    public CreditCard getCardNumber() {
        return credit;
    }

    /**
     * @see com.benscribner.beercraft.model.Order#getLineItems()
     */
    @Override
    @OneToMany(cascade = { CascadeType.ALL }, targetEntity = LineItemObject.class, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinTable(name = "order_items", joinColumns = { @JoinColumn(name = "order_id") }, inverseJoinColumns = @JoinColumn(name = "item_id"))
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    /**
     * @param orderNumber the order number to set
     */
    public void setOrderNumber(final Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @param user the person to set
     */
    public void setPerson(final Person user) {
        this.user = user;
    }

    /**
     * @param credit the card number to set
     */
    public void setCardNumber(final CreditCard credit) {
        this.credit = credit;
    }

    /**
     * @param lineItems the line items to set
     */
    public void setLineItems(final List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    /**
     * @param shipping the shipping address to set
     */
    public void setShipping(final Address shipping) {
        this.shipping = shipping;
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
    public void setShippingAddress(final String add1, final String add2, final String add3, final String city,
            final String state, final String zip, final String country) {
        shipping = new Address();
        shipping.setAddress1(add1);
        shipping.setAddress2(add2);
        shipping.setAddress3(add3);
        shipping.setCity(city);
        shipping.setState(state);
        shipping.setPostalCode(zip);
        shipping.setCountry(country);
    }

    /**
     * Convenience method for setting the credit card
     * 
     * @param expDate the expiration date
     * @param creditNum the card number
     * @param ccType the card type
     */
    public void setCredit(final Date expDate, final String creditNum, final CreditCardType ccType) {
        credit = new ConcreteCreditCard(expDate, creditNum, ccType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (o != null && o.getClass() != this.getClass()) {
            return false;
        }

        return EqualsBuilder.reflectionEquals(this, o, new String[] { "lineItems" });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, new String[] { "lineItems" });
    }

    /**
     * @see com.benscribner.beercraft.model.Order#getTotalCost()
     */
    @Override
    @Transient
    public Double getTotalCost() {
        double total = 0;
        for (final LineItem item : getLineItems()) {
            total += item.getTotalCost();
        }

        return total;
    }
}
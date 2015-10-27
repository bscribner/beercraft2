package com.benscribner.beercraft.model.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.benscribner.beercraft.model.Beer;
import com.benscribner.beercraft.model.Cart;
import com.benscribner.beercraft.model.LineItem;
import com.benscribner.beercraft.model.Person;

/**
 *  File:       ShoppingCart
 *  Project:    BeerCraft
 *  Author:     @author Kristin Caldwell
 *  Version:    @version 1.0
 *  Date:
 *  Purpose:    @see com.benscribner.beercraft.model.Cart
 */
@Entity
@Table(name = "shopping_carts")
public class ShoppingCart extends AbstractIdentifiableObject implements Cart {
    private static final long serialVersionUID = -6973372620624309461L;

    private Person person;
    private List<LineItem> lineItems = new ArrayList<LineItem>();

    /**
     * @see com.benscribner.beercraft.model.Cart#getPerson()
     */
    @Override
    @OneToOne(targetEntity = PersonImpl.class)
    @JoinColumn(name = "person_id")
    public Person getPerson() {
        return person;
    }

    /**
     * @see com.benscribner.beercraft.model.Cart#getLineItems()
     */
    @Override
    @OneToMany(cascade = { CascadeType.ALL }, targetEntity = LineItemObject.class, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinTable(name = "shopping_cart_items", joinColumns = { @JoinColumn(name = "shopping_cart_id") }, inverseJoinColumns = @JoinColumn(name = "item_id"))
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    /**
     * @param lineItems the line items to set
     */
    public void setLineItems(final List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    /**
     * @param person
     *            the person to set
     */
    public void setPerson(final Person person) {
        this.person = person;
    }

    /**
     * Convenience method for adding a line item
     * 
     * @param beer the beer to set
     */
    public void setLineItems(final Beer beer) {
        lineItems.add(new LineItemObject(beer));
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
     * @see com.benscribner.beercraft.model.Cart#getTotalCost()
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
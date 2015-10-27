package com.benscribner.beercraft.model.impl;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.benscribner.beercraft.model.Beer;
import com.benscribner.beercraft.model.LineItem;

/**
 *  File:       LineItemObject
 *  Project:    BeerCraft
 *  Author:     @author Kristin Caldwell
 *  Version:    @version 1.0
 *  Date:
 *  Purpose:    @see com.benscribner.beercraft.model.LineItem
 */
@Entity
@Table(name = "line_items")
public class LineItemObject extends AbstractIdentifiableObject implements LineItem {
    private static final long serialVersionUID = -4622295858447711244L;

    private Beer beer;
    private Integer quantity;

    /**
     * Default constructor
     */
    public LineItemObject() {

    }

    /**
     * Constructor which sets the beer and initializes the quantity to 1
     * 
     * @param beer the beer to set
     */
    public LineItemObject(final Beer beer) {
        this.beer = beer;
        this.quantity = 1;
    }

    /**
     * @see com.benscribner.beercraft.model.LineItem#getBeer()
     */
    @Override
    @ManyToOne(cascade = { CascadeType.REFRESH }, targetEntity = BeerImpl.class)
    @JoinColumn(name = "beer_id")
    public Beer getBeer() {
        return beer;
    }

    /**
     * @see com.benscribner.beercraft.model.LineItem#getQuantity()
     */
    @Override
    @Column(name = "quantity", nullable = false)
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @param beer the beer to set
     */
    public void setBeer(final Beer beer) {
        this.beer = beer;
    }

    /**
     * @see com.benscribner.beercraft.model.LineItem#getTotalCost()
     */
    @Override
    @Transient
    public Double getTotalCost() {
        return getBeer().getPrice() * getQuantity();
    }
}
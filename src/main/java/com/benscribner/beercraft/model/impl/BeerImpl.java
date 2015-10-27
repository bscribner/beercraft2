package com.benscribner.beercraft.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.benscribner.beercraft.model.Beer;
import com.benscribner.beercraft.model.Brewery;
import com.benscribner.beercraft.model.Category;
import com.benscribner.beercraft.model.Origin;

/**
 * File: BeerImpl Project: BeerCraft Author: @author Kristin Caldwell Version: @version
 * 1.0 Date: Purpose: @see com.benscribner.beercraft.model.Beer
 */
@Entity
@Table(name = "beer")
public class BeerImpl extends AbstractNamedObject implements Beer {
    private static final long serialVersionUID = 6451222304353219701L;

    /**
     * variable declarations
     */
    private Category category;
    private String icon;
    private Origin origin;
    private Double abv;
    private Double price;
    private Brewery brewery;

    /**
     * Default Constructor
     */
    public BeerImpl() {

    }

    /**
     * Create a new instance and set the name and category
     * 
     * @param name
     *            The name of the beer
     * @param description
     *            The description of the beer
     * @param category
     *            The category of the beer (type)
     * @param origin
     *            The country of origin where the beer was crafted
     * @param brewery
     *            The brewery where the beer was crafted
     * @param icon
     *            The relative path for the icon of the beer
     * @param abv
     *            The alcohol by volume % that is contained within the beer
     * @param price
     *            The price of the beer
     */
    public BeerImpl(final String name, final String description, final Category category, final Origin origin,
            final Brewery brewery, final String icon, final Double abv, final Double price) {
        setName(name);
        setDescription(description);
        setCategory(category);
        setOrigin(origin);
        setBrewery(brewery);
        setIcon(icon);
        setAlcoholContent(abv);
        setPrice(price);
    }

    /**
     * Set the category
     * 
     * @param category
     *            the category to set
     */
    public void setCategory(final Category category) {
        this.category = category;
    }

    /**
     * @see com.benscribner.beercraft.model.Beer#getCategory()
     */
    @Override
    @NotNull(message = "Category is required")
    @ManyToOne(targetEntity = CategoryImpl.class)
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }

    /**
     * @see com.benscribner.beercraft.model.Beer#getIcon()
     */
    @Override
    @Column(name = "icon_path")
    public String getIcon() {
        return icon;
    }

    /**
     * Set the icon
     * 
     * @param icon
     *            the icon to set
     */
    public void setIcon(final String icon) {
        this.icon = icon;
    }

    /**
     * @see com.benscribner.beercraft.model.Beer#getAlcoholContent()
     */
    @Override
    @NotNull(message = "ABV is required")
    @Digits(integer = 12, fraction = 2, message = "ABV is invalid")
    @Column(name = "abv")
    public Double getAlcoholContent() {
        return abv;
    }

    /**
     * Set the ABV
     * 
     * @param abv
     *            the ABV to set
     */
    public void setAlcoholContent(final Double abv) {
        this.abv = abv;
    }

    /**
     * @see com.benscribner.beercraft.model.Beer#getBrewery()
     */
    @Override
    @NotNull(message = "Brewery is required")
    @ManyToOne(targetEntity = BreweryImpl.class)
    @JoinColumn(name = "brewery_id")
    public Brewery getBrewery() {
        return brewery;
    }

    /**
     * Set the Brewery
     * 
     * @param brewery
     *            the Brewery to set
     */
    public void setBrewery(final Brewery brewery) {
        this.brewery = brewery;
    }

    /**
     * @see com.benscribner.beercraft.model.Beer#getPrice()
     */
    @Override
    @NotNull(message = "Price is required")
    @Digits(integer = 12, fraction = 2, message = "Price is invalid")
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    /**
     * Set the Price
     * 
     * @param price
     *            the Price to set
     */
    public void setPrice(final Double price) {
        this.price = price;
    }

    /**
     * @see com.benscribner.beercraft.model.Beer#getOrigin()
     */
    @Override
    @NotNull(message = "Origin is required")
    @ManyToOne(targetEntity = OriginImpl.class)
    @JoinColumn(name = "origin_id")
    public Origin getOrigin() {
        return origin;
    }

    /**
     * Set the Origin
     * 
     * @param origin
     *            the Origin to set
     */
    public void setOrigin(final Origin origin) {
        this.origin = origin;
    }
}
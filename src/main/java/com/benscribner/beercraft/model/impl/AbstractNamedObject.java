package com.benscribner.beercraft.model.impl;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.benscribner.beercraft.model.Named;

/**
 * Base named class
 */
@MappedSuperclass
public abstract class AbstractNamedObject extends AbstractIdentifiableObject implements Named {
    private static final long serialVersionUID = 5900211985255050789L;

    private String name;
    private String description;

    /**
     * @see com.benscribner.beercraft.model.Named#getName()
     */
    @Override
    @NotEmpty(message = "Name is required")
    @Column(nullable = false, length = 50, unique = true)
    public String getName() {
        return this.name;
    }

    /**
     * @see com.benscribner.beercraft.model.Named#getDescription()
     */
    @Override
    @Length(max = 255, message = "Max length is 255")
    @Column(length = 255)
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the name
     * 
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Set the description
     * 
     * @param description
     *            the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }
}
package com.benscribner.beercraft.model.impl;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.benscribner.beercraft.model.Role;

/**
 *  File:       PersonImpl
 *  Project:    BeerCraft
 *  Author:     @author Ben Scribner
 *  Version:    @version 1.0
 *  Date:
 *  Purpose:    @see com.benscribner.beercraft.model.Role
 */
@Entity
@Table(name = "role")
public class RoleImpl extends AbstractNamedObject implements Role, Comparable<Role> {

    private static final long serialVersionUID = 3690197650654049848L;

    /**
     * Default constructor
     */
    public RoleImpl() {

    }

    /**
     * Create a new instance and set the name
     * 
     * @param name
     *            name of the role
     */
    public RoleImpl(final String name) {
        setName(name);
    }

    /**
     * @see org.springframework.security.core.GrantedAuthority#getAuthority()
     */
    @Override
    @Transient
    public String getAuthority() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final Role o) {
        return (equals(o) ? 0 : -1);
    }
}
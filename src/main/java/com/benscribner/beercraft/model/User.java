package com.benscribner.beercraft.model;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * A class to represent a User
 */
public interface User extends UserDetails, Identifiable {

    /**
     * The username
     * 
     * @return the username
     */
    @Override
    String getUsername();

    /**
     * The password
     * 
     * @return the password
     */
    @Override
    String getPassword();

    /**
     * The confirm password
     * 
     * @return the confirm password
     */
    String getConfirmPassword();

    /**
     * The roles
     * 
     * @return a Set of Roles
     */
    List<Role> getRoles();

    /**
     * get Adds a role
     * 
     * @param role
     *            the role to add
     */
    void addRole(Role role);

    /**
     * Is the account enabled?
     */
    @Override
    boolean isEnabled();

    /**
     * The person record associated with this user
     * 
     * @return the person associated with this user
     */
    Person getPerson();
}

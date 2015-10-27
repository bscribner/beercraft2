package com.benscribner.beercraft.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.benscribner.beercraft.model.Identifiable;

/**
 * DAO for persisting a User object
 */
public interface UserDao<T extends Identifiable> extends IdentifiableDao<T> {

    /**
     * Gets users information based on login name.
     * 
     * @param username
     *            the user's username
     * @return userDetails populated userDetails object
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException
     *             thrown when user not found in database
     */
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
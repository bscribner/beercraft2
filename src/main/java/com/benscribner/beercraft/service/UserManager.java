package com.benscribner.beercraft.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.benscribner.beercraft.dao.UserDao;
import com.benscribner.beercraft.model.Identifiable;

/**
 * Manager class that talks to {@link UserDao}.
 */
public interface UserManager<T extends Identifiable> extends IdentifiableManager<T>, UserDetailsService {

    /**
     * Gets users information based on login name.
     * 
     * @param username
     *            the user's username
     * @return userDetails populated userDetails object
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException
     *             thrown when user not found in database
     */
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Save an already existing User
     * 
     * @param user
     *            the user to save
     * @return the persisted object
     */
    T saveExistingUser(T user);
}

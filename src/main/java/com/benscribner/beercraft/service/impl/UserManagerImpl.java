package com.benscribner.beercraft.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.benscribner.beercraft.dao.UserDao;
import com.benscribner.beercraft.model.User;
import com.benscribner.beercraft.model.impl.UserImpl;
import com.benscribner.beercraft.service.UserManager;

/**
 * @see com.benscribner.beercraft.service.UserManager
 */
@Service("userManager")
public class UserManagerImpl extends GenericIdentifiableManager<User, UserDao<User>> implements UserManager<User> {

    private PasswordEncoder passwordEncoder = null;

    /**
     * Default constructor
     */
    public UserManagerImpl() {

    }

    /**
     * Constructor that sets the user dao and the password encoder
     * 
     * @param userDao
     *            the user dao to set
     * @param passwordEncoder
     *            the pasword encoder to set
     */
    @Autowired
    public UserManagerImpl(final UserDao<User> userDao, final PasswordEncoder passwordEncoder) {
        super(userDao);
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User save(final User user) {
        ((UserImpl) user).setPassword(passwordEncoder.encodePassword(user.getPassword(), null));

        return dao.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User saveExistingUser(final User user) {
        // Existing user, check password in DB
        UserDetails existingUser = null;

        try {
            existingUser = dao.loadUserByUsername(user.getUsername());
        } catch (final UsernameNotFoundException e) {
            // username could not be found
        }

        String currentPassword = "";
        if (existingUser == null) {
            // lowercase userId
            ((UserImpl) user).setUsername(user.getUsername().toLowerCase());
        } else {
            currentPassword = existingUser.getPassword();
        }        

        // if new user or changed password, encrypt the password
        if (StringUtils.isBlank(currentPassword) || !currentPassword.equals(user.getPassword())) {
            ((UserImpl) user).setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
        }

        return dao.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return dao.loadUserByUsername(username);
    }
}
package com.benscribner.beercraft.dao.hibernate;

import org.hibernate.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.benscribner.beercraft.dao.UserDao;
import com.benscribner.beercraft.model.User;
import com.benscribner.beercraft.model.impl.UserImpl;

/**
 * A DAO to persist Users in Hibernate
 */
@Repository("userDao")
public class UserDaoHibernate extends GenericIdentifiableDaoHibernate<User> implements UserDao<User>,
        UserDetailsService {

    /**
     * Constructor that sets the entity
     */
    public UserDaoHibernate() {
        super(UserImpl.class);
    }

    /**
     * Save the user
     * 
     * @param user
     *            the user to save
     * @return the modified user
     */
    @Override
    public User save(final User user) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + user.getId());
        }
        super.save(user);
        getCurrentSession().flush();
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Query query = getCurrentSession().createQuery("from UserImpl where username=:username");
        query.setParameter("username", username);
        final UserDetails user = (UserDetails) query.uniqueResult();
        if (user == null) {
            throw new UsernameNotFoundException("user '" + username + "' not found...");
        } else {
            return user;
        }
    }
}
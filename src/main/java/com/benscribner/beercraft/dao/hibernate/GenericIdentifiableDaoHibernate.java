package com.benscribner.beercraft.dao.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.base.Preconditions;

import com.benscribner.beercraft.dao.IdentifiableDao;
import com.benscribner.beercraft.model.Identifiable;

/**
 * Base DAO for persisting objects in Hibernate
 */
public class GenericIdentifiableDaoHibernate<T extends Identifiable> implements IdentifiableDao<T> {

    protected final Log log = LogFactory.getLog(getClass());

    protected final Class<? extends T> persistentClass;
    private SessionFactory sessionFactory;

    /**
     * Constructor
     * 
     * @param persistentClass
     *            the class to persist
     */
    public GenericIdentifiableDaoHibernate(final Class<? extends T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    /**
     * Retrieve the session factory
     * 
     * @return the session factory
     */
    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    /**
     * Set the session factory
     * 
     * @param sessionFactory
     *            the session factory to set
     */
    @Autowired
    @Required
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return this.getCurrentSession().createQuery("from " + this.persistentClass.getName()).list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(final String id) {
        final T entity = (T) this.getCurrentSession().get(this.persistentClass, id);

        if (entity == null) {
            log.warn("The object of type '" + this.persistentClass + "' and with id '" + id + "' could not be found...");
            return null;
        }

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists(final String id) {
        final T entity = get(id);
        return entity != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public T save(final T object) {
        Preconditions.checkNotNull(object);
        if (object.getId() == null) {
            this.getCurrentSession().persist(object);
            return object;
        } else {
            return (T) this.getCurrentSession().merge(object);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final String id) {
        final T object = get(id);
        Preconditions.checkNotNull(object);
        this.getCurrentSession().delete(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final T object) {
        Preconditions.checkNotNull(object);
        this.getCurrentSession().delete(object);
    }

    /**
     * Get the current session
     * 
     * @return the current session
     */
    protected final Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
package com.benscribner.beercraft.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.benscribner.beercraft.dao.IdentifiableDao;
import com.benscribner.beercraft.model.Identifiable;
import com.benscribner.beercraft.service.IdentifiableManager;

/**
 * @see com.benscribner.beercraft.service.IdenfifiableManager
 */
@Transactional
public class GenericIdentifiableManager<T extends Identifiable, DAO extends IdentifiableDao<T>> implements IdentifiableManager<T> {
    
    /**
     * Default constructor
     */
    public GenericIdentifiableManager() {
        
    }

    /**
     * Log variable for all child classes.
     */
    protected final Log log = LogFactory.getLog(getClass());

    /**
     * GenericDao instance, set by constructor of child classes
     */
    protected DAO dao;

    /**
     * Constructor that sets the dao
     * 
     * @param dao
     *            the dao to set
     */
    public GenericIdentifiableManager(final DAO dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAll() {
        return dao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(String id) {
        return dao.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists(String id) {
        return dao.exists(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T save(T object) {
        return dao.save(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(String id) {
        dao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(T object) {
        dao.delete(object);
    }
}
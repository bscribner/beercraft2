package com.benscribner.beercraft.dao.hibernate;

import com.benscribner.beercraft.model.Brewery;
import com.benscribner.beercraft.model.impl.BreweryImpl;

/**
 * Unit tests for the {@link BreweryDaoHibernate} class
 */
public class BreweryDaoHibernateTest extends GenericNamedDaoHibernateTest<Brewery, BreweryDaoHibernate> {

    /**
     * @see com.benscribner.beercraft.dao.hibernate.GenericIdentifiableDaoHibernateTest#getDao()
     */
    @Override
    protected BreweryDaoHibernate getDao() {
        BreweryDaoHibernate dao = new BreweryDaoHibernate();
        dao.setSessionFactory(mockSessionFactory);
        return dao;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.GenericIdentifiableDaoHibernateTest#getPersistentClass()
     */
    @Override
    protected Class<BreweryImpl> getPersistentClass() {
        return BreweryImpl.class;
    }
}

package com.benscribner.beercraft.dao.hibernate;

import com.benscribner.beercraft.model.Origin;
import com.benscribner.beercraft.model.impl.OriginImpl;

/**
 * Unit tests for the {@link OriginDaoHibernate} class
 */
public class OriginDaoHibernateTest extends GenericNamedDaoHibernateTest<Origin, OriginDaoHibernate> {

    /**
     * @see com.benscribner.beercraft.dao.hibernate.GenericIdentifiableDaoHibernateTest#getDao()
     */
    @Override
    protected OriginDaoHibernate getDao() {
        OriginDaoHibernate dao = new OriginDaoHibernate();
        dao.setSessionFactory(mockSessionFactory);
        return dao;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.GenericIdentifiableDaoHibernateTest#getPersistentClass()
     */
    @Override
    protected Class<OriginImpl> getPersistentClass() {
        return OriginImpl.class;
    }
}

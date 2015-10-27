package com.benscribner.beercraft.dao.hibernate;

import com.benscribner.beercraft.model.LineItem;
import com.benscribner.beercraft.model.impl.LineItemObject;

/**
 * Unit tests for the {@link LineItemDaoHibernate} class
 */
public class LineItemDaoHibernateTest extends GenericIdentifiableDaoHibernateTest<LineItem, LineItemDaoHibernate> {

    /**
     * @see com.benscribner.beercraft.dao.hibernate.GenericIdentifiableDaoHibernateTest#getPersistentClass()
     */
    @Override
    protected Class<LineItemObject> getPersistentClass() {
        return LineItemObject.class;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.GenericIdentifiableDaoHibernateTest#getDao()
     */
    @Override
    protected LineItemDaoHibernate getDao() {
        final LineItemDaoHibernate dao = new LineItemDaoHibernate();
        dao.setSessionFactory(mockSessionFactory);
        return dao;
    }
}
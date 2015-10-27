package com.benscribner.beercraft.dao.hibernate.integration;

import org.springframework.beans.factory.annotation.Autowired;

import com.benscribner.beercraft.dao.hibernate.OriginDaoHibernate;
import com.benscribner.beercraft.model.Origin;
import com.benscribner.beercraft.model.impl.OriginImpl;

/**
 * Unit tests for the {@link OriginDaoHibernate} class
 */
public class OriginDaoHibernateIntegrationTest extends GenericNamedDaoHibernateIntegrationTest<Origin> {

    @Autowired
    private OriginDaoHibernate originDao;

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.GenericNamedDaoHibernateIntegrationTest#getDao()
     */
    @Override
    protected OriginDaoHibernate getDao() {
        return originDao;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#createObject()
     */
    @Override
    protected Origin createObject() {
        final OriginImpl origin = new OriginImpl("test origin", "test description");

        return origin;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#updateObject(com.benscribner.beercraft.model.Identifiable)
     */
    @Override
    protected void updateObject(final Origin origin) {
        OriginImpl updatedOrigin = (OriginImpl) origin;
        updatedOrigin.setName("new name");
        updatedOrigin.setDescription("new description");
    }
}

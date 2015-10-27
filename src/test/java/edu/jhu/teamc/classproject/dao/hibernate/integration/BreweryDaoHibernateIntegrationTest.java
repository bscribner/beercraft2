package com.benscribner.beercraft.dao.hibernate.integration;

import org.springframework.beans.factory.annotation.Autowired;

import com.benscribner.beercraft.dao.hibernate.BreweryDaoHibernate;
import com.benscribner.beercraft.model.Brewery;
import com.benscribner.beercraft.model.impl.BreweryImpl;

/**
 * Unit tests for the {@link BreweryDaoHibernate} class
 */
public class BreweryDaoHibernateIntegrationTest extends GenericNamedDaoHibernateIntegrationTest<Brewery> {

    @Autowired
    private BreweryDaoHibernate breweryDao;

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.GenericNamedDaoHibernateIntegrationTest#getDao()
     */
    @Override
    protected BreweryDaoHibernate getDao() {
        return breweryDao;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#createObject()
     */
    @Override
    protected Brewery createObject() {
        final BreweryImpl brewery = new BreweryImpl("test brewery", "test description");

        return brewery;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#updateObject(com.benscribner.beercraft.model.Identifiable)
     */
    @Override
    protected void updateObject(final Brewery brewery) {
        BreweryImpl updatedBrewery = (BreweryImpl) brewery;
        updatedBrewery.setName("new name");
        updatedBrewery.setDescription("new description");
    }
}

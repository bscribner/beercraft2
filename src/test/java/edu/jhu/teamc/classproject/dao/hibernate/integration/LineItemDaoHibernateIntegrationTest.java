package com.benscribner.beercraft.dao.hibernate.integration;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.benscribner.beercraft.dao.IdentifiableDao;
import com.benscribner.beercraft.dao.hibernate.BeerDaoHibernate;
import com.benscribner.beercraft.dao.hibernate.BreweryDaoHibernate;
import com.benscribner.beercraft.dao.hibernate.CategoryDaoHibernate;
import com.benscribner.beercraft.dao.hibernate.LineItemDaoHibernate;
import com.benscribner.beercraft.dao.hibernate.OriginDaoHibernate;
import com.benscribner.beercraft.model.Brewery;
import com.benscribner.beercraft.model.Category;
import com.benscribner.beercraft.model.LineItem;
import com.benscribner.beercraft.model.Origin;
import com.benscribner.beercraft.model.impl.BeerImpl;
import com.benscribner.beercraft.model.impl.BreweryImpl;
import com.benscribner.beercraft.model.impl.CategoryImpl;
import com.benscribner.beercraft.model.impl.LineItemObject;
import com.benscribner.beercraft.model.impl.OriginImpl;

/**
 * Unit tests for the {@link LineItemDaoHibernate} class
 */
public class LineItemDaoHibernateIntegrationTest extends AbstractHibernateIntegrationTest<LineItem> {

    @Autowired
    private LineItemDaoHibernate lineItemDao;

    @Autowired
    private BeerDaoHibernate beerDao;

    @Autowired
    private BreweryDaoHibernate breweryDao;

    @Autowired
    private CategoryDaoHibernate categoryDao;

    @Autowired
    private OriginDaoHibernate originDao;

    private BeerImpl beer;

    /**
     * Setup the test
     */
    @Before
    public void setup() {
        Category category = categoryDao.save(new CategoryImpl("name", "description"));
        Origin origin = originDao.save(new OriginImpl("name", "description"));
        Brewery brewery = breweryDao.save(new BreweryImpl("name", "description"));

        beer = new BeerImpl("name", "description", category, origin, brewery, null, 5.5, 2.99);
        beerDao.save(beer);
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#getDao()
     */
    @Override
    protected IdentifiableDao<LineItem> getDao() {
        return lineItemDao;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#createObject()
     */
    @Override
    protected LineItem createObject() {
        return new LineItemObject(beer);
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#updateObject(com.benscribner.beercraft.model.Identifiable)
     */
    @Override
    protected void updateObject(final LineItem lineItem) {
        final LineItemObject lineItemObject = (LineItemObject) lineItem;
        lineItemObject.setQuantity(2);
    }
}
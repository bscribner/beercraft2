package com.benscribner.beercraft.dao.hibernate.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.benscribner.beercraft.dao.hibernate.BeerDaoHibernate;
import com.benscribner.beercraft.dao.hibernate.BreweryDaoHibernate;
import com.benscribner.beercraft.dao.hibernate.CategoryDaoHibernate;
import com.benscribner.beercraft.dao.hibernate.OriginDaoHibernate;
import com.benscribner.beercraft.model.Beer;
import com.benscribner.beercraft.model.Brewery;
import com.benscribner.beercraft.model.Category;
import com.benscribner.beercraft.model.Origin;
import com.benscribner.beercraft.model.impl.BeerImpl;
import com.benscribner.beercraft.model.impl.BreweryImpl;
import com.benscribner.beercraft.model.impl.CategoryImpl;
import com.benscribner.beercraft.model.impl.OriginImpl;

/**
 * Unit tests for the {@link BeerDaoHibernate} class
 */
public class BeerDaoHibernateIntegrationTest extends GenericNamedDaoHibernateIntegrationTest<Beer> {

    @Autowired
    private BeerDaoHibernate beerDao;

    @Autowired
    private CategoryDaoHibernate categoryDao;

    @Autowired
    private BreweryDaoHibernate breweryDao;

    @Autowired
    private OriginDaoHibernate originDao;

    private Category category1;
    private Category category2;
    private Category category3;
    private Brewery brewery1;
    private Brewery brewery2;
    private Brewery brewery3;
    private Origin origin1;
    private Origin origin2;
    private Origin origin3;

    /**
     * Setup the test
     */
    @Before
    public void setup() {
        category1 = new CategoryImpl("category1", "description");
        category2 = new CategoryImpl("category2", "description");
        category3 = new CategoryImpl("category3", "description");
        categoryDao.save(category1);
        categoryDao.save(category2);
        categoryDao.save(category3);

        brewery1 = new BreweryImpl("brewery1", "description");
        brewery2 = new BreweryImpl("brewery2", "description");
        brewery3 = new BreweryImpl("brewery3", "description");
        breweryDao.save(brewery1);
        breweryDao.save(brewery2);
        breweryDao.save(brewery3);

        origin1 = new OriginImpl("origin1", "description");
        origin2 = new OriginImpl("origin2", "description");
        origin3 = new OriginImpl("origin3", "description");
        originDao.save(origin1);
        originDao.save(origin2);
        originDao.save(origin3);
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.GenericNamedDaoHibernateIntegrationTest#getDao()
     */
    @Override
    protected BeerDaoHibernate getDao() {
        return beerDao;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#createObject()
     */
    @Override
    protected Beer createObject() {
        final BeerImpl beer = new BeerImpl();
        beer.setName("name");
        beer.setDescription("description");
        beer.setCategory(category1);
        beer.setBrewery(brewery1);
        beer.setOrigin(origin1);
        beer.setAlcoholContent(5.0);
        beer.setPrice(10.0);
        beer.setIcon("path/to/icon");

        return beer;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#updateObject(com.benscribner.beercraft.model.Identifiable)
     */
    @Override
    protected void updateObject(final Beer beer) {
        BeerImpl updatedBeer = (BeerImpl) beer;
        updatedBeer.setName("new name");
        updatedBeer.setDescription("new description");
        updatedBeer.setCategory(category2);
        updatedBeer.setBrewery(brewery2);
        updatedBeer.setOrigin(origin2);
        updatedBeer.setAlcoholContent(5.5);
        updatedBeer.setPrice(10.5);
        updatedBeer.setIcon("path/to/new/icon");
    }

    /**
     * Test retrieving beer by category without any results
     * 
     * @throws Exception
     */
    @Test
    public void testGetByCategoryWithoutResults() throws Exception {
        final List<Beer> beerList = getDao().getBeerByCategory(category3);
        assertNotNull(beerList);
        assertTrue(beerList.isEmpty());
    }

    /**
     * Test retrieving beer by category with results
     * 
     * @throws Exception
     */
    @Test
    public void testGetByCategoryWithResults() throws Exception {
        final Beer beer = createObject();
        getDao().save(beer);
        flush();

        final List<Beer> beerList = getDao().getBeerByCategory(category1);
        assertNotNull(beerList);
        assertEquals(1, beerList.size());
    }

    /**
     * Test retrieving beer by origin without any results
     * 
     * @throws Exception
     */
    @Test
    public void testGetByOriginWithoutResults() throws Exception {
        final List<Beer> beerList = getDao().getBeerByOrigin(origin3);
        assertNotNull(beerList);
        assertTrue(beerList.isEmpty());
    }

    /**
     * Test retrieving beer by origin with results
     * 
     * @throws Exception
     */
    @Test
    public void testGetByOriginWithResults() throws Exception {
        final Beer beer = createObject();
        getDao().save(beer);
        flush();

        final List<Beer> beerList = getDao().getBeerByOrigin(origin1);
        assertNotNull(beerList);
        assertEquals(1, beerList.size());
    }

    /**
     * Test retrieving beer by brewery without any results
     * 
     * @throws Exception
     */
    @Test
    public void testGetByBreweryWithoutResults() throws Exception {
        final List<Beer> beerList = getDao().getBeerByBrewery(brewery3);
        assertNotNull(beerList);
        assertTrue(beerList.isEmpty());
    }

    /**
     * Test retrieving beer by brewery with results
     * 
     * @throws Exception
     */
    @Test
    public void testGetByBreweryWithResults() throws Exception {
        final Beer beer = createObject();
        getDao().save(beer);
        flush();

        final List<Beer> beerList = getDao().getBeerByBrewery(brewery1);
        assertNotNull(beerList);
        assertEquals(1, beerList.size());
    }
}

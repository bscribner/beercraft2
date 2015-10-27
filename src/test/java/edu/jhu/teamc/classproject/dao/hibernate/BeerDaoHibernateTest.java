package com.benscribner.beercraft.dao.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.mockito.Mock;

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
public class BeerDaoHibernateTest extends GenericNamedDaoHibernateTest<Beer, BeerDaoHibernate> {
    
    @Mock
    private Beer mockBeer;

    /**
     * @see com.benscribner.beercraft.dao.hibernate.GenericIdentifiableDaoHibernateTest#getDao()
     */
    @Override
    protected BeerDaoHibernate getDao() {
        BeerDaoHibernate dao = new BeerDaoHibernate();
        dao.setSessionFactory(mockSessionFactory);
        return dao;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.GenericIdentifiableDaoHibernateTest#getPersistentClass()
     */
    @Override
    protected Class<BeerImpl> getPersistentClass() {
        return BeerImpl.class;
    }

    /**
     * Test retrieving beer by category without any results
     * 
     * @throws Exception
     */
    @Test
    public void testGetByCategoryWithoutResults() throws Exception {
        when(mockSession.createQuery(isA(String.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(isA(String.class), isA(Category.class))).thenReturn(mockQuery);
        when(mockQuery.list()).thenReturn(null);

        assertNull(getDao().getBeerByCategory(new CategoryImpl("invalid", "invalid")));

        verify(mockSession).createQuery(isA(String.class));
        verify(mockQuery).setParameter(isA(String.class), isA(Category.class));
        verify(mockQuery).list();
    }

    /**
     * Test retrieving beer by category with results
     * 
     * @throws Exception
     */
    @Test
    public void testGetByCategoryWithResults() throws Exception {
        when(mockSession.createQuery(isA(String.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(isA(String.class), isA(Category.class))).thenReturn(mockQuery);
        when(mockQuery.list()).thenReturn(Arrays.asList(mockBeer));

        assertEquals(1, getDao().getBeerByCategory(new CategoryImpl("valid", "valid")).size());

        verify(mockSession).createQuery(isA(String.class));
        verify(mockQuery).setParameter(isA(String.class), isA(Category.class));
        verify(mockQuery).list();
    }

    /**
     * Test retrieving beer by origin without any results
     * 
     * @throws Exception
     */
    @Test
    public void testGetByOriginWithoutResults() throws Exception {
        when(mockSession.createQuery(isA(String.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(isA(String.class), isA(Origin.class))).thenReturn(mockQuery);
        when(mockQuery.list()).thenReturn(null);

        assertNull(getDao().getBeerByOrigin(new OriginImpl("invalid", "invalid")));

        verify(mockSession).createQuery(isA(String.class));
        verify(mockQuery).setParameter(isA(String.class), isA(Origin.class));
        verify(mockQuery).list();
    }

    /**
     * Test retrieving beer by origin with results
     * 
     * @throws Exception
     */
    @Test
    public void testGetByOriginWithResults() throws Exception {
        when(mockSession.createQuery(isA(String.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(isA(String.class), isA(Origin.class))).thenReturn(mockQuery);
        when(mockQuery.list()).thenReturn(Arrays.asList(mockBeer));

        assertEquals(1, getDao().getBeerByOrigin(new OriginImpl("valid", "valid")).size());

        verify(mockSession).createQuery(isA(String.class));
        verify(mockQuery).setParameter(isA(String.class), isA(Origin.class));
        verify(mockQuery).list();
    }

    /**
     * Test retrieving beer by brewery without any results
     * 
     * @throws Exception
     */
    @Test
    public void testGetByBreweryWithoutResults() throws Exception {
        when(mockSession.createQuery(isA(String.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(isA(String.class), isA(Brewery.class))).thenReturn(mockQuery);
        when(mockQuery.list()).thenReturn(null);

        assertNull(getDao().getBeerByBrewery(new BreweryImpl("invalid", "invalid")));

        verify(mockSession).createQuery(isA(String.class));
        verify(mockQuery).setParameter(isA(String.class), isA(Brewery.class));
        verify(mockQuery).list();
    }

    /**
     * Test retrieving beer by brewery with results
     * 
     * @throws Exception
     */
    @Test
    public void testGetByBreweryWithResults() throws Exception {
        when(mockSession.createQuery(isA(String.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(isA(String.class), isA(Brewery.class))).thenReturn(mockQuery);
        when(mockQuery.list()).thenReturn(Arrays.asList(mockBeer));

        assertEquals(1, getDao().getBeerByBrewery(new BreweryImpl("valid", "valid")).size());

        verify(mockSession).createQuery(isA(String.class));
        verify(mockQuery).setParameter(isA(String.class), isA(Brewery.class));
        verify(mockQuery).list();
    }
}

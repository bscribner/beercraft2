package com.benscribner.beercraft.model.impl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.base.testing.EqualsTester;

import com.benscribner.beercraft.model.Brewery;
import com.benscribner.beercraft.model.Category;
import com.benscribner.beercraft.model.Origin;

/**
 * Unit tests for the {@link BeerImpl} class
 */
public class BeerImplTest {

    private BeerImpl classUnderTest = null;

    private Category category1;
    private Category category2;
    private Brewery brewery1;
    private Brewery brewery2;
    private Origin origin1;
    private Origin origin2;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        classUnderTest = new BeerImpl();

        category1 = new CategoryImpl("category1", "description");
        category2 = new CategoryImpl("category2", "description");

        brewery1 = new BreweryImpl("brewery1", "description");
        brewery2 = new BreweryImpl("brewery2", "description");

        origin1 = new OriginImpl("origin1", "description");
        origin2 = new OriginImpl("origin2", "description");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        classUnderTest = null;
    }

    /**
     * Test the constructor which takes a name
     */
    @Test
    public void testConstructor() {
        classUnderTest = new BeerImpl("name", "description", category1, origin1, brewery1, "path/to/icon", 5.0, 10.0);
        assertEquals("name", classUnderTest.getName());
        assertEquals("description", classUnderTest.getDescription());
        assertEquals(category1, classUnderTest.getCategory());
        assertEquals(origin1, classUnderTest.getOrigin());
        assertEquals(brewery1, classUnderTest.getBrewery());
        assertEquals("path/to/icon", classUnderTest.getIcon());
        assertEquals(new Double(5.0), classUnderTest.getAlcoholContent());
        assertEquals(new Double(10.0), classUnderTest.getPrice());
    }

    /**
     * Test the getters and setters
     */
    @Test
    public void testGettersAndSetters() {
        classUnderTest.setName("name");
        classUnderTest.setDescription("description");
        classUnderTest.setOrigin(origin1);
        classUnderTest.setCategory(category1);
        classUnderTest.setBrewery(brewery1);
        classUnderTest.setIcon("path/to/icon");
        classUnderTest.setAlcoholContent(5.0);
        classUnderTest.setPrice(10.0);

        assertEquals("name", classUnderTest.getName());
        assertEquals("description", classUnderTest.getDescription());
        assertEquals(category1, classUnderTest.getCategory());
        assertEquals(origin1, classUnderTest.getOrigin());
        assertEquals(brewery1, classUnderTest.getBrewery());
        assertEquals("path/to/icon", classUnderTest.getIcon());
        assertEquals(new Double(5.0), classUnderTest.getAlcoholContent());
        assertEquals(new Double(10.0), classUnderTest.getPrice());
    }

    /**
     * Test the equals and hashcode methods
     */
    @Test
    @SuppressWarnings("serial")
    public void testEqualsAndHashcode() {
        final BeerImpl a = new BeerImpl("name", "description", category1, origin1, brewery1, "path/to/icon", 5.0, 10.0);
        final BeerImpl b = new BeerImpl("name", "description", category1, origin1, brewery1, "path/to/icon", 5.0, 10.0);
        final BeerImpl c = new BeerImpl("new name", "new description", category2, origin2, brewery2, "path/to/new/icon", 5.5, 10.5);
        final BeerImpl d = new BeerImpl("name", "description", category1, origin1, brewery1, "path/to/icon", 5.0, 10.0) {
        };

        new EqualsTester(a, b, c, d);
    }
}

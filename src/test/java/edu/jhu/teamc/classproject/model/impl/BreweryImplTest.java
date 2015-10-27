package com.benscribner.beercraft.model.impl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.base.testing.EqualsTester;

/**
 * Unit tests for the {@link BreweryImpl} class
 */
public class BreweryImplTest {

    private BreweryImpl classUnderTest = null;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        classUnderTest = new BreweryImpl();
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
        classUnderTest = new BreweryImpl("name", "description");
        assertEquals("name", classUnderTest.getName());
        assertEquals("description", classUnderTest.getDescription());
    }

    /**
     * Test the getters and setters
     */
    @Test
    public void testGettersAndSetters() {
        classUnderTest.setName("name");
        classUnderTest.setDescription("description");

        assertEquals("name", classUnderTest.getName());
        assertEquals("description", classUnderTest.getDescription());
    }

    /**
     * Test the equals and hashcode methods
     */
    @Test
    @SuppressWarnings("serial")
    public void testEqualsAndHashcode() {
        final BreweryImpl a = new BreweryImpl("name", "description");
        final BreweryImpl b = new BreweryImpl("name", "description");
        final BreweryImpl c = new BreweryImpl("different name", "different description");
        final BreweryImpl d = new BreweryImpl("name", "description") {
        };

        new EqualsTester(a, b, c, d);
    }
}

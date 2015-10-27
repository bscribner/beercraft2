package com.benscribner.beercraft.model.impl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.base.testing.EqualsTester;

import com.benscribner.beercraft.model.Beer;

/**
 * Unit tests for the {@link LineItemObject} class
 */
public class LineItemObjectTest {

    private LineItemObject classUnderTest = null;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        classUnderTest = new LineItemObject();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        classUnderTest = null;
    }

    /**
     * Test the constructor which takes a beer
     */
    @Test
    public void testConstructor() {
        final Beer beer = new BeerImpl("beer", "description", null, null, null, null, null, null);

        classUnderTest = new LineItemObject(beer);
        assertEquals(beer, classUnderTest.getBeer());
        assertEquals(new Integer(1), classUnderTest.getQuantity());
    }

    /**
     * Test the getters and setters
     */
    @Test
    public void testGettersAndSetters() {
        final Beer beer = new BeerImpl("beer", "description", null, null, null, null, null, null);

        classUnderTest.setBeer(beer);
        classUnderTest.setQuantity(5);

        assertEquals(beer, classUnderTest.getBeer());
        assertEquals(new Integer(5), classUnderTest.getQuantity());
    }

    /**
     * Test the equals and hashcode methods
     */
    @Test
    @SuppressWarnings("serial")
    public void testEqualsAndHashcode() {
        final Beer beer = new BeerImpl("beer", "description", null, null, null, null, null, null);
        final Beer beer2 = new BeerImpl("different beer", "description", null, null, null, null, null, null);

        final LineItemObject a = new LineItemObject(beer);
        final LineItemObject b = new LineItemObject(beer);
        final LineItemObject c = new LineItemObject(beer2);
        final LineItemObject d = new LineItemObject(beer) {
        };

        new EqualsTester(a, b, c, d);
    }
}
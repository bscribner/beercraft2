package com.benscribner.beercraft.model.impl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.base.testing.EqualsTester;

/**
 * Unit tests for the {@link OriginImpl} class
 */
public class OriginImplTest {

    private OriginImpl classUnderTest = null;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        classUnderTest = new OriginImpl();
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
        classUnderTest = new OriginImpl("name", "description");
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
        final OriginImpl a = new OriginImpl("name", "description");
        final OriginImpl b = new OriginImpl("name", "description");
        final OriginImpl c = new OriginImpl("different name", "different description");
        final OriginImpl d = new OriginImpl("name", "description") {
        };

        new EqualsTester(a, b, c, d);
    }
}

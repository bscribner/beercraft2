package com.benscribner.beercraft.model.impl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.base.testing.EqualsTester;

/**
 * Unit tests for the {@link CategoryImpl} class
 */
public class CategoryImplTest {

    private CategoryImpl classUnderTest = null;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        classUnderTest = new CategoryImpl();
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
        classUnderTest = new CategoryImpl("name", "description");
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
        final CategoryImpl a = new CategoryImpl("name", "description");
        final CategoryImpl b = new CategoryImpl("name", "description");
        final CategoryImpl c = new CategoryImpl("different name", "different description");
        final CategoryImpl d = new CategoryImpl("name", "description") {
        };

        new EqualsTester(a, b, c, d);
    }
}

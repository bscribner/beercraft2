package com.benscribner.beercraft.model.impl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.base.testing.EqualsTester;

/**
 * Unit tests for the {@link RoleImpl} class
 */
public class RoleImplTest {

    private RoleImpl classUnderTest = null;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        classUnderTest = new RoleImpl();
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
        classUnderTest = new RoleImpl("name");
        assertEquals("name", classUnderTest.getName());
    }

    /**
     * Test the getters and setters
     */
    @Test
    public void testGettersAndSetters() {
        classUnderTest.setName("name");
        classUnderTest.setDescription("description");

        assertEquals("name", classUnderTest.getName());
        assertEquals("name", classUnderTest.getAuthority());
        assertEquals("description", classUnderTest.getDescription());
    }

    /**
     * Test the equals and hashcode methods
     */
    @Test
    @SuppressWarnings("serial")
    public void testEqualsAndHashcode() {
        final RoleImpl a = new RoleImpl("name");
        final RoleImpl b = new RoleImpl("name");
        final RoleImpl c = new RoleImpl("different name");
        final RoleImpl d = new RoleImpl("name") {
        };

        new EqualsTester(a, b, c, d);
    }
}

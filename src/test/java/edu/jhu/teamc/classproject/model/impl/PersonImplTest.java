package com.benscribner.beercraft.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.base.testing.EqualsTester;

/**
 * Unit tests for the {@link PersonImpl} class
 */
public class PersonImplTest {

    private PersonImpl classUnderTest = null;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        classUnderTest = new PersonImpl();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        classUnderTest = null;
    }

    /**
     * Test the constructor which takes a first and last name
     */
    @Test
    public void testConstructor() {
        classUnderTest = new PersonImpl("firstname", "lastname");
        assertEquals("firstname", classUnderTest.getFirstName());
        assertEquals("lastname", classUnderTest.getLastName());
    }

    /**
     * Test the getters and setters
     */
    @Test
    public void testGettersAndSetters() {
        classUnderTest.setFirstName("firstname");
        classUnderTest.setLastName("lastname");
        classUnderTest.setEmail("email");
        classUnderTest.setPhoneNumber("day phone");

        assertEquals("firstname", classUnderTest.getFirstName());
        assertEquals("lastname", classUnderTest.getLastName());
        assertEquals("lastname, firstname", classUnderTest.getFullName());
        assertEquals("email", classUnderTest.getEmail());
        assertEquals("day phone", classUnderTest.getPhoneNumber());

        assertNotNull(classUnderTest.getAddress());
    }

    /**
     * Test the equals and hashcode methods
     */
    @Test
    @SuppressWarnings("serial")
    public void testEqualsAndHashcode() {
        final PersonImpl a = new PersonImpl("first", "last");
        final PersonImpl b = new PersonImpl("first", "last");
        final PersonImpl c = new PersonImpl("different first", "different last");
        final PersonImpl d = new PersonImpl("first", "last") {
        };

        new EqualsTester(a, b, c, d);
    }
}

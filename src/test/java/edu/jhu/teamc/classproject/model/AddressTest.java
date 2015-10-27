package com.benscribner.beercraft.model;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.base.testing.EqualsTester;

/**
 * Unit tests for the {@link Address} class
 */
public class AddressTest {

    private Address classUnderTest = null;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        classUnderTest = new Address();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        classUnderTest = null;
    }

    /**
     * Test the getters and setters
     */
    @Test
    public void testGettersAndSetters() {
        classUnderTest.setAddress1("address1");
        classUnderTest.setAddress2("address2");
        classUnderTest.setAddress3("address3");
        classUnderTest.setCity("city");
        classUnderTest.setState("state");
        classUnderTest.setCountry("country");
        classUnderTest.setPostalCode("postalCode");

        assertEquals("address1", classUnderTest.getAddress1());
        assertEquals("address2", classUnderTest.getAddress2());
        assertEquals("address3", classUnderTest.getAddress3());
        assertEquals("city", classUnderTest.getCity());
        assertEquals("state", classUnderTest.getState());
        assertEquals("country", classUnderTest.getCountry());
        assertEquals("postalCode", classUnderTest.getPostalCode());
    }

    /**
     * Test the equals and hashcode methods
     */
    @Test
    @SuppressWarnings("serial")
    public void testEqualsAndHashcode() {
        final Address a = new Address();
        final Address b = new Address();
        final Address c = new Address();
        c.setAddress1("something different");

        final Address d = new Address() {
        };

        new EqualsTester(a, b, c, d);
    }
}

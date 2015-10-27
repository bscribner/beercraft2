package com.benscribner.beercraft.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.base.testing.EqualsTester;

import com.benscribner.beercraft.model.Address;
import com.benscribner.beercraft.model.CreditCardType;

/**
 * Unit tests for the {@link ConcreteCreditCard} class
 */
public class ConcreteCreditCardTest {

    private ConcreteCreditCard classUnderTest = null;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        classUnderTest = new ConcreteCreditCard();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        classUnderTest = null;
    }

    /**
     * Test the constructor which takes a date, number, and type
     */
    @Test
    public void testConstructor() {
        final Date date = new Date();

        classUnderTest = new ConcreteCreditCard(date, "1234", CreditCardType.VISA);
        assertEquals(date, classUnderTest.getExpirationDate());
        assertEquals("1234", classUnderTest.getNumber());
        assertEquals(CreditCardType.VISA, classUnderTest.getType());
    }

    /**
     * Test the getters and setters
     */
    @Test
    public void testGettersAndSetters() {
        final Date date = new Date();

        final Address address = new Address();
        address.setAddress1("address1");
        address.setAddress2("address2");
        address.setAddress3("address3");
        address.setCity("city");
        address.setState("state");
        address.setCountry("country");
        address.setPostalCode("postalCode");

        classUnderTest.setExpirationDate(date);
        classUnderTest.setNumber("1234");
        classUnderTest.setType(CreditCardType.VISA);
        classUnderTest.setBillingAddress(address);

        assertEquals(date, classUnderTest.getExpirationDate());
        assertEquals("1234", classUnderTest.getNumber());
        assertEquals(CreditCardType.VISA, classUnderTest.getType());
        assertEquals(address, classUnderTest.getBillingAddress());

        // reset the billing address
        classUnderTest.setBillingAddress(null);
        assertNull(classUnderTest.getBillingAddress());

        classUnderTest.setBillingAddress("address1", "address2", "address3", "city", "state", "postalCode", "country");
        assertEquals(address, classUnderTest.getBillingAddress());
    }

    /**
     * Test the equals and hashcode methods
     */
    @Test
    @SuppressWarnings("serial")
    public void testEqualsAndHashcode() {
        final Date date = new Date();
        final Date date2 = new Date();

        final ConcreteCreditCard a = new ConcreteCreditCard(date, "1234", CreditCardType.VISA);
        final ConcreteCreditCard b = new ConcreteCreditCard(date, "1234", CreditCardType.VISA);
        final ConcreteCreditCard c = new ConcreteCreditCard(date2, "4321", CreditCardType.AMERICAN_EXPRESS);
        final ConcreteCreditCard d = new ConcreteCreditCard(date, "1234", CreditCardType.VISA) {
        };

        new EqualsTester(a, b, c, d);
    }
}

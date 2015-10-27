package com.benscribner.beercraft.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.base.testing.EqualsTester;

import com.benscribner.beercraft.model.Address;
import com.benscribner.beercraft.model.Beer;
import com.benscribner.beercraft.model.CreditCard;
import com.benscribner.beercraft.model.CreditCardType;
import com.benscribner.beercraft.model.LineItem;
import com.benscribner.beercraft.model.Person;

/**
 * Unit tests for the {@link PurchaseOrder} class
 */
public class PurchaseOrderTest {

    private PurchaseOrder classUnderTest = null;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        classUnderTest = new PurchaseOrder();
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
        final Date date = new Date();
        final Person person = new PersonImpl("first", "last");
        final CreditCard card = new ConcreteCreditCard(date, "1234", CreditCardType.VISA);
        final Beer beer = new BeerImpl("beer", "description", null, null, null, null, null, null);
        final LineItem item = new LineItemObject(beer);

        final Address address = new Address();
        address.setAddress1("address1");
        address.setAddress2("address2");
        address.setAddress3("address3");
        address.setCity("city");
        address.setState("state");
        address.setCountry("country");
        address.setPostalCode("postalCode");

        classUnderTest.setOrderNumber(1000L);
        classUnderTest.setPerson(person);
        classUnderTest.setCardNumber(card);
        classUnderTest.setShipping(address);
        classUnderTest.getLineItems().add(item);

        assertEquals(new Long("1000"), classUnderTest.getOrderNumber());
        assertEquals(person, classUnderTest.getPerson());
        assertEquals(card, classUnderTest.getCardNumber());
        assertEquals(address, classUnderTest.getShipping());

        assertEquals(1, classUnderTest.getLineItems().size());
        assertEquals(item, classUnderTest.getLineItems().get(0));

        // reset the billing address
        classUnderTest.setShipping(null);
        assertNull(classUnderTest.getShipping());

        classUnderTest.setShippingAddress("address1", "address2", "address3", "city", "state", "postalCode", "country");
        assertEquals(address, classUnderTest.getShipping());

        // reset the card number
        classUnderTest.setCardNumber(null);
        assertNull(classUnderTest.getCardNumber());

        classUnderTest.setCredit(date, "1234", CreditCardType.VISA);
        assertEquals(card, classUnderTest.getCardNumber());
    }

    /**
     * Test the equals and hashcode methods
     */
    @Test
    @SuppressWarnings("serial")
    public void testEqualsAndHashcode() {
        final Person person = new PersonImpl("first", "last");
        final Person person2 = new PersonImpl("different first", "different last");
        final CreditCard card = new ConcreteCreditCard(new Date(), "1234", CreditCardType.VISA);
        final CreditCard card2 = new ConcreteCreditCard(new Date(), "4321", CreditCardType.AMERICAN_EXPRESS);
        final Beer beer = new BeerImpl("beer", "description", null, null, null, null, null, null);
        final Beer beer2 = new BeerImpl("different beer", "different description", null, null, null, null, null, null);
        final LineItem item = new LineItemObject(beer);
        final LineItem item2 = new LineItemObject(beer2);

        final PurchaseOrder a = new PurchaseOrder();
        a.setPerson(person);
        a.setCardNumber(card);
        a.setOrderNumber(1000L);
        a.getLineItems().add(item);

        final PurchaseOrder b = new PurchaseOrder();
        b.setPerson(person);
        b.setCardNumber(card);
        b.setOrderNumber(1000L);
        b.getLineItems().add(item);

        final PurchaseOrder c = new PurchaseOrder();
        c.setPerson(person2);
        c.setCardNumber(card2);
        c.setOrderNumber(2000L);
        c.getLineItems().add(item2);

        final PurchaseOrder d = new PurchaseOrder() {
        };
        d.setPerson(person);
        d.setCardNumber(card);
        d.setOrderNumber(1000L);
        d.getLineItems().add(item);

        new EqualsTester(a, b, c, d);
    }
}

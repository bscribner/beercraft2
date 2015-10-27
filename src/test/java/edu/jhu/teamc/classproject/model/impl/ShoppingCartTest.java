package com.benscribner.beercraft.model.impl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.base.testing.EqualsTester;

import com.benscribner.beercraft.model.Beer;
import com.benscribner.beercraft.model.LineItem;
import com.benscribner.beercraft.model.Person;

/**
 * Unit tests for the {@link ShoppingCart} class
 */
public class ShoppingCartTest {

    private ShoppingCart classUnderTest = null;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        classUnderTest = new ShoppingCart();
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
        final Person person = new PersonImpl("first", "last");
        final Beer beer = new BeerImpl("beer", "description", null, null, null, null, null, null);
        final LineItem item = new LineItemObject(beer);

        classUnderTest.setPerson(person);
        classUnderTest.getLineItems().add(item);

        assertEquals(person, classUnderTest.getPerson());

        assertEquals(1, classUnderTest.getLineItems().size());
        assertEquals(item, classUnderTest.getLineItems().get(0));
    }

    /**
     * Test the equals and hashcode methods
     */
    @Test
    @SuppressWarnings("serial")
    public void testEqualsAndHashcode() {
        final Person person = new PersonImpl("first", "last");
        final Person person2 = new PersonImpl("different first", "different last");
        final Beer beer = new BeerImpl("beer", "description", null, null, null, null, null, null);
        final Beer beer2 = new BeerImpl("different beer", "different description", null, null, null, null, null, null);
        final LineItem item = new LineItemObject(beer);
        final LineItem item2 = new LineItemObject(beer2);

        final ShoppingCart a = new ShoppingCart();
        a.setPerson(person);
        a.getLineItems().add(item);

        final ShoppingCart b = new ShoppingCart();
        b.setPerson(person);
        b.getLineItems().add(item);

        final ShoppingCart c = new ShoppingCart();
        c.setPerson(person2);
        c.getLineItems().add(item2);

        final ShoppingCart d = new ShoppingCart() {
        };
        d.setPerson(person);
        d.getLineItems().add(item);

        new EqualsTester(a, b, c, d);
    }
}

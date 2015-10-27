package com.benscribner.beercraft.dao.hibernate.integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.benscribner.beercraft.dao.IdentifiableDao;
import com.benscribner.beercraft.dao.hibernate.BeerDaoHibernate;
import com.benscribner.beercraft.dao.hibernate.BreweryDaoHibernate;
import com.benscribner.beercraft.dao.hibernate.CategoryDaoHibernate;
import com.benscribner.beercraft.dao.hibernate.OrderDaoHibernate;
import com.benscribner.beercraft.dao.hibernate.OriginDaoHibernate;
import com.benscribner.beercraft.dao.hibernate.UserDaoHibernate;
import com.benscribner.beercraft.model.Address;
import com.benscribner.beercraft.model.Brewery;
import com.benscribner.beercraft.model.Category;
import com.benscribner.beercraft.model.CreditCardType;
import com.benscribner.beercraft.model.LineItem;
import com.benscribner.beercraft.model.Order;
import com.benscribner.beercraft.model.Origin;
import com.benscribner.beercraft.model.impl.BeerImpl;
import com.benscribner.beercraft.model.impl.BreweryImpl;
import com.benscribner.beercraft.model.impl.CategoryImpl;
import com.benscribner.beercraft.model.impl.ConcreteCreditCard;
import com.benscribner.beercraft.model.impl.LineItemObject;
import com.benscribner.beercraft.model.impl.OriginImpl;
import com.benscribner.beercraft.model.impl.PersonImpl;
import com.benscribner.beercraft.model.impl.PurchaseOrder;
import com.benscribner.beercraft.model.impl.UserImpl;

/**
 * Unit tests for the {@link OrderDaoHibernate} class
 */
public class OrderDaoHibernateIntegrationTest extends AbstractHibernateIntegrationTest<Order> {

    @Autowired
    private OrderDaoHibernate orderDao;

    @Autowired
    private UserDaoHibernate userDao;

    @Autowired
    private BeerDaoHibernate beerDao;

    @Autowired
    private BreweryDaoHibernate breweryDao;

    @Autowired
    private CategoryDaoHibernate categoryDao;

    @Autowired
    private OriginDaoHibernate originDao;

    private PersonImpl person;
    private BeerImpl beer;

    /**
     * Setup the test
     */
    @Before
    public void setup() {
        final UserImpl user = new UserImpl("username");
        user.setPassword("password");
        person = new PersonImpl("firstname", "lastname");
        person.setEmail("email@email.com");
        person.setPhoneNumber("phone");
        user.setPerson(person);
        userDao.save(user);

        Category category = categoryDao.save(new CategoryImpl("name", "description"));
        Origin origin = originDao.save(new OriginImpl("name", "description"));
        Brewery brewery = breweryDao.save(new BreweryImpl("name", "description"));

        beer = new BeerImpl("name", "description", category, origin, brewery, null, 5.5, 2.99);
        beerDao.save(beer);
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#getDao()
     */
    @Override
    protected IdentifiableDao<Order> getDao() {
        return orderDao;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#createObject()
     */
    @Override
    protected Order createObject() {
        final Address address = new Address();
        address.setAddress1("address1");
        address.setAddress2("address2");
        address.setAddress3("address3");
        address.setCity("city");
        address.setState("state");
        address.setCountry("country");
        address.setPostalCode("postalCode");

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, 1);

        final ConcreteCreditCard card = new ConcreteCreditCard(cal.getTime(), "4532691470629762", CreditCardType.VISA);
        card.setBillingAddress(address);

        final PurchaseOrder order = new PurchaseOrder();
        order.setPerson(person);
        order.setCardNumber(card);
        order.setShipping(address);

        return order;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#updateObject(com.benscribner.beercraft.model.Identifiable)
     */
    @Override
    protected void updateObject(final Order order) {
        final PurchaseOrder purchaseOrder = (PurchaseOrder) order;

        final LineItem lineItem = new LineItemObject(beer);

        purchaseOrder.getLineItems().add(lineItem);
    }

    /**
     * Test retrieving an object by person
     * 
     * @throws Exception
     */
    @Test
    public void testGetByPerson() throws Exception {
        final Order order = createObject();
        getDao().save(order);
        flush();

        final List<Order> ordersFromDb = orderDao.getForPerson(person);
        assertNotNull(ordersFromDb);
        assertTrue(ordersFromDb.size() > 0);
    }
}
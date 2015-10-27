package com.benscribner.beercraft.dao.hibernate.integration;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.benscribner.beercraft.dao.IdentifiableDao;
import com.benscribner.beercraft.dao.hibernate.BeerDaoHibernate;
import com.benscribner.beercraft.dao.hibernate.BreweryDaoHibernate;
import com.benscribner.beercraft.dao.hibernate.CartDaoHibernate;
import com.benscribner.beercraft.dao.hibernate.CategoryDaoHibernate;
import com.benscribner.beercraft.dao.hibernate.OriginDaoHibernate;
import com.benscribner.beercraft.dao.hibernate.UserDaoHibernate;
import com.benscribner.beercraft.model.Brewery;
import com.benscribner.beercraft.model.Cart;
import com.benscribner.beercraft.model.Category;
import com.benscribner.beercraft.model.LineItem;
import com.benscribner.beercraft.model.Origin;
import com.benscribner.beercraft.model.impl.BeerImpl;
import com.benscribner.beercraft.model.impl.BreweryImpl;
import com.benscribner.beercraft.model.impl.CategoryImpl;
import com.benscribner.beercraft.model.impl.LineItemObject;
import com.benscribner.beercraft.model.impl.OriginImpl;
import com.benscribner.beercraft.model.impl.PersonImpl;
import com.benscribner.beercraft.model.impl.ShoppingCart;
import com.benscribner.beercraft.model.impl.UserImpl;

/**
 * Unit tests for the {@link CartDaoHibernate} class
 */
public class CartDaoHibernateIntegrationTest extends AbstractHibernateIntegrationTest<Cart> {

    @Autowired
    private CartDaoHibernate cartDao;

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
    protected IdentifiableDao<Cart> getDao() {
        return cartDao;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#createObject()
     */
    @Override
    protected Cart createObject() {
        final ShoppingCart cart = new ShoppingCart();
        cart.setPerson(person);

        return cart;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#updateObject(com.benscribner.beercraft.model.Identifiable)
     */
    @Override
    protected void updateObject(final Cart cart) {
        final ShoppingCart shoppingCart = (ShoppingCart) cart;

        final LineItem lineItem = new LineItemObject(beer);

        shoppingCart.getLineItems().add(lineItem);
    }

    /**
     * Test retrieving an object by person
     * 
     * @throws Exception
     */
    @Test
    public void testGetByPerson() throws Exception {
        final Cart cart = createObject();
        getDao().save(cart);
        flush();

        final Cart cartFromDb = cartDao.getForPerson(person);
        assertNotNull(cartFromDb);
    }
}
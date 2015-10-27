package com.benscribner.beercraft.dao.hibernate;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mock;

import com.benscribner.beercraft.model.Cart;
import com.benscribner.beercraft.model.Person;
import com.benscribner.beercraft.model.impl.PersonImpl;
import com.benscribner.beercraft.model.impl.ShoppingCart;

/**
 * Unit tests for the {@link CartDaoHibernate} class
 */
public class CartDaoHibernateTest extends GenericIdentifiableDaoHibernateTest<Cart, CartDaoHibernate> {

    @Mock
    private Cart mockCart;

    /**
     * @see com.benscribner.beercraft.dao.hibernate.GenericIdentifiableDaoHibernateTest#getPersistentClass()
     */
    @Override
    protected Class<ShoppingCart> getPersistentClass() {
        return ShoppingCart.class;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.GenericIdentifiableDaoHibernateTest#getDao()
     */
    @Override
    protected CartDaoHibernate getDao() {
        final CartDaoHibernate dao = new CartDaoHibernate();
        dao.setSessionFactory(mockSessionFactory);
        return dao;
    }

    /**
     * Test retrieving by a person
     * 
     * @throws Exception
     */
    @Test
    public void testGetByPerson() throws Exception {
        when(mockSession.createQuery(isA(String.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(isA(String.class), isA(Person.class))).thenReturn(mockQuery);
        when(mockQuery.uniqueResult()).thenReturn(mockCart);

        getDao().getForPerson(new PersonImpl("first name", "last name"));

        verify(mockSession).createQuery(isA(String.class));
        verify(mockQuery).setParameter(isA(String.class), isA(Person.class));
        verify(mockQuery).uniqueResult();
    }
}
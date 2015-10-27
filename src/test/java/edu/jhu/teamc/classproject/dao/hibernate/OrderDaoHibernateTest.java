package com.benscribner.beercraft.dao.hibernate;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.UUID;

import org.junit.Test;
import org.mockito.Mock;

import com.benscribner.beercraft.model.Order;
import com.benscribner.beercraft.model.Person;
import com.benscribner.beercraft.model.impl.PersonImpl;
import com.benscribner.beercraft.model.impl.PurchaseOrder;

/**
 * Unit tests for the {@link OrderDaoHibernate} class
 */
public class OrderDaoHibernateTest extends GenericIdentifiableDaoHibernateTest<Order, OrderDaoHibernate> {

    @Mock
    private PurchaseOrder mockOrder;

    /**
     * @see com.benscribner.beercraft.dao.hibernate.GenericIdentifiableDaoHibernateTest#getPersistentClass()
     */
    @Override
    protected Class<PurchaseOrder> getPersistentClass() {
        return PurchaseOrder.class;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.GenericIdentifiableDaoHibernateTest#getDao()
     */
    @Override
    protected OrderDaoHibernate getDao() {
        final OrderDaoHibernate dao = new OrderDaoHibernate();
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
        when(mockQuery.list()).thenReturn(Arrays.asList(mockOrder));

        getDao().getForPerson(new PersonImpl("first name", "last name"));

        verify(mockSession).createQuery(isA(String.class));
        verify(mockQuery).setParameter(isA(String.class), isA(Person.class));
        verify(mockQuery).list();
    }

    /**
     * Test the save method on a new object
     */
    @Override
    @Test
    public void testSaveNew() {
        when(mockOrder.getId()).thenReturn(null);
        when(mockOrder.getOrderNumber()).thenReturn(null);

        when(mockSession.createQuery(isA(String.class))).thenReturn(mockQuery);
        when(mockQuery.uniqueResult()).thenReturn(1000L);

        getDao().save(mockOrder);

        verify(mockSession).persist(eq(mockOrder));
        verify(mockSession).createQuery(isA(String.class));
        verify(mockQuery).uniqueResult();
    }

    /**
     * Test the save method on an existing object
     */
    @Override
    @Test
    public void testSaveExisting() {
        when(mockOrder.getId()).thenReturn(UUID.randomUUID().toString());
        when(mockSession.merge(isA(getPersistentClass()))).thenReturn(mockOrder);

        getDao().save(mockOrder);

        verify(mockSession).merge(eq(mockOrder));
    }
}
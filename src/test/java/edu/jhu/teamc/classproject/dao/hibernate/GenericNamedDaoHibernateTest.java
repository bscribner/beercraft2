package com.benscribner.beercraft.dao.hibernate;

import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.benscribner.beercraft.dao.NamedObjectDao;
import com.benscribner.beercraft.model.Identifiable;
import com.benscribner.beercraft.model.Named;

/**
 * Unit tests for the {@link GenericNamedDaoHibernate} class
 */
public abstract class GenericNamedDaoHibernateTest<T extends Identifiable & Named, DAO extends NamedObjectDao<T>> extends
        GenericIdentifiableDaoHibernateTest<T, DAO> {

    /**
     * Test retrieving an invalid named object
     * 
     * @throws Exception
     */
    @Test
    public void testGetByNameInvalid() throws Exception {
        when(mockSession.createQuery(isA(String.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(isA(String.class), isA(String.class))).thenReturn(mockQuery);
        when(mockQuery.uniqueResult()).thenReturn(null);

        assertNull(getDao().getByName("invalid"));

        verify(mockSession).createQuery(isA(String.class));
        verify(mockQuery).setParameter(isA(String.class), isA(String.class));
        verify(mockQuery).uniqueResult();
    }

    /**
     * Test retrieving an object by name
     * 
     * @throws Exception
     */
    @Test
    public void testGetByName() throws Exception {
        when(mockSession.createQuery(isA(String.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(isA(String.class), isA(String.class))).thenReturn(mockQuery);
        when(mockQuery.uniqueResult()).thenReturn(mockPersistentObject);

        getDao().getByName("valid");

        verify(mockSession).createQuery(isA(String.class));
        verify(mockQuery).setParameter(isA(String.class), isA(String.class));
        verify(mockQuery).uniqueResult();
    }
}
package com.benscribner.beercraft.dao.hibernate;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.benscribner.beercraft.dao.IdentifiableDao;
import com.benscribner.beercraft.model.Identifiable;

/**
 * Unit tests for the {@link GenericIdentifiableDaoHibernate} class
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(GenericIdentifiableDaoHibernate.class)
public abstract class GenericIdentifiableDaoHibernateTest<T extends Identifiable, DAO extends IdentifiableDao<T>> {

    @Mock
    protected T mockPersistentObject;

    @Mock
    protected SessionFactory mockSessionFactory;

    @Mock
    protected Session mockSession;

    @Mock
    protected Query mockQuery;

    /**
     * Retrieve the persistent class
     * 
     * @return the persistent class
     */
    protected abstract Class<? extends T> getPersistentClass();

    /**
     * Retrieve the DAO to be used for this test
     * 
     * @return the DAO to be used for this test
     */
    protected abstract DAO getDao();

    /**
     * Set up the test classes
     * 
     * @throws Exception
     */
    @Before
    public void setup() throws Exception {
        when(mockSessionFactory.getCurrentSession()).thenReturn(mockSession);
    }

    /**
     * Test the getAll method
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetAll() {
        when(mockSession.createQuery(isA(String.class))).thenReturn(mockQuery);
        when(mockQuery.list()).thenReturn(Arrays.asList(mockPersistentObject, mockPersistentObject));

        assertNotNull(getDao().getAll());

        verify(mockSession).createQuery(isA(String.class));
        verify(mockQuery).list();
    }

    /**
     * Test the get method
     */
    @Test
    public void testGet() {
        when(mockSession.get(eq(getPersistentClass()), isA(String.class))).thenReturn(mockPersistentObject);

        assertNotNull(getDao().get(UUID.randomUUID().toString()));

        verify(mockSession).get(eq(getPersistentClass()), isA(String.class));
    }

    /**
     * Test the get method with a null return object
     */
    @Test
    public void testGetReturnNull() {
        when(mockSession.get(eq(getPersistentClass()), isA(String.class))).thenReturn(null);

        assertNull(getDao().get(UUID.randomUUID().toString()));

        verify(mockSession).get(eq(getPersistentClass()), isA(String.class));
    }

    /**
     * Test the exists method
     */
    @Test
    public void testExists() {
        when(mockSession.get(eq(getPersistentClass()), isA(String.class))).thenReturn(mockPersistentObject);

        assertTrue(getDao().exists(UUID.randomUUID().toString()));

        verify(mockSession).get(eq(getPersistentClass()), isA(String.class));
    }

    /**
     * Test the save method on a new object
     */
    @Test
    public void testSaveNew() {
        when(mockPersistentObject.getId()).thenReturn(null);

        getDao().save(mockPersistentObject);

        verify(mockSession).persist(eq(mockPersistentObject));
    }

    /**
     * Test the save method on an existing object
     */
    @Test
    public void testSaveExisting() {
        when(mockPersistentObject.getId()).thenReturn(UUID.randomUUID().toString());
        when(mockSession.merge(isA(getPersistentClass()))).thenReturn(mockPersistentObject);

        getDao().save(mockPersistentObject);

        verify(mockSession).merge(eq(mockPersistentObject));
    }

    /**
     * Test the delete method
     */
    @Test
    public void testDelete() {
        when(mockSession.get(eq(getPersistentClass()), isA(String.class))).thenReturn(mockPersistentObject);

        getDao().delete(UUID.randomUUID().toString());
        getDao().delete(mockPersistentObject);

        verify(mockSession).get(eq(getPersistentClass()), isA(String.class));
        verify(mockSession, times(2)).delete(eq(mockPersistentObject));
    }
}
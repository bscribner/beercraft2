package com.benscribner.beercraft.dao.hibernate.integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.benscribner.beercraft.dao.hibernate.GenericNamedDaoHibernate;
import com.benscribner.beercraft.model.Identifiable;
import com.benscribner.beercraft.model.Named;

/**
 * Unit tests for the {@link GenericNamedDaoHibernate} class
 */
public abstract class GenericNamedDaoHibernateIntegrationTest<T extends Identifiable & Named> extends AbstractHibernateIntegrationTest<T> {

    /**
     * Retrieve the DAO to be used for this test
     * 
     * @return the DAO to be used for this test
     */
    @Override
    protected abstract GenericNamedDaoHibernate<T> getDao();

    /**
     * Test retrieving an invalid named object
     * 
     * @throws Exception
     */
    @Test
    public void testGetByNameInvalid() throws Exception {
        final T object = getDao().getByName("invalid");
        assertNull(object);
    }

    /**
     * Test retrieving an object by name
     * 
     * @throws Exception
     */
    @Test
    public void testGetByName() throws Exception {
        final T object = createObject();
        getDao().save(object);
        flush();

        final T objectFromDB = getDao().getByName(object.getName());
        assertNotNull(objectFromDB);
    }
}
package com.benscribner.beercraft.dao.hibernate.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.benscribner.beercraft.dao.IdentifiableDao;
import com.benscribner.beercraft.model.Identifiable;

/**
 * Base class for running model integration tests.
 */
@ContextConfiguration(locations = { "classpath:/applicationContext-resources.xml", "classpath:/applicationContext-dao.xml",
        "classpath:/applicationContext.xml" })
public abstract class AbstractHibernateIntegrationTest<T extends Identifiable> extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;

    protected final Log log = LogFactory.getLog(getClass());

    /**
     * Retrieve the DAO to be used for this test
     * 
     * @return the DAO to be used for this test
     */
    protected abstract IdentifiableDao<T> getDao();

    /**
     * Create object for persistence
     * 
     * @return the object to be persisted
     */
    protected abstract T createObject();

    /**
     * Update the object for persistence
     * 
     * @return the updated item to be persisted
     */
    protected abstract void updateObject(T object);

    /**
     * Test the getAll method
     * 
     * @throws Exception
     */
    @Test
    public void testGetAll() throws Exception {
        assertNotNull(getDao().getAll());
    }

    /**
     * Test the get method with an invalid id
     * 
     * @throws Exception
     */
    @Test
    public void testGetInvalid() throws Exception {
        // pick a random UUID that should not equal anything in the database
        final T object = getDao().get(UUID.randomUUID().toString());

        assertNull(object);
    }

    /**
     * Test the get method
     * 
     * @throws Exception
     */
    @Test
    public void testGet() throws Exception {
        final T object = createObject();
        final String id = getDao().save(object).getId();

        final T objectFromDatabase = getDao().get(id);

        assertNotNull(objectFromDatabase);
    }

    /**
     * Test adding and removing
     * 
     * @throws Exception
     */
    @Test
    public void testAddAndRemove() throws Exception {
        final T object = createObject();
        final String id = getDao().save(object).getId();
        assertNotNull(id);
        flush();

        T objectFromDB = getDao().get(id);
        assertNotNull(objectFromDB);

        getDao().delete(objectFromDB);
        flush();

        objectFromDB = getDao().get(id);
        assertNull(objectFromDB);
    }

    /**
     * Test updating
     * 
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        final T object = createObject();
        final String id = getDao().save(object).getId();
        assertNotNull(id);
        flush();

        T objectFromDB = getDao().get(id);
        assertNotNull(objectFromDB);

        updateObject(objectFromDB);
        getDao().save(objectFromDB);
        flush();

        T newObjectFromDB = getDao().get(id);
        assertEquals(objectFromDB, newObjectFromDB);
    }

    /**
     * Create a HibernateTemplate from the SessionFactory and call flush() and
     * clear() on it.
     */
    protected void flush() throws BeansException {
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();
    }
}

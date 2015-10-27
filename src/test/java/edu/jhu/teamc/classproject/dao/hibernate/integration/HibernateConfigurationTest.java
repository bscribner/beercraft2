package com.benscribner.beercraft.dao.hibernate.integration;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.EntityPersister;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * Test the hibernate configuration
 * 
 * Original version located here:
 * https://github.com/appfuse/appfuse/blob/master/data/hibernate/src/test/java/org/appfuse/dao/hibernate/HibernateConfigurationTest.java
 */
@ContextConfiguration(locations = { "classpath:/applicationContext-resources.xml", "classpath:/applicationContext-dao.xml",
        "classpath:/applicationContext.xml" })
public class HibernateConfigurationTest extends AbstractTransactionalJUnit4SpringContextTests {

    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Tests the column mapping
     * 
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testColumnMapping() throws Exception {
        Session session = sessionFactory.openSession();
        try {
            Map metadata = sessionFactory.getAllClassMetadata();
            for (Object o : metadata.values()) {
                EntityPersister persister = (EntityPersister) o;
                String className = persister.getEntityName();
                log.debug("Trying select * from: " + className);
                Query q = session.createQuery("from " + className + " c");
                q.iterate();
                log.debug("ok: " + className);
            }
        } finally {
            session.close();
        }
    }
}

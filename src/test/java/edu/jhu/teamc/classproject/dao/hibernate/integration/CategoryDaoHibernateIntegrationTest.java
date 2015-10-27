package com.benscribner.beercraft.dao.hibernate.integration;

import org.springframework.beans.factory.annotation.Autowired;

import com.benscribner.beercraft.dao.hibernate.CategoryDaoHibernate;
import com.benscribner.beercraft.model.Category;
import com.benscribner.beercraft.model.impl.CategoryImpl;

/**
 * Unit tests for the {@link CategoryDaoHibernate} class
 */
public class CategoryDaoHibernateIntegrationTest extends GenericNamedDaoHibernateIntegrationTest<Category> {

    @Autowired
    private CategoryDaoHibernate categoryDao;

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.GenericNamedDaoHibernateIntegrationTest#getDao()
     */
    @Override
    protected CategoryDaoHibernate getDao() {
        return categoryDao;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#createObject()
     */
    @Override
    protected Category createObject() {
        final CategoryImpl category = new CategoryImpl("test category", "test description");

        return category;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#updateObject(com.benscribner.beercraft.model.Identifiable)
     */
    @Override
    protected void updateObject(final Category category) {
        CategoryImpl updatedCategory = (CategoryImpl) category;
        updatedCategory.setName("new name");
        updatedCategory.setDescription("new description");
    }
}

package com.benscribner.beercraft.dao.hibernate;

import com.benscribner.beercraft.model.Category;
import com.benscribner.beercraft.model.impl.CategoryImpl;

/**
 * Unit tests for the {@link CategoryDaoHibernate} class
 */
public class CategoryDaoHibernateTest extends GenericNamedDaoHibernateTest<Category, CategoryDaoHibernate> {

    /**
     * @see com.benscribner.beercraft.dao.hibernate.GenericIdentifiableDaoHibernateTest#getDao()
     */
    @Override
    protected CategoryDaoHibernate getDao() {
        CategoryDaoHibernate dao = new CategoryDaoHibernate();
        dao.setSessionFactory(mockSessionFactory);
        return dao;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.GenericIdentifiableDaoHibernateTest#getPersistentClass()
     */
    @Override
    protected Class<CategoryImpl> getPersistentClass() {
        return CategoryImpl.class;
    }
}

package com.benscribner.beercraft.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.benscribner.beercraft.model.Category;
import com.benscribner.beercraft.model.impl.CategoryImpl;

/**
 * A DAO to persist Categories in Hibernate
 */
@Repository("categoryDao")
public class CategoryDaoHibernate extends GenericNamedDaoHibernate<Category> {

    /**
     * Constructor
     */
    public CategoryDaoHibernate() {
        super(CategoryImpl.class);
    }
}
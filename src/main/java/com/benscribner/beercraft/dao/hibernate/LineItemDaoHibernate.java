package com.benscribner.beercraft.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.benscribner.beercraft.model.LineItem;
import com.benscribner.beercraft.model.impl.LineItemObject;

/**
 * A DAO to persist LineItems in Hibernate
 */
@Repository("lineItemDao")
public class LineItemDaoHibernate extends GenericIdentifiableDaoHibernate<LineItem> {

    /**
     * Constructor that sets the entity
     */
    public LineItemDaoHibernate() {
        super(LineItemObject.class);
    }
}
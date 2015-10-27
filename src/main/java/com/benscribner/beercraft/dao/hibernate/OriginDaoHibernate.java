package com.benscribner.beercraft.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.benscribner.beercraft.model.Origin;
import com.benscribner.beercraft.model.impl.OriginImpl;

/**
 * A DAO to persist Origins in Hibernate
 */
@Repository("originDao")
public class OriginDaoHibernate extends GenericNamedDaoHibernate<Origin> {

    /**
     * Constructor
     */
    public OriginDaoHibernate() {
        super(OriginImpl.class);
    }
}
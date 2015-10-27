package com.benscribner.beercraft.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.benscribner.beercraft.model.Role;
import com.benscribner.beercraft.model.impl.RoleImpl;

/**
 * A DAO to persist Roles in Hibernate
 */
@Repository("roleDao")
public class RoleDaoHibernate extends GenericNamedDaoHibernate<Role> {

    /**
     * Constructor
     */
    public RoleDaoHibernate() {
        super(RoleImpl.class);
    }
}
package com.benscribner.beercraft.dao.hibernate;

import com.benscribner.beercraft.model.Role;
import com.benscribner.beercraft.model.impl.RoleImpl;

/**
 * Unit tests for the {@link RoleDaoHibernate} class
 */
public class RoleDaoHibernateTest extends GenericNamedDaoHibernateTest<Role, RoleDaoHibernate> {

    /**
     * @see com.benscribner.beercraft.dao.hibernate.GenericIdentifiableDaoHibernateTest#getDao()
     */
    @Override
    protected RoleDaoHibernate getDao() {
        RoleDaoHibernate dao = new RoleDaoHibernate();
        dao.setSessionFactory(mockSessionFactory);
        return dao;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.GenericIdentifiableDaoHibernateTest#getPersistentClass()
     */
    @Override
    protected Class<RoleImpl> getPersistentClass() {
        return RoleImpl.class;
    }
}

package com.benscribner.beercraft.dao.hibernate.integration;

import org.springframework.beans.factory.annotation.Autowired;

import com.benscribner.beercraft.dao.hibernate.RoleDaoHibernate;
import com.benscribner.beercraft.model.Role;
import com.benscribner.beercraft.model.impl.RoleImpl;

/**
 * Unit tests for the {@link RoleDaoHibernate} class
 */
public class RoleDaoHibernateIntegrationTest extends GenericNamedDaoHibernateIntegrationTest<Role> {

    @Autowired
    private RoleDaoHibernate roleDao;

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.GenericNamedDaoHibernateIntegrationTest#getDao()
     */
    @Override
    protected RoleDaoHibernate getDao() {
        return roleDao;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#createObject()
     */
    @Override
    protected Role createObject() {
        final RoleImpl role = new RoleImpl("test role");
        role.setDescription("test description");

        return role;
    }

    /**
     * @see com.benscribner.beercraft.dao.hibernate.integration.AbstractHibernateIntegrationTest#updateObject(com.benscribner.beercraft.model.Identifiable)
     */
    @Override
    protected void updateObject(final Role role) {
        RoleImpl updatedRole = (RoleImpl) role;
        updatedRole.setName("new name");
        updatedRole.setDescription("new description");
    }
}

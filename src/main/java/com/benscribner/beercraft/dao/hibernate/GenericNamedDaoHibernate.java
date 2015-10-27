package com.benscribner.beercraft.dao.hibernate;

import org.hibernate.Query;

import com.benscribner.beercraft.dao.NamedObjectDao;
import com.benscribner.beercraft.model.Identifiable;
import com.benscribner.beercraft.model.Named;

/**
 * A DAO to persist named objects in Hibernate
 */
public class GenericNamedDaoHibernate<T extends Identifiable & Named> extends GenericIdentifiableDaoHibernate<T>
        implements NamedObjectDao<T> {

    /**
     * Constructor
     */
    public GenericNamedDaoHibernate(final Class<? extends T> persistentClass) {
        super(persistentClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public T getByName(final String name) {
        final Query namedQuery = getCurrentSession().createQuery("from " + persistentClass.getName() + " where name=:name");
        namedQuery.setParameter("name", name);

        return (T) namedQuery.uniqueResult();
    }
}
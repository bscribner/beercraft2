package com.benscribner.beercraft.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.benscribner.beercraft.dao.BeerDao;
import com.benscribner.beercraft.model.Beer;
import com.benscribner.beercraft.model.Brewery;
import com.benscribner.beercraft.model.Category;
import com.benscribner.beercraft.model.Origin;
import com.benscribner.beercraft.model.impl.BeerImpl;

/**
 * A DAO to persist Beer in Hibernate
 */
@Repository("beerDao")
public class BeerDaoHibernate extends GenericNamedDaoHibernate<Beer> implements BeerDao {

    /**
     * Constructor
     */
    public BeerDaoHibernate() {
        super(BeerImpl.class);
    }

    /**
     * @see com.benscribner.beercraft.dao.BeerDao#getBeerByCategory(com.benscribner.beercraft.model.Category)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Beer> getBeerByCategory(Category category) {
        final Query query = getCurrentSession().createQuery("from " + persistentClass.getName() + " where category=:category");
        query.setParameter("category", category);

        return query.list();
    }

    /**
     * @see com.benscribner.beercraft.dao.BeerDao#getBeerByOrigin(com.benscribner.beercraft.model.Origin)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Beer> getBeerByOrigin(Origin origin) {
        final Query query = getCurrentSession().createQuery("from " + persistentClass.getName() + " where origin=:origin");
        query.setParameter("origin", origin);

        return query.list();
    }

    /**
     * @see com.benscribner.beercraft.dao.BeerDao#getBeerByBrewery(com.benscribner.beercraft.model.Brewery)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Beer> getBeerByBrewery(Brewery brewery) {
        final Query query = getCurrentSession().createQuery("from " + persistentClass.getName() + " where brewery=:brewery");
        query.setParameter("brewery", brewery);

        return query.list();
    }
}
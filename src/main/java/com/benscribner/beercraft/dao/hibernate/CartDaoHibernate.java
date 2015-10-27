package com.benscribner.beercraft.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.benscribner.beercraft.dao.CartDao;
import com.benscribner.beercraft.model.Cart;
import com.benscribner.beercraft.model.Person;
import com.benscribner.beercraft.model.impl.ShoppingCart;

/**
 * A DAO to persist Carts in Hibernate
 */
@Repository("cartDao")
public class CartDaoHibernate extends GenericIdentifiableDaoHibernate<Cart> implements CartDao {

    /**
     * Constructor that sets the entity
     */
    public CartDaoHibernate() {
        super(ShoppingCart.class);
    }

    /**
     * @see com.benscribner.beercraft.dao.CartDao#getForPerson(com.benscribner.beercraft.model.Person)
     */
    @Override
    public Cart getForPerson(final Person person) {
        final Query query = getCurrentSession().createQuery("from ShoppingCart where person=:person");
        query.setParameter("person", person);
        return (Cart) query.uniqueResult();
    }
}
package com.benscribner.beercraft.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.benscribner.beercraft.dao.OrderDao;
import com.benscribner.beercraft.model.Order;
import com.benscribner.beercraft.model.Person;
import com.benscribner.beercraft.model.impl.PurchaseOrder;

/**
 * A DAO to persist Orders in Hibernate
 */
@Repository("orderDao")
public class OrderDaoHibernate extends GenericIdentifiableDaoHibernate<Order> implements OrderDao {

    /**
     * Constructor that sets the entity
     */
    public OrderDaoHibernate() {
        super(PurchaseOrder.class);
    }

    /**
     * @see com.benscribner.beercraft.dao.OrderDao#getForPerson(com.benscribner.beercraft.model.Person)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Order> getForPerson(final Person person) {
        final Query query = getCurrentSession().createQuery("from PurchaseOrder where person=:person");
        query.setParameter("person", person);
        return query.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Order save(final Order order) {
        if (order.getOrderNumber() == null) {
            final String SQL_QUERY = "select max(orderNumber) from PurchaseOrder";
            final Query query = getCurrentSession().createQuery(SQL_QUERY);
            Long orderNumber = (Long) query.uniqueResult();
            
            if (orderNumber == null) {
                orderNumber = 1000L;
            }
    
            ((PurchaseOrder) order).setOrderNumber(orderNumber + 100);
        }

        return super.save(order);
    }
}
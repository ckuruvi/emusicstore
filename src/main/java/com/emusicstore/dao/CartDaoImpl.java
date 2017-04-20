package com.emusicstore.dao;

import com.emusicstore.model.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by CK on 19-04-2017.
 */
@Repository
@Transactional
public class CartDaoImpl implements CartDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Cart getCartById(int cartId) {

        Session session= sessionFactory.getCurrentSession();
        return (Cart)session.get(Cart.class,cartId);
    }

    public void update(Cart cart) {
        Session session= sessionFactory.getCurrentSession();

    }
}

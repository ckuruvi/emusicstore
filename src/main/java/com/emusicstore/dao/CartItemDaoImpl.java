package com.emusicstore.dao;

import com.emusicstore.model.Cart;
import com.emusicstore.model.CartItem;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.applet.AudioClip;
import java.util.List;

/**
 * Created by CK on 19-04-2017.
 */
@Repository
@Transactional
public class CartItemDaoImpl implements CartItemDao{

    @Autowired
    private SessionFactory sessionFactory;

    public void addCartItem(CartItem cartItem) {

        Session session=sessionFactory.getCurrentSession();
        session.saveOrUpdate(cartItem);
        session.flush();

    }

    public void removeCartItem(CartItem cartItem) {
        Session session=sessionFactory.getCurrentSession();
        session.delete(cartItem);
        session.flush();

    }

    public void removeAllCartItems(Cart cart) {
        Session session=sessionFactory.getCurrentSession();
        List<CartItem> cartItems=cart.getCartItem();

        for(CartItem item:cartItems){
            removeCartItem(item);
        }
    }

    public CartItem getCartItemByProductId(int productId) {
        Session session=sessionFactory.getCurrentSession();
        Query query= session.createQuery("from CartItem where productId=?");
        query.setInteger(0,productId);
        session.flush();
        return (CartItem)query.uniqueResult();
    }
}

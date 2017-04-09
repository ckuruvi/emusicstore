package com.emusicstore.dao;

import com.emusicstore.model.Cart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CK on 08-04-2017.
 */

@Repository
public class CartDaoImpl implements CartDao {

    private Map<String,Cart> listOfCarts;

    public CartDaoImpl() {
        listOfCarts = new HashMap<String, Cart>();
    }

    public Cart create(Cart cart) {
        if(listOfCarts.keySet().contains(cart.getCartId())){
            throw new IllegalArgumentException(String.format("Cannot create cart. A cart with the given id already exists",cart.getCartId()));
        }

        listOfCarts.put(cart.getCartId(),cart);
        return cart;
    }

    public Cart read(String cartId) {
        Cart cart=listOfCarts.get(cartId);
        return cart;
    }

    public void update(String cartId, Cart cart) {
        if(!listOfCarts.keySet().contains(cart.getCartId())){
            throw new IllegalArgumentException(String.format("Cart with the given id does not exist",cart.getCartId()));
        }
       listOfCarts.put(cart.getCartId(),cart);
    }

    public void delete(String cartId) {

        if(!listOfCarts.keySet().contains(cartId)){
            throw new IllegalArgumentException(String.format("cannot delete cart. cart does not exist",cartId));
        }
        listOfCarts.remove(cartId);

    }
}

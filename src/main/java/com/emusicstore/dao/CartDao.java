package com.emusicstore.dao;

import com.emusicstore.model.Cart;

/**
 * Created by CK on 08-04-2017.
 */
public interface CartDao {

    Cart getCartById(int cartId);
    void update(Cart cart);
}

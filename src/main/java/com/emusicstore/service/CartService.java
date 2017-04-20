package com.emusicstore.service;

import com.emusicstore.model.Cart;

/**
 * Created by CK on 19-04-2017.
 */
public interface CartService {

    Cart getCartById(int cartId);
    void update(Cart cart);
}

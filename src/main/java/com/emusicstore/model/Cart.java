package com.emusicstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by CK on 08-04-2017.
 */

@Entity
public class Cart implements Serializable {


    private static final long serialVersionUID = 3940548625296145582L;


    @Id
    @GeneratedValue
    private int cartId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartItem> cartItem;


    @OneToOne
    @JoinColumn(name="customerId")
    @JsonIgnore
    private Customer customer;

    private double grandTotal;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public List<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }
}

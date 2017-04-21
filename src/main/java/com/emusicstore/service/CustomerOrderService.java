package com.emusicstore.service;

import com.emusicstore.model.CustomerOrder;

/**
 * Created by CK on 20-04-2017.
 */
public interface CustomerOrderService {

    void addCustomerOrder(CustomerOrder customerOrder);

    double getCustomerOrderGrandTotal(int cartId);
}

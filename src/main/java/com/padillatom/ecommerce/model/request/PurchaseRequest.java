package com.padillatom.ecommerce.model.request;

import com.padillatom.ecommerce.model.enetity.Address;
import com.padillatom.ecommerce.model.enetity.Customer;
import com.padillatom.ecommerce.model.enetity.Order;
import com.padillatom.ecommerce.model.enetity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class PurchaseRequest {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}

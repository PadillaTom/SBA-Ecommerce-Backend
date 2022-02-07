package com.padillatom.ecommerce.service.impl;

import com.padillatom.ecommerce.model.enetity.Customer;
import com.padillatom.ecommerce.model.enetity.Order;
import com.padillatom.ecommerce.model.enetity.OrderItem;
import com.padillatom.ecommerce.model.request.PurchaseRequest;
import com.padillatom.ecommerce.model.response.PurchaseResponse;
import com.padillatom.ecommerce.repository.CustomerRepository;
import com.padillatom.ecommerce.service.CheckoutService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    public CheckoutServiceImpl(@Lazy CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(PurchaseRequest purchaseRequest) {
        Order order = purchaseRequest.getOrder();
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // So cascade persist them into DB:
        Set<OrderItem> orderItems = purchaseRequest.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        order.setBillingAddress(purchaseRequest.getBillingAddress());
        order.setShippingAddress(purchaseRequest.getShippingAddress());

        //Find if Customer already Exists:
        Customer customer = purchaseRequest.getCustomer();
        Customer foundCustomer = customerRepository.findByEmail(customer.getEmail());
        if (foundCustomer != null){
            customer = foundCustomer;
        }

        customer.add(order);
        customerRepository.save(customer);
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }

}

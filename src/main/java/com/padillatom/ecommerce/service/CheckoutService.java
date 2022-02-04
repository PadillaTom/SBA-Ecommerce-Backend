package com.padillatom.ecommerce.service;

import com.padillatom.ecommerce.model.request.PurchaseRequest;
import com.padillatom.ecommerce.model.response.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(PurchaseRequest purchaseRequest);
}

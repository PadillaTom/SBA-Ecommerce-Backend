package com.padillatom.ecommerce.controller;

import com.padillatom.ecommerce.model.request.PurchaseRequest;
import com.padillatom.ecommerce.model.response.PurchaseResponse;
import com.padillatom.ecommerce.service.CheckoutService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    public CheckoutController(@Lazy CheckoutService checkoutService){
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody PurchaseRequest purchaseRequest){
        return checkoutService.placeOrder(purchaseRequest);
    }

}

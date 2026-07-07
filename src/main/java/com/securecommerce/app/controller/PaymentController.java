package com.securecommerce.app.controller;

import com.securecommerce.app.dto.PaymentOrderResponse;
import com.securecommerce.app.service.OrderService;
import com.securecommerce.app.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public PaymentOrderResponse createOrder(
            @RequestParam double amount
    ) throws Exception {

        return paymentService.createOrder(amount);

    }

    @PostMapping("/success")
    public String paymentSuccess(@RequestParam String email) {

        return orderService.completeOrderByEmail(email);

    }

}
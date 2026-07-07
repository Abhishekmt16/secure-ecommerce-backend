package com.securecommerce.app.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.securecommerce.app.dto.PaymentOrderResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    public PaymentOrderResponse createOrder(double amount) throws Exception {

        RazorpayClient client =
                new RazorpayClient(keyId, keySecret);

        JSONObject orderRequest = new JSONObject();

        orderRequest.put("amount", (int) (amount * 100));

        orderRequest.put("currency", "INR");

        orderRequest.put("receipt",
                "receipt_" + System.currentTimeMillis());

        Order order = client.orders.create(orderRequest);

        return new PaymentOrderResponse(
                order.get("id"),
                order.get("amount"),
                order.get("currency"),
                keyId
        );
    }
}
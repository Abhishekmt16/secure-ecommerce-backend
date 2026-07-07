package com.securecommerce.app.service;

import com.securecommerce.app.dto.FraudRequest;
import com.securecommerce.app.dto.FraudResponse;
import com.securecommerce.app.entity.*;
import com.securecommerce.app.enums.TransactionStatus;
import com.securecommerce.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OtpService otpService;

    @Value("${ai.service.url}")
    private String aiServiceUrl;

    private double tempAmount;
    @Autowired
    private CartItemRepository cartItemRepository;

    public String checkout(Long userId, List<Map<String, Object>> items) {

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return "User not found";

        double amount = calculateAmount(items);
        this.tempAmount = amount;

        String risk = callFraudService(amount);

        if (risk.equals("LOW")) {
            return "LOW";
        } else if (risk.equals("MEDIUM")) {
            otpService.sendOtp(user.getEmail());
            return "MEDIUM";
        } else if ("HIGH".equalsIgnoreCase(risk)) {

            return "HIGH";
        }
        return "Unable to process transaction";
    }


    private double calculateAmount(List<Map<String, Object>> items) {
        double amount = 0;

        for (Map<String, Object> item : items) {
            double price = Double.parseDouble(item.get("price").toString());
            int quantity = Integer.parseInt(item.get("quantity").toString());

            amount += price * quantity;
        }
        return amount;
    }

    private String callFraudService(double amount) {
        FraudRequest request = new FraudRequest();
        request.setAmount(amount);

        FraudResponse response = restTemplate.postForObject(
                aiServiceUrl + "/fraud/check",
                request,
                FraudResponse.class
        );

        return response.getResult();


    }

    public String completeOrder(User user, double amount) {

        Order order = new Order();
        order.setTotalAmount(amount);
        order.setUser(user);
        order.setStatus("SUCCESS");
        orderRepository.save(order);

        Transaction txn = new Transaction();
        txn.setAmount(amount);
        txn.setStatus(TransactionStatus.SUCCESS.name());
        txn.setUser(user);
        transactionRepository.save(txn);

        return "LOW risk - Order placed successfully";
    }

    public List<Order> getOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public String verifyOtp(String email) {
        System.out.println("OTP METHOD CALLED");

        return "OTP_VERIFIED";
    }

    public String verifyBiometric(String email) {
        return "BIOMETRIC_VERIFIED";
    }

    public String completeOrderByEmail(String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            return "User not found";
        }

        completeOrder(user, tempAmount);

        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        cartItemRepository.deleteAll(cartItems);

        return "SUCCESS";
    }
}
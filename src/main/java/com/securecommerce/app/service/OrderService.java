package com.securecommerce.app.service;

import com.securecommerce.app.dto.FraudRequest;
import com.securecommerce.app.dto.FraudResponse;
import com.securecommerce.app.entity.*;
import com.securecommerce.app.enums.TransactionStatus;
import com.securecommerce.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
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
            return handleLowRisk(user, amount);
        } else if (risk.equals("MEDIUM")) {
            otpService.sendOtp(user.getEmail());
            return "MEDIUM risk - OTP sent";
        } else if ("HIGH".equalsIgnoreCase(risk)) {

            Order order = new Order();
            order.setTotalAmount(amount);
            order.setUser(user);
            order.setStatus("BLOCKED");   // 🔥 important
            orderRepository.save(order);

            return "HIGH risk - biometric required";
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
                "http://localhost:5000/fraud/check",
                request,
                FraudResponse.class
        );

        return response.getResult();


    }

    private String handleLowRisk(User user, double amount) {

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

    public String verifyOtpAndPlaceOrder(String email) {
        System.out.println("OTP METHOD CALLED");

        User user = userRepository.findByEmail(email);
        if (user == null) return "User not found";

        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        System.out.println("Cart size: " + cartItems.size());
        double amount = this.tempAmount;

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

        cartItemRepository.deleteAll(cartItems);

        return "OTP_VERIFIED";
    }
}
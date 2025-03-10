package com.example.service;

import com.example.dto.UserDTO;
import com.example.entity.Order;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

//    private final String PAYMENT_SERVICE_URL = "http://localhost:9090/payments"; // Payment Service URL
//    private final String USER_SERVICE_URL = "http://localhost:9090/users"; // User Service URL

    private final String PAYMENT_SERVICE_URL = "http://localhost:8082/payments"; // Payment Service URL
    private final String USER_SERVICE_URL = "http://localhost:8081/users"; // User Service URL


    // ✅ Check if User Exists in User Microservice
    public boolean isUserValid(Long userId) {
        String url = USER_SERVICE_URL + "/" + userId;
        try {
            ResponseEntity<UserDTO> response = restTemplate.getForEntity(url, UserDTO.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (HttpClientErrorException.NotFound e) {
            return false; // User does not exist
        } catch (Exception e) {
            throw new RuntimeException("Error checking user validity: " + e.getMessage());
        }
    }

    // ✅ Create Order (Validate User & Notify Payment Microservice)
    public Order createOrder(Order order) {
        if (!isUserValid(order.getUserId())) {
            throw new RuntimeException("User does not exist!");
        }

        // Save Order
        Order savedOrder = orderRepository.save(order);

        // Notify Payment Microservice
        PaymentRequest paymentRequest = new PaymentRequest(
                savedOrder.getOrderId(), // ✅ Fix: Correct getter method
                "Credit Card", // Default payment method
                "Pending",
                "TXN" + savedOrder.getOrderId()
        );

        restTemplate.postForObject(PAYMENT_SERVICE_URL, paymentRequest, PaymentRequest.class);

        return savedOrder;
    }

    // ✅ Get All Orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // ✅ Get Order by ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // ✅ Update Order Status
    public Order updateOrderStatus(Long id, String status) {
        return orderRepository.findById(id).map(order -> {
            order.setStatus(status);
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // ✅ Delete Order
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    // ✅ DTO for Payment Request
    static class PaymentRequest {
        private Long orderId;
        private String paymentMethod;
        private String paymentStatus;
        private String transactionId;

        public PaymentRequest(Long orderId, String paymentMethod, String paymentStatus, String transactionId) {
            this.orderId = orderId;
            this.paymentMethod = paymentMethod;
            this.paymentStatus = paymentStatus;
            this.transactionId = transactionId;
        }

        // Getters and Setters
        public Long getOrderId() { return orderId; }
        public void setOrderId(Long orderId) { this.orderId = orderId; }

        public String getPaymentMethod() { return paymentMethod; }
        public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

        public String getPaymentStatus() { return paymentStatus; }
        public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

        public String getTransactionId() { return transactionId; }
        public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    }
}

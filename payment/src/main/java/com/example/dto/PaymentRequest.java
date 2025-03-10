package com.example.dto;

public class PaymentRequest {
    private int orderId;
    private String paymentMethod;
    private String paymentStatus;
    private String transactionId;

    // Constructors
    public PaymentRequest() {}

    public PaymentRequest(int orderId, String paymentMethod, String paymentStatus, String transactionId) {
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.transactionId = transactionId;
    }

        // Getters and Setters
        public int getOrderId() { return orderId; }
        public void setOrderId(int orderId) { this.orderId = orderId; }

        public String getPaymentMethod() { return paymentMethod; }
        public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

        public String getPaymentStatus() { return paymentStatus; }
        public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

        public String getTransactionId() { return transactionId; }
        public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    }


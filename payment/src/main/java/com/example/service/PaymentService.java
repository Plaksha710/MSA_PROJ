package com.example.service;

import com.example.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<Payment> getAllPayments();
    Optional<Payment> getPaymentById(int id);
    Payment savePayment(Payment payment);
    void deletePayment(int id);
}

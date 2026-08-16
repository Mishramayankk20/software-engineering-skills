package com.strategyPattern;

class PaymentService {
    private PaymentStrategy strategy;

    PaymentService(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    void makePayment(double amount) {
        strategy.pay(amount);
    }
}
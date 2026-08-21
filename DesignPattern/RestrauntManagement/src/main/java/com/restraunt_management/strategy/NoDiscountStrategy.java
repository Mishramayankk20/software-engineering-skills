package com.restraunt_management.strategy;


import org.springframework.stereotype.Component;

@Component
public class NoDiscountStrategy implements DiscountStrategy {

    @Override
    public double calculateDiscount(double amount) {
        return 0;
    }
}
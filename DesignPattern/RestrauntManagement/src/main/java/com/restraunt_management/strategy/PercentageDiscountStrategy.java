package com.restraunt_management.strategy;



import org.springframework.stereotype.Component;

@Component
public class PercentageDiscountStrategy implements DiscountStrategy {

    @Override
    public double calculateDiscount(double amount) {
        return amount * 0.10;
    }
}
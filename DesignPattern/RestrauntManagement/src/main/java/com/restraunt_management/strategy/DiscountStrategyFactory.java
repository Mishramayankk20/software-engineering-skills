package com.restraunt_management.strategy;

import org.springframework.stereotype.Component;

@Component
public class DiscountStrategyFactory {

    private final NoDiscountStrategy noDiscountStrategy;
    private final PercentageDiscountStrategy percentageDiscountStrategy;

    public DiscountStrategyFactory(
            NoDiscountStrategy noDiscountStrategy,
            PercentageDiscountStrategy percentageDiscountStrategy) {

        this.noDiscountStrategy = noDiscountStrategy;
        this.percentageDiscountStrategy =
                percentageDiscountStrategy;
    }

    public DiscountStrategy getStrategy(String type) {

        if (type == null) {
            return noDiscountStrategy;
        }

        return switch (type.toUpperCase()) {

            case "PERCENTAGE" ->
                    percentageDiscountStrategy;

            case "NONE" ->
                    noDiscountStrategy;

            default ->
                    throw new IllegalArgumentException(
                            "Unknown discount type: " + type);
        };
    }
}
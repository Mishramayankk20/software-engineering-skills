package com.restraunt_management.decorator;

import com.restraunt_management.model.RestrauntOrder;

public class BasicBillCalculator implements BillCalculator {

    private final RestrauntOrder order;

    public BasicBillCalculator(RestrauntOrder order) {
        this.order = order;
    }

    @Override
    public double calculate() {
        return order.getSubtotal();
    }
}
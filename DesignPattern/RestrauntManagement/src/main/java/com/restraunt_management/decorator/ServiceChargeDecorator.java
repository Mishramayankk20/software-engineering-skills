package com.restraunt_management.decorator;

public class ServiceChargeDecorator extends BillDecorator {

	public ServiceChargeDecorator(BillCalculator billCalculator) {

		super(billCalculator);
	}

	@Override
	public double calculate() {

		double amount = billCalculator.calculate();

		double serviceCharge = amount * 0.10;

		return amount + serviceCharge;
	}
}
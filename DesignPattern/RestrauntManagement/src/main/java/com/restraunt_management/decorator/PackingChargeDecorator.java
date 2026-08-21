package com.restraunt_management.decorator;

public class PackingChargeDecorator extends BillDecorator {

	public PackingChargeDecorator(BillCalculator billCalculator) {

		super(billCalculator);
	}

	@Override
	public double calculate() {

		double amount = billCalculator.calculate();

		return amount + 20;
	}
}
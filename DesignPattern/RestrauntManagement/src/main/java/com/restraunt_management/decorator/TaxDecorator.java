package com.restraunt_management.decorator;

public class TaxDecorator extends BillDecorator {

	public TaxDecorator(BillCalculator billCalculator) {
		super(billCalculator);
	}

	@Override
	public double calculate() {

		double amount = billCalculator.calculate();

		double tax = amount * 0.05;

		return amount + tax;
	}
}
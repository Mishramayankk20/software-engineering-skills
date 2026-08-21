package com.restraunt_management.decorator;

public abstract class BillDecorator implements BillCalculator {

	protected BillCalculator billCalculator;

	public BillDecorator(BillCalculator billCalculator) {
		this.billCalculator = billCalculator;
	}
}
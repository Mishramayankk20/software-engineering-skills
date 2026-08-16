package com.strategyPattern;

public class UPIPayment implements PaymentStrategy {
	public void pay(double amount) {
		System.out.println("Paid ₹" + amount + " using UPI");
	}
}
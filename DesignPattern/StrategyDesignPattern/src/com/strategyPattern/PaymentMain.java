package com.strategyPattern;

public class PaymentMain {
	public static void main(String[] args) {

		PaymentService payment = new PaymentService(new UPIPayment());
		PaymentService creditCard = new PaymentService(new CreditCardPayment());

		payment.makePayment(500);
		creditCard.makePayment(2000);
	}
}

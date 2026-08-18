package com.decoratorpattern;

public class CoffeeDecorator implements Coffee{
	public Coffee coffee;
	
	public CoffeeDecorator(Coffee coffee) {
		this.coffee = coffee;
	}

	@Override
	public String getDescription() {
		return coffee.getDescription()+" coffee";
	}

	@Override
	public double cost() {
		return coffee.cost();
	}
	
	

}

package com.decoratorpattern;

public class BasicCoffee implements Coffee {

	@Override
	public String getDescription() {
        return "Basic Coffee";
    }
	
	@Override
    public double cost() {
        return 5.0;
    }

}

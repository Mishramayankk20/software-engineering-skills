package com.builderDesignPattern;


/*The Builder Design Pattern is a creational design pattern 
used to construct complex objects step by step. 
It is especially useful when an object has many 
optional parameters or when you want to make object creation readable and flexible.*/
public class Main {
	public static void main(String[] args) {
		Computer computer = new Computer.Builder()
			    .setCPU("Intel i7")
			    .setRAM(16)
			    .setStorage(512)
			    .build();
		
		System.out.println(computer);
	}

}

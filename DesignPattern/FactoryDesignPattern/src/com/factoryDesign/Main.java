package com.factoryDesign;

public class Main {
	public static void main(String[] args) {
		Vehicle vehicle = VehicleFactory.createVehicle("suv");
		vehicle.book();
	}
}
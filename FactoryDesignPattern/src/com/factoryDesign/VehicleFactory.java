package com.factoryDesign;

class VehicleFactory {

	public static Vehicle createVehicle(String type) {

		if (type.equalsIgnoreCase("bike")) {
			return new Bike();
		}

		if (type.equalsIgnoreCase("sedan")) {
			return new Sedan();
		}

		if (type.equalsIgnoreCase("suv")) {
			return new SUV();
		}

		throw new IllegalArgumentException("Invalid vehicle type");
	}
}
package com.vehicle.ParkingLot.dto;

import com.vehicle.ParkingLot.enums.VehicleType;

public class ParkVehicleRequest {

	private String vehicleNumber;

	private VehicleType vehicleType;

	public ParkVehicleRequest() {
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
}
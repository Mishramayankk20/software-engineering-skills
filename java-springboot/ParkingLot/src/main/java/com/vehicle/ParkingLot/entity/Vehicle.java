package com.vehicle.ParkingLot.entity;

import com.vehicle.ParkingLot.enums.VehicleType;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String vehicleNumber;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private VehicleType vehicleType;

	public Vehicle() {
	}

	public Vehicle(String vehicleNumber, VehicleType vehicleType) {
		this.vehicleNumber = vehicleNumber;
		this.vehicleType = vehicleType;
	}

	public Long getId() {
		return id;
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
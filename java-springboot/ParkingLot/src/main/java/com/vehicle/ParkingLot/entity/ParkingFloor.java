package com.vehicle.ParkingLot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "parking_floors")
public class ParkingFloor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer floorNumber;

	@ManyToOne
	@JoinColumn(name = "parking_lot_id", nullable = false)
	private ParkingLot parkingLot;

	public ParkingFloor() {
	}

	public ParkingFloor(Integer floorNumber, ParkingLot parkingLot) {
		this.floorNumber = floorNumber;
		this.parkingLot = parkingLot;
	}

	public Long getId() {
		return id;
	}

	public Integer getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(Integer floorNumber) {
		this.floorNumber = floorNumber;
	}

	public ParkingLot getParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}
}
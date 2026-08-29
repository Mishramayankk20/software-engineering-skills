package com.vehicle.ParkingLot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "parking_lots")
public class ParkingLot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	public ParkingLot() {
	}

	public ParkingLot(String name) {
		this.name = name;
	}

	public void setId(long l) {
		this.id =  id;
	}	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
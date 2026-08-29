package com.vehicle.ParkingLot.dto;


import java.time.LocalDateTime;

public class ParkVehicleResponse {

	private String ticketNumber;
	private String vehicleNumber;
	private Integer floorNumber;
	private Integer spotNumber;
	private LocalDateTime entryTime;

	public ParkVehicleResponse(String ticketNumber, String vehicleNumber, Integer floorNumber, Integer spotNumber,
			LocalDateTime entryTime) {
		this.ticketNumber = ticketNumber;
		this.vehicleNumber = vehicleNumber;
		this.floorNumber = floorNumber;
		this.spotNumber = spotNumber;
		this.entryTime = entryTime;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public Integer getFloorNumber() {
		return floorNumber;
	}

	public Integer getSpotNumber() {
		return spotNumber;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}
}
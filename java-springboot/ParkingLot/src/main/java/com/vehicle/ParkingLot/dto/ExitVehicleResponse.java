package com.vehicle.ParkingLot.dto;

import java.time.LocalDateTime;

public class ExitVehicleResponse {

	private String ticketNumber;
	private String vehicleNumber;
	private LocalDateTime entryTime;
	private LocalDateTime exitTime;
	private long durationInHours;
	private double parkingFee;

	public ExitVehicleResponse(String ticketNumber, String vehicleNumber, LocalDateTime entryTime,
			LocalDateTime exitTime, long durationInHours, double parkingFee) {
		this.ticketNumber = ticketNumber;
		this.vehicleNumber = vehicleNumber;
		this.entryTime = entryTime;
		this.exitTime = exitTime;
		this.durationInHours = durationInHours;
		this.parkingFee = parkingFee;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public LocalDateTime getExitTime() {
		return exitTime;
	}

	public long getDurationInHours() {
		return durationInHours;
	}

	public double getParkingFee() {
		return parkingFee;
	}
}
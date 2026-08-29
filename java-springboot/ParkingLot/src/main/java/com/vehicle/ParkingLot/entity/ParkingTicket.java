package com.vehicle.ParkingLot.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import com.vehicle.ParkingLot.enums.TicketStatus;

@Entity
@Table(name = "parking_tickets")
public class ParkingTicket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String ticketNumber;

	@ManyToOne
	@JoinColumn(name = "vehicle_id", nullable = false)
	private Vehicle vehicle;

	@ManyToOne
	@JoinColumn(name = "parking_spot_id", nullable = false)
	private ParkingSpot parkingSpot;

	@Column(nullable = false)
	private LocalDateTime entryTime;

	private LocalDateTime exitTime;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TicketStatus status;

	public ParkingTicket() {
	}

	public ParkingTicket(String ticketNumber, Vehicle vehicle, ParkingSpot parkingSpot) {
		this.ticketNumber = ticketNumber;
		this.vehicle = vehicle;
		this.parkingSpot = parkingSpot;
		this.entryTime = LocalDateTime.now();
		this.status = TicketStatus.ACTIVE;
	}

	public Long getId() {
		return id;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public ParkingSpot getParkingSpot() {
		return parkingSpot;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public LocalDateTime getExitTime() {
		return exitTime;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void closeTicket() {
		this.exitTime = LocalDateTime.now();
		this.status = TicketStatus.CLOSED;
	}
}
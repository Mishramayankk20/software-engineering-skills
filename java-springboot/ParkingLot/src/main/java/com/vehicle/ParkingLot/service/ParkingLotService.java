package com.vehicle.ParkingLot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vehicle.ParkingLot.dto.ExitVehicleResponse;
import com.vehicle.ParkingLot.dto.ParkVehicleRequest;
import com.vehicle.ParkingLot.dto.ParkVehicleResponse;
import com.vehicle.ParkingLot.entity.ParkingSpot;
import com.vehicle.ParkingLot.entity.ParkingTicket;
import com.vehicle.ParkingLot.entity.Vehicle;
import com.vehicle.ParkingLot.enums.SpotType;
import com.vehicle.ParkingLot.enums.TicketStatus;
import com.vehicle.ParkingLot.enums.VehicleType;
import com.vehicle.ParkingLot.exceptions.ParkingSpotNotAvailableException;
import com.vehicle.ParkingLot.exceptions.VehicleAlreadyParkedException;
import com.vehicle.ParkingLot.repository.ParkingSpotRepository;
import com.vehicle.ParkingLot.repository.ParkingTicketRepository;
import com.vehicle.ParkingLot.repository.VehicleRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ParkingLotService {

	private final ParkingSpotRepository parkingSpotRepository;
	private final VehicleRepository vehicleRepository;
	private final ParkingTicketRepository parkingTicketRepository;

	public ParkingLotService(ParkingSpotRepository parkingSpotRepository, VehicleRepository vehicleRepository,
			ParkingTicketRepository parkingTicketRepository) {
		this.parkingSpotRepository = parkingSpotRepository;
		this.vehicleRepository = vehicleRepository;
		this.parkingTicketRepository = parkingTicketRepository;
	}

	@Transactional
	public ParkVehicleResponse parkVehicle(ParkVehicleRequest request) {

		// 1. Check if vehicle is already parked
		boolean alreadyParked = parkingTicketRepository
				.existsByVehicleVehicleNumberAndStatus(request.getVehicleNumber(), TicketStatus.ACTIVE);

		if (alreadyParked) {
			throw new VehicleAlreadyParkedException("Vehicle is already parked: " + request.getVehicleNumber());
		}

		// 2. Find an available parking spot
		SpotType spotType = SpotType.valueOf(request.getVehicleType().name());

		ParkingSpot parkingSpot = parkingSpotRepository.findFirstBySpotTypeAndOccupiedFalse(spotType)
				.orElseThrow(() -> new ParkingSpotNotAvailableException(
						"No parking spot available for " + request.getVehicleType()));

		// 3. Find existing vehicle or create a new one
		Vehicle vehicle = vehicleRepository.findByVehicleNumber(request.getVehicleNumber())
				.orElseGet(() -> new Vehicle(request.getVehicleNumber(), request.getVehicleType()));

		// 4. Save vehicle
		vehicle = vehicleRepository.save(vehicle);

		// 5. Park vehicle
		parkingSpot.parkVehicle(vehicle);

		// 6. Save parking spot
		parkingSpotRepository.save(parkingSpot);

		// 7. Create ticket
		String ticketNumber = "TICKET-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

		ParkingTicket ticket = new ParkingTicket(ticketNumber, vehicle, parkingSpot);

		// 8. Save ticket
		ticket = parkingTicketRepository.save(ticket);

		// 9. Return response
		return new ParkVehicleResponse(ticket.getTicketNumber(), vehicle.getVehicleNumber(),
				parkingSpot.getParkingFloor().getFloorNumber(), parkingSpot.getSpotNumber(), ticket.getEntryTime());
	}

	@Transactional
	public ExitVehicleResponse exitVehicle(String ticketNumber) {

		// 1. Find ticket
		ParkingTicket ticket = parkingTicketRepository.findByTicketNumber(ticketNumber)
				.orElseThrow(() -> new RuntimeException("Ticket not found: " + ticketNumber));

		// 2. Check ticket status
		if (ticket.getStatus() == TicketStatus.CLOSED) {
			throw new RuntimeException("Ticket is already closed");
		}

		// 3. Close ticket
		ticket.closeTicket();

		// 4. Calculate duration
		LocalDateTime entryTime = ticket.getEntryTime();

		LocalDateTime exitTime = ticket.getExitTime();

		long durationInHours = Duration.between(entryTime, exitTime).toHours();

		// Minimum one hour charge
		if (durationInHours == 0) {
			durationInHours = 1;
		}

		// 5. Calculate fee
		double hourlyRate = getHourlyRate(ticket.getVehicle().getVehicleType());

		double parkingFee = durationInHours * hourlyRate;

		// 6. Release parking spot
		ParkingSpot parkingSpot = ticket.getParkingSpot();

		parkingSpot.removeVehicle();

		parkingSpotRepository.save(parkingSpot);

		// 7. Save closed ticket
		parkingTicketRepository.save(ticket);

		// 8. Return response
		return new ExitVehicleResponse(ticket.getTicketNumber(), ticket.getVehicle().getVehicleNumber(), entryTime,
				exitTime, durationInHours, parkingFee);
	}

	private double getHourlyRate(VehicleType vehicleType) {

		return switch (vehicleType) {

		case BIKE -> 20.0;

		case CAR -> 40.0;

		case TRUCK -> 80.0;
		};
	}
}
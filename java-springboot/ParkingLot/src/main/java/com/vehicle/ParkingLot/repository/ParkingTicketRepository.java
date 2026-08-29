package com.vehicle.ParkingLot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.ParkingLot.entity.ParkingTicket;
import com.vehicle.ParkingLot.enums.TicketStatus;
import java.util.Optional;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {

	Optional<ParkingTicket> findByTicketNumber(String ticketNumber);

	boolean existsByVehicleVehicleNumberAndStatus(String vehicleNumber, TicketStatus status);
}
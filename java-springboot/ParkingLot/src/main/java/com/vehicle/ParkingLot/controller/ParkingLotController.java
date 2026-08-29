package com.vehicle.ParkingLot.controller;

import org.springframework.web.bind.annotation.*;

import com.vehicle.ParkingLot.dto.ExitVehicleResponse;
import com.vehicle.ParkingLot.dto.ParkVehicleRequest;
import com.vehicle.ParkingLot.dto.ParkVehicleResponse;
import com.vehicle.ParkingLot.entity.ParkingSpot;
import com.vehicle.ParkingLot.enums.SpotType;
import com.vehicle.ParkingLot.repository.ParkingSpotRepository;
import com.vehicle.ParkingLot.service.ParkingLotService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking")
public class ParkingLotController {

	private final ParkingLotService parkingLotService;
	private final ParkingSpotRepository parkingSpotRepository;

	public ParkingLotController(ParkingLotService parkingLotService, ParkingSpotRepository parkingSpotRepository) {
		this.parkingLotService = parkingLotService;
		this.parkingSpotRepository = parkingSpotRepository;
	}

	@PostMapping("/park")
	public ParkVehicleResponse parkVehicle(@RequestBody ParkVehicleRequest request) {

		return parkingLotService.parkVehicle(request);
	}

	@PostMapping("/exit/{ticketNumber}")
	public ExitVehicleResponse exitVehicle(@PathVariable String ticketNumber) {

		return parkingLotService.exitVehicle(ticketNumber);
	}

	@GetMapping("/available-spots")
	public List<ParkingSpot> getAvailableSpots(@RequestParam SpotType type) {

		return parkingSpotRepository.findByOccupiedFalse().stream().filter(spot -> spot.getSpotType() == type).toList();
	}
}
package com.vehicle.ParkingLot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.ParkingLot.entity.ParkingSpot;
import com.vehicle.ParkingLot.enums.SpotType;

import java.util.List;
import java.util.Optional;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

	Optional<ParkingSpot> findFirstBySpotTypeAndOccupiedFalse(SpotType spotType);

	List<ParkingSpot> findByOccupiedFalse();
}
package com.vehicle.ParkingLot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.ParkingLot.entity.ParkingFloor;

public interface ParkingFloorRepository extends JpaRepository<ParkingFloor, Long> {
	List<ParkingFloor> findByParkingLotId(Long parkingLotId);
}

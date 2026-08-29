package com.vehicle.ParkingLot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.ParkingLot.entity.ParkingLot;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {

}

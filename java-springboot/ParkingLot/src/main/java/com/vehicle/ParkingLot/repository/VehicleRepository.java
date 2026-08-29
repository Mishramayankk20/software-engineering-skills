package com.vehicle.ParkingLot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.ParkingLot.entity.Vehicle;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

	Optional<Vehicle> findByVehicleNumber(String vehicleNumber);
}
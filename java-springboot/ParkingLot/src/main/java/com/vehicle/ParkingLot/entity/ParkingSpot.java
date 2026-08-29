package com.vehicle.ParkingLot.entity;


import com.vehicle.ParkingLot.enums.SpotType;

import jakarta.persistence.*;

@Entity
@Table(name = "parking_spots")
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer spotNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpotType spotType;

    @Column(nullable = false)
    private boolean occupied;

    @ManyToOne
    @JoinColumn(name = "parking_floor_id", nullable = false)
    private ParkingFloor parkingFloor;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public ParkingSpot() {
    }

    public ParkingSpot(
            Integer spotNumber,
            SpotType spotType,
            ParkingFloor parkingFloor
    ) {
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.parkingFloor = parkingFloor;
        this.occupied = false;
    }

    public Long getId() {
        return id;
    }

    public Integer getSpotNumber() {
        return spotNumber;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.occupied = true;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.occupied = false;
    }
}
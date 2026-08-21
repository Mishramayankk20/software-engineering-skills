package com.restraunt_management.model;

public class RestrauntTable {

    private Long id;
    private int tableNumber;
    private boolean occupied;

    public RestrauntTable(Long id, int tableNumber) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.occupied = false;
    }

    public Long getId() {
        return id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
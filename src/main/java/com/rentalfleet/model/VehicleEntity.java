package com.rentalfleet.model;

public record VehicleEntity(
        int id,
        String modelName,
        String plateNo,
        double dailyRate,
        String status
) {
    // Constructor for inserting new records (ID auto-incremented by SQLite)
    public VehicleEntity(String modelName, String plateNo, double dailyRate, String status) {
        this(0, modelName, plateNo, dailyRate, status);
    }
}
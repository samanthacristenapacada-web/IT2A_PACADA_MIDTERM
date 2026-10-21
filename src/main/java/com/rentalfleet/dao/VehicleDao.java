package com.rentalfleet.dao;

import com.rentalfleet.model.VehicleEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    public void addVehicle(VehicleEntity vehicle) {
        String sql = "INSERT INTO vehicles (model_name, plate_no, daily_rate, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vehicle.modelName());
            pstmt.setString(2, vehicle.plateNo());
            pstmt.setDouble(3, vehicle.dailyRate());
            pstmt.setString(4, vehicle.status());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding vehicle: " + e.getMessage());
        }
    }

    public List<VehicleEntity> getAllVehicles() {
        List<VehicleEntity> vehicles = new ArrayList<>();
        String sql = "SELECT id, model_name, plate_no, daily_rate, status FROM vehicles";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                VehicleEntity vehicle = new VehicleEntity(
                        rs.getInt("id"),
                        rs.getString("model_name"),
                        rs.getString("plate_no"),
                        rs.getDouble("daily_rate"),
                        rs.getString("status")
                );
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching vehicles: " + e.getMessage());
        }
        return vehicles;
    }
}
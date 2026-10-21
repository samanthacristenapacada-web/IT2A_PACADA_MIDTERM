package com.rentalfleet;

import com.rentalfleet.dao.DatabaseConnection;
import com.rentalfleet.ui.VehicleMenu;

public class Main {
    public static void main(String[] args) {
        // Initialize SQLite Database and Table
        DatabaseConnection.initializeDatabase();

        // Launch Console Menu UI
        VehicleMenu menu = new VehicleMenu();
        menu.start();
    }
}
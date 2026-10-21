package com.rentalfleet.ui;

import com.rentalfleet.dao.VehicleDao;
import com.rentalfleet.model.VehicleEntity;

import java.util.List;
import java.util.Scanner;

public class VehicleMenu {
    private final VehicleDao vehicleDao;
    private final Scanner scanner;

    public VehicleMenu() {
        this.vehicleDao = new VehicleDao();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice = 0;
        do {
            System.out.println("==================================================");
            System.out.println("             CAR RENTAL FLEET TRACKER             ");
            System.out.println("==================================================");
            System.out.println("[1] Add New Vehicle");
            System.out.println("[2] View Fleet Vehicles");
            System.out.println("[3] Exit System");
            System.out.println("==================================================");
            System.out.print("Enter choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear newline buffer
            } else {
                System.out.println("Invalid input. Please enter a valid number.\n");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    addNewVehiclePrompt();
                    break;
                case 2:
                    viewVehiclesTable();
                    break;
                case 3:
                    System.out.println("\nExiting system... Thank you!");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please select an option between 1 and 3.\n");
            }
        } while (choice != 3);
        scanner.close();
    }

    private void addNewVehiclePrompt() {
        System.out.println("\n--- ADD NEW VEHICLE ---");
        System.out.print("Enter Model Name: ");
        String modelName = scanner.nextLine();

        System.out.print("Enter License Plate Number: ");
        String plateNo = scanner.nextLine();

        double dailyRate = 0.0;
        while (true) {
            System.out.print("Enter Daily Rate: ");
            if (scanner.hasNextDouble()) {
                dailyRate = scanner.nextDouble();
                scanner.nextLine(); // Clear newline buffer
                break;
            } else {
                System.out.println("Invalid amount. Please enter a valid numeric daily rate.");
                scanner.nextLine();
            }
        }

        System.out.print("Enter Status (AVAILABLE / RENTED): ");
        String status = scanner.nextLine().trim().toUpperCase();

        VehicleEntity newVehicle = new VehicleEntity(modelName, plateNo, dailyRate, status);
        vehicleDao.addVehicle(newVehicle);
        System.out.println("\nSUCCESS: Vehicle registered to fleet!\n");
    }

    private void viewVehiclesTable() {
        List<VehicleEntity> vehicles = vehicleDao.getAllVehicles();
        System.out.println("\n==================================================");
        System.out.println("              RENTAL FLEET VEHICLES               ");
        System.out.println("==================================================");
        System.out.println("+-----+---------------------+-------------+---------------+-------------+");
        System.out.println("| ID  | MODEL NAME          | PLATE NO    | DAILY RATE    | STATUS      |");
        System.out.println("+-----+---------------------+-------------+---------------+-------------+");

        for (VehicleEntity v : vehicles) {
            System.out.printf("| %-3d | %-19s | %-11s | %-13.2f | %-11s |\n",
                    v.id(),
                    v.modelName(),
                    v.plateNo(),
                    v.dailyRate(),
                    v.status());
        }
        System.out.println("+-----+---------------------+-------------+---------------+-------------+");
        System.out.println("Total Records Displayed: " + vehicles.size() + "\n");
    }
}


package ELECTRICITY;

import java.io.*;
import java.util.Date;

public class ElectricityBill {
    // Member Variables
    String name;
    int units;
    String type;
    double baseBill;
    double tax;
    double finalBill;
    String receiptId;

    // Constructor
    ElectricityBill(String name, int units, String type, int count) {
        this.name = name;
        this.units = units;
        this.type = type;
        this.receiptId = "MTR-2026-" + (500 + count);
    }

    // Advanced Calculation Logic
    void calculateBill() {
        // Base rates
        if (type.equalsIgnoreCase("household")) {
            baseBill = units * 4.0;
        } else if (type.equalsIgnoreCase("commercial")) {
            baseBill = units * 7.5; // Slightly higher for realism
        } else {
            baseBill = units * 2.0; // Default/Other
        }

        // Professional Add-ons: 5% GST and Fixed Service Charge
        tax = baseBill * 0.05;
        finalBill = baseBill + tax + 25.00; // Rs 25 fixed service charge
    }

    // The Digital Meter ASCII Art
    void displayBill() {
        String fUnits = String.format("%07d", units);
        String fTotal = String.format("%.2f", finalBill);
        String date = new Date().toString().substring(0, 10);

        System.out.println("\n          POWER GRID CORPORATION");
        System.out.println("      _______________________________________");
        System.out.println("     /                                       \\");
        System.out.println("    |   ___________________________________   |");
        System.out.println("    |  |  _______________________________  |  |");
        System.out.println("    |  | |                               | |  |");
        System.out.printf("    |  | |   CURRENT: %s kWh      | |  |%n", fUnits);
        System.out.println("    |  | |_______________________________| |  |");
        System.out.println("    |  |___________________________________|  |");
        System.out.println("    |                                         |");
        System.out.printf("    |   ID    : %-26s|%n", receiptId);
        System.out.printf("    |   USER  : %-26s|%n", name.toUpperCase());
        System.out.printf("    |   TYPE  : %-26s|%n", type.toUpperCase());
        System.out.printf("    |   DATE  : %-26s|%n", date);
        System.out.println("    |    _________________________________    |");
        System.out.printf("    |   TOTAL PAYABLE: RS %-15s |%n", fTotal);
        System.out.println("    |_________________________________________|");
        System.out.println("             |                       |");
        System.out.println("             |_______________________|");
    }

    public static void main(String[] args) {
        ElectricityBill[] users = new ElectricityBill[4];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double grandTotalRevenue = 0;

        try {
            System.out.println("==============================================");
            System.out.println("   ELECTRICITY BILLING MANAGEMENT SYSTEM      ");
            System.out.println("              GROUP PROJECT            ");
            System.out.println("==============================================");
            
            for (int i = 0; i < 4; i++) {
                System.out.println("\n[DATA ENTRY FOR CUSTOMER " + (i + 1) + "]");
                System.out.print("Enter Name: ");
                String name = br.readLine();
                System.out.print("Enter Units: ");
                int units = Integer.parseInt(br.readLine());
                System.out.print("Enter Type (Household/Commercial): ");
                String type = br.readLine();

                users[i] = new ElectricityBill(name, units, type, i);
                users[i].calculateBill();
                grandTotalRevenue += users[i].finalBill;
            }

            // Printing Meters
            System.out.println("\n\n--- GENERATING INDIVIDUAL DIGITAL METERS ---");
            for (ElectricityBill user : users) {
                user.displayBill();
                Thread.sleep(500); // Small delay for effect
            }

            // PROFESSIONAL SUMMARY TABLE
            System.out.println("\n\n==============================================");
            System.out.println("             FINAL REVENUE SUMMARY            ");
            System.out.println("==============================================");
            System.out.printf("| %-15s | %-10s | %-10s |%n", "NAME", "UNITS", "BILL (RS)");
            System.out.println("----------------------------------------------");
            for (ElectricityBill user : users) {
                System.out.printf("| %-15s | %-10d | %-10.2f |%n", user.name, user.units, user.finalBill);
            }
            System.out.println("----------------------------------------------");
            System.out.printf("  GRAND TOTAL REVENUE: RS %.2f%n", grandTotalRevenue);
            System.out.println("==============================================");

        } catch (Exception e) {
            System.out.println("Execution Error: Check your input format.");
        }
    }
}
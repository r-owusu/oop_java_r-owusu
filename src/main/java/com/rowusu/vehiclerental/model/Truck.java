package com.rowusu.vehiclerental.model;
import com.rowusu.vehiclerental.exceptions.CustomerNotEligible;
import com.rowusu.vehiclerental.interfaces.Rentable;
import com.rowusu.vehiclerental.customers.Customer;
public class Truck extends Vehicle implements Rentable{
    // Private fields for critical information
    private boolean hasCargoLift;
    private boolean hasRefrigeratedStorage;

    // Constructor with validation
    public Truck(String vehicleId, String model, double baseRentalRate, boolean hasCargoLift, boolean hasRefrigeratedStorage) {
        super(vehicleId, model, baseRentalRate);
        this.hasCargoLift = hasCargoLift;
        this.hasRefrigeratedStorage = hasRefrigeratedStorage;
    }

    // Public getter and setter methods with input validation
    public boolean hasCargoLift() {
        return hasCargoLift;
    }

    public void setCargoLift(boolean hasCargoLift) {
        this.hasCargoLift = hasCargoLift;
    }

    public boolean hasRefrigeratedStorage() {
        return hasRefrigeratedStorage;
    }

    public void setRefrigeratedStorage(boolean hasRefrigeratedStorage) {
        this.hasRefrigeratedStorage = hasRefrigeratedStorage;
    }
    @Override
    public double calculateRentalCost(int days) {
        double baseCost = getBaseRentalRate() * days; // Base rate
        double featureCost = calculateTotalFeatureCost() * days; // Dynamic surcharge
        return baseCost + featureCost;
    }

    // Rental cost calculations
  /*  @Override
    public double calculateRentalCost(int days) {
        double cost = getBaseRentalRate() * days;
        if (hasCargoLift) {
            cost += 20 * days; // Cargo lift surcharge
        }
        if (hasRefrigeratedStorage) {
            cost += 30 * days; // Refrigerated storage surcharge
        }
        return cost;
    }*/

    // Availability checks
    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }

    // Special features (e.g., description of the truck's unique features)
    @Override
    public String toString() {
        return String.format("Truck[ID=%s, Model=%s, BaseRate=%.2f, CargoLift=%s, RefrigeratedStorage=%s]",
                getVehicleId(), getModel(), getBaseRentalRate(),
                hasCargoLift ? "Yes" : "No",
                hasRefrigeratedStorage ? "Yes" : "No");
    }
@Override
    public void rent(Customer customer, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Rental period must be greater than zero.");
        }

        if (!isAvailable()) {
            throw new IllegalStateException("Vehicle is already rented.");
        }

        if (!customer.isEligibleForRental()) {
            throw new IllegalStateException("Customer is not eligible for rental.");
        }
        int rentalLimit=2;
        if (customer.getCurrentRentals().size() >= rentalLimit) {
            throw new IllegalStateException("Customer has exceeded rental limit.");
        }
        // Mark the car as rented
        setAvailable(false);

        // Add this car to the customer's current rentals
        customer.addCurrentRental(this, days);

        // Print rental confirmation
        System.out.println("Truck rented successfully to " + customer.getName() + " for " + days + " days.");
    }
   /* @Override
    public void returnVehicle() {
        if (isAvailable()) {
            System.out.println("Truck is already available.");
            return;
        }

        // Mark the car as available
        setAvailable(true);

        // Notify that the car has been returned
        System.out.println("Truck returned successfully.");

    }*/
   /*@Override
   public void returnVehicle() {
       if (isAvailable()) {
           System.out.println("Car is already available.");
           return;
       }

       // Mark the car as available
       setAvailable(true);

       // Remove from the customer's rentals list
       if (currentRenter != null) {
           currentRenter.removeCurrentRental(this);
       }

       // Notify that the car has been returned
       System.out.println("Car returned successfully.");
   }*/

    @Override
    public void returnVehicle() {
        if (isAvailable()) {
            throw new IllegalStateException("Car is already available.");
        }

        // Mark car as available
        setAvailable(true);

        // Assuming the customer is passed directly or is tracked elsewhere (like in a rental service or system)
        // You should manage this relation in the `Customer` or `Rental` class, not directly in `Car`.

        System.out.println("Car returned successfully.");
    }

}

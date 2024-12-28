package com.rowusu.vehiclerental.model;
import com.rowusu.vehiclerental.interfaces.Rentable;
import com.rowusu.vehiclerental.customers.Customer;
public class Car extends Vehicle implements Rentable{
    // Private fields for critical information
    private boolean hasGPS;
    private boolean hasChildSeat;
    private boolean hasSunroof;

    // Constructor with validation
    public Car(String vehicleId, String model, double baseRentalRate, boolean hasGPS, boolean hasChildSeat, boolean hasSunroof) {
        super(vehicleId, model, baseRentalRate);
        this.hasGPS = hasGPS;
        this.hasChildSeat = hasChildSeat;
        this.hasSunroof = hasSunroof;
    }

    // Public getter and setter methods with input validation
    public boolean hasGPS() {
        return hasGPS;
    }

    public void setGPS(boolean hasGPS) {
        this.hasGPS = hasGPS;
    }

    public boolean hasChildSeat() {
        return hasChildSeat;
    }

    public void setChildSeat(boolean hasChildSeat) {
        this.hasChildSeat = hasChildSeat;
    }

    public boolean hasSunroof() {
        return hasSunroof;
    }

    public void setSunroof(boolean hasSunroof) {
        this.hasSunroof = hasSunroof;
    }


    // Availability checks
    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }

    // Special features (e.g., description of the car's unique features)
    @Override
    public String toString() {
        return String.format("Car[ID=%s, Model=%s, BaseRate=%.2f, GPS=%s, ChildSeat=%s, Sunroof=%s]",
                getVehicleId(), getModel(), getBaseRentalRate(),
                hasGPS ? "Yes" : "No",
                hasChildSeat ? "Yes" : "No",
                hasSunroof ? "Yes" : "No");
    }
    @Override
    public void rent(Customer customer, int days) {
  //   int rentlalLimit=2;
        if (!isAvailable()) {
            throw new IllegalStateException("Vehicle is already rented.");
        }
        if (days <= 0) {
            throw new IllegalArgumentException("Rental period must be greater than zero.");
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
        System.out.println("Car rented successfully to " + customer.getName() + " for " + days + " days.");
    }
    /*@Override
    /ublic void returnVehicle() {
        if (isAvailable()) {
            System.out.println("Car is already available.");
            return;
        }

        // Mark the car as available
        setAvailable(true);

        // Notify that the car has been returned
        System.out.println("Car returned successfully.");
    }*/
    /*@Override
    public void returnVehicle() {
        if (isAvailable()) {
            throw new IllegalStateException("Car is already available.");
        }

        // Mark car as available
        setAvailable(true);

        // Assuming the customer is passed directly or is tracked elsewhere (like in a rental service or system)
        // You should manage this relation in the `Customer` or `Rental` class, not directly in `Car`.

        System.out.println("Car returned successfully.");
    }*/

    @Override
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
    }

        //Calculating rental cost
    @Override
    public double calculateRentalCost(int days) {
        double baseCost = getBaseRentalRate() * days; // Base rental rate
        double featureCost = calculateTotalFeatureCost() * days; // Surcharges for features
        return baseCost + featureCost;
    }
}


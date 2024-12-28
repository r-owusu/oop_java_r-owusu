package com.rowusu.vehiclerental.model;
import com.rowusu.vehiclerental.interfaces.Rentable;
import com.rowusu.vehiclerental.customers.Customer;
public class Motorcycle extends Vehicle implements Rentable{
    // Private fields for critical information
    private boolean hasHelmet;
    private boolean hasLuggageStorage;

    // Constructor with validation
    public Motorcycle(String vehicleId, String model, double baseRentalRate, boolean hasHelmet, boolean hasLuggageStorage) {
        super(vehicleId, model, baseRentalRate);
        this.hasHelmet = hasHelmet;
        this.hasLuggageStorage = hasLuggageStorage;
    }

    // Public getter and setter methods with input validation
    public boolean hasHelmet() {
        return hasHelmet;
    }

    public void setHelmet(boolean hasHelmet) {
        this.hasHelmet = hasHelmet;
    }

    public boolean hasLuggageStorage() {
        return hasLuggageStorage;
    }

    public void setLuggageStorage(boolean hasLuggageStorage) {
        this.hasLuggageStorage = hasLuggageStorage;
    }



    // Availability checks
    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }

    // Special features (e.g., description of the motorcycle's unique features)
    @Override
    public String toString() {
        return String.format("Motorcycle[ID=%s, Model=%s, BaseRate=%.2f, Helmet=%s, LuggageStorage=%s]",
                getVehicleId(), getModel(), getBaseRentalRate(),
                hasHelmet ? "Yes" : "No",
                hasLuggageStorage ? "Yes" : "No");
    }
   /* @Override
    public void rent(Customer customer, int days) {
        if (!isAvailable()) {
            System.out.println("Motorcycle is not available for rental.");
            return;
        }

        if (!customer.isEligibleForRental()) {
            System.out.println("Customer is not eligible for rental.");
            return;
        }*/
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
        System.out.println("Motorcycle rented successfully to " + customer.getName() + " for " + days + " days.");
    }
   /* @Override
    public void returnVehicle() {
        if (isAvailable()) {
            System.out.println("Motorcycle is already available.");
            return;
        }

        // Mark the car as available
        setAvailable(true);

        // Notify that the car has been returned
        System.out.println("Motorcycle returned successfully.");
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

//Rental Cost Calculation
    @Override
    public double calculateRentalCost(int days) {
        double baseCost = getBaseRentalRate() * days; // Base rate
        double featureCost = calculateTotalFeatureCost() * days; // Dynamic surcharge
        return baseCost + featureCost;
    }



}

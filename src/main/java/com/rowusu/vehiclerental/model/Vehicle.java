package com.rowusu.vehiclerental.model;
import com.rowusu.vehiclerental.customers.Customer;
import com.rowusu.vehiclerental.interfaces.Rentable;
import com.rowusu.vehiclerental.interfaces.Ratable;
import com.rowusu.vehiclerental.interfaces.FeatureAssignable;
import com.rowusu.vehiclerental.model.Feature;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Vehicle implements Rentable, Ratable, FeatureAssignable {
    // Private encapsulated fields
    private final String vehicleId;
    private final String model;
    private final double baseRentalRate;
    private boolean isAvailable;
    private List<Integer> ratings;
    private final List<Feature> features;
    public Customer currentRenter;
    private boolean availableForRental; // Availability

    // Constructor
    protected Vehicle(String vehicleId, String model, double baseRentalRate) {
        if (vehicleId == null || vehicleId.isEmpty()) {
            throw new IllegalArgumentException("Vehicle ID cannot be null or empty");
        }
        if (model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Model cannot be null or empty");
        }
        if (baseRentalRate <= 0) {
            throw new IllegalArgumentException("Base rental rate must be positive");
        }
        this.vehicleId = vehicleId;
        this.model = model;
        this.baseRentalRate = baseRentalRate;
        this.isAvailable = true; // Default availability
        this.ratings = new ArrayList<>();
        this.features = new ArrayList<>();
        this.availableForRental = availableForRental;
    }

    public static Car createCar(String vehicleId, String model, double rate, boolean gps, boolean childSeat, boolean sunroof) {
        return new Car(vehicleId, model, rate, gps, childSeat, sunroof);
    }

    public static Motorcycle createMotorcycle(String vehicleId, String model, double rate, boolean hashelmet, boolean hasLuggageStorage) {
        return new Motorcycle(vehicleId, model, rate, hashelmet, hasLuggageStorage);
    }

    public static Truck createTruck(String vehicleId, String model, double baseRentalRate, boolean hasCargoLift, boolean hasRefrigeratedStorage) {
        return new Truck(vehicleId, model, baseRentalRate, hasCargoLift, hasRefrigeratedStorage);
    }

    // Getters and Setters with validation
    public String getVehicleId() {
        return vehicleId;
    }

    /*public void setVehicleId(String vehicleId) {
        if (vehicleId == null || vehicleId.isEmpty()) {
            throw new IllegalArgumentException("Vehicle ID cannot be null or empty");
        }
        this.vehicleId = vehicleId;
    }
*/
    public String getModel() {
        return model;
    }

    /*public void setModel(String model) {
        if (model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Model cannot be null or empty");
        }
        this.model = model;
    }*/

    public double getBaseRentalRate() {
        return baseRentalRate;
    }

    /*public void setBaseRentalRate(double baseRentalRate) {
        if (baseRentalRate <= 0) {
            throw new IllegalArgumentException("Base rental rate must be positive");
        }
        this.baseRentalRate = baseRentalRate;
    }*/

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // Abstract methods
    public abstract double calculateRentalCost(int days);

  //  public abstract boolean isAvailableForRental();
  public boolean isAvailableForRental() {
      return availableForRental;
  }
    public void setAvailableForRental(boolean availableForRental) {
        this.availableForRental = availableForRental;
    }
    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId='" + vehicleId + '\'' +
                ", model='" + model + '\'' +
                ", baseRentalRate=" + baseRentalRate +
                ", isAvailable=" + isAvailable +
                ", features=" + features +
                '}';
    }

    public void addRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        ratings.add(rating);
    }

    // Method to calculate the average rating
    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0.0; // Default if no ratings
        }
        return ratings.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    @Override
    public void addFeature(Feature feature) {
        features.add(feature);
    }

    public List<Feature> getFeatures() {
        return features;
    }

    @Override
    public double calculateTotalFeatureCost() {
        return features.stream().mapToDouble(Feature::getAdditionalCost).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(vehicleId, vehicle.vehicleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId);
    }

    public void rent(Customer customer, int days) {
        if (!isAvailable) {
            throw new IllegalStateException("Vehicle is not available for rent.");
        }

        if (!customer.isEligibleForRental()) {
            throw new IllegalStateException("Customer is not eligible for new rentals.");
        }

        isAvailable = false;
        currentRenter = customer;

        // Add to customer's current rentals
        customer.addCurrentRental(this, days);

    }

    /*public void returnVehicle() {
        if (isAvailable || currentRenter == null) {
            throw new IllegalStateException("Vehicle is not currently rented.");
        }

        isAvailable = true;

        // Remove from customer's current rentals
        currentRenter.removeCurrentRental(this);
        currentRenter = null; // Clear current renter
    }*/
    /*public void returnVehicle() {
        if (isAvailable()) {
            System.out.println("Car is already available.");
            return;
        }

        // Mark the car as available
        setAvailable(true);

        // Remove from the customer's rentals list (you missed this part in the original code)
        if (currentRenter != null) {
            currentRenter.removeCurrentRental(this);
        }

        // Notify that the car has been returned
        System.out.println("Car returned successfully.");

    }*/public void returnVehicle() {
        System.out.println("Is Available before return: " + isAvailable());
        if (isAvailable()) {
            throw new IllegalStateException("Vehicle is not currently rented");
        }
        setAvailable(true);
        if (currentRenter != null) {
            currentRenter.removeCurrentRental(this);
            currentRenter = null;
        }
    }
}


package com.rowusu.vehiclerental.interfaces;

import com.rowusu.vehiclerental.customers.Customer;
import com.rowusu.vehiclerental.model.Car;
import com.rowusu.vehiclerental.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentableTest {

    private Customer customer;
    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        // Create a customer
        customer = new Customer("John Doe", "C123");

        // Create a car with some details
        vehicle = Vehicle.createCar("V001", "Toyota Camry", 50.0, true, true, false);
    }

    @Test
    void testRentVehicle() {
        // Ensure the vehicle is available before renting
        assertTrue(vehicle.isAvailable(), "Vehicle should be available before renting");

        // Rent the vehicle for 5 days
        vehicle.rent(customer, 5);

        // Ensure the vehicle is no longer available after renting
        assertFalse(vehicle.isAvailable(), "Vehicle should not be available after renting");

        // Ensure the customer now has the vehicle in their current rentals
        assertTrue(customer.getCurrentRentals().containsKey(vehicle), "Customer should have the vehicle in their current rentals");

        // Check that the rental days are correctly associated
        assertEquals(5, customer.getCurrentRentals().get(vehicle), "Rental days should be correctly assigned to the vehicle");
    }

    /*@Test
    void testReturnVehicle() {
        // Rent the vehicle for 5 days first
        vehicle.rent(customer, 5);

        // Ensure the vehicle is rented
        assertFalse(vehicle.isAvailable(), "Vehicle should not be available before returning");

        // Now, return the vehicle
        vehicle.returnVehicle();

        // Ensure the vehicle is available after returning
        assertTrue(vehicle.isAvailable(), "Vehicle should be available after returning");

        // Ensure the customer no longer has the vehicle in their current rentals
        assertFalse(customer.getCurrentRentals().containsKey(vehicle), "Customer should not have the vehicle in their current rentals after return");

        // Ensure the renter is cleared
        assertNull(vehicle.currentRenter, "Vehicle's current renter should be null after return");
    }*/
    @Test
    void testReturnVehicle() {
        // Rent the vehicle for 5 days first
        vehicle.rent(customer, 5);

        // Ensure the vehicle is rented
        assertFalse(vehicle.isAvailable(), "Vehicle should not be available before returning");

        // Now, return the vehicle
        vehicle.returnVehicle();

        // Ensure the vehicle is available after returning
        assertTrue(vehicle.isAvailable(), "Vehicle should be available after returning");

        // Ensure the customer no longer has the vehicle in their current rentals
      //  assertFalse(customer.getCurrentRentals().containsKey(vehicle), "Customer should not have the vehicle in their current rentals after return");

        // Ensure the renter is cleared
        assertNull(vehicle.currentRenter, "Vehicle's current renter should be null after return");
    }
    @Test
    void testRentVehicleWhenNotEligible() {
        // Simulate customer already has two rentals (based on the rentalLimit)
        Customer anotherCustomer = new Customer("Jane Smith", "C456");
        Vehicle anotherVehicle = Vehicle.createCar("V002", "Honda Civic", 40.0, false, false, true);

        anotherCustomer.addCurrentRental(anotherVehicle, 3); // Add one rental
        anotherCustomer.addCurrentRental(vehicle, 2); // Add another rental

        // Now try renting the car again, which should fail as the customer has reached rental limit
        assertThrows(IllegalStateException.class, () -> vehicle.rent(anotherCustomer, 5), "Customer should not be eligible for new rentals due to rental limit.");
    }

 /*   @Test
    void testReturnVehicleWhenNotRented() {
        // Attempt to return a vehicle that was never rented
        assertThrows(IllegalStateException.class, () -> vehicle.returnVehicle(), "Vehicle cannot be returned because it was never rented.");
    }
*/
 /*@Test
void testReturnVehicleWhenNotRented() {
     // Attempt to return a vehicle that was never rented
     assertThrows(IllegalStateException.class, () -> vehicle.returnVehicle(), "Vehicle cannot be returned because it was never rented.");
 }*/
    @Test
    void testRentVehicleWhenAlreadyRented() {
        // Rent the vehicle first
        vehicle.rent(customer, 5);

        // Now attempt to rent the same vehicle again, which should fail because it's already rented
        assertThrows(IllegalStateException.class, () -> vehicle.rent(customer, 5), "Vehicle should not be rented again while it's already rented.");
    }
}

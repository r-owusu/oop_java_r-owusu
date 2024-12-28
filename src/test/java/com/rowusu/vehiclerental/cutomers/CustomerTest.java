package com.rowusu.vehiclerental.cutomers;

import com.rowusu.vehiclerental.exceptions.VehicleNotAvailable;
import com.rowusu.vehiclerental.model.Car;  // Assume Car is a subclass of Vehicle
import com.rowusu.vehiclerental.customers.Customer;
import com.rowusu.vehiclerental.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer customer;
    private Vehicle car;
    private Vehicle truck;

    @BeforeEach
    void setUp() {
        customer = new Customer("John Doe", "C123");
        car = new Car("V001", "Car Model", 50.0,true,true,true);  // Assumes Vehicle constructor (id, model, price)
        truck = new Car("V002", "Truck Model", 100.0,true,true,true);  // Example with a truck
    }

    @Test
    void testAddCurrentRentalWithinLimit() {
        customer.addCurrentRental(car, 5);
        assertTrue(customer.getCurrentRentals().containsKey(car), "Car should be added to current rentals");
        assertEquals(5, customer.getCurrentRentals().get(car), "Rental days should be 5");
    }

    @Test
    void testAddCurrentRentalExceedingLimit() {
        customer.addCurrentRental(car, 5);  // First rental
        customer.addCurrentRental(truck, 3);  // Second rental
        assertThrows(IllegalStateException.class, () -> {
            customer.addCurrentRental(new Car("V003", "Bike Model", 30.0,true,true,true), 2);  // Third rental
        }, "Should throw IllegalStateException when exceeding rental limit");
    }

    @Test
    void testIsEligibleForRental() {
        customer.addCurrentRental(car, 5); // First rental
        customer.addCurrentRental(truck, 3); // Second rental

        // Now the rental limit should be reached
        assertFalse(customer.isEligibleForRental(), "Customer should not be eligible for more rentals after reaching limit");
    }

    @Test
    void testAddRating() {
        customer.addRating(4); // First rating
        customer.addRating(5); // Second rating

        // Calculate average: (4 + 5) / 2 = 4.5
        assertEquals(4.5, customer.getAverageRating(), "Average rating should be 4.5");

        // Test invalid rating
        assertThrows(IllegalArgumentException.class, () -> customer.addRating(6), "Rating must be between 1 and 5");
        assertThrows(IllegalArgumentException.class, () -> customer.addRating(0), "Rating must be between 1 and 5");
    }


    @Test
    void testAddLoyaltyPoints() {
        customer.addLoyaltyPoints(10);
        assertEquals(10, customer.getLoyaltyPoints(), "Loyalty points should be correctly added");

        customer.addLoyaltyPoints(90);
        assertEquals(100, customer.getLoyaltyPoints(), "Loyalty points should be correctly added");

        // Test invalid negative points
        assertThrows(IllegalArgumentException.class, () -> customer.addLoyaltyPoints(-5), "Loyalty points cannot be negative");
    }

    @Test
    void testLoyaltyStatus() {
        customer.addLoyaltyPoints(30);
        assertEquals("Bronze", customer.getLoyaltyStatus(), "Customer should be Bronze status");

        customer.addLoyaltyPoints(30);
        assertEquals("Silver", customer.getLoyaltyStatus(), "Customer should be Silver status");

        customer.addLoyaltyPoints(40);
        assertEquals("Gold", customer.getLoyaltyStatus(), "Customer should be Gold status");
    }

    @Test
    void testRentalHistory() {
        customer.addRental(car, "First rental", 5);  // Add car rental
        assertEquals(1, customer.getRentalHistory().size(), "Rental history should have one entry");

        customer.addRental(truck, "Second rental", 3);  // Add truck rental
        assertEquals(2, customer.getRentalHistory().size(), "Rental history should have two entries");
    }

    @Test
    void testEqualsAndHashCode() {
        Customer anotherCustomer = new Customer("John Doe", "C123");
        assertTrue(customer.equals(anotherCustomer), "Customers with the same customerId should be equal");
        assertEquals(customer.hashCode(), anotherCustomer.hashCode(), "Hash codes for equal customers should be the same");

        Customer differentCustomer = new Customer("Jane Doe", "C124");
        assertFalse(customer.equals(differentCustomer), "Customers with different customerId should not be equal");
    }
}

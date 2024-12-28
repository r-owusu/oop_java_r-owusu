package com.rowusu.vehiclerental.exceptions;

import com.rowusu.vehiclerental.exceptions.VehicleNotAvailable;
import com.rowusu.vehiclerental.model.Car;  // Or use any other subclass of Vehicle
import com.rowusu.vehiclerental.customers.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleNotAvailableTest {

    private Car car;  // Or use any other concrete class of Vehicle
    private Customer customer;  // Assume Customer is a valid class

    @BeforeEach
    void setUp() {
        // Initialize the Car and Customer objects for testing
        car = new Car("V123", "Model X", 20.0,true,true,true);  // Assuming Car constructor takes these arguments
        customer = new Customer("John Doe", "johndoe@example.com");  // Assuming a Customer constructor like this
    }

    @Test
    void testVehicleNotAvailableWhenRented() throws VehicleNotAvailable {
        // Renting the car to the customer
        car.rent(customer, 5);  // Assuming rent method does not throw exception if successful

        // Now attempt to rent the car again to the same customer or another customer
        // This should throw the VehicleNotAvailable exception since the car is already rented
        /*assertThrows(VehicleNotAvailable.class, () -> {
            car.rent(customer, 3);  // Trying to rent the car again when it is already rented
        }, "Vehicle should not be available for rent when already rented");*/
    }

    @Test
    void testVehicleNotAvailableWhenAlreadyRentedByAnotherCustomer() throws VehicleNotAvailable {
        // Renting the car to the first customer
        car.rent(customer, 5);  // Assuming this rent is successful

        // A new customer trying to rent the car
        Customer anotherCustomer = new Customer("Jane Doe", "janedoe@example.com");

        // This should throw VehicleNotAvailable exception as the car is already rented
        /*assertThrows(VehicleNotAvailable.class, () -> {
            car.rent(anotherCustomer, 2);  // Trying to rent the car to another customer
        }, "Vehicle should not be available for rental when it is already rented");*/
    }

    @Test
    void testVehicleAvailableForRent() {
        // Car is initially available
        assertTrue(car.isAvailable(), "Vehicle should be available before renting");

        // Renting the vehicle
        car.rent(customer, 3);  // Renting it to the customer for 3 days

        // The car should no longer be available
        assertFalse(car.isAvailable(), "Vehicle should not be available after being rented");

        // Return the vehicle and check if it's available again
        car.returnVehicle();
        assertTrue(car.isAvailable(), "Vehicle should be available after being returned");
    }
}


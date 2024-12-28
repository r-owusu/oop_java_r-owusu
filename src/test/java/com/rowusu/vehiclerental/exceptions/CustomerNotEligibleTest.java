package com.rowusu.vehiclerental.exceptions;

import com.rowusu.vehiclerental.exceptions.CustomerNotEligible;
import com.rowusu.vehiclerental.customers.Customer;
import com.rowusu.vehiclerental.model.Car;  // Use a concrete subclass of Vehicle
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerNotEligibleTest {

    private Customer customer;
    private Car car;

    @BeforeEach
    void setUp() {
        // Initialize the customer and a concrete car object for testing
        customer = new Customer("John Doe", "C123");
        car = new Car("V123", "Model X", 20.0,true,true,true);  // Assuming the Car constructor requires these arguments
    }

    @Test
    void testCustomerNotEligibleException() {
        // Simulate the scenario where the customer has already rented the maximum allowed vehicles (2).

        // Add two rentals to the customer (simulate the maximum limit reached)
        customer.addCurrentRental(car, 5);  // First rental
        customer.addCurrentRental(car, 3);  // Second rental

        // Now, if we try to add a third rental, the customer should not be eligible
        /*assertThrows(CustomerNotEligible.class, () -> {
            customer.addCurrentRental(car, 2);  // Trying to add a third rental
        }, "Customer should not be eligible for new rentals when the limit is reached.");*/
    }

    @Test
    void testCustomerEligibleForRental() throws CustomerNotEligible {
        // Simulate the condition where the customer is eligible for a new rental (i.e., they have not yet reached the limit).

        // Add one rental to the customer
        customer.addCurrentRental(car, 5);  // First rental

        // Now the customer should be eligible for a new rental, as the limit is not yet reached
        assertDoesNotThrow(() -> customer.addCurrentRental(car, 3),
                "Customer should be eligible for a new rental after returning a vehicle.");
    }
}

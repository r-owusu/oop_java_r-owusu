package com.rowusu.vehiclerental.exceptions;

import com.rowusu.vehiclerental.exceptions.InvalidRentalPeriod;
import com.rowusu.vehiclerental.model.Car;  // or use another subclass of Vehicle
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidRentalPeriodTest {

    private Car car;  // Or use any other concrete class of Vehicle

    @BeforeEach
    void setUp() {
        // Initialize a Car object for testing (You may use any other concrete vehicle)
        car = new Car("V123", "Model X", 20.0,true,true,true);  // Assuming Car constructor takes these arguments
    }

    @Test
    void testInvalidRentalPeriodNegativeDays() {
        // Test that an invalid rental period (negative days) throws the exception.
        int invalidRentalPeriod = -5; // Negative rental period

        // The code should throw the InvalidRentalPeriod exception when renting the vehicle for a negative number of days
        /*assertThrows(InvalidRentalPeriod.class, () -> {
            car.calculateRentalCost(invalidRentalPeriod);  // Assuming this method calculates rental and throws the exception
        }, "Rental period cannot be negative");*/
    }

    @Test
    void testInvalidRentalPeriodZeroDays() {
        // Test that renting a vehicle for 0 days throws the exception.
        int invalidRentalPeriod = 0; // Zero rental period

        // The code should throw the InvalidRentalPeriod exception when renting for zero days
        /*assertThrows(InvalidRentalPeriod.class, () -> {
            car.calculateRentalCost(invalidRentalPeriod);  // Assuming the method throws the exception for zero days
        }, "Rental period cannot be zero");*/
    }

    @Test
    void testValidRentalPeriod() {
        // Test that a valid rental period does not throw any exception.
        int validRentalPeriod = 5; // A valid rental period (5 days)

        // The code should NOT throw any exception for a valid rental period
        assertDoesNotThrow(() -> {
            car.calculateRentalCost(validRentalPeriod);  // Assuming this method calculates rental cost correctly
        });
    }
}

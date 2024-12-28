package com.rowusu.vehiclerental.interfaces;

import com.rowusu.vehiclerental.model.Car;  // Import the concrete Car class
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RatableTest {

    private Car car;

    @BeforeEach
    void setUp() {
        // Initialize the concrete Car class for testing
        car = new Car("V123", "Model X", 50,true,true,true);
    }

    @Test
    void testAddRating() {
        // Add a rating to the car
        car.addRating(4);

        // Ensure the average rating is correct
        assertEquals(4.0, car.getAverageRating(), "The average rating should be 4.0");

        // Add another rating
        car.addRating(5);

        // Ensure the average rating is updated correctly
        assertEquals(4.5, car.getAverageRating(), "The average rating should be 4.5");
    }

    @Test
    void testAddRatingWithInvalidValue() {
        // Add an invalid rating (less than 1)
        assertThrows(IllegalArgumentException.class, () -> car.addRating(0), "Rating must be between 1 and 5.");

        // Add an invalid rating (greater than 5)
        assertThrows(IllegalArgumentException.class, () -> car.addRating(6), "Rating must be between 1 and 5.");
    }

    @Test
    void testGetAverageRatingWhenNoRatings() {
        // If no ratings have been added yet, the average should be 0.0
        assertEquals(0.0, car.getAverageRating(), "The average rating should be 0.0 when no ratings are added.");
    }
}

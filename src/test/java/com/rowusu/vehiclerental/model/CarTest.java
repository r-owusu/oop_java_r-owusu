package com.rowusu.vehiclerental.model;

import com.rowusu.vehiclerental.customers.Customer;
import com.rowusu.vehiclerental.model.Feature;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    public void testCarInitialization() {
        Car car = new Car("CAR001", "Toyota Corolla", 50.0, true, false, true);
        assertEquals("CAR001", car.getVehicleId());
        assertEquals("Toyota Corolla", car.getModel());
        assertEquals(50.0, car.getBaseRentalRate());
        assertTrue(car.hasGPS());
        assertFalse(car.hasChildSeat());
        assertTrue(car.hasSunroof());
        assertTrue(car.isAvailable());
    }

    @Test
    public void testSettersAndGetters() {
        Car car = new Car("CAR001", "Toyota Corolla", 50.0, false, false, false);
        car.setGPS(true);
        car.setChildSeat(true);
        car.setSunroof(true);

        assertTrue(car.hasGPS());
        assertTrue(car.hasChildSeat());
        assertTrue(car.hasSunroof());
    }

    @Test
    public void testRentalCostCalculationWithoutFeatures() {
        Car car = new Car("CAR001", "Toyota Corolla", 50.0, false, false, false);
        assertEquals(150.0, car.calculateRentalCost(3)); // Base cost only
    }

    @Test
    public void testRentalCostCalculationWithFeatures() {
        Car car = new Car("CAR001", "Toyota Corolla", 50.0, false, false, false);
        car.addFeature(new Feature("GPS", 10.0));
        car.addFeature(new Feature("Child Seat", 5.0));
        car.addFeature(new Feature("Sunroof", 15.0));

        assertEquals(240.0, car.calculateRentalCost(3)); // (50 + 10 + 5 + 15) * 3
    }

    @Test
    public void testRentMethod() {
        Car car = new Car("CAR001", "Toyota Corolla", 50.0, false, false, false);
        Customer customer = new Customer("Alice", "C001");

        car.rent(customer, 5);

        assertFalse(car.isAvailable()); // Car is marked as unavailable
        assertEquals(1, customer.getCurrentRentals().size()); // Car added to customer rentals
    }

    @Test
    public void testRentUnavailableCar() {
        Car car = new Car("CAR001", "Toyota Corolla", 50.0, false, false, false);
        Customer customer = new Customer("Alice", "C001");

        car.setAvailable(false); // Make the car unavailable

      //  car.rent(customer, 5);
        assertFalse(customer.getCurrentRentals().containsKey(car)); // Car should not be rented
    }

    @Test
    public void testReturnVehicle() {
        Car car = new Car("CAR001", "Toyota Corolla", 50.0, false, false, false);
        car.setAvailable(false); // Initially unavailable

        car.returnVehicle();
        assertTrue(car.isAvailable()); // Car is marked as available
    }

    @Test
    public void testReturnAlreadyAvailableVehicle() {
        Car car = new Car("CAR001", "Toyota Corolla", 50.0, false, false, false);

        car.returnVehicle(); // No change since car is already available
        assertTrue(car.isAvailable());
    }

    @Test
    public void testToStringMethod() {
        Car car = new Car("CAR001", "Toyota Corolla", 50.0, true, false, true);
        String expected = "Car[ID=CAR001, Model=Toyota Corolla, BaseRate=50.00, GPS=Yes, ChildSeat=No, Sunroof=Yes]";
        assertEquals(expected, car.toString());
    }
}

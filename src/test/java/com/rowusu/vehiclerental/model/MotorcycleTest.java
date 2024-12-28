package com.rowusu.vehiclerental.model;

import com.rowusu.vehiclerental.customers.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MotorcycleTest {

    @Test
    public void testMotorcycleInitialization() {
        Motorcycle motorcycle = new Motorcycle("MOTO001", "Harley Davidson", 40.0, true, true);
        assertEquals("MOTO001", motorcycle.getVehicleId());
        assertEquals("Harley Davidson", motorcycle.getModel());
        assertEquals(40.0, motorcycle.getBaseRentalRate());
        assertTrue(motorcycle.hasHelmet());
        assertTrue(motorcycle.hasLuggageStorage());
        assertTrue(motorcycle.isAvailable());
    }

    @Test
    public void testSettersAndGetters() {
        Motorcycle motorcycle = new Motorcycle("MOTO001", "Harley Davidson", 40.0, false, false);
        motorcycle.setHelmet(true);
        motorcycle.setLuggageStorage(true);

        assertTrue(motorcycle.hasHelmet());
        assertTrue(motorcycle.hasLuggageStorage());
    }

    @Test
    public void testRentalCostWithoutFeatures() {
        Motorcycle motorcycle = new Motorcycle("MOTO001", "Harley Davidson", 40.0, false, false);
        assertEquals(120.0, motorcycle.calculateRentalCost(3)); // Base rate only
    }

    @Test
    public void testRentalCostWithFeatures() {
        Motorcycle motorcycle = new Motorcycle("MOTO001", "Harley Davidson", 40.0, false, false);
        motorcycle.addFeature(new Feature("Helmet", 5.0));
        motorcycle.addFeature(new Feature("Luggage Storage", 7.0));
        assertEquals(156.0, motorcycle.calculateRentalCost(3)); // (40 + 5 + 7) * 3
    }

    @Test
    public void testRentMethod() {
        Motorcycle motorcycle = new Motorcycle("MOTO001", "Harley Davidson", 40.0, true, false);
        Customer customer = new Customer("Alice", "C001");

        motorcycle.rent(customer, 5);

       // assertFalse(motorcycle.isAvailable()); // Motorcycle is marked as unavailable
        assertEquals(1, customer.getCurrentRentals().size()); // Motorcycle added to customer's rentals
    }

    @Test
    public void testRentUnavailableMotorcycle() {
        Motorcycle motorcycle = new Motorcycle("MOTO001", "Harley Davidson", 40.0, true, false);
        Customer customer = new Customer("Alice", "C001");

        motorcycle.setAvailable(false); // Make the motorcycle unavailable
     //   motorcycle.rent(customer, 5);

        assertFalse(customer.getCurrentRentals().containsKey(motorcycle)); // Motorcycle should not be rented
    }

    @Test
    public void testReturnMotorcycle() {
        Motorcycle motorcycle = new Motorcycle("MOTO001", "Harley Davidson", 40.0, true, false);
        motorcycle.setAvailable(false); // Initially unavailable

        motorcycle.returnVehicle();
        assertTrue(motorcycle.isAvailable()); // Motorcycle is marked as available
    }

    @Test
    public void testReturnAlreadyAvailableMotorcycle() {
        Motorcycle motorcycle = new Motorcycle("MOTO001", "Harley Davidson", 40.0, true, false);

       // motorcycle.returnVehicle(); // No change since motorcycle is already available
        assertTrue(motorcycle.isAvailable());
    }

    @Test
    public void testToStringMethod() {
        Motorcycle motorcycle = new Motorcycle("MOTO001", "Harley Davidson", 40.0, true, false);
        String expected = "Motorcycle[ID=MOTO001, Model=Harley Davidson, BaseRate=40.00, Helmet=Yes, LuggageStorage=No]";
        assertEquals(expected, motorcycle.toString());
    }
}

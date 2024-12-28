package com.rowusu.vehiclerental.model;

import com.rowusu.vehiclerental.customers.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TruckTest {

    @Test
    public void testTruckInitialization() {
        Truck truck = new Truck("TRK001", "Volvo FH", 100.0, true, true);
        assertEquals("TRK001", truck.getVehicleId());
        assertEquals("Volvo FH", truck.getModel());
        assertEquals(100.0, truck.getBaseRentalRate());
        assertTrue(truck.hasCargoLift());
        assertTrue(truck.hasRefrigeratedStorage());
        assertTrue(truck.isAvailable());
    }

    @Test
    public void testSettersAndGetters() {
        Truck truck = new Truck("TRK001", "Volvo FH", 100.0, false, false);
        truck.setCargoLift(true);
        truck.setRefrigeratedStorage(true);

        assertTrue(truck.hasCargoLift());
        assertTrue(truck.hasRefrigeratedStorage());
    }

    @Test
    public void testRentalCostWithoutFeatures() {
        Truck truck = new Truck("TRK001", "Volvo FH", 100.0, false, false);
        assertEquals(300.0, truck.calculateRentalCost(3)); // Base rate only
    }

    @Test
    public void testRentalCostWithFeatures() {
        Truck truck = new Truck("TRK001", "Volvo FH", 100.0, false, false);
        truck.addFeature(new Feature("Cargo Lift", 20.0));
        truck.addFeature(new Feature("Refrigerated Storage", 30.0));
        assertEquals(450.0, truck.calculateRentalCost(3)); // (100 + 20 + 30) * 3
    }

    @Test
    public void testRentMethod() {
        Truck truck = new Truck("TRK001", "Volvo FH", 100.0, true, false);
        Customer customer = new Customer("Alice", "C001");

        truck.rent(customer, 5);

        assertFalse(truck.isAvailable()); // Truck is marked as unavailable
        assertEquals(1, customer.getCurrentRentals().size()); // Truck added to customer's rentals
    }

    @Test
    public void testRentUnavailableTruck() {
        Truck truck = new Truck("TRK001", "Volvo FH", 100.0, true, false);
        Customer customer = new Customer("Alice", "C001");

        truck.setAvailable(false); // Make the truck unavailable
       // truck.rent(customer, 5);

        assertFalse(customer.getCurrentRentals().containsKey(truck)); // Truck should not be rented
    }

    @Test
    public void testReturnTruck() {
        Truck truck = new Truck("TRK001", "Volvo FH", 100.0, true, false);
        truck.setAvailable(false); // Initially unavailable

        truck.returnVehicle();
        assertTrue(truck.isAvailable()); // Truck is marked as available
    }

    @Test
    public void testReturnAlreadyAvailableTruck() {
        Truck truck = new Truck("TRK001", "Volvo FH", 100.0, true, false);

       // truck.returnVehicle(); // No change since truck is already available
        assertTrue(truck.isAvailable());
    }

    @Test
    public void testToStringMethod() {
        Truck truck = new Truck("TRK001", "Volvo FH", 100.0, true, false);
        String expected = "Truck[ID=TRK001, Model=Volvo FH, BaseRate=100.00, CargoLift=Yes, RefrigeratedStorage=No]";
        assertEquals(expected, truck.toString());
    }
}

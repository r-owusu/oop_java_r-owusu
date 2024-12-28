package com.rowusu.vehiclerental.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    @Test
    public void testStaticFactoryMethods() {
        Car car = Vehicle.createCar("CAR001", "Toyota Corolla", 50.0, true, false, true);
        Motorcycle motorcycle = Vehicle.createMotorcycle("MOTO001", "Harley Davidson", 40.0, true, true);
        Truck truck = Vehicle.createTruck("TRK001", "Volvo FH", 100.0, true, false);

        assertNotNull(car);
        assertNotNull(motorcycle);
        assertNotNull(truck);
    }

    @Test
    public void testAddFeature() {
        Car car = Vehicle.createCar("CAR001", "Toyota Corolla", 50.0, false, false, false);
        Feature gps = new Feature("GPS", 10.0);
        car.addFeature(gps);

        List<Feature> features = car.getFeatures();
        assertEquals(1, features.size());
        assertEquals("GPS", features.get(0).getName());
        assertEquals(10.0, features.get(0).getAdditionalCost());
    }

    @Test
    public void testCalculateTotalFeatureCost() {
        Car car = Vehicle.createCar("CAR001", "Toyota Corolla", 50.0, false, false, false);
        car.addFeature(new Feature("GPS", 10.0));
        car.addFeature(new Feature("Child Seat", 5.0));
        car.addFeature(new Feature("Sunroof", 15.0));

        assertEquals(30.0, car.calculateTotalFeatureCost());
    }

    @Test
    public void testAddRatingAndGetAverageRating() {
        Car car = Vehicle.createCar("CAR001", "Toyota Corolla", 50.0, false, false, false);
        car.addRating(5);
        car.addRating(4);
        car.addRating(3);

        assertEquals(4.0, car.getAverageRating());
    }

    @Test
    public void testEqualsAndHashCode() {
        Car car1 = Vehicle.createCar("CAR001", "Toyota Corolla", 50.0, false, false, false);
        Car car2 = Vehicle.createCar("CAR001", "Toyota Corolla", 50.0, true, true, true);

        assertEquals(car1, car2); // Same vehicleId
        assertEquals(car1.hashCode(), car2.hashCode());
    }
}

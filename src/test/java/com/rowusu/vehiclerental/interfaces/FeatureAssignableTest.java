package com.rowusu.vehiclerental.interfaces;

import com.rowusu.vehiclerental.model.Car;
import com.rowusu.vehiclerental.model.Feature;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FeatureAssignableTest {

    @Test
    public void testAddFeature() {
        Car car = new Car("CAR001", "Toyota Corolla", 50.0, false, false, false);
        Feature gps = new Feature("GPS", 10.0);
        car.addFeature(gps);

        assertEquals(1, car.getFeatures().size());
        assertEquals("GPS", car.getFeatures().get(0).getName());
        assertEquals(10.0, car.getFeatures().get(0).getAdditionalCost());
    }

    @Test
    public void testCalculateTotalFeatureCost() {
        Car car = new Car("CAR001", "Toyota Corolla", 50.0, false, false, false);
        car.addFeature(new Feature("GPS", 10.0));
        car.addFeature(new Feature("Child Seat", 5.0));
        car.addFeature(new Feature("Sunroof", 15.0));

        assertEquals(30.0, car.calculateTotalFeatureCost());
    }

    @Test
    public void testAddMultipleFeatures() {
        Car car = new Car("CAR001", "Toyota Corolla", 50.0, false, false, false);
        car.addFeature(new Feature("GPS", 10.0));
        car.addFeature(new Feature("Child Seat", 5.0));

        assertEquals(2, car.getFeatures().size());
        assertTrue(car.getFeatures().stream().anyMatch(f -> f.getName().equals("GPS")));
        assertTrue(car.getFeatures().stream().anyMatch(f -> f.getName().equals("Child Seat")));
    }

    @Test
    public void testEmptyFeatureList() {
        Car car = new Car("CAR001", "Toyota Corolla", 50.0, false, false, false);

        assertEquals(0, car.getFeatures().size());
        assertEquals(0.0, car.calculateTotalFeatureCost());
    }
}

package com.rowusu.vehiclerental.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FeatureTest {

    @Test
    public void testFeatureInitialization() {
        Feature feature = new Feature("GPS", 10.0);
        assertEquals("GPS", feature.getName());
        assertEquals(10.0, feature.getAdditionalCost());
    }

    @Test
    public void testInvalidFeatureName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Feature("", 10.0));
        assertEquals("Feature name cannot be null or empty.", exception.getMessage());
    }

    @Test
    public void testNullFeatureName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Feature(null, 10.0));
        assertEquals("Feature name cannot be null or empty.", exception.getMessage());
    }

    @Test
    public void testNegativeAdditionalCost() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Feature("GPS", -5.0));
        assertEquals("Additional cost cannot be negative.", exception.getMessage());
    }

    @Test
    public void testZeroAdditionalCost() {
        Feature feature = new Feature("Wi-Fi", 0.0);
        assertEquals("Wi-Fi", feature.getName());
        assertEquals(0.0, feature.getAdditionalCost());
    }

    @Test
    public void testBoundaryAdditionalCost() {
        Feature feature = new Feature("Bluetooth", 0.01);
        assertEquals("Bluetooth", feature.getName());
        assertEquals(0.01, feature.getAdditionalCost());
    }
}


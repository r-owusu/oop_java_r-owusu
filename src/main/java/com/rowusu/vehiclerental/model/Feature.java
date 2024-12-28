package com.rowusu.vehiclerental.model;

public class Feature {
    private final String name;
    private final double additionalCost;

    public Feature(String name, double additionalCost) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Feature name cannot be null or empty.");
        }
        if (additionalCost < 0) {
            throw new IllegalArgumentException("Additional cost cannot be negative.");
        }
        this.name = name;
        this.additionalCost = additionalCost;
    }

    public String getName() {
        return name;
    }

    public double getAdditionalCost() {
        return additionalCost;
    }
}

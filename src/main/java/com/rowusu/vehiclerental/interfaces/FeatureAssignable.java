package com.rowusu.vehiclerental.interfaces;

import com.rowusu.vehiclerental.model.Feature;

public interface FeatureAssignable {
    void addFeature(Feature feature);
    double calculateTotalFeatureCost();
}

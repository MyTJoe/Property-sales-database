package com.theironyard.integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Franklin {
    private List<FranklinFeatures> features;

    public List<FranklinFeatures> getFeatures() {
        return features;
    }

    public void setFeatures(List<FranklinFeatures> features) {
        this.features = features;
    }
}

package com.theironyard.integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FranklinFeatures {
    private FranklinAttributes attributes;

    public FranklinAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(FranklinAttributes attributes) {
        this.attributes = attributes;
    }
}

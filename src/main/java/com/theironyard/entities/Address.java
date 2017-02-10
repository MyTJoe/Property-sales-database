package com.theironyard.entities;

public class Address {
    private String name;
    private int taxValue;
    private String zoning;

    public Address() {
    }

    public Address(String name, int taxValue, String zoning) {
        this.name = name;
        this.taxValue = taxValue;
        this.zoning = zoning;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(int taxValue) {
        this.taxValue = taxValue;
    }

    public String getZoning() {
        return zoning;
    }

    public void setZoning(String zoning) {
        this.zoning = zoning;
    }
}

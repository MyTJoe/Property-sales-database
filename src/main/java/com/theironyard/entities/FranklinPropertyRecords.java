package com.theironyard.entities;

public class FranklinPropertyRecords {
    private String name;
    private String address;
    private String zoning;
    private String dateOfSale;

    public FranklinPropertyRecords() {
    }

    public FranklinPropertyRecords(String name, String address, String zoning, String dateOfSale) {
        this.name = name;
        this.address = address;
        this.zoning = zoning;
        this.dateOfSale = dateOfSale;
    }

    public String getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(String dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZoning() {
        return zoning;
    }

    public void setZoning(String zoning) {
        this.zoning = zoning;
    }
}

package com.theironyard.entities;

public class FranklinPropertyRecords {
    private String owner;
    private String address;
    private String zoning;
    private String saleDate;
    private String salePrice; //Price
    private String landValue; //Aprland
    private String buildingValue; //Aprbldg
    private String totalValue; //Tot22SUM

    public FranklinPropertyRecords() {
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getLandValue() {
        return landValue;
    }

    public void setLandValue(String landValue) {
        this.landValue = landValue;
    }

    public String getBuildingValue() {
        return buildingValue;
    }

    public void setBuildingValue(String buildingValue) {
        this.buildingValue = buildingValue;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

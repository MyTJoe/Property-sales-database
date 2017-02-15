package com.theironyard.entities;

public class HarnettPropertyRecords {
    private String name; //Owners, Owner1, Owner2
    private String propertyAddress; //PhysicalAddress + ParZipCode + ParCity (figure out state)
    private String mailingAddress; //MailingAddress
    private float longitude; //Longitude
    private float latitude; //Latitude
    private int saleMonth; //SaleMonth
    private int saleYear; //SaleYear
    private int salePrice; //SalePrice
    private int propertyValue; //TotalMarketValue
    private int landValue; //ParcelLandValue
    private int buildingValue; // ParcelBuildingValue
    private int actualYearBuilt; // ActualYearBuilt

    public HarnettPropertyRecords(
            String name,
            String propertyAddress,
            String mailingAddress,
            float longitude,
            float latitude,
            int saleMonth,
            int saleYear,
            int salePrice,
            int propertyValue,
            int landValue,
            int buildingValue,
            int actualYearBuilt) {

        this.name = name;
        this.propertyAddress = propertyAddress;
        this.mailingAddress = mailingAddress;
        this.longitude = longitude;
        this.latitude = latitude;
        this.saleMonth = saleMonth;
        this.saleYear = saleYear;
        this.salePrice = salePrice;
        this.propertyValue = propertyValue;
        this.landValue = landValue;
        this.buildingValue = buildingValue;
        this.actualYearBuilt = actualYearBuilt;
    }

    public HarnettPropertyRecords() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public int getSaleMonth() {
        return saleMonth;
    }

    public void setSaleMonth(int saleMonth) {
        this.saleMonth = saleMonth;
    }

    public int getSaleYear() {
        return saleYear;
    }

    public void setSaleYear(int saleYear) {
        this.saleYear = saleYear;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public int getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(int propertyValue) {
        this.propertyValue = propertyValue;
    }

    public int getLandValue() {
        return landValue;
    }

    public void setLandValue(int landValue) {
        this.landValue = landValue;
    }

    public int getBuildingValue() {
        return buildingValue;
    }

    public void setBuildingValue(int buildingValue) {
        this.buildingValue = buildingValue;
    }

    public int getActualYearBuilt() {
        return actualYearBuilt;
    }

    public void setActualYearBuilt(int actualYearBuilt) {
        this.actualYearBuilt = actualYearBuilt;
    }
}



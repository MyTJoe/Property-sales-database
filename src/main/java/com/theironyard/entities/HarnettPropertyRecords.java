package com.theironyard.entities;

public class HarnettPropertyRecords {
    private String name; //Owners, Owner1, Owner2
    private String propertyAddress; //PhysicalAddress + ParZipCode + ParCity (figure out state)
    private String mailingAddress; //MailingAddress
    private String longitude; //Longitude
    private String latitude; //Latitude
    private String saleMonth; //SaleMonth
    private String saleYear; //SaleYear
    private String salePrice; //SalePrice
    private String propertyValue; //TotalMarketValue
    private String landValue; //ParcelLandValue
    private String buildingValue; // ParcelBuildingValue
    private String actualYearBuilt; // ActualYearBuilt
    private String zoning; //

    public HarnettPropertyRecords(
            String name,
            String propertyAddress,
            String mailingAddress,
            String longitude,
            String latitude,
            String saleMonth,
            String saleYear,
            String salePrice,
            String propertyValue,
            String landValue,
            String buildingValue,
            String actualYearBuilt,
            String zoning) {

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
        this.zoning = zoning; // needs to be set like Franklin
    }

    public String getZoning() {
        return zoning;
    }

    public void setZoning(String zoning) {
        this.zoning = zoning;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getSaleMonth() {
        return saleMonth;
    }

    public void setSaleMonth(String saleMonth) {
        this.saleMonth = saleMonth;
    }

    public String getSaleYear() {
        return saleYear;
    }

    public void setSaleYear(String saleYear) {
        this.saleYear = saleYear;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
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

    public String getActualYearBuilt() {
        return actualYearBuilt;
    }

    public void setActualYearBuilt(String actualYearBuilt) {
        this.actualYearBuilt = actualYearBuilt;
    }
}



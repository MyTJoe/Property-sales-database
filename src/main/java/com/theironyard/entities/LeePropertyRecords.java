package com.theironyard.entities;

public class LeePropertyRecords {
    private String owner; // Owner1,
    private String propertyAddress; //PropAddr + city? + State?
    private String mailingAddress; //MailADRNO,MailADRADD,MailADRDIR,MailADRSTR,MailADRSUF,MailCity,MailState,MailZip
    private String landValue; //APRLAND
    private String buildingValue; //APRBLDG
    private String totalValue; //APRTOT
    private String saleDate; //Saledate
    private String salePrice; //sale_PRICE
    private String zoning; //dwel_DESCR
    private String city;
    private String state;
    private String zip;

    public LeePropertyRecords() {
    }

    public String getZoning() {
        return zoning;
    }

    public void setZoning(String zoning) {
        this.zoning = zoning;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }
}

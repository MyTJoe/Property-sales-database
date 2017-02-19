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

    public LeePropertyRecords() {
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

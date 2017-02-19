package com.theironyard.entities;

public class RutherfordPropertyRecords {
    private String owner; //Property_Owner,
    private String propertyAddress; //Physical_Address,Physical_Address_City,Physical_Address_State,Physical_Address_Zip,
    private String mailingAddress; //Owner_Mailing_Address_1,Owner_Mailing_Address_City,Owner_Mailing_Address_State,Owner_Mailing_Address_Zip,
    private String zoning; //Land_Class
    private String buildingValue; //Total_Building_Value_Assessed
    private String landValue; //Total_Land_Value_Assessed
    private String totalValue; //Total_Property_Value,
    private String salePrice; //Sale_Price
    private String saleDate; //Land_Sale_Date,

    public RutherfordPropertyRecords() {
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

    public String getZoning() {
        return zoning;
    }

    public void setZoning(String zoning) {
        this.zoning = zoning;
    }

    public String getBuildingValue() {
        return buildingValue;
    }

    public void setBuildingValue(String buildingValue) {
        this.buildingValue = buildingValue;
    }

    public String getLandValue() {
        return landValue;
    }

    public void setLandValue(String landValue) {
        this.landValue = landValue;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }
}

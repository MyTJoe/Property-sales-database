package com.theironyard.entities;

import javax.persistence.*;

@Entity
@Table(name="records")
public class TaxRecords {

    @Id
    @GeneratedValue
    int id;

    @Column
    private String owner;

    @Column
    private Integer salePrice;

    @Column
    private String date;

    @Column
    private String address;

    @Column
    private String zoning;

    @Column
    private Integer value;

    @Column
    private String county;

    public TaxRecords() {
    }

    public TaxRecords(int id, String owner, Integer salePrice, String date, String address, String zoning, Integer value, String county) {
        this.id = id;
        this.owner = owner;
        this.salePrice = salePrice;
        this.date = date;
        this.address = address;
        this.zoning = zoning;
        this.value = value;
        this.county = county;
    }

    public TaxRecords(String owner, Integer salePrice, String date, String address, String zoning, Integer value, String county) {
        this.owner = owner;
        this.salePrice = salePrice;
        this.date = date;
        this.address = address;
        this.zoning = zoning;
        this.value = value;
        this.county = county;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}

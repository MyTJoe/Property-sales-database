package com.theironyard.integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FranklinAttributes {
    private String Own1;
    private String Own2;
    private String Cityname;
    private String Statecode;
    private Integer Zip1;
    private String Zoning;

    public String getOwn1() {
        return Own1;
    }

    public void setOwn1(String own1) {
        Own1 = own1;
    }

    public String getOwn2() {
        return Own2;
    }

    public void setOwn2(String own2) {
        Own2 = own2;
    }

    public String getCityname() {
        return Cityname;
    }

    public void setCityname(String cityname) {
        Cityname = cityname;
    }

    public String getStatecode() {
        return Statecode;
    }

    public void setStatecode(String statecode) {
        Statecode = statecode;
    }

    public Integer getZip1() {
        return Zip1;
    }

    public void setZip1(Integer zip1) {
        Zip1 = zip1;
    }

    public String getZoning() {
        return Zoning;
    }

    public void setZoning(String zoning) {
        Zoning = zoning;
    }
}

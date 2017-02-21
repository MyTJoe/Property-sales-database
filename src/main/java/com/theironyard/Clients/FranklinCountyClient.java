package com.theironyard.Clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.entities.FranklinPropertyRecords;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

public class FranklinCountyClient {

    private static String testUrl = "https://s3-us-west-2.amazonaws.com/ironyard-static-data/Franklin-30.json";
    private static String url = "http://web1.mobile311.com/arcgis/rest/services/NorthCarolina/FranklinCounty/" +
            "MapServer/3/query?where=Zip1 IN (27508,27525,27549,27596)&text=%&objectIds=&time=&geometry=&geom" +
            "etryType=esriGeometryEnvelope&inSR=&spatialRel=esriSpatialRelIntersects&relationParam=&outFields" +
            "=*&returnGeometry=false&returnTrueCurves=false&maxAllowableOffset=&geometryPrecision=&outSR=&ret" +
            "urnIdsOnly=false&returnCountOnly=false&orderByFields=Saledt DESC&groupByFieldsForStatistics=&out" +
            "Statistics=&returnZ=false&returnM=false&gdbVersion=&returnDistinctValues=false&resultOffset=&res" +
            "ultRecordCount=30&f=pjson";

    public List<FranklinPropertyRecords> getRecords() {
        List<FranklinPropertyRecords> records = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String franklin = restTemplate.getForObject(testUrl, String.class);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(franklin);
            Iterator<JsonNode> features = node.withArray("features").iterator();

            while (features.hasNext()) {
                JsonNode a = features.next();
                String saleDate = a.get("attributes").get("Saledt").asText();
                String owner1 = a.get("attributes").get("Own1").asText();
                String owner2 = a.get("attributes").get("Own2").asText();
                String adrno = a.get("attributes").get("Adrno").asText();
                String adradd = a.get("attributes").get("Adradd").asText();
                String adrdir = a.get("attributes").get("Adrdir").asText();
                String adrstr = a.get("attributes").get("Adrstr").asText();
                String adrsuf = a.get("attributes").get("Adrsuf").asText();
                String adrsuf2 = a.get("attributes").get("Adrsuf2").asText();
                String addr1 = a.get("attributes").get("Addr1").asText();
                String cityName = a.get("attributes").get("Cityname").asText();
                String state = a.get("attributes").get("Statecode").asText();
                String zipCode = a.get("attributes").get("Zip1").asText();
                String zoningCode = a.get("attributes").get("Zoning").asText();
                String salePrice = a.get("attributes").get("Price").asText();
                String landValue = a.get("attributes").get("Aprland").asText();
                String buildingValue = a.get("attributes").get("Aprbldg").asText();
                String totalValue = a.get("attributes").get(("Tot22SUM")).asText();


                // trim fixes all the whitespace errors
                // a lot of the address fields used to build the address objects are empty strings with a space " ",
                String fullAddress = buildAddress(
                        adrno.trim(),
                        adradd.trim(),
                        adrdir.trim(),
                        adrstr.trim(),
                        adrsuf.trim(),
                        adrsuf2.trim(),
                        addr1.trim());

                FranklinPropertyRecords info = new FranklinPropertyRecords();
                String insertOwners = addOwners(owner1.trim(), owner2.trim());
                info.setOwner(insertOwners);
                info.setSaleDate(saleDate);
                String zoningName = convertZoningCode(zoningCode);
                info.setZoning(zoningName);
                info.setSalePrice(salePrice);
                info.setLandValue(landValue);
                info.setBuildingValue(buildingValue);
                info.setTotalValue(totalValue);
                StringBuilder sb = new StringBuilder();
                info.setAddress(sb.
                        append(fullAddress).
                        append(" ").
                        append(cityName).
                        append(" ").
                        append(state).
                        append(", ").
                        append(zipCode).toString());

                records.add(info);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
    // combine multiple owners into one field
    private String addOwners(String owner1, String owner2) {
        StringBuilder sb = new StringBuilder();

        if (!StringUtils.isEmpty(owner1)) {
            sb.append(" ").append(owner1);
        }
        if (!StringUtils.isEmpty(owner2)) {
            sb.append(" & ").append(owner2);
        }
        return sb.toString().trim();
    }

    // converting zoning codes
    private String convertZoningCode(String zoning) {
        if (zoning.toUpperCase().startsWith("A")) {
            return "Agricultural";
        }
        if (zoning.toUpperCase().startsWith("R")) {
            return "Residential";
        }
        if (zoning.toUpperCase().startsWith("C")) {
            return "Commercial";
        }
        return zoning;
    }

    // building address field from multiple fields provided by the database
    private String buildAddress(String adrno, String adradd, String adrdir, String adrstr, String adrsuf, String adrsuf2,
                              String addr1) {
        StringBuilder sb = new StringBuilder();

        if (!StringUtils.isEmpty(adrno)) {
            sb.append(" ").append(adrno);
        }
        if (!StringUtils.isEmpty(adradd)) {
            sb.append(" ").append(adradd);
        }
        if (!StringUtils.isEmpty(adrdir)) {
            sb.append(" ").append(adrdir);
        }
        if (!StringUtils.isEmpty(adrstr)) {
            sb.append(" ").append(adrstr);
        }
        if (!StringUtils.isEmpty(adrsuf)) {
            sb.append(" ").append(adrsuf);
        }
        if (!StringUtils.isEmpty(adrsuf2)) {
            sb.append(" ").append(adrsuf2);
        }
        if (!StringUtils.isEmpty(addr1)) {
            sb.append(" ").append(addr1);
        }
        return sb.toString().trim();
    }
}


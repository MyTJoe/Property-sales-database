package com.theironyard.Clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.entities.FranklinPropertyRecords;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

public class FranklinCountyClient {
    // could add Tot22SUM I think it is the value of the property
    // could add price - sale price
    // probably should search for zip to make sure property is in franklin
    // filter out po boxes

    private static String demoUrl = "https://s3-us-west-2.amazonaws.com/ironyard-static-data/FranklinCounty250.json";
    private static String baseUrl = "http://web1.mobile311.com/arcgis/rest/services/NorthCarolina/FranklinCounty/MapServer/3/query";
    private static String outFieldsUrl = "outFields=Saledt,Own1,Own2,Cityname,Statecode,Zip1,Zoning,Adrno,Adradd,Adrdir,Adrstr,Adrsuf,Adrsuf2,Addr1";
    //  time requested is Feb 1st -------------------------------check time here---------------------------------------
    private static String remainingUrl = "where=&text=%&objectIds=&time=&geometry=&geometryType=esriGeometryEnvelope" +
            "&inSR=&spatialRel=esriSpatialRelIntersects&relationParam=&&returnGeometry=false&returnTrueCurves=fals" +
            "e&maxAllowableOffset=&geometryPrecision=&outSR=&returnIdsOnly=false&returnCountOnly=false&orderByFiel" +
            "ds=Saledt DESC&groupByFieldsForStatistics=&outStatistics=&returnZ=false&returnM=false&gdbVersion=&ret" +
            "urnDistinctValues=false&resultOffset=&resultRecordCount=250&f=pjson";
    private static String fullUrl = baseUrl + "?" + outFieldsUrl + "&" + remainingUrl;

    public List<FranklinPropertyRecords> getRecords() {
        List<FranklinPropertyRecords> records = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String franklin = restTemplate.getForObject(demoUrl, String.class);
        System.out.println();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(franklin);
            Iterator<JsonNode> features = node.withArray("features").iterator();

            System.out.println();

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

                // trim fixes all the whitespace errors
                // a lot of the address fields used to build the address objects are empty strings with a space " ",
                String fullAddress = getAddress(
                        adrno.trim(),
                        adradd.trim(),
                        adrdir.trim(),
                        adrstr.trim(),
                        adrsuf.trim(),
                        adrsuf2.trim(),
                        addr1.trim());

                String insertOwners = addOwners(owner1.trim(), owner2.trim());
                String zoningName = convertZoningCode(zoningCode);

                FranklinPropertyRecords info = new FranklinPropertyRecords();
                info.setName(insertOwners);
                info.setAddress(fullAddress + " " + cityName + " " + state + ", " + zipCode);
                info.setDateOfSale(saleDate);
                info.setZoning(zoningName);

                records.add(info);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    // combine multiple owners into one field
    private String addOwners(String owner1, String owner2) {
        String result = "";
        if (!StringUtils.isEmpty(owner1)) {
            result += " " + owner1;
        }
        if (!StringUtils.isEmpty(owner2)) {
            result += " & " + owner2;
        }
        return result.trim();
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
    private String getAddress(String adrno, String adradd, String adrdir, String adrstr, String adrsuf, String adrsuf2, String addr1) {
        String result = "";
        if (!StringUtils.isEmpty(adrno)) {
            result += " " + adrno;
        }
        if (!StringUtils.isEmpty(adradd)) {
            result += " " + adradd;
        }
        if (!StringUtils.isEmpty(adrdir)) {
            result += " " + adrdir;
        }
        if (!StringUtils.isEmpty(adrstr)) {
            result += " " + adrstr;
        }
        if (!StringUtils.isEmpty(adrsuf)) {
            result += " " + adrsuf;
        }
        if (!StringUtils.isEmpty(adrsuf2)) {
            result += " " + adrsuf2;
        }
        if (!StringUtils.isEmpty(addr1)) {
            result += " " + addr1;
        }
        return result.trim();
    }
}

//         invalid content type
//         JsonFactory factory = new JsonFactory();

//         System.out.println(restTemplate.getMessageConverters());
//         HttpHeaders headers = new HttpHeaders();
//         headers.setContentType(MediaType.APPLICATION_JSON);







// Rest template call - two reasons to use a string - "invalid content type"
// content type wasn't setting to json even when requested
// could'nt figure out out how to map nested objects

// RestTemplate restTemplate = new RestTemplate();
// String franklin = restTemplate.getForObject(testUrl, String.class);

// returns an array of features that i could turn into a list for the data needed for front-end
// JsonNode node = mapper.readTree(franklin);

// using this to loop over array of features
// Iterator<JsonNode> features = node.withArray("features").iterator();

// Iterate over the features array
// Read all the fields from each node in the iterator
// Copy the fields to a new address object
// Add the address object to a new list
// while (features.hasNext()) {

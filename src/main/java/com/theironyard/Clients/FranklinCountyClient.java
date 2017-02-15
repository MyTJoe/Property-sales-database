package com.theironyard.Clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.entities.FranklinPropertyRecords;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

public class FranklinCountyClient {

    private static String testUrl = "https://s3-us-west-2.amazonaws.com/ironyard-static-data/FranklinCounty250.json";
    private static String baseUrl = "http://web1.mobile311.com/arcgis/rest/services/NorthCarolina/FranklinCounty/MapServer/3/query";
    private static String outFieldsUrl = "outFields=Saledt,Own1,Own2,Cityname,Statecode,Zip1,Zoning,Adrno,Adradd,Adrdir,Adrstr,Adrsuf,Adrsuf2,Addr1";
    //  time requested is Feb 1st -------------------------------check time here---------------------------------------
    private static String remainingUrl = "where=&text=%&objectIds=&time=&geometry=&geometryType=esriGeometryEnvelope" +
            "&inSR=&spatialRel=esriSpatialRelIntersects&relationParam=&&returnGeometry=false&returnTrueCurves=fals" +
            "e&maxAllowableOffset=&geometryPrecision=&outSR=&returnIdsOnly=false&returnCountOnly=false&orderByFiel" +
            "ds=Saledt DESC&groupByFieldsForStatistics=&outStatistics=&returnZ=false&returnM=false&gdbVersion=&ret" +
            "urnDistinctValues=false&resultOffset=&resultRecordCount=250&f=pjson&time=1485962060000";
    private static String fullUrl = baseUrl + "?" + outFieldsUrl + "&" + remainingUrl;

    public List<FranklinPropertyRecords> getRecords() {
        List<FranklinPropertyRecords> records = new ArrayList<>();

        // Rest template call - two reasons to use a string - "invalid content type"
        // content type wasn't setting to json even when requested
        // could'nt figure out out how to map nested java objects
        RestTemplate restTemplate = new RestTemplate();
        String franklin = restTemplate.getForObject(testUrl, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            // returns an array of features that i could turn into a list for the data needed for front-end
            JsonNode node = mapper.readTree(franklin);
            // using this to loop over array of features
            Iterator<JsonNode> features = node.withArray("features").iterator();

            // Iterate over the features array
            // Read all the fields from each node in the iterator
            // Copy the fields to a new address object
            // Add the address object to a new list
            while (features.hasNext()) {
                JsonNode a = features.next();
                // getting the attributes field to get to the fields used to build the
                // date of sale, owners, address, zoning info that I'm sending back to front end.
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
                String fixZoning = getZoning(zoningCode);

                FranklinPropertyRecords info = new FranklinPropertyRecords();
                info.setName(insertOwners);
                info.setAddress(fullAddress + " " + cityName + " " + state + ", " + zipCode);
                info.setDateOfSale(saleDate);
                info.setZoning(fixZoning);

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
    private String getZoning(String zoning) {
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





// all the lovely outFields
// outFields=OBJECTID_1,Shape,OBJECTID,PRODNO,PID,PIN,FTR_CODE,RECN,COMPACRES,MAPACRES,PARID,Shape_STAr,Shape_STLe,ID,
// Taxyr,Parid_1,Class,Cur,Nbhd,Zoning,Idname,Acres,Altid,Aprland,Subdiv,Sublot,Obyval,Aprbldg,Taxdist,PlatBk,Page,
// Legal1,Field19,Field20,Field21,Field22,Field23,Field24,Field25,Field26,Addrtype,Own1,Own2,Adrno,Adradd,Adrdir,Adrstr,
// Adrsuf,Adrsuf2,Addr1,Cityname,Statecode,Addr2,Zip1,Tot22SUM,Field42,Field43,Field44,Book,Parid_12,Page_1,Saledt,
// Taxyr_1,ID_1,Price,Parid_1_13,Cur_1,Taxyr_12,Effyr,Grade,Sfla,Cdpct,Cdu,Class_1,ID_12,Yrblt,Shape_Length,Shape_Area&

//         invalid content type
//         JsonFactory factory = new JsonFactory();

//         System.out.println(restTemplate.getMessageConverters());
//         HttpHeaders headers = new HttpHeaders();
//         headers.setContentType(MediaType.APPLICATION_JSON);


// next county could be Harnett - their api is currently up and running again - this link is for Tax Sales

// http://gis.harnett.org/arcgis/rest/services/Tax/TaxSales/MapServer/2/query?where=&text=%25&objectIds=&time=&geometry
// =&geometryType=esriGeometryEnvelope&inSR=&spatialRel=esriSpatialRelIntersects&relationParam=&outFields=&
// returnGeometry=true&returnTrueCurves=false&maxAllowableOffset=&geometryPrecision=&outSR=&returnIdsOnly=
// false&returnCountOnly=false&orderByFields=&groupByFieldsForStatistics=&outStatistics=&returnZ=false&returnM=
// false&gdbVersion=&returnDistinctValues=false&resultOffset=&resultRecordCount=&f=html

// Harnett - Tax Parcel data

// http://gis.harnett.org/arcgis/rest/services/Tax/TaxParcels/MapServer/1/query?where=&text=%25&objectIds=&time=&
// geometry=&geometryType=esriGeometryEnvelope&inSR=&spatialRel=esriSpatialRelIntersects&relationParam=&outFields=
// &returnGeometry=true&returnTrueCurves=false&maxAllowableOffset=&geometryPrecision=&outSR=&returnIdsOnly=false&return
// CountOnly=false&orderByFields=&groupByFieldsForStatistics=&outStatistics=&returnZ=false&returnM=false&gdbVersion=
// &returnDistinctValues=false&resultOffset=&resultRecordCount=&f=html

// Lee County - Tax Parcel data

// http://web1.mobile311.com/arcgis/rest/services/NorthCarolina/LeeCounty/MapServer/1/query?where=&text=%25&objectIds=
// &time=&geometry=&geometryType=esriGeometryEnvelope&inSR=&spatialRel=esriSpatialRelIntersects&relationParam=&outFields
// =&returnGeometry=true&returnTrueCurves=false&maxAllowableOffset=&geometryPrecision=&outSR=&returnIdsOnly=false&return
// CountOnly=false&orderByFields=&groupByFieldsForStatistics=&outStatistics=&returnZ=false&returnM=false&gdbVersion=
// &returnDistinctValues=false&resultOffset=&resultRecordCount=&f=html

// Franklin County - Tax Parcel data

// http://web1.mobile311.com/arcgis/rest/services/NorthCarolina/FranklinCounty/MapServer/3/query?
// outFields=Saledt,Own1,Own2,Cityname,Statecode,Zip1,Zoning,Adrno,Adradd,Adrdir,Adrstr,Adrsuf,Adrsuf2,Addr1&
// where=&text=%&objectIds=&time=&geometry=&geometryType=esriGeometryEnvelope&inSR=&spatialRel=esriSpatialRelIntersects&
// relationParam=&&returnGeometry=false&returnTrueCurves=false&maxAllowableOffset=&geometryPrecision=&outSR=&
// returnIdsOnly=false&returnCountOnly=false&orderByFields=Saledt DESC&groupByFieldsForStatistics=&outStatistics=&
// returnZ=false&returnM=false&gdbVersion=&returnDistinctValues=false&resultOffset=&resultRecordCount=250&f=pjson&time=
// 1485962060000







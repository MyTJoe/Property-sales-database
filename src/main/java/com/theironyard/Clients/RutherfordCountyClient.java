package com.theironyard.Clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.entities.RutherfordPropertyRecords;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RutherfordCountyClient {
    private String testUrl = "https://s3-us-west-2.amazonaws.com/ironyard-static-data/rutherford-30.json";
    private String url = "http://web1.mobile311.com/arcgis/rest/services/NorthCarolina/RutherfordCounty/MapServer" +
            "/46/query?where=&text=%&objectIds=&time=&geometry=&geometryType=esriGeometryEnvelope&inSR=&spatialRe" +
            "l=esriSpatialRelIntersects&relationParam=&outFields=Property_Owner,Land_Sale_Date,Land_Class,Physica" +
            "l_Address,Physical_Address_City,Physical_Address_State,Physical_Address_Zip,Total_Land_Value_Assesse" +
            "d,Total_Building_Value_Assessed,Total_Property_Value,Owner_Mailing_Address_1,Owner_Mailing_Address_C" +
            "ity,Owner_Mailing_Address_State,Owner_Mailing_Address_Zip,Sale_Price&returnGeometry=false&returnTrue" +
            "Curves=false&maxAllowableOffset=&geometryPrecision=&outSR=&returnIdsOnly=false&returnCountOnly=false" +
            "&orderByFields=Land_Sale_Date DESC&groupByFieldsForStatistics=&outStatistics=&returnZ=false&returnM=" +
            "false&gdbVersion=&returnDistinctValues=false&resultOffset=&resultRecordCount=30&f=pjson";

    public List<RutherfordPropertyRecords> getRecords() {
        List<RutherfordPropertyRecords> records = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String rutherford = restTemplate.getForObject(testUrl, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(rutherford);
            Iterator<JsonNode> features = node.withArray("features").iterator();

            while (features.hasNext()) {
                JsonNode a = features.next();
                String owner = a.get("attributes").get("Property_Owner").asText();
                String physicalAddress = a.get("attributes").get("Physical_Address").asText();
                String physicalAddressCity = a.get("attributes").get("Physical_Address_City").asText();
                String physicalAddressState = a.get("attributes").get("Physical_Address_State").asText();
                String physicalAddressZip = a.get("attributes").get("Physical_Address_Zip").asText();
                String ownerMailingAddress1 = a.get("attributes").get("Owner_Mailing_Address_1").asText();
                String ownerMailingAddressCity = a.get("attributes").get("Owner_Mailing_Address_City").asText();
                String ownerMailingAddressState = a.get("attributes").get("Owner_Mailing_Address_State").asText();
                String ownerMailingAddressZip = a.get("attributes").get("Owner_Mailing_Address_Zip").asText();
                String salePrice = a.get("attributes").get("Sale_Price").asText();
                String totalValue = a.get("attributes").get("Total_Property_Value").asText();
                String zoning = a.get("attributes").get("Land_Class").asText();
                String saleDate = a.get("attributes").get("Land_Sale_Date").asText();
                String landValue = a.get("attributes").get("Total_Land_Value_Assessed").asText();
                String buildingValue = a.get("attributes").get("Total_Building_Value_Assessed").asText();

                RutherfordPropertyRecords info = new RutherfordPropertyRecords();
                info.setOwner(owner);
                String createPropAddress = buildPropertyAddress(
                        physicalAddress,
                        physicalAddressCity,
                        physicalAddressState,
                        physicalAddressZip);
                info.setPropertyAddress(createPropAddress);

                String createMailAddress = buildMailingAddress(
                        ownerMailingAddress1,
                        ownerMailingAddressCity,
                        ownerMailingAddressState,
                        ownerMailingAddressZip);
                info.setMailingAddress(createMailAddress);

                info.setSalePrice(salePrice);
                info.setTotalValue(totalValue);
                info.setBuildingValue(buildingValue);
                info.setLandValue(landValue);
                info.setZoning(zoning);
                info.setSaleDate(saleDate);
                records.add(info);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }

    private String buildPropertyAddress(String address, String city, String state, String zip) {
        StringBuilder sb = new StringBuilder();
        if (!address.isEmpty()) {
            sb.append(address);
        }
        if (!city.isEmpty()) {
            sb.append(" ").append(city);
        }
        if (!state.isEmpty()) {
            sb.append(" ").append(state);
        }
        if (!zip.isEmpty()) {
            sb.append(", ").append(zip);
        }
        return sb.toString().trim();
    }

    private String buildMailingAddress(String mAddress, String mCity, String mState, String mZip) {
        StringBuilder sb = new StringBuilder();
        if (!mAddress.isEmpty()) {
            sb.append(mAddress);
        }
        if (!mCity.isEmpty()) {
            sb.append(" ").append(mCity);
        }
        if (!mState.isEmpty()) {
            sb.append(" ").append(mState);
        }
        if (!mZip.isEmpty()) {
            sb.append(", ").append(mZip);
        }
        return sb.toString().trim();
    }
}



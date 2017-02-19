package com.theironyard.Clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.entities.RutherfordPropertyRecords;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RutherfordCountyClient {
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
        String rutherford = restTemplate.getForObject(url, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(rutherford);
            Iterator<JsonNode> features = node.withArray("features").iterator();

            System.out.println();

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
                String buildingValue = a.get("attributes").get("Total_Buidling_Value_Assessed").asText();

                RutherfordPropertyRecords info = new RutherfordPropertyRecords();
                info.setOwner(owner);
                info.setSalePrice(salePrice);
                info.setTotalValue(totalValue);
                info.setBuildingValue(buildingValue);
                info.setLandValue(landValue);
                info.setZoning(zoning);
                info.setSaleDate(saleDate);





                //records.add();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }
}

//         Total_Land_Value_Assessed,
//         Total_Building_Value_Assessed,


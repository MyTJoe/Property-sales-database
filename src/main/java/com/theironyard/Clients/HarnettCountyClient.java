package com.theironyard.Clients;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.entities.HarnettPropertyRecords;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HarnettCountyClient {
    //            Angier, Coats, Lilling, BunnL, Cameron, Dunn, Erwin
    //parzipcode = (27501, 27521, 27546, 28323, 28326, 28334, 28339)


    private String textUrl = "";
    private String url = "http://gis.harnett.org/arcgis/rest/services/Tax/TaxParcels/MapServer/1/query?" +
            "where=parzipcode+IN+%2827501%2C+27521%2C+27546%2C+28323%2C+28326%2C+28334%2C+28339%29&text" +
            "=%25&objectIds=&time=&geometry=&geometryType=esriGeometryEnvelope&inSR=&spatialRel=esriSpa" +
            "tialRelIntersects&relationParam=&outFields=*&returnGeometry=true&returnTrueCurves=false&ma" +
            "xAllowableOffset=&geometryPrecision=&outSR=&returnIdsOnly=false&returnCountOnly=false&orde" +
            "rByFields=&groupByFieldsForStatistics=&outStatistics=&returnZ=false&returnM=false&gdbVersi" +
            "on=&returnDistinctValues=false&resultOffset=&resultRecordCount=&f=pjson";

    public List<HarnettPropertyRecords> getRecords() {
        List<HarnettPropertyRecords> records = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String harnett = restTemplate.getForObject(url, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(harnett);
            Iterator<JsonNode> features = node.withArray("features").iterator();

            while (features.hasNext()) {
                JsonNode a = features.next();

                String owner1 = a.get("attributes").get("Owner1").asText();
                String owner2 = a.get("attributes").get("Owner2").asText();
                String physicalAddress = a.get("attributes").get("PhysicalAddress").asText();
                String parZipCode = a.get("attributes").get("ParZipCode").asText();
                String parCity = a.get("attributes").get("ParCity").asText();
                String mailingAddress = a.get("attributes").get("MailingAddress").asText();
                String longitude = a.get("attributes").get("Longitude").asText();
                String latitude = a.get("attributes").get("Latitude").asText();
                String saleMoth = a.get("attributes").get("SaleMonth").asText();
                String saleYear = a.get("attributes").get("SaleYear").asText();
                String salePrice = a.get("attributes").get("SalePrice").asText();
                String propertyValue = a.get("attributes").get("TotalMarketValue").asText();
                String landeValue = a.get("attributes").get("ParcelLandValue").asText();
                String buildingValue = a.get("attributes").get("ParcelBuildingValue").asText();
                String actualYearBuilt = a.get("attributes").get("ActualYearBuilt").asText();
                String zoning = a.get("attributes").get("Zoning").asText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }
}
//    private String propertyAddress; //PhysicalAddress + ParZipCode + ParCity (figure out state)



//    private int landValue; //ParcelLandValue
//    private int buildingValue; // ParcelBuildingValue
//    private int actualYearBuilt; // ActualYearBuilt
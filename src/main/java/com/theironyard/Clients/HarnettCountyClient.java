package com.theironyard.Clients;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.entities.HarnettPropertyRecords;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HarnettCountyClient {

    private String testUrl = "";
    private String url = "http://gis.harnett.org/arcgis/rest/services/Tax/TaxParcels/MapServer/1/query?where=" +
            "parzipcode IN (27501,27521,27546,28323,28334,28339)&text=%&objectIds=&time=&geometry=&geometryTy" +
            "pe=esriGeometryEnvelope&inSR=&spatialRel=esriSpatialRelIntersects&relationParam=&outFields=Owner" +
            "1,Owner2,PhysicalAddress,ParCity,ParZipCode,MailingAddress,Longitude,Latitude,SaleMonth,SaleYear" +
            ",SalePrice,TotalMarketValue,ParcelLandValue,ParcelBuildingValue,ParcelObxfValue,ActualYearBuilt," +
            "Zoning&returnGeometry=false&returnTrueCurves=false&maxAllowableOffset=&geometryPrecision=&outSR=" +
            "&returnIdsOnly=false&returnCountOnly=false&orderByFields=SaleYear DESC,SaleMonth DESC&groupByFie" +
            "ldsForStatistics=&outStatistics=&returnZ=false&returnM=false&gdbVersion=&returnDistinctValues=fa" +
            "lse&resultOffset=&resultRecordCount=500&f=pjson";

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
                String landValue = a.get("attributes").get("ParcelLandValue").asText();
                String buildingValue = a.get("attributes").get("ParcelBuildingValue").asText();
                String parcelObxfValue = a.get("attributes").get("ParcelObxfValue").asText();
                String zoning = a.get("attributes").get("Zoning").asText();

                HarnettPropertyRecords info = new HarnettPropertyRecords();
                String zoningName = convertZoningCode(zoning);

                info.setZoning(zoning);
                info.setOwner(owner1);
                info.setPropertyAddress(physicalAddress + " " + parCity + ", NC " + parZipCode);
                info.setMailingAddress(mailingAddress);
                info.setSaleMonth(saleMoth);
                info.setSaleYear(saleYear);
                info.setSalePrice(salePrice);
                info.setPropertyValue(propertyValue);
                info.setLandValue(landValue);
                info.setBuildingValue(buildingValue);
                info.setAdditionalValue(parcelObxfValue);
                info.setLongitude(longitude);
                info.setLatitude(latitude);

                records.add(info);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }

    private String convertZoningCode(String zoningCode) {
        if (zoningCode.toUpperCase().startsWith("A")) {
            return "Agricultural";
        }
        if (zoningCode.toUpperCase().startsWith("C")) {
            return "Commercial";
        }
        if (zoningCode.toUpperCase().startsWith("R")) {
            return "Residential";
        }
        return zoningCode;
    }

}




//    private String propertyAddress; //PhysicalAddress + ParZipCode + ParCity (figure out state)
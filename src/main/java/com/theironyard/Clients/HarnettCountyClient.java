package com.theironyard.Clients;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.entities.HarnettPropertyRecords;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HarnettCountyClient {
    private String testUrl30 = "https://s3-us-west-2.amazonaws.com/ironyard-static-data/Harnett-30.json";
    private String testUrl100 = "https://s3-us-west-2.amazonaws.com/ironyard-static-data/Harnett-100.json";
    private String url = "http://gis.harnett.org/arcgis/rest/services/Tax/TaxParcels/MapServer/1/query?where=" +
            "parzipcode IN (27501,27521,27546,28323,28334,28339)&text=%&objectIds=&time=&geometry=&geometryTy" +
            "pe=esriGeometryEnvelope&inSR=&spatialRel=esriSpatialRelIntersects&relationParam=&outFields=OBJEC" +
            "TID,ParcelID,Owner1,PhysicalAddress,ParCity,ParZipCode,MailingAddress,Longitude,Latitude,SaleMon" +
            "th,SaleYear,SalePrice,TotalMarketValue,ParcelLandValue,ParcelBuildingValue,ParcelObxfValue,Actua" +
            "lYearBuilt,Zoning&returnGeometry=false&returnTrueCurves=false&maxAllowableOffset=&geometryPrecis" +
            "ion=&outSR=&returnIdsOnly=false&returnCountOnly=false&orderByFields=SaleYear DESC,SaleMonth DESC" +
            "&groupByFieldsForStatistics=&outStatistics=&returnZ=false&returnM=false&gdbVersion=&returnDistin" +
            "ctValues=false&resultOffset=&resultRecordCount=30&f=pjson";

    public List<HarnettPropertyRecords> getRecords() {
        List<HarnettPropertyRecords> records = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String harnett = restTemplate.getForObject(testUrl30, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(harnett);
            Iterator<JsonNode> features = node.withArray("features").iterator();

            while (features.hasNext()) {
                JsonNode a = features.next();

                String parcelID = a.get("attributes").get("ParcelID").asText();
                String owner1 = a.get("attributes").get("Owner1").asText();
                String physicalAddress = a.get("attributes").get("PhysicalAddress").asText();
                String parZipCode = a.get("attributes").get("ParZipCode").asText();
                String parCity = a.get("attributes").get("ParCity").asText();
                String mailingAddress = a.get("attributes").get("MailingAddress").asText();
                String longitude = a.get("attributes").get("Longitude").asText();
                String latitude = a.get("attributes").get("Latitude").asText();
                String saleMonth = a.get("attributes").get("SaleMonth").asText();
                String saleYear = a.get("attributes").get("SaleYear").asText();
                String salePrice = a.get("attributes").get("SalePrice").asText();
                String totalValue = a.get("attributes").get("TotalMarketValue").asText();
                String landValue = a.get("attributes").get("ParcelLandValue").asText();
                String buildingValue = a.get("attributes").get("ParcelBuildingValue").asText();
                String parcelObxfValue = a.get("attributes").get("ParcelObxfValue").asText();
                String zoning = a.get("attributes").get("Zoning").asText();

                HarnettPropertyRecords info = new HarnettPropertyRecords();

                info.setPropertyId(parcelID);
                info.setOwner(" " + owner1);
                info.setMailingAddress(mailingAddress);
                info.setSalePrice(salePrice);
                info.setTotalValue(totalValue);
                info.setLandValue(landValue);
                info.setBuildingValue(buildingValue);
                info.setAdditionalValue(parcelObxfValue);
                info.setLongitude(longitude);
                info.setLatitude(latitude);
                info.setZoning(zoning);
                info.setPropertyAddress(physicalAddress);
                info.setZip(parZipCode);
                info.setCity(parCity);

                String createDate = buildSaleDate(saleMonth,saleYear);
                info.setSaleDate(createDate);
                
                records.add(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }
    private String buildSaleDate(String month, String year) {
        StringBuilder sb = new StringBuilder();

        if (!StringUtils.isEmpty(month)) {
            sb.append(month).append("-");
        }
        if (!StringUtils.isEmpty(year)) {
            sb.append(year);
        }
        return sb.toString().trim();
    }

}




//    private String propertyAddress; //PhysicalAddress + ParZipCode + ParCity (figure out state)

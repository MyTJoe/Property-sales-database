package com.theironyard.Clients;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.entities.HarnettPropertyRecords;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HarnettCountyClient {
private String url = "http://gis.harnett.org/arcgis/rest/services/Tax/TaxParcels/MapServer/1/query?where=&text=%25&" +
        "objectIds=&time=&geometry=&geometryType=esriGeometryEnvelope&inSR=&spatialRel=esriSpatialRelIntersects&" +
        "relationParam=&outFields=*&returnGeometry=true&returnTrueCurves=false&maxAllowableOffset=&geometryPrecision=" +
        "&outSR=&returnIdsOnly=false&returnCountOnly=false&orderByFields=&groupByFieldsForStatistics=&outStatistics=&" +
        "returnZ=false&returnM=false&gdbVersion=&returnDistinctValues=false&resultOffset=&resultRecordCount=&f=pjson";

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

                String 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }
}

package com.theironyard.Clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.entities.LeePropertyRecords;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LeeCountyClient {
    private String testUrl30 = "https://s3-us-west-2.amazonaws.com/ironyard-static-data/Lee-30.json";
    private String testUrl50 = "https://s3-us-west-2.amazonaws.com/ironyard-static-data/Lee-50.json";
    private String testUrl100 = "https://s3-us-west-2.amazonaws.com/ironyard-static-data/Lee-100.json";
    private String url = "http://web1.mobile311.com/arcgis/rest/services/NorthCarolina/LeeCounty/MapServer/1/" +
            "query?where=&text=%&objectIds=&time=&geometry=&geometryType=esriGeometryEnvelope&inSR=&spatialRe" +
            "l=esriSpatialRelIntersects&relationParam=&outFields=*&returnGeometry=false&returnTrueCurves=fals" +
            "e&maxAllowableOffset=&geometryPrecision=&outSR=&returnIdsOnly=false&returnCountOnly=false&orderB" +
            "yFields=SaleDate DESC&groupByFieldsForStatistics=&outStatistics=&returnZ=false&returnM=false&gdb" +
            "Version=&returnDistinctValues=false&resultOffset=&resultRecordCount=30&f=pjson";

    public List<LeePropertyRecords> getRecords() {
        List<LeePropertyRecords> records = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String lee = restTemplate.getForObject(testUrl30, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(lee);
            Iterator<JsonNode> features = node.withArray("features").iterator();

            while (features.hasNext()) {
                JsonNode a = features.next();

                String owner1 = a.get("attributes").get("Owner1").asText();
                String propAddr = a.get("attributes").get("PropAddr").asText();
                String mailAdrno = a.get("attributes").get("MailADRADD").asText();
                String mailAdradd = a.get("attributes").get("MailADRADD").asText();
                String mailAdrdir = a.get("attributes").get("MailADRDIR").asText();
                String mailAdrstr = a.get("attributes").get("MailADRSTR").asText();
                String mailAdrsuf = a.get("attributes").get("MailADRSUF").asText();
                String mailCity = a.get("attributes").get("MailCity").asText();
                String mailState = a.get("attributes").get("MailState").asText();
                String mailZip = a.get("attributes").get("MailZip").asText();
                String aprLand = a.get("attributes").get("APRLAND").asText();
                String aprBldg = a.get("attributes").get("APRBLDG").asText();
                String aprTot = a.get("attributes").get("APRTOT").asText();
                String saleDate = a.get("attributes").get("Saledate").asText();
                String salePrice = a.get("attributes").get("sale_PRICE").asText();

                LeePropertyRecords info = new LeePropertyRecords();
                String newAddress = buildAddress(
                        mailAdrno,
                        mailAdradd,
                        mailAdrdir,
                        mailAdrstr,
                        mailAdrsuf);

                info.setOwner(" " + owner1);
                info.setPropertyAddress(propAddr);
                info.setMailingAddress(newAddress);
                info.setLandValue(aprLand);
                info.setBuildingValue(aprBldg);
                info.setTotalValue(aprTot);
                info.setSalePrice(salePrice);
                info.setSaleDate(saleDate);
                info.setCity(mailCity);
                info.setState(mailState);
                info.setZip(mailZip);

                // Lee County doesn't allow querying for specific zip codes from the API
                if (mailZip.equals("27330")) {
                    records.add(info);
                }
                if (mailZip.equals("27332")) {
                    records.add(info);
                }
                if (mailZip.equals("27505")) {
                    records.add(info);
                }
                records.add(info);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
    //Build address from different fields provided by the database
    private String buildAddress(String mailno, String mailadd, String maildir, String mailstr, String mailsuf) {
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isEmpty(mailno)) {
            sb.append(" ").append(mailno);
        }
        if (!StringUtils.isEmpty(mailadd)) {
            sb.append(" ").append(mailadd);
        }
        if (!StringUtils.isEmpty(maildir)) {
            sb.append(" ").append(maildir);
        }
        if (!StringUtils.isEmpty(mailstr)) {
            sb.append(" ").append(mailstr);
        }
        if (!StringUtils.isEmpty(mailsuf)) {
            sb.append(" ").append(mailsuf);
        }
        return sb.toString().trim();
    }
}

package com.theironyard.controllers;

import com.theironyard.entities.PropertyRecords;

import com.theironyard.integration.FranklinClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



import java.util.ArrayList;
import java.util.List;



@Controller
public class PropertyController {
    private static List<PropertyRecords> propertyRecords = new ArrayList<>();

    @CrossOrigin
    @ResponseBody
    @RequestMapping(path = "/address.json", method = RequestMethod.GET)
    public List<PropertyRecords> jsonHome() {
        List<PropertyRecords> results = new FranklinClient().getRecords();
        return results;
    }
}

//    @CrossOrigin
//    @PostConstruct
//    public void init() throws FileNotFoundException {
//
//        if (propertyRecordss.size() == 0) {
//            File f = new File("addresses.csv");
//            Scanner fileScanner = new Scanner(f);
//
//            while (fileScanner.hasNext()) {
//
//                String line = fileScanner.nextLine();
//                String[] columns = line.split(",");
//                PropertyRecords propertyRecords = new PropertyRecords(columns[0], (columns[1]), columns[2]);
//
//                propertyRecordss.add(propertyRecords);
//            }
//        }
//    }
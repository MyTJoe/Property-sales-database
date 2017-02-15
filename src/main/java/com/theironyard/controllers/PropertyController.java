package com.theironyard.controllers;

import com.theironyard.entities.FranklinPropertyRecords;

import com.theironyard.Clients.FranklinCountyClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;


@Controller
public class PropertyController {


    @CrossOrigin
    @ResponseBody
    @RequestMapping(path = "/address.json", method = RequestMethod.GET)
    public List<FranklinPropertyRecords> jsonHome() {
        List<FranklinPropertyRecords> results = new FranklinCountyClient().getRecords();
        return results;
    }
}



//  private static List<FranklinPropertyRecords> propertyRecords = new ArrayList<>();

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
//                FranklinPropertyRecords propertyRecords = new FranklinPropertyRecords(columns[0], (columns[1]), columns[2]);
//
//                propertyRecordss.add(propertyRecords);
//            }
//        }
//    }
package com.theironyard.controllers;

import com.theironyard.Clients.HarnettCountyClient;
import com.theironyard.Clients.LeeCountyClient;
import com.theironyard.entities.FranklinPropertyRecords;

import com.theironyard.Clients.FranklinCountyClient;
import com.theironyard.entities.HarnettPropertyRecords;
import com.theironyard.entities.LeePropertyRecords;
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
    @RequestMapping(path = "/harnett", method = RequestMethod.GET)
    public List<HarnettPropertyRecords> harnett() {
        List<HarnettPropertyRecords> results = new HarnettCountyClient().getRecords();
        return results;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(path = "/franklin", method = RequestMethod.GET)
    public List<FranklinPropertyRecords> franklin() {
        List<FranklinPropertyRecords> results = new FranklinCountyClient().getRecords();
        return results;
    }

//    @CrossOrigin
//    @ResponseBody
//    @RequestMapping(path = "/lee", method = RequestMethod.GET)
//        public List<LeePropertyRecords> lee() {
//        List<LeePropertyRecords> results = new LeeCountyClient().getRecords();
//        return results;
//    }


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
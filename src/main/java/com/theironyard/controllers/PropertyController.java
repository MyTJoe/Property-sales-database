package com.theironyard.controllers;

import com.theironyard.Clients.HarnettCountyClient;
import com.theironyard.Clients.LeeCountyClient;
import com.theironyard.Clients.RutherfordCountyClient;
import com.theironyard.entities.FranklinPropertyRecords;

import com.theironyard.Clients.FranklinCountyClient;
import com.theironyard.entities.HarnettPropertyRecords;
import com.theironyard.entities.LeePropertyRecords;
import com.theironyard.entities.RutherfordPropertyRecords;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;


@Controller
public class PropertyController {

    @CrossOrigin
    @RequestMapping(path = "/", method = RequestMethod.GET)
        public String home() {
            return "/index.html";
    }

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

    @CrossOrigin
    @ResponseBody
    @RequestMapping(path = "/lee", method = RequestMethod.GET)
    public List<LeePropertyRecords> lee() {
        List<LeePropertyRecords> results = new LeeCountyClient().getRecords();
        return results;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(path = "/rutherford", method = RequestMethod.GET)
    public List<RutherfordPropertyRecords> rutherford() {
        List<RutherfordPropertyRecords> results = new RutherfordCountyClient().getRecords();
        return results;
    }
}
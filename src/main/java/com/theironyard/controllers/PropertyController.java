package com.theironyard.controllers;

import com.theironyard.entities.Address;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Controller
public class PropertyController {
    private static List<Address> addresses = new ArrayList<>();

    @CrossOrigin
    @PostConstruct
    public void init() throws FileNotFoundException {

        if (addresses.size() == 0) {
            File f = new File("addresses.csv");
            Scanner fileScanner = new Scanner(f);

            while (fileScanner.hasNext()) {

                String line = fileScanner.nextLine();
                String[] columns = line.split(",");
                Address address = new Address(columns[0], Integer.valueOf(columns[1]), columns[2]);

                addresses.add(address);
            }
        }
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(path = "/address.json", method = RequestMethod.GET)
    public List<Address> jsonHome() {
        return addresses;
    }
}

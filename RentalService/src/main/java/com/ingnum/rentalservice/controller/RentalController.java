package com.ingnum.rentalservice.controller;

import com.ingnum.rentalservice.service.NameServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentalController {

    @Autowired
    private NameServiceClient nameServiceClient;

    @GetMapping("/customer/{name}")
    public String getCustomer(@PathVariable String name) {
        NameServiceClient.CustomerInfo customerInfo = nameServiceClient.getCustomerInfo(name);
        return "Client: " + customerInfo.getName();
    }
}


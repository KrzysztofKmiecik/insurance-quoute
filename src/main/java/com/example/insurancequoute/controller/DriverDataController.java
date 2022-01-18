package com.example.insurancequoute.controller;

import com.example.insurancequoute.model.DriverData;
import com.example.insurancequoute.service.DriverDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class DriverDataController {

    private final DriverDataService driverDataService;

    public DriverDataController(DriverDataService driverDataService) {
        this.driverDataService = driverDataService;
    }

    @GetMapping("/drivers/getAll")
    public List<DriverData> getAllDrivers(){
        return driverDataService.getAllDrivers();
    }
}

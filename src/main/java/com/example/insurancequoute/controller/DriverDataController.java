package com.example.insurancequoute.controller;

import com.example.insurancequoute.model.DriverData;
import com.example.insurancequoute.service.DriverDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class DriverDataController {

    private final DriverDataService driverDataService;

    public DriverDataController(DriverDataService driverDataService) {
        this.driverDataService = driverDataService;
    }

    @GetMapping("/driver/getAll")
    public List<DriverData> getAllDrivers() {
        return driverDataService.getAllDrivers();
    }

    @PostMapping("/driver")
    public DriverData createDriver(@RequestBody DriverData driverData) {
        return driverDataService.createDriver(driverData);
    }

    @DeleteMapping("/driver/{id}")
    public void deleteDriverById(@PathVariable("id") Integer id) {
        driverDataService.deleteDriverById(id);
    }

    @GetMapping("/driver/get/{id}")
    public DriverData getDriverById(@PathVariable("id") int id) {
        return driverDataService.getDriverById(id);
    }

    @PutMapping("/driver/updatePhoneNumber/{id}/{phoneNumber}")
    public DriverData updateDriversPhoneById(@PathVariable("id") int id,
                                             @PathVariable("phoneNumber") int phoneNumber) {
        return driverDataService.updateDriversPhoneById(id, phoneNumber);
    }

    @PutMapping("/driver/update/{id}")
    public DriverData updateDriverById(@PathVariable("id") int id,
                                       @PathVariable DriverData driverData) {
        return driverDataService.updateDriverById(id);
    }
}

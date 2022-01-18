package com.example.insurancequoute.controller;

import com.example.insurancequoute.model.DriverData;
import com.example.insurancequoute.service.DriverDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
    public ResponseEntity<Void> deleteDriverById(@PathParam("id") int id) {
        driverDataService.deleteDriverById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/driver/get/{id}")
    public DriverData getDriverById(@PathParam("id") int id) {
        return driverDataService.getDriverById(id);
    }

    @PutMapping("/driver/updatePhoneNumber/{id}/{phoneNumber}")
    public DriverData updateDriversPhoneById(@PathParam("id") int id,
                                             @PathParam("phoneNumber") int phoneNumber) {
        return driverDataService.updateDriversPhoneById(id, phoneNumber);
    }

    @PutMapping("/driver/update/{id}")
    public DriverData updateDriverById(@PathParam("id") int id,
                                       @RequestParam DriverData driverData) {
        return driverDataService.updateDriverById(id);
    }
}

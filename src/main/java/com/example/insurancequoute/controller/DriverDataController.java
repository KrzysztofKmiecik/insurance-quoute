package com.example.insurancequoute.controller;

import com.example.insurancequoute.model.DriverData;
import com.example.insurancequoute.service.DriverDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DriverDataController {

    private final DriverDataService driverDataService;


    @GetMapping
    public List<DriverData> getAll() {
        return driverDataService.getAll();
    }

}

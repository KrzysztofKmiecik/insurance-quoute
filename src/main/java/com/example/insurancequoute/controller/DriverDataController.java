package com.example.insurancequoute.controller;

import com.example.insurancequoute.model.DriverData;
import com.example.insurancequoute.service.DriverDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers")
public class DriverDataController {

    private final DriverDataService driverDataService;


    @GetMapping
    public List<DriverData> getAll() {
        return driverDataService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverData> getById(@PathVariable("id") Long id) {
        return driverDataService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DriverData> addDriver(@RequestBody DriverData newDriverData) {
        driverDataService.addDriver(newDriverData);
        return new ResponseEntity<DriverData>(newDriverData, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeDriver(@PathVariable("id") Long id){
      driverDataService.removeDriver(id);

    }


}

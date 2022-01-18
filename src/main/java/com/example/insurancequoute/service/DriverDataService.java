package com.example.insurancequoute.service;

import com.example.insurancequoute.model.DriverData;
import com.example.insurancequoute.repository.DriverDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverDataService {

    private final DriverDataRepository driverDataRepository;

    public List<DriverData> getAll() {
       return driverDataRepository.findAll();
    }
}

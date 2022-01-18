package com.example.insurancequoute.service;

import com.example.insurancequoute.model.DriverData;
import com.example.insurancequoute.repository.DriverDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverDataService {

    private final DriverDataRepository driverDataRepository;

    public List<DriverData> getAll() {
        return driverDataRepository.findAll();
    }

    public Optional<DriverData> getById(Long id) {
        return driverDataRepository.findById(id);
    }

    public DriverData addDriver(DriverData driver3) {
        return driver3;
    }


    public void removeDriver(Long id) {
        driverDataRepository.delete(driverDataRepository.getById(id));
    }
}

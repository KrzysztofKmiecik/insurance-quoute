package com.example.insurancequoute.repository;

import com.example.insurancequoute.model.DriverData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DriverDataRepositoryTest {

    @Autowired
    private DriverDataRepository driverDataRepository;

    @Test
    public void shouldGetAllDrivers() {
        DriverData newDriver1 = DriverData.builder()
                .id(123L)
                .build();
        driverDataRepository.save(newDriver1);
        DriverData newDriver2 = DriverData.builder()
                .id(456L)
                .build();
        driverDataRepository.save(newDriver2);

        List<DriverData> foundDrivers = driverDataRepository.findAll();

        assertFalse(foundDrivers.isEmpty());
        assertEquals(2, foundDrivers.size());
    }

    @Test
    public void shouldGetAllDriversNoRecords() {
        fail();
    }

    @Test
    public void shouldSaveNewDriver() {
        int startNumberOfDrivers = driverDataRepository.findAll().size();
        DriverData newDriver = DriverData.builder()
                .id(123L)
                .build();

        driverDataRepository.save(newDriver);
        List<DriverData> foundDrivers = driverDataRepository.findAll();

        assertFalse(foundDrivers.isEmpty());
        assertEquals(startNumberOfDrivers+1, foundDrivers.size());
    }

    @Test
    public void shouldDeleteDriverById() {
        fail();
    }

    @Test
    public void shouldDeleteDriverByIdMissingDriver() {
        fail();
    }

    @Test
    public void shouldGetDriverById() {
        fail();
    }

    @Test
    public void shouldGetDriverByIdMissingDriver() {
        fail();
    }

    @Test
    public void shouldUpdatePhoneById() {
        fail();
    }

    @Test
    public void shouldUpdatePhoneByIdMissingDriver() {
        fail();
    }

    @Test
    public void shouldUpdateDriverById() {
        fail();
    }

    @Test
    public void shouldUpdateDriverByIdMissingDriver() {
        fail();
    }

}
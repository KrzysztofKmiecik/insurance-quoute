package com.example.insurancequoute.repository;

import com.example.insurancequoute.model.DriverData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DriverDataRepositoryTest {

    public static final long ID_1 = 123L;
    public static final long ID_2 = 456L;

    @Autowired
    private DriverDataRepository driverDataRepository;

    @Test
    @DisplayName("Should return all two drivers "+ ID_1 + " and " + ID_2)
    public void shouldGetAllDrivers() {

        insertExampleDrivers(ID_1, ID_2);

        List<DriverData> foundDrivers = driverDataRepository.findAll();

        assertFalse(foundDrivers.isEmpty());
        assertEquals(2, foundDrivers.size());
    }

    @Test
    @DisplayName("Should return empty list when no driver found by getAll")
    public void shouldGetAllDriversNoRecords() {
        List<DriverData> foundDrivers = driverDataRepository.findAll();

        assertTrue(foundDrivers.isEmpty());
        assertEquals(0, foundDrivers.size());
    }

    @Test
    @DisplayName("Should save new driver "+ ID_1)
    public void shouldSaveNewDriver() {
        int startNumberOfDrivers = driverDataRepository.findAll().size();
        DriverData newDriver = DriverData.builder()
                .id(ID_1)
                .build();

        driverDataRepository.save(newDriver);
        List<DriverData> foundDrivers = driverDataRepository.findAll();

        assertFalse(foundDrivers.isEmpty());
        assertEquals(startNumberOfDrivers+1, foundDrivers.size());
    }

    @Test
    @DisplayName("Should delete driver "+ ID_1)
    public void shouldDeleteDriverById() {
        insertExampleDrivers(ID_1, ID_2);

        assertEquals(2, driverDataRepository.findAll().size());

        driverDataRepository.deleteById(ID_1);

        assertEquals(1, driverDataRepository.findAll().size());
        assertEquals(ID_2, driverDataRepository.findAll().get(0).getId());
    }

    @Test
    @DisplayName("Should throw exception when no driver found by id")
    public void shouldThrowExceptionWhenDeleteDriverByIdMissingDriver() {
        insertExampleDrivers(ID_1, ID_2);

        assertThrows(
                EmptyResultDataAccessException.class,
                () -> driverDataRepository.deleteById(1L)
        );
    }

    @Test
    @DisplayName("Should find driver by id "+ ID_2)
    public void shouldGetDriverById() {
        insertExampleDrivers(ID_1, ID_2);

        DriverData foundDriver = driverDataRepository.getById(ID_2);

        assertEquals(ID_2, foundDriver.getId());
    }

    @Test
    public void shouldGetDriverByIdMissingDriver() {
        fail();
    }

    @Test
    @DisplayName("Should update phone number for "+ ID_1)
    public void shouldUpdatePhoneById() {
        insertExampleDrivers(ID_1, ID_2);

        DriverData foundDriver = driverDataRepository.getById(ID_1);

        assertEquals("123456789", foundDriver.getTelephoneNumber());

        //driverDataRepository.updatePhoneById(ID_1, "42763784587354");

        foundDriver = driverDataRepository.getById(ID_1);

        assertEquals("42763784587354", foundDriver.getTelephoneNumber());
    }

    @Test
    public void shouldUpdatePhoneByIdMissingDriver() {
        fail();
    }

    @Test
    @DisplayName("Should update driver "+ ID_1)
    public void shouldUpdateDriverById() {
        insertExampleDrivers(ID_1, ID_2);

        DriverData foundDriver = driverDataRepository.getById(ID_1);

        assertEquals("Adam", foundDriver.getOwnerFirstName());

        DriverData driverToUpdate = DriverData.builder()
                .id(ID_1)
                .ownerFirstName("Bartek")
                .build();

        //driverDataRepository.updateDriverById(ID_1, driverToUpdate);
        driverDataRepository.save(driverToUpdate);
        foundDriver = driverDataRepository.getById(ID_1);

        assertEquals("Bartek", foundDriver.getOwnerFirstName());
    }

    @Test
    public void shouldUpdateDriverByIdMissingDriver() {
        fail();
    }

    private void insertExampleDrivers(long ID1, long ID2) {
        DriverData newDriver1 = DriverData.builder()
                .id(ID1)
                .telephoneNumber("123456789")
                .ownerFirstName("Adam")
                .build();
        driverDataRepository.save(newDriver1);
        DriverData newDriver2 = DriverData.builder()
                .id(ID2)
                .build();
        driverDataRepository.save(newDriver2);
    }
}
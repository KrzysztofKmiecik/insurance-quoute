package com.example.insurancequoute.repository;

import com.example.insurancequoute.model.DriverData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDateTime;
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

        insertExampleDriver(ID_1);
        insertExampleDriver(ID_2);

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
        insertExampleDriver(ID_1);
        insertExampleDriver(ID_2);

        assertEquals(2, driverDataRepository.findAll().size());

        driverDataRepository.deleteById(ID_1);

        assertEquals(1, driverDataRepository.findAll().size());
        assertEquals(ID_2, driverDataRepository.findAll().get(0).getId());
    }

    @Test
    @DisplayName("Should throw exception when no driver found by id")
    public void shouldThrowExceptionWhenDeleteDriverByIdMissingDriver() {
        insertExampleDriver(ID_1);
        insertExampleDriver(ID_2);

        assertThrows(
                EmptyResultDataAccessException.class,
                () -> driverDataRepository.deleteById(1L)
        );
    }

    @Test
    @DisplayName("Should find driver by id "+ ID_2)
    public void shouldGetDriverById() {
        insertExampleDriver(ID_1);
        insertExampleDriver(ID_2);

        DriverData foundDriver = driverDataRepository.getById(ID_2);

        assertEquals(ID_2, foundDriver.getId());
    }

    @Test
    @DisplayName("Should update driver "+ ID_1)
    public void shouldUpdateDriverById() {
        String newName = "Bartosz";
        insertExampleDriver(ID_1);
        insertExampleDriver(ID_2);

        DriverData foundDriver = driverDataRepository.getById(ID_1);

        assertEquals("Adam", foundDriver.getOwnerFirstName());

        foundDriver.setOwnerFirstName(newName);

        driverDataRepository.save(foundDriver);
        foundDriver = driverDataRepository.getById(ID_1);

        assertEquals(newName, foundDriver.getOwnerFirstName());
    }

    private void insertExampleDriver(long id) {
        DriverData newDriver1 = DriverData.builder()
                .id(id)
                .prefix("pan")
                .ownerFirstName("Adam")
                .ownerLastName("Adamski")
                .telephoneNumber("123456789")
                .addressLine1("ul. Adamska")
                .addressLine2("1/2")
                .city("Adamowo")
                .postCode("00-000")
                .vehicleType("Cabriolet")
                .engineSize(1000)
                .additionalDrivers(1)
                .commercialPurpose(true)
                .usedOutsideTheRegisteredState(true)
                .currentValue(1234567)
                .firstRegisteredDate(LocalDateTime.MIN)
                .premium(100)
                .build();
        driverDataRepository.save(newDriver1);
    }
}
package com.example.insurancequoute.repository;

import com.example.insurancequoute.model.DriverData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.Objects.isNull;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DriverDataRepositoryTest {

    @Autowired
    private DriverDataRepository driverDataRepository;

    @Test
    public void shouldGetAllDrivers() {
        final long ID1 = 123L;
        final long ID2 = 456L;

        insertExampleDrivers(ID1, ID2);

        List<DriverData> foundDrivers = driverDataRepository.findAll();

        assertFalse(foundDrivers.isEmpty());
        assertEquals(2, foundDrivers.size());
    }

    @Test
    public void shouldGetAllDriversNoRecords() {
        List<DriverData> foundDrivers = driverDataRepository.findAll();

        assertTrue(foundDrivers.isEmpty());
        assertEquals(0, foundDrivers.size());
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
        final long ID1 = 123L;
        final long ID2 = 456L;

        insertExampleDrivers(ID1, ID2);

        assertEquals(2, driverDataRepository.findAll().size());

        driverDataRepository.deleteById(ID1);

        assertEquals(1, driverDataRepository.findAll().size());
        assertEquals(ID2, driverDataRepository.findAll().get(0).getId());
    }

    @Test
    public void shouldDeleteDriverByIdMissingDriver() {
        final long ID1 = 123L;
        final long ID2 = 456L;

        insertExampleDrivers(ID1, ID2);

        assertThrows(
                EmptyResultDataAccessException.class,
                () -> driverDataRepository.deleteById(1L)
        );
    }

    @Test
    public void shouldGetDriverById() {
        final long ID1 = 123L;
        final long ID2 = 456L;

        insertExampleDrivers(ID1, ID2);

        DriverData foundDriver = driverDataRepository.getById(ID2);

        assertEquals(ID2, foundDriver.getId());
    }

    @Test
    public void shouldGetDriverByIdMissingDriver() {
        fail();
    }

    @Test
    public void shouldUpdatePhoneById() {
        final long ID1 = 123L;
        final long ID2 = 456L;

        insertExampleDrivers(ID1, ID2);

        DriverData foundDriver = driverDataRepository.getById(ID1);

        assertEquals("123456789", foundDriver.getTelephoneNumber());

        driverDataRepository.updatePhoneById(ID1, "42763784587354");

        foundDriver = driverDataRepository.getById(ID1);

        assertEquals("42763784587354", foundDriver.getTelephoneNumber());
    }

    @Test
    public void shouldUpdatePhoneByIdMissingDriver() {
        fail();
    }

    @Test
    public void shouldUpdateDriverById() {
        final long ID1 = 123L;
        final long ID2 = 456L;

        insertExampleDrivers(ID1, ID2);

        DriverData foundDriver = driverDataRepository.getById(ID1);

        assertEquals("Adam", foundDriver.getOwnerFirstName());

        DriverData driverToUpdate = DriverData.builder()
                .id(ID1)
                .ownerFirstName("Bartek")
                .build();

        driverDataRepository.updateDriverById(ID1, driverToUpdate);

        foundDriver = driverDataRepository.getById(ID1);

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
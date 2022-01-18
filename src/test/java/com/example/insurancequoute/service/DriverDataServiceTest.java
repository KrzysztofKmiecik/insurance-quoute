package com.example.insurancequoute.service;

import com.example.insurancequoute.model.DriverData;
import com.example.insurancequoute.repository.DriverDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DriverDataServiceTest {

    @Mock
    private DriverDataRepository driverDataRepository;

    @InjectMocks
    private DriverDataService driverDataService;

    private DriverData driverData1;
    private DriverData driverData2;


    @BeforeEach
    public void setup() {
        driverData1 = Mockito.mock(DriverData.class);
        driverData2 = Mockito.mock(DriverData.class);
    }

    @Test
    void getVehicleTypeFactor_shouldReturnCorrectFactorValueWhenVehicleTypeCabriolet() {

        double cabrioletFactor = driverDataService.getVehicleTypeFactor("Cabriolet");

        assertEquals(1.3, cabrioletFactor);
    }

    @Test
    void getVehicleTypeFactor_shouldReturnCorrectFactorValueWhenVehicleTypeCoupe() {

        double coupeFactor = driverDataService.getVehicleTypeFactor("Coupe");

        assertEquals(1.4, coupeFactor);
    }

    @Test
    void getVehicleTypeFactor_shouldReturnCorrectFactorValueWhenVehicleTypeEstate() {

        double estateFactor = driverDataService.getVehicleTypeFactor("Estate");

        assertEquals(1.5, estateFactor);
    }

    @Test
    void getVehicleTypeFactor_shouldReturnCorrectFactorValueWhenVehicleTypeHatchback() {

        double hatchbackFactor = driverDataService.getVehicleTypeFactor("Hatchback");

        assertEquals(1.6, hatchbackFactor);
    }

    @Test
    void getVehicleTypeFactor_shouldReturnCorrectFactorValueWhenVehicleTypeOther() {

        double otherFactor = driverDataService.getVehicleTypeFactor("Other");

        assertEquals(1.7, otherFactor);
    }

    @Test
    void shouldReturnWhenVehicleTypeNull() {

        fail();
    }

    @Test
    void shouldReturnCorrectFactorValueWhenEngineSize1000() {

        double engineFactor = driverDataService.getEngineSizeFactor(1000);

        assertEquals(1.0, engineFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenEngineSize1600() {

        double engineFactor = driverDataService.getEngineSizeFactor(1600);

        assertEquals(1.6, engineFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenEngineSize2000() {

        double engineFactor = driverDataService.getEngineSizeFactor(2000);

        assertEquals(2.0, engineFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenEngineSize2500() {

        double engineFactor = driverDataService.getEngineSizeFactor(2500);

        assertEquals(2.5, engineFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenEngineSize3000() {

        double engineFactor = driverDataService.getEngineSizeFactor(3000);

        assertEquals(3.0, engineFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenEngineSizeOther() {

        double engineFactor = driverDataService.getEngineSizeFactor(14500);

        assertEquals(3.5, engineFactor);
    }

    @Test
    void shouldReturnWhenEngineSizeNull() {

        fail();
    }

    @Test
    void shouldReturnCorrectFactorValueWhenVehicleValueEqualOrGreater5000() {

        double vehicleFactor = driverDataService.getVehicleValueFactor(5000);

        assertEquals(1.0, vehicleFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenVehicleValueLessThan5000() {

        double vehicleFactor = driverDataService.getVehicleValueFactor(4800);

        assertEquals(1.2, vehicleFactor);
    }

    @Test
    void shouldReturnWhenVehicleValueLessThanNull() {

        fail();
    }

    @Test
    void shouldReturnCorrectFactorValueWhenAdditionalDriversLessThan2() {

        double additionalDriversFactor = driverDataService.getAdditionalDriversFactor(1);

        assertEquals(1.1, additionalDriversFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenAdditionalDriversEqualOrGreaterThan2() {

        double additionalDriversFactor = driverDataService.getAdditionalDriversFactor(4);

        assertEquals(1.2, additionalDriversFactor);
    }

    @Test
    void shouldReturnWhenAdditionalDriversNull() {

        fail();
    }

    @Test
    void shouldReturnCorrectFactorValueWhenCommercialUseTrue() {

        double commercialUseFactor = driverDataService.getCommercialUseFactor(true);

        assertEquals(1.1, commercialUseFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenCommercialUseFalse() {

        double commercialUseFactor = driverDataService.getCommercialUseFactor(false);

        assertEquals(1.0, commercialUseFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenOutsideStatusTrue() {

        double outsideStateFactor = driverDataService.getOutsideStateFactor(true);

        assertEquals(1.1, outsideStateFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenOutsideStatusFalse() {

        double outsideStateFactor = driverDataService.getOutsideStateFactor(false);

        assertEquals(1.1, outsideStateFactor);
    }

    @Test
    void calculateInsuranceQuotation_returnCorrectValueWhenAllValuesGiven() {
        when(driverDataService.getVehicleTypeFactor(any())).thenReturn(2.0);
        when(driverDataService.getEngineSizeFactor(any())).thenReturn(1.0);
        when(driverDataService.getAdditionalDriversFactor(any())).thenReturn(3.0);
        when(driverDataService.getCommercialUseFactor(any())).thenReturn(1.0);
        when(driverDataService.getOutsideStateFactor(any())).thenReturn(4.0);
        when(driverDataService.getVehicleValueFactor(any())).thenReturn(1.0);

        Double insurance = driverDataService.calculateInsuranceQuotation(driverData1);

        assertEquals(2400, insurance);
    }

    @Test
    void getAllDrivers_shouldReturnDriversList() {
        List<DriverData> drivers = new ArrayList<>();
        drivers.add(driverData1);
        drivers.add(driverData2);

        when(driverDataRepository.findAll()).thenReturn(drivers);

        List<DriverData> allDrivers = driverDataService.getAllDrivers();

        assertFalse(allDrivers.isEmpty());
        assertEquals(2, allDrivers.size());
    }

    @Test
    void addNewDriver_shouldSaveDriverInDb() {

        driverDataService.addNewDriver(driverData1);

    }

    @Test
    void deleteDriverRecordById_shouldDeleteDriverFromDb() {
        when(driverDataRepository.findById(any())).thenReturn(Optional.of(driverData1));

        driverDataService.deleteDriverRecordById(1L);

    }

    @Test
    void getDriverRecordById_shouldReturnDriverWithGivenId() {

        driverDataService.getDriverRecordById(1L);

    }

    @Test
    void updateDriversTelephoneRecordById_shouldUpdateDriversTelephoneWhenIdGiven() {

        driverDataService.updateDriversTelephoneRecordById(1L, 876478);

    }

    @Test
    void updateAllDriversDataById_shouldUpdateDriversDataWhenIdGiven() {
        DriverData driverData = new DriverData();
        driverData.setAdditionalDrivers(5);
        driverData.setCommercialPurpose(true);
        driverData.setEngineSize(5000);
        driverData.setCurrentValue(2000);
        driverData.setVehicleType("Coupe");
        driverData.setUsedOutsideTheRegisteredState(false);

        driverDataService.updateAllDriversDataById(driverData);

        DriverData driver = (DriverData) driverDataRepository.findById(1L).get();

        assertAll(
                () -> assertEquals(5, driver.getAdditionalDrivers()),
                () -> assertEquals(true, driver.isCommercialPurpose()),
                () -> assertEquals(5000, driver.getEngineSize()),
                () -> assertEquals(2000, driver.getCurrentValue()),
                () -> assertEquals("Coupe", driver.getVehicleType())
        );
    }


}
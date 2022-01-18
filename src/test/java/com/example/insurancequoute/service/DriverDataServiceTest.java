package com.example.insurancequoute.service;

import com.example.insurancequoute.repository.DriverDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@ExtendWith(MockitoExtension.class)
class DriverDataServiceTest {

    @Mock
    private DriverDataRepository driverDataRepository;

    @InjectMocks
    private DriverDataService driverDataService;

    @Test
    void shouldReturnCorrectFactorValueWhenVehicleTypeCabriolet() {

        int cabrioletFactor = driverDataService.getVehicleTypeFactor("Cabriolet");

        assertEquals(1.3, cabrioletFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenVehicleTypeCoupe() {

        int coupeFactor = driverDataService.getVehicleTypeFactor("Coupe");

        assertEquals(1.4, coupeFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenVehicleTypeEstate() {

        int estateFactor = driverDataService.getVehicleTypeFactor("Estate");

        assertEquals(1.5, estateFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenVehicleTypeHatchback() {

        int hatchbackFactor = driverDataService.getVehicleTypeFactor("Hatchback");

        assertEquals(1.6, hatchbackFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenVehicleTypeOther() {

        int otherFactor = driverDataService.getVehicleTypeFactor("Other");

        assertEquals(1.7, otherFactor);
    }

    @Test
    void shouldReturnWhenVehicleTypeNull() {

        fail();
    }

    @Test
    void shouldReturnCorrectFactorValueWhenEngineSize1000() {

        int engineFactor = driverDataService.getEngineSizeFactor(1000);

        assertEquals(1.0, engineFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenEngineSize1600() {

        int engineFactor = driverDataService.getEngineSizeFactor(1600);

        assertEquals(1.6, engineFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenEngineSize2000() {

        int engineFactor = driverDataService.getEngineSizeFactor(2000);

        assertEquals(2.0, engineFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenEngineSize2500() {

        int engineFactor = driverDataService.getEngineSizeFactor(2500);

        assertEquals(2.5, engineFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenEngineSize3000() {

        int engineFactor = driverDataService.getEngineSizeFactor(3000);

        assertEquals(3.0, engineFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenEngineSizeOther() {

        int engineFactor = driverDataService.getEngineSizeFactor(14500);

        assertEquals(3.5, engineFactor);
    }

    @Test
    void shouldReturnWhenEngineSizeNull() {

        fail();
    }

    @Test
    void shouldReturnCorrectFactorValueWhenVehicleValueEqualOrGreater5000() {

        int vehicleFactor = driverDataService.getVehicleValueFactor(5000);

        assertEquals(1.0, vehicleFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenVehicleValueLessThan5000() {

        int vehicleFactor = driverDataService.getVehicleValueFactor(4800);

        assertEquals(1.2, vehicleFactor);
    }

    @Test
    void shouldReturnWhenVehicleValueLessThanNull() {

        fail();
    }

    @Test
    void shouldReturnCorrectFactorValueWhenAdditionalDriversLessThan2() {

        int additionalDriversFactor = driverDataService.getAdditionalDriversFactor(1);

        assertEquals(1.1, additionalDriversFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenAdditionalDriversEqualOrGreaterThan2() {

        int additionalDriversFactor = driverDataService.getAdditionalDriversFactor(4);

        assertEquals(1.2, additionalDriversFactor);
    }

    @Test
    void shouldReturnWhenAdditionalDriversNull() {

        fail();
    }

    @Test
    void shouldReturnCorrectFactorValueWhenCommercialUseTrue() {

        int commercialUseFactor = driverDataService.getCommercialUseFactor(true);

        assertEquals(1.1, commercialUseFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenCommercialUseFalse() {

        int commercialUseFactor = driverDataService.getCommercialUseFactor(false);

        assertEquals(1.0, commercialUseFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenOutsideStatusTrue() {

        int outsideStateFactor = driverDataService.getOutsideStateFactor(true);

        assertEquals(1.1, outsideStateFactor);
    }

    @Test
    void shouldReturnCorrectFactorValueWhenOutsideStatusFalse() {

        int outsideStateFactor = driverDataService.getOutsideStateFactor(false);

        assertEquals(1.1, outsideStateFactor);
    }

    @Test
    void testInsuranceQuotationCalculationWhenAllFactorsGiven() {

        driverDataService.calculateInsuranceQuotation();
    }

    @Test
    void testInsuranceQuotationCalculationWhenCabrioletGiven() {

        fail();
    }

    @Test
    void testInsuranceQuotationCalculationWhenNoVehicleTypeGiven() {

        fail();
    }

    @Test
    void testInsuranceQuotationCalculationWhenEngineSize2000Given() {

        fail();
    }

    @Test
    void testInsuranceQuotationCalculationWhenVehicleValue6500Given() {

        fail();
    }

    @Test
    void testInsuranceQuotationCalculationWhenAdditionalDrivers5Given() {

        fail();
    }

    @Test
    void testInsuranceQuotationCalculationWhenAdditionalDriversGiven() {

        fail();
    }

    @Test
    void testInsuranceQuotationCalculationWhenCommercialUseTrue() {

        fail();
    }

    @Test
    void testInsuranceQuotationCalculationWhenCommercialUseFalse() {

        fail();
    }

    @Test
    void testInsuranceQuotationCalculationWhenOutsideStateTrue() {

        fail();
    }

    @Test
    void testInsuranceQuotationCalculationWhenOutsideStateFalse() {

        fail();
    }

    @Test
    void testInsuranceQuotationCalculationWhenOutsideStateNotGiven() {

        fail();
    }
}
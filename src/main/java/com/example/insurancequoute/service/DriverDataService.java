package com.example.insurancequoute.service;

import com.example.insurancequoute.model.DriverData;
import org.springframework.stereotype.Service;

@Service
class DriverDataService {

    private static final Integer MULTIPLIER = 100;

    public Integer calculateInsuranceQuotation(DriverData driverData) {
        int typeFactor = getVehicleTypeFactor(driverData.getVehicleType());
        int engineSizeFactor = getEngineSizeFactor(driverData.getEngineSize());
        int additionalDriversFactor = getAdditionalDriversFactor(driverData.getAdditionalDrivers());
        int commercialUseFactor = getCommercialUseFactor(driverData.isCommercialPurpose());
        int outsideStateFactor = getOutsideStateFactor(driverData.isUsedOutsideTheRegisteredState());
        int vehicleValueFactor = getVehicleValueFactor(driverData.getCurrentValue());
        return MULTIPLIER * typeFactor * engineSizeFactor * additionalDriversFactor * commercialUseFactor * outsideStateFactor * vehicleValueFactor;
    }

    public int getVehicleTypeFactor(String vehicleType) {
        return 0;
    }

    public int getEngineSizeFactor(int engineSize) {
        return 0;
    }

    public int getAdditionalDriversFactor(int additionalDrivers) {
        return 0;
    }

    public int getCommercialUseFactor(boolean commercialPurpose) {
        return 0;
    }

    public int getOutsideStateFactor(boolean usedOutsideTheRegisteredState) {
        return 0;
    }

    public int getVehicleValueFactor(int currentValue) {
        return 0;
    }
}
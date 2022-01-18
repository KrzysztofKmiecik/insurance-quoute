package com.example.insurancequoute.service;

import com.example.insurancequoute.model.DriverData;
import com.example.insurancequoute.repository.DriverDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
class DriverDataService {

    private DriverDataRepository driverDataRepository;

    private static final Integer MULTIPLIER = 100;

    public Double calculateInsuranceQuotation(DriverData driverData) {
        double typeFactor = getVehicleTypeFactor(driverData.getVehicleType());
        double engineSizeFactor = getEngineSizeFactor(driverData.getEngineSize());
        double additionalDriversFactor = getAdditionalDriversFactor(driverData.getAdditionalDrivers());
        double commercialUseFactor = getCommercialUseFactor(driverData.isCommercialPurpose());
        double outsideStateFactor = getOutsideStateFactor(driverData.isUsedOutsideTheRegisteredState());
        double vehicleValueFactor = getVehicleValueFactor(driverData.getCurrentValue());
        return MULTIPLIER * typeFactor * engineSizeFactor * additionalDriversFactor * commercialUseFactor * outsideStateFactor * vehicleValueFactor;
    }

    public List<DriverData> getAllDrivers(){
        return (List<DriverData>) driverDataRepository.findAll();
    }

    public void addNewDriver(DriverData driver){
         driverDataRepository.save(driver);
    }

    public void deleteDriverRecordById(Long id){
         driverDataRepository.deleteById(id);
    }

    public Optional getDriverRecordById(Long id){
        return driverDataRepository.findById(id);
    }

    public void updateDriversTelephoneRecordById(Long id, Integer phoneNumber){
    }

    public void updateAllDriversDataById(DriverData driver){
    }

    public double getVehicleTypeFactor(String vehicleType) {
        switch(vehicleType){
            case "Cabriolet":
                return 1.3;
            case "Coupe":
                return 1.4;
            case "Estate":
                return 1.5;
            case "Hatchback":
                return 1.6;
            default:
                return 1.7;
        }
    }

    public double getEngineSizeFactor(int engineSize) {
        switch(engineSize){
            case 1000:
                return 1.0;
            case 1600:
                return 1.6;
            case 2000:
                return 2.0;
            case 2500:
                return 2.5;
            case 3000:
                return 3.0;
            default:
                return 3.5;
        }
    }

    public double getAdditionalDriversFactor(int additionalDrivers) {
        return additionalDrivers < 2 ? 1.1 : 1.2;
    }

    public double getCommercialUseFactor(boolean commercialPurpose) {
        return commercialPurpose ? 1.1 : 1.0;
    }

    public double getOutsideStateFactor(boolean usedOutsideTheRegisteredState) {
        return usedOutsideTheRegisteredState ? 1.1 : 1.0;
    }

    public double getVehicleValueFactor(int currentValue) {
        return currentValue > 5000 ? 1.2 : 1.0;
    }
}
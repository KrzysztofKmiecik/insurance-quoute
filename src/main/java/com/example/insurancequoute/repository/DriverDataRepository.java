package com.example.insurancequoute.repository;

import com.example.insurancequoute.model.DriverData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverDataRepository extends JpaRepository<DriverData, Long> {


    void updatePhoneById(long ID1, String s);

    void updateDriverById(long id1, DriverData driverToUpdate);
}

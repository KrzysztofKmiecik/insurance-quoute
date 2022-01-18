package com.example.insurancequoute.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public
class DriverData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String prefix;
    private String ownerFirstName;
    private String ownerLastName;

    private String telephoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postCode;
    private String vehicleType;
    private int engineSize;
    private int additionalDrivers;
    private boolean commercialPurpose;
    private boolean usedOutsideTheRegisteredState;
    private int currentValue;
    private LocalDateTime firstRegisteredDate;

    private int premium;
}

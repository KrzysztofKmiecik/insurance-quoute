package com.example.insurancequoute.controller;

import com.example.insurancequoute.model.DriverData;
import com.example.insurancequoute.service.DriverDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest
class DriverDataControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DriverDataService driverDataService;
    private List<DriverData> drivers;


    @BeforeEach
    void beforeEach(){
        drivers = getDriverData();
    }

    @Test
    void shouldGetAliveEndpoint_status200() throws Exception {

        Mockito.when(driverDataService.getAll()).thenReturn(drivers);

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        //   fail();
    }



    @Test
    void shouldGetAlldriverRecordsById_status200() throws Exception {

        Mockito.when(driverDataService.getById(1L)).thenReturn(Optional.ofNullable(drivers.get(0)));

        mockMvc.perform(MockMvcRequestBuilders.get("/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.prefix").value("Mr"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ownerFirstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ownerLastName").value("Woo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.telephoneNumber").value("123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addressLine1").value("BakerStreet"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addressLine2").value("ff"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("London"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.postCode").value("233-2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleType").value("Coupe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.engineSize").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.additionalDrivers").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.commercialPurpose").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usedOutsideTheRegisteredState").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currentValue").value(20000));

//fail();
    }
    private List<DriverData> getDriverData() {
        DriverData driver1 = new DriverData(1L, "Mr", "John", "Woo", "123",
                "BakerStreet", "ff", "London", "233-2", "Coupe", 1000,
                1, false, false, 20000, LocalDateTime.now());
        DriverData driver2 = new DriverData(2L, "Mr", "John", "Woo", "123",
                "BakerStreet", "ff", "London", "233-2", "Coupe", 1000,
                1, false, false, 20000, LocalDateTime.now());
        List<DriverData> drivers = new ArrayList<>();
        drivers.add(driver1);
        drivers.add(driver2);
        return drivers;
    }
}
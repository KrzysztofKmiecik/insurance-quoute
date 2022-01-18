package com.example.insurancequoute.controller;

import com.example.insurancequoute.model.DriverData;
import com.example.insurancequoute.service.DriverDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class DriverDataControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private DriverDataService driverDataService;

    @Test
    @DisplayName("GET driver/getAll -> 200")
    public void getAllDrivers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/driver/getAll"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST driver -> 200")
    public void postDriver() throws Exception {
        DriverData driver = generateExampleDriver();
        String requestJson = objectMapper.writeValueAsString(driver);

        mockMvc.perform(MockMvcRequestBuilders.post("/driver")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST driver -> 400")
    public void postDriverMissingBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/driver")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("DELETE driver/{id} -> 200")
    public void deleteDriverById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/driver/24353"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE driver/{id} missing param -> 405")
    public void deleteDriverMissingId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/driver/"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("GET driver/get/{id} -> 200")
    public void getDriverById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/driver/get/23535"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET driver/get/{id} missing param -> 405")
    public void getDriverByIdMissingId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/driver/get/"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("PUT driver/updatePhoneNumber/{id}/{phoneNumber} -> 200")
    public void updateDriversPhoneById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/driver/updatePhoneNumber/123/2465768"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT driver/updatePhoneNumber/{id}/{phoneNumber} missing param-> 405")
    public void updateDriversPhoneByIdMissingParameters() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/driver/updatePhoneNumber"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("PUT driver/update/{id} -> 200")
    public void updateDriverById() throws Exception {
        DriverData driver = generateExampleDriver();
        String requestJson = objectMapper.writeValueAsString(driver);

        mockMvc.perform(MockMvcRequestBuilders.put("/driver/update/2465768")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT driver/update/{id} missing id -> 405")
    public void updateDriverByIdMissingParam() throws Exception {
        DriverData driver = generateExampleDriver();
        String requestJson = objectMapper.writeValueAsString(driver);

        mockMvc.perform(MockMvcRequestBuilders.put("/driver/update/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("PUT driver/update/{id} missing id -> 400")
    public void updateDriverByIdMissingBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/driver/update/34353")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private DriverData generateExampleDriver() {
        DriverData newDriver = DriverData.builder()
                .id(2434L)
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
        return newDriver;
    }

}
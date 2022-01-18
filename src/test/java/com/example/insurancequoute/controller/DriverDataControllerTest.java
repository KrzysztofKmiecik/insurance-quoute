package com.example.insurancequoute.controller;

import com.example.insurancequoute.service.DriverDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest
class DriverDataControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private DriverDataService driverDataService;

    @Test
    @DisplayName("http://localhost/driver/getAll -> 200")
    public void getAllDrivers() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/driver/getAll"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void postDriver() {
        fail();
    }

    @Test
    public void deleteDriverById() {
        fail();
    }

    @Test
    public void getDriverById() {
        fail();
    }

    @Test
    public void updateDriversPhoneById() {
        fail();
    }

    @Test
    public void updateDriverById() {
        fail();
    }

}
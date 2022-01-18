package com.example.insurancequoute.controller;

import com.example.insurancequoute.service.DriverDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.fail;

@WebMvcTest
class DriverDataControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private DriverDataService driverDataService;

    @Test
    public void getAllDrivers() {
        fail();
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
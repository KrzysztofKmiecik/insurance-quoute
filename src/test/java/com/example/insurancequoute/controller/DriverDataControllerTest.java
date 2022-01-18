package com.example.insurancequoute.controller;

import com.example.insurancequoute.service.DriverDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest
class DriverDataControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DriverDataService driverDataService;

    @Test
    void shouldGetAliveEndpoint_status200() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

     //   fail();
    }

}
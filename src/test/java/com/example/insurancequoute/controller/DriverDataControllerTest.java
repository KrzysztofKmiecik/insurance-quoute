package com.example.insurancequoute.controller;

import com.example.insurancequoute.model.DriverData;
import com.example.insurancequoute.service.DriverDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
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

    private List<DriverData> drivers;


    @BeforeEach
    void beforeEach() {
        drivers = getDriverData();
    }

    @Test
    @DisplayName("should Get Alive Endpoint_status200")
    void shouldGetAliveEndpoint_status200() throws Exception {

        // Mockito.when(driverDataService.getAll()).thenReturn(drivers);

        mockMvc.perform(MockMvcRequestBuilders.get("/drivers"))
                .andDo(print())
                .andExpect(status().isOk());

        //   fail();
    }

    @Test
    @DisplayName("should Post New Driver Record")
    void shouldPostANewDriverRecord() throws Exception {
        DriverData driver3 = new DriverData(3L, "Mr", "Franek", "Woo", "123",
                "BakerStreet", "ff", "London", "233-2", "Coupe", 1000,
                1, false, false, 20000, LocalDateTime.now());
      /*  doNothing().when(driverDataService).addDriver(isA(DriverData.class));
        driverDataService.addDriver(driver3);*/
        //  Mockito.when(driverDataService.addDriver(driver3)).thenReturn(driver3);

        mockMvc.perform(MockMvcRequestBuilders.post("/drivers")
                        .content(objectMapper.writeValueAsString(driver3))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());


        //  fail();
    }


    @Test
    void shouldGetAlldriverRecordsById_status200() throws Exception {

        Mockito.when(driverDataService.getById(1L)).thenReturn(Optional.ofNullable(drivers.get(0)));

        mockMvc.perform(MockMvcRequestBuilders.get("/drivers/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
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

    @Test
    public void getDriverByIdAPI() throws Exception {
        Mockito.when(driverDataService.getById(1L)).thenReturn(Optional.ofNullable(drivers.get(0)));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/drivers/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }


    @Test
    @DisplayName("DELETE /drivers/1")
    public void deleteDriver() throws Exception {
        doNothing().when(driverDataService).removeDriver(isA(Long.class));
        drivers.remove(drivers.get((int)1L));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/drivers/{id}", 1L))
                .andExpect(status().isNoContent());

        assertEquals(1, drivers.size());
    }

    @Test
    @DisplayName("PATCH telephone by id")
    public void updateTelephoneById(){
        fail();
    }

    @Test
    @DisplayName("PUT by id")
    public void updateAllById(){
        fail();
    }


    private List<DriverData> getDriverData() {
        DriverData driver1 = new DriverData(1L, "Mr", "John", "Woo", "123",
                "BakerStreet", "ff", "London", "233-2", "Coupe", 1000,
                1, false, false, 20000, LocalDateTime.now());
        DriverData driver2 = new DriverData(2L, "Ms", "Ann", "Wooa", "1234",
                "BakerStreeta", "ffa", "Londona", "233-2a", "Coupea", 2000,
                3, true, true, 30000, LocalDateTime.now());
        List<DriverData> drivers = new ArrayList<>();
        drivers.add(driver1);
        drivers.add(driver2);
        return drivers;
    }
}
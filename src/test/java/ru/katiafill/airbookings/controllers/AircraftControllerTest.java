package ru.katiafill.airbookings.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.katiafill.airbookings.exception.DatabaseException;
import ru.katiafill.airbookings.models.Aircraft;
import ru.katiafill.airbookings.models.FareConditions;
import ru.katiafill.airbookings.models.LocalizedString;
import ru.katiafill.airbookings.models.Seat;
import ru.katiafill.airbookings.services.AircraftService;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AircraftController.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class AircraftControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AircraftService service;

    private static Aircraft aircraft;

    @BeforeAll
    static void setUp() {
        aircraft = Aircraft.builder()
                .code("SMP")
                .model(new LocalizedString("Sample", "Пример"))
                .range(1000)
                .build();
    }

    @Test
    void getAircrafts() throws Exception {
        when(service.findAll()).thenReturn(List.of(aircraft));

        mvc.perform(get("/aircrafts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].code").value(aircraft.getCode()))
                .andReturn();
    }

    @Test
    void getAircraftById() throws Exception {
        when(service.findById(any())).thenReturn(Optional.of(aircraft));

        mvc.perform(get("/aircraft/" + aircraft.getCode())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(aircraft.getCode()))
                .andReturn();
    }

    @Test
    void getSeatsByAircraft() throws Exception {
        Seat economySeat = new Seat(aircraft.getCode(), "A1", FareConditions.Economy);
        when(service.getSeatsByAircraftCodeAndFareCondition(any(), any()))
                .thenReturn(List.of(economySeat));

        mvc.perform(get("/aircraft/" + aircraft.getCode() + "/seats/Economy")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].aircraftCode").value(economySeat.getAircraftCode()))
                .andExpect(jsonPath("$[0].seatNo").value(economySeat.getSeatNo()))
                .andExpect(jsonPath("$[0].fareConditions").value(economySeat.getFareConditions().name()))
                .andReturn();
    }

    @Test
    void addAircraft() throws Exception {
        when(service.save(any())).thenReturn(aircraft);
        ObjectMapper objectMapper = new ObjectMapper();

        mvc.perform(
                post("/aircraft")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(aircraft)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(aircraft.getCode()))
                .andReturn();
    }

    @Test
    void deleteAircraft() throws Exception {
        mvc.perform(delete("/aircraft/" + aircraft.getCode()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteNoAircraft() throws Exception {
        doThrow(new DatabaseException("Delete Error")).when(service).delete(aircraft.getCode());

        mvc.perform(
                delete("/aircraft/" + aircraft.getCode()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Delete Error"));

    }
}
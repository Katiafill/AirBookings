package ru.katiafill.airbookings.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.katiafill.airbookings.models.Aircraft;
import ru.katiafill.airbookings.models.FareConditions;
import ru.katiafill.airbookings.models.LocalizedString;
import ru.katiafill.airbookings.models.Seat;
import ru.katiafill.airbookings.repositories.AircraftRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AircraftServiceTest {

    @Autowired
    private AircraftService service;

    @MockBean
    private AircraftRepository repository;

    private Aircraft aircraft;
    private Seat economySeat, comfortSeat, businessSeat;
    private String aircraftCode;

    @BeforeEach
    public void setUp() {
        aircraftCode = "SMP";

        economySeat = new Seat(aircraftCode, "A1", FareConditions.Economy);
        comfortSeat = new Seat(aircraftCode, "B1", FareConditions.Comfort);
        businessSeat = new Seat(aircraftCode, "C1", FareConditions.Business);

        aircraft = Aircraft.builder()
                .code(aircraftCode)
                .model(new LocalizedString("Sample", "Пример"))
                .range(1000)
                .build();

    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(List.of(aircraft));

        List<Aircraft> aircrafts = service.findAll();

        assertNotNull(aircrafts);
        assertEquals(aircrafts.size(), 1);
        assertEquals(aircrafts.get(0), aircraft);
    }

    @Test
    void findById() {
        when(repository.findById(aircraftCode)).thenReturn(Optional.of(aircraft));

        Optional<Aircraft> optionalAircraft = service.findById(aircraftCode);

        assertTrue(optionalAircraft.isPresent());
        assertEquals(optionalAircraft.get(), aircraft);
    }

    @Test
    void findById_invalidId() {
        when(repository.findById(aircraftCode)).thenReturn(Optional.empty());

        Optional<Aircraft> optionalAircraft = service.findById(aircraftCode);
        assertTrue(optionalAircraft.isEmpty());
    }

    @Test
    void save() {
        service.save(aircraft);
        verify(repository, times(1)).save(aircraft);
    }

    @Test
    void delete() {
        service.delete(aircraftCode);
        verify(repository, times(1)).deleteById(aircraftCode);
    }

    @Test
    void delete_null() {
        service.delete(null);
        verify(repository, times(1)).deleteById(null);
    }

    @Test
    void getSeatsByAircraftCodeAndFareCondition() {
        testSeatsByFareCondition(FareConditions.Economy, economySeat);
        testSeatsByFareCondition(FareConditions.Comfort, comfortSeat);
        testSeatsByFareCondition(FareConditions.Business, businessSeat);
    }

    private void testSeatsByFareCondition(FareConditions conditions, Seat actual) {
        when(repository.findSeatsByFareConditions(any(), any())).thenReturn(List.of(actual));

        List<Seat> seatList = service.getSeatsByAircraftCodeAndFareCondition(aircraftCode, conditions);

        assertNotNull(seatList);
        assertEquals(seatList.size(), 1);
        assertEquals(seatList.get(0), actual);
    }

    @Test
    void getSeatsForAircraft() {
        when(repository.findAllSeats(any())).thenReturn(List.of(economySeat, comfortSeat, businessSeat));

        Map<FareConditions, List<String>> actualSeats = Map.of(
                FareConditions.Economy, List.of(economySeat.getSeatNo()),
                FareConditions.Comfort, List.of(comfortSeat.getSeatNo()),
                FareConditions.Business, List.of(businessSeat.getSeatNo())
        );

        Map<FareConditions, List<String>> seats = service.getSeatsForAircraft(aircraftCode);
        assertEquals(seats, actualSeats);
    }

}
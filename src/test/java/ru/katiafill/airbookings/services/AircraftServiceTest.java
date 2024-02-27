package ru.katiafill.airbookings.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

@SpringBootTest
class AircraftServiceTest {

    @Autowired
    private AircraftService service;

    @MockBean
    private AircraftRepository repository;

    private Aircraft aircraft;
    private Seat economySeat, comfortSeat, businessSeat;
    private List<Seat> seats;
    private String aircraftCode;

    @BeforeEach
    public void setUp() {
        aircraftCode = "SMP";

        economySeat = new Seat(aircraftCode, "A1", FareConditions.Economy);
        comfortSeat = new Seat(aircraftCode, "B1", FareConditions.Comfort);
        businessSeat = new Seat(aircraftCode, "C1", FareConditions.Business);
        seats = List.of(economySeat, comfortSeat, businessSeat);

        aircraft = Aircraft.builder()
                .code(aircraftCode)
                .model(new LocalizedString("Sample", "Пример"))
                .range(1000)
                .seats(seats)
                .build();

    }

    @Test
    void findAll() {
        Mockito.when(repository.findAll()).thenReturn(List.of(aircraft));

        List<Aircraft> aircrafts = service.findAll();

        assertNotNull(aircrafts);
        assertEquals(aircrafts.size(), 1);
        assertEquals(aircrafts.get(0), aircraft);
    }

    @Test
    void findById() {
        Mockito.when(repository.findById(aircraftCode)).thenReturn(Optional.of(aircraft));

        Optional<Aircraft> optionalAircraft = service.findById(aircraftCode);

        assertTrue(optionalAircraft.isPresent());
        assertEquals(optionalAircraft.get(), aircraft);
    }

    @Test
    void getSeatsByAircraftCodeAndFareCondition() {
        Mockito.when(repository.findById(aircraftCode)).thenReturn(Optional.of(aircraft));

        testSeatsByFareCondition(FareConditions.Economy, economySeat);
        testSeatsByFareCondition(FareConditions.Comfort, comfortSeat);
        testSeatsByFareCondition(FareConditions.Business, businessSeat);
    }

    private void testSeatsByFareCondition(FareConditions conditions, Seat actual) {
        List<Seat> seatList = service.getSeatsByAircraftCodeAndFareCondition(aircraftCode, conditions);

        assertNotNull(seatList);
        assertEquals(seatList.size(), 1);
        assertEquals(seatList.get(0), actual);
    }

    @Test
    void getSeatsForAircraft() {
        Mockito.when(repository.findById(aircraftCode)).thenReturn(Optional.of(aircraft));
        Map<FareConditions, List<String>> actualSeats = Map.of(
                FareConditions.Economy, List.of(economySeat.getSeatNo()),
                FareConditions.Comfort, List.of(comfortSeat.getSeatNo()),
                FareConditions.Business, List.of(businessSeat.getSeatNo())
        );

        Map<FareConditions, List<String>> seats = service.getSeatsForAircraft(aircraftCode);
        assertEquals(seats, actualSeats);
    }

    @Test
    void findAllDepartureAirportsByAircraftCode() {
    }

    @Test
    void findAllArrivalAirportsByAircraftCode() {
    }
}
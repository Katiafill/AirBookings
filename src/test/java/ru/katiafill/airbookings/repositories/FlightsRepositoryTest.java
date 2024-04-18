package ru.katiafill.airbookings.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.katiafill.airbookings.models.*;

import javax.persistence.EntityManager;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class FlightsRepositoryTest {
    @Autowired
    private FlightsRepository repository;

    @Autowired
    private RoutesRepository routesRepository;

    @Autowired
    private EntityManager entityManager;

    private Flight flight;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Aircraft aircraft;

    @BeforeEach
    void setUp() {
        aircraft = Aircraft.builder()
                .code("321")
                .model(new LocalizedString("Airbus A321-200", "Аэробус A321-200"))
                .range(5600)
                .build();

        departureAirport = Airport.builder()
                .code("LED")
                .name(new LocalizedString("Pulkovo Airport", "Пулково"))
                .city(new LocalizedString("St. Petersburg", "Санкт-Петербург"))
                .coordinates(new Point(30.262500762939453,59.80030059814453))
                .timezone("Europe/Moscow")
                .build();

        arrivalAirport = Airport.builder()
                .code("DME")
                .name(new LocalizedString("Domodedovo International Airport", "Домодедово"))
                .city(new LocalizedString("Moscow", "Москва"))
                .coordinates(new Point(37.90629959106445,55.40879821777344))
                .timezone("Europe/Moscow")
                .build();

        flight = Flight.builder()
                .id(182L)
                .flightNo("PG0402")
                .scheduledDeparture(ZonedDateTime.of(2024, 4, 20, 16, 15, 0, 0, ZoneId.of("Asia/Novosibirsk")))
                .scheduledArrival(ZonedDateTime.of(2024, 4, 20, 17, 20, 0, 0, ZoneId.of("Asia/Novosibirsk")))
                .status(FlightStatus.SCHEDULED)
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .aircraft(aircraft)
                .build();


        entityManager.persist(aircraft);
        entityManager.persist(departureAirport);
        entityManager.persist(arrivalAirport);
        entityManager.persist(flight);
    }

    @Test
    void findByFlightNo() {
        List<Flight> flights = repository.findByFlightNo(flight.getFlightNo());
        assertEquals(flights.size(), 1);
        Flight flight1 = flights.get(0);
        assertEquals(flight1, flight);
    }

    @Test
    void findByStatus() {
        List<Flight> flights = repository.findByStatus(FlightStatus.SCHEDULED);
        assertEquals(flights.size(), 1);
        Flight flight1 = flights.get(0);
        assertEquals(flight1, flight);
    }

    @Test
    void findByDepartureAirportAndArrivalAirport() {
        List<Flight> flights = repository.findScheduledFlights(departureAirport.getCode(), arrivalAirport.getCode());
        assertEquals(flights.size(), 1);
        Flight flight1 = flights.get(0);
        assertEquals(flight1, flight);
    }

}
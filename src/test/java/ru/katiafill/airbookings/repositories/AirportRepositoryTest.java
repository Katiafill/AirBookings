package ru.katiafill.airbookings.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.katiafill.airbookings.models.Airport;
import ru.katiafill.airbookings.models.LocalizedString;
import ru.katiafill.airbookings.models.Point;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class AirportRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AirportRepository repository;

    private Airport airport;
    @BeforeEach
    void setUp() {
          airport = Airport.builder()
                .code("SMP")
                .name(new LocalizedString("Sample Airport", "Тестовый Аэропорт"))
                .city(new LocalizedString("Novosibirsk", "Новосибирск"))
                .coordinates(new Point(82.6, 55.0))
                .timezone("Asia/Novosibirsk")
                .build();
          entityManager.persist(airport);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findById() {
        Optional<Airport> optionalAirport = repository.findById(airport.getCode());
        assertTrue(optionalAirport.isPresent());
        assertEquals(optionalAirport.get(), this.airport);
    }

    @Test
    void findAllByTimezone() {
        List<Airport> airports = repository.findAllByTimezone(airport.getTimezone());
        assertEquals(airports.size(), 1);
        assertEquals(airports.get(0), airport);
    }

    @Test
    void findAllByCity() {
        List<Airport> airports = repository.findAllByCity(airport.getCity().getEn());
        assertEquals(airports.size(), 1);
        Airport airport1 = airports.get(0);
        assertEquals(airport1, airport);

        airports = repository.findAllByCity(airport.getCity().getRu());
        assertEquals(airports.size(), 1);

        assertEquals(airports.get(0), airport1);
    }

    @Test
    void findByName() {
        Optional<Airport> optionalAirport = repository.findByName(airport.getName().getEn());
        assertTrue(optionalAirport.isPresent());
        Airport air = optionalAirport.get();
        assertEquals(air, airport);

        optionalAirport = repository.findByName(airport.getName().getRu());
        assertTrue(optionalAirport.isPresent());

        assertEquals(optionalAirport.get(), air);
    }

    @Test
    void findAllCities() {
        List<LocalizedString> cities = repository.findAllCities();
        assertEquals(cities.size(), 1);
        assertEquals(cities.get(0), airport.getCity());
    }
}
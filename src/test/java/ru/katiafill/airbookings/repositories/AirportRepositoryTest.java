package ru.katiafill.airbookings.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.katiafill.airbookings.models.Aircraft;
import ru.katiafill.airbookings.models.Airport;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class AirportRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(AirportRepositoryTest.class);
    @Autowired
    private AirportRepository repository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        List<Airport> airports = (List<Airport>) repository.findAll();
        assertNotNull(airports);
        assertFalse(airports.isEmpty());
        airports.forEach(System.out::println);
    }

    @Test
    void findAllByTimezone() {
        List<Airport> airports = repository.findAllByTimezone("Asia/Novosibirsk");
        assertNotNull(airports);
        assertEquals(1, airports.size());
    }

    @Test
    void findAllByCity() {
        List<Airport> airports = repository.findAllByCity("Novosibirsk");
        assertNotNull(airports);
        assertEquals(1, airports.size());
        Airport airport = airports.get(0);

        airports = repository.findAllByCity("Новосибирск");
        assertNotNull(airports);
        assertEquals(1, airports.size());

        assertEquals(airport, airports.get(0));
    }

    @Test
    void findAllByName() {
        Optional<Airport> airport = repository.findByName("Толмачёво");
        assertTrue(airport.isPresent());
        Airport air = airport.get();

        airport = repository.findByName("Tolmachevo Airport");
        assertTrue(airport.isPresent());

        assertEquals(airport.get(), air);
    }
}
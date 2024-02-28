package ru.katiafill.airbookings.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.katiafill.airbookings.models.Aircraft;
import ru.katiafill.airbookings.models.Airport;
import ru.katiafill.airbookings.models.LocalizedString;
import ru.katiafill.airbookings.models.Point;

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
    private TestEntityManager entityManager;

    @Autowired
    private AirportRepository repository;

    private Airport airport;
    @BeforeEach
    void setUp() {
          airport = Airport.builder()
                .code("SMP")
                .name(new LocalizedString("Sample Airport", "Тестовый Аэропорт"))
                .city(new LocalizedString("Novosibirsk2", "Новосибирск2"))
                .coordinates(new Point(82.6, 55.0))
                .timezone("Asia/Novosibirsk2")
                .build();
          entityManager.persist(airport);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findById() {
        Optional<Airport> airport = repository.findById("SMP");
        assertFalse(airport.isEmpty());
        assertEquals(airport.get(), this.airport);
    }

    @Test
    void findAllByTimezone() {
        List<Airport> airports = repository.findAllByTimezone("Asia/Novosibirsk2");
        assertNotNull(airports);
        assertEquals(1, airports.size());
    }

    @Test
    void findAllByCity() {
        List<Airport> airports = repository.findAllByCity("Novosibirsk2");
        assertNotNull(airports);
        assertEquals(1, airports.size());
        Airport airport = airports.get(0);

        airports = repository.findAllByCity("Новосибирск2");
        assertNotNull(airports);
        assertEquals(1, airports.size());

        assertEquals(airport, airports.get(0));
    }

    @Test
    void findAllByName() {
        Optional<Airport> airport = repository.findByName("Sample Airport");
        assertTrue(airport.isPresent());
        Airport air = airport.get();

        airport = repository.findByName("Тестовый Аэропорт");
        assertTrue(airport.isPresent());

        assertEquals(airport.get(), air);
    }

    @Test
    void findAllCities() {
        List<LocalizedString> cities = repository.findAllCities();
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
    }
}
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
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import ru.katiafill.airbookings.models.Aircraft;
import ru.katiafill.airbookings.models.FareConditions;
import ru.katiafill.airbookings.models.LocalizedString;
import ru.katiafill.airbookings.models.Seat;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class AircraftRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(AircraftRepositoryTest.class);
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AircraftRepository repository;

    private Aircraft aircraft;

    @BeforeEach
    void setUp() {
        aircraft = Aircraft.builder()
                .code("SMP")
                .model(new LocalizedString("Sample", "Пример"))
                .range(1000)
                .build();
        entityManager.persist(aircraft);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findById() {
        Optional<Aircraft> aircraft = repository.findById("SMP");
        assertFalse(aircraft.isEmpty());
        assertEquals(aircraft.get(), this.aircraft);
    }

    @Test
    void save() {
        entityManager.clear();
        aircraft = Aircraft.builder()
                .code("SMP")
                .model(new LocalizedString("Sample", "Пример"))
                .range(1000)
                .seats(List.of(new Seat("SMP","A1", FareConditions.Economy)))
                .build();

        repository.save(aircraft);
        repository.findAll().forEach(a -> logger.info(a.toString()));
    }

    @Test
    void deleteNotPresentId() {
        try {
            repository.deleteById("SMP");
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
        }
    }
}
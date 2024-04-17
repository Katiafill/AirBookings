package ru.katiafill.airbookings.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataAccessException;
import ru.katiafill.airbookings.models.Aircraft;
import ru.katiafill.airbookings.models.FareConditions;
import ru.katiafill.airbookings.models.LocalizedString;
import ru.katiafill.airbookings.models.Seat;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class AircraftRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AircraftRepository repository;

    private Aircraft aircraft;

    @BeforeEach
    void setUp() {
        Seat seat = new Seat("SMP", "A1", FareConditions.Economy);

        aircraft = Aircraft.builder()
                .code("SMP")
                .model(new LocalizedString("Sample", "Пример"))
                .range(1000)
                .build();

        entityManager.persist(seat);
        entityManager.persist(aircraft);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByModel() {
        List<Aircraft> aircrafts = repository.findAllByModel(aircraft.getModel().getRu());
        assertEquals(aircrafts.size(), 1);
        Aircraft aircraft1 = aircrafts.get(0);
        assertEquals(aircraft1, aircraft);
    }

    @Test
    void deleteNotPresentId() {
        try {
            repository.deleteById("SMP");
        } catch (DataAccessException ex) {
            log.error(ex.getLocalizedMessage());
        }
    }
}
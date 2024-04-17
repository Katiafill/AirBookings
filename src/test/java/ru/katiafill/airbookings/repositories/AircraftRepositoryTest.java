package ru.katiafill.airbookings.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class AircraftRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AircraftRepository repository;

    private Aircraft aircraft;
    private Seat seat;

    @BeforeEach
    void setUp() {
        seat = new Seat("SMP", "A1", FareConditions.Economy);

        aircraft = Aircraft.builder()
                .code("SMP")
                .model(new LocalizedString("Sample", "Пример"))
                .range(1000)
                .build();

        entityManager.persist(seat);
        entityManager.persist(aircraft);
        entityManager.flush();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByModel() {
        Optional<Aircraft> optionalAircraft = repository.findByModel(aircraft.getModel().getRu());
        assertTrue(optionalAircraft.isPresent());
        assertEquals(optionalAircraft.get(), aircraft);
    }

    @Test
    void findAllSeats() {
        List<Seat> seats = repository.findAllSeats(aircraft.getCode());
        assertEquals(seats.size(), 1);
        assertEquals(seats.get(0), seat);
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
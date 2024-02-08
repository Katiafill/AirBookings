package ru.katiafill.airbookings.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.katiafill.airbookings.models.Flight;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class FlightsRepositoryTest {
    @Autowired
    private FlightsRepository repository;

    @Test
    public void findAll() {
        List<Flight> flights = (List<Flight>) repository.findAll();
        assertNotNull(flights);
        assertFalse(flights.isEmpty());
        flights.subList(0, 10).forEach(System.out::println);
    }
}
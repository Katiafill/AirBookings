package ru.katiafill.airbookings.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.katiafill.airbookings.models.Airport;
import ru.katiafill.airbookings.models.Flight;
import ru.katiafill.airbookings.models.Route;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class FlightsRepositoryTest {
    @Autowired
    private FlightsRepository repository;

    @Autowired
    private RoutesRepository routesRepository;

    @Test
    public void findAll() {
        List<Flight> flights = (List<Flight>) repository.findAll();
        assertNotNull(flights);
        assertFalse(flights.isEmpty());
        flights.subList(0, 10).forEach(System.out::println);
    }

    @Test
    public void findAllRoutes() {
        List<Route> routes = routesRepository.findAll();
        routes.forEach(System.out::println);
    }

    @Test
    public void findAllByAircraftCode() {
        List<Route> routes = routesRepository.findAllByAircraftCode("CR2");
        assertNotNull(routes);
        assertFalse(routes.isEmpty());
        routes.forEach(System.out::println);
    }
}
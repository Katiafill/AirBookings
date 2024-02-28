package ru.katiafill.airbookings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.katiafill.airbookings.models.Flight;
import ru.katiafill.airbookings.models.Route;

import java.util.List;

public interface RoutesRepository extends JpaRepository<Route, String> {
    public List<Route> findAllByAircraftCode(String aircraftCode);
}

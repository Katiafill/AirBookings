package ru.katiafill.airbookings.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.katiafill.airbookings.models.Airport;
import ru.katiafill.airbookings.models.Flight;
import ru.katiafill.airbookings.models.Route;

import java.util.List;

public interface FlightsRepository extends CrudRepository<Flight, Long> {

}

package ru.katiafill.airbookings.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.katiafill.airbookings.models.Flight;

public interface FlightsRepository extends CrudRepository<Flight, Long> {
}

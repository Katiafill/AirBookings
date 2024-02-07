package ru.katiafill.airbookings.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.katiafill.airbookings.models.Airport;

public interface AirportRepository extends CrudRepository<Airport, String> {
}

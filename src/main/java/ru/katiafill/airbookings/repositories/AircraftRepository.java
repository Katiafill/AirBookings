package ru.katiafill.airbookings.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.katiafill.airbookings.models.Aircraft;

public interface AircraftRepository extends CrudRepository<Aircraft, String> {
}

package ru.katiafill.airbookings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.katiafill.airbookings.models.Aircraft;

public interface AircraftRepository extends JpaRepository<Aircraft, String> {
}

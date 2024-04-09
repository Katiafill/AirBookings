package ru.katiafill.airbookings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.katiafill.airbookings.models.Aircraft;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, String> {

}

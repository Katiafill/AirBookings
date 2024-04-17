package ru.katiafill.airbookings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.katiafill.airbookings.models.Aircraft;
import java.util.List;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, String> {
    @Query(value = "select * from {h-schema}aircrafts_data " +
            "where model->>'en' = :model or model->>'ru' = :model",
            nativeQuery = true)
    List<Aircraft> findAllByModel(@Param("model") String model);
}

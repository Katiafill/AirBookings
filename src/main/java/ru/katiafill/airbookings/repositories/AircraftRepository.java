package ru.katiafill.airbookings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.katiafill.airbookings.models.Aircraft;
import ru.katiafill.airbookings.models.FareConditions;
import ru.katiafill.airbookings.models.Seat;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, String> {
    /* Получение самолета по модели.
    * Название модели передается в одной из локали (ru, en). */
    @Query(value = "select * from {h-schema}aircrafts_data " +
            "where model->>'en' = :model or model->>'ru' = :model",
            nativeQuery = true)
    Optional<Aircraft> findByModel(@Param("model") String model);

    /* Получение всех мест в самолете по его идентификатору. */
    @Query(value = "select s from Seat s where s.aircraftCode = :code")
    List<Seat> findAllSeats(@Param("code") String aircraftCode);

    @Query(value = "select s from Seat s where s.aircraftCode = :code and s.fareConditions = :conditions")
    List<Seat> findSeatsByFareConditions(@Param("code") String aircraftCode,
                                         @Param("conditions") FareConditions fareConditions);
}

package ru.katiafill.airbookings.services;

import ru.katiafill.airbookings.exception.DatabaseException;
import ru.katiafill.airbookings.models.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AircraftService {
    List<Aircraft> findAll() throws DatabaseException;
    Optional<Aircraft> findById(@NotNull String id) throws DatabaseException;
    Aircraft save(@NotNull Aircraft aircraft) throws DatabaseException;
    void delete(@NotNull String aircraftCode) throws DatabaseException;

    List<Seat> getAllSeats(@NotNull String aircraftCode) throws DatabaseException;
    List<Seat> getSeatsByFareConditions(@NotNull String aircraftCode, @NotNull FareConditions conditions) throws DatabaseException;
    /* Получить места для лайнера, сгруппированные по классам обслуживания.*/
    Map<FareConditions, List<String>> getGroupedSeats(@NotNull String aircraftCode) throws DatabaseException;
}

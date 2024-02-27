package ru.katiafill.airbookings.services;

import ru.katiafill.airbookings.models.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AircraftService {
    List<Aircraft> findAll();
    Optional<Aircraft> findById(String id);
    List<Seat> getSeatsByAircraftCodeAndFareCondition(String aircraftCode, FareConditions conditions);
    /* Получить места для лайнера, сгруппированные по классам обслуживания.*/
    Map<FareConditions, List<String>> getSeatsForAircraft(String aircraftCode);

    Aircraft save(Aircraft aircraft);
    void delete(String aircraftCode);
}

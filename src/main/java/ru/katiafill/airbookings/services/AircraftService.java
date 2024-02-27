package ru.katiafill.airbookings.services;

import ru.katiafill.airbookings.models.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AircraftService {
    List<Aircraft> findAll();
    Optional<Aircraft> findById(String id);
    List<Seat> getSeatsByAircraftCodeAndFareCondition(String aircraftCode, FareConditions conditions);
    /* Получить места для лайнера, сгруппированные по класса обслуживания.*/
    Map<FareConditions, List<String>> getSeatsForAircraft(String aircraftCode);
    List<Airport> findAllDepartureAirportsByAircraftCode(String aircraftCode);
    List<Airport> findAllArrivalAirportsByAircraftCode(String aircraftCode);
}

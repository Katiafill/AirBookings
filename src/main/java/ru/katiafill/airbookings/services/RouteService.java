package ru.katiafill.airbookings.services;

import ru.katiafill.airbookings.models.Airport;

import java.util.List;

public interface RouteService {

    List<Airport> findAllDepartureAirportsByAircraftCode(String aircraftCode);
    List<Airport> findAllArrivalAirportsByAircraftCode(String aircraftCode);
}

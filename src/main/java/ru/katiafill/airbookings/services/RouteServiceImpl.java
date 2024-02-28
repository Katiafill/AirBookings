package ru.katiafill.airbookings.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.katiafill.airbookings.models.Airport;
import ru.katiafill.airbookings.models.Route;
import ru.katiafill.airbookings.repositories.RoutesRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RoutesRepository routesRepository;

    @Override
    public List<Airport> findAllDepartureAirportsByAircraftCode(String aircraftCode) {
        return getRoutesStreamByAircraftCode(aircraftCode)
                .map(Route::getDepartureAirport)
                .collect(Collectors.toList());
    }

    @Override
    public List<Airport> findAllArrivalAirportsByAircraftCode(String aircraftCode) {
        return getRoutesStreamByAircraftCode(aircraftCode)
                .map(Route::getArrivalAirport)
                .collect(Collectors.toList());
    }

    private Stream<Route> getRoutesStreamByAircraftCode(String aircraftCode) {
        return routesRepository.findAllByAircraftCode(aircraftCode)
                .stream();
    }
}

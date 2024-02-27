package ru.katiafill.airbookings.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.katiafill.airbookings.models.*;
import ru.katiafill.airbookings.repositories.AircraftRepository;
import ru.katiafill.airbookings.repositories.RoutesRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;
    private final RoutesRepository routesRepository;

    @Override
    public List<Aircraft> findAll() {
        return aircraftRepository.findAll();
    }

    @Override
    public Optional<Aircraft> findById(String id) {
        return aircraftRepository.findById(id);
    }

    @Override
    public List<Seat> getSeatsByAircraftCodeAndFareCondition(String aircraftCode, FareConditions conditions) {
        Optional<Aircraft> optionalAircraft = findById(aircraftCode);

        if (optionalAircraft.isEmpty()) {
            return List.of();
        }

        Aircraft aircraft = optionalAircraft.get();
        return aircraft.getSeats()
                .stream()
                .filter(s -> s.getFareConditions() == conditions)
                .collect(Collectors.toList());
    }

    @Override
    public Map<FareConditions, List<String>> getSeatsForAircraft(String aircraftCode) {
        Optional<Aircraft> optionalAircraft = findById(aircraftCode);

        if (optionalAircraft.isEmpty()) {
            return Map.of();
        }

        Aircraft aircraft = optionalAircraft.get();
        return aircraft.getSeats()
                .stream()
                .collect(Collectors.groupingBy(Seat::getFareConditions,
                        Collectors.mapping(Seat::getSeatNo, Collectors.toList())));
    }


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

package ru.katiafill.airbookings.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.katiafill.airbookings.exception.DatabaseException;
import ru.katiafill.airbookings.models.Aircraft;
import ru.katiafill.airbookings.models.FareConditions;
import ru.katiafill.airbookings.models.Seat;
import ru.katiafill.airbookings.repositories.AircraftRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;

    @Override
    public List<Aircraft> findAll() throws DatabaseException {
        try {
            return aircraftRepository.findAll();
        } catch (DataAccessException ex) {
            log.error("Failed find all aircrafts", ex);
            throw new DatabaseException("Exception occurred when find all aircrafts", ex);
        }
    }

    @Override
    public Optional<Aircraft> findById(String id) throws DatabaseException {
        try {
            return aircraftRepository.findById(id);
        } catch (DataAccessException ex) {
            log.error("Failed find aircraft by id: " + id, ex);
            throw new DatabaseException("Exception occurred when find aircraft by id: " + id, ex);
        }

    }

    @Override
    public Aircraft save(Aircraft aircraft) throws DatabaseException {
        try {
            Aircraft saved = aircraftRepository.save(aircraft);
            log.info("Success saved aircraft with id: " + aircraft.getCode());
            return saved;
        } catch (DataAccessException ex) {
            log.error("Failed save aircraft: " + aircraft, ex);
            throw new DatabaseException("Exception occurred when save aircraft", ex);
        }
    }

    @Override
    public void delete(String aircraftCode) throws DatabaseException {
        try {
            aircraftRepository.deleteById(aircraftCode);
            log.info("Success deleted aircraft by id: " + aircraftCode);
        } catch (DataAccessException ex) {
            log.error("Failed delete aircraft by id: " + aircraftCode, ex);
            throw new DatabaseException("Exception occurred when deleting an aircraft by id: " + aircraftCode, ex);
        }
    }

    @Override
    public List<Seat> getSeatsByAircraftCodeAndFareCondition(String aircraftCode, FareConditions conditions) {
        Optional<Aircraft> optionalAircraft = findById(aircraftCode);

        if (optionalAircraft.isEmpty()) {
            log.info("Did not find aircraft with id: " + aircraftCode);
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
            log.info("Did not find aircraft with id: " + aircraftCode);
            return Map.of();
        }

        Aircraft aircraft = optionalAircraft.get();
        return aircraft.getSeats()
                .stream()
                .collect(Collectors.groupingBy(Seat::getFareConditions,
                        Collectors.mapping(Seat::getSeatNo, Collectors.toList())));
    }

}

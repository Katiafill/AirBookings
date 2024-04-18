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
            throw new DatabaseException("Exception occurred when find all aircrafts", ex);
        }
    }

    @Override
    public Optional<Aircraft> findById(String id) throws DatabaseException {
        try {
            return aircraftRepository.findById(id);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Exception occurred when find aircraft by id: " + id, ex);
        }

    }

    @Override
    public Aircraft save(Aircraft aircraft) throws DatabaseException {
        try {
            Aircraft saved = aircraftRepository.save(aircraft);
            log.info("Success saved aircraft with id: {}", aircraft.getCode());
            return saved;
        } catch (DataAccessException ex) {
            throw new DatabaseException("Exception occurred when save aircraft", ex);
        }
    }

    @Override
    public void delete(String aircraftCode) throws DatabaseException {
        try {
            aircraftRepository.deleteById(aircraftCode);
            log.info("Success deleted aircraft by id: {}", aircraftCode);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Exception occurred when deleting an aircraft by id: " + aircraftCode, ex);
        }
    }

    @Override
    public List<Seat> getAllSeats(String aircraftCode) throws DatabaseException {
        try {
            return aircraftRepository.findAllSeats(aircraftCode);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Exception occurred when find all seats for aircraft: " + aircraftCode, ex);
        }
    }

    @Override
    public List<Seat> getSeatsByFareConditions(String aircraftCode, FareConditions conditions) throws DatabaseException {
        try {
            return aircraftRepository.findSeatsByFareConditions(aircraftCode, conditions);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Exception occurred when find all seats for aircraft: " + aircraftCode + ", conditions: " + conditions, ex);
        }
    }

    @Override
    public Map<FareConditions, List<String>> getGroupedSeats(String aircraftCode) throws DatabaseException {
        List<Seat> seats = getAllSeats(aircraftCode);

        return seats.stream()
                .collect(Collectors.groupingBy(Seat::getFareConditions,
                        Collectors.mapping(Seat::getSeatNo, Collectors.toList())));
    }

}

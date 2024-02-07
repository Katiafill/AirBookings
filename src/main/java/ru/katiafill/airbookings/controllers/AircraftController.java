package ru.katiafill.airbookings.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.katiafill.airbookings.models.Aircraft;
import ru.katiafill.airbookings.models.Airport;
import ru.katiafill.airbookings.repositories.AircraftRepository;
import ru.katiafill.airbookings.repositories.AirportRepository;

@RestController()
@AllArgsConstructor
public class AircraftController {
    private final AircraftRepository repository;
    private final AirportRepository airportRepository;

    @GetMapping("/aircraft")
    public Iterable<Aircraft> getAircrafts() {
        return repository.findAll();
    }

    @GetMapping("/airport")
    public Iterable<Airport> getAirports() {
        return airportRepository.findAll();
    }
}

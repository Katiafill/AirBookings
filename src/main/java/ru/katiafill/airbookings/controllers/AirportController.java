package ru.katiafill.airbookings.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.katiafill.airbookings.models.Airport;
import ru.katiafill.airbookings.repositories.AirportRepository;

@AllArgsConstructor
@RestController
public class AirportController {
    private final AirportRepository airportRepository;

    @GetMapping("/airport")
    public Iterable<Airport> getAirports() {
        return airportRepository.findAll();
    }
}

package ru.katiafill.airbookings.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.katiafill.airbookings.models.Aircraft;
import ru.katiafill.airbookings.models.FareConditions;
import ru.katiafill.airbookings.models.Seat;
import ru.katiafill.airbookings.services.AircraftService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class AircraftController {
    private final AircraftService service;

    @GetMapping("/aircraft")
    public List<Aircraft> getAircrafts() {
        return service.findAll();
    }

    @GetMapping("/aircraft/{id}")
    public Optional<Aircraft> getAircraftById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/aircraft/{id}/{conditions}")
    public List<Seat> getSeatsByAircraft(@PathVariable String id,
                                         @PathVariable FareConditions conditions) {
        return service.getSeatsByAircraftCodeAndFareCondition(id, conditions);
    }
}

package ru.katiafill.airbookings.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.katiafill.airbookings.models.Aircraft;
import ru.katiafill.airbookings.repositories.AircraftRepository;

@RestController()
@AllArgsConstructor
public class AircraftController {
    private final AircraftRepository repository;

    @GetMapping("/aircraft")
    public Iterable<Aircraft> getAircrafts() {
        return repository.findAll();
    }
}

package ru.katiafill.airbookings.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/aircraft/{id}/seats")
    public List<Seat> getSeatsByAircraft(@PathVariable String id,
                                         @RequestParam(required = false) FareConditions conditions) {
        if (conditions == null) {
            return service.getAllSeats(id);
        } else {
            return service.getSeatsByFareConditions(id, conditions);
        }
    }

    @PostMapping("/aircraft")
    @ResponseBody
    public Aircraft addAircraft(@RequestBody Aircraft aircraft) {
        return service.save(aircraft);
    }

    @DeleteMapping("/aircraft/{id}")
    public void deleteAircraft(@PathVariable String id) {
        service.delete(id);
    }

}

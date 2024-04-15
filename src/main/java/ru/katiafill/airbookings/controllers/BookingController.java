package ru.katiafill.airbookings.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.katiafill.airbookings.models.Booking;
import ru.katiafill.airbookings.repositories.BookingsRepository;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class BookingController {
    private final static int PAGE_SIZE = 10;
    private final BookingsRepository bookingsRepository;

    @GetMapping("/bookings")
    public Iterable<Booking> getBookings(@RequestParam(defaultValue = "0") int page) {
        return bookingsRepository.findAll(PageRequest.of(page, PAGE_SIZE));
    }

    @GetMapping("/booking/{id}")
    public Optional<Booking> findById(@PathVariable String id) {
        return bookingsRepository.findById(id);
    }
}

package ru.katiafill.airbookings.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.katiafill.airbookings.models.Booking;

public interface BookingsRepository extends CrudRepository<Booking, String> {
}

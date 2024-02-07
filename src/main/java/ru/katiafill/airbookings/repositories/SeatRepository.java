package ru.katiafill.airbookings.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.katiafill.airbookings.models.Seat;

public interface SeatRepository extends CrudRepository<Seat, Seat.SeatPK> {
}

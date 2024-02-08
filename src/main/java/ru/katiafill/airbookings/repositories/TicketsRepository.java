package ru.katiafill.airbookings.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.katiafill.airbookings.models.Ticket;

public interface TicketsRepository extends CrudRepository<Ticket, String> {
}

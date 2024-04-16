package ru.katiafill.airbookings.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.katiafill.airbookings.models.Ticket;
import java.util.List;

@Repository
public interface TicketsRepository extends CrudRepository<Ticket, String> {
    List<Ticket> findByBookNo(String bookNo);
}

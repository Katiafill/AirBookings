package ru.katiafill.airbookings.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.katiafill.airbookings.models.Ticket;
import ru.katiafill.airbookings.models.TicketFlights;

import java.util.List;

@Repository
public interface TicketsRepository extends CrudRepository<Ticket, String> {
    List<Ticket> findByBookNo(String bookNo);
    List<Ticket> findByPassengerId(String passengerId);

    @Query(value = "SELECT tf FROM TicketFlights tf WHERE tf.ticketNo = :ticketNo")
    List<TicketFlights> findTicketFlightsByTicketNo(@Param("ticketNo") String ticketNo);
}

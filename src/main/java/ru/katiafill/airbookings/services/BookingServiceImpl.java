package ru.katiafill.airbookings.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.katiafill.airbookings.exception.DatabaseException;
import ru.katiafill.airbookings.models.Ticket;
import ru.katiafill.airbookings.repositories.BookingsRepository;
import ru.katiafill.airbookings.repositories.TicketsRepository;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingsRepository bookingsRepository;
    private final TicketsRepository ticketsRepository;

    @Override
    public List<Ticket> getTicketsByBookNo(String bookNo) {
        try {
            return ticketsRepository.findByBookNo(bookNo);
        } catch (DataAccessException ex) {
            log.error("Failed find tickets by bookNo", ex);
            throw new DatabaseException("Exception occurred when find tickets by bookNo", ex);
        }
    }
}

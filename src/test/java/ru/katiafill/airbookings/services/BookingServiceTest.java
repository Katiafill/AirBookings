package ru.katiafill.airbookings.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.katiafill.airbookings.models.Booking;
import ru.katiafill.airbookings.models.Ticket;
import ru.katiafill.airbookings.repositories.BookingsRepository;
import ru.katiafill.airbookings.repositories.TicketsRepository;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookingServiceTest {

    @Autowired
    private BookingService service;

    @MockBean
    private TicketsRepository ticketsRepository;

    @MockBean
    private BookingsRepository bookingsRepository;

    private Booking booking;
    private Ticket ticket;

    @BeforeEach
    void setUp() {
        booking = Booking.builder()
                .bookNo("000001")
                .totalAmount(BigDecimal.valueOf(1400.00))
                .bookDate(ZonedDateTime.now())
                .build();

        ticket = Ticket.builder()
                .bookNo(booking.getBookNo())
                .ticketNo("0005432527326")
                .passengerId("9091 269355")
                .passengerName("TAMARA ZAYCEVA")
                .contactData("{\"email\":\"tamarazayceva-1971@postgrespro.ru\"}")
                .build();
    }

    @Test
    void getTicketsByBookNo() {
        when(ticketsRepository.findByBookNo(any())).thenReturn(List.of(ticket));

        List<Ticket> tickets = service.getTicketsByBookNo(booking.getBookNo());

        assertEquals(tickets.size(), 1);
        Ticket ticket1 = tickets.get(0);
        assertEquals(ticket1, ticket);
    }
}
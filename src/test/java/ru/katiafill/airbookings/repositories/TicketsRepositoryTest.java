package ru.katiafill.airbookings.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.katiafill.airbookings.models.BoardingPass;
import ru.katiafill.airbookings.models.FareConditions;
import ru.katiafill.airbookings.models.Ticket;
import ru.katiafill.airbookings.models.TicketFlights;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class TicketsRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TicketsRepository repository;

    private Ticket ticket;
    private TicketFlights ticketFlight;
    private BoardingPass boardingPass;

    @BeforeEach
    void setUp() {
        ticket = Ticket.builder()
                .ticketNo("0005432527326")
                .bookNo("000012")
                .passengerId("9091 269355")
                .passengerName("TAMARA ZAYCEVA")
                .contactData("{\"email\":\"tamarazayceva-1971@postgrespro.ru\"}")
                .build();

        ticketFlight = TicketFlights.builder()
                .ticketNo(ticket.getTicketNo())
                .flightId(15386L)
                .amount(BigDecimal.valueOf(14000.00))
                .fareConditions(FareConditions.Economy)
                .build();

        boardingPass = BoardingPass.builder()
                .ticketNo(ticketFlight.getTicketNo())
                .flightId(ticketFlight.getFlightId())
                .boardingNo(211L)
                .seatNo("28C")
                .build();


        ticketFlight.setBoardingPass(boardingPass);
        ticket.setFlights(List.of(ticketFlight));

        //entityManager.persist(ticketFlight);
        entityManager.persist(ticket);
    }

    @Test
    void findByBookNo() {
        List<Ticket> tickets = repository.findByBookNo(ticket.getBookNo());
        assertEquals(tickets.size(), 1);
        Ticket ticket1 = tickets.get(0);
        assertEquals(ticket1, ticket);
        log.info(ticket1.toString());
    }

    @Test
    void findByPassengerId() {
        List<Ticket> tickets = repository.findByPassengerId(ticket.getPassengerId());
        assertEquals(tickets.size(), 1);
        Ticket ticket1 = tickets.get(0);
        assertEquals(ticket1, ticket);
    }

    @Test
    void findTicketFlightsByTicketNo() {
        List<TicketFlights> ticketFlights = repository.findTicketFlightsByTicketNo(ticket.getTicketNo());
        assertEquals(ticketFlights.size(), 1);
        TicketFlights ticketFlight1 = ticketFlights.get(0);
        assertEquals(ticketFlight1, ticketFlight);
        assertEquals(ticketFlight1.getBoardingPass(), boardingPass);
    }
}
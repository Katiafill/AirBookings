package ru.katiafill.airbookings.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.katiafill.airbookings.models.Ticket;

import static org.junit.jupiter.api.Assertions.*;
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

    @BeforeEach
    void setUp() {
        ticket = Ticket.builder()
                .ticketNo("0005432527326")
                .bookNo("000012")
                .passengerId("9091 269355")
                .passengerName("TAMARA ZAYCEVA")
                .contactData("{\"email\":\"tamarazayceva-1971@postgrespro.ru\"}")
                .build();

        entityManager.persist(ticket);
    }

    @Test
    void findByBookNo() {
        List<Ticket> tickets = repository.findByBookNo(ticket.getBookNo());
        assertEquals(tickets.size(), 1);
        Ticket ticket1 = tickets.get(0);
        assertEquals(ticket1, ticket);
    }
}
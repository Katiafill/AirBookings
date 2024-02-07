package ru.katiafill.airbookings.repositories;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.katiafill.airbookings.models.Seat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class SeatRepositoryTest {

    @Autowired
    private SeatRepository repository;

    @Test
    public void findAll() {
        List<Seat> seats = (List<Seat>) repository.findAll();
        seats.forEach(System.out::println);
    }
}
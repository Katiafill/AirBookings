package ru.katiafill.airbookings.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.katiafill.airbookings.models.Booking;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(
        replace= AutoConfigureTestDatabase.Replace.NONE
)
class BookingsRepositoryTest {
    @Autowired
    private BookingsRepository repository;

    @Test
    public void findById() {
        Optional<Booking> book = repository.findById("DA7166");
        assertTrue(book.isPresent());
        assertNotNull(book.get());
        System.out.println(book.get().toString());
    }
}
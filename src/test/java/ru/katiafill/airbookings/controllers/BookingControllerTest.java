package ru.katiafill.airbookings.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.katiafill.airbookings.models.Booking;
import ru.katiafill.airbookings.repositories.BookingsRepository;
import ru.katiafill.airbookings.services.AircraftService;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BookingController.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class BookingControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookingsRepository repository;

    private static Booking booking;

    @BeforeAll
    static void setUp() {
        booking = Booking.builder()
                .bookNo("123456")
                .bookDate(ZonedDateTime.now())
                .totalAmount(BigDecimal.valueOf(1234.00))
                .build();
    }

    @Test
    void getBookings() throws Exception {
        when(repository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(List.of(booking)));

        mvc.perform(get("/bookings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].bookNo").value(booking.getBookNo()))
                .andExpect(jsonPath("$.content[0].totalAmount").value(booking.getTotalAmount()))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.size").value(1))
                .andReturn();
    }

    @Test
    void findById() throws Exception {
        when(repository.findById(booking.getBookNo())).thenReturn(Optional.of(booking));

        mvc.perform(get("/booking/" + booking.getBookNo())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bookNo").value(booking.getBookNo()))
                .andReturn();
    }
}
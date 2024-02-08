package ru.katiafill.airbookings.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @Column(name = "book_ref", length = 6)
    private String bookNo;

    @Column(name = "book_date")
    private ZonedDateTime bookDate;

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_ref")
    private List<Ticket> tickets;
}

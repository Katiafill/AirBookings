package ru.katiafill.airbookings.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ticket_flights")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(TicketPK.class)
public class TicketFlights {
    @Id
    @Column(name = "ticket_no", length = 13, nullable = false)
    private String ticketNo;

    @Id
    @Column(name = "flight_id", nullable = false)
    private Long flightId;

    @Column(name = "fare_conditions", nullable = false)
    @Enumerated(EnumType.STRING)
    private FareConditions fareConditions;

    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ticket_no"),
            @JoinColumn(name = "flight_id")
    })
    private BoardingPass boardingPass;
}

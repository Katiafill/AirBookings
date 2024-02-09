package ru.katiafill.airbookings.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "boarding_passes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(TicketPK.class)
public class BoardingPass {
    @Id
    @Column(name = "ticket_no", length = 13, nullable = false)
    private String ticketNo;

    @Id
    @Column(name = "flight_id", nullable = false)
    private Long flightId;

    @Column(name = "boarding_no", nullable = false)
    private Long boardingNo;

    @Column(name = "seat_no", nullable = false)
    private String seatNo;
}

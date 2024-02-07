package ru.katiafill.airbookings.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "seats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(Seat.SeatPK.class)
public class Seat {
    @Id
    @Column(name = "aircraft_code", length = 3)
    private String aircraftCode;

    @Id
    @Column(name = "seat_no", length = 4)
    private String seatNo;

    @Column(name = "fare_conditions", length = 10)
    @Enumerated(EnumType.STRING)
    private FareConditions fareConditions;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SeatPK implements Serializable {
        private String aircraftCode;
        private String seatNo;
    }
}

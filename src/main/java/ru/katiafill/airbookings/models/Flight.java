package ru.katiafill.airbookings.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "flights")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @Column(name = "flight_id", nullable = false)
    private Long id;

    @Column(name = "flight_no", length = 6, nullable = false)
    private String flightNo;

    @Column(name = "scheduled_departure", nullable = false)
    private ZonedDateTime scheduledDeparture;

    @Column(name = "scheduled_arrival", nullable = false)
    private ZonedDateTime scheduledArrival;

    @Column(name = "actual_departure", nullable = true)
    private ZonedDateTime actualDeparture;

    @Column(name = "actual_arrival", nullable = true)
    private ZonedDateTime actualArrival;

    @Column(name = "status", nullable = false)
    private FlightStatus status;

    @OneToOne
    @JoinColumn(name = "aircraft_code", nullable = false)
    private Aircraft aircraft;

    @OneToOne
    @JoinColumn(name = "departure_airport", referencedColumnName = "airport_code",  nullable = false)
    private Airport departureAirport;

    @OneToOne
    @JoinColumn(name = "arrival_airport", referencedColumnName = "airport_code", nullable = false)
    private Airport arrivalAirport;
}

package ru.katiafill.airbookings.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.util.List;

@Entity
@Table(name = "routes_v")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name = "list-array", typeClass = IntegerArrayType.class)
public class Route {
    @Id
    @Column(name = "flight_no", nullable = false)
    private String flightNo;

    @OneToOne
    @JoinColumn(name = "departure_airport", referencedColumnName = "airport_code")
    private Airport departureAirport;

    @OneToOne
    @JoinColumn(name = "arrival_airport", referencedColumnName = "airport_code")
    private Airport arrivalAirport;

    @OneToOne
    @JoinColumn(name = "aircraft_code")
    private Aircraft aircraft;

    @Column(name = "departure_time")
    private OffsetTime departureTime;

    @Column(name = "arrival_time")
    private OffsetTime arrivalTime;

    private LocalTime duration;

    @Column(name = "days_of_week", columnDefinition = "integer[]")
    @Type(type = "list-array")
    @Enumerated(EnumType.ORDINAL)
    private List<DayOfWeek> daysOfWeek;
}

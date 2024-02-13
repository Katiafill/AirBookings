package ru.katiafill.airbookings.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@Entity
@Table(name = "aircrafts_data")
@NoArgsConstructor
@AllArgsConstructor
public class Aircraft {
    // Aircraft code, IATA
    @Id
    @Column(name = "aircraft_code", length = 3)
    private String code;

    // Aircraft model
    @Column(nullable = false)
    private LocalizedString model;

    // Maximal flying distance, km
    @Column(nullable = false)
    @Min(1)
    private Integer range;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "aircraft_code")
    @ToString.Exclude
    private List<Seat> seats;
}

package ru.katiafill.airbookings.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "aircrafts_data")
@NoArgsConstructor
@AllArgsConstructor
public class Aircraft {
    // Aircraft code, IATA
    @Id
    @Column(name = "aircraft_code")
    @Size(min = 1, max = 3)
    private String code;

    // Aircraft model
    @NotNull
    private String model;

    // Maximal flying distance, km
    @NotNull
    @Min(1)
    private Integer range;
}

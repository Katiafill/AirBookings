package ru.katiafill.airbookings.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Column(name = "aircraft_code", length = 3)
    private String code;

    // Aircraft model
    @NotNull
    @Convert(converter = LocalizedStringAttributeConverter.class)
    private LocalizedString model;

    // Maximal flying distance, km
    @NotNull
    @Min(1)
    private Integer range;
}

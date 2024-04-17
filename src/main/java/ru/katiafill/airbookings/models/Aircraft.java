package ru.katiafill.airbookings.models;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "aircrafts_data")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Aircraft {
    // Aircraft code, IATA
    @Id
    @Column(name = "aircraft_code", length = 3, nullable = false)
    private String code;

    // Aircraft model
    @Column(nullable = false, columnDefinition = "jsonb", unique = true)
    @Type(type = "jsonb")
    private LocalizedString model;

    // Maximal flying distance, km
    @Column(nullable = false)
    @Min(1)
    private Integer range;
}

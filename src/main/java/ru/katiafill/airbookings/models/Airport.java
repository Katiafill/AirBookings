package ru.katiafill.airbookings.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.TypeDef;

import java.time.ZoneId;
import java.util.TimeZone;

@Entity
@Table(name = "airports_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name = "point", typeClass = PointType.class)
public class Airport {
    @Id
    @Column(name = "airport_code", length = 3, nullable = false)
    private String code;

    @Column(name = "airport_name", nullable = false)
    private LocalizedString name;

    @Column(nullable = false)
    private LocalizedString city;

    @Column(nullable = false)
    @Type(type="point")
    private Point coordinates;

    @Column(nullable = false)
    private String timezone;
}

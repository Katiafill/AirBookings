package ru.katiafill.airbookings.models;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import org.hibernate.annotations.TypeDef;

@Entity
@Table(name = "airports_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@TypeDef(name = "point", typeClass = PointType.class)
public class Airport {
    @Id
    @Column(name = "airport_code", length = 3, nullable = false)
    private String code;

    @Column(name = "airport_name", nullable = false)
    @Type(type = "jsonb")
    private LocalizedString name;

    @Column(nullable = false)
    @Type(type = "jsonb")
    private LocalizedString city;

    @Column(nullable = false)
    @Type(type="point")
    private Point coordinates;

    @Column(nullable = false)
    private String timezone;
}

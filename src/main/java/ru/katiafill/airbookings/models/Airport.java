package ru.katiafill.airbookings.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.TypeDef;

@Entity
@Table(name = "airports_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name = "point", typeClass = PointType.class)
public class Airport {
    @Id
    @Column(name = "airport_code", length = 3)
    private String code;

    @NotNull
    @Column(name = "airport_name")
    @Convert(converter = LocalizedStringAttributeConverter.class)
    private LocalizedString name;

    @NotNull
    @Convert(converter = LocalizedStringAttributeConverter.class)
    private LocalizedString city;

    @NotNull
    @Type(type="point")
    private Point coordinates;

    @NotNull
    private String timezone;
}

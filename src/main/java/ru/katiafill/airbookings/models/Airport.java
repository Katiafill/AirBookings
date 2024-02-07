package ru.katiafill.airbookings.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.TypeDef;
import org.postgresql.geometric.PGpoint;

@Entity
@Table(name = "airports_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name = "pgpoint", typeClass = PGpointType.class)
public class Airport {
    @Id
    @Column(name = "airport_code", length = 3)
    private String code;

    @NotNull
    @Column(name = "airport_name")
    private String name;

    @NotNull
    private String city;

    @NotNull
    @Type(type="pgpoint")
    private PGpoint coordinates;

    @NotNull
    private String timezone;
}

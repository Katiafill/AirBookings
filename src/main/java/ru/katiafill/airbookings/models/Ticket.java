package ru.katiafill.airbookings.models;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Ticket {
    @Id
    @Column(name = "ticket_no", length = 13, nullable = false)
    private String ticketNo;

    @Column(name = "passenger_id", length = 20, nullable = false)
    private String passengerId;

    @Column(name = "passenger_name", nullable = false)
    private String passengerName;

    @Type(type = "jsonb")
    @Column(name = "contact_data", columnDefinition = "jsonb")
    private String contactData;

    @Column(name = "book_ref", length = 6, nullable = false)
    private String bookNo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_no")
    private List<TicketFlights> flights;
}

package ru.katiafill.airbookings.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @Column(name = "ticket_no", length = 13, nullable = false)
    private String ticketNo;

    @Column(name = "passenger_id", length = 20, nullable = false)
    private String passengerId;

    @Column(name = "passenger_name", nullable = false)
    private String passengerName;

    @Column(name = "contact_data")
    private String contactData;

//    @Column(name = "book_ref")
//    private String bookRef;
}

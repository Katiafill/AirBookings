package ru.katiafill.airbookings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.katiafill.airbookings.models.Flight;
import ru.katiafill.airbookings.models.FlightStatus;

import java.util.List;

@Repository
public interface FlightsRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByFlightNo(String flightNo);
    List<Flight> findByStatus(FlightStatus status);
    @Query(value = "select f from Flight f " +
            "where f.departureAirport.code = :departureAirport " +
            "and f.arrivalAirport.code = :arrivalAirport " +
            "and f.status = 'Scheduled'")
    List<Flight> findScheduledFlights(@Param("departureAirport") String departureAirportCode,
                                      @Param("arrivalAirport") String arrivalAirportCode);


}

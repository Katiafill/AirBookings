package ru.katiafill.airbookings.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.katiafill.airbookings.models.Airport;
import ru.katiafill.airbookings.models.LocalizedString;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

public interface AirportRepository extends CrudRepository<Airport, String> {
    @Query(value = "SELECT * FROM airports_data WHERE city->>'en' = :city OR city->>'ru' = :city", nativeQuery = true)
    public List<Airport> findAllByCity(@Param("city") String city);
    public List<Airport> findAllByTimezone(String timezone);
    @Query(value = "SELECT * FROM airports_data WHERE airport_name->>'en' = :name OR airport_name->>'ru' = :name", nativeQuery = true)
    public Optional<Airport> findByName(@Param("name") String name);

    @Query(value = "SELECT a.city FROM Airport a GROUP BY a.city")
    public List<LocalizedString> findAllCities();
}

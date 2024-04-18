package ru.katiafill.airbookings.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.katiafill.airbookings.models.Booking;

@Repository
public interface BookingsRepository extends PagingAndSortingRepository<Booking, String> {
}

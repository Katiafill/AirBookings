package ru.katiafill.airbookings.services;

import ru.katiafill.airbookings.models.Ticket;
import java.util.List;

public interface BookingService {

    /* Получение всех билетов для заданного заказа.
    * В одном заказе может быть несколько билетов как для одного пассажира,
    * так и для разных.
    * */
    List<Ticket> getTicketsByBookNo(String bookNo);
}

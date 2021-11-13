package com.usa.ciclo3.retociclo3.crudrepository;

import com.usa.ciclo3.retociclo3.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {

    public List<Reservation> findAllByStatus(String status);

    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);

    @Query("SELECT c.client, COUNT(c.client) FROM Reservation AS c group by c.client order by COUNT(c.client)DESC")
    public List<Object[]>countTotalReservationByClient();
}

package TicketFlash.Repository;

import TicketFlash.Model.Event;
import TicketFlash.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEventRepository extends JpaRepository <Event, Long> {
    List<Event> findByNameContainingIgnoreCase(String name);

}

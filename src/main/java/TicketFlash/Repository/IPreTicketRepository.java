package TicketFlash.Repository;

import TicketFlash.Model.PreTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPreTicketRepository extends JpaRepository<PreTicket, Long> {
    public PreTicket findByUuid(String uuid);
}

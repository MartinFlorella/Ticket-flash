package TicketFlash.Repository;

import TicketFlash.Model.Category;
import TicketFlash.Model.CategoryTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryTicketRepository extends JpaRepository<CategoryTicket, Long> {
}

package TicketFlash.Repository;

import TicketFlash.Model.Category;
import TicketFlash.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
}

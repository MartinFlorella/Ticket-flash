package TicketFlash.Service;

import TicketFlash.Model.Event;
import TicketFlash.Model.User;

public interface IUserService {

    public User findById(Long id);
    public User saveUser(User user);
}

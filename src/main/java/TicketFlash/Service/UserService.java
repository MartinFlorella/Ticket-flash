package TicketFlash.Service;

import TicketFlash.Model.User;
import TicketFlash.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    @Autowired
    IUserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User saveUser(User user) {

        return userRepository.save(user);
    }
}

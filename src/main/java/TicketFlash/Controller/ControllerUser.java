package TicketFlash.Controller;

import TicketFlash.Model.User;
import TicketFlash.Repository.IUserRepository;
import TicketFlash.Service.IUserService;
import TicketFlash.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerUser {
    @Autowired
    private UserService userService;

    @PostMapping("/user/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {

        userService.saveUser(user);
        return ResponseEntity.ok("Usuario creado correctamente");
    }
}

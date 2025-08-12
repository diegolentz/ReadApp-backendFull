package ar.com.mandarina.readapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mandarina.readapp.models.User;
import ar.com.mandarina.readapp.services.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

}

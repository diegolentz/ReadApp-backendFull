package ar.com.mandarina.readapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mandarina.readapp.services.LoginService;


@RestController
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public boolean login(@RequestParam("username") String username,@RequestParam("password") String password) {

        return loginService.validateCredentials(username, password);
    }

}

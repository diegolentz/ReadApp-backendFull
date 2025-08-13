package ar.com.mandarina.readapp.controllers;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Long> login(@RequestParam("username") String username,@RequestParam("password") String password) {
    Long userId = loginService.validateCredentials(username, password);
    return ResponseEntity.ok(userId);
    }

}

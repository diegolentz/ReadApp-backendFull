package ar.com.mandarina.readapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mandarina.readapp.dtos.LoginRequest;
import ar.com.mandarina.readapp.exceptions.NotFoundException;
import ar.com.mandarina.readapp.services.LoginService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody LoginRequest request) {
        Long userId = loginService.validateCredentials(request.getUsername(), request.getPassword());
        if (userId == null) {
            throw new NotFoundException("User not found");
        }
        return ResponseEntity.ok(userId);
    }
}
package ar.com.mandarina.readapp.controllers;

import javax.security.auth.login.LoginContext;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mandarina.readapp.dtos.LoginDto;
import ar.com.mandarina.readapp.dtos.LoginRequest;
import ar.com.mandarina.readapp.exceptions.NotFoundException;
import ar.com.mandarina.readapp.services.LoginService;
import ar.com.mandarina.readapp.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class LoginController {
    private final LoginService loginService;
    private final UserService userService;

    public LoginController(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> login(@RequestBody LoginRequest request) {
        Long userId = loginService.validateCredentials(request.getUsername(), request.getPassword());
        LoginDto loginDto = userService.getLoginData(userId);
        return ResponseEntity.ok(loginDto);
    }
}
package ar.com.mandarina.readapp.services;

import org.springframework.stereotype.Service;

import ar.com.mandarina.readapp.Repository.LoginRepository;
import ar.com.mandarina.readapp.exceptions.InvalidCredentialsException;
import ar.com.mandarina.readapp.exceptions.NotFoundException;
import ar.com.mandarina.readapp.models.Profile;

@Service
public class LoginService {
    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public Long validateCredentials(String username, String password) {
        Profile user = loginRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("No se encontro " + username));
        if (!user.getPassword().equals(password)) {
            throw new InvalidCredentialsException();
        }
        return user.getId();
    }
}

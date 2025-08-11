package ar.com.mandarina.readapp.services;

import org.springframework.stereotype.Service;

import ar.com.mandarina.readapp.Repository.LoginRepository;
import ar.com.mandarina.readapp.exceptions.UserNotFoundException;
import ar.com.mandarina.readapp.models.Profile;

@Service
public class LoginService {
    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public boolean validateCredentials(String username, String password) {
        Profile user = loginRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        return user.getPassword().equals(password);
    }
}

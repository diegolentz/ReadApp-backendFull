package ar.com.mandarina.readapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.mandarina.readapp.Repository.UserRepository;
import ar.com.mandarina.readapp.models.User;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}

package ar.com.mandarina.readapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ar.com.mandarina.readapp.Repository.UserRepository;
import ar.com.mandarina.readapp.dtos.UserDto;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

  public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
            .map(user -> new UserDto(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getBirthdate()
            ))
            .collect(Collectors.toList());
    }

}

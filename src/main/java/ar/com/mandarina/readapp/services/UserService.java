package ar.com.mandarina.readapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.mandarina.readapp.Repository.UserRepository;
import ar.com.mandarina.readapp.dtos.LoginDto;
import ar.com.mandarina.readapp.dtos.UserDto;
import ar.com.mandarina.readapp.exceptions.NotFoundException;
import ar.com.mandarina.readapp.models.User;
import jakarta.transaction.Transactional;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // public List<UserDto> getAllUsers() {
  // return userRepository.findAll().stream()
  // .map(user -> new UserDto(
  // user.getId(),
  // user.getName(),
  // user.getLastname(),
  // user.getEmail(),
  // user.getBirthdate()
  // ))
  // .collect(Collectors.toList());
  // }

  public LoginDto getLoginData(Long userId) {
    return userRepository.findById(userId)
        .map(user -> new LoginDto(
            user.getId(),
            user.getName(),
            user.getLastname(),
            user.getImg()))
        .orElseThrow(() -> new NotFoundException("Credenciales incorrectas"));
  }

  public List<User> getUsersByIds(List<Long> usersId) {
    return userRepository.findAllById(usersId);
  }

  public UserDto profileUser(long id) {
    return userRepository.findById(id)
        .map(user -> new UserDto(
            user.getId(),
            user.getName(),
            user.getLastname(),
            user.getProfile().getUsername(),
            user.getEmail(),
            user.getPhone(),
            user.getBirthdate(),
            user.getAdress(),
            user.getMoney(),
            user.getImg()))
        .orElseThrow(() -> new NotFoundException("User not found"));
  }

  @Transactional
  public User getUserById(long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("User not found"));
  }

  @Transactional
  public void updateUser(User user) {
    userRepository.save(user);
  }

  public User getUser(Long id){
    return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
  }

}

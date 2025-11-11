package ar.com.mandarina.readapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mandarina.readapp.dtos.UserDto;
import ar.com.mandarina.readapp.models.ProfileDataUser;
import ar.com.mandarina.readapp.models.User;
import ar.com.mandarina.readapp.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // @GetMapping("/user")
    // public List<UserDto> getUsers() {
    // return userService.getAllUsers();
    // }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") long id) {
        return userService.profileUser(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateUserData(@PathVariable("id") long id, @RequestBody ProfileDataUser userDto) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Actualización parcial: solo pisamos si viene un valor no nulo/útil
        if (userDto.getUsername() != null && !userDto.getUsername().isBlank()) {
            user.getProfile().setUsername(userDto.getUsername());
        }
        if (userDto.getEmail() != null && !userDto.getEmail().isBlank()) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getAdress() != null && !userDto.getAdress().isBlank()) {
            user.setAdress(userDto.getAdress());
        }
        if (userDto.getPhone() != null && !userDto.getPhone().isBlank()) {
            user.setPhone(userDto.getPhone());
        }
        if (userDto.getBirthdate() != null) {
            user.setBirthdate(userDto.getBirthdate());
        }

        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/{id}/money")
    public ResponseEntity<?> postMethodName(@PathVariable("id") long id, @RequestBody Double money) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Double currentMoney = user.getMoney();
        money += currentMoney ;
        user.setMoney(money);
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }
    

}

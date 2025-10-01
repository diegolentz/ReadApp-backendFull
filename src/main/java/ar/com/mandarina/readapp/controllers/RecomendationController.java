package ar.com.mandarina.readapp.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mandarina.readapp.dtos.RecomendationDto;
import ar.com.mandarina.readapp.models.User;
import ar.com.mandarina.readapp.services.RecomendationService;
import ar.com.mandarina.readapp.services.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class RecomendationController {
    private final RecomendationService recomendationService;
    private final UserService userService;

    public RecomendationController(RecomendationService recomendationService, UserService userService) {
        this.recomendationService = recomendationService;
        this.userService = userService;
    }

    @GetMapping("/recomendations")
    public ResponseEntity<List<RecomendationDto>> getAllRecomendations() {
        List<RecomendationDto> recomendations = recomendationService.getAllRecomendations();
        List <Long> usersId = recomendations.stream().map(RecomendationDto::getUserId).distinct().toList();
        List<User> users = userService.getUsersByIds(usersId);
        recomendations.forEach(recomendation -> {
            users.stream()
                .filter(user -> user.getId() == recomendation.getUserId())
                .findFirst()
                .ifPresent(user -> recomendation.setImg(user.getImg()));
        });
        return ResponseEntity.ok(recomendations);
    }
    
}

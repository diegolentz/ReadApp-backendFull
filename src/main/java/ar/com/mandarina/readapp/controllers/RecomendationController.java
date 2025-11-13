package ar.com.mandarina.readapp.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mandarina.readapp.dtos.RecomendationDto;
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
    public Page<RecomendationDto> getAllRecomendations(Pageable pageable) {
        return recomendationService.getAllRecomendations(pageable);
    }
    
    
}

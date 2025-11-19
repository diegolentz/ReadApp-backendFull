package ar.com.mandarina.readapp.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mandarina.readapp.dtos.RecomendationDto;
import ar.com.mandarina.readapp.dtos.ValorateRecomendationDTO;
import ar.com.mandarina.readapp.models.Recomendations;
import ar.com.mandarina.readapp.models.User;
import ar.com.mandarina.readapp.services.RecomendationService;
import ar.com.mandarina.readapp.services.UserService;
import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
        return recomendationService.getAllRecomendations(pageable).map(
                recomendation -> {
                    RecomendationDto dto = new RecomendationDto(recomendation);
                    return dto;
                });
    }

    @GetMapping("/my-recomendations")
    public List<RecomendationDto> getMyRecomendations(@RequestParam(name = "userId") Long id) {
        User user = userService.getUser(id);
        List<Recomendations> recomendations = user.getRecomendations();
        return recomendations.stream().map(
                recomendation -> {
                    RecomendationDto dto = new RecomendationDto(recomendation);
                    return dto;
                }).toList();
    }

    @PostMapping("/recomendations/rate")
    public ResponseEntity<?> postMethodName(@RequestBody ValorateRecomendationDTO data) {
        try {
            Recomendations recomendation = recomendationService.getRecomendationById(data.getRecommendationId());
            recomendation.setValorations(data);
            recomendationService.saveRecomendation(recomendation);
            return ResponseEntity.ok().body("Success");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("An error occurred"+ e.getMessage());
        }
    }

}

package ar.com.mandarina.readapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.mandarina.readapp.Repository.RecomendationsRepository;
import ar.com.mandarina.readapp.dtos.RecomendationDto;
import ar.com.mandarina.readapp.models.Recomendations;

@Service
public class RecomendationService {
    private final RecomendationsRepository recomendationRepository;

    public RecomendationService(RecomendationsRepository recomendationRepository) {
        this.recomendationRepository = recomendationRepository;
    }

    public List<RecomendationDto> getAllRecomendations() {
        List<Recomendations> recomendaciones = recomendationRepository.findAll();
        return recomendaciones.stream()
                .map(RecomendationDto::new) 
                .toList();
    }
    
}
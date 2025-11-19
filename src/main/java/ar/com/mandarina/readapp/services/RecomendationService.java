package ar.com.mandarina.readapp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.com.mandarina.readapp.Repository.RecomendationsRepository;
import ar.com.mandarina.readapp.models.Recomendations;
import jakarta.transaction.Transactional;

@Service
public class RecomendationService {
    private final RecomendationsRepository recomendationRepository;

    public RecomendationService(RecomendationsRepository recomendationRepository) {
        this.recomendationRepository = recomendationRepository;
    }

    public Page<Recomendations> getAllRecomendations(Pageable pageable) {
        return recomendationRepository.findAll(pageable);
    }

    public Recomendations getRecomendationById(Long id) {
        return recomendationRepository.findById(id).orElse(null);
    }

    @Transactional
    public void saveRecomendation(Recomendations recomendation) {
        recomendationRepository.save(recomendation);
    }
    
}
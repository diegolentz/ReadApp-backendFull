package ar.com.mandarina.readapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.mandarina.readapp.Repository.TranslationRepository;
import ar.com.mandarina.readapp.models.Translation;

@Service
public class TranslationService {

    private final TranslationRepository translationRepository;

    public TranslationService(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    public List<Translation> getAllTranslations() {
        return translationRepository.findAll();
    }

}

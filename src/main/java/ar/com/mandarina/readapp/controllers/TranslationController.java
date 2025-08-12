package ar.com.mandarina.readapp.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ar.com.mandarina.readapp.models.Translation;
import ar.com.mandarina.readapp.services.TranslationService;

@Controller
public class TranslationController {

    private final TranslationService translationService;

    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @GetMapping("/translation")
    public List<Translation> getTranslations() {
        return translationService.getAllTranslations();
    }

}

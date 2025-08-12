package ar.com.mandarina.readapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.mandarina.readapp.models.Translation;

public interface TranslationRepository extends JpaRepository<Translation, Long> {

    Translation findByLanguage(String language);
}

package ar.com.mandarina.readapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.mandarina.readapp.models.Translation;

public interface TranslationsRepository extends JpaRepository<Translation, Long> {

    Translation findByLanguage(String language);
}

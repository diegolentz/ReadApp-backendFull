package ar.com.mandarina.readapp.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.mandarina.readapp.models.Recomendations;

public interface RecomendationsRepository extends JpaRepository<Recomendations, Long> {

    @EntityGraph(attributePaths = {"user", "books"})
    Page<Recomendations> findAll(Pageable pageable);



}

package ar.com.mandarina.readapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.mandarina.readapp.models.RecomBook;

public interface RecomBookRepository extends JpaRepository<RecomBook, Long> {

}

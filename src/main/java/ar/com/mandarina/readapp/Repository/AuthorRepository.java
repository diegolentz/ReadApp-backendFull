package ar.com.mandarina.readapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.mandarina.readapp.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}

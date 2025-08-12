package ar.com.mandarina.readapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.mandarina.readapp.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Additional query methods can be defined here if needed

}

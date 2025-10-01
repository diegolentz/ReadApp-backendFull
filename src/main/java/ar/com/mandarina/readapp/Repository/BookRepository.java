package ar.com.mandarina.readapp.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.mandarina.readapp.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    Collection<Book> findByTitleContainingIgnoreCase(String text);

}

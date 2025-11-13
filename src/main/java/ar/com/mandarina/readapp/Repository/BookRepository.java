package ar.com.mandarina.readapp.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import ar.com.mandarina.readapp.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findAll(Pageable pageable);
    Page<Book> findByTitleContainingIgnoreCase(String text, Pageable pageable);
    Page<Book> findByGenere_GenereIgnoreCase(String name, Pageable pageable);

}

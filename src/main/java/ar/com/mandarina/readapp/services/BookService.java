package ar.com.mandarina.readapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.mandarina.readapp.Repository.BookRepository;
import ar.com.mandarina.readapp.models.Book;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

}

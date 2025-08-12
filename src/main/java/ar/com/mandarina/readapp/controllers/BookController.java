package ar.com.mandarina.readapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mandarina.readapp.models.Book;
import ar.com.mandarina.readapp.services.BookService;


@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }
    

}

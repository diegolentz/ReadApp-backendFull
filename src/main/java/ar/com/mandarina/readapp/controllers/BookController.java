package ar.com.mandarina.readapp.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mandarina.readapp.dtos.BookDto;
import ar.com.mandarina.readapp.services.BookService;


@RestController
@CrossOrigin(origins = "http://localhost:5173") 
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    

}

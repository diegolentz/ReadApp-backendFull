package ar.com.mandarina.readapp.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mandarina.readapp.dtos.AuthorDto;
import ar.com.mandarina.readapp.dtos.BookDto;
import ar.com.mandarina.readapp.dtos.TranslationDto;
import ar.com.mandarina.readapp.models.Book;
import ar.com.mandarina.readapp.models.Genere;
import ar.com.mandarina.readapp.models.User;
import ar.com.mandarina.readapp.services.BookService;
import ar.com.mandarina.readapp.services.GenereService;
import ar.com.mandarina.readapp.services.UserService;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final GenereService genereService;
    private final UserService userService;

    public BookController(BookService bookService, GenereService genereService, UserService userService) {
        this.bookService = bookService;
        this.genereService = genereService;
        this.userService = userService;
    }

    @GetMapping("")
    public Page<BookDto> getBooks(Pageable pageable) {
        return bookService.getAllBooks(pageable);
    }

    @GetMapping("/genres")
    public List<String> getGenres() {
        return genereService.getAllGeneres();
    }

    @GetMapping("/search")
    public Page<BookDto> searchBooks(@RequestParam(name = "text") String text, Pageable pageable) {
        return bookService.searchBooks(text, pageable);
    }

    @GetMapping("/genre")
    public Page<BookDto> getGenres(@RequestParam(name = "name", required = true) String name, Pageable pageable) {
        return bookService.findBooksByGenereName(name, pageable);
    }

    @GetMapping("/mybooks/{id}")
    public List<BookDto> getMyBooks(@PathVariable(name = "id") Long id) {
        User user = userService.getUser(id);
        List<Book> books = user.getBooks();

        return books.stream()
                .map(book -> {
                    var authorDto = new AuthorDto(
                            book.getAuthor().getId(),
                            book.getAuthor().getName(),
                            book.getAuthor().getLastName());
                    var translationDtos = book.getTranslations().stream()
                            .map(tr -> new TranslationDto(tr.getId(), tr.getLanguage()))
                            .toList();
                    return new BookDto(
                            book.getId(),
                            book.getTitle(),
                            book.getPages(),
                            book.getImg(),
                            book.getGenere().getGenere(),
                            authorDto,
                            translationDtos,
                            book.getPrice());
                })
                .toList();
    }

    @GetMapping("/mybooks/search")
    public List<BookDto> searchMyBooks(@RequestParam(name = "userId") Long userId,
                                       @RequestParam(name = "text") String text) {
        User user = userService.getUser(userId);
        List<Book> books = user.getBooks();
        
        
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(text.toLowerCase()))
                .map(book -> {
                    var authorDto = new AuthorDto(
                            book.getAuthor().getId(),
                            book.getAuthor().getName(),
                            book.getAuthor().getLastName());
                    var translationDtos = book.getTranslations().stream()
                            .map(tr -> new TranslationDto(tr.getId(), tr.getLanguage()))
                            .toList();
                    return new BookDto(
                            book.getId(),
                            book.getTitle(),
                            book.getPages(),
                            book.getImg(),
                            book.getGenere().getGenere(),
                            authorDto,
                            translationDtos,
                            book.getPrice());
                })
                .toList();
    }
    

}

package ar.com.mandarina.readapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ar.com.mandarina.readapp.Repository.BookRepository;
import ar.com.mandarina.readapp.dtos.AuthorDto;
import ar.com.mandarina.readapp.dtos.BookDto;
import ar.com.mandarina.readapp.dtos.TranslationDto;
import ar.com.mandarina.readapp.exceptions.NotFoundException;
import ar.com.mandarina.readapp.models.Book;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            throw new NotFoundException("No se encontraron libros");
        }
        return books.stream()
            .sorted(java.util.Comparator.comparing(b -> b.getTitle() == null ? "" : b.getTitle(), String.CASE_INSENSITIVE_ORDER))
            .map(book -> {
                AuthorDto authorDto = new AuthorDto(
                    book.getAuthor().getId(),
                    book.getAuthor().getName(),
                    book.getAuthor().getLastName());
                List<TranslationDto> translationDtos = book.getTranslations().stream()
                    .map(tr -> new TranslationDto(tr.getId(), tr.getLanguage()))
                    .collect(Collectors.toList());
                return new BookDto(
                    book.getId(),
                    book.getTitle(),
                    book.getPages(),
                    book.getImg(),
                    authorDto,
                    translationDtos);
            })
            .collect(Collectors.toList());
    }
    public List<BookDto> searchBooks(String text) {
        return bookRepository.findByTitleContainingIgnoreCase(text).stream()
            .map(book -> {
                AuthorDto authorDto = new AuthorDto(
                    book.getAuthor().getId(),
                    book.getAuthor().getName(),
                    book.getAuthor().getLastName());
                List<TranslationDto> translationDtos = book.getTranslations().stream()
                    .map(tr -> new TranslationDto(tr.getId(), tr.getLanguage()))
                    .collect(Collectors.toList());
                return new BookDto(
                    book.getId(),
                    book.getTitle(),
                    book.getPages(),
                    book.getImg(),
                    authorDto,
                    translationDtos);
            })
            .collect(Collectors.toList());
    }

}

package ar.com.mandarina.readapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.com.mandarina.readapp.Repository.BookRepository;
import ar.com.mandarina.readapp.dtos.AuthorDto;
import ar.com.mandarina.readapp.dtos.BookDto;
import ar.com.mandarina.readapp.dtos.TranslationDto;
import ar.com.mandarina.readapp.models.Book;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public Page<BookDto> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(book -> {
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
                book.getGenere().getGenere(),
                authorDto,
                translationDtos,
                book.getPrice()
            );
        });
    }
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
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
                    book.getGenere().getGenere(),
                    authorDto,
                    translationDtos,
                    book.getPrice()
                    );
            })
            .collect(Collectors.toList());
    }
    public Page<BookDto> searchBooks(String text, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCase(text, pageable).map(book -> {
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
                book.getGenere().getGenere(),
                authorDto,
                translationDtos,
                book.getPrice()
            );
        });
    }
    public Page<BookDto> findBooksByGenereName(String name, Pageable pageable) {
        return bookRepository.findByGenere_GenereIgnoreCase(name, pageable).map(book -> {
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
                book.getGenere().getGenere(),
                authorDto,
                translationDtos,
                book.getPrice()
            );
        });
}
    public Book getBookById(long id) {
           return bookRepository.findById(id).orElse(null);
    }
   
}

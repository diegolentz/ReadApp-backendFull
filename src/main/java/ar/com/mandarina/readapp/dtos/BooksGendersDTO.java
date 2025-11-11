package ar.com.mandarina.readapp.dtos;

import java.util.List;

public class BooksGendersDTO {
    private List<BookDto> book;
    private List<String> genere;

    public BooksGendersDTO(List<BookDto> book, List<String> genere) {
        this.book = book;
        this.genere = genere;
    }

    public List<BookDto> getBook() {
        return book;
    }

    public void setBook(List<BookDto> book) {
        this.book = book;
    }

    public List<String> getGenere() {
        return genere;
    }

    public void setGenere(List<String> genere) {
        this.genere = genere;
    }
}

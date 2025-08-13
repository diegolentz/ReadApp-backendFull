package ar.com.mandarina.readapp.dtos;

import java.util.List;

public class BookDto {
   private Long id;
    private String title;
    private Integer pages;
    private AuthorDto author;
    private List<TranslationDto> translations;

    public BookDto() {}

    public BookDto(Long id, String title, Integer pages, AuthorDto author, List<TranslationDto> translations) {
        this.id = id;
        this.title = title;
        this.pages = pages;
        this.author = author;
        this.translations = translations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public List<TranslationDto> getTranslations() {
        return translations;
    }

    public void setTranslations(List<TranslationDto> translations) {
        this.translations = translations;
    }
}
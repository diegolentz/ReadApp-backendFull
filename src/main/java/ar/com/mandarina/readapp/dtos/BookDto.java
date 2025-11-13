package ar.com.mandarina.readapp.dtos;

import java.util.List;

public class BookDto {
    private Long id;
    private String title;
    private Integer pages;
    private String img;
    private String genere;
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    private AuthorDto author;
    private List<TranslationDto> translations;

    public BookDto() {
    }

    public BookDto(Long id, String title, Integer pages, String img, String genere, AuthorDto author,
            List<TranslationDto> translations, Double price) {
        this.id = id;
        this.title = title;
        this.pages = pages;
        this.img = img;
        this.genere = genere;
        this.price = price;
    
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
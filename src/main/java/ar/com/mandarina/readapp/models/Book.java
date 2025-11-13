package ar.com.mandarina.readapp.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genere_id", nullable = false)
    private Genere genere;

    @Column(name = "pages")
    private Integer pages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "books_translations", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "translation_id"))
    private List<Translation> translations;

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private List<Recomendations> recomendations;

    @Column(name = "img")
    private String img;

    @Column(name = "price")
    private Double price;
    
    
    public Book() {
    }
    
    public List<Recomendations> getRecomendations() {
        return recomendations;
    }
    
    public void setRecomendations(List<Recomendations> recomendations) {
        this.recomendations = recomendations;
    }
    
    public String getImg() {
        return img;
    }
    
    public void setImg(String img) {
        this.img = img;
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

    public Genere getGenere() {
        return genere;
    }
    
    public void setGenere(Genere genere) {
        this.genere = genere;
    }
    
    public List<Translation> getTranslations() {
        return translations;
    }
    
    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

    public Integer getPages() {
        return pages;
    }
    
    public void setPages(Integer pages) {
        this.pages = pages;
    }
    
    public Author getAuthor() {
        return author;
    }
    
    public void setAuthor(Author author) {
        this.author = author;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    
}

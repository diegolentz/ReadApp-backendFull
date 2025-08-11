package ar.com.mandarina.readapp.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "pages")
    private Integer pages;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToMany(mappedBy = "booksReaded")
    private List<User> usersReaded;

    @ManyToMany(mappedBy = "booksToRead")
    private List<User> usersToRead;

    @ManyToMany
    @JoinTable(name = "books_translations", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "translation_id"))
    private List<Translation> translations;

    public Book() {
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

    public List<User> getUsersReaded() {
        return usersReaded;
    }

    public void setUsersReaded(List<User> usersReaded) {
        this.usersReaded = usersReaded;
    }

    public List<User> getUsersToRead() {
        return usersToRead;
    }

    public void setUsersToRead(List<User> usersToRead) {
        this.usersToRead = usersToRead;
    }
}

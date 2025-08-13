package ar.com.mandarina.readapp.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "translations")
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String language;

    @ManyToMany(mappedBy = "translations", fetch = FetchType.LAZY)
    private List<Book> books;

    public Translation() {}

    public Translation(String language) {
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
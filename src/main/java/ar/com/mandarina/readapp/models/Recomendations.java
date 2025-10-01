package ar.com.mandarina.readapp.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "recomendations")
public class Recomendations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "recomendation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RecomBook> books;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genere_id", nullable = false)
    private Genere genere;

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

    public String getName() {
        return user.getName();
    }


    public String getLastname() {
        return user.getLastname();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<RecomBook> getBooks() {
        return books;
    }

    public void setBooks(List<RecomBook> books) {
        this.books = books;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }
    public Genere getGenere() {
        return genere;
    }

    public Recomendations() {
    }
}

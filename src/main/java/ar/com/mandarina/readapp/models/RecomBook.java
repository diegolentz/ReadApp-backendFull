package ar.com.mandarina.readapp.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "recom_books")
public class RecomBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recomendation_id", nullable = false)
    private Recomendations recomendation;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column
    private boolean toValorate;

    @Column
    private boolean valorated;

    @OneToMany(mappedBy = "recomBook", cascade = CascadeType.ALL)
    private List<Valoration> valorations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recomendations getRecomendation() {
        return recomendation;
    }

    public void setRecomendation(Recomendations recomendation) {
        this.recomendation = recomendation;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public boolean isToValorate() {
        return toValorate;
    }

    public void setToValorate(boolean toValorate) {
        this.toValorate = toValorate;
    }

    public boolean isValorated() {
        return valorated;
    }

    public void setValorated(boolean valorated) {
        this.valorated = valorated;
    }

    public List<Valoration> getValorations() {
        return valorations;
    }

    public void setValorations(List<Valoration> valorations) {
        this.valorations = valorations;
    }

}

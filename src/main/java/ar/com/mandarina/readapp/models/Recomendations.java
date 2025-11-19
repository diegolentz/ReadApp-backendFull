package ar.com.mandarina.readapp.models;

import java.util.List;

import ar.com.mandarina.readapp.dtos.ValorateRecomendationDTO;
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

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "recommendations", fetch = FetchType.LAZY, cascade = jakarta.persistence.CascadeType.ALL)
    private List<Valoration> valorations;

    @ManyToMany
    @JoinTable(name = "recommendations_books", joinColumns = @JoinColumn(name = "recommendation_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public List<Valoration> getValorations() {
        return valorations;
    }

    public void setValorations(ValorateRecomendationDTO valoration) {
        Valoration newValoration = new Valoration();
        newValoration.setValue(valoration.getRate().intValue());
        newValoration.setUserId(valoration.getUserId());
        newValoration.setDescription(valoration.getComment());
        newValoration.setRecommendations(this);

        this.valorations.add(newValoration);
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

    public Double getRating() {
        return this.valorations.stream()
            .mapToDouble(Valoration::getValue)
            .average()
            .orElse(0.0);
    }

    public Recomendations() {
    }

    public void createValoration(ValorateRecomendationDTO data) {
        Long rate = data.getRate();
        Valoration valoration = new Valoration();
        valoration.setValue(rate.intValue());
        valoration.setUserId(data.getUserId());
        valoration.setDescription(data.getComment());
        this.valorations.add(valoration);

    }
}

package ar.com.mandarina.readapp.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String lastname;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Profile profile;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private LocalDate birthdate;

    @Column
    private String adress;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_books", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Recomendations> recomendations;

    @Column
    private Double money;

    @Column
    private String img;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Recomendations> getRecomendations() {
        return recomendations;
    }

    public void setRecomendations(List<Recomendations> recomendations) {
        this.recomendations = recomendations;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public User() {
    }

    public User(Long id, String name, String lastname, Profile profile, String email, String phone, LocalDate birthdate,
            String adress, List<Book> books, List<Recomendations> recomendations, Double money, String img) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.profile = profile;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.adress = adress;
        this.books = books;
        this.recomendations = recomendations;
        this.money = money;
        this.img = img;
    }

    public List<Book> getFirstBooks(int cant) {
        return books.stream().limit(cant).toList();
    }

    public Recomendations createRecommendation(String titulo, String descripcion, List<Book> books) {
        Recomendations recomendation = new Recomendations();
        recomendation.setTitle(titulo);
        recomendation.setDescription(descripcion);
        recomendation.setUser(this);
        recomendation.setBooks(books);
        this.setRecomendations(recomendations);
        return recomendation;
    }

    public Valoration createValoration(Integer value, String description, Recomendations recomendation) {
        Valoration valoration = new Valoration();
        valoration.setValue(value);
        valoration.setDescription(description);
        valoration.setUserId(this.id);
        valoration.setRecommendations(recomendation);
        return valoration;
    }

    public void sellBook(Long bookId) {
        Book bookToRemove = this.books.stream()
            .filter(book -> book.getId().equals(bookId))
            .findFirst()
            .orElse(null);

        if (bookToRemove != null) {
            Double bookPrice = bookToRemove.getPrice();
            this.money += bookPrice;
            this.books.remove(bookToRemove);
        }
    }

    public void buyBook(Book book) {
            Double bookPrice = book.getPrice();
            if (canBuy(book.getPrice()) && notHaveBook(book.getId())) {
                this.money -= bookPrice;
                this.books.add(book);
            } else {
                throw new IllegalArgumentException("Insufficient funds to buy the book.");
            }
    }

    public boolean canBuy(Double price) {
        boolean sufficientFunds = this.money >= price;
        if (!sufficientFunds) {
            throw new IllegalArgumentException("Insufficient funds to buy the book.");
        }
        return sufficientFunds;
    }

    public boolean notHaveBook(Long bookId) {
        boolean notOwned = this.books.stream().noneMatch(book -> book.getId().equals(bookId));
        if (!notOwned) {
            throw new IllegalArgumentException("User already owns the book.");
        }
        return notOwned;
    
    }

}

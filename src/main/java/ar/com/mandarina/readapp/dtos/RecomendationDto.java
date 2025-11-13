package ar.com.mandarina.readapp.dtos;

import java.util.List;

import ar.com.mandarina.readapp.models.Book;
import ar.com.mandarina.readapp.models.Recomendations;

public class RecomendationDto {

    private long id;
    private String title;
    private long userId;
    private String userName;
    private String userLastname;
    private List<String> books;
    private String img;
    private String genere; // Añadido para el género

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public RecomendationDto(Recomendations recomendation) {
        this.id = recomendation.getId();
        this.title = recomendation.getTitle();
        this.userId = recomendation.getUser().getId();
        this.userName = recomendation.getUser().getName();
        this.userLastname = recomendation.getUser().getLastname();
        this.books = recomendation.getBooks().stream()
                .map(Book::getTitle)
                .toList();
        this.img = recomendation.getUser().getImg();
    }


}

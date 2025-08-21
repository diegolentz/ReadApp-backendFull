package ar.com.mandarina.readapp.dtos;

public class LoginDto {
private Long id;
private String name;
private String lastname;
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

public String getImg() {
    return img;
}

public void setImg(String img) {
    this.img = img;
}

public LoginDto(Long id, String name, String lastname, String img) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.img = img;
}

}

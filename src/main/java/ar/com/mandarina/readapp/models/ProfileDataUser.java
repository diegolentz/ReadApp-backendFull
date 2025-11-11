package ar.com.mandarina.readapp.models;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ProfileDataUser {
    private String username;
    private String email;
    private String adress;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    public ProfileDataUser() {
    }

    public ProfileDataUser(String username, String email, String adress, String phone, LocalDate birthdate) {
        this.username = username;
        this.email = email;
        this.adress = adress;
        this.phone = phone;
        this.birthdate = birthdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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

}

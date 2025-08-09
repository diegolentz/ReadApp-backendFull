package ar.com.mandarina.readapp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.mandarina.readapp.Repository.UserRepository;
import ar.com.mandarina.readapp.models.User;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        createUser();

    }
    
    public void createUser(){
        User diego = new User(
            "Diego",
            "Lentz",
            "diego@gmail.com",
            Date.valueOf("1992-04-19")
        );

        User pedro = new User(
            "Pedro",
            "Geraghty",
            "pedro@mandarina.com",
            Date.valueOf("1965-10-30")
        );

        User matias = new User(
            "Matias",
            "Diaz",
            "matias@mandarina.com",
            Date.valueOf("1990-10-30")
        );

        User adrian = new User(
            "Adrian",
            "Perez",
            "adrian@mandarina.com",
            Date.valueOf("1985-10-30")
        );

        User valentin = new User(
            "Valentin",
            "Pugliese",
            "valentin@mandarina.com",
            Date.valueOf("1995-10-30")
        );

        List<User> users = new ArrayList<>();
        users.add(diego);
        users.add(pedro);
        users.add(matias);
        users.add(adrian);
        users.add(valentin);

        userRepository.saveAll(users);
    }

}

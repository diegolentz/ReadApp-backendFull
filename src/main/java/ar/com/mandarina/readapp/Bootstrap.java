package ar.com.mandarina.readapp;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ar.com.mandarina.readapp.Repository.UserRepository;
import ar.com.mandarina.readapp.models.Profile;
import ar.com.mandarina.readapp.models.User;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        createUsersWithProfiles();
    }

    private void createUsersWithProfiles() {
        User diego = new User("Diego", "Lentz", "diego@gmail.com", Date.valueOf("1992-04-19"));
        diego.setProfile(new Profile("diego", "zxc", diego));

        User pedro = new User("Pedro", "Geraghty", "pedro@mandarina.com", Date.valueOf("1965-10-30"));
        pedro.setProfile(new Profile("pedro", "pedro", pedro));

        User matias = new User("Matias", "Diaz", "matias@mandarina.com", Date.valueOf("1990-10-30"));
        matias.setProfile(new Profile("matias", "matias", matias));

        User adrian = new User("Adrian", "Perez", "adrian@mandarina.com", Date.valueOf("1985-10-30"));
        adrian.setProfile(new Profile("adrian", "adrian", adrian));

        User valentin = new User("Valentin", "Pugliese", "valentin@mandarina.com", Date.valueOf("1995-10-30"));
        valentin.setProfile(new Profile("valentin", "valentin", valentin));

        List<User> users = Arrays.asList(diego, pedro, matias, adrian, valentin);
        userRepository.saveAll(users);
    }
}

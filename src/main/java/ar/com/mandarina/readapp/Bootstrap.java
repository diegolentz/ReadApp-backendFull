package ar.com.mandarina.readapp;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ar.com.mandarina.readapp.Repository.AuthorRepository;
import ar.com.mandarina.readapp.Repository.UserRepository;
import ar.com.mandarina.readapp.Repository.TranslationsRepository;
import ar.com.mandarina.readapp.Repository.BookRepository;
import ar.com.mandarina.readapp.models.Author;
import ar.com.mandarina.readapp.models.Book;
import ar.com.mandarina.readapp.models.Profile;
import ar.com.mandarina.readapp.models.Translation;
import ar.com.mandarina.readapp.models.User;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private TranslationsRepository translationRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        createUsersWithProfiles();
        createTranslations(); // <--- primero creamos los idiomas
        createAuthorsAndBooks(); // <--- después los libros y autores
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

    private void createTranslations() {
        List<String> idiomas = Arrays.asList("Español", "Ingles", "Frances", "Aleman", "Italiano", "Ruso", "Portugues",
                "Chino", "Japones", "Neerlandes");
        List<Translation> traducciones = idiomas.stream()
                .map(Translation::new)
                .collect(Collectors.toList());
        translationRepository.saveAll(traducciones);
    }

    private void createAuthorsAndBooks() {
        // Recuperar los idiomas ya persistidos
        Translation espanol = translationRepository.findByLanguage("Español");
        Translation ingles = translationRepository.findByLanguage("Ingles");
        Translation frances = translationRepository.findByLanguage("Frances");
        Translation aleman = translationRepository.findByLanguage("Aleman");
        Translation italiano = translationRepository.findByLanguage("Italiano");
        Translation ruso = translationRepository.findByLanguage("Ruso");
        Translation portugues = translationRepository.findByLanguage("Portugues");
        Translation chino = translationRepository.findByLanguage("Chino");
        Translation japones = translationRepository.findByLanguage("Japones");
        Translation neerlandes = translationRepository.findByLanguage("Neerlandes");

        // === AUTOR 1: William Shakespeare (Drama) ===
        Author shakespeare = new Author();
        shakespeare.setName("William");
        shakespeare.setLastName("Shakespeare");

        Book sh1 = new Book();
        sh1.setTitle("Hamlet");
        sh1.setPages(288);
        sh1.setAuthor(shakespeare);
        sh1.setTranslations(Arrays.asList(espanol, ingles, frances, aleman, italiano));

        Book sh2 = new Book();
        sh2.setTitle("Othello");
        sh2.setPages(304);
        sh2.setAuthor(shakespeare);
        sh2.setTranslations(Arrays.asList(espanol, ingles, frances, portugues, aleman));

        Book sh3 = new Book();
        sh3.setTitle("King Lear");
        sh3.setPages(272);
        sh3.setAuthor(shakespeare);
        sh3.setTranslations(Arrays.asList(espanol, ingles, frances, ruso, italiano));

        Book sh4 = new Book();
        sh4.setTitle("Macbeth");
        sh4.setPages(256);
        sh4.setAuthor(shakespeare);
        sh4.setTranslations(Arrays.asList(espanol, ingles, frances, japones, aleman));

        Book sh5 = new Book();
        sh5.setTitle("Romeo and Juliet");
        sh5.setPages(240);
        sh5.setAuthor(shakespeare);
        sh5.setTranslations(Arrays.asList(espanol, ingles, frances, italiano, portugues));

        Book sh6 = new Book();
        sh6.setTitle("The Tempest");
        sh6.setPages(208);
        sh6.setAuthor(shakespeare);
        sh6.setTranslations(Arrays.asList(espanol, ingles, frances, aleman, chino));

        shakespeare.setBooks(Arrays.asList(sh1, sh2, sh3, sh4, sh5, sh6));

        // === AUTOR 2: Agatha Christie (Suspenso) ===
        Author christie = new Author();
        christie.setName("Agatha");
        christie.setLastName("Christie");

        Book ac1 = new Book();
        ac1.setTitle("Murder on the Orient Express");
        ac1.setPages(256);
        ac1.setAuthor(christie);
        ac1.setTranslations(Arrays.asList(espanol, ingles, frances, ruso));

        Book ac2 = new Book();
        ac2.setTitle("And Then There Were None");
        ac2.setPages(272);
        ac2.setAuthor(christie);
        ac2.setTranslations(Arrays.asList(espanol, ingles, frances, italiano));

        Book ac3 = new Book();
        ac3.setTitle("The Murder of Roger Ackroyd");
        ac3.setPages(320);
        ac3.setAuthor(christie);
        ac3.setTranslations(Arrays.asList(espanol, ingles, frances));

        Book ac4 = new Book();
        ac4.setTitle("Death on the Nile");
        ac4.setPages(336);
        ac4.setAuthor(christie);
        ac4.setTranslations(Arrays.asList(espanol, ingles, frances, portugues));

        Book ac5 = new Book();
        ac5.setTitle("The ABC Murders");
        ac5.setPages(288);
        ac5.setAuthor(christie);
        ac5.setTranslations(Arrays.asList(espanol, ingles, frances, neerlandes));

        Book ac6 = new Book();
        ac6.setTitle("The Mousetrap");
        ac6.setPages(96);
        ac6.setAuthor(christie);
        ac6.setTranslations(Arrays.asList(espanol, ingles));

        christie.setBooks(Arrays.asList(ac1, ac2, ac3, ac4, ac5, ac6));

        // === AUTOR 3: Stephen King (Terror) ===
        Author king = new Author();
        king.setName("Stephen");
        king.setLastName("King");

        Book sk1 = new Book();
        sk1.setTitle("Carrie");
        sk1.setPages(304);
        sk1.setAuthor(king);
        sk1.setTranslations(Arrays.asList(espanol, ingles, ruso));

        Book sk2 = new Book();
        sk2.setTitle("The Shining");
        sk2.setPages(512);
        sk2.setAuthor(king);
        sk2.setTranslations(Arrays.asList(espanol, ingles, frances, japones));

        Book sk3 = new Book();
        sk3.setTitle("It");
        sk3.setPages(1138);
        sk3.setAuthor(king);
        sk3.setTranslations(Arrays.asList(espanol, ingles, frances, chino));

        Book sk4 = new Book();
        sk4.setTitle("Pet Sematary");
        sk4.setPages(416);
        sk4.setAuthor(king);
        sk4.setTranslations(Arrays.asList(espanol, ingles, frances, aleman));

        Book sk5 = new Book();
        sk5.setTitle("Misery");
        sk5.setPages(368);
        sk5.setAuthor(king);
        sk5.setTranslations(Arrays.asList(espanol, ingles, frances, italiano, portugues));

        Book sk6 = new Book();
        sk6.setTitle("The Stand");
        sk6.setPages(1152);
        sk6.setAuthor(king);
        sk6.setTranslations(Arrays.asList(espanol, ingles, frances, ruso, chino));

        king.setBooks(Arrays.asList(sk1, sk2, sk3, sk4, sk5, sk6));

        // === AUTOR 4: Isaac Asimov (Ficción) ===
        Author asimov = new Author();
        asimov.setName("Isaac");
        asimov.setLastName("Asimov");

        Book ia1 = new Book();
        ia1.setTitle("Foundation");
        ia1.setPages(255);
        ia1.setAuthor(asimov);
        ia1.setTranslations(Arrays.asList(espanol, ingles, frances, ruso));

        Book ia2 = new Book();
        ia2.setTitle("I, Robot");
        ia2.setPages(224);
        ia2.setAuthor(asimov);
        ia2.setTranslations(Arrays.asList(espanol, ingles, frances, aleman, ruso));

        Book ia3 = new Book();
        ia3.setTitle("The Gods Themselves");
        ia3.setPages(288);
        ia3.setAuthor(asimov);
        ia3.setTranslations(Arrays.asList(espanol, ingles, frances, italiano));

        Book ia4 = new Book();
        ia4.setTitle("The Caves of Steel");
        ia4.setPages(270);
        ia4.setAuthor(asimov);
        ia4.setTranslations(Arrays.asList(espanol, ingles, frances, japones));

        Book ia5 = new Book();
        ia5.setTitle("The Naked Sun");
        ia5.setPages(288);
        ia5.setAuthor(asimov);
        ia5.setTranslations(Arrays.asList(espanol, ingles, frances, portugues));

        Book ia6 = new Book();
        ia6.setTitle("Second Foundation");
        ia6.setPages(242);
        ia6.setAuthor(asimov);
        ia6.setTranslations(Arrays.asList(espanol, ingles, frances, chino));

        asimov.setBooks(Arrays.asList(ia1, ia2, ia3, ia4, ia5, ia6));

        // === AUTOR 5: Jules Verne (Aventura) ===
        Author verne = new Author();
        verne.setName("Jules");
        verne.setLastName("Verne");

        Book jv1 = new Book();
        jv1.setTitle("Twenty Thousand Leagues Under the Seas");
        jv1.setPages(416);
        jv1.setAuthor(verne);
        jv1.setTranslations(Arrays.asList(espanol, ingles, frances, aleman));

        Book jv2 = new Book();
        jv2.setTitle("Journey to the Center of the Earth");
        jv2.setPages(320);
        jv2.setAuthor(verne);
        jv2.setTranslations(Arrays.asList(espanol, ingles, frances, italiano));

        Book jv3 = new Book();
        jv3.setTitle("Around the World in Eighty Days");
        jv3.setPages(312);
        jv3.setAuthor(verne);
        jv3.setTranslations(Arrays.asList(espanol, ingles, frances, portugues));

        Book jv4 = new Book();
        jv4.setTitle("The Mysterious Island");
        jv4.setPages(656);
        jv4.setAuthor(verne);
        jv4.setTranslations(Arrays.asList(espanol, ingles, frances, ruso, chino));

        Book jv5 = new Book();
        jv5.setTitle("From the Earth to the Moon");
        jv5.setPages(288);
        jv5.setAuthor(verne);
        jv5.setTranslations(Arrays.asList(espanol, ingles, frances, japones));

        Book jv6 = new Book();
        jv6.setTitle("Michael Strogoff");
        jv6.setPages(384);
        jv6.setAuthor(verne);
        jv6.setTranslations(Arrays.asList(espanol, ingles, frances, neerlandes));

        verne.setBooks(Arrays.asList(jv1, jv2, jv3, jv4, jv5, jv6));

        // GUARDAR todos los autores (y sus libros y traducciones intermedias)
        authorRepository.save(shakespeare);
        authorRepository.save(christie);
        authorRepository.save(king);
        authorRepository.save(asimov);
        authorRepository.save(verne);

    }
}
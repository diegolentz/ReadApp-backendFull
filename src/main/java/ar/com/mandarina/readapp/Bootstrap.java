package ar.com.mandarina.readapp;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ar.com.mandarina.readapp.Repository.AuthorRepository;
import ar.com.mandarina.readapp.Repository.BookRepository;
import ar.com.mandarina.readapp.Repository.RecomendationsRepository;
import ar.com.mandarina.readapp.Repository.RecomBookRepository;
import ar.com.mandarina.readapp.Repository.TranslationRepository;
import ar.com.mandarina.readapp.Repository.UserBookRepository;
import ar.com.mandarina.readapp.Repository.UserRepository;
import ar.com.mandarina.readapp.Repository.ValorationRepository;
import ar.com.mandarina.readapp.models.Author;
import ar.com.mandarina.readapp.models.Book;
import ar.com.mandarina.readapp.models.Profile;
import ar.com.mandarina.readapp.models.RecomBook;
import ar.com.mandarina.readapp.models.Recomendations;
import ar.com.mandarina.readapp.models.Translation;
import ar.com.mandarina.readapp.models.User;
import ar.com.mandarina.readapp.models.UserBook;
import ar.com.mandarina.readapp.models.Valoration;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private TranslationRepository translationRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserBookRepository userBookRepository;

    // Repos adicionales para recomendaciones
    @Autowired
    private RecomendationsRepository recomendationsRepository;
    @Autowired
    private RecomBookRepository recomBookRepository;
    @Autowired
    private ValorationRepository valorationRepository;

    @Override
    public void run(String... args) throws Exception {
        createUsersWithProfiles();
        createTranslations(); // <--- primero creamos los idiomas
        createAuthorsAndBooks(); // <--- después los libros y autores
        usersAddBooks();
        createRecommendations();
        userValuatesAnotherRecommendation();
    }

    private void createUsersWithProfiles() {
        User diego = new User("Diego", "Lentz", "diego@gmail.com", Date.valueOf("1992-04-19"),"https://imgs.search.brave.com/kJXuk64IUlo2fu0HReXcsP_IK_GcwyjuY9hgWQ5gMCg/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9jZG4u/cGl4YWJheS5jb20v/cGhvdG8vMjAyNC8w/Ni8wNi8xNi81My9h/aS1nZW5lcmF0ZWQt/ODgxMzExOV82NDAu/anBn");
        diego.setProfile(new Profile("diego", "zxc", diego));

        User pedro = new User("Pedro", "Geraghty", "pedro@mandarina.com", Date.valueOf("1965-10-30"),"https://imgs.search.brave.com/kJXuk64IUlo2fu0HReXcsP_IK_GcwyjuY9hgWQ5gMCg/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9jZG4u/cGl4YWJheS5jb20v/cGhvdG8vMjAyNC8w/Ni8wNi8xNi81My9h/aS1nZW5lcmF0ZWQt/ODgxMzExOV82NDAu/anBn");
        pedro.setProfile(new Profile("pedro", "pedro", pedro));

        User matias = new User("Matias", "Diaz", "matias@mandarina.com", Date.valueOf("1990-10-30"),"https://imgs.search.brave.com/kJXuk64IUlo2fu0HReXcsP_IK_GcwyjuY9hgWQ5gMCg/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9jZG4u/cGl4YWJheS5jb20v/cGhvdG8vMjAyNC8w/Ni8wNi8xNi81My9h/aS1nZW5lcmF0ZWQt/ODgxMzExOV82NDAu/anBn");
        matias.setProfile(new Profile("matias", "matias", matias));

        User adrian = new User("Adrian", "Perez", "adrian@mandarina.com", Date.valueOf("1985-10-30"),"https://imgs.search.brave.com/kJXuk64IUlo2fu0HReXcsP_IK_GcwyjuY9hgWQ5gMCg/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9jZG4u/cGl4YWJheS5jb20v/cGhvdG8vMjAyNC8w/Ni8wNi8xNi81My9h/aS1nZW5lcmF0ZWQt/ODgxMzExOV82NDAu/anBn");
        adrian.setProfile(new Profile("adrian", "adrian", adrian));

        User valentin = new User("Valentin", "Pugliese", "valentin@mandarina.com", Date.valueOf("1995-10-30"),"https://imgs.search.brave.com/kJXuk64IUlo2fu0HReXcsP_IK_GcwyjuY9hgWQ5gMCg/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9jZG4u/cGl4YWJheS5jb20v/cGhvdG8vMjAyNC8w/Ni8wNi8xNi81My9h/aS1nZW5lcmF0ZWQt/ODgxMzExOV82NDAu/anBn");
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
        sh1.setImg("https://m.media-amazon.com/images/I/71rbyj791AL._SY466_.jpg");

        Book sh2 = new Book();
        sh2.setTitle("Othello");
        sh2.setPages(304);
        sh2.setAuthor(shakespeare);
        sh2.setTranslations(Arrays.asList(espanol, ingles, frances, portugues, aleman));
        sh2.setImg("https://m.media-amazon.com/images/I/51YXwe8en6L._SY445_SX342_.jpg");

        Book sh3 = new Book();
        sh3.setTitle("King Lear");
        sh3.setPages(272);
        sh3.setAuthor(shakespeare);
        sh3.setTranslations(Arrays.asList(espanol, ingles, frances, ruso, italiano));
        sh3.setImg("https://m.media-amazon.com/images/I/41cWH7A2FhL._SY445_SX342_.jpg");

        Book sh4 = new Book();
        sh4.setTitle("Macbeth");
        sh4.setPages(256);
        sh4.setAuthor(shakespeare);
        sh4.setTranslations(Arrays.asList(espanol, ingles, frances, japones, aleman));
        sh4.setImg("https://m.media-amazon.com/images/I/51NmobtO9QL._SY445_SX342_.jpg");

        Book sh5 = new Book();
        sh5.setTitle("Romeo and Juliet");
        sh5.setPages(240);
        sh5.setAuthor(shakespeare);
        sh5.setTranslations(Arrays.asList(espanol, ingles, frances, italiano, portugues));
        sh5.setImg("https://m.media-amazon.com/images/I/41VowPay1ZL._SY445_SX342_.jpg");

        Book sh6 = new Book();
        sh6.setTitle("The Tempest");
        sh6.setPages(208);
        sh6.setAuthor(shakespeare);
        sh6.setTranslations(Arrays.asList(espanol, ingles, frances, aleman, chino));
        sh6.setImg("https://m.media-amazon.com/images/I/41OW1XLfAWL._SX342_SY445_.jpg");

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
        ac1.setImg("https://m.media-amazon.com/images/I/51iFntTFAPL._SY445_SX342_.jpg");

        Book ac2 = new Book();
        ac2.setTitle("And Then There Were None");
        ac2.setPages(272);
        ac2.setAuthor(christie);
        ac2.setTranslations(Arrays.asList(espanol, ingles, frances, italiano));
        ac2.setImg("https://m.media-amazon.com/images/I/41rFnXkCvAL._SY466_.jpg");

        Book ac3 = new Book();
        ac3.setTitle("The Murder of Roger Ackroyd");
        ac3.setPages(320);
        ac3.setAuthor(christie);
        ac3.setTranslations(Arrays.asList(espanol, ingles, frances));
        ac3.setImg("https://m.media-amazon.com/images/I/61fi4ooNSNL._AC_UY218_.jpg");

        Book ac4 = new Book();
        ac4.setTitle("Death on the Nile");
        ac4.setPages(336);
        ac4.setAuthor(christie);
        ac4.setTranslations(Arrays.asList(espanol, ingles, frances, portugues));
        ac4.setImg("https://m.media-amazon.com/images/I/71dQeKBE8PL._AC_UY218_.jpg");

        Book ac5 = new Book();
        ac5.setTitle("The ABC Murders");
        ac5.setPages(288);
        ac5.setAuthor(christie);
        ac5.setTranslations(Arrays.asList(espanol, ingles, frances, neerlandes));
        ac5.setImg("https://m.media-amazon.com/images/I/61oAKfwmrhL._AC_UY218_.jpg");

        Book ac6 = new Book();
        ac6.setTitle("The Mousetrap");
        ac6.setPages(96);
        ac6.setAuthor(christie);
        ac6.setTranslations(Arrays.asList(espanol, ingles));
        ac6.setImg("https://m.media-amazon.com/images/I/81HG8JrAmGL._AC_UY218_.jpg");

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
        sk1.setImg("https://m.media-amazon.com/images/I/A1r32P5ztQL._AC_UY218_.jpg");

        Book sk2 = new Book();
        sk2.setTitle("The Shining");
        sk2.setPages(512);
        sk2.setAuthor(king);
        sk2.setTranslations(Arrays.asList(espanol, ingles, frances, japones));
        sk2.setImg("https://m.media-amazon.com/images/I/71Og9BY-IOL._AC_UY218_.jpg");

        Book sk3 = new Book();
        sk3.setTitle("It");
        sk3.setPages(1138);
        sk3.setAuthor(king);
        sk3.setTranslations(Arrays.asList(espanol, ingles, frances, chino));
        sk3.setImg("https://m.media-amazon.com/images/I/71oBjdOSkHL._AC_UY218_.jpg");

        Book sk4 = new Book();
        sk4.setTitle("Pet Sematary");
        sk4.setPages(416);
        sk4.setAuthor(king);
        sk4.setTranslations(Arrays.asList(espanol, ingles, frances, aleman));
        sk4.setImg("https://m.media-amazon.com/images/I/91IzEtlVjdL._AC_UY218_.jpg");

        Book sk5 = new Book();
        sk5.setTitle("Misery");
        sk5.setPages(368);
        sk5.setAuthor(king);
        sk5.setTranslations(Arrays.asList(espanol, ingles, frances, italiano, portugues));
        sk5.setImg("https://m.media-amazon.com/images/I/71UV2D2aD6L._AC_UY218_.jpg");

        Book sk6 = new Book();
        sk6.setTitle("The Stand");
        sk6.setPages(1152);
        sk6.setAuthor(king);
        sk6.setTranslations(Arrays.asList(espanol, ingles, frances, ruso, chino));
        sk6.setImg("https://m.media-amazon.com/images/I/81xZcMvbTwL._AC_UY218_.jpg");

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
        ia1.setImg("https://m.media-amazon.com/images/I/81yHpH4q6IL._AC_UY218_.jpg");

        Book ia2 = new Book();
        ia2.setTitle("I, Robot");
        ia2.setPages(224);
        ia2.setAuthor(asimov);
        ia2.setTranslations(Arrays.asList(espanol, ingles, frances, aleman, ruso));
        ia2.setImg("https://m.media-amazon.com/images/I/81I5sU1KAbL._AC_UY218_.jpg");

        Book ia3 = new Book();
        ia3.setTitle("The Gods Themselves");
        ia3.setPages(288);
        ia3.setAuthor(asimov);
        ia3.setTranslations(Arrays.asList(espanol, ingles, frances, italiano));
        ia3.setImg("https://m.media-amazon.com/images/I/71oFiob1xsL._AC_UY218_.jpg");

        Book ia4 = new Book();
        ia4.setTitle("The Caves of Steel");
        ia4.setPages(270);
        ia4.setAuthor(asimov);
        ia4.setTranslations(Arrays.asList(espanol, ingles, frances, japones));
        ia4.setImg("https://m.media-amazon.com/images/I/41102eHAs8L._AC_UY218_.jpg");

        Book ia5 = new Book();
        ia5.setTitle("The Naked Sun");
        ia5.setPages(288);
        ia5.setAuthor(asimov);
        ia5.setTranslations(Arrays.asList(espanol, ingles, frances, portugues));
        ia5.setImg("https://m.media-amazon.com/images/I/61byaUYPalL._AC_UY218_.jpg");

        Book ia6 = new Book();
        ia6.setTitle("Second Foundation");
        ia6.setPages(242);
        ia6.setAuthor(asimov);
        ia6.setTranslations(Arrays.asList(espanol, ingles, frances, chino));
        ia6.setImg("https://m.media-amazon.com/images/I/A1XlxEPz8zL._AC_UY218_.jpg");

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
        jv1.setImg("https://m.media-amazon.com/images/I/9197kRM1c-L._AC_UY218_.jpg");

        Book jv2 = new Book();
        jv2.setTitle("Journey to the Center of the Earth");
        jv2.setPages(320);
        jv2.setAuthor(verne);
        jv2.setTranslations(Arrays.asList(espanol, ingles, frances, italiano));
        jv2.setImg("https://m.media-amazon.com/images/I/912HUortCAL._AC_UY218_.jpg");

        Book jv3 = new Book();
        jv3.setTitle("Around the World in Eighty Days");
        jv3.setPages(312);
        jv3.setAuthor(verne);
        jv3.setTranslations(Arrays.asList(espanol, ingles, frances, portugues));
        jv3.setImg("https://m.media-amazon.com/images/I/71xihjG35SL._AC_UY218_.jpg");

        Book jv4 = new Book();
        jv4.setTitle("The Mysterious Island");
        jv4.setPages(656);
        jv4.setAuthor(verne);
        jv4.setTranslations(Arrays.asList(espanol, ingles, frances, ruso, chino));
        jv4.setImg("https://m.media-amazon.com/images/I/81MNM2cXFHL._AC_UY218_.jpg");

        Book jv5 = new Book();
        jv5.setTitle("From the Earth to the Moon");
        jv5.setPages(288);
        jv5.setAuthor(verne);
        jv5.setTranslations(Arrays.asList(espanol, ingles, frances, japones));
        jv5.setImg("https://m.media-amazon.com/images/I/81hffOMqe-L._AC_UY218_.jpg");

        Book jv6 = new Book();
        jv6.setTitle("Michael Strogoff");
        jv6.setPages(384);
        jv6.setAuthor(verne);
        jv6.setTranslations(Arrays.asList(espanol, ingles, frances, neerlandes));
        jv6.setImg("https://m.media-amazon.com/images/I/51tH-xbDUzL._AC_UY218_.jpg");

        verne.setBooks(Arrays.asList(jv1, jv2, jv3, jv4, jv5, jv6));

        // GUARDAR todos los autores (y sus libros y traducciones intermedias)
        authorRepository.save(shakespeare);
        authorRepository.save(christie);
        authorRepository.save(king);
        authorRepository.save(asimov);
        authorRepository.save(verne);

    }

    private void usersAddBooks() {
        // Recuperar los usuarios
        User uno = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));
        User dos = userRepository.findById(2L).orElseThrow(() -> new RuntimeException("User not found"));
        User tres = userRepository.findById(3L).orElseThrow(() -> new RuntimeException("User not found"));
        User cuatro = userRepository.findById(4L).orElseThrow(() -> new RuntimeException("User not found"));
        User cinco = userRepository.findById(5L).orElseThrow(() -> new RuntimeException("User not found"));

        // Recuperar los libros
        Book libro1 = bookRepository.findById(1L).orElseThrow(() -> new RuntimeException("Book not found"));
        Book libro2 = bookRepository.findById(2L).orElseThrow(() -> new RuntimeException("Book not found"));
        Book libro3 = bookRepository.findById(3L).orElseThrow(() -> new RuntimeException("Book not found"));
        Book libro4 = bookRepository.findById(4L).orElseThrow(() -> new RuntimeException("Book not found"));
        Book libro5 = bookRepository.findById(5L).orElseThrow(() -> new RuntimeException("Book not found"));

        // Asignar libros a usuarios
        UserBook userBook1 = new UserBook();
        userBook1.setUser(uno);
        userBook1.setBook(libro1);
        userBook1.setIsReaded(true);
        userBook1.setIsToRead(false);

        UserBook userBook2 = new UserBook();
        userBook2.setUser(uno);
        userBook2.setBook(libro2);
        userBook2.setIsReaded(false);
        userBook2.setIsToRead(true);

        UserBook userBook3 = new UserBook();
        userBook3.setUser(dos);
        userBook3.setBook(libro3);
        userBook3.setIsReaded(true);
        userBook3.setIsToRead(false);

        UserBook userBook4 = new UserBook();
        userBook4.setUser(tres);
        userBook4.setBook(libro4);
        userBook4.setIsReaded(false);
        userBook4.setIsToRead(true);

        UserBook userBook5 = new UserBook();
        userBook5.setUser(cuatro);
        userBook5.setBook(libro5);
        userBook5.setIsReaded(true);
        userBook5.setIsToRead(false);

        userBookRepository.saveAll(Arrays.asList(userBook1, userBook2, userBook3, userBook4, userBook5));
    }

    private void createRecommendations() {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            // Buscar libros leídos por el usuario
            List<UserBook> readBooks = user.getUserBooks().stream()
                    .filter(UserBook::getIsReaded)
                    .collect(Collectors.toList());

            if (readBooks.isEmpty())
                continue; // Salta usuarios sin libros leídos

            // Crear la recomendación
            Recomendations recomendation = new Recomendations();
            recomendation.setName("Recomendación de " + user.getName());
            recomendation.setUser(user);

            recomendationsRepository.save(recomendation);

            // Toma el primer libro leído (o más, si querés cambiar el límite)
            List<RecomBook> recomBooks = readBooks.stream()
                    .limit(1)
                    .map(userBook -> {
                        RecomBook rb = new RecomBook();
                        rb.setRecomendation(recomendation);
                        rb.setBook(userBook.getBook());
                        rb.setToValorate(false);
                        rb.setValorated(false);
                        return rb;
                    }).collect(Collectors.toList());

            recomBookRepository.saveAll(recomBooks);

            // Asignar la lista de RecomBook a la recomendación (opcional)
            recomendation.setBooks(recomBooks);
            recomendationsRepository.save(recomendation);
        }
    }

    // === NUEVO: Un usuario valora la recomendación de otro usuario ===
    private void userValuatesAnotherRecommendation() {
        List<Recomendations> allRecs = recomendationsRepository.findAll();

        for (Recomendations rec : allRecs) {
            User creator = rec.getUser();
            // Buscar un usuario diferente al creador para hacer la valoración
            User anotherUser = userRepository.findAll().stream()
                    .filter(u -> !u.getId().equals(creator.getId()))
                    .findFirst().orElse(null);

            if (anotherUser == null)
                continue;

            // Valorar el primer RecomBook de la recomendación (puedes recorrer todos si
            // quieres)
            for (RecomBook rb : rec.getBooks()) {
                Valoration val = new Valoration();
                val.setRecomBook(rb);
                val.setUser(anotherUser);
                val.setValue(5); // Valor fijo, puedes randomizarlo
                val.setDescription("¡Excelente selección!");
                valorationRepository.save(val);
            }
        }
    }
}
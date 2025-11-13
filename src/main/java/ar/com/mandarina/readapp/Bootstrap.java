package ar.com.mandarina.readapp;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ar.com.mandarina.readapp.Repository.AuthorRepository;
import ar.com.mandarina.readapp.Repository.BookRepository;
import ar.com.mandarina.readapp.Repository.GenereRepository;
import ar.com.mandarina.readapp.Repository.RecomendationsRepository;
import ar.com.mandarina.readapp.Repository.TranslationRepository;
import ar.com.mandarina.readapp.Repository.UserRepository;
import ar.com.mandarina.readapp.Repository.ValorationRepository;
import ar.com.mandarina.readapp.models.Author;
import ar.com.mandarina.readapp.models.Book;
import ar.com.mandarina.readapp.models.Genere;
import ar.com.mandarina.readapp.models.Profile;
import ar.com.mandarina.readapp.models.Recomendations;
import ar.com.mandarina.readapp.models.Translation;
import ar.com.mandarina.readapp.models.User;
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
        private GenereRepository genereRepository;

        // Repos adicionales para recomendaciones
        @Autowired
        private RecomendationsRepository recomendationsRepository;

        @Autowired
        private ValorationRepository valorationRepository;

        @Override
        public void run(String... args) throws Exception {
                createUsersWithProfiles();
                createTranslations();
                createGeneres();
                createAuthorsAndBooks();
                usersAddBooks();
                createRecommendations();
                createValoration();
        }

        private void createUsersWithProfiles() {
                // Diego
                User diego = new User();
                diego.setName("Diego");
                diego.setLastname("Lentz");
                diego.setEmail("diego@gmail.com");
                diego.setBirthdate(LocalDate.parse("1992-04-19"));
                diego.setImg("https://res.cloudinary.com/dumcjdzxo/image/upload/v1746218809/diego_uyhcwb.avif");
                diego.setMoney(10000.0);
                diego.setPhone("+54 11 1111-1111");
                diego.setAdress("Calle Falsa 123, CABA");
                diego.setProfile(new Profile("diego", "diego", diego));

                // Pedro
                User pedro = new User();
                pedro.setName("Pedro");
                pedro.setLastname("Geraghty");
                pedro.setEmail("pedro@mandarina.com");
                pedro.setBirthdate(LocalDate.parse("1965-10-30"));
                pedro.setImg("https://res.cloudinary.com/dumcjdzxo/image/upload/v1746218789/perdo1_jfmu6o.avif");
                pedro.setMoney(8000.0);
                pedro.setPhone("+54 11 2222-2222");
                pedro.setAdress("Av. Siempre Viva 742, CABA");
                pedro.setProfile(new Profile("pedro", "pedro", pedro));

                // Matias
                User matias = new User();
                matias.setName("Matias");
                matias.setLastname("Diaz");
                matias.setEmail("matias@mandarina.com");
                matias.setBirthdate(LocalDate.parse("1990-10-30"));
                matias.setImg("https://res.cloudinary.com/dumcjdzxo/image/upload/v1746218781/matias_tclwsz.jpg");
                matias.setMoney(9000.0);
                matias.setPhone("+54 11 3333-3333");
                matias.setAdress("Belgrano 456, CABA");
                matias.setProfile(new Profile("matias", "matias", matias));

                // Adrian
                User adrian = new User();
                adrian.setName("Adrian");
                adrian.setLastname("Perez");
                adrian.setEmail("adrian@mandarina.com");
                adrian.setBirthdate(LocalDate.parse("1985-10-30"));
                adrian.setImg("https://res.cloudinary.com/dumcjdzxo/image/upload/v1746218776/adrian_cdouit.jpg");
                adrian.setMoney(7000.0);
                adrian.setPhone("+54 11 4444-4444");
                adrian.setAdress("San Martín 789, CABA");
                adrian.setProfile(new Profile("adrian", "adrian", adrian));

                // Valentin
                User valentin = new User();
                valentin.setName("Valentin");
                valentin.setLastname("Pugliese");
                valentin.setEmail("valentin@mandarina.com");
                valentin.setBirthdate(LocalDate.parse("1995-10-30"));
                valentin.setImg("https://res.cloudinary.com/dumcjdzxo/image/upload/v1746218776/valen_ilptyh.jpg");
                valentin.setMoney(6000.0);
                valentin.setPhone("+54 11 5555-5555");
                valentin.setAdress("Rivadavia 1010, CABA");
                valentin.setProfile(new Profile("valentin", "valentin", valentin));

                userRepository.saveAll(Arrays.asList(diego, pedro, matias, adrian, valentin));
        }

        private void createTranslations() {
                List<String> idiomas = List.of("Español", "Ingles", "Frances", "Aleman", "Italiano", "Ruso",
                                "Portugues",
                                "Chino", "Japones", "Neerlandes");
                List<Translation> traducciones = idiomas.stream()
                                .map(Translation::new)
                                .collect(Collectors.toList());
                translationRepository.saveAll(traducciones);
        }

        public void createGeneres() {
                List<String> generos = List.of("FICCIÓN", "MISTERIO", "FANTASIA", "CIENCIA FICCIÓN", "BIOGRAFÍA",
                                "HISTORIA", "ROMANCE", "TERROR", "AVENTURA", "AUTOAYUDA", "SALUD", "VIAJES", "RELIGIÓN",
                                "COCINA", "NO FICCIÓN");
                List<Genere> generes = generos.stream()
                                .map(nombre -> {
                                        Genere g = new Genere();
                                        g.setGenere(nombre);
                                        return g;
                                })
                                .collect(Collectors.toList());
                genereRepository.saveAll(generes);
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

                Genere ficcion = genereRepository.findByGenere("FICCIÓN");
                Genere misterio = genereRepository.findByGenere("MISTERIO");
                // Recuperamos los géneros que se usan más abajo
                // (evitamos declarar variables no utilizadas que generen advertencias)
                genereRepository.findByGenere("FANTASIA");
                Genere cienciaFiccion = genereRepository.findByGenere("CIENCIA FICCIÓN");
                genereRepository.findByGenere("BIOGRAFÍA");
                genereRepository.findByGenere("HISTORIA");
                genereRepository.findByGenere("ROMANCE");
                Genere terror = genereRepository.findByGenere("TERROR");
                Genere aventura = genereRepository.findByGenere("AVENTURA");
                genereRepository.findByGenere("AUTOAYUDA");
                genereRepository.findByGenere("SALUD");
                genereRepository.findByGenere("VIAJES");
                genereRepository.findByGenere("RELIGIÓN");
                genereRepository.findByGenere("COCINA");
                genereRepository.findByGenere("NO FICCIÓN");

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
                sh1.setGenere(ficcion);
                sh1.setPrice(1000.0);

                Book sh2 = new Book();
                sh2.setTitle("Othello");
                sh2.setPages(304);
                sh2.setAuthor(shakespeare);
                sh2.setTranslations(Arrays.asList(espanol, ingles, frances, portugues, aleman));
                sh2.setImg("https://m.media-amazon.com/images/I/51YXwe8en6L._SY445_SX342_.jpg");
                sh2.setGenere(ficcion);
                sh2.setPrice(2500.0);

                Book sh3 = new Book();
                sh3.setTitle("King Lear");
                sh3.setPages(272);
                sh3.setAuthor(shakespeare);
                sh3.setTranslations(Arrays.asList(espanol, ingles, frances, ruso, italiano));
                sh3.setImg("https://m.media-amazon.com/images/I/41cWH7A2FhL._SY445_SX342_.jpg");
                sh3.setGenere(ficcion);
                sh3.setPrice(3000.0);

                Book sh4 = new Book();
                sh4.setTitle("Macbeth");
                sh4.setPages(256);
                sh4.setAuthor(shakespeare);
                sh4.setTranslations(Arrays.asList(espanol, ingles, frances, japones, aleman));
                sh4.setImg("https://m.media-amazon.com/images/I/51NmobtO9QL._SY445_SX342_.jpg");
                sh4.setGenere(ficcion);
                sh4.setPrice(11500.0);
                
                Book sh5 = new Book();
                sh5.setTitle("Romeo and Juliet");
                sh5.setPages(240);
                sh5.setAuthor(shakespeare);
                sh5.setTranslations(Arrays.asList(espanol, ingles, frances, italiano, portugues));
                sh5.setImg("https://m.media-amazon.com/images/I/41VowPay1ZL._SY445_SX342_.jpg");
                sh5.setGenere(ficcion);
                sh5.setPrice(5000.0);

                Book sh6 = new Book();
                sh6.setTitle("The Tempest");
                sh6.setPages(208);
                sh6.setAuthor(shakespeare);
                sh6.setTranslations(Arrays.asList(espanol, ingles, frances, aleman, chino));
                sh6.setImg("https://m.media-amazon.com/images/I/41OW1XLfAWL._SX342_SY445_.jpg");
                sh6.setGenere(ficcion);
                sh6.setPrice(7500.0);

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
                ac1.setGenere(misterio);
                ac1.setPrice(2000.0);

                Book ac2 = new Book();
                ac2.setTitle("And Then There Were None");
                ac2.setPages(272);
                ac2.setAuthor(christie);
                ac2.setTranslations(Arrays.asList(espanol, ingles, frances, italiano));
                ac2.setImg("https://m.media-amazon.com/images/I/41rFnXkCvAL._SY466_.jpg");
                ac2.setGenere(misterio);
                ac2.setPrice(12000.0);

                Book ac3 = new Book();
                ac3.setTitle("The Murder of Roger Ackroyd");
                ac3.setPages(320);
                ac3.setAuthor(christie);
                ac3.setTranslations(Arrays.asList(espanol, ingles, frances));
                ac3.setImg("https://m.media-amazon.com/images/I/61fi4ooNSNL._AC_UY218_.jpg");
                ac3.setGenere(misterio);
                ac3.setPrice(15000.0);

                Book ac4 = new Book();
                ac4.setTitle("Death on the Nile");
                ac4.setPages(336);
                ac4.setAuthor(christie);
                ac4.setTranslations(Arrays.asList(espanol, ingles, frances, portugues));
                ac4.setImg("https://m.media-amazon.com/images/I/71dQeKBE8PL._AC_UY218_.jpg");
                ac4.setGenere(misterio);
                ac4.setPrice(18000.0);

                Book ac5 = new Book();
                ac5.setTitle("The ABC Murders");
                ac5.setPages(288);
                ac5.setAuthor(christie);
                ac5.setTranslations(Arrays.asList(espanol, ingles, frances, neerlandes));
                ac5.setImg("https://m.media-amazon.com/images/I/61oAKfwmrhL._AC_UY218_.jpg");
                ac5.setGenere(misterio);
                ac5.setPrice(20000.0);

                Book ac6 = new Book();
                ac6.setTitle("The Mousetrap");
                ac6.setPages(96);
                ac6.setAuthor(christie);
                ac6.setTranslations(Arrays.asList(espanol, ingles));
                ac6.setImg("https://m.media-amazon.com/images/I/81HG8JrAmGL._AC_UY218_.jpg");
                ac6.setGenere(misterio);
                ac6.setPrice(11000.0);

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
                sk1.setGenere(terror);
                sk1.setPrice(13000.0);

                Book sk2 = new Book();
                sk2.setTitle("The Shining");
                sk2.setPages(512);
                sk2.setAuthor(king);
                sk2.setTranslations(Arrays.asList(espanol, ingles, frances, japones));
                sk2.setImg("https://m.media-amazon.com/images/I/71Og9BY-IOL._AC_UY218_.jpg");
                sk2.setGenere(terror);
                sk2.setPrice(22000.0);

                Book sk3 = new Book();
                sk3.setTitle("It");
                sk3.setPages(1138);
                sk3.setAuthor(king);
                sk3.setTranslations(Arrays.asList(espanol, ingles, frances, chino));
                sk3.setImg("https://m.media-amazon.com/images/I/71oBjdOSkHL._AC_UY218_.jpg");
                sk3.setGenere(terror);
                sk3.setPrice(30000.0);

                Book sk4 = new Book();
                sk4.setTitle("Pet Sematary");
                sk4.setPages(416);
                sk4.setAuthor(king);
                sk4.setTranslations(Arrays.asList(espanol, ingles, frances, aleman));
                sk4.setImg("https://m.media-amazon.com/images/I/91IzEtlVjdL._AC_UY218_.jpg");
                sk4.setGenere(terror);
                sk4.setPrice(17000.0);

                Book sk5 = new Book();
                sk5.setTitle("Misery");
                sk5.setPages(368);
                sk5.setAuthor(king);
                sk5.setTranslations(Arrays.asList(espanol, ingles, frances, italiano, portugues));
                sk5.setImg("https://m.media-amazon.com/images/I/71UV2D2aD6L._AC_UY218_.jpg");
                sk5.setGenere(terror);
                sk5.setPrice(21000.0);

                Book sk6 = new Book();
                sk6.setTitle("The Stand");
                sk6.setPages(1152);
                sk6.setAuthor(king);
                sk6.setTranslations(Arrays.asList(espanol, ingles, frances, ruso, chino));
                sk6.setImg("https://m.media-amazon.com/images/I/81xZcMvbTwL._AC_UY218_.jpg");
                sk6.setGenere(terror);
                sk6.setPrice(29000.0);

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
                ia1.setGenere(cienciaFiccion);
                ia1.setPrice(14000.0);

                Book ia2 = new Book();
                ia2.setTitle("I, Robot");
                ia2.setPages(224);
                ia2.setAuthor(asimov);
                ia2.setTranslations(Arrays.asList(espanol, ingles, frances, aleman, ruso));
                ia2.setImg("https://m.media-amazon.com/images/I/81I5sU1KAbL._AC_UY218_.jpg");
                ia2.setGenere(cienciaFiccion);
                ia2.setPrice(16000.0);

                Book ia3 = new Book();
                ia3.setTitle("The Gods Themselves");
                ia3.setPages(288);
                ia3.setAuthor(asimov);
                ia3.setTranslations(Arrays.asList(espanol, ingles, frances, italiano));
                ia3.setImg("https://m.media-amazon.com/images/I/71oFiob1xsL._AC_UY218_.jpg");
                ia3.setGenere(cienciaFiccion);
                ia3.setPrice(17000.0);

                Book ia4 = new Book();
                ia4.setTitle("The Caves of Steel");
                ia4.setPages(270);
                ia4.setAuthor(asimov);
                ia4.setTranslations(Arrays.asList(espanol, ingles, frances, japones));
                ia4.setImg("https://m.media-amazon.com/images/I/41102eHAs8L._AC_UY218_.jpg");
                ia4.setGenere(cienciaFiccion);
                ia4.setPrice(18000.0);

                Book ia5 = new Book();
                ia5.setTitle("The Naked Sun");
                ia5.setPages(288);
                ia5.setAuthor(asimov);
                ia5.setTranslations(Arrays.asList(espanol, ingles, frances, portugues));
                ia5.setImg("https://m.media-amazon.com/images/I/61byaUYPalL._AC_UY218_.jpg");
                ia5.setGenere(cienciaFiccion);
                ia5.setPrice(19000.0);

                Book ia6 = new Book();
                ia6.setTitle("Second Foundation");
                ia6.setPages(242);
                ia6.setAuthor(asimov);
                ia6.setTranslations(Arrays.asList(espanol, ingles, frances, chino));
                ia6.setImg("https://m.media-amazon.com/images/I/A1XlxEPz8zL._AC_UY218_.jpg");
                ia6.setGenere(cienciaFiccion);
                ia6.setPrice(20000.0);

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
                jv1.setGenere(aventura);
                jv1.setPrice(12000.0);

                Book jv2 = new Book();
                jv2.setTitle("Journey to the Center of the Earth");
                jv2.setPages(320);
                jv2.setAuthor(verne);
                jv2.setTranslations(Arrays.asList(espanol, ingles, frances, italiano));
                jv2.setImg("https://m.media-amazon.com/images/I/912HUortCAL._AC_UY218_.jpg");
                jv2.setGenere(aventura);
                jv2.setPrice(14000.0);

                Book jv3 = new Book();
                jv3.setTitle("Around the World in Eighty Days");
                jv3.setPages(312);
                jv3.setAuthor(verne);
                jv3.setTranslations(Arrays.asList(espanol, ingles, frances, portugues));
                jv3.setImg("https://m.media-amazon.com/images/I/71xihjG35SL._AC_UY218_.jpg");
                jv3.setGenere(aventura);
                jv3.setPrice(16000.0);

                Book jv4 = new Book();
                jv4.setTitle("The Mysterious Island");
                jv4.setPages(656);
                jv4.setAuthor(verne);
                jv4.setTranslations(Arrays.asList(espanol, ingles, frances, ruso, chino));
                jv4.setImg("https://m.media-amazon.com/images/I/81MNM2cXFHL._AC_UY218_.jpg");
                jv4.setGenere(aventura);
                jv4.setPrice(18000.0);

                Book jv5 = new Book();
                jv5.setTitle("From the Earth to the Moon");
                jv5.setPages(288);
                jv5.setAuthor(verne);
                jv5.setTranslations(Arrays.asList(espanol, ingles, frances, japones));
                jv5.setImg("https://m.media-amazon.com/images/I/81hffOMqe-L._AC_UY218_.jpg");
                jv5.setGenere(aventura);
                jv5.setPrice(20000.0);

                Book jv6 = new Book();
                jv6.setTitle("Michael Strogoff");
                jv6.setPages(384);
                jv6.setAuthor(verne);
                jv6.setTranslations(Arrays.asList(espanol, ingles, frances, neerlandes));
                jv6.setImg("https://m.media-amazon.com/images/I/51tH-xbDUzL._AC_UY218_.jpg");
                jv6.setGenere(aventura);
                jv6.setPrice(22000.0);

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
                Book libro6 = bookRepository.findById(6L).orElseThrow(() -> new RuntimeException("Book not found"));
                Book libro7 = bookRepository.findById(7L).orElseThrow(() -> new RuntimeException("Book not found"));
                Book libro8 = bookRepository.findById(8L).orElseThrow(() -> new RuntimeException("Book not found"));
                Book libro9 = bookRepository.findById(9L).orElseThrow(() -> new RuntimeException("Book not found"));
                Book libro10 = bookRepository.findById(10L).orElseThrow(() -> new RuntimeException("Book not found"));
                Book libro11 = bookRepository.findById(1L).orElseThrow(() -> new RuntimeException("Book not found"));
                Book libro12 = bookRepository.findById(2L).orElseThrow(() -> new RuntimeException("Book not found"));
                // Book libro13 = bookRepository.findById(3L).orElseThrow(() -> new
                // RuntimeException("Book not found"));
                // Book libro14 = bookRepository.findById(4L).orElseThrow(() -> new
                // RuntimeException("Book not found"));
                Book libro15 = bookRepository.findById(5L).orElseThrow(() -> new RuntimeException("Book not found"));
                Book libro16 = bookRepository.findById(6L).orElseThrow(() -> new RuntimeException("Book not found"));
                // Book libro17 = bookRepository.findById(7L).orElseThrow(() -> new
                // RuntimeException("Book not found"));
                // Book libro18 = bookRepository.findById(8L).orElseThrow(() -> new
                // RuntimeException("Book not found"));
                // Book libro19 = bookRepository.findById(9L).orElseThrow(() -> new
                // RuntimeException("Book not found"));
                // Book libro20 = bookRepository.findById(10L).orElseThrow(() -> new
                // RuntimeException("Book not found"));

                List<Book> libros1 = List.of(libro1, libro2, libro3, libro4);
                List<Book> libros2 = List.of(libro5, libro6, libro7, libro8);
                List<Book> libros3 = List.of(libro1, libro9, libro3, libro10);
                List<Book> libros4 = List.of(libro11, libro6, libro12, libro9);
                List<Book> libros5 = List.of(libro15, libro2, libro16, libro4);
                // Asignar libros a usuarios
                uno.setBooks(libros1);
                dos.setBooks(libros2);
                tres.setBooks(libros3);
                cuatro.setBooks(libros4);
                cinco.setBooks(libros5);
                userRepository.saveAll(Arrays.asList(uno, dos, tres, cuatro, cinco));
        }

        private void createRecommendations() {
                List<User> users = userRepository.findAll();
                User diego = users.stream()
                                .filter(u -> u.getName().equalsIgnoreCase("Diego")).findFirst().orElse(null);
                User pedro = users.stream()
                                .filter(u -> u.getName().equalsIgnoreCase("Pedro")).findFirst().orElse(null);
                User matias = users.stream()
                                .filter(u -> u.getName().equalsIgnoreCase("Matias")).findFirst().orElse(null);
                User adrian = users.stream()
                                .filter(u -> u.getName().equalsIgnoreCase("Adrian")).findFirst().orElse(null);
                User valentin = users.stream()
                                .filter(u -> u.getName().equalsIgnoreCase("Valentin")).findFirst().orElse(null);

                List<Book> booksDiego = diego.getFirstBooks(2);
                List<Book> booksPedro = pedro.getFirstBooks(2);
                List<Book> booksMatias = matias.getFirstBooks(3);
                List<Book> booksAdrian = adrian.getFirstBooks(3);
                List<Book> booksValentin = valentin.getFirstBooks(2);

                Recomendations recoDiego = diego.createRecommendation("Mejores libros para el tiempo libre",
                                "estos estan buenardos", booksDiego);
                Recomendations recoPedro = pedro.createRecommendation("Libros para pensar", "estos estan buenardos",
                                booksPedro);
                Recomendations recoMatias = matias.createRecommendation("Libros para no dormir",
                                "estos estan buenardos", booksMatias);
                Recomendations recoAdrian = adrian.createRecommendation("Libros para viajar sin moverse",
                                "estos estan buenardos", booksAdrian);
                Recomendations recoValentin = valentin.createRecommendation("Libros para aprender a cocinar",
                                "estos estan buenardos", booksValentin);

                recomendationsRepository
                                .saveAll(Arrays.asList(recoDiego, recoPedro, recoMatias, recoAdrian, recoValentin));
                userRepository.saveAll(List.of(diego, pedro, matias, adrian, valentin));
        }

        // === Un usuario valora la recomendación de otro usuario ===
        private void createValoration() {
                List<User> users = userRepository.findAll();
                User diego = users.stream()
                                .filter(u -> u.getName().equalsIgnoreCase("Diego")).findFirst().orElse(null);
                User pedro = users.stream()
                                .filter(u -> u.getName().equalsIgnoreCase("Pedro")).findFirst().orElse(null);
                User matias = users.stream()
                                .filter(u -> u.getName().equalsIgnoreCase("Matias")).findFirst().orElse(null);
                // User adrian = users.stream()
                // .filter(u ->
                // u.getName().equalsIgnoreCase("Adrian")).findFirst().orElse(null);
                // User valentin = users.stream()
                // .filter(u ->
                // u.getName().equalsIgnoreCase("Valentin")).findFirst().orElse(null);

                List<Recomendations> allRecs = recomendationsRepository.findAll();

                Valoration valDiego = diego.createValoration(5, "Excelente recomendación, me encantó!", allRecs.get(3));
                Valoration valPedro = pedro.createValoration(4, "Muy buena selección de libros.", allRecs.get(0));
                Valoration valMatias = matias.createValoration(3, "La recomendación estuvo bien, pero esperaba más.",
                                allRecs.get(1));

                allRecs.get(0).setValorations(List.of(valDiego));
                allRecs.get(1).setValorations(List.of(valPedro));
                allRecs.get(3).setValorations(List.of(valMatias));

                valorationRepository.saveAll(Arrays.asList(valDiego, valPedro, valMatias));
                recomendationsRepository.saveAll(Arrays.asList(allRecs.get(0), allRecs.get(1), allRecs.get(3)));

        }
}

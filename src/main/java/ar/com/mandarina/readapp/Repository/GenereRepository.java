package ar.com.mandarina.readapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.mandarina.readapp.models.Genere;

public interface GenereRepository extends JpaRepository<Genere, Long> {

    public Genere findByGenere(String genero);

}

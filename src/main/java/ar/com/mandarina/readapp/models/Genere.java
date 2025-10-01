package ar.com.mandarina.readapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Genere {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "genere", nullable = false, unique = true)
    private GenereType genere;
    
        public Genere() {
        }

    public Genere(GenereType genereString) {
            this.genere = genereString;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GenereType getGenere() {
        return genere;
    }

    public void setGenere(GenereType genere) {
        this.genere = genere;
    }

}
package ar.com.mandarina.readapp.services;

import java.util.List;
import org.springframework.stereotype.Service;

import ar.com.mandarina.readapp.Repository.GenereRepository;
import ar.com.mandarina.readapp.models.Genere;

@Service
public class GenereService {

    private final GenereRepository genereRepository;

    public GenereService(GenereRepository genereRepository) {
        this.genereRepository = genereRepository;
    }

    public List<String> getAllGeneres() {
        return genereRepository.findAll().stream()
            .map(Genere::getGenere)
            .sorted(String.CASE_INSENSITIVE_ORDER)
            .toList();
    }
}

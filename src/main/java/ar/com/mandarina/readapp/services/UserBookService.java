package ar.com.mandarina.readapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.mandarina.readapp.Repository.UserBookRepository;
import ar.com.mandarina.readapp.models.UserBook;

@Service
public class UserBookService {

    private final UserBookRepository userBookRepository;

    public UserBookService(UserBookRepository userBookRepository) {
        this.userBookRepository = userBookRepository;
    }

    public List<UserBook> getAllUserBooks() {
        return userBookRepository.findAll();
    }
}

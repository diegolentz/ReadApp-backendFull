package ar.com.mandarina.readapp.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ar.com.mandarina.readapp.models.UserBook;
import ar.com.mandarina.readapp.services.UserBookService;

@Controller
public class UserBookController {

    private final UserBookService userBookService;

    public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }

    @GetMapping("/userBooks")
    public List<UserBook> getAllUserBooks() {
        return userBookService.getAllUserBooks();
    }

}

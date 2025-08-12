package ar.com.mandarina.readapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.mandarina.readapp.models.UserBook;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {

}

package ar.com.mandarina.readapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.mandarina.readapp.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "userBooks")
    List<User> findAll();

}

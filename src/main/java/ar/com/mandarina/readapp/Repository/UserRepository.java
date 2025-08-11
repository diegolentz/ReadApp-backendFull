package ar.com.mandarina.readapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.mandarina.readapp.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

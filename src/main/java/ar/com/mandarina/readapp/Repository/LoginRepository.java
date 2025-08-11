package ar.com.mandarina.readapp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.mandarina.readapp.models.Profile;

public interface LoginRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByUsername(String username);

}

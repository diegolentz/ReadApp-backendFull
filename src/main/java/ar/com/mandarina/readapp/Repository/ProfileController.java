package ar.com.mandarina.readapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.mandarina.readapp.models.Profile;

public interface ProfileController extends JpaRepository<Profile, Long> {

}

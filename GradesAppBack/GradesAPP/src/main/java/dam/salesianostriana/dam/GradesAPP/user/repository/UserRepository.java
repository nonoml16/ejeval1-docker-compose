package dam.salesianostriana.dam.GradesAPP.user.repository;

import dam.salesianostriana.dam.GradesAPP.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findFirstByUsername(String username);

    @Query("""
            Select u.email From User u
            """)
    List<String> getAllEmails();

    @Query("""
            Select u.username From User u
            """)
    List<String> getAllUsernames();

}

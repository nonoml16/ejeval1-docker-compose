package dam.salesianostriana.dam.GradesAPP.user.service;

import dam.salesianostriana.dam.GradesAPP.alumno.model.Alumno;
import dam.salesianostriana.dam.GradesAPP.user.dto.UserRegister;
import dam.salesianostriana.dam.GradesAPP.user.model.User;
import dam.salesianostriana.dam.GradesAPP.user.model.UserRole;
import dam.salesianostriana.dam.GradesAPP.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findById(UUID userId) {
        return repo.findById(userId);
    }

    public Optional<User> findByUsername(String name){
        return repo.findFirstByUsername(name);
    }

    public Optional<User> saveAlumno (UserRegister u){
        return Optional.of(repo.save(Alumno.builder()
                        .email(u.getEmail())
                        .password(passwordEncoder.encode(u.getPassword()))
                        .fechaNacimiento(u.getFechaNacimiento())
                        .username(u.getUsername())
                        .roles(EnumSet.of(UserRole.USER) )
                .build()
        ));
    }

    public boolean existEmail(String s ) {
        return repo.getAllEmails().stream().toList().contains(s);
    }

    public boolean existUsername(String s ) {
        return repo.getAllUsernames().stream().toList().contains(s);
    }
}

package dam.salesianostriana.dam.GradesAPP.profesor.service;

import dam.salesianostriana.dam.GradesAPP.MyPage;
import dam.salesianostriana.dam.GradesAPP.alumno.dto.GetAlumnoListDTO;
import dam.salesianostriana.dam.GradesAPP.alumno.model.Alumno;
import dam.salesianostriana.dam.GradesAPP.exception.NotFoundException;
import dam.salesianostriana.dam.GradesAPP.profesor.dto.EditTeacherRequired;
import dam.salesianostriana.dam.GradesAPP.profesor.dto.NewTeacherRequired;
import dam.salesianostriana.dam.GradesAPP.profesor.dto.TeacherListResponse;
import dam.salesianostriana.dam.GradesAPP.profesor.model.Profesor;
import dam.salesianostriana.dam.GradesAPP.profesor.repository.ProfesorRepository;
import dam.salesianostriana.dam.GradesAPP.user.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfesorService {
    private final ProfesorRepository repo;
    private final PasswordEncoder passwordEncoder;

    public MyPage<GetAlumnoListDTO> obtenerAlumnosPorProfesor(UUID profesorId, Pageable pageable) {
        Page<Alumno> result = repo.findAlumnosByProfesor(profesorId, pageable);
        if (result.isEmpty())
            throw new NotFoundException("Profesor");
        Page<GetAlumnoListDTO> resDto = result.map(GetAlumnoListDTO::of);
        return MyPage.of(resDto);
    }

    public Optional<Profesor> findById (UUID id){
        return repo.findById(id);
    }

    public MyPage<TeacherListResponse> getAll(Pageable pageable){
        Page<Profesor> result = repo.findAll(pageable);
        if (result.isEmpty())
            throw new NotFoundException("Profesor");
        Page<TeacherListResponse> respuesta = result.map(TeacherListResponse::of);
        return MyPage.of(respuesta);
    }

    public Optional<Profesor> save (NewTeacherRequired p){
        return Optional.of(repo.save(Profesor.builder()
                .nombre(p.nombre())
                .apellidos(p.apellidos())
                .email(p.email())
                .titulacion(p.titulacion())
                .asignaturas(p.asignaturas())
                .roles(EnumSet.of(UserRole.ADMIN))
                .password(passwordEncoder.encode(p.password()))
                .username(p.username())
                .build()
        ));
    }

    public Optional<Profesor> edit (UUID id, EditTeacherRequired p){
        Optional<Profesor> acambiar = repo.findById(id);

        if (acambiar.isPresent()){
            acambiar.get().setTitulacion(p.titulacion());
            acambiar.get().setNombre(p.nombre());
            acambiar.get().setApellidos(p.apellidos());
            return Optional.of(repo.save(acambiar.get()));
        }

        throw new NotFoundException("Profesor");

    }

    public void delete (UUID id){
        Optional<Profesor> aborrar = repo.findById(id);

        if (aborrar.isPresent())
            repo.deleteById(id);
        else
            throw new NotFoundException("Profesor");
    }

}
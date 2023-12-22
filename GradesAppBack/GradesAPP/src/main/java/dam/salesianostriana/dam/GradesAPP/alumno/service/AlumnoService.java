package dam.salesianostriana.dam.GradesAPP.alumno.service;

import dam.salesianostriana.dam.GradesAPP.alumno.dto.EditAlumnoDTO;
import dam.salesianostriana.dam.GradesAPP.alumno.dto.GetAlumnoDTO;
import dam.salesianostriana.dam.GradesAPP.alumno.dto.PostAlumnoDTO;
import dam.salesianostriana.dam.GradesAPP.alumno.model.Alumno;
import dam.salesianostriana.dam.GradesAPP.alumno.repository.AlumnoRepository;
import dam.salesianostriana.dam.GradesAPP.asignatura.model.Asignatura;
import dam.salesianostriana.dam.GradesAPP.asignatura.repository.AsignaturaRepository;
import dam.salesianostriana.dam.GradesAPP.calificacion.model.Calificacion;
import dam.salesianostriana.dam.GradesAPP.calificacion.repository.CalificacionRepository;
import dam.salesianostriana.dam.GradesAPP.exception.NotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlumnoService {
    private final AlumnoRepository repo;
    private final AsignaturaRepository asignaturaRepository;
    private final CalificacionRepository calificacionRepository;

    public GetAlumnoDTO save(@Valid PostAlumnoDTO nuevo) {
        Alumno a = PostAlumnoDTO.from(nuevo);
        return GetAlumnoDTO.of(repo.save(a));
    }

    public GetAlumnoDTO edit(UUID id, @Valid EditAlumnoDTO aEditar) {
        Optional<Alumno> result = repo.findById(id);
        if (result.isEmpty())
            throw new NotFoundException("Alumno");

        Alumno editado = result.get();
        editado.setNombre(aEditar.nombre());
        editado.setApellidos(aEditar.apellidos());
        editado.setEmail(aEditar.email());
        editado.setTelefono(aEditar.telefono());
        return GetAlumnoDTO.of(repo.save(editado));
    }

    public GetAlumnoDTO details(UUID id) {
        Optional<Alumno> alumnoOptional = repo.findById(id);
        if (alumnoOptional.isEmpty())
            throw new NotFoundException("Alumno");

        return GetAlumnoDTO.of(alumnoOptional.get());
    }

    public List<Alumno> obtenerAlumnosDeAsignatura(Asignatura asignatura) {
        Hibernate.initialize(repo.findByAsignaturasContains(asignatura));
        return repo.findByAsignaturasContains(asignatura);
    }

    @Transactional
    public void delete(UUID id) {
        Optional<Alumno> alumnoOpt = repo.findById(id);
        if (alumnoOpt.isPresent()) {
            Alumno alumno = alumnoOpt.get();

            alumno.desvincularDeAsignaturas();
            List<Calificacion> calificaciones = calificacionRepository.findByAlumno(alumno);
            calificaciones.forEach(calificacion -> calificacion.setAlumno(null));
            calificacionRepository.saveAll(calificaciones);

            repo.delete(alumno);
        }
    }
}

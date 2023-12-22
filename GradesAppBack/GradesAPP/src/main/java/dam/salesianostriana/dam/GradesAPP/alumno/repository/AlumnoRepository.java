package dam.salesianostriana.dam.GradesAPP.alumno.repository;

import dam.salesianostriana.dam.GradesAPP.alumno.model.Alumno;
import dam.salesianostriana.dam.GradesAPP.asignatura.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AlumnoRepository extends JpaRepository<Alumno, UUID> {

    List<Alumno> findByAsignaturasContains(Asignatura asignatura);
}

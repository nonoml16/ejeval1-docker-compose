package dam.salesianostriana.dam.GradesAPP.profesor.repository;

import dam.salesianostriana.dam.GradesAPP.alumno.model.Alumno;
import dam.salesianostriana.dam.GradesAPP.asignatura.model.Asignatura;
import dam.salesianostriana.dam.GradesAPP.profesor.model.Profesor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProfesorRepository extends JpaRepository<Profesor, UUID> {
    @Query("""
        SELECT DISTINCT a FROM Alumno a
        JOIN a.asignaturas asignatura
        WHERE asignatura.profesor.id = :profesorId
    """)
    Page<Alumno> findAlumnosByProfesor(@Param("profesorId") UUID profesorId, Pageable pageable);

}

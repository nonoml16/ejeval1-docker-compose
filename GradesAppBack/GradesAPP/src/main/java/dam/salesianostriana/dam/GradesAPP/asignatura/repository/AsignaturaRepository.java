package dam.salesianostriana.dam.GradesAPP.asignatura.repository;

import dam.salesianostriana.dam.GradesAPP.alumno.model.Alumno;
import dam.salesianostriana.dam.GradesAPP.asignatura.AsignaturaDTO.GetAsignaturaDTO;
import dam.salesianostriana.dam.GradesAPP.asignatura.model.Asignatura;
import dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.model.ReferenteEvaluacion;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface AsignaturaRepository extends JpaRepository<Asignatura, UUID> {

    @Query("""
            select a.referentes from Asignatura a
            where a.id = :id
            """)
    Page<ReferenteEvaluacion> getReferentesFromAsignatura(UUID id, Pageable pageable);

    Page<Asignatura> findAll(Pageable pageable);

    @Query("""
            select a.referentes from Asignatura a
            where a.id = :id
            """)
    List<ReferenteEvaluacion> getNonPagedReferentesFromAsignatura(UUID id);

    @Query("""
            select a.referentes from Asignatura a
            """)
    List<ReferenteEvaluacion> getAllReferentes();
    @Query(
            """
            select r from ReferenteEvaluacion r
            where r.codReferente = :id        
            """
    )
    Optional<ReferenteEvaluacion> getReferenteById(String id);
    @Query("""
        select new dam.salesianostriana.dam.GradesAPP.asignatura.AsignaturaDTO.GetAsignaturaDTO(
            a.id, a.nombre, a.descripcion, concat(a.profesor.nombre,' ', a.profesor.apellidos), a.hexColor, (
                select case 
                    when count(al) > 0 then count(al)
                    else 0
                end
                from Alumno al
                where a member of al.asignaturas
            )
        )
        from Asignatura a            
        """)
    Page<GetAsignaturaDTO> obtenerTodasConNumeroAlumnos(Pageable pageable);

    @Query("""

        select new dam.salesianostriana.dam.GradesAPP.asignatura.AsignaturaDTO.GetAsignaturaDTO(
            a.id, a.nombre, a.descripcion, concat(a.profesor.nombre,' ', a.profesor.apellidos), a.hexColor, (
                select case 
                    when count(al) > 0 then count(al)
                    else 0
                end
                from Alumno al
                where a member of al.asignaturas
            )
        )
        from Asignatura a 
        where a.profesor.id= :profesorId           
        """)
    Page<GetAsignaturaDTO> getAsignaturasByProfesor (@Param("profesorId") UUID profesorId, Pageable pageable);
        @Query("""
            select a from Asignatura a
            join fetch a.referentes
            where a.id = :idAsig
            """)
    Optional<Asignatura> findByIdWithRefrerente(UUID idAsig);

    @Modifying
    @Transactional
    @Query(
            """
                    delete from ReferenteEvaluacion r
                    where r.codReferente = :codReferente
                    """
    )
    int deleteReferenteByCodReferente(String codReferente);
    @Query("""
            select a from Alumno a
            join fetch a.asignaturas
            where :a member of a.asignaturas
            """)
    List<Alumno> getAlumnosFromAsignatura(Asignatura a);
}

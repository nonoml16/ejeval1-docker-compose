package dam.salesianostriana.dam.GradesAPP.instrumento.repository;

import dam.salesianostriana.dam.GradesAPP.instrumento.model.Instrumento;
import dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.model.ReferenteEvaluacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InstrumentoRepository extends JpaRepository<Instrumento, UUID> {

    Page<Instrumento> findAllByAsignatura_Id(UUID id, Pageable pageable);

    List<Instrumento> findAllByAsignatura_Id(UUID id);
    Optional<Instrumento> findByNombre(String name);
    @Query("""
            select i.referentes from Instrumento i
            where i.id = :id
            """)
    List<ReferenteEvaluacion> getReferentesfromId(UUID id);

    @Query("""
            select i from Instrumento i
            join fetch i.referentes
            where i.id = :id
            """)
    Optional<Instrumento> findByIdWithReferentes(UUID id);

    @Query("""
            select i from Instrumento i
            join fetch i.referentes
            where :referenteEvaluacion member of i.referentes
            """)
    List<Instrumento> getAllWithReferente(ReferenteEvaluacion referenteEvaluacion);
}

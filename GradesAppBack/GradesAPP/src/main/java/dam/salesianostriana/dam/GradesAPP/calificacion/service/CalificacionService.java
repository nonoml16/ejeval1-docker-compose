package dam.salesianostriana.dam.GradesAPP.calificacion.service;

import dam.salesianostriana.dam.GradesAPP.MyPage;
import dam.salesianostriana.dam.GradesAPP.alumno.model.Alumno;
import dam.salesianostriana.dam.GradesAPP.alumno.repository.AlumnoRepository;
import dam.salesianostriana.dam.GradesAPP.asignatura.repository.AsignaturaRepository;
import dam.salesianostriana.dam.GradesAPP.calificacion.DTO.GETCalificacionDTO;
import dam.salesianostriana.dam.GradesAPP.calificacion.DTO.POSTCalificacionDTO;
import dam.salesianostriana.dam.GradesAPP.calificacion.model.Calificacion;
import dam.salesianostriana.dam.GradesAPP.calificacion.repository.CalificacionRepository;
import dam.salesianostriana.dam.GradesAPP.exception.NotFoundException;
import dam.salesianostriana.dam.GradesAPP.instrumento.model.Instrumento;
import dam.salesianostriana.dam.GradesAPP.instrumento.repository.InstrumentoRepository;
import dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.model.ReferenteEvaluacion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CalificacionService {
    private final CalificacionRepository repo;
    private final InstrumentoRepository repoIns;
    private final AsignaturaRepository repoAs;
    private final AlumnoRepository repoAl;

    public MyPage<GETCalificacionDTO> getAllCalificacionesFromInstrumento(UUID uuid, Pageable pageable){
        if(!repoIns.existsById(uuid))
            throw new NotFoundException("Instrumento");
        Page<Calificacion> request = repo.findAllByInstrumento_Id(uuid, pageable);
        Page<GETCalificacionDTO> toReturn = request.map(GETCalificacionDTO::of);
        return MyPage.of(toReturn);

    }

    public GETCalificacionDTO createCalificacion(UUID id, POSTCalificacionDTO newCal) {
        if(!repoIns.existsById(id))
            throw new NotFoundException("Instrumento");
        Instrumento selected = repoIns.getReferenceById(id);
        Optional<Alumno> selectedAl = repoAl.findById(UUID.fromString(newCal.idAlumno()));
        if(selectedAl.isEmpty())
            throw new NotFoundException("Alumno");

        Optional<ReferenteEvaluacion> selectedRef = repoAs.getAllReferentes().stream().filter(ref -> ref.getCodReferente().equals(newCal.codRef())).findFirst();
        System.out.println(selectedRef);
        if(selectedRef.isEmpty())
            throw new NotFoundException("Referente");
        ReferenteEvaluacion ref = selectedRef.get();
        Calificacion created = repo.save(POSTCalificacionDTO.from(selectedAl.get(),newCal, ref, selected));
        return GETCalificacionDTO.of(created);
    }

    public void deleteCalificacion(UUID id) {
        Optional<Calificacion> selected = repo.findById(id);
        if(selected.isEmpty())
            throw new NotFoundException("Calificacion");

        repo.delete(selected.get());
    }
}

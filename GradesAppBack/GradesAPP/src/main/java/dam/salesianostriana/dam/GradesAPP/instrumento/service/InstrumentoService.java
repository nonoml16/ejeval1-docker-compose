package dam.salesianostriana.dam.GradesAPP.instrumento.service;


import dam.salesianostriana.dam.GradesAPP.MyPage;
import dam.salesianostriana.dam.GradesAPP.asignatura.model.Asignatura;
import dam.salesianostriana.dam.GradesAPP.asignatura.repository.AsignaturaRepository;
import dam.salesianostriana.dam.GradesAPP.calificacion.model.Calificacion;
import dam.salesianostriana.dam.GradesAPP.calificacion.repository.CalificacionRepository;
import dam.salesianostriana.dam.GradesAPP.exception.NotFoundException;
import dam.salesianostriana.dam.GradesAPP.instrumento.dto.GETInstrumentoDTO;
import dam.salesianostriana.dam.GradesAPP.instrumento.dto.POSTInstrumentoDTO;
import dam.salesianostriana.dam.GradesAPP.instrumento.model.Instrumento;
import dam.salesianostriana.dam.GradesAPP.instrumento.repository.InstrumentoRepository;
import dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.DTO.ADDReferenteDTO;
import dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.model.ReferenteEvaluacion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstrumentoService {
    private final InstrumentoRepository repo;
    private final AsignaturaRepository repoAs;
    private final CalificacionRepository repoCal;

    public MyPage<GETInstrumentoDTO> getAllInstrumentosFromAsignatura(UUID id, Pageable pageable){
        Optional<Asignatura> selectedAs = repoAs.findById(id);
        if (selectedAs.isEmpty())
            throw new NotFoundException("Asignatura");
        Page<Instrumento> result = repo.findAllByAsignatura_Id(id, pageable);
        if(result.isEmpty())
            throw new NotFoundException("Instrumento");
        Page<GETInstrumentoDTO> res = result.map(GETInstrumentoDTO::of);



        return MyPage.of(res);
    }

    public GETInstrumentoDTO createInstrumento( UUID id ,POSTInstrumentoDTO newIns){
        Optional<Asignatura> selectedAs = repoAs.findById(id);
        if (selectedAs.isEmpty())
            throw new NotFoundException("Asignatura");

        Set<ReferenteEvaluacion> existingReferentes = repoAs.getNonPagedReferentesFromAsignatura(id)
                .stream()
                .filter(ref -> newIns.referentes().contains(ref.getCodReferente()))
                .collect(Collectors.toSet());
        Instrumento created = POSTInstrumentoDTO.from(newIns, selectedAs.get());
        existingReferentes.forEach(created::addReferente);
        return GETInstrumentoDTO.of(repo.save(created));
    }

    public boolean intrumentoExists(String s){
        return repo.findByNombre(s).isPresent();
    }

    public GETInstrumentoDTO getInstrumentoDetails(UUID id){
        Optional<Instrumento> selected = repo.findById(id);
        if(selected.isEmpty())
            throw new NotFoundException("Instrumento");
        return GETInstrumentoDTO.ofDetails(selected.get(), repo.getReferentesfromId(id));
    }

    public GETInstrumentoDTO editInstrumento(UUID id, POSTInstrumentoDTO edited) {
        Optional<Instrumento> selected = repo.findByIdWithReferentes(id);
        if(selected.isEmpty())
            throw new NotFoundException("Instrumento");
        Instrumento toEdit = selected.get();
        toEdit.setNombre(edited.nombre());
        toEdit.setContenidos(edited.contenidos());
        Set<ReferenteEvaluacion> toSave = repoAs.getAllReferentes().stream()
                .filter(ref -> edited.referentes().contains(ref.getCodReferente()))
                .collect(Collectors.toSet());
        toEdit.setReferentes(toSave);
        repo.save(toEdit);
        return GETInstrumentoDTO.of(toEdit);
    }

    public void deleteInstrumento(UUID id) {
        Optional<Instrumento> selected = repo.findByIdWithReferentes(id);
        if(selected.isEmpty())
            throw new NotFoundException("Instrumento");
        Instrumento toDelete = selected.get();
        toDelete.getReferentes().removeAll(toDelete.getReferentes());
        List<Calificacion> calfs = repoCal.findAllByInstrumento_Id(id);
        if(calfs.isEmpty()){
            repo.delete(toDelete);
        }else{
            calfs.forEach(repoCal::delete);
            repo.delete(toDelete);
        }
    }
}

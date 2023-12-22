package dam.salesianostriana.dam.GradesAPP.calificacion.DTO;

import dam.salesianostriana.dam.GradesAPP.alumno.dto.GetAlumnoCalificacionDTO;
import dam.salesianostriana.dam.GradesAPP.alumno.dto.GetAlumnoListDTO;
import dam.salesianostriana.dam.GradesAPP.calificacion.model.Calificacion;
import dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.DTO.GETReferenteDTO;
import dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.model.ReferenteEvaluacion;
import lombok.Builder;

import java.util.UUID;

@Builder
public record GETCalificacionDTO(
        UUID id,
        double calificacion,
        GETReferenteDTO referente,
        GetAlumnoCalificacionDTO alumno
) {
    public static GETCalificacionDTO of(Calificacion calificacion){
        return GETCalificacionDTO.builder()
                .id(calificacion.getId())
                .calificacion(calificacion.getCalificacion())
                .referente(GETReferenteDTO.of(calificacion.getReferente()))
                .alumno(GetAlumnoCalificacionDTO.of(calificacion.getAlumno()))
                .build();
    }
}

package dam.salesianostriana.dam.GradesAPP.alumno.dto;

import dam.salesianostriana.dam.GradesAPP.alumno.model.Alumno;
import lombok.Builder;

import java.util.UUID;

@Builder
public record GetAlumnoCalificacionDTO (
        UUID id,
        String nombre
){
    public static GetAlumnoCalificacionDTO of(Alumno al){
        return GetAlumnoCalificacionDTO.builder()
                .id(al.getId())
                .nombre(al.getNombre())
                .build();
    }
}

package dam.salesianostriana.dam.GradesAPP.profesor.dto;

import dam.salesianostriana.dam.GradesAPP.profesor.model.Profesor;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record TeacherListResponse(UUID id, String nombre, String titulacion, String apellidos, String email) {

    public static TeacherListResponse of (Profesor profe){
        return TeacherListResponse.builder()
                .id(profe.getId())
                .nombre(profe.getNombre())
                .apellidos(profe.getApellidos())
                .email(profe.getEmail())
                .titulacion(profe.getTitulacion())
                .build();
    }
}

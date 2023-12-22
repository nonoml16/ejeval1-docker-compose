package dam.salesianostriana.dam.GradesAPP.alumno.dto;

import dam.salesianostriana.dam.GradesAPP.alumno.model.Alumno;

import java.time.LocalDate;
import java.util.UUID;

public record GetAlumnoDTO(
        UUID id,
        String nombre,
        String apellido,
        LocalDate fechaNacimiento,
        String telefono,
        String email
) {
    public static GetAlumnoDTO of(Alumno a){
        return new GetAlumnoDTO(
                a.getId(),
                a.getNombre(),
                a.getApellidos(),
                a.getFechaNacimiento(),
                a.getTelefono(),
                a.getEmail()
        );
    }
}

package dam.salesianostriana.dam.GradesAPP.alumno.dto;

import dam.salesianostriana.dam.GradesAPP.alumno.model.Alumno;

import java.time.LocalDate;
import java.util.UUID;

public record GetAlumnoListDTO(
        UUID id,
        String nombreApellidos,
        LocalDate fechaNacimiento) {
    public static GetAlumnoListDTO of(Alumno a) {
        return new GetAlumnoListDTO(
                a.getId(),
                a.getNombre() + " " + a.getApellidos(),
                a.getFechaNacimiento());
    }
}

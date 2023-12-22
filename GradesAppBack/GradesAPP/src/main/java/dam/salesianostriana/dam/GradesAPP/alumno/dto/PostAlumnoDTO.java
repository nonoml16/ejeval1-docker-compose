package dam.salesianostriana.dam.GradesAPP.alumno.dto;

import dam.salesianostriana.dam.GradesAPP.alumno.model.Alumno;
import dam.salesianostriana.dam.GradesAPP.validation.validator.LocalDateNotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PostAlumnoDTO(

        @NotEmpty(message = "{PostAlumnoDTO.nombre.notempty}")
        @NotNull
        String nombre,
        @NotEmpty(message = "{PostAlumnoDTO.apellidos.notempty}")
        @NotNull
        String apellidos,
        @LocalDateNotNull(message = "{PostAlumnoDTO.fechaNacimiento.localdatenotnull}")
        LocalDate fechaNacimiento,
        @NotEmpty(message = "{PostAlumnoDTO.email.notempty}")
        @NotNull
        String email,
        @NotEmpty(message = "{PostAlumnoDTO.telefono.notempty}")
        @NotNull
        String telefono,
        @NotEmpty(message = "{PostAlumnoDTO.username.notempty}")
        @NotNull
        String username,
        @NotEmpty(message = "{PostAlumnoDTO.password.notempty}")
        @NotNull
        String password

) {
    public static Alumno from(PostAlumnoDTO a){
        return Alumno.builder()
                .nombre(a.nombre)
                .apellidos(a.apellidos)
                .fechaNacimiento(a.fechaNacimiento)
                .email(a.email)
                .telefono(a.telefono)
                .username(a.username)
                .password(a.password)
                .build();
    }

}

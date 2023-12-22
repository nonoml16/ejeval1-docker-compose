package dam.salesianostriana.dam.GradesAPP.profesor.dto;

import com.fasterxml.jackson.annotation.JsonView;
import dam.salesianostriana.dam.GradesAPP.asignatura.model.Asignatura;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.FieldsValueMatch;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.PasswordLength;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.UniqueEmail;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record EditTeacherRequired (
        @NotNull(message = "{UserRegister.name.notempty}")
        @JsonView({teacherEditResponse.class})
        @NotEmpty(message = "{UserRegister.name.notempty}")
        String nombre,
        @NotNull(message = "{UserRegister.surname.notempty}")
        @JsonView({teacherEditResponse.class})
        @NotEmpty(message = "{UserRegister.surname.notempty}")
        String apellidos,
        @NotNull(message = "{UserRegister.title.notempty}")
        @NotEmpty(message = "{UserRegister.title.notempty}")
        String titulacion,
        Set<Asignatura> asignaturas
){
        public static class teacherEditResponse {}
}

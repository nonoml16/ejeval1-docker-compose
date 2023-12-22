package dam.salesianostriana.dam.GradesAPP.profesor.dto;

import com.fasterxml.jackson.annotation.JsonView;
import dam.salesianostriana.dam.GradesAPP.asignatura.model.Asignatura;
import dam.salesianostriana.dam.GradesAPP.user.dto.UserRegister;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.FieldsValueMatch;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.PasswordLength;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.UniqueEmail;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

@FieldsValueMatch(
        field = "password", fieldMatch = "repeatPassword",
        message = "{UserRegister.password.nomatch}"
)
public record NewTeacherRequired (
        @NotNull(message = "{UserRegister.name.notempty}")
        @NotEmpty(message = "{UserRegister.name.notempty}")
        String nombre,
        @NotNull(message = "{UserRegister.surname.notempty}")
        @NotEmpty(message = "{UserRegister.surname.notempty}")
        String apellidos,
        @NotNull(message = "{UserRegister.email.notempty}")
        @NotEmpty(message = "{UserRegister.email.notempty}")
        @Email(message = "{UserRegister.email.notanemail}")
        @UniqueEmail
        String email,
        @NotNull(message = "{UserRegister.title.notempty}")
        @NotEmpty(message = "{UserRegister.title.notempty}")
        String titulacion,
        @NotNull(message = "{UserRegister.username.notempty}")
        @JsonView({teacherResponse.class})
        @NotEmpty(message = "{UserRegister.username.notempty}")
        @UniqueUsername
        String username,
        @NotNull(message = "{UserRegister.password.notempty}")
        @JsonView({teacherResponse.class})
        @NotEmpty(message = "{UserRegister.password.notempty}")
        @PasswordLength
        String password,
        @NotEmpty(message = "{UserRegister.password.notempty}")
        String repeatPassword,
        Set<Asignatura> asignaturas
        ){

        public static class teacherResponse {}
}

package dam.salesianostriana.dam.GradesAPP.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import dam.salesianostriana.dam.GradesAPP.user.model.User;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldsValueMatch(
        field = "password", fieldMatch = "repeatPassword",
        message = "{UserRegister.password.nomatch}"
)
public class UserRegister {

    protected UUID id;

    @NotNull(message = "{UserRegister.username.notempty}")
    @JsonView({UserResponse.class})
    @NotEmpty(message = "{UserRegister.username.notempty}")
    @UniqueUsername
    protected String username;

    @NotNull(message = "{UserRegister.email.notempty}")
    @NotEmpty(message = "{UserRegister.email.notempty}")
    @Email(message = "{UserRegister.email.notanemail}")
    @UniqueEmail
    private String email;

    @NotNull(message = "{UserRegister.password.notempty}")
    @JsonView({UserResponse.class})
    @NotEmpty(message = "{UserRegister.password.notempty}")
    @PasswordLength
    private String password;

    @NotEmpty(message = "{UserRegister.password.notempty}")
    private String repeatPassword;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate fechaNacimiento;

    public static UserRegister fromUser(User user) {

        return UserRegister.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .repeatPassword(user.getPassword())
                .build();
    }

    public static class UserResponse {}
}

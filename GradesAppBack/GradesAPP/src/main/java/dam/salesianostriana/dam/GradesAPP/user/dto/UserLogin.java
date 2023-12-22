package dam.salesianostriana.dam.GradesAPP.user.dto;

import com.fasterxml.jackson.annotation.JsonView;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.PasswordLength;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.UniqueEmail;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserLogin {
    protected String username;
    private String password;
}

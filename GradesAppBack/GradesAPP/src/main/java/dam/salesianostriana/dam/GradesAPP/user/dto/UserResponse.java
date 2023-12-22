package dam.salesianostriana.dam.GradesAPP.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dam.salesianostriana.dam.GradesAPP.profesor.model.Profesor;
import dam.salesianostriana.dam.GradesAPP.profesor.service.ProfesorService;
import dam.salesianostriana.dam.GradesAPP.user.model.User;
import dam.salesianostriana.dam.GradesAPP.user.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
public class UserResponse {

    protected String id;
    protected String username;
    protected String rol;

    public static UserResponse fromUser(User user) {

        return UserResponse.builder()
                .id(user.getId().toString())
                .username(user.getUsername())
                .rol((user.getRoles().stream().findFirst().isPresent()) ? user.getRoles().stream().findFirst().get().toString() : "USER" )
                .build();
    }
}

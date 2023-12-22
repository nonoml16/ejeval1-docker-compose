package dam.salesianostriana.dam.GradesAPP.user.controller;

import com.fasterxml.jackson.annotation.JsonView;
import dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.model.ReferenteEvaluacion;
import dam.salesianostriana.dam.GradesAPP.security.jwt.access.JwtProvider;
import dam.salesianostriana.dam.GradesAPP.user.dto.JwtUserResponse;
import dam.salesianostriana.dam.GradesAPP.user.dto.UserLogin;
import dam.salesianostriana.dam.GradesAPP.user.dto.UserRegister;
import dam.salesianostriana.dam.GradesAPP.user.model.User;
import dam.salesianostriana.dam.GradesAPP.user.service.UserService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authManager;
    private final UserService userService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "El usuario se ha creado correctamente", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "username": "jimenez.feale23",
                                                    "password": "123456789"
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Los datos introducidos no son válidos",
                    content = @Content)
    })
    @JsonView(UserRegister.UserResponse.class)
    @PostMapping("/register")
    public ResponseEntity<UserRegister> save (@Valid @RequestBody UserRegister u){
        userService.saveAlumno(u);

        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "El usuario se ha registrado correctamente", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "id": "f53fd11b-a28f-4748-a725-41c617faf2f3",
                                                                               "username": "Pepeillo",
                                                                               "rol": "ADMIN",
                                                                               "token": "eyJ0eXAiOiJKV1
                                                                               QiLCJhbGciOiJIUzUxMiJ9.eyJ
                                                                               zdWIiOiJmNTNmZDExYi1hMjhmLT
                                                                               Q3NDgtYTcyNS00MWM2MTdmYWYy
                                                                               ZjMiLCJpYXQiOjE3MDExOTgzNTg
                                                                               sImV4cCI6MTcwMTI4NDc1OH0.aQ
                                                                               6EYXHlHcUXAjKzSMXsXKOtpac3O
                                                                               Lrw9VkAYb31PtmRea8X2RrRzMci8k
                                                                               A-_BRG6U6Y9rX3Jyc8s0jXM8rbMw"
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "401",
                    description = "Los datos introducidos no son válidos",
                    content = @Content)
    })
    //@CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<JwtUserResponse> login(@RequestBody UserLogin userLogin) {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getUsername(),
                        userLogin.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        User user = (User) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JwtUserResponse.of(user, token));


    }

}

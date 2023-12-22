package dam.salesianostriana.dam.GradesAPP.alumno.controller;

import dam.salesianostriana.dam.GradesAPP.alumno.dto.EditAlumnoDTO;
import dam.salesianostriana.dam.GradesAPP.alumno.dto.GetAlumnoDTO;
import dam.salesianostriana.dam.GradesAPP.alumno.dto.PostAlumnoDTO;
import dam.salesianostriana.dam.GradesAPP.alumno.model.Alumno;
import dam.salesianostriana.dam.GradesAPP.alumno.service.AlumnoService;
import dam.salesianostriana.dam.GradesAPP.instrumento.model.Instrumento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/alumno")
@RequiredArgsConstructor
@Tag(name= "Alumno", description = "Controlador para la gestión de alumnos")
public class AlumnoController {
    private final AlumnoService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "El Alumno se ha creado de forma correcta", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Alumno.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "id": "07bc8d93-1fa6-4438-95f4-38f1c8b63e66",
                                                    "nombre": "Nombre del Alumno",
                                                    "apellido": "Apellidos del Alumno",
                                                    "fechaNacimiento": "2000-01-01",
                                                    "telefono": "123456789",
                                                    "email": "correo@example.com"
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No se han aportado los datos correctamente",
                    content = @Content)
    })
    @Operation(summary = "Crear un nuevo Alumno")
    @PostMapping("/")
    public ResponseEntity<GetAlumnoDTO> agregarAlumno(@Valid @RequestBody PostAlumnoDTO nuevo){
        GetAlumnoDTO a = service.save(nuevo);
        URI createdUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(a.id()).toUri();

        return ResponseEntity
                .created(createdUri)
                .body(a);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAlumnoDTO> obtenerPorId(@PathVariable String id){
        GetAlumnoDTO a = service.details(UUID.fromString(id));
        URI createdUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(a.id()).toUri();

        return ResponseEntity
                .created(createdUri)
                .body(a);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "El Alumno se ha editado de forma correcta", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Alumno.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "id": "07bc8d93-1fa6-4438-95f4-38f1c8b63e66",
                                                    "nombre": "Nombre del Alumno",
                                                    "apellido": "Apellidos del Alumno",
                                                    "fechaNacimiento": "2000-01-01",
                                                    "telefono": "123456789",
                                                    "email": "correo@example.com"
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "debe ser una dirección de correo electrónico con formato correcto",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "La contraseña es demasiado corta",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "No se han aportado los datos correctamente",
                    content = @Content)
    })
    @Operation(summary = "Editar un Alumno")
    @PutMapping("/edit/{id}")
    public GetAlumnoDTO editarAlumno(
            @PathVariable String id,
            @Valid @RequestBody EditAlumnoDTO edit
    ){
        return service.edit(UUID.fromString(id), edit);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Alumno eliminado correctamente")
    })
    @Operation(summary = "Editar un Alumno")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> borrarAlumno(@PathVariable String id){
        service.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}

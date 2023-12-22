package dam.salesianostriana.dam.GradesAPP.profesor.controller;

import com.fasterxml.jackson.annotation.JsonView;
import dam.salesianostriana.dam.GradesAPP.MyPage;
import dam.salesianostriana.dam.GradesAPP.alumno.dto.GetAlumnoListDTO;
import dam.salesianostriana.dam.GradesAPP.alumno.model.Alumno;
import dam.salesianostriana.dam.GradesAPP.profesor.dto.EditTeacherRequired;
import dam.salesianostriana.dam.GradesAPP.profesor.dto.NewTeacherRequired;
import dam.salesianostriana.dam.GradesAPP.profesor.dto.TeacherListResponse;
import dam.salesianostriana.dam.GradesAPP.profesor.model.Profesor;
import dam.salesianostriana.dam.GradesAPP.profesor.service.ProfesorService;
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
import org.apache.coyote.Response;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.UUID;

@RestController
@RequestMapping("/profesor")
@RequiredArgsConstructor
@Tag(name = "Profesor", description = "Controlador de la entidad Profesor")
public class ProfesorController {
    private final ProfesorService service;

    @Operation(summary = "Obtiene la lista de alumnos de un profesor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se han encontrado alumnos", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Alumno.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "content": [
                                            {
                                                "nombreApellidos": "Paco Paquito Pacazo",
                                                "fechaNacimiento": "2023-11-27"
                                            }
                                        ],
                                        "size": 10,
                                        "totalElements": 1,
                                        "pageNumber": 0,
                                        "first": true,
                                        "last": true
                                    }
                                    """) }) }),
            @ApiResponse(responseCode = "404", description = "The Profesor or the list could not be found", content = @Content),
    })
    @GetMapping("/{id}/alumnos")
    public MyPage<GetAlumnoListDTO> obtenerAlumnosPorProfesor(
            @PathVariable UUID id,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return service.obtenerAlumnosPorProfesor(id, pageable);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se han encontrado profesores", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Profesor.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "content": [
                                                {
                                                    "id": "573743e7-9579-408f-ae0f-a77110651ea2",
                                                    "nombre": "Juan",
                                                    "apellidos": "Paquito",
                                                    "email": "juanpa@gmail.com",
                                                    "titulacion": null
                                                },
                                                {
                                                    "id": "12f25202-a522-42cd-8a73-da07ffacf84e",
                                                    "nombre": "Luismi",
                                                    "apellidos": "Lopez Magaña",
                                                    "email": "Luismilopes@gmail.com",
                                                    "titulacion": "Crack"
                                                }
                                            ],
                                            "size": 10,
                                            "totalElements": 3,
                                            "pageNumber": 0,
                                            "first": true,
                                            "last": true
                                    }
                                    """) }) }),
            @ApiResponse(responseCode = "404", description = "No se encuentran resultados", content = @Content),
    })
    @GetMapping("")
    public MyPage<TeacherListResponse> obtenerTodos (@PageableDefault(page = 0, size = 10) Pageable pageable){
        return service.getAll(pageable);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se ha creado el profesor", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Profesor.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "username": "usuario",
                                        "password": "123456789"
                                    }
                                    """) }) }),
            @ApiResponse(responseCode = "400", description = "Los datos introducidos no son válidos", content = @Content),
    })
    @JsonView({NewTeacherRequired.teacherResponse.class})
    @PostMapping("/register")
    public ResponseEntity<NewTeacherRequired> createTeacher (@Valid @RequestBody NewTeacherRequired teacher){
        service.save(teacher);

        return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha editado el profesor", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Profesor.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "nombre": "Luismi",
                                        "apellidos": "López"
                                    }
                                    """) }) }),
            @ApiResponse(responseCode = "400", description = "Los datos introducidos no son válidos", content = @Content),
    })
    @JsonView({EditTeacherRequired.teacherEditResponse.class})
    @PutMapping("/{id}/edit")
    public ResponseEntity<EditTeacherRequired> editTeacher (@PathVariable UUID id,@Valid @RequestBody EditTeacherRequired teacher){
        service.edit(id, teacher);
        return ResponseEntity.status(HttpStatus.OK).body(teacher);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Se ha borrado correctamente", content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

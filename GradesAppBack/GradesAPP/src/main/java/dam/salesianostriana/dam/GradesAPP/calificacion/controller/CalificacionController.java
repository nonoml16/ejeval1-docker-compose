package dam.salesianostriana.dam.GradesAPP.calificacion.controller;

import dam.salesianostriana.dam.GradesAPP.MyPage;
import dam.salesianostriana.dam.GradesAPP.calificacion.DTO.GETCalificacionDTO;
import dam.salesianostriana.dam.GradesAPP.calificacion.DTO.POSTCalificacionDTO;
import dam.salesianostriana.dam.GradesAPP.calificacion.model.Calificacion;
import dam.salesianostriana.dam.GradesAPP.calificacion.service.CalificacionService;
import dam.salesianostriana.dam.GradesAPP.instrumento.model.Instrumento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CalificacionController {
    private final CalificacionService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtiene todas las calificaciones asociadas a un instrumento con id dado", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Calificacion.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "content": [
                                                        {
                                                            "id": "3cfeb723-74b4-4a70-be10-e1113ec90300",
                                                            "calificacion": 9.2,
                                                            "referente": {
                                                                "codReferente": "Ad.2",
                                                                "descripcion": "Hola mundo"
                                                            },
                                                            "alumno": {
                                                                "id": "1853c705-ca61-4583-92c6-6bde8c1161d4",
                                                                "nombre": "Juan"
                                                            }
                                                        }
                                                    ],
                                                    "size": 10,
                                                    "totalElements": 1,
                                                    "pageNumber": 0,
                                                    "first": true,
                                                    "last": true
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el Instrumento",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Instrumento.class)),
                            examples = {@ExampleObject(
                                    """
                                        {
                                            "error": "The Instrumento or the list of it could not be found" 
                                        }       
                                    """
                            )
                            }))
    })
    @Operation(summary = "Busca todas las Calificaciones de un Instrumento", description = "Devuelve una lista paginada de todas las Calificaciones de un Instrumento")
    @GetMapping("/teacher/instrumento/{id}/calificaciones")
    public MyPage<GETCalificacionDTO> getAllCalificacionesfromInstrumento(@PathVariable UUID id, @PageableDefault(size = 10, page = 0)Pageable pageable){
        return service.getAllCalificacionesFromInstrumento(id, pageable);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtiene todas las calificaciones asociadas a un instrumento con id dado", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Calificacion.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "content": [
                                                        {
                                                            "id": "3cfeb723-74b4-4a70-be10-e1113ec90300",
                                                            "calificacion": 9.2,
                                                            "referente": {
                                                                "codReferente": "Ad.2",
                                                                "descripcion": "Hola mundo"
                                                            },
                                                            "alumno": {
                                                                "id": "1853c705-ca61-4583-92c6-6bde8c1161d4",
                                                                "nombre": "Juan"
                                                            }
                                                        }
                                                    ],
                                                    "size": 10,
                                                    "totalElements": 1,
                                                    "pageNumber": 0,
                                                    "first": true,
                                                    "last": true
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el Instrumento",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Instrumento.class)),
                            examples = {@ExampleObject(
                                    """
                                        {
                                            "error": "The Instrumento or the list of it could not be found" 
                                        }       
                                    """
                            )
                            }))
    })
    @Operation(summary = "Busca todas las Calificaciones de un Instrumento", description = "Devuelve una lista paginada de todas las Calificaciones de un Instrumento")
    @GetMapping("/student/instrumento/{id}/calificaciones")
    public MyPage<GETCalificacionDTO> getAllCalificacionesfromInstrumentoAlumno(@PathVariable UUID id, @PageableDefault(size = 10, page = 0)Pageable pageable){
        return service.getAllCalificacionesFromInstrumento(id, pageable);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "La calificacion se ha creado de forma correcta", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Calificacion.class)),
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
    @Operation(summary = "Crear una nueva Calificacion")
    @PostMapping("/teacher/instrumento/{id}/calificacion")
    public ResponseEntity<GETCalificacionDTO> createCalificacion(@PathVariable UUID id, @RequestBody POSTCalificacionDTO newCal){
        GETCalificacionDTO created = service.createCalificacion(id, newCal);
        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id()).toUri();
        return ResponseEntity.created(createdURI).body(created);
    }

    @DeleteMapping("/teacher/calificacion/{id}")
    public ResponseEntity<?> deleteCalificacion(@PathVariable UUID id){
        service.deleteCalificacion(id);
        return ResponseEntity.noContent().build();
    }
}

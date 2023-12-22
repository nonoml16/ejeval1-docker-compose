package dam.salesianostriana.dam.GradesAPP.instrumento.controller;

import com.fasterxml.jackson.annotation.JsonView;
import dam.salesianostriana.dam.GradesAPP.MyPage;
import dam.salesianostriana.dam.GradesAPP.instrumento.dto.GETInstrumentoDTO;
import dam.salesianostriana.dam.GradesAPP.instrumento.dto.POSTInstrumentoDTO;
import dam.salesianostriana.dam.GradesAPP.instrumento.jsonViews.InstrumentoViews;
import dam.salesianostriana.dam.GradesAPP.instrumento.model.Instrumento;
import dam.salesianostriana.dam.GradesAPP.instrumento.service.InstrumentoService;
import dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.model.ReferenteEvaluacion;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name= "RestController para Instrumento", description = "Controlador para la gestión de Instrumentos ")
public class InstrumentoController {
    private final InstrumentoService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtiene todos los instrumentos de la Asigantura con Id dado", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Instrumento.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "content": [
                                                        {
                                                            "id": "1724fc40-512a-42d1-8495-f8eae0967e9e",
                                                            "nombre": "Examen T1",
                                                            "fecha": "2023-11-03T12:30:00"
                                                        },
                                                        {
                                                            "id": "299d93b4-c8f0-4e09-ada5-740ea6ea86f3",
                                                            "nombre": "Proyecto T1",
                                                            "fecha": "2023-12-09T12:30:00"
                                                        }
                                                    ],
                                                    "size": 10,
                                                    "totalElements": 2,
                                                    "pageNumber": 0,
                                                    "first": true,
                                                    "last": true
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la Asignatura o está no tiene Instrumentos asociados",
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
    @Operation(summary = "Buscar todas los Instrumentos de una Asigantura", description = "Devuelve una lista de Instrumentos paginados")
    @GetMapping("/teacher/asignatura/{id}/instrumentos")
    @JsonView(InstrumentoViews.InstrumentoList.class)
    public MyPage<GETInstrumentoDTO> getAllInstrumentosFromAsignatura(@PathVariable UUID id, @PageableDefault(page = 0, size = 10)Pageable pageable){
        return service.getAllInstrumentosFromAsignatura(id, pageable);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "El Instrumento se ha creado de forma correcta", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Instrumento.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                            "id": "1724fc40-512a-42d1-8495-f8eae0967e9e",
                                                            "nombre": "Examen T1",
                                                            "fecha": "2023-11-03T12:30:00"
                                                        }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la Asignatura",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Instrumento.class)),
                            examples = {@ExampleObject(
                                    """
                                        {
                                            "error": "The Asignatura or the list of it could not be found" 
                                        }       
                                    """
                            )
                            })),
            @ApiResponse(responseCode = "400",
                    description = "La información dada no es correcta con respecto a lo que se requiere",
                    content = @Content)
    })
    @Operation(summary = "Crear un Nuevo Instrumento", description = "Crea un nuevo Instrumento en una asignatura designada")
    @PostMapping("/teacher/asignatura/{id}/instrumento")
    public ResponseEntity<GETInstrumentoDTO> createInstrumento(@PathVariable UUID id,@Valid @RequestBody POSTInstrumentoDTO newIns) {
        GETInstrumentoDTO created = service.createInstrumento(id, newIns);
        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id()).toUri();
        return ResponseEntity.created(createdURI).body(created);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtiene el instrumento con el id correspondiente", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Instrumento.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "id": "fbf22985-b030-4d41-bb1c-a986cedeb3d7",
                                                    "nombre": "Examen T1",
                                                    "fecha": "2023-11-03",
                                                    "referentes": [
                                                        {
                                                            "codReferente": "Ad.2",
                                                            "descripcion": "Hola mundo"
                                                        }
                                                    ]
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
    @Operation(summary = "Busca instrumento por su id", description = "Devuelve un instrumento con la lista de sus referentes asociados")
    @GetMapping("/teacher/instrumento/{id}")
    @JsonView(InstrumentoViews.InstrumentoDetails.class)
    public GETInstrumentoDTO getInstrumentoDetails(@PathVariable UUID id){
        return service.getInstrumentoDetails(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edita el instrumento de forma correcta", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Instrumento.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "id": "fbf22985-b030-4d41-bb1c-a986cedeb3d7",
                                                    "nombre": "Examen T1",
                                                    "fecha": "2023-11-03",
                                                    "referentes": [
                                                        {
                                                            "codReferente": "Ad.2",
                                                            "descripcion": "Hola mundo"
                                                        }
                                                    ]
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
    @Operation(summary = "Edita un instrumento", description = "Devuelve el instrumento editado")
    @PutMapping("/teacher/instrumento/{id}")
    @JsonView(InstrumentoViews.InstrumentoDetails.class)
    public GETInstrumentoDTO editInstrumento(@PathVariable UUID id, @Valid @RequestBody POSTInstrumentoDTO edited){
        return service.editInstrumento(id, edited);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "El instrumento y sus calificaciones se han borrado correctamente",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404",
                    description = "No encontrado el instrumento a borrar",
                    content = @Content)
    })
    @Operation(summary = "Borrar Instrumento", description = "Devuelve 204 no content si todo va bien ")
    @DeleteMapping("/teacher/instrumento/{id}")
    public ResponseEntity<?> deleteInstrumento(@PathVariable UUID id){
        service.deleteInstrumento(id);
        return ResponseEntity.noContent().build();
    }
}

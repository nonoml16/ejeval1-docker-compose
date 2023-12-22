package dam.salesianostriana.dam.GradesAPP.asignatura.controller;


import dam.salesianostriana.dam.GradesAPP.alumno.dto.GetAlumnoDTO;
import dam.salesianostriana.dam.GradesAPP.alumno.model.Alumno;
import dam.salesianostriana.dam.GradesAPP.asignatura.AsignaturaDTO.GetAsignaturaDTO;

import dam.salesianostriana.dam.GradesAPP.MyPage;
import dam.salesianostriana.dam.GradesAPP.asignatura.AsignaturaDTO.PostAsignaturaDTO;
import dam.salesianostriana.dam.GradesAPP.asignatura.AsignaturaDTO.PutAsignaturaDTO;
import dam.salesianostriana.dam.GradesAPP.asignatura.model.Asignatura;

import dam.salesianostriana.dam.GradesAPP.asignatura.service.AsignaturaService;
import dam.salesianostriana.dam.GradesAPP.MyPage;
import dam.salesianostriana.dam.GradesAPP.asignatura.model.Asignatura;
import dam.salesianostriana.dam.GradesAPP.instrumento.model.Instrumento;
import dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.DTO.ADDReferenteDTO;
import dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.DTO.GETReferenteDTO;
import dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.DTO.PUTReferenteDTO;
import dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.model.ReferenteEvaluacion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
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
@Tag(name= "Asignatura", description= "Este es el controlador de las asignaturas, en el cual se gestionan y se enlazan las rutas con los métodos de esta entidad")
public class AsignaturaController {

    private final AsignaturaService service;

    @Operation(summary = "Obtiene todas las asignaturas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado todas las asignaturas",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Asignatura.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "content": [
                                                    {
                                                        "id": "0420354e-9bd3-405c-b5fa-2a8431a47ed8",
                                                        "asigNombre": "AD",
                                                        "asigDescripcion": "Esta es una buena asignatura",
                                                        "nombreProfesor": "Pepe Perez",
                                                        "color": "#ff6961",
                                                        "numAlumnos": 0
                                                    },
                                                    {
                                                        "id": "d56a2194-24b3-44be-9986-97ef314562cb",
                                                        "asigNombre": "Diseño Interfaces",
                                                        "asigDescripcion": "Hola",
                                                        "nombreProfesor": "Juan Paquito",
                                                        "color": "#77dd77",
                                                        "numAlumnos": 0
                                                    },
                                                    {
                                                        "id": "79ff6883-7f9e-4a24-98a8-dee031b65217",
                                                        "asigNombre": "Masa madre",
                                                        "asigDescripcion": "Cositas varias",
                                                        "nombreProfesor": "Luismi Lopez Magaña",
                                                        "color": "#ff6961",
                                                        "numAlumnos": 1
                                                    }
                                                ],
                                                "size": 12,
                                                "totalElements": 10,
                                                "pageNumber": 0,
                                                "first": true,
                                                "last": true
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna asignatura",
                    content = @Content),
    })
    @GetMapping("/teacher/asignatura/")
    public MyPage<GetAsignaturaDTO> GetAll(@PageableDefault(size = 9, page = 0) Pageable pageable) {
        return service.findAll(pageable);
    }

    @Operation(summary = "Crea una nueva asignaturas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado todas las asignaturas",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Asignatura.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                                                
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "La información requerida no es correcta",
                    content = @Content),
    })
    @PostMapping("/teacher/asignatura/")
    public ResponseEntity<GetAsignaturaDTO> createAsignatura(@Valid @RequestBody PostAsignaturaDTO nuevo) {
        GetAsignaturaDTO created = service.createAsignatura(nuevo);
        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(created.id()).toUri();
        return ResponseEntity.created(createdURI).body(created);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtiene todos los referentes de la Asigantura con Id dado", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ReferenteEvaluacion.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                     "content": [
                                                         {
                                                             "codReferente": "Ad.2",
                                                             "descripcion": "Hola mundo"
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
                    description = "No se ha encontrado la Asignatura o está no tiene Referentes asociados",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ReferenteEvaluacion.class)),
                            examples = {@ExampleObject(
                                    """
                                                {
                                                    "error": "The Referente/Asignatura or the list of it could not be found" 
                                                }       
                                            """
                            )
                            }))
    })
    @Operation(summary = "Buscar todas los Referentes de una Asigantura", description = "Devuelve una lista de Referentes de evaluacion paginados")
    @GetMapping("/teacher/asignatura/{id}/referentes")
    public MyPage<GETReferenteDTO> getAllReferentesFromAsignatura(@PathVariable UUID id, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return service.getReferentesFromAsignatura(id, pageable);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "El Referente de Evaluación se ha creado de forma correcta", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ReferenteEvaluacion.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "codReferente": "ad1",
                                                    "descripcion": "Hola mundo"
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la Asignatura",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ReferenteEvaluacion.class)),
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
    @Operation(summary = "Crear un Nuevo Referente de Evaluación", description = "Crea un nuevo Referente de Evaluación en una asignatura designada")
    @PostMapping("/teacher/asignatura/{id}/referente")
    public ResponseEntity<GETReferenteDTO> addReferente(@PathVariable UUID id, @Valid @RequestBody ADDReferenteDTO referenteDTO) {
        ReferenteEvaluacion created = service.addReferente(id, referenteDTO);
        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getCodReferente()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(GETReferenteDTO.of(created));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edita el referente indicado con la nueva descripción", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ReferenteEvaluacion.class)),
                            examples = {@ExampleObject(
                                    value = """
                                             {
                                                "codReferente": "Ad.2",
                                                "descripcion": "Hola mundo"
                                             }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el Referente o la Asignatura",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ReferenteEvaluacion.class)),
                            examples = {@ExampleObject(
                                    """
                                                {
                                                    "error": "The Referente or the list of it could not be found" 
                                                }       
                                            """
                            )
                            }))
    })
    @Operation(summary = "Edita un referente", description = "Devuelve el referente editado")
    @PutMapping("/teacher/asignatura/{id_asig}/referente/{id}")
    public GETReferenteDTO editReferente(@PathVariable UUID id_asig, @PathVariable String id, @Valid @RequestBody PUTReferenteDTO referenteDTO) {
        return service.editReferente(id_asig, id, referenteDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtiene todos el Referente con el cod Ref dado", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ReferenteEvaluacion.class)),
                            examples = {@ExampleObject(
                                    value = """
                                             {
                                                "codReferente": "Ad.2",
                                                "descripcion": "Hola mundo"
                                             }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el Referente",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ReferenteEvaluacion.class)),
                            examples = {@ExampleObject(
                                    """
                                                {
                                                    "error": "The Referente or the list of it could not be found" 
                                                }       
                                            """
                            )
                            }))
    })
    @Operation(summary = "Buscar un referente por id", description = "Devuelve el referente buscado")
    @GetMapping("/teacher/referente/{id}")
    public GETReferenteDTO getReferenteById(@PathVariable String id) {
        return service.getReferenteById(id);
    }

    @Operation(summary = "Obtiene todas las asignaturas que tiene un profesor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado todas las asignaturas que tiene un profesor",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Asignatura.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                    {
                                                        "content": [
                                                            {
                                                                "id": "a86fdae4-55d5-4946-9c41-2090ccd09a85",
                                                                "asigNombre": "Diseño Interfaces",
                                                                "asigDescripcion": "Hola",
                                                                "nombreProfesor": "Juan Paquito",
                                                                "color": "#77dd77",
                                                                "numAlumnos": 0
                                                            },
                                                            {
                                                                "id": "992c5f3c-0598-4e00-b957-8a7d21ff4ef2",
                                                                "asigNombre": "Diseño Interfaces",
                                                                "asigDescripcion": "Hola",
                                                                "nombreProfesor": "Juan Paquito",
                                                                "color": "#77dd77",
                                                                "numAlumnos": 0
                                                            }
                                                        ],
                                                        "size": 10,
                                                        "totalElements": 8,
                                                        "pageNumber": 0,
                                                        "first": true,
                                                        "last": true
                                                    }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna asignatura",
                    content = @Content),
    })
    @GetMapping("/teacher/{id}/asignatura/")
    public MyPage<GetAsignaturaDTO> getAllAsignaturasByProfesor(@PathVariable UUID id, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return service.getAsignaturasByProfesor(pageable, id);
    }


    @Operation(summary = "Edita una asignatura", description = "Devuelve el referente buscado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edita la asignatura con un nuevo color y una nueva descripción", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Asignatura.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la asignatura",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Asignatura.class)),
                            examples = {@ExampleObject(
                                    """
                                        {
                                            "error": "The Asignatura or the list of it could not be found" 
                                        }       
                                    """
                            )
                            }))
    })
    @PutMapping("/teacher/asignatura/{id}")
    public GetAsignaturaDTO editAsignatura(@PathVariable UUID idAsig, @Valid @RequestBody PutAsignaturaDTO editAsignatura ){
    return service.editAsignatura(idAsig, editAsignatura);

    }

        @ApiResponses(value = {
                @ApiResponse(responseCode = "204",
                        description = "El Referente y sus calificaiones se han borrado correctamente",
                        content = @Content
                ),
                @ApiResponse(responseCode = "404",
                        description = "No encontrado el Referente a borrar",
                        content = @Content)
        })
        @Operation(summary = "Borrar Referente de Evaluacion", description = "Devuelve 204 no content si todo va bien ")
        @DeleteMapping("/teacher/referente/{id}")
        public ResponseEntity<?> deleteReferente (@PathVariable String id){
            service.deleteReferente(id);
            return ResponseEntity.noContent().build();
        }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alumnos encontrados", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Alumno.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                [
                                                    {
                                                        "id": "07bc8d93-1fa6-4438-95f4-38f1c8b63e66",
                                                        "nombre": "Nombre del Alumno",
                                                        "apellido": "Apellidos del Alumno",
                                                        "fechaNacimiento": "2000-01-01",
                                                        "telefono": "123456789",
                                                        "email": "correo@example.com"
                                                    }
                                                ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "Asignatura no encontrada",
                    content = @Content)
        })
        @Operation(summary = "Buscar todos los alumnos de una asignatura")
        @GetMapping("/teacher/asignatura/{id}/alumnos")
        public List<GetAlumnoDTO> getAlumnosFromAsignatura(@PathVariable UUID id){
            return service.getAlumnosFromAsignatura(id);
        }
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtiene todos el Referente con el cod Ref dado", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ReferenteEvaluacion.class)),
                            examples = {@ExampleObject(
                                    value = """
                                             [
                                                 {
                                                     "codReferente": "Ad.2",
                                                     "descripcion": "Hola mundo"
                                                 },
                                                 {
                                                     "codReferente": "Ad.3",
                                                     "descripcion": "Hola mundo"
                                                 }
                                                ]
                                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la asignatura",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Asignatura.class)),
                            examples = {@ExampleObject(
                                    """
                                        {
                                            "error": "The Asignatura or the list of it could not be found" 
                                        }       
                                    """
                            )
                            }))
    })
        @Operation(summary = "Buscar todos los referentes de un Instrumento", description = "Devuelve la lista de Referentes")
        @GetMapping("/student/instrumento/{id_ins}/referentes")
        public List<GETReferenteDTO> getReferenteAlumnoInstrumento(@PathVariable UUID id_ins) {
        return service.getReferentesFromInstrumento(id_ins);
        }
}


package dam.salesianostriana.dam.GradesAPP.asignatura.AsignaturaDTO;

import dam.salesianostriana.dam.GradesAPP.asignatura.model.Asignatura;
import dam.salesianostriana.dam.GradesAPP.profesor.model.Profesor;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.UniqueInstrument;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostAsignaturaDTO(
        @NotBlank(message = "El nombre debe de estar completado")
        @UniqueInstrument
        String nombre,
        @Min(value = 0, message = "Las horas no puede ser menor a 0")
        Long horas,

        @NotBlank(message = "El profesor no puede estar en blanco")
        String idProfesor,
        @NotNull(message = "Este campo no puedo ser nulo")
        String descripcion,
        @NotBlank(message = "El color debe de estar completado")
        String color) {

    public static Asignatura from(PostAsignaturaDTO nuevo, Profesor profesor){
        return Asignatura.builder()
                .nombre(nuevo.nombre())
                .horas(nuevo.horas())
                .profesor(profesor)
                .descripcion(nuevo.descripcion())
                .hexColor(nuevo.color())
                .build();

    }
}

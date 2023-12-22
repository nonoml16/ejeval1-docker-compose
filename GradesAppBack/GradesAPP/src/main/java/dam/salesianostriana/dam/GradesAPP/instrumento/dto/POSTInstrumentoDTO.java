package dam.salesianostriana.dam.GradesAPP.instrumento.dto;

import dam.salesianostriana.dam.GradesAPP.asignatura.model.Asignatura;
import dam.salesianostriana.dam.GradesAPP.instrumento.model.Instrumento;
import dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.model.ReferenteEvaluacion;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.UniqueInstrument;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record POSTInstrumentoDTO(
        @NotBlank(message = "El nombre no puede estar vacío")
        @NotNull(message = "El nombre no puede ser nulo")
        @UniqueInstrument
        String nombre,
        @NotNull(message = "La fecha no puede ser nula")
        @FutureOrPresent(message = "La fecha no puede ser anterior a la fecha actual")
        LocalDate fecha,
        @NotBlank(message = "Los contenidos no pueden estar vacíos")
        @NotNull(message = "Los contenidos no pueden ser nulos")
        String contenidos,
        @UniqueElements(message = "Ha introducido más de una vez el mismo referente")
        @NotEmpty(message = "El instrumento debe tener al menos 1 referente")
        List<String> referentes

) {
    public static Instrumento from(POSTInstrumentoDTO newIns, Asignatura as){
        return  Instrumento.builder()
                .nombre(newIns.nombre())
                .contenidos(newIns.contenidos())
                .asignatura(as)
                .fecha(newIns.fecha)
                .build();
    }

}

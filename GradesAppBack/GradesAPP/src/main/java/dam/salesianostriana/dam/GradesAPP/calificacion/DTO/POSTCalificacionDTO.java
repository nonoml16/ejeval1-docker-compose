package dam.salesianostriana.dam.GradesAPP.calificacion.DTO;

import dam.salesianostriana.dam.GradesAPP.alumno.model.Alumno;
import dam.salesianostriana.dam.GradesAPP.calificacion.model.Calificacion;
import dam.salesianostriana.dam.GradesAPP.instrumento.model.Instrumento;
import dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.model.ReferenteEvaluacion;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record POSTCalificacionDTO(
        @NotBlank(message = "La calificación debe estar asociada a un referente")
        String codRef,
        @NotBlank(message = "La calificacion debe estar asociada a un Alumno")
        String idAlumno,
        @NotNull(message = "La calificacion no puede ser nula")
        @Min(value = 0, message = "La calificacion no puede ser menor a 0" )
        double calificacion
) {
    public static Calificacion from(Alumno al, POSTCalificacionDTO newCal, ReferenteEvaluacion ref, Instrumento ins){
        return Calificacion.builder()
                .referente(ref)
                .calificacion(newCal.calificacion)
                .instrumento(ins)
                .alumno(al)
                .build();
    }
}

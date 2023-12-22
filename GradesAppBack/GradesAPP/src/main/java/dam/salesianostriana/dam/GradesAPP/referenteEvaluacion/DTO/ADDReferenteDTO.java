package dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.DTO;

import dam.salesianostriana.dam.GradesAPP.asignatura.model.Asignatura;
import dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.model.ReferenteEvaluacion;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.UniqueCodReferente;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record ADDReferenteDTO(
        @NotBlank(message = "{ADDreferenteDTO.codReferente.notempty}")
        @UniqueCodReferente
        String codReferente,
        @NotBlank(message = "{ADDreferenteDTO.descripcion.notempty}")
        String descripcion

) {
    public static ReferenteEvaluacion from(ADDReferenteDTO referenteDTO, Asignatura as){
        return ReferenteEvaluacion.builder()
                .descripcion(referenteDTO.descripcion())
                .codReferente(referenteDTO.codReferente())
                .asignatura(as)
                .build();
    }
}

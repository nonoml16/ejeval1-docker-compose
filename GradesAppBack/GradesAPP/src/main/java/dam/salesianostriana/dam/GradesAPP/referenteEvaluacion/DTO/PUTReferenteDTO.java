package dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.DTO;

import dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.model.ReferenteEvaluacion;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record PUTReferenteDTO(
        @NotBlank(message = "La descripcion no puede estar vacía")
        String descripcion
) {
    public static ReferenteEvaluacion from(ReferenteEvaluacion referenteEvaluacion, PUTReferenteDTO referenteDTO){
        referenteEvaluacion.setDescripcion(referenteDTO.descripcion());
        return referenteEvaluacion;
    }
}

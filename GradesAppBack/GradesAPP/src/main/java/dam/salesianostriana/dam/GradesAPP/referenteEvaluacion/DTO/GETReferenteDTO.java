package dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import dam.salesianostriana.dam.GradesAPP.instrumento.jsonViews.InstrumentoViews;
import dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.model.ReferenteEvaluacion;
import lombok.Builder;

@Builder
public record GETReferenteDTO(
        @JsonView(InstrumentoViews.InstrumentoDetails.class)
        String codReferente,
        @JsonView(InstrumentoViews.InstrumentoDetails.class)
        String descripcion
) {
    public static GETReferenteDTO of(ReferenteEvaluacion ref){
        return GETReferenteDTO.builder()
                .codReferente(ref.getCodReferente())
                .descripcion(ref.getDescripcion())
                .build();
    }

}

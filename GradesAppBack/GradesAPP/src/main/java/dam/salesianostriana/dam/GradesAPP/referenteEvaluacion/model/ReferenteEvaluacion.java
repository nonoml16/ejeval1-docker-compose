package dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.model;

import dam.salesianostriana.dam.GradesAPP.asignatura.model.Asignatura;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ReferenteEvaluacionPK.class)
public class ReferenteEvaluacion {

    @Id
    private String codReferente;

    @Id
    @ManyToOne
    private Asignatura asignatura;

    private String descripcion;

    public ReferenteEvaluacionPK getId() {
        return new ReferenteEvaluacionPK(asignatura, codReferente);
    }

    public void setId(ReferenteEvaluacionPK referenteEvaluacionPK) {
        this.asignatura = referenteEvaluacionPK.getAsignatura();
        this.codReferente = referenteEvaluacionPK.getCodReferente();
    }
}

package dam.salesianostriana.dam.GradesAPP.asignatura.model;

import dam.salesianostriana.dam.GradesAPP.alumno.model.Alumno;
import dam.salesianostriana.dam.GradesAPP.profesor.model.Profesor;
import dam.salesianostriana.dam.GradesAPP.referenteEvaluacion.model.ReferenteEvaluacion;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Objects;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Asignatura {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator", parameters = {
            @Parameter(name = "uuid_gen_strategy_clas", value = "org.hibernate.id.uuid.CurstomVersionOneStrategy")
    })
    private UUID id;

    private String nombre;
    private Long horas;
    private String descripcion;
    private String hexColor;

    @Getter
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

    @OneToMany(mappedBy = "asignatura", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private List<ReferenteEvaluacion> referentes = new ArrayList<>();

    //Esto lo he puesto ya que no he sido capaz de sacar la consulta. Fdo. Nono
    @ManyToMany(mappedBy = "asignaturas", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<Alumno> alumnos;

    public void addReferente(ReferenteEvaluacion referenteEvaluacion) {
        this.referentes.add(referenteEvaluacion);
        referenteEvaluacion.setAsignatura(this);
    }

    public Asignatura removeReferente(ReferenteEvaluacion referenteEvaluacion) {
        removeReferente(referenteEvaluacion.getCodReferente());
        referenteEvaluacion.setAsignatura(null);
        return this;
    }

    private void removeReferente(String codReferente) {
        referentes
                .removeIf(referenteEvaluacion -> referenteEvaluacion.getCodReferente().equalsIgnoreCase(codReferente));
    }

    public void addProfesor(Profesor p) {
        this.profesor = p;
        p.getAsignaturas().add(this);
    }

    public void removeProfesor(Profesor p) {
        this.profesor = null;
        p.getAsignaturas().remove(this);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass)
            return false;
        Asignatura that = (Asignatura) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}

package dam.salesianostriana.dam.GradesAPP.alumno.model;

import dam.salesianostriana.dam.GradesAPP.asignatura.model.Asignatura;
import dam.salesianostriana.dam.GradesAPP.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter@Setter
public class Alumno extends User {

    private String telefono;
    private LocalDate fechaNacimiento;

    @Getter
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "Alumno_asignaturas",
            joinColumns = @JoinColumn(name = "alumno_id"),
            inverseJoinColumns = @JoinColumn(name = "asignaturas_id"))
    @Builder.Default
    private Set<Asignatura> asignaturas = new LinkedHashSet<>();

    public void addAsignatura(Asignatura a){
        this.asignaturas.add(a);
    }
    public void removeAsignatura(Asignatura a){
        this.asignaturas.remove(a);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void desvincularDeAsignaturas() {
        for (Asignatura asignatura : asignaturas) {
            asignatura.getAlumnos().remove(this);
        }
        asignaturas.clear();
    }
}

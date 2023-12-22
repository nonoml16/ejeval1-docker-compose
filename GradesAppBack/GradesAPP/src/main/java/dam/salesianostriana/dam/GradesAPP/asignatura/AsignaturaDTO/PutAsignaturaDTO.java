package dam.salesianostriana.dam.GradesAPP.asignatura.AsignaturaDTO;

import dam.salesianostriana.dam.GradesAPP.asignatura.model.Asignatura;

public record PutAsignaturaDTO(
        String color,
        String descripcion
) {

    public static Asignatura from(Asignatura asignatura, PutAsignaturaDTO editAsig){
        asignatura.setDescripcion(editAsig.descripcion());
        asignatura.setHexColor(editAsig.color());
        return asignatura;
    }
}

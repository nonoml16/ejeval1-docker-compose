package dam.salesianostriana.dam.GradesAPP.validation.validator;

import dam.salesianostriana.dam.GradesAPP.asignatura.service.AsignaturaService;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.UniqueCodReferente;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class UniqueCodReferenteValidator implements ConstraintValidator<UniqueCodReferente, String> {
    @Autowired
    private AsignaturaService service;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !service.referenteExists(s);
    }
}

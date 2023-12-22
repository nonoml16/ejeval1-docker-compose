package dam.salesianostriana.dam.GradesAPP.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class LocalDateNotNullValidator implements ConstraintValidator<LocalDateNotNull, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return value != null;
    }
}

package dam.salesianostriana.dam.GradesAPP.validation.annotation;

import dam.salesianostriana.dam.GradesAPP.validation.validator.UniqueCodReferenteValidator;
import dam.salesianostriana.dam.GradesAPP.validation.validator.UniqueInstrumentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueInstrumentValidator.class)
@Documented
public @interface UniqueInstrument {

    String message() default "Ya existe un isntrumento con ese nombre";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

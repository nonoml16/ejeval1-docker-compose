package dam.salesianostriana.dam.GradesAPP.validation.annotation;

import dam.salesianostriana.dam.GradesAPP.validation.validator.UniqueCodReferenteValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCodReferenteValidator.class)
@Documented
public @interface UniqueCodReferente {

    String message() default "Ya existe un referente con ese código de referente";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

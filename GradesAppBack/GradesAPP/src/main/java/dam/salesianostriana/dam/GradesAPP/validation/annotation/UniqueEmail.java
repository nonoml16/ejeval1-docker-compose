package dam.salesianostriana.dam.GradesAPP.validation.annotation;

import dam.salesianostriana.dam.GradesAPP.validation.validator.UniqueCodReferenteValidator;
import dam.salesianostriana.dam.GradesAPP.validation.validator.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
@Documented
public @interface UniqueEmail {

    String message() default "email ya registrado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

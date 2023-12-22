package dam.salesianostriana.dam.GradesAPP.validation.annotation;

import dam.salesianostriana.dam.GradesAPP.validation.validator.UniqueCodReferenteValidator;
import dam.salesianostriana.dam.GradesAPP.validation.validator.UniqueUsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Documented
public @interface UniqueUsername {

    String message() default "Nombre de usuario en uso";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

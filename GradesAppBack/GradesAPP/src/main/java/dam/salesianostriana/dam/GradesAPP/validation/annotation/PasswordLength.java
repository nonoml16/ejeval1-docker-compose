package dam.salesianostriana.dam.GradesAPP.validation.annotation;

import dam.salesianostriana.dam.GradesAPP.validation.validator.PasswordLengthValidator;
import dam.salesianostriana.dam.GradesAPP.validation.validator.UniqueCodReferenteValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordLengthValidator.class)
@Documented
public @interface PasswordLength {

    String message() default "La contraseña es demasiado corta";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

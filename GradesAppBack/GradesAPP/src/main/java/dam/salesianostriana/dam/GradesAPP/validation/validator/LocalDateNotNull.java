package dam.salesianostriana.dam.GradesAPP.validation.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LocalDateNotNullValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "Date must not be null")
public @interface LocalDateNotNull {
    String message() default "Date must not be null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
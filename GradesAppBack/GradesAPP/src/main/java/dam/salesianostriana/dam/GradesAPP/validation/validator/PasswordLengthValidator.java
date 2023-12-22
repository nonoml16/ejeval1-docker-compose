package dam.salesianostriana.dam.GradesAPP.validation.validator;

import dam.salesianostriana.dam.GradesAPP.user.service.UserService;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.PasswordLength;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.UniqueCodReferente;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class PasswordLengthValidator implements ConstraintValidator<PasswordLength, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.hasText(s) && (s.length()>8);
    }
}

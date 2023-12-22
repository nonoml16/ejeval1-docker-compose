package dam.salesianostriana.dam.GradesAPP.validation.validator;

import dam.salesianostriana.dam.GradesAPP.user.service.UserService;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.existEmail(s);
    }
}

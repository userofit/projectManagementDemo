package cafytech.projectManagementDemo.registration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 *  This class is one of the classes that work for validating Registration Data
 */

//PasswordDto
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        PasswordDto pw = (PasswordDto) obj;
        return pw.getNewPassword().equals(pw.getMatchingPassword());
    }

}
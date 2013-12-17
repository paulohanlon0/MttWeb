package ie.mtt.mtttodo.web.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ie.mtt.mtttodo.web.form.LoginForm;

public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		LoginForm form = (LoginForm)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.user.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password.required");
	}

}

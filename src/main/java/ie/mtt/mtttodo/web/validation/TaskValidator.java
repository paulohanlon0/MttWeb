package ie.mtt.mtttodo.web.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ie.mtt.mtttodo.services.Task;

public class TaskValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Task.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Task task = (Task)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description.required");
		String description = task.getDescription();
		if(description.length() > 254) {
			errors.rejectValue("description", "error.description.too.long");
		}
	}
}
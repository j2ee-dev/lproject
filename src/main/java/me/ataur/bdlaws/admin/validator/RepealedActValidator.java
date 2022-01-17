package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.RepealedAct;
import me.ataur.bdlaws.admin.repository.RepealedActRepository;

import java.util.regex.Pattern;


/**
 * @author Amran
 */
@Component
public class RepealedActValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(RepealedActValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    RepealedActRepository repealedActRepository;
	public boolean supports(Class<?> clazz) {
		return RepealedAct.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		RepealedAct repealedAct = (RepealedAct) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		

		
		
		
	}
}

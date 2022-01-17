package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.ActRoleFootnote;
import me.ataur.bdlaws.admin.repository.ActRoleFootnoteRepository;

import java.util.regex.Pattern;

/**
 * @author Amran
 */
@Component
public class ActRoleFootnoteValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(ActRoleFootnoteValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    ActRoleFootnoteRepository actRoleFootnoteRepository;
	public boolean supports(Class<?> clazz) {
		return ActRoleFootnote.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ActRoleFootnote actRoleFootnote = (ActRoleFootnote) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(actRoleFootnote.getFootnoteNumber()==null){
			errors.rejectValue("footnoteNumber","ActRoleFootnote.footnoteNumber.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "footnoteNumber", "ActRoleFootnote.footnoteNumber.required");


		if(actRoleFootnote.getFootnoteSign()!=null && actRoleFootnote.getFootnoteSign().length() >255 ){
			errors.rejectValue("footnoteSign","ActRoleFootnote.footnoteSign.max_length");
		}

		if(actRoleFootnote.getStatus()==null){
			errors.rejectValue("status","ActRoleFootnote.status.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "ActRoleFootnote.status.required");

		
		
		
	}
}

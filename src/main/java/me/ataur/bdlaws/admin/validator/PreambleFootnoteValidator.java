package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.PreambleFootnote;
import me.ataur.bdlaws.admin.repository.PreambleFootnoteRepository;

import java.util.regex.Pattern;


/**
 * @author Amran
 */
@Component
public class PreambleFootnoteValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(PreambleFootnoteValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    PreambleFootnoteRepository preambleFootnoteRepository;
	public boolean supports(Class<?> clazz) {
		return PreambleFootnote.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		PreambleFootnote preambleFootnote = (PreambleFootnote) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(preambleFootnote.getFootnoteNumber()==null){
			errors.rejectValue("footnoteNumber","PreambleFootnote.footnoteNumber.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "footnoteNumber", "PreambleFootnote.footnoteNumber.required");


		if(preambleFootnote.getFootnoteSign()!=null && preambleFootnote.getFootnoteSign().length() >255 ){
			errors.rejectValue("footnoteSign","PreambleFootnote.footnoteSign.max_length");
		}

		if(preambleFootnote.getStatus()==null){
			errors.rejectValue("status","PreambleFootnote.status.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "PreambleFootnote.status.required");

		
		
		
	}
}

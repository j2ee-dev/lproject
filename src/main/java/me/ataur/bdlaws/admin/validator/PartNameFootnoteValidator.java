package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.PartNameFootnote;
import me.ataur.bdlaws.admin.repository.PartNameFootnoteRepository;

import java.util.regex.Pattern;

/**
 * @author Amran
 */
@Component
public class PartNameFootnoteValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(PartNameFootnoteValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    PartNameFootnoteRepository partNameFootnoteRepository;
	public boolean supports(Class<?> clazz) {
		return PartNameFootnote.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		PartNameFootnote partNameFootnote = (PartNameFootnote) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(partNameFootnote.getFootnoteNumber()==null){
			errors.rejectValue("footnoteNumber","PartNameFootnote.footnoteNumber.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "footnoteNumber", "PartNameFootnote.footnoteNumber.required");


		if(partNameFootnote.getFootnoteSign()!=null && partNameFootnote.getFootnoteSign().length() >255 ){
			errors.rejectValue("footnoteSign","PartNameFootnote.footnoteSign.max_length");
		}

		if(partNameFootnote.getStatus()==null){
			errors.rejectValue("status","PartNameFootnote.status.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "PartNameFootnote.status.required");

		
		
		
	}
}

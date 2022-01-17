package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.PartNoFootnote;
import me.ataur.bdlaws.admin.repository.PartNoFootnoteRepository;

import java.util.regex.Pattern;


/**
 * @author Amran
 */
@Component
public class PartNoFootnoteValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(PartNoFootnoteValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    PartNoFootnoteRepository partNoFootnoteRepository;
	public boolean supports(Class<?> clazz) {
		return PartNoFootnote.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		PartNoFootnote partNoFootnote = (PartNoFootnote) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(partNoFootnote.getFootnoteNumber()==null){
			errors.rejectValue("footnoteNumber","PartNoFootnote.footnoteNumber.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "footnoteNumber", "PartNoFootnote.footnoteNumber.required");


		if(partNoFootnote.getFootnoteSign()!=null && partNoFootnote.getFootnoteSign().length() >255 ){
			errors.rejectValue("footnoteSign","PartNoFootnote.footnoteSign.max_length");
		}

		if(partNoFootnote.getStatus()==null){
			errors.rejectValue("status","PartNoFootnote.status.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "PartNoFootnote.status.required");

		
		
		
	}
}

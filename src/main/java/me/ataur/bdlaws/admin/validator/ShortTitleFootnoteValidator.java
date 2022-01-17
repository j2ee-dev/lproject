package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.ShortTitleFootnote;
import me.ataur.bdlaws.admin.repository.ShortTitleFootnoteRepository;

import java.util.regex.Pattern;

/**
 * @author Amran
 */
@Component
public class ShortTitleFootnoteValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(ShortTitleFootnoteValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    ShortTitleFootnoteRepository shortTitleFootnoteRepository;
	public boolean supports(Class<?> clazz) {
		return ShortTitleFootnote.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ShortTitleFootnote shortTitleFootnote = (ShortTitleFootnote) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(shortTitleFootnote.getFootnoteNumber()==null){
			errors.rejectValue("footnoteNumber","ShortTitleFootnote.footnoteNumber.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "footnoteNumber", "ShortTitleFootnote.footnoteNumber.required");


		if(shortTitleFootnote.getFootnoteSign()!=null && shortTitleFootnote.getFootnoteSign().length() >255 ){
			errors.rejectValue("footnoteSign","ShortTitleFootnote.footnoteSign.max_length");
		}

		if(shortTitleFootnote.getStatus()==null){
			errors.rejectValue("status","ShortTitleFootnote.status.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "ShortTitleFootnote.status.required");

		
		
		
	}
}

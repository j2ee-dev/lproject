package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.SectionNameFootnote;
import me.ataur.bdlaws.admin.repository.SectionNameFootnoteRepository;

import java.util.regex.Pattern;

/**
 * @author Amran
 */
@Component
public class SectionNameFootnoteValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(SectionNameFootnoteValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    SectionNameFootnoteRepository sectionNameFootnoteRepository;
	public boolean supports(Class<?> clazz) {
		return SectionNameFootnote.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		SectionNameFootnote sectionNameFootnote = (SectionNameFootnote) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(sectionNameFootnote.getFootnoteNumber()==null){
			errors.rejectValue("footnoteNumber","SectionNameFootnote.footnoteNumber.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "footnoteNumber", "SectionNameFootnote.footnoteNumber.required");


		if(sectionNameFootnote.getFootnoteSign()!=null && sectionNameFootnote.getFootnoteSign().length() >255 ){
			errors.rejectValue("footnoteSign","SectionNameFootnote.footnoteSign.max_length");
		}

		if(sectionNameFootnote.getStatus()==null){
			errors.rejectValue("status","SectionNameFootnote.status.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "SectionNameFootnote.status.required");

		
		
		
	}
}

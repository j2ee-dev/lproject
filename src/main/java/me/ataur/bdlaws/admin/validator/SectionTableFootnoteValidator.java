package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.SectionTableFootnote;
import me.ataur.bdlaws.admin.repository.SectionTableFootnoteRepository;

import java.util.regex.Pattern;

/**
 * @author Amran
 */
@Component
public class SectionTableFootnoteValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(SectionTableFootnoteValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    SectionTableFootnoteRepository sectionTableFootnoteRepository;
	public boolean supports(Class<?> clazz) {
		return SectionTableFootnote.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		SectionTableFootnote sectionTableFootnote = (SectionTableFootnote) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(sectionTableFootnote.getFootnoteNumber()==null){
			errors.rejectValue("footnoteNumber","SectionTableFootnote.footnoteNumber.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "footnoteNumber", "SectionTableFootnote.footnoteNumber.required");


		if(sectionTableFootnote.getFootnoteSign()!=null && sectionTableFootnote.getFootnoteSign().length() >255 ){
			errors.rejectValue("footnoteSign","SectionTableFootnote.footnoteSign.max_length");
		}

		if(sectionTableFootnote.getStatus()==null){
			errors.rejectValue("status","SectionTableFootnote.status.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "SectionTableFootnote.status.required");

		
		
		
	}
}

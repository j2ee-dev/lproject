package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.SectionDescriptionFootnote;
import me.ataur.bdlaws.admin.repository.SectionDescriptionFootnoteRepository;

import java.util.regex.Pattern;


/**
 * @author Amran
 */
@Component
public class SectionDescriptionFootnoteValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(SectionDescriptionFootnoteValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    SectionDescriptionFootnoteRepository sectionDescriptionFootnoteRepository;
	public boolean supports(Class<?> clazz) {
		return SectionDescriptionFootnote.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		SectionDescriptionFootnote sectionDescriptionFootnote = (SectionDescriptionFootnote) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(sectionDescriptionFootnote.getFootnoteNumber()==null){
			errors.rejectValue("footnoteNumber","SectionDescriptionFootnote.footnoteNumber.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "footnoteNumber", "SectionDescriptionFootnote.footnoteNumber.required");


		if(sectionDescriptionFootnote.getFootnoteSign()!=null && sectionDescriptionFootnote.getFootnoteSign().length() >255 ){
			errors.rejectValue("footnoteSign","SectionDescriptionFootnote.footnoteSign.max_length");
		}

		if(sectionDescriptionFootnote.getStatus()==null){
			errors.rejectValue("status","SectionDescriptionFootnote.status.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "SectionDescriptionFootnote.status.required");

		
		
		
	}
}

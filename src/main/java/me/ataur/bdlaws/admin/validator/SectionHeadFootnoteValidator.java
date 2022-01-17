package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.SectionHeadFootnote;
import me.ataur.bdlaws.admin.repository.SectionHeadFootnoteRepository;

import java.util.regex.Pattern;

/**
 * @author Amran
 */
@Component
public class SectionHeadFootnoteValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(SectionHeadFootnoteValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    SectionHeadFootnoteRepository sectionHeadFootnoteRepository;
	public boolean supports(Class<?> clazz) {
		return SectionHeadFootnote.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		SectionHeadFootnote sectionHeadFootnote = (SectionHeadFootnote) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(sectionHeadFootnote.getFootnoteNumber()==null){
			errors.rejectValue("footnoteNumber","SectionHeadFootnote.footnoteNumber.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "footnoteNumber", "SectionHeadFootnote.footnoteNumber.required");


		if(sectionHeadFootnote.getFootnoteSign()!=null && sectionHeadFootnote.getFootnoteSign().length() >255 ){
			errors.rejectValue("footnoteSign","SectionHeadFootnote.footnoteSign.max_length");
		}

		if(sectionHeadFootnote.getStatus()==null){
			errors.rejectValue("status","SectionHeadFootnote.status.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "SectionHeadFootnote.status.required");

		
		
		
	}
}

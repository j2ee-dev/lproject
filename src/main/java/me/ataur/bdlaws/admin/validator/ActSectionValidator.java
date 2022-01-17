package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.ActSection;
import me.ataur.bdlaws.admin.repository.ActSectionRepository;

import java.util.regex.Pattern;

/**
 * @author Amran
 */
@Component
public class ActSectionValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(ActSectionValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    ActSectionRepository actSectionRepository;
	public boolean supports(Class<?> clazz) {
		return ActSection.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ActSection actSection = (ActSection) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(actSection.getSectionNo()==null){
			errors.rejectValue("sectionNo","ActSection.sectionNo.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sectionNo", "ActSection.sectionNo.required");


		if(actSection.getSectionName()!=null && actSection.getSectionName().length() >255 ){
			errors.rejectValue("sectionName","ActSection.sectionName.max_length");
		}


		if(actSection.getAttachment()!=null && actSection.getAttachment().length() >255 ){
			errors.rejectValue("attachment","ActSection.attachment.max_length");
		}

		if(actSection.getStatus()==null){
			errors.rejectValue("status","ActSection.status.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "ActSection.status.required");

		
		
		
	}
}

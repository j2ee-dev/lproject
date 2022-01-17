package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.ActSchedule;
import me.ataur.bdlaws.admin.repository.ActScheduleRepository;

import java.util.regex.Pattern;


/**
 * @author Amran
 */
@Component
public class ActScheduleValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(ActScheduleValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    ActScheduleRepository actScheduleRepository;
	public boolean supports(Class<?> clazz) {
		return ActSchedule.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ActSchedule actSchedule = (ActSchedule) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(actSchedule.getTitle()==null){
			errors.rejectValue("title","ActSchedule.title.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "ActSchedule.title.required");


		if(actSchedule.getTitle()!=null && actSchedule.getTitle().length() >255 ){
			errors.rejectValue("title","ActSchedule.title.max_length");
		}

		if(actSchedule.getAttachment()==null){
			errors.rejectValue("attachment","ActSchedule.attachment.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "attachment", "ActSchedule.attachment.required");


		if(actSchedule.getAttachment()!=null && actSchedule.getAttachment().length() >255 ){
			errors.rejectValue("attachment","ActSchedule.attachment.max_length");
		}

		if(actSchedule.getStatus()==null){
			errors.rejectValue("status","ActSchedule.status.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "ActSchedule.status.required");

		
		
		
	}
}

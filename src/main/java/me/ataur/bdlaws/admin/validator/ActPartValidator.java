package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.ActPart;
import me.ataur.bdlaws.admin.repository.ActPartRepository;

import java.util.regex.Pattern;

/**
 * @author Amran
 */
@Component
public class ActPartValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(ActPartValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    ActPartRepository actPartRepository;
	public boolean supports(Class<?> clazz) {
		return ActPart.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ActPart actPart = (ActPart) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(actPart.getPartNo()==null){
			errors.rejectValue("partNo","ActPart.partNo.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "partNo", "ActPart.partNo.required");


		if(actPart.getPartNo()!=null && actPart.getPartNo().length() >255 ){
			errors.rejectValue("partNo","ActPart.partNo.max_length");
		}

		if(actPart.getPartName()==null){
			errors.rejectValue("partName","ActPart.partName.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "partName", "ActPart.partName.required");


		if(actPart.getPartName()!=null && actPart.getPartName().length() >255 ){
			errors.rejectValue("partName","ActPart.partName.max_length");
		}

		if(actPart.getPartOrder()==null){
			errors.rejectValue("partOrder","ActPart.partOrder.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "partOrder", "ActPart.partOrder.required");


		if(actPart.getAttachment()!=null && actPart.getAttachment().length() >255 ){
			errors.rejectValue("attachment","ActPart.attachment.max_length");
		}

		if(actPart.getStatus()==null){
			errors.rejectValue("status","ActPart.status.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "ActPart.status.required");

		
		
		
	}
}

package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.Act;
import me.ataur.bdlaws.admin.repository.ActRepository;

import java.util.regex.Pattern;

/**
 * @author Amran
 */
@Component
public class ActValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(ActValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    ActRepository actRepository;
	public boolean supports(Class<?> clazz) {
		return Act.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Act act = (Act) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(act.getShortTitle()==null){
			errors.rejectValue("shortTitle","Act.shortTitle.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shortTitle", "Act.shortTitle.required");


		if(act.getShortTitle()!=null && act.getShortTitle().length() >255 ){
			errors.rejectValue("shortTitle","Act.shortTitle.max_length");
		}


		if(act.getNumber()!=null && act.getNumber().length() >255 ){
			errors.rejectValue("number","Act.number.max_length");
		}


		if(act.getYear()!=null && act.getYear().length() >255 ){
			errors.rejectValue("year","Act.year.max_length");
		}


		if(act.getPage()!=null && act.getPage().length() >255 ){
			errors.rejectValue("page","Act.page.max_length");
		}


		if(act.getPublishDate()!=null && act.getPublishDate().length() >255 ){
			errors.rejectValue("publishDate","Act.publishDate.max_length");
		}


		if(act.getPreambleAttachment()!=null && act.getPreambleAttachment().length() >255 ){
			errors.rejectValue("preambleAttachment","Act.preambleAttachment.max_length");
		}


		/*if(act.getActAttachment()!=null && act.getActAttachment().length() >255 ){
			errors.rejectValue("actAttachment","Act.actAttachment.max_length");
		}*/

		
		
		
	}
}

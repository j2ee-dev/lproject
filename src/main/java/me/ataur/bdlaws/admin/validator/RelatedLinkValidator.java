package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.RelatedLink;
import me.ataur.bdlaws.admin.repository.RelatedLinkRepository;

import java.util.regex.Pattern;

/**
 * @author Amran
 */
@Component
public class RelatedLinkValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(RelatedLinkValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    RelatedLinkRepository relatedLinkRepository;
	public boolean supports(Class<?> clazz) {
		return RelatedLink.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		RelatedLink relatedLink = (RelatedLink) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(relatedLink.getTitleEnglish()==null){
			errors.rejectValue("titleEnglish","RelatedLink.titleEnglish.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titleEnglish", "RelatedLink.titleEnglish.required");


		if(relatedLink.getTitleEnglish()!=null && relatedLink.getTitleEnglish().length() >255 ){
			errors.rejectValue("titleEnglish","RelatedLink.titleEnglish.max_length");
		}

		if(relatedLink.getTitleBangla()==null){
			errors.rejectValue("titleBangla","RelatedLink.titleBangla.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titleBangla", "RelatedLink.titleBangla.required");


		if(relatedLink.getTitleBangla()!=null && relatedLink.getTitleBangla().length() >255 ){
			errors.rejectValue("titleBangla","RelatedLink.titleBangla.max_length");
		}

		if(relatedLink.getLink()==null){
			errors.rejectValue("link","RelatedLink.link.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "link", "RelatedLink.link.required");


		if(relatedLink.getLink()!=null && relatedLink.getLink().length() >255 ){
			errors.rejectValue("link","RelatedLink.link.max_length");
		}

		if(relatedLink.getStatus()==null){
			errors.rejectValue("status","RelatedLink.status.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "RelatedLink.status.required");

		
		
		
	}
}

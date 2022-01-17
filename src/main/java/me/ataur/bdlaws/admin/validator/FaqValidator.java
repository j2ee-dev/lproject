package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.Faq;
import me.ataur.bdlaws.admin.repository.FaqRepository;

import java.util.regex.Pattern;


/**
 * @author Amran
 */
@Component
public class FaqValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(FaqValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    FaqRepository faqRepository;
	public boolean supports(Class<?> clazz) {
		return Faq.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Faq faq = (Faq) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(faq.getQuestionEnglish()==null){
			errors.rejectValue("questionEnglish","Faq.questionEnglish.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "questionEnglish", "Faq.questionEnglish.required");


		if(faq.getQuestionEnglish()!=null && faq.getQuestionEnglish().length() >255 ){
			errors.rejectValue("questionEnglish","Faq.questionEnglish.max_length");
		}

		if(faq.getQuestionBangla()==null){
			errors.rejectValue("questionBangla","Faq.questionBangla.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "questionBangla", "Faq.questionBangla.required");


		if(faq.getQuestionBangla()!=null && faq.getQuestionBangla().length() >255 ){
			errors.rejectValue("questionBangla","Faq.questionBangla.max_length");
		}

		if(faq.getStatus()==null){
			errors.rejectValue("status","Faq.status.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "Faq.status.required");

		
		
		
	}
}

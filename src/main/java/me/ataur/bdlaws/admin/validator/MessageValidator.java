package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.Message;
import me.ataur.bdlaws.admin.repository.MessageRepository;

import java.util.regex.Pattern;


/**
 * @author Amran
 */
@Component
public class MessageValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(MessageValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    MessageRepository messageRepository;
	public boolean supports(Class<?> clazz) {
		return Message.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Message message = (Message) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(message.getCode()==null){
			errors.rejectValue("code","Message.code.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "Message.code.required");


		if(messageRepository.findByLocaleAndCode(message.getLocale(),message.getCode())!=null && message.getId()!=messageRepository.findByLocaleAndCode(message.getLocale(),message.getCode()).getId()){
		 	
			errors.rejectValue("locale","Message.LocaleAndCode.group_unique");
								

			errors.rejectValue("code","Message.LocaleAndCode.group_unique");
								
		}


		if(message.getCode()!=null && message.getCode().length() >50 ){
			errors.rejectValue("code","Message.code.max_length");
		}

		if(message.getLocale()==null){
			errors.rejectValue("locale","Message.locale.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "locale", "Message.locale.required");


		if(message.getLocale()!=null && message.getLocale().length() >10 ){
			errors.rejectValue("locale","Message.locale.max_length");
		}

		if(message.getValue()==null){
			errors.rejectValue("value","Message.value.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", "Message.value.required");

		
		
		
	}
}

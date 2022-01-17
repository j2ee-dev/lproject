package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.News;
import me.ataur.bdlaws.admin.repository.NewsRepository;

import java.util.regex.Pattern;

/**
 * @author Amran
 */
@Component
public class NewsValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(NewsValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    NewsRepository newsRepository;
	public boolean supports(Class<?> clazz) {
		return News.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		News news = (News) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(news.getTitleEnglish()==null){
			errors.rejectValue("titleEnglish","News.titleEnglish.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titleEnglish", "News.titleEnglish.required");


		if(news.getTitleEnglish()!=null && news.getTitleEnglish().length() >255 ){
			errors.rejectValue("titleEnglish","News.titleEnglish.max_length");
		}

		if(news.getTitleBangla()==null){
			errors.rejectValue("titleBangla","News.titleBangla.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titleBangla", "News.titleBangla.required");


		if(news.getTitleBangla()!=null && news.getTitleBangla().length() >255 ){
			errors.rejectValue("titleBangla","News.titleBangla.max_length");
		}

		if(news.getStatus()==null){
			errors.rejectValue("status","News.status.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "News.status.required");

		
		
		
	}
}

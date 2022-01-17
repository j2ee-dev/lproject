package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.ActChapter;
import me.ataur.bdlaws.admin.repository.ActChapterRepository;

import java.util.regex.Pattern;

/**
 * @author Amran
 */
@Component
public class ActChapterValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(ActChapterValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    ActChapterRepository actChapterRepository;
	public boolean supports(Class<?> clazz) {
		return ActChapter.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ActChapter actChapter = (ActChapter) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(actChapter.getChapterNo()==null){
			errors.rejectValue("chapterNo","ActChapter.chapterNo.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "chapterNo", "ActChapter.chapterNo.required");


		if(actChapter.getChapterNo()!=null && actChapter.getChapterNo().length() >255 ){
			errors.rejectValue("chapterNo","ActChapter.chapterNo.max_length");
		}

		if(actChapter.getChapterName()==null){
			errors.rejectValue("chapterName","ActChapter.chapterName.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "chapterName", "ActChapter.chapterName.required");


		if(actChapter.getChapterName()!=null && actChapter.getChapterName().length() >255 ){
			errors.rejectValue("chapterName","ActChapter.chapterName.max_length");
		}

		if(actChapter.getChapterOrder()==null){
			errors.rejectValue("chapterOrder","ActChapter.chapterOrder.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "chapterOrder", "ActChapter.chapterOrder.required");


		if(actChapter.getAttachment()!=null && actChapter.getAttachment().length() >255 ){
			errors.rejectValue("attachment","ActChapter.attachment.max_length");
		}

		if(actChapter.getStatus()==null){
			errors.rejectValue("status","ActChapter.status.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "ActChapter.status.required");

		
		
		
	}
}

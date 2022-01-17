package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.ChapterNameFootnote;
import me.ataur.bdlaws.admin.repository.ChapterNameFootnoteRepository;

import java.util.regex.Pattern;

/**
 * @author Amran
 */
@Component
public class ChapterNameFootnoteValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(ChapterNameFootnoteValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    ChapterNameFootnoteRepository chapterNameFootnoteRepository;
	public boolean supports(Class<?> clazz) {
		return ChapterNameFootnote.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ChapterNameFootnote chapterNameFootnote = (ChapterNameFootnote) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(chapterNameFootnote.getFootnoteNumber()==null){
			errors.rejectValue("footnoteNumber","ChapterNameFootnote.footnoteNumber.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "footnoteNumber", "ChapterNameFootnote.footnoteNumber.required");


		if(chapterNameFootnote.getFootnoteSign()!=null && chapterNameFootnote.getFootnoteSign().length() >255 ){
			errors.rejectValue("footnoteSign","ChapterNameFootnote.footnoteSign.max_length");
		}

		if(chapterNameFootnote.getStatus()==null){
			errors.rejectValue("status","ChapterNameFootnote.status.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "ChapterNameFootnote.status.required");

		
		
		
	}
}

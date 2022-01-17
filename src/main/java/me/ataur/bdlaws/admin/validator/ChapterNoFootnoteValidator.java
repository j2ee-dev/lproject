package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.ChapterNoFootnote;
import me.ataur.bdlaws.admin.repository.ChapterNoFootnoteRepository;

import java.util.regex.Pattern;


/**
 * @author Amran
 */
@Component
public class ChapterNoFootnoteValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(ChapterNoFootnoteValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    ChapterNoFootnoteRepository chapterNoFootnoteRepository;
	public boolean supports(Class<?> clazz) {
		return ChapterNoFootnote.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ChapterNoFootnote chapterNoFootnote = (ChapterNoFootnote) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(chapterNoFootnote.getFootnoteNumber()==null){
			errors.rejectValue("footnoteNumber","ChapterNoFootnote.footnoteNumber.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "footnoteNumber", "ChapterNoFootnote.footnoteNumber.required");


		if(chapterNoFootnote.getFootnoteSign()!=null && chapterNoFootnote.getFootnoteSign().length() >255 ){
			errors.rejectValue("footnoteSign","ChapterNoFootnote.footnoteSign.max_length");
		}

		if(chapterNoFootnote.getStatus()==null){
			errors.rejectValue("status","ChapterNoFootnote.status.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "ChapterNoFootnote.status.required");

		
		
		
	}
}

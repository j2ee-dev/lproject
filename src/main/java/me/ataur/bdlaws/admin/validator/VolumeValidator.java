package me.ataur.bdlaws.admin.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ataur.bdlaws.admin.model.Volume;
import me.ataur.bdlaws.admin.repository.VolumeRepository;

import java.util.regex.Pattern;

/**
 * @author Amran
 */
@Component
public class VolumeValidator implements Validator {
	private static final Logger logger        = LoggerFactory.getLogger(VolumeValidator.class);
	private static final Pattern emailPattern = Pattern.compile("/^([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$/i");
	@Autowired
    VolumeRepository volumeRepository;
	public boolean supports(Class<?> clazz) {
		return Volume.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Volume volume = (Volume) target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "field_name", "validation.properties");
		
		//Business validation

		// if(condition){
		// 	errors.rejectValue("field_name","validation.properties");
		// }

		
		if(volume.getVolumeNumber()==null){
			errors.rejectValue("volumeNumber","Volume.volumeNumber.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "volumeNumber", "Volume.volumeNumber.required");


		if(volumeRepository.findByVolumeNumber(volume.getVolumeNumber())!=null && volume.getId()!=volumeRepository.findByVolumeNumber(volume.getVolumeNumber()).getId()){
		 	errors.rejectValue("volumeNumber","Volume.volumeNumber.is_unique");
		}

		if(volume.getVolumeName()==null){
			errors.rejectValue("volumeName","Volume.volumeName.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "volumeName", "Volume.volumeName.required");


		if(volume.getVolumeName()!=null && volume.getVolumeName().length() >100 ){
			errors.rejectValue("volumeName","Volume.volumeName.max_length");
		}

		if(volume.getVolumeHead()==null){
			errors.rejectValue("volumeHead","Volume.volumeHead.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "volumeHead", "Volume.volumeHead.required");


		if(volume.getVolumeHead()!=null && volume.getVolumeHead().length() >255 ){
			errors.rejectValue("volumeHead","Volume.volumeHead.max_length");
		}


		if(volume.getVolumeYear()!=null && volume.getVolumeYear().length() >255 ){
			errors.rejectValue("volumeYear","Volume.volumeYear.max_length");
		}

		if(volume.getStatus()==null){
			errors.rejectValue("status","Volume.status.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "Volume.status.required");

		if(volume.getIsBangla()==null){
			errors.rejectValue("isBangla","Volume.isBangla.required");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isBangla", "Volume.isBangla.required");

		
		
		
	}
}

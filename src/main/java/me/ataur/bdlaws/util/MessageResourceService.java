package me.ataur.bdlaws.util;

import me.ataur.bdlaws.util.model.MessageResource;
import me.ataur.bdlaws.util.repository.MessageResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Amran
 */
@Service
public class MessageResourceService {
    @Autowired
    MessageResourceRepository messageResourceRepository;
    public MessageResource findByCodeAndLocale(String code, String locale){
        return messageResourceRepository.findByCodeAndLocale(code,locale);
    }
}

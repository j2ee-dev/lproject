package me.ataur.bdlaws.admin.controller;

import com.fasterxml.jackson.annotation.JsonView;
import me.ataur.bdlaws.admin.model.ActGazettedCopyForAct;
import me.ataur.bdlaws.admin.model.ActSchedule;
import me.ataur.bdlaws.admin.repository.ActGazetedCopyRepository;
import me.ataur.bdlaws.admin.repository.ActRepository;
import me.ataur.bdlaws.admin.repository.ActScheduleDataTableRepository;
import me.ataur.bdlaws.admin.repository.ActScheduleRepository;
import me.ataur.bdlaws.admin.validator.ActScheduleValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*IMPORT_EXTRA_MODEL*/

/**
 * Created by imran hossain
 */

@Controller
@RequestMapping({"/admin/ActGazettedCopy", "/admin/actgazettedcopy"})
public class GazettedCopyController extends MyBaseController {

    @Value("${deletionPath}")
    String deletionPath;

    private static final Logger logger = LoggerFactory.getLogger(GazettedCopyController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    ActGazetedCopyRepository actGazettedCopyRepository;


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id) {

        ActGazettedCopyForAct actGazettedCopyForAct = actGazettedCopyRepository.findOne(id);
        try {
            File file = new File(deletionPath +actGazettedCopyForAct.getAttachment());
            if(file.delete()) {
                System.out.println(file.getName() + "Act Gazzeted Copy File Is Deleted!");
            } else {
                System.out.println("Delete operation Is Failed.");
            }
        }
        catch(Exception e) {
            System.out.println("Failed to Delete File !!");
        }
        actGazettedCopyRepository.delete(id);
        Map<String, Object> response = new HashMap();
        response.put("success", true);
        response.put("message", "You have successfully deleted the record");
        return response;
        /*actGazettedCopyRepository.delete(id);
        Map<String, Object> response = new HashMap();
        response.put("success", true);
        response.put("message", "You have successfully deleted ActGazetedCopy");
        return response;*/
    }
}

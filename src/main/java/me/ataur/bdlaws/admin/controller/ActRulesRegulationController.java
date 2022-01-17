package me.ataur.bdlaws.admin.controller;


import me.ataur.bdlaws.admin.model.ActAttachment;
import me.ataur.bdlaws.admin.model.ActRulesRegulation;
import me.ataur.bdlaws.admin.repository.ActAttachmentRepository;
import me.ataur.bdlaws.admin.repository.ActRulesRegulationForActRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by imran hossain
 */

@Controller
@RequestMapping({"/admin/ActRulesRegulation", "/admin/actrulesregulation"})
public class ActRulesRegulationController extends MyBaseController {


    @Value("${deletionPath}")
    String deletionPath;


    private static final Logger logger = LoggerFactory.getLogger(ActRulesRegulationController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    ActRulesRegulationForActRepository actRulesRegulationForActRepository;

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id, Model model) {


        ActRulesRegulation actRulesRegulation = actRulesRegulationForActRepository.findOne(id);
        try {
            File file = new File(deletionPath + actRulesRegulation.getAttachment());
            if (file.delete()) {
                System.out.println(file.getName() + " ActAttachment From Act Controller File Is Deleted!");
            } else {
                System.out.println("Delete operation Is Failed.");
            }
        } catch (Exception e) {
            System.out.println("Failed to Delete File !!");
        }

        actRulesRegulationForActRepository.delete(id);
        Map<String, Object> response = new HashMap();
        response.put("success", true);
        response.put("message", "You have successfully deleted the record");
        return response;
    }

}

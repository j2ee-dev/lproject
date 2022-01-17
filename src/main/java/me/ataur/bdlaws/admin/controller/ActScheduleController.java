package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.ActSchedule;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.ActScheduleRepository;
import me.ataur.bdlaws.admin.repository.ActScheduleDataTableRepository;


import me.ataur.bdlaws.admin.repository.ActRepository;

import me.ataur.bdlaws.admin.validator.ActScheduleValidator;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by imran hossain
 */

@Controller
@RequestMapping({"/admin/ActSchedule", "/admin/actSchedule"})
public class ActScheduleController extends MyBaseController {
    @Value("${deletionPath}")
    String deletionPath;

    private static final Logger logger = LoggerFactory.getLogger(ActScheduleController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    ActScheduleRepository actScheduleRepository;
    @Autowired
    ActScheduleDataTableRepository actScheduleDataTableRepository;


    @Autowired
    ActRepository actRepository;

    @Autowired
    ActScheduleValidator actScheduleValidator;

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "admin/pages/ActSchedule/datatable";
    }

    @RequestMapping(value = {"/create", "/create/*"}, method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {

        ActSchedule actSchedule = new ActSchedule();
        model.addAttribute("actSchedule", actSchedule);
        model.addAttribute("action", "/create");

        model.addAttribute("actList", actRepository.findAllByOrderByShortTitleAsc());

        return "admin/pages/ActSchedule/create";
    }


    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String save(Model model, @Valid @ModelAttribute("actSchedule") ActSchedule actSchedule, BindingResult result, HttpServletRequest request, @RequestParam("attachmentFile") MultipartFile attachmentFile) {


        String attachment = uploadFile(attachmentFile);

        actSchedule.setAttachment(null);

        if (attachment == null) {
            actSchedule.setAttachment(null);
        } else {
            actSchedule.setAttachment(attachment);
        }


        actScheduleValidator.validate(actSchedule, result);
        if (result.hasErrors()) {
            model.addAttribute("actSchedule", actSchedule);
            model.addAttribute("action", "/create");

            model.addAttribute("actList", actRepository.findAllByOrderByShortTitleAsc());

            return "admin/pages/ActSchedule/create";
        }





        /*CHILD_TABLE_DATA_CREATE*/


        if (actSchedule.getActId().getId() == 0) {
            actSchedule.setActId(null);
        }


        actSchedule.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        actSchedule.setCreatedAt(new Date());
        actScheduleRepository.save(actSchedule);
        return "redirect:/admin/actSchedule";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {


        ActSchedule actSchedule = actScheduleRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("actSchedule", actSchedule);
        model.addAttribute("action", "/edit/" + id);

        model.addAttribute("actList", actRepository.findAllByOrderByShortTitleAsc());

        return "admin/pages/ActSchedule/edit";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id, Model model, @Valid @ModelAttribute("actSchedule") ActSchedule actSchedule, BindingResult result, HttpServletRequest request, @RequestParam("attachmentFile") MultipartFile attachmentFile) {

        ActSchedule actScheduleOld = actScheduleRepository.findOne(id);

        String attachment = uploadFile(attachmentFile);

        actSchedule.setAttachment(null);

        if (attachment == null) {
            actSchedule.setAttachment(actScheduleOld.getAttachment());
        } else {
            actSchedule.setAttachment(attachment);
        }

        actScheduleValidator.validate(actSchedule, result);
        if (result.hasErrors()) {
            model.addAttribute("actSchedule", actSchedule);
            model.addAttribute("action", "/edit/" + id);

            model.addAttribute("actList", actRepository.findAllByOrderByShortTitleAsc());

            return "admin/pages/ActSchedule/edit";
        }

        
        
        /*CHILD_TABLE_DATA_EDIT*/


        if (actSchedule.getActId().getId() == 0) {
            actSchedule.setActId(null);
        }


        actSchedule.setCreatedBy(actScheduleOld.getCreatedBy());
        actSchedule.setCreatedAt(actScheduleOld.getCreatedAt());

        actSchedule.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        actSchedule.setUpdatedAt(new Date());

        actScheduleRepository.save(actSchedule);
        return "redirect:/admin/actSchedule";
    }


    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

        ActSchedule actSchedule = actScheduleRepository.findOne(id);
        model.addAttribute("actSchedule", actSchedule);
        return "admin/pages/ActSchedule/details";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id) {

        ActSchedule actSchedule = actScheduleRepository.findOne(id);
                try {
                    File file = new File(deletionPath +actSchedule.getAttachment());
                    if(file.delete()) {
                System.out.println(file.getName() + "Act Schedule File Is Deleted!");
            } else {
                System.out.println("Delete operation Is Failed.");
            }
        }
        catch(Exception e) {
            System.out.println("Failed to Delete File !!");
        }
        actScheduleRepository.delete(id);
        Map<String, Object> response = new HashMap();
        response.put("success", true);
        response.put("message", "You have successfully deleted the Act Schedule");
        return response;
        /*actScheduleRepository.delete(id);
        Map<String, Object> response = new HashMap();
        response.put("success", true);
        response.put("message", "You have successfully deleted the record");
        return response;*/
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ActSchedule getById(@PathVariable("id") Integer id, Model model) {
        ActSchedule actSchedule = actScheduleRepository.findOne(id);
        return actSchedule;
    }


    @RequestMapping(value = "/dataApi", method = RequestMethod.GET)
    @ResponseBody
    public List<ActSchedule> data() {
        return actScheduleRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST, headers = "Accept=application/json")
    public DataTablesOutput<ActSchedule> getActSchedules(@Valid @RequestBody DataTablesInput input) {

        return actScheduleDataTableRepository.findAll(input);
    }

    /*EXTRA_METHOD*/

}

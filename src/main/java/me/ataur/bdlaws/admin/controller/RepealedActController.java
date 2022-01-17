package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.RepealedAct;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.RepealedActRepository;
import me.ataur.bdlaws.admin.repository.RepealedActDataTableRepository;


import me.ataur.bdlaws.admin.repository.ActRepository;

import me.ataur.bdlaws.admin.validator.RepealedActValidator;

import com.fasterxml.jackson.annotation.JsonView;
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
@RequestMapping({"/admin/RepealedAct", "/admin/repealedAct"})
public class RepealedActController extends MyBaseController {

    private static final Logger logger = LoggerFactory.getLogger(RepealedActController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    RepealedActRepository repealedActRepository;
    @Autowired
    RepealedActDataTableRepository repealedActDataTableRepository;


    @Autowired
    ActRepository actRepository;

    @Autowired
    RepealedActValidator repealedActValidator;

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "admin/pages/RepealedAct/datatable";
    }

    @RequestMapping(value = {"/create", "/create/*"}, method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {

        RepealedAct repealedAct = new RepealedAct();
        model.addAttribute("repealedAct", repealedAct);
        model.addAttribute("action", "/create");

        model.addAttribute("actList", actRepository.findAllByOrderByShortTitleAsc());

        return "admin/pages/RepealedAct/create";
    }


    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String save(Model model, @Valid @ModelAttribute("repealedAct") RepealedAct repealedAct, BindingResult result, HttpServletRequest request) {


        repealedActValidator.validate(repealedAct, result);
        if (result.hasErrors()) {
            model.addAttribute("repealedAct", repealedAct);
            model.addAttribute("action", "/create");

            model.addAttribute("actList", actRepository.findAllByOrderByShortTitleAsc());

            return "admin/pages/RepealedAct/create";
        }
        
        

        

        /*CHILD_TABLE_DATA_CREATE*/


        if (repealedAct.getActId().getId() == 0) {
            repealedAct.setActId(null);
        }


        repealedAct.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        repealedAct.setCreatedAt(new Date());
        repealedActRepository.save(repealedAct);
        return "admin/pages/RepealedAct/details";
        /*return "redirect:/admin/repealedAct";*/
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {


        RepealedAct repealedAct = repealedActRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("repealedAct", repealedAct);
        model.addAttribute("action", "/edit/" + id);

        model.addAttribute("actList", actRepository.findAllByOrderByShortTitleAsc());

        return "admin/pages/RepealedAct/edit";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id, Model model, @Valid @ModelAttribute("repealedAct") RepealedAct repealedAct, BindingResult result, HttpServletRequest request) {

        RepealedAct repealedActOld = repealedActRepository.findOne(id);

        repealedActValidator.validate(repealedAct, result);
        if (result.hasErrors()) {
            model.addAttribute("repealedAct", repealedAct);
            model.addAttribute("action", "/edit/" + id);

            model.addAttribute("actList", actRepository.findAllByOrderByShortTitleAsc());

            return "admin/pages/RepealedAct/edit";
        }

        
        
        /*CHILD_TABLE_DATA_EDIT*/


        if (repealedAct.getActId().getId() == 0) {
            repealedAct.setActId(null);
        }


        repealedAct.setCreatedBy(repealedActOld.getCreatedBy());
        repealedAct.setCreatedAt(repealedActOld.getCreatedAt());

        repealedAct.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        repealedAct.setUpdatedAt(new Date());

        repealedActRepository.save(repealedAct);
        return "redirect:/admin/repealedAct";
    }


    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

        RepealedAct repealedAct = repealedActRepository.findOne(id);
        model.addAttribute("repealedAct", repealedAct);
        return "admin/pages/RepealedAct/details";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id, Model model) {
        repealedActRepository.delete(id);
        Map<String, Object> response = new HashMap();

        response.put("success", true);
        response.put("message", "You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RepealedAct getById(@PathVariable("id") Integer id, Model model) {
        RepealedAct repealedAct = repealedActRepository.findOne(id);
        return repealedAct;
    }


    @RequestMapping(value = "/dataApi", method = RequestMethod.GET)
    @ResponseBody
    public List<RepealedAct> data() {
        return repealedActRepository.findAll();
    }

 /*   @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST, headers = "Accept=application/json")
    public DataTablesOutput<RepealedAct> getRepealedActs(@Valid @RequestBody DataTablesInput input) {

        return repealedActDataTableRepository.findAll(input);
    }*/
    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST, headers = "Accept=application/json")
    public DataTablesOutput<RepealedAct> getActParts(@Valid @RequestBody DataTablesInput input) {

        return repealedActDataTableRepository.findAll(input);
    }
    /*EXTRA_METHOD*/

}

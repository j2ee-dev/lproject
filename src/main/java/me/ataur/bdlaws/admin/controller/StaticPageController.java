package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.StaticPage;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.StaticPageRepository;
import me.ataur.bdlaws.admin.repository.StaticPageDataTableRepository;

/*IMPORT_EXTRA_REPOSITORY*/

import me.ataur.bdlaws.admin.validator.StaticPageValidator;

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
@RequestMapping({"/admin/StaticPage", "/admin/staticPage"})
public class StaticPageController extends MyBaseController {

    private static final Logger logger = LoggerFactory.getLogger(StaticPageController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    StaticPageRepository staticPageRepository;
    @Autowired
    StaticPageDataTableRepository staticPageDataTableRepository;


    /*AUTOWIRED_EXTRA_REPOSITORY*/
    @Autowired
    StaticPageValidator staticPageValidator;

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "admin/pages/StaticPage/datatable";
    }

    @RequestMapping(value = {"/create", "/create/*"}, method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {

        StaticPage staticPage = new StaticPage();
        model.addAttribute("staticPage", staticPage);
        model.addAttribute("action", "/create");
        /*EXTA_PARAMETER*/
        return "admin/pages/StaticPage/create";
    }


    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String save(Model model, @Valid @ModelAttribute("staticPage") StaticPage staticPage, BindingResult result, HttpServletRequest request) {


        staticPageValidator.validate(staticPage, result);
        if (result.hasErrors()) {
            model.addAttribute("staticPage", staticPage);
            model.addAttribute("action", "/create");
            /*EXTA_PARAMETER*/
            return "admin/pages/StaticPage/create";
        }
        
        

        

        /*CHILD_TABLE_DATA_CREATE*/

        /*SET_NULL_TO_FOREIN_KEY*/
        staticPage.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        staticPage.setCreatedAt(new Date());
        staticPageRepository.save(staticPage);
        return "redirect:/admin/staticPage";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {


        StaticPage staticPage = staticPageRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("staticPage", staticPage);
        model.addAttribute("action", "/edit/" + id);
        /*EXTA_PARAMETER*/
        return "admin/pages/StaticPage/edit";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id, Model model, @Valid @ModelAttribute("staticPage") StaticPage staticPage, BindingResult result, HttpServletRequest request) {

        StaticPage staticPageOld = staticPageRepository.findOne(id);

        staticPageValidator.validate(staticPage, result);
        if (result.hasErrors()) {
            model.addAttribute("staticPage", staticPage);
            model.addAttribute("action", "/edit/" + id);
            /*EXTA_PARAMETER*/
            return "admin/pages/StaticPage/edit";
        }

        
        
        /*CHILD_TABLE_DATA_EDIT*/
        
        /*SET_NULL_TO_FOREIN_KEY*/
        staticPage.setCreatedBy(staticPageOld.getCreatedBy());
        staticPage.setCreatedAt(staticPageOld.getCreatedAt());

        staticPage.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        staticPage.setUpdatedAt(new Date());

        staticPageRepository.save(staticPage);
        return "redirect:/admin/staticPage";
    }


    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

        StaticPage staticPage = staticPageRepository.findOne(id);
        model.addAttribute("staticPage", staticPage);
        return "admin/pages/StaticPage/details";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id, Model model) {
        staticPageRepository.delete(id);
        Map<String, Object> response = new HashMap();

        response.put("success", true);
        response.put("message", "You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public StaticPage getById(@PathVariable("id") Integer id, Model model) {
        StaticPage staticPage = staticPageRepository.findOne(id);
        return staticPage;
    }


    @RequestMapping(value = "/dataApi", method = RequestMethod.GET)
    @ResponseBody
    public List<StaticPage> data() {
        return staticPageRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST, headers = "Accept=application/json")
    public DataTablesOutput<StaticPage> getStaticPages(@Valid @RequestBody DataTablesInput input) {

        return staticPageDataTableRepository.findAll(input);
    }

    /*EXTRA_METHOD*/

}

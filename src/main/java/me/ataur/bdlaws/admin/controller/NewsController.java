package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.News;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.NewsRepository;
import me.ataur.bdlaws.admin.repository.NewsDataTableRepository;

/*IMPORT_EXTRA_REPOSITORY*/

import me.ataur.bdlaws.admin.validator.NewsValidator;

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
@RequestMapping({"/admin/News", "/admin/news"})
public class NewsController extends MyBaseController {

    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    NewsRepository newsRepository;
    @Autowired
    NewsDataTableRepository newsDataTableRepository;


    /*AUTOWIRED_EXTRA_REPOSITORY*/
    @Autowired
    NewsValidator newsValidator;

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "admin/pages/News/datatable";
    }

    @RequestMapping(value = {"/create", "/create/*"}, method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {

        News news = new News();
        model.addAttribute("news", news);
        model.addAttribute("action", "/create");
        /*EXTA_PARAMETER*/
        return "admin/pages/News/create";
    }


    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String save(Model model, @Valid @ModelAttribute("news") News news, BindingResult result, HttpServletRequest request) {


        newsValidator.validate(news, result);
        if (result.hasErrors()) {
            model.addAttribute("news", news);
            model.addAttribute("action", "/create");
            /*EXTA_PARAMETER*/
            return "admin/pages/News/create";
        }
        
        

        

        /*CHILD_TABLE_DATA_CREATE*/

        /*SET_NULL_TO_FOREIN_KEY*/
        news.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        news.setCreatedAt(new Date());
        newsRepository.save(news);
        return "redirect:/admin/news";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {


        News news = newsRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("news", news);
        model.addAttribute("action", "/edit/" + id);
        /*EXTA_PARAMETER*/
        return "admin/pages/News/edit";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id, Model model, @Valid @ModelAttribute("news") News news, BindingResult result, HttpServletRequest request) {

        News newsOld = newsRepository.findOne(id);

        newsValidator.validate(news, result);
        if (result.hasErrors()) {
            model.addAttribute("news", news);
            model.addAttribute("action", "/edit/" + id);
            /*EXTA_PARAMETER*/
            return "admin/pages/News/edit";
        }

        
        
        /*CHILD_TABLE_DATA_EDIT*/
        
        /*SET_NULL_TO_FOREIN_KEY*/
        news.setCreatedBy(newsOld.getCreatedBy());
        news.setCreatedAt(newsOld.getCreatedAt());

        news.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        news.setUpdatedAt(new Date());

        newsRepository.save(news);
        return "redirect:/admin/news";
    }


    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

        News news = newsRepository.findOne(id);
        model.addAttribute("news", news);
        return "admin/pages/News/details";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id, Model model) {
        newsRepository.delete(id);
        Map<String, Object> response = new HashMap();

        response.put("success", true);
        response.put("message", "You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public News getById(@PathVariable("id") Integer id, Model model) {
        News news = newsRepository.findOne(id);
        return news;
    }


    @RequestMapping(value = "/dataApi", method = RequestMethod.GET)
    @ResponseBody
    public List<News> data() {
        return newsRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST, headers = "Accept=application/json")
    public DataTablesOutput<News> getNewss(@Valid @RequestBody DataTablesInput input) {

        return newsDataTableRepository.findAll(input);
    }

    /*EXTRA_METHOD*/

}

package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.FeedBack;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.FeedBackRepository;
import me.ataur.bdlaws.admin.repository.FeedBackDataTableRepository;

/*IMPORT_EXTRA_REPOSITORY*/

import me.ataur.bdlaws.admin.validator.FeedBackValidator;

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
@RequestMapping({"/admin/FeedBack","/admin/feedBack"})
public class FeedBackController extends MyBaseController{

    private static final Logger logger = LoggerFactory.getLogger(FeedBackController.class);

    @InitBinder
    public void initBinder ( WebDataBinder binder )
    {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    FeedBackRepository feedBackRepository;
     @Autowired
    FeedBackDataTableRepository feedBackDataTableRepository;


    /*AUTOWIRED_EXTRA_REPOSITORY*/
    @Autowired
    FeedBackValidator feedBackValidator;

    @RequestMapping(value={"","/index"},method = RequestMethod.GET)
    public String index(Model model){
        return "admin/pages/FeedBack/datatable";
    }





    @RequestMapping(value="/replay/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id,Model model,HttpServletRequest request){


        FeedBack feedBack = feedBackRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("feedBack", feedBack);
        model.addAttribute("action","/edit/"+id);
        /*EXTA_PARAMETER*/
        return "admin/pages/FeedBack/create";
    }


    @RequestMapping(value="/reply/{id}",method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id,Model model , @Valid @ModelAttribute("feedBack") FeedBack feedBack, BindingResult result,HttpServletRequest request ){

        FeedBack feedBackOld = feedBackRepository.findOne(id);

        feedBackValidator.validate(feedBack, result);
        if(result.hasErrors()){
            model.addAttribute("feedBack", feedBack);
            model.addAttribute("action","/edit/"+id);
            /*EXTA_PARAMETER*/
            return "admin/pages/FeedBack/create";
        }



        /*CHILD_TABLE_DATA_EDIT*/

        /*SET_NULL_TO_FOREIN_KEY*/
        feedBack.setCreatedBy(feedBackOld.getCreatedBy());
        feedBack.setCreatedAt(feedBackOld.getCreatedAt());

        feedBack.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        feedBack.setUpdatedAt(new Date());

        feedBackRepository.save(feedBack);
        return "redirect:/admin/feedBack";
    }



    @RequestMapping(value="/details/{id}",method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
        FeedBack feedBack = feedBackRepository.findOne(id);
        model.addAttribute("feedBack", feedBack);
        return "admin/pages/FeedBack/details";
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String,Object> delete(@PathVariable("id") Integer id,Model model){
        feedBackRepository.delete(id);
        Map<String,Object> response = new HashMap();
  
        response.put("success",true);
        response.put("message","You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  FeedBack getById(@PathVariable("id") Integer id,Model model){
        FeedBack feedBack = feedBackRepository.findOne(id);
        return feedBack;
    }


    @RequestMapping(value="/dataApi",method = RequestMethod.GET)
    @ResponseBody
    public List<FeedBack> data(){
        return feedBackRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST,headers="Accept=application/json")
    public DataTablesOutput<FeedBack> getFeedBacks(@Valid @RequestBody DataTablesInput input) {

        return feedBackDataTableRepository.findAll(input);
    }

    /*EXTRA_METHOD*/

}

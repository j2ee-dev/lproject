package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.RelatedLink;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.RelatedLinkRepository;
import me.ataur.bdlaws.admin.repository.RelatedLinkDataTableRepository;

/*IMPORT_EXTRA_REPOSITORY*/

import me.ataur.bdlaws.admin.validator.RelatedLinkValidator;

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
@RequestMapping({"/admin/RelatedLink","/admin/relatedLink"})
public class RelatedLinkController extends MyBaseController{

    private static final Logger logger = LoggerFactory.getLogger(RelatedLinkController.class);

    @InitBinder
    public void initBinder ( WebDataBinder binder )
    {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    RelatedLinkRepository relatedLinkRepository;
     @Autowired
    RelatedLinkDataTableRepository relatedLinkDataTableRepository;


    /*AUTOWIRED_EXTRA_REPOSITORY*/
    @Autowired
    RelatedLinkValidator relatedLinkValidator;

    @RequestMapping(value={"","/index"},method = RequestMethod.GET)
    public String index(Model model){
        return "admin/pages/RelatedLink/datatable";
    }

    @RequestMapping(value={"/create","/create/*"},method = RequestMethod.GET)
    public String create(Model model,HttpServletRequest request){
        
        RelatedLink relatedLink = new RelatedLink();
        model.addAttribute("relatedLink", relatedLink);
        model.addAttribute("action","/create");
        /*EXTA_PARAMETER*/
        return "admin/pages/RelatedLink/create";
    }


    @RequestMapping(value={"/create"},method = RequestMethod.POST)
    public String save(Model model , @Valid @ModelAttribute("relatedLink") RelatedLink relatedLink, BindingResult result,HttpServletRequest request ){
        
        

        relatedLinkValidator.validate(relatedLink, result);
        if(result.hasErrors()){
            model.addAttribute("relatedLink", relatedLink);
            model.addAttribute("action","/create");
            /*EXTA_PARAMETER*/
            return "admin/pages/RelatedLink/create";
        }
        
        

        

        /*CHILD_TABLE_DATA_CREATE*/

        /*SET_NULL_TO_FOREIN_KEY*/
        relatedLink.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        relatedLink.setCreatedAt(new Date());
        relatedLinkRepository.save(relatedLink);
        return "redirect:/admin/relatedLink";
    }



    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
      
        RelatedLink relatedLink = relatedLinkRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("relatedLink", relatedLink);
        model.addAttribute("action","/edit/"+id);
        /*EXTA_PARAMETER*/
        return "admin/pages/RelatedLink/edit";
    }


    @RequestMapping(value="/edit/{id}",method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id,Model model , @Valid @ModelAttribute("relatedLink") RelatedLink relatedLink, BindingResult result,HttpServletRequest request ){
        
        RelatedLink relatedLinkOld = relatedLinkRepository.findOne(id);
        
        relatedLinkValidator.validate(relatedLink, result);
        if(result.hasErrors()){
            model.addAttribute("relatedLink", relatedLink);
            model.addAttribute("action","/edit/"+id);
            /*EXTA_PARAMETER*/
            return "admin/pages/RelatedLink/edit";
        }

        
        
        /*CHILD_TABLE_DATA_EDIT*/
        
        /*SET_NULL_TO_FOREIN_KEY*/
        relatedLink.setCreatedBy(relatedLinkOld.getCreatedBy());
        relatedLink.setCreatedAt(relatedLinkOld.getCreatedAt());

        relatedLink.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        relatedLink.setUpdatedAt(new Date());

        relatedLinkRepository.save(relatedLink);
        return "redirect:/admin/relatedLink";
    }



    @RequestMapping(value="/details/{id}",method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
        RelatedLink relatedLink = relatedLinkRepository.findOne(id);
        model.addAttribute("relatedLink", relatedLink);
        return "admin/pages/RelatedLink/details";
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String,Object> delete(@PathVariable("id") Integer id,Model model){
        relatedLinkRepository.delete(id);
        Map<String,Object> response = new HashMap();
  
        response.put("success",true);
        response.put("message","You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  RelatedLink getById(@PathVariable("id") Integer id,Model model){
        RelatedLink relatedLink = relatedLinkRepository.findOne(id);
        return relatedLink;
    }


    @RequestMapping(value="/dataApi",method = RequestMethod.GET)
    @ResponseBody
    public List<RelatedLink> data(){
        return relatedLinkRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST,headers="Accept=application/json")
    public DataTablesOutput<RelatedLink> getRelatedLinks(@Valid @RequestBody DataTablesInput input) {

        return relatedLinkDataTableRepository.findAll(input);
    }

    /*EXTRA_METHOD*/

}

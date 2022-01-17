package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.ActRoleFootnote;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.ActRoleFootnoteRepository;
import me.ataur.bdlaws.admin.repository.ActRoleFootnoteDataTableRepository;


import me.ataur.bdlaws.admin.repository.ActRepository;

import me.ataur.bdlaws.admin.validator.ActRoleFootnoteValidator;

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
@RequestMapping({"/admin/ActRoleFootnote","/admin/actRoleFootnote"})
public class ActRoleFootnoteController extends MyBaseController{

    private static final Logger logger = LoggerFactory.getLogger(ActRoleFootnoteController.class);

    @InitBinder
    public void initBinder ( WebDataBinder binder )
    {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    ActRoleFootnoteRepository actRoleFootnoteRepository;
     @Autowired
    ActRoleFootnoteDataTableRepository actRoleFootnoteDataTableRepository;


    
	@Autowired
    ActRepository actRepository;

    @Autowired
    ActRoleFootnoteValidator actRoleFootnoteValidator;

    @RequestMapping(value={"","/index"},method = RequestMethod.GET)
    public String index(Model model){
        return "admin/pages/ActRoleFootnote/datatable";
    }

    @RequestMapping(value={"/create","/create/*"},method = RequestMethod.GET)
    public String create(Model model,HttpServletRequest request){
        
        ActRoleFootnote actRoleFootnote = new ActRoleFootnote();
        model.addAttribute("actRoleFootnote", actRoleFootnote);
        model.addAttribute("action","/create");
        
			model.addAttribute("actList",actRepository.findAllByOrderByShortTitleAsc() );
			
        return "admin/pages/ActRoleFootnote/create";
    }


    @RequestMapping(value={"/create"},method = RequestMethod.POST)
    public String save(Model model , @Valid @ModelAttribute("actRoleFootnote") ActRoleFootnote actRoleFootnote, BindingResult result,HttpServletRequest request ){
        
        

        actRoleFootnoteValidator.validate(actRoleFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("actRoleFootnote", actRoleFootnote);
            model.addAttribute("action","/create");
            
			model.addAttribute("actList",actRepository.findAllByOrderByShortTitleAsc() );
			
            return "admin/pages/ActRoleFootnote/create";
        }
        
        

        

        /*CHILD_TABLE_DATA_CREATE*/

        
		if(actRoleFootnote.getActId().getId()==0){
            actRoleFootnote.setActId(null);
        }

        
        actRoleFootnote.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        actRoleFootnote.setCreatedAt(new Date());
        actRoleFootnoteRepository.save(actRoleFootnote);
        return "redirect:/admin/actRoleFootnote";
    }



    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
      
        ActRoleFootnote actRoleFootnote = actRoleFootnoteRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("actRoleFootnote", actRoleFootnote);
        model.addAttribute("action","/edit/"+id);
        
			model.addAttribute("actList",actRepository.findAllByOrderByShortTitleAsc() );
			
        return "admin/pages/ActRoleFootnote/create";
    }


    @RequestMapping(value="/edit/{id}",method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id,Model model , @Valid @ModelAttribute("actRoleFootnote") ActRoleFootnote actRoleFootnote, BindingResult result,HttpServletRequest request ){
        
        ActRoleFootnote actRoleFootnoteOld = actRoleFootnoteRepository.findOne(id);
        
        actRoleFootnoteValidator.validate(actRoleFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("actRoleFootnote", actRoleFootnote);
            model.addAttribute("action","/edit/"+id);
            
			model.addAttribute("actList",actRepository.findAllByOrderByShortTitleAsc() );
			
            return "admin/pages/ActRoleFootnote/create";
        }

        
        
        /*CHILD_TABLE_DATA_EDIT*/
        
        
		if(actRoleFootnote.getActId().getId()==0){
            actRoleFootnote.setActId(null);
        }

        
        actRoleFootnote.setCreatedBy(actRoleFootnoteOld.getCreatedBy());
        actRoleFootnote.setCreatedAt(actRoleFootnoteOld.getCreatedAt());

        actRoleFootnote.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        actRoleFootnote.setUpdatedAt(new Date());

        actRoleFootnoteRepository.save(actRoleFootnote);
        return "redirect:/admin/actRoleFootnote";
    }



    @RequestMapping(value="/details/{id}",method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
        ActRoleFootnote actRoleFootnote = actRoleFootnoteRepository.findOne(id);
        model.addAttribute("actRoleFootnote", actRoleFootnote);
        return "admin/pages/ActRoleFootnote/details";
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String,Object> delete(@PathVariable("id") Integer id,Model model){


        Integer footnoteId=id;
        ActRoleFootnote dlActRoleFootnote = actRoleFootnoteRepository.getOne(id);

        List<ActRoleFootnote> actRoleFootnotes= actRoleFootnoteRepository.getFootnoteWithGrateaterOrder(dlActRoleFootnote.getActId().getId(), dlActRoleFootnote.getFootnoteNumber());

        if (actRoleFootnotes.size()!=0)
        {
            for (ActRoleFootnote actRoleFootnote:actRoleFootnotes)
            {
                actRoleFootnote.setFootnoteNumber( actRoleFootnote.getFootnoteNumber()-1);
                actRoleFootnoteRepository.save(actRoleFootnote);
            }
        }


        actRoleFootnoteRepository.delete(id);
        Map<String,Object> response = new HashMap();
  
        response.put("success",true);
        response.put("message","You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  ActRoleFootnote getById(@PathVariable("id") Integer id,Model model){
        ActRoleFootnote actRoleFootnote = actRoleFootnoteRepository.findOne(id);
        return actRoleFootnote;
    }


    @RequestMapping(value="/dataApi",method = RequestMethod.GET)
    @ResponseBody
    public List<ActRoleFootnote> data(){
        return actRoleFootnoteRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST,headers="Accept=application/json")
    public DataTablesOutput<ActRoleFootnote> getActRoleFootnotes(@Valid @RequestBody DataTablesInput input) {

        return actRoleFootnoteDataTableRepository.findAll(input);
    }

    /*EXTRA_METHOD*/

}

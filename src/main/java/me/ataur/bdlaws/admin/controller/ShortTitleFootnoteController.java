package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.ShortTitleFootnote;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.ShortTitleFootnoteRepository;
import me.ataur.bdlaws.admin.repository.ShortTitleFootnoteDataTableRepository;


import me.ataur.bdlaws.admin.repository.ActRepository;

import me.ataur.bdlaws.admin.validator.ShortTitleFootnoteValidator;

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
@RequestMapping({"/admin/ShortTitleFootnote","/admin/shortTitleFootnote"})
public class ShortTitleFootnoteController extends MyBaseController{

    private static final Logger logger = LoggerFactory.getLogger(ShortTitleFootnoteController.class);

    @InitBinder
    public void initBinder ( WebDataBinder binder )
    {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    ShortTitleFootnoteRepository shortTitleFootnoteRepository;
     @Autowired
    ShortTitleFootnoteDataTableRepository shortTitleFootnoteDataTableRepository;


    
	@Autowired
    ActRepository actRepository;

    @Autowired
    ShortTitleFootnoteValidator shortTitleFootnoteValidator;

    @RequestMapping(value={"","/index"},method = RequestMethod.GET)
    public String index(Model model){
        return "admin/pages/ShortTitleFootnote/datatable";
    }

    @RequestMapping(value={"/create","/create/*"},method = RequestMethod.GET)
    public String create(Model model,HttpServletRequest request){
        
        ShortTitleFootnote shortTitleFootnote = new ShortTitleFootnote();
        model.addAttribute("shortTitleFootnote", shortTitleFootnote);
        model.addAttribute("action","/create");
        
			model.addAttribute("actList",actRepository.findAllByOrderByShortTitleAsc() );
			
        return "admin/pages/ShortTitleFootnote/create";
    }


    @RequestMapping(value={"/create"},method = RequestMethod.POST)
    public String save(Model model , @Valid @ModelAttribute("shortTitleFootnote") ShortTitleFootnote shortTitleFootnote, BindingResult result,HttpServletRequest request ){
        
        

        shortTitleFootnoteValidator.validate(shortTitleFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("shortTitleFootnote", shortTitleFootnote);
            model.addAttribute("action","/create");
            
			model.addAttribute("actList",actRepository.findAllByOrderByShortTitleAsc() );
			
            return "admin/pages/ShortTitleFootnote/create";
        }
        
        

        

        /*CHILD_TABLE_DATA_CREATE*/

        
		if(shortTitleFootnote.getActId().getId()==0){
            shortTitleFootnote.setActId(null);
        }

        
        shortTitleFootnote.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        shortTitleFootnote.setCreatedAt(new Date());
        shortTitleFootnoteRepository.save(shortTitleFootnote);
        return "redirect:/admin/shortTitleFootnote";
    }



    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
      
        ShortTitleFootnote shortTitleFootnote = shortTitleFootnoteRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("shortTitleFootnote", shortTitleFootnote);
        model.addAttribute("action","/edit/"+id);
        
			model.addAttribute("actList",actRepository.findAllByOrderByShortTitleAsc() );
			
        return "admin/pages/ShortTitleFootnote/create";
    }


    @RequestMapping(value="/edit/{id}",method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id,Model model , @Valid @ModelAttribute("shortTitleFootnote") ShortTitleFootnote shortTitleFootnote, BindingResult result,HttpServletRequest request ){
        
        ShortTitleFootnote shortTitleFootnoteOld = shortTitleFootnoteRepository.findOne(id);
        
        shortTitleFootnoteValidator.validate(shortTitleFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("shortTitleFootnote", shortTitleFootnote);
            model.addAttribute("action","/edit/"+id);
            
			model.addAttribute("actList",actRepository.findAllByOrderByShortTitleAsc() );
			
            return "admin/pages/ShortTitleFootnote/create";
        }

        
        
        /*CHILD_TABLE_DATA_EDIT*/
        
        
		if(shortTitleFootnote.getActId().getId()==0){
            shortTitleFootnote.setActId(null);
        }

        
        shortTitleFootnote.setCreatedBy(shortTitleFootnoteOld.getCreatedBy());
        shortTitleFootnote.setCreatedAt(shortTitleFootnoteOld.getCreatedAt());

        shortTitleFootnote.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        shortTitleFootnote.setUpdatedAt(new Date());

        shortTitleFootnoteRepository.save(shortTitleFootnote);
        return "redirect:/admin/shortTitleFootnote";
    }



    @RequestMapping(value="/details/{id}",method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
        ShortTitleFootnote shortTitleFootnote = shortTitleFootnoteRepository.findOne(id);
        model.addAttribute("shortTitleFootnote", shortTitleFootnote);
        return "admin/pages/ShortTitleFootnote/details";
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String,Object> delete(@PathVariable("id") Integer id,Model model){

        Integer footnoteId=id;
        ShortTitleFootnote dlShortTitleFootnote = shortTitleFootnoteRepository.getOne(id);

        List<ShortTitleFootnote> shortTitleFootnotes= shortTitleFootnoteRepository.getFootnoteWithGrateaterOrder(dlShortTitleFootnote.getActId().getId(), dlShortTitleFootnote.getFootnoteNumber());

        if (shortTitleFootnotes.size()!=0)
        {
            for (ShortTitleFootnote shortTitleFootnote:shortTitleFootnotes)
            {
                shortTitleFootnote.setFootnoteNumber( shortTitleFootnote.getFootnoteNumber()-1);
                shortTitleFootnoteRepository.save(shortTitleFootnote);
            }
        }
        shortTitleFootnoteRepository.delete(id);
        Map<String,Object> response = new HashMap();
  
        response.put("success",true);
        response.put("message","You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  ShortTitleFootnote getById(@PathVariable("id") Integer id,Model model){
        ShortTitleFootnote shortTitleFootnote = shortTitleFootnoteRepository.findOne(id);
        return shortTitleFootnote;
    }


    @RequestMapping(value="/dataApi",method = RequestMethod.GET)
    @ResponseBody
    public List<ShortTitleFootnote> data(){
        return shortTitleFootnoteRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST,headers="Accept=application/json")
    public DataTablesOutput<ShortTitleFootnote> getShortTitleFootnotes(@Valid @RequestBody DataTablesInput input) {

        return shortTitleFootnoteDataTableRepository.findAll(input);
    }

    /*EXTRA_METHOD*/

}

package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.SectionNameFootnote;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.SectionNameFootnoteRepository;
import me.ataur.bdlaws.admin.repository.SectionNameFootnoteDataTableRepository;


import me.ataur.bdlaws.admin.repository.ActSectionRepository;

import me.ataur.bdlaws.admin.validator.SectionNameFootnoteValidator;

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
@RequestMapping({"/admin/SectionNameFootnote","/admin/sectionNameFootnote"})
public class SectionNameFootnoteController extends MyBaseController{

    private static final Logger logger = LoggerFactory.getLogger(SectionNameFootnoteController.class);

    @InitBinder
    public void initBinder ( WebDataBinder binder )
    {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    SectionNameFootnoteRepository sectionNameFootnoteRepository;
     @Autowired
    SectionNameFootnoteDataTableRepository sectionNameFootnoteDataTableRepository;


    
	@Autowired
    ActSectionRepository actSectionRepository;

    @Autowired
    SectionNameFootnoteValidator sectionNameFootnoteValidator;

    @RequestMapping(value={"","/index"},method = RequestMethod.GET)
    public String index(Model model){
        return "admin/pages/SectionNameFootnote/datatable";
    }

    @RequestMapping(value={"/create","/create/*"},method = RequestMethod.GET)
    public String create(Model model,HttpServletRequest request){
        
        SectionNameFootnote sectionNameFootnote = new SectionNameFootnote();
        model.addAttribute("sectionNameFootnote", sectionNameFootnote);
        model.addAttribute("action","/create");
        
			model.addAttribute("actSectionList",actSectionRepository.findAllByOrderBySectionNameAsc() );
			
        return "admin/pages/SectionNameFootnote/create";
    }


    @RequestMapping(value={"/create"},method = RequestMethod.POST)
    public String save(Model model , @Valid @ModelAttribute("sectionNameFootnote") SectionNameFootnote sectionNameFootnote, BindingResult result,HttpServletRequest request ){
        
        

        sectionNameFootnoteValidator.validate(sectionNameFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("sectionNameFootnote", sectionNameFootnote);
            model.addAttribute("action","/create");
            
			model.addAttribute("actSectionList",actSectionRepository.findAllByOrderBySectionNameAsc() );
			
            return "admin/pages/SectionNameFootnote/create";
        }
        
        

        

        /*CHILD_TABLE_DATA_CREATE*/

        
		if(sectionNameFootnote.getSectionId().getId()==0){
            sectionNameFootnote.setSectionId(null);
        }

        
        sectionNameFootnote.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        sectionNameFootnote.setCreatedAt(new Date());
        sectionNameFootnoteRepository.save(sectionNameFootnote);
        return "redirect:/admin/sectionNameFootnote";
    }



    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
      
        SectionNameFootnote sectionNameFootnote = sectionNameFootnoteRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("sectionNameFootnote", sectionNameFootnote);
        model.addAttribute("action","/edit/"+id);
        
			model.addAttribute("actSectionList",actSectionRepository.findAllByOrderBySectionNameAsc() );
			
        return "admin/pages/SectionNameFootnote/create";
    }


    @RequestMapping(value="/edit/{id}",method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id,Model model , @Valid @ModelAttribute("sectionNameFootnote") SectionNameFootnote sectionNameFootnote, BindingResult result,HttpServletRequest request ){
        
        SectionNameFootnote sectionNameFootnoteOld = sectionNameFootnoteRepository.findOne(id);
        
        sectionNameFootnoteValidator.validate(sectionNameFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("sectionNameFootnote", sectionNameFootnote);
            model.addAttribute("action","/edit/"+id);
            
			model.addAttribute("actSectionList",actSectionRepository.findAllByOrderBySectionNameAsc() );
			
            return "admin/pages/SectionNameFootnote/create";
        }

        
        
        /*CHILD_TABLE_DATA_EDIT*/
        
        
		if(sectionNameFootnote.getSectionId().getId()==0){
            sectionNameFootnote.setSectionId(null);
        }

        
        sectionNameFootnote.setCreatedBy(sectionNameFootnoteOld.getCreatedBy());
        sectionNameFootnote.setCreatedAt(sectionNameFootnoteOld.getCreatedAt());

        sectionNameFootnote.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        sectionNameFootnote.setUpdatedAt(new Date());

        sectionNameFootnoteRepository.save(sectionNameFootnote);
        return "redirect:/admin/sectionNameFootnote";
    }



    @RequestMapping(value="/details/{id}",method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
        SectionNameFootnote sectionNameFootnote = sectionNameFootnoteRepository.findOne(id);
        model.addAttribute("sectionNameFootnote", sectionNameFootnote);
        return "admin/pages/SectionNameFootnote/details";
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String,Object> delete(@PathVariable("id") Integer id,Model model){

        Integer footnoteId=id;
        SectionNameFootnote section = sectionNameFootnoteRepository.getOne(id);

        List<SectionNameFootnote> sectionNameFootnotes= sectionNameFootnoteRepository.getFootnoteWithGrateaterOrder(section.getSectionId().getId(), section.getFootnoteNumber());

        if (sectionNameFootnotes.size()!=0)
        {
            for (SectionNameFootnote sectionNameFootnote:sectionNameFootnotes)
            {
                sectionNameFootnote.setFootnoteNumber( sectionNameFootnote.getFootnoteNumber()-1);
                sectionNameFootnoteRepository.save(sectionNameFootnote);
            }
        }

        sectionNameFootnoteRepository.delete(id);
        Map<String,Object> response = new HashMap();
  
        response.put("success",true);
        response.put("message","You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  SectionNameFootnote getById(@PathVariable("id") Integer id,Model model){
        SectionNameFootnote sectionNameFootnote = sectionNameFootnoteRepository.findOne(id);
        return sectionNameFootnote;
    }


    @RequestMapping(value="/dataApi",method = RequestMethod.GET)
    @ResponseBody
    public List<SectionNameFootnote> data(){
        return sectionNameFootnoteRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST,headers="Accept=application/json")
    public DataTablesOutput<SectionNameFootnote> getSectionNameFootnotes(@Valid @RequestBody DataTablesInput input) {

        return sectionNameFootnoteDataTableRepository.findAll(input);
    }

    /*EXTRA_METHOD*/

}

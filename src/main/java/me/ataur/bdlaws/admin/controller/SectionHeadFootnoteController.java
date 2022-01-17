package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.SectionHeadFootnote;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.SectionHeadFootnoteRepository;
import me.ataur.bdlaws.admin.repository.SectionHeadFootnoteDataTableRepository;


import me.ataur.bdlaws.admin.repository.ActSectionRepository;

import me.ataur.bdlaws.admin.validator.SectionHeadFootnoteValidator;

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
@RequestMapping({"/admin/SectionHeadFootnote","/admin/sectionHeadFootnote"})
public class SectionHeadFootnoteController extends MyBaseController{

    private static final Logger logger = LoggerFactory.getLogger(SectionHeadFootnoteController.class);

    @InitBinder
    public void initBinder ( WebDataBinder binder )
    {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    SectionHeadFootnoteRepository sectionHeadFootnoteRepository;
     @Autowired
    SectionHeadFootnoteDataTableRepository sectionHeadFootnoteDataTableRepository;


    
	@Autowired
    ActSectionRepository actSectionRepository;

    @Autowired
    SectionHeadFootnoteValidator sectionHeadFootnoteValidator;

    @RequestMapping(value={"","/index"},method = RequestMethod.GET)
    public String index(Model model){
        return "admin/pages/SectionHeadFootnote/datatable";
    }

    @RequestMapping(value={"/create","/create/*"},method = RequestMethod.GET)
    public String create(Model model,HttpServletRequest request){
        
        SectionHeadFootnote sectionHeadFootnote = new SectionHeadFootnote();
        model.addAttribute("sectionHeadFootnote", sectionHeadFootnote);
        model.addAttribute("action","/create");
        
			model.addAttribute("actSectionList",actSectionRepository.findAllByOrderBySectionNameAsc() );
			
        return "admin/pages/SectionHeadFootnote/create";
    }


    @RequestMapping(value={"/create"},method = RequestMethod.POST)
    public String save(Model model , @Valid @ModelAttribute("sectionHeadFootnote") SectionHeadFootnote sectionHeadFootnote, BindingResult result,HttpServletRequest request ){
        
        

        sectionHeadFootnoteValidator.validate(sectionHeadFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("sectionHeadFootnote", sectionHeadFootnote);
            model.addAttribute("action","/create");
            
			model.addAttribute("actSectionList",actSectionRepository.findAllByOrderBySectionNameAsc() );
			
            return "admin/pages/SectionHeadFootnote/create";
        }
        
        

        

        /*CHILD_TABLE_DATA_CREATE*/

        
		if(sectionHeadFootnote.getSectionId().getId()==0){
            sectionHeadFootnote.setSectionId(null);
        }

        
        sectionHeadFootnote.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        sectionHeadFootnote.setCreatedAt(new Date());
        sectionHeadFootnoteRepository.save(sectionHeadFootnote);
        return "redirect:/admin/sectionHeadFootnote";
    }



    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
      
        SectionHeadFootnote sectionHeadFootnote = sectionHeadFootnoteRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("sectionHeadFootnote", sectionHeadFootnote);
        model.addAttribute("action","/edit/"+id);
        
			model.addAttribute("actSectionList",actSectionRepository.findAllByOrderBySectionNameAsc() );
			
        return "admin/pages/SectionHeadFootnote/create";
    }


    @RequestMapping(value="/edit/{id}",method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id,Model model , @Valid @ModelAttribute("sectionHeadFootnote") SectionHeadFootnote sectionHeadFootnote, BindingResult result,HttpServletRequest request ){
        
        SectionHeadFootnote sectionHeadFootnoteOld = sectionHeadFootnoteRepository.findOne(id);
        
        sectionHeadFootnoteValidator.validate(sectionHeadFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("sectionHeadFootnote", sectionHeadFootnote);
            model.addAttribute("action","/edit/"+id);
            
			model.addAttribute("actSectionList",actSectionRepository.findAllByOrderBySectionNameAsc() );
			
            return "admin/pages/SectionHeadFootnote/create";
        }

        
        
        /*CHILD_TABLE_DATA_EDIT*/
        
        
		if(sectionHeadFootnote.getSectionId().getId()==0){
            sectionHeadFootnote.setSectionId(null);
        }

        
        sectionHeadFootnote.setCreatedBy(sectionHeadFootnoteOld.getCreatedBy());
        sectionHeadFootnote.setCreatedAt(sectionHeadFootnoteOld.getCreatedAt());

        sectionHeadFootnote.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        sectionHeadFootnote.setUpdatedAt(new Date());

        sectionHeadFootnoteRepository.save(sectionHeadFootnote);
        return "redirect:/admin/sectionHeadFootnote";
    }



    @RequestMapping(value="/details/{id}",method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
        SectionHeadFootnote sectionHeadFootnote = sectionHeadFootnoteRepository.findOne(id);
        model.addAttribute("sectionHeadFootnote", sectionHeadFootnote);
        return "admin/pages/SectionHeadFootnote/details";
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String,Object> delete(@PathVariable("id") Integer id,Model model){
        Integer footnoteId=id;
        SectionHeadFootnote section = sectionHeadFootnoteRepository.getOne(id);

        List<SectionHeadFootnote> sectionHeadFootnotes= sectionHeadFootnoteRepository.getFootnoteWithGrateaterOrder(section.getSectionId().getId(), section.getFootnoteNumber());

        if (sectionHeadFootnotes.size()!=0)
        {
            for (SectionHeadFootnote sectionHeadFootnote:sectionHeadFootnotes)
            {
                sectionHeadFootnote.setFootnoteNumber( sectionHeadFootnote.getFootnoteNumber()-1);
                sectionHeadFootnoteRepository.save(sectionHeadFootnotes);
            }
        }
        sectionHeadFootnoteRepository.delete(id);
        Map<String,Object> response = new HashMap();
  
        response.put("success",true);
        response.put("message","You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  SectionHeadFootnote getById(@PathVariable("id") Integer id,Model model){
        SectionHeadFootnote sectionHeadFootnote = sectionHeadFootnoteRepository.findOne(id);
        return sectionHeadFootnote;
    }


    @RequestMapping(value="/dataApi",method = RequestMethod.GET)
    @ResponseBody
    public List<SectionHeadFootnote> data(){
        return sectionHeadFootnoteRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST,headers="Accept=application/json")
    public DataTablesOutput<SectionHeadFootnote> getSectionHeadFootnotes(@Valid @RequestBody DataTablesInput input) {

        return sectionHeadFootnoteDataTableRepository.findAll(input);
    }

    /*EXTRA_METHOD*/

}

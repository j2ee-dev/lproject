package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.SectionTableFootnote;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.SectionTableFootnoteRepository;
import me.ataur.bdlaws.admin.repository.SectionTableFootnoteDataTableRepository;


import me.ataur.bdlaws.admin.repository.ActSectionRepository;

import me.ataur.bdlaws.admin.validator.SectionTableFootnoteValidator;

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
 * Created by imran hossin
 */

@Controller
@RequestMapping({"/admin/SectionTableFootnote","/admin/sectionTableFootnote"})
public class SectionTableFootnoteController extends MyBaseController{

    private static final Logger logger = LoggerFactory.getLogger(SectionTableFootnoteController.class);

    @InitBinder
    public void initBinder ( WebDataBinder binder )
    {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    SectionTableFootnoteRepository sectionTableFootnoteRepository;
     @Autowired
    SectionTableFootnoteDataTableRepository sectionTableFootnoteDataTableRepository;


    
	@Autowired
    ActSectionRepository actSectionRepository;

    @Autowired
    SectionTableFootnoteValidator sectionTableFootnoteValidator;

    @RequestMapping(value={"","/index"},method = RequestMethod.GET)
    public String index(Model model){
        return "admin/pages/SectionTableFootnote/datatable";
    }

    @RequestMapping(value={"/create","/create/*"},method = RequestMethod.GET)
    public String create(Model model,HttpServletRequest request){
        
        SectionTableFootnote sectionTableFootnote = new SectionTableFootnote();
        model.addAttribute("sectionTableFootnote", sectionTableFootnote);
        model.addAttribute("action","/create");
        
			model.addAttribute("actSectionList",actSectionRepository.findAllByOrderBySectionNameAsc() );
			
        return "admin/pages/SectionTableFootnote/create";
    }


    @RequestMapping(value={"/create"},method = RequestMethod.POST)
    public String save(Model model , @Valid @ModelAttribute("sectionTableFootnote") SectionTableFootnote sectionTableFootnote, BindingResult result,HttpServletRequest request ){
        
        

        sectionTableFootnoteValidator.validate(sectionTableFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("sectionTableFootnote", sectionTableFootnote);
            model.addAttribute("action","/create");
            
			model.addAttribute("actSectionList",actSectionRepository.findAllByOrderBySectionNameAsc() );
			
            return "admin/pages/SectionTableFootnote/create";
        }
        
        

        

        /*CHILD_TABLE_DATA_CREATE*/

        
		if(sectionTableFootnote.getSectionId().getId()==0){
            sectionTableFootnote.setSectionId(null);
        }

        
        sectionTableFootnote.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        sectionTableFootnote.setCreatedAt(new Date());
        sectionTableFootnoteRepository.save(sectionTableFootnote);
        return "redirect:/admin/sectionTableFootnote";
    }



    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
      
        SectionTableFootnote sectionTableFootnote = sectionTableFootnoteRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("sectionTableFootnote", sectionTableFootnote);
        model.addAttribute("action","/edit/"+id);
        
			model.addAttribute("actSectionList",actSectionRepository.findAllByOrderBySectionNameAsc() );
			
        return "admin/pages/SectionTableFootnote/create";
    }


    @RequestMapping(value="/edit/{id}",method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id,Model model , @Valid @ModelAttribute("sectionTableFootnote") SectionTableFootnote sectionTableFootnote, BindingResult result,HttpServletRequest request ){
        
        SectionTableFootnote sectionTableFootnoteOld = sectionTableFootnoteRepository.findOne(id);
        
        sectionTableFootnoteValidator.validate(sectionTableFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("sectionTableFootnote", sectionTableFootnote);
            model.addAttribute("action","/edit/"+id);
            
			model.addAttribute("actSectionList",actSectionRepository.findAllByOrderBySectionNameAsc() );
			
            return "admin/pages/SectionTableFootnote/create";
        }

        
        
        /*CHILD_TABLE_DATA_EDIT*/
        
        
		if(sectionTableFootnote.getSectionId().getId()==0){
            sectionTableFootnote.setSectionId(null);
        }

        
        sectionTableFootnote.setCreatedBy(sectionTableFootnoteOld.getCreatedBy());
        sectionTableFootnote.setCreatedAt(sectionTableFootnoteOld.getCreatedAt());

        sectionTableFootnote.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        sectionTableFootnote.setUpdatedAt(new Date());

        sectionTableFootnoteRepository.save(sectionTableFootnote);
        return "redirect:/admin/sectionTableFootnote";
    }



    @RequestMapping(value="/details/{id}",method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
        SectionTableFootnote sectionTableFootnote = sectionTableFootnoteRepository.findOne(id);
        model.addAttribute("sectionTableFootnote", sectionTableFootnote);
        return "admin/pages/SectionTableFootnote/details";
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String,Object> delete(@PathVariable("id") Integer id,Model model){
        Integer footnoteId=id;
        SectionTableFootnote section = sectionTableFootnoteRepository.getOne(id);

        List<SectionTableFootnote> sectionTableFootnotes= sectionTableFootnoteRepository.getFootnoteWithGrateaterOrder(section.getSectionId().getId(), section.getFootnoteNumber());

        if (sectionTableFootnotes.size()!=0)
        {
            for (SectionTableFootnote sectionTableFootnote:sectionTableFootnotes)
            {
                sectionTableFootnote.setFootnoteNumber( sectionTableFootnote.getFootnoteNumber()-1);
                sectionTableFootnoteRepository.save(sectionTableFootnote);
            }
        }

        sectionTableFootnoteRepository.delete(id);
        Map<String,Object> response = new HashMap();
  
        response.put("success",true);
        response.put("message","You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  SectionTableFootnote getById(@PathVariable("id") Integer id,Model model){
        SectionTableFootnote sectionTableFootnote = sectionTableFootnoteRepository.findOne(id);
        return sectionTableFootnote;
    }


    @RequestMapping(value="/dataApi",method = RequestMethod.GET)
    @ResponseBody
    public List<SectionTableFootnote> data(){
        return sectionTableFootnoteRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST,headers="Accept=application/json")
    public DataTablesOutput<SectionTableFootnote> getSectionTableFootnotes(@Valid @RequestBody DataTablesInput input) {

        return sectionTableFootnoteDataTableRepository.findAll(input);
    }

    /*EXTRA_METHOD*/

}

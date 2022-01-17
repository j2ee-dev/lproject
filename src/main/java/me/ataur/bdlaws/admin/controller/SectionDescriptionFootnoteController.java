package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.SectionDescriptionFootnote;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.SectionDescriptionFootnoteRepository;
import me.ataur.bdlaws.admin.repository.SectionDescriptionFootnoteDataTableRepository;


import me.ataur.bdlaws.admin.repository.ActSectionRepository;

import me.ataur.bdlaws.admin.validator.SectionDescriptionFootnoteValidator;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.authentication.dao.SystemWideSaltSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping({"/admin/SectionDescriptionFootnote","/admin/sectionDescriptionFootnote"})
public class SectionDescriptionFootnoteController extends MyBaseController{

    private static final Logger logger = LoggerFactory.getLogger(SectionDescriptionFootnoteController.class);

    @InitBinder
    public void initBinder ( WebDataBinder binder )
    {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    SectionDescriptionFootnoteRepository sectionDescriptionFootnoteRepository;
     @Autowired
    SectionDescriptionFootnoteDataTableRepository sectionDescriptionFootnoteDataTableRepository;


    
	@Autowired
    ActSectionRepository actSectionRepository;

    @Autowired
    SectionDescriptionFootnoteValidator sectionDescriptionFootnoteValidator;

    @RequestMapping(value={"","/index"},method = RequestMethod.GET)
    public String index(Model model){
        return "admin/pages/SectionDescriptionFootnote/datatable";
    }

    @RequestMapping(value={"/create","/create/*"},method = RequestMethod.GET)
    public String create(Model model,HttpServletRequest request){
        
        SectionDescriptionFootnote sectionDescriptionFootnote = new SectionDescriptionFootnote();
        model.addAttribute("sectionDescriptionFootnote", sectionDescriptionFootnote);
        model.addAttribute("action","/create");
        
			model.addAttribute("actSectionList",actSectionRepository.findAllByOrderBySectionNameAsc() );
			
        return "admin/pages/SectionDescriptionFootnote/create";
    }


    @RequestMapping(value={"/create"},method = RequestMethod.POST)
    public String save(Model model , @Valid @ModelAttribute("sectionDescriptionFootnote") SectionDescriptionFootnote sectionDescriptionFootnote, BindingResult result,HttpServletRequest request ){
        
        

        sectionDescriptionFootnoteValidator.validate(sectionDescriptionFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("sectionDescriptionFootnote", sectionDescriptionFootnote);
            model.addAttribute("action","/create");
            
			model.addAttribute("actSectionList",actSectionRepository.findAllByOrderBySectionNameAsc() );
			
            return "admin/pages/SectionDescriptionFootnote/create";
        }
        
        

        

        /*CHILD_TABLE_DATA_CREATE*/

        
		if(sectionDescriptionFootnote.getSectionId().getId()==0){
            sectionDescriptionFootnote.setSectionId(null);
        }

        
        sectionDescriptionFootnote.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        sectionDescriptionFootnote.setCreatedAt(new Date());
        sectionDescriptionFootnoteRepository.save(sectionDescriptionFootnote);
        return "redirect:/admin/sectionDescriptionFootnote";
    }



    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
      
        SectionDescriptionFootnote sectionDescriptionFootnote = sectionDescriptionFootnoteRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("sectionDescriptionFootnote", sectionDescriptionFootnote);
        model.addAttribute("action","/edit/"+id);
        
			model.addAttribute("actSectionList",actSectionRepository.findAllByOrderBySectionNameAsc() );
			
        return "admin/pages/SectionDescriptionFootnote/create";
    }


    @RequestMapping(value="/edit/{id}",method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id,Model model , @Valid @ModelAttribute("sectionDescriptionFootnote") SectionDescriptionFootnote sectionDescriptionFootnote, BindingResult result,HttpServletRequest request ){
        
        SectionDescriptionFootnote sectionDescriptionFootnoteOld = sectionDescriptionFootnoteRepository.findOne(id);
        
        sectionDescriptionFootnoteValidator.validate(sectionDescriptionFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("sectionDescriptionFootnote", sectionDescriptionFootnote);
            model.addAttribute("action","/edit/"+id);
            
			model.addAttribute("actSectionList",actSectionRepository.findAllByOrderBySectionNameAsc() );
			
            return "admin/pages/SectionDescriptionFootnote/create";
        }

        
        
        /*CHILD_TABLE_DATA_EDIT*/
        
        
		if(sectionDescriptionFootnote.getSectionId().getId()==0){
            sectionDescriptionFootnote.setSectionId(null);
        }

        
        sectionDescriptionFootnote.setCreatedBy(sectionDescriptionFootnoteOld.getCreatedBy());
        sectionDescriptionFootnote.setCreatedAt(sectionDescriptionFootnoteOld.getCreatedAt());

        sectionDescriptionFootnote.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        sectionDescriptionFootnote.setUpdatedAt(new Date());

        sectionDescriptionFootnoteRepository.save(sectionDescriptionFootnote);
        return "redirect:/admin/sectionDescriptionFootnote";
    }



    @RequestMapping(value="/details/{id}",method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
        SectionDescriptionFootnote sectionDescriptionFootnote = sectionDescriptionFootnoteRepository.findOne(id);
        model.addAttribute("sectionDescriptionFootnote", sectionDescriptionFootnote);
        return "admin/pages/SectionDescriptionFootnote/details";
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String,Object> delete(@PathVariable("id") Integer id,Model model){
        Integer footnoteId=id;
        SectionDescriptionFootnote section = sectionDescriptionFootnoteRepository.getOne(id);

        List<SectionDescriptionFootnote> sectionDescriptionFootnotes= sectionDescriptionFootnoteRepository.getFootnoteWithGrateaterOrder(section.getSectionId().getId(), section.getFootnoteNumber());

        if (sectionDescriptionFootnotes.size()!=0)
        {
            for (SectionDescriptionFootnote sectionDescriptionFootnote:sectionDescriptionFootnotes)
            {
              sectionDescriptionFootnote.setFootnoteNumber( sectionDescriptionFootnote.getFootnoteNumber()-1);
                sectionDescriptionFootnoteRepository.save(sectionDescriptionFootnote);
            }
        }

        Map<String,Object> response = new HashMap();
        sectionDescriptionFootnoteRepository.delete(id);
        response.put("success",true);
        response.put("message","You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  SectionDescriptionFootnote getById(@PathVariable("id") Integer id,Model model){
        SectionDescriptionFootnote sectionDescriptionFootnote = sectionDescriptionFootnoteRepository.findOne(id);
        return sectionDescriptionFootnote;
    }


    @RequestMapping(value="/dataApi",method = RequestMethod.GET)
    @ResponseBody
    public List<SectionDescriptionFootnote> data(){
        return sectionDescriptionFootnoteRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST,headers="Accept=application/json")
    public DataTablesOutput<SectionDescriptionFootnote> getSectionDescriptionFootnotes(@Valid @RequestBody DataTablesInput input) {

        return sectionDescriptionFootnoteDataTableRepository.findAll(input);
    }

    /*EXTRA_METHOD*/

}

package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.PreambleFootnote;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.PreambleFootnoteRepository;
import me.ataur.bdlaws.admin.repository.PreambleFootnoteDataTableRepository;


import me.ataur.bdlaws.admin.repository.ActRepository;

import me.ataur.bdlaws.admin.validator.PreambleFootnoteValidator;

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
@RequestMapping({"/admin/PreambleFootnote","/admin/preambleFootnote"})
public class PreambleFootnoteController extends MyBaseController{

    private static final Logger logger = LoggerFactory.getLogger(PreambleFootnoteController.class);

    @InitBinder
    public void initBinder ( WebDataBinder binder )
    {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    PreambleFootnoteRepository preambleFootnoteRepository;
     @Autowired
    PreambleFootnoteDataTableRepository preambleFootnoteDataTableRepository;


    
	@Autowired
    ActRepository actRepository;

    @Autowired
    PreambleFootnoteValidator preambleFootnoteValidator;

    @RequestMapping(value={"","/index"},method = RequestMethod.GET)
    public String index(Model model){
        return "admin/pages/PreambleFootnote/datatable";
    }

    @RequestMapping(value={"/create","/create/*"},method = RequestMethod.GET)
    public String create(Model model,HttpServletRequest request){
        
        PreambleFootnote preambleFootnote = new PreambleFootnote();
        model.addAttribute("preambleFootnote", preambleFootnote);
        model.addAttribute("action","/create");
        
			model.addAttribute("actList",actRepository.findAllByOrderByShortTitleAsc() );
			
        return "admin/pages/PreambleFootnote/create";
    }


    @RequestMapping(value={"/create"},method = RequestMethod.POST)
    public String save(Model model , @Valid @ModelAttribute("preambleFootnote") PreambleFootnote preambleFootnote, BindingResult result,HttpServletRequest request ){
        
        

        preambleFootnoteValidator.validate(preambleFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("preambleFootnote", preambleFootnote);
            model.addAttribute("action","/create");
            
			model.addAttribute("actList",actRepository.findAllByOrderByShortTitleAsc() );
			
            return "admin/pages/PreambleFootnote/create";
        }
        
        

        

        /*CHILD_TABLE_DATA_CREATE*/

        
		if(preambleFootnote.getActId().getId()==0){
            preambleFootnote.setActId(null);
        }

        
        preambleFootnote.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        preambleFootnote.setCreatedAt(new Date());
        preambleFootnoteRepository.save(preambleFootnote);
        return "redirect:/admin/preambleFootnote";
    }



    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
      
        PreambleFootnote preambleFootnote = preambleFootnoteRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("preambleFootnote", preambleFootnote);
        model.addAttribute("action","/edit/"+id);
        
			model.addAttribute("actList",actRepository.findAllByOrderByShortTitleAsc() );
			
        return "admin/pages/PreambleFootnote/create";
    }


    @RequestMapping(value="/edit/{id}",method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id,Model model , @Valid @ModelAttribute("preambleFootnote") PreambleFootnote preambleFootnote, BindingResult result,HttpServletRequest request ){
        
        PreambleFootnote preambleFootnoteOld = preambleFootnoteRepository.findOne(id);
        
        preambleFootnoteValidator.validate(preambleFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("preambleFootnote", preambleFootnote);
            model.addAttribute("action","/edit/"+id);
            
			model.addAttribute("actList",actRepository.findAllByOrderByShortTitleAsc() );
			
            return "admin/pages/PreambleFootnote/create";
        }

        
        
        /*CHILD_TABLE_DATA_EDIT*/
        
        
		if(preambleFootnote.getActId().getId()==0){
            preambleFootnote.setActId(null);
        }

        
        preambleFootnote.setCreatedBy(preambleFootnoteOld.getCreatedBy());
        preambleFootnote.setCreatedAt(preambleFootnoteOld.getCreatedAt());

        preambleFootnote.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        preambleFootnote.setUpdatedAt(new Date());

        preambleFootnoteRepository.save(preambleFootnote);
        return "redirect:/admin/preambleFootnote";
    }



    @RequestMapping(value="/details/{id}",method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
        PreambleFootnote preambleFootnote = preambleFootnoteRepository.findOne(id);
        model.addAttribute("preambleFootnote", preambleFootnote);
        return "admin/pages/PreambleFootnote/details";
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String,Object> delete(@PathVariable("id") Integer id,Model model){

        Integer footnoteId=id;
        PreambleFootnote dlPreambleFootnote= preambleFootnoteRepository.getOne(id);

        List<PreambleFootnote> preambleFootnotes= preambleFootnoteRepository.getFootnoteWithGrateaterOrder(dlPreambleFootnote.getActId().getId(), dlPreambleFootnote.getFootnoteNumber());

        if (preambleFootnotes.size()!=0)
        {
            for (PreambleFootnote preambleFootnote:preambleFootnotes)
            {
                preambleFootnote.setFootnoteNumber( preambleFootnote.getFootnoteNumber()-1);
                preambleFootnoteRepository.save(preambleFootnote);
            }
        }


        preambleFootnoteRepository.delete(id);
        Map<String,Object> response = new HashMap();
  
        response.put("success",true);
        response.put("message","You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  PreambleFootnote getById(@PathVariable("id") Integer id,Model model){
        PreambleFootnote preambleFootnote = preambleFootnoteRepository.findOne(id);
        return preambleFootnote;
    }


    @RequestMapping(value="/dataApi",method = RequestMethod.GET)
    @ResponseBody
    public List<PreambleFootnote> data(){
        return preambleFootnoteRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST,headers="Accept=application/json")
    public DataTablesOutput<PreambleFootnote> getPreambleFootnotes(@Valid @RequestBody DataTablesInput input) {

        return preambleFootnoteDataTableRepository.findAll(input);
    }

    /*EXTRA_METHOD*/

}

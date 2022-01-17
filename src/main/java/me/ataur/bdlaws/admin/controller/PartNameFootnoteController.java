package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.PartNameFootnote;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.PartNameFootnoteRepository;
import me.ataur.bdlaws.admin.repository.PartNameFootnoteDataTableRepository;


import me.ataur.bdlaws.admin.repository.ActPartRepository;

import me.ataur.bdlaws.admin.validator.PartNameFootnoteValidator;

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
@RequestMapping({"/admin/PartNameFootnote","/admin/partNameFootnote"})
public class PartNameFootnoteController extends MyBaseController{

    private static final Logger logger = LoggerFactory.getLogger(PartNameFootnoteController.class);

    @InitBinder
    public void initBinder ( WebDataBinder binder )
    {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    PartNameFootnoteRepository partNameFootnoteRepository;
     @Autowired
    PartNameFootnoteDataTableRepository partNameFootnoteDataTableRepository;


    
	@Autowired
    ActPartRepository actPartRepository;

    @Autowired
    PartNameFootnoteValidator partNameFootnoteValidator;

    @RequestMapping(value={"","/index"},method = RequestMethod.GET)
    public String index(Model model){
        return "admin/pages/PartNameFootnote/datatable";
    }

    @RequestMapping(value={"/create","/create/*"},method = RequestMethod.GET)
    public String create(Model model,HttpServletRequest request){
        
        PartNameFootnote partNameFootnote = new PartNameFootnote();
        model.addAttribute("partNameFootnote", partNameFootnote);
        model.addAttribute("action","/create");
        
			model.addAttribute("actPartList",actPartRepository.findAllByOrderByPartNoAsc() );
			
        return "admin/pages/PartNameFootnote/create";
    }


    @RequestMapping(value={"/create"},method = RequestMethod.POST)
    public String save(Model model , @Valid @ModelAttribute("partNameFootnote") PartNameFootnote partNameFootnote, BindingResult result,HttpServletRequest request ){
        
        

        partNameFootnoteValidator.validate(partNameFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("partNameFootnote", partNameFootnote);
            model.addAttribute("action","/create");
            
			model.addAttribute("actPartList",actPartRepository.findAllByOrderByPartNoAsc() );
			
            return "admin/pages/PartNameFootnote/create";
        }
        
        

        

        /*CHILD_TABLE_DATA_CREATE*/

        
		if(partNameFootnote.getPartId().getId()==0){
            partNameFootnote.setPartId(null);
        }

        
        partNameFootnote.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        partNameFootnote.setCreatedAt(new Date());
        partNameFootnoteRepository.save(partNameFootnote);
        return "redirect:/admin/partNameFootnote";
    }



    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
      
        PartNameFootnote partNameFootnote = partNameFootnoteRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("partNameFootnote", partNameFootnote);
        model.addAttribute("action","/edit/"+id);
        
			model.addAttribute("actPartList",actPartRepository.findAllByOrderByPartNoAsc() );
			
        return "admin/pages/PartNameFootnote/create";
    }


    @RequestMapping(value="/edit/{id}",method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id,Model model , @Valid @ModelAttribute("partNameFootnote") PartNameFootnote partNameFootnote, BindingResult result,HttpServletRequest request ){
        
        PartNameFootnote partNameFootnoteOld = partNameFootnoteRepository.findOne(id);
        
        partNameFootnoteValidator.validate(partNameFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("partNameFootnote", partNameFootnote);
            model.addAttribute("action","/edit/"+id);
            
			model.addAttribute("actPartList",actPartRepository.findAllByOrderByPartNoAsc() );
			
            return "admin/pages/PartNameFootnote/create";
        }

        
        
        /*CHILD_TABLE_DATA_EDIT*/
        
        
		if(partNameFootnote.getPartId().getId()==0){
            partNameFootnote.setPartId(null);
        }

        
        partNameFootnote.setCreatedBy(partNameFootnoteOld.getCreatedBy());
        partNameFootnote.setCreatedAt(partNameFootnoteOld.getCreatedAt());

        partNameFootnote.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        partNameFootnote.setUpdatedAt(new Date());

        partNameFootnoteRepository.save(partNameFootnote);
        return "redirect:/admin/partNameFootnote";
    }



    @RequestMapping(value="/details/{id}",method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
        PartNameFootnote partNameFootnote = partNameFootnoteRepository.findOne(id);
        model.addAttribute("partNameFootnote", partNameFootnote);
        return "admin/pages/PartNameFootnote/details";
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String,Object> delete(@PathVariable("id") Integer id,Model model){

        Integer footnoteId=id;
        PartNameFootnote dlPartNameFootnote= partNameFootnoteRepository.getOne(id);

        List<PartNameFootnote> partNameFootnotes= partNameFootnoteRepository.getFootnoteWithGrateaterOrder(dlPartNameFootnote.getPartId().getId(), dlPartNameFootnote.getFootnoteNumber());

        if (partNameFootnotes.size()!=0)
        {
            for (PartNameFootnote partNameFootnote:partNameFootnotes)
            {
                partNameFootnote.setFootnoteNumber(partNameFootnote.getFootnoteNumber()-1);
                partNameFootnoteRepository.save(partNameFootnote);
            }
        }


        partNameFootnoteRepository.delete(id);
        Map<String,Object> response = new HashMap();
  
        response.put("success",true);
        response.put("message","You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  PartNameFootnote getById(@PathVariable("id") Integer id,Model model){
        PartNameFootnote partNameFootnote = partNameFootnoteRepository.findOne(id);
        return partNameFootnote;
    }


    @RequestMapping(value="/dataApi",method = RequestMethod.GET)
    @ResponseBody
    public List<PartNameFootnote> data(){
        return partNameFootnoteRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST,headers="Accept=application/json")
    public DataTablesOutput<PartNameFootnote> getPartNameFootnotes(@Valid @RequestBody DataTablesInput input) {

        return partNameFootnoteDataTableRepository.findAll(input);
    }

    /*EXTRA_METHOD*/

}

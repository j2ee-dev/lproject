package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.PartNoFootnote;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.PartNoFootnoteRepository;
import me.ataur.bdlaws.admin.repository.PartNoFootnoteDataTableRepository;


import me.ataur.bdlaws.admin.repository.ActPartRepository;

import me.ataur.bdlaws.admin.validator.PartNoFootnoteValidator;

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
@RequestMapping({"/admin/PartNoFootnote","/admin/partNoFootnote"})
public class PartNoFootnoteController extends MyBaseController{

    private static final Logger logger = LoggerFactory.getLogger(PartNoFootnoteController.class);

    @InitBinder
    public void initBinder ( WebDataBinder binder )
    {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    PartNoFootnoteRepository partNoFootnoteRepository;
     @Autowired
    PartNoFootnoteDataTableRepository partNoFootnoteDataTableRepository;


    
	@Autowired
    ActPartRepository actPartRepository;

    @Autowired
    PartNoFootnoteValidator partNoFootnoteValidator;

    @RequestMapping(value={"","/index"},method = RequestMethod.GET)
    public String index(Model model){
        return "admin/pages/PartNoFootnote/datatable";
    }

    @RequestMapping(value={"/create","/create/*"},method = RequestMethod.GET)
    public String create(Model model,HttpServletRequest request){
        
        PartNoFootnote partNoFootnote = new PartNoFootnote();
        model.addAttribute("partNoFootnote", partNoFootnote);
        model.addAttribute("action","/create");
        
			model.addAttribute("actPartList",actPartRepository.findAllByOrderByPartNoAsc() );
			
        return "admin/pages/PartNoFootnote/create";
    }


    @RequestMapping(value={"/create"},method = RequestMethod.POST)
    public String save(Model model , @Valid @ModelAttribute("partNoFootnote") PartNoFootnote partNoFootnote, BindingResult result,HttpServletRequest request ){
        
        

        partNoFootnoteValidator.validate(partNoFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("partNoFootnote", partNoFootnote);
            model.addAttribute("action","/create");
            
			model.addAttribute("actPartList",actPartRepository.findAllByOrderByPartNoAsc() );
			
            return "admin/pages/PartNoFootnote/create";
        }
        
        

        

        /*CHILD_TABLE_DATA_CREATE*/

        
		if(partNoFootnote.getPartId().getId()==0){
            partNoFootnote.setPartId(null);
        }

        
        partNoFootnote.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        partNoFootnote.setCreatedAt(new Date());
        partNoFootnoteRepository.save(partNoFootnote);
        return "redirect:/admin/partNoFootnote";
    }



    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
      
        PartNoFootnote partNoFootnote = partNoFootnoteRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("partNoFootnote", partNoFootnote);
        model.addAttribute("action","/edit/"+id);
        
			model.addAttribute("actPartList",actPartRepository.findAllByOrderByPartNoAsc() );
			
        return "admin/pages/PartNoFootnote/create";
    }


    @RequestMapping(value="/edit/{id}",method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id,Model model , @Valid @ModelAttribute("partNoFootnote") PartNoFootnote partNoFootnote, BindingResult result,HttpServletRequest request ){
        
        PartNoFootnote partNoFootnoteOld = partNoFootnoteRepository.findOne(id);
        
        partNoFootnoteValidator.validate(partNoFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("partNoFootnote", partNoFootnote);
            model.addAttribute("action","/edit/"+id);
            
			model.addAttribute("actPartList",actPartRepository.findAllByOrderByPartNoAsc() );
			
            return "admin/pages/PartNoFootnote/create";
        }

        
        
        /*CHILD_TABLE_DATA_EDIT*/
        
        
		if(partNoFootnote.getPartId().getId()==0){
            partNoFootnote.setPartId(null);
        }

        
        partNoFootnote.setCreatedBy(partNoFootnoteOld.getCreatedBy());
        partNoFootnote.setCreatedAt(partNoFootnoteOld.getCreatedAt());

        partNoFootnote.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        partNoFootnote.setUpdatedAt(new Date());

        partNoFootnoteRepository.save(partNoFootnote);
        return "redirect:/admin/partNoFootnote";
    }



    @RequestMapping(value="/details/{id}",method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
        PartNoFootnote partNoFootnote = partNoFootnoteRepository.findOne(id);
        model.addAttribute("partNoFootnote", partNoFootnote);
        return "admin/pages/PartNoFootnote/details";
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String,Object> delete(@PathVariable("id") Integer id,Model model){

        Integer footnoteId=id;
        PartNoFootnote dlPartNameFootnote= partNoFootnoteRepository.getOne(id);

        List<PartNoFootnote> partNoFootnotes= partNoFootnoteRepository.getFootnoteWithGrateaterOrder(dlPartNameFootnote.getPartId().getId(), dlPartNameFootnote.getFootnoteNumber());

        if (partNoFootnotes.size()!=0)
        {
            for (PartNoFootnote partNoFootnote:partNoFootnotes)
            {
                partNoFootnote.setFootnoteNumber(partNoFootnote.getFootnoteNumber()-1);
                partNoFootnoteRepository.save(partNoFootnote);
            }
        }

        partNoFootnoteRepository.delete(id);
        Map<String,Object> response = new HashMap();
  
        response.put("success",true);
        response.put("message","You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  PartNoFootnote getById(@PathVariable("id") Integer id,Model model){
        PartNoFootnote partNoFootnote = partNoFootnoteRepository.findOne(id);
        return partNoFootnote;
    }


    @RequestMapping(value="/dataApi",method = RequestMethod.GET)
    @ResponseBody
    public List<PartNoFootnote> data(){
        return partNoFootnoteRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST,headers="Accept=application/json")
    public DataTablesOutput<PartNoFootnote> getPartNoFootnotes(@Valid @RequestBody DataTablesInput input) {

        return partNoFootnoteDataTableRepository.findAll(input);
    }

    /*EXTRA_METHOD*/

}

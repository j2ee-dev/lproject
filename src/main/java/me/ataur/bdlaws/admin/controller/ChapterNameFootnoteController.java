package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.ChapterNameFootnote;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.ChapterNameFootnoteRepository;
import me.ataur.bdlaws.admin.repository.ChapterNameFootnoteDataTableRepository;


import me.ataur.bdlaws.admin.repository.ActChapterRepository;

import me.ataur.bdlaws.admin.validator.ChapterNameFootnoteValidator;

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
@RequestMapping({"/admin/ChapterNameFootnote","/admin/chapterNameFootnote"})
public class ChapterNameFootnoteController extends MyBaseController{

    private static final Logger logger = LoggerFactory.getLogger(ChapterNameFootnoteController.class);

    @InitBinder
    public void initBinder ( WebDataBinder binder )
    {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    ChapterNameFootnoteRepository chapterNameFootnoteRepository;
     @Autowired
    ChapterNameFootnoteDataTableRepository chapterNameFootnoteDataTableRepository;


    
	@Autowired
    ActChapterRepository actChapterRepository;

    @Autowired
    ChapterNameFootnoteValidator chapterNameFootnoteValidator;

    @RequestMapping(value={"","/index"},method = RequestMethod.GET)
    public String index(Model model){
        return "admin/pages/ChapterNameFootnote/datatable";
    }

    @RequestMapping(value={"/create","/create/*"},method = RequestMethod.GET)
    public String create(Model model,HttpServletRequest request){
        
        ChapterNameFootnote chapterNameFootnote = new ChapterNameFootnote();
        model.addAttribute("chapterNameFootnote", chapterNameFootnote);
        model.addAttribute("action","/create");
        
			model.addAttribute("actChapterList",actChapterRepository.findAllByOrderByChapterNoAsc() );
			
        return "admin/pages/ChapterNameFootnote/create";
    }


    @RequestMapping(value={"/create"},method = RequestMethod.POST)
    public String save(Model model , @Valid @ModelAttribute("chapterNameFootnote") ChapterNameFootnote chapterNameFootnote, BindingResult result,HttpServletRequest request ){
        
        

        chapterNameFootnoteValidator.validate(chapterNameFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("chapterNameFootnote", chapterNameFootnote);
            model.addAttribute("action","/create");
            
			model.addAttribute("actChapterList",actChapterRepository.findAllByOrderByChapterNoAsc() );
			
            return "admin/pages/ChapterNameFootnote/create";
        }
        
        

        

        /*CHILD_TABLE_DATA_CREATE*/

        
		if(chapterNameFootnote.getChapterId().getId()==0){
            chapterNameFootnote.setChapterId(null);
        }

        
        chapterNameFootnote.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        chapterNameFootnote.setCreatedAt(new Date());
        chapterNameFootnoteRepository.save(chapterNameFootnote);
        return "redirect:/admin/chapterNameFootnote";
    }



    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
      
        ChapterNameFootnote chapterNameFootnote = chapterNameFootnoteRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("chapterNameFootnote", chapterNameFootnote);
        model.addAttribute("action","/edit/"+id);
        
			model.addAttribute("actChapterList",actChapterRepository.findAllByOrderByChapterNoAsc() );
			
        return "admin/pages/ChapterNameFootnote/create";
    }


    @RequestMapping(value="/edit/{id}",method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id,Model model , @Valid @ModelAttribute("chapterNameFootnote") ChapterNameFootnote chapterNameFootnote, BindingResult result,HttpServletRequest request ){
        
        ChapterNameFootnote chapterNameFootnoteOld = chapterNameFootnoteRepository.findOne(id);
        
        chapterNameFootnoteValidator.validate(chapterNameFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("chapterNameFootnote", chapterNameFootnote);
            model.addAttribute("action","/edit/"+id);
            
			model.addAttribute("actChapterList",actChapterRepository.findAllByOrderByChapterNoAsc() );
			
            return "admin/pages/ChapterNameFootnote/create";
        }

        
        
        /*CHILD_TABLE_DATA_EDIT*/
        
        
		if(chapterNameFootnote.getChapterId().getId()==0){
            chapterNameFootnote.setChapterId(null);
        }

        
        chapterNameFootnote.setCreatedBy(chapterNameFootnoteOld.getCreatedBy());
        chapterNameFootnote.setCreatedAt(chapterNameFootnoteOld.getCreatedAt());

        chapterNameFootnote.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        chapterNameFootnote.setUpdatedAt(new Date());

        chapterNameFootnoteRepository.save(chapterNameFootnote);
        return "redirect:/admin/chapterNameFootnote";
    }



    @RequestMapping(value="/details/{id}",method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
        ChapterNameFootnote chapterNameFootnote = chapterNameFootnoteRepository.findOne(id);
        model.addAttribute("chapterNameFootnote", chapterNameFootnote);
        return "admin/pages/ChapterNameFootnote/details";
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String,Object> delete(@PathVariable("id") Integer id,Model model){

        Integer footnoteId=id;
        ChapterNameFootnote dlChapterNameFootnote= chapterNameFootnoteRepository.getOne(id);

        List<ChapterNameFootnote> chapterNameFootnotes= chapterNameFootnoteRepository.getFootnoteWithGrateaterOrder(dlChapterNameFootnote.getChapterId().getId(), dlChapterNameFootnote.getFootnoteNumber());

        if (chapterNameFootnotes.size()!=0)
        {
            for (ChapterNameFootnote chapterNameFootnote:chapterNameFootnotes)
            {
                chapterNameFootnote.setFootnoteNumber(chapterNameFootnote.getFootnoteNumber()-1);
                chapterNameFootnoteRepository.save(chapterNameFootnote);
            }
        }


        chapterNameFootnoteRepository.delete(id);
        Map<String,Object> response = new HashMap();
  
        response.put("success",true);
        response.put("message","You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  ChapterNameFootnote getById(@PathVariable("id") Integer id,Model model){
        ChapterNameFootnote chapterNameFootnote = chapterNameFootnoteRepository.findOne(id);
        return chapterNameFootnote;
    }


    @RequestMapping(value="/dataApi",method = RequestMethod.GET)
    @ResponseBody
    public List<ChapterNameFootnote> data(){
        return chapterNameFootnoteRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST,headers="Accept=application/json")
    public DataTablesOutput<ChapterNameFootnote> getChapterNameFootnotes(@Valid @RequestBody DataTablesInput input) {

        return chapterNameFootnoteDataTableRepository.findAll(input);
    }

    /*EXTRA_METHOD*/

}

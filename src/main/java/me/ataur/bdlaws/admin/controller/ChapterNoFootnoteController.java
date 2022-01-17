package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.ChapterNoFootnote;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.ChapterNoFootnoteRepository;
import me.ataur.bdlaws.admin.repository.ChapterNoFootnoteDataTableRepository;


import me.ataur.bdlaws.admin.repository.ActChapterRepository;

import me.ataur.bdlaws.admin.validator.ChapterNoFootnoteValidator;

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
@RequestMapping({"/admin/ChapterNoFootnote","/admin/chapterNoFootnote"})
public class ChapterNoFootnoteController extends MyBaseController{

    private static final Logger logger = LoggerFactory.getLogger(ChapterNoFootnoteController.class);

    @InitBinder
    public void initBinder ( WebDataBinder binder )
    {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    ChapterNoFootnoteRepository chapterNoFootnoteRepository;
     @Autowired
    ChapterNoFootnoteDataTableRepository chapterNoFootnoteDataTableRepository;


    
	@Autowired
    ActChapterRepository actChapterRepository;

    @Autowired
    ChapterNoFootnoteValidator chapterNoFootnoteValidator;

    @RequestMapping(value={"","/index"},method = RequestMethod.GET)
    public String index(Model model){
        return "admin/pages/ChapterNoFootnote/datatable";
    }

    @RequestMapping(value={"/create","/create/*"},method = RequestMethod.GET)
    public String create(Model model,HttpServletRequest request){
        
        ChapterNoFootnote chapterNoFootnote = new ChapterNoFootnote();
        model.addAttribute("chapterNoFootnote", chapterNoFootnote);
        model.addAttribute("action","/create");
        
			model.addAttribute("actChapterList",actChapterRepository.findAllByOrderByChapterNoAsc() );
			
        return "admin/pages/ChapterNoFootnote/create";
    }


    @RequestMapping(value={"/create"},method = RequestMethod.POST)
    public String save(Model model , @Valid @ModelAttribute("chapterNoFootnote") ChapterNoFootnote chapterNoFootnote, BindingResult result,HttpServletRequest request ){
        
        

        chapterNoFootnoteValidator.validate(chapterNoFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("chapterNoFootnote", chapterNoFootnote);
            model.addAttribute("action","/create");
            
			model.addAttribute("actChapterList",actChapterRepository.findAllByOrderByChapterNoAsc() );
			
            return "admin/pages/ChapterNoFootnote/create";
        }
        
        

        

        /*CHILD_TABLE_DATA_CREATE*/

        
		if(chapterNoFootnote.getChapterId().getId()==0){
            chapterNoFootnote.setChapterId(null);
        }

        
        chapterNoFootnote.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        chapterNoFootnote.setCreatedAt(new Date());
        chapterNoFootnoteRepository.save(chapterNoFootnote);
        return "redirect:/admin/chapterNoFootnote";
    }



    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
      
        ChapterNoFootnote chapterNoFootnote = chapterNoFootnoteRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("chapterNoFootnote", chapterNoFootnote);
        model.addAttribute("action","/edit/"+id);
        
			model.addAttribute("actChapterList",actChapterRepository.findAllByOrderByChapterNoAsc() );
			
        return "admin/pages/ChapterNoFootnote/create";
    }


    @RequestMapping(value="/edit/{id}",method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id,Model model , @Valid @ModelAttribute("chapterNoFootnote") ChapterNoFootnote chapterNoFootnote, BindingResult result,HttpServletRequest request ){
        
        ChapterNoFootnote chapterNoFootnoteOld = chapterNoFootnoteRepository.findOne(id);
        
        chapterNoFootnoteValidator.validate(chapterNoFootnote, result);
        if(result.hasErrors()){
            model.addAttribute("chapterNoFootnote", chapterNoFootnote);
            model.addAttribute("action","/edit/"+id);
            
			model.addAttribute("actChapterList",actChapterRepository.findAllByOrderByChapterNoAsc() );
			
            return "admin/pages/ChapterNoFootnote/create";
        }

        
        
        /*CHILD_TABLE_DATA_EDIT*/
        
        
		if(chapterNoFootnote.getChapterId().getId()==0){
            chapterNoFootnote.setChapterId(null);
        }

        
        chapterNoFootnote.setCreatedBy(chapterNoFootnoteOld.getCreatedBy());
        chapterNoFootnote.setCreatedAt(chapterNoFootnoteOld.getCreatedAt());

        chapterNoFootnote.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        chapterNoFootnote.setUpdatedAt(new Date());

        chapterNoFootnoteRepository.save(chapterNoFootnote);
        return "redirect:/admin/chapterNoFootnote";
    }



    @RequestMapping(value="/details/{id}",method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
        ChapterNoFootnote chapterNoFootnote = chapterNoFootnoteRepository.findOne(id);
        model.addAttribute("chapterNoFootnote", chapterNoFootnote);
        return "admin/pages/ChapterNoFootnote/details";
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String,Object> delete(@PathVariable("id") Integer id,Model model){

        Integer footnoteId=id;
        ChapterNoFootnote dlChapterNameFootnote= chapterNoFootnoteRepository.getOne(id);

        List<ChapterNoFootnote> chapterNoFootnotes= chapterNoFootnoteRepository.getFootnoteWithGrateaterOrder(dlChapterNameFootnote.getChapterId().getId(), dlChapterNameFootnote.getFootnoteNumber());

        if (chapterNoFootnotes.size()!=0)
        {
            for (ChapterNoFootnote chapterNoFootnote:chapterNoFootnotes)
            {
                chapterNoFootnote.setFootnoteNumber(chapterNoFootnote.getFootnoteNumber()-1);
                chapterNoFootnoteRepository.save(chapterNoFootnote);
            }
        }

        chapterNoFootnoteRepository.delete(id);
        Map<String,Object> response = new HashMap();
  
        response.put("success",true);
        response.put("message","You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  ChapterNoFootnote getById(@PathVariable("id") Integer id,Model model){
        ChapterNoFootnote chapterNoFootnote = chapterNoFootnoteRepository.findOne(id);
        return chapterNoFootnote;
    }


    @RequestMapping(value="/dataApi",method = RequestMethod.GET)
    @ResponseBody
    public List<ChapterNoFootnote> data(){
        return chapterNoFootnoteRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST,headers="Accept=application/json")
    public DataTablesOutput<ChapterNoFootnote> getChapterNoFootnotes(@Valid @RequestBody DataTablesInput input) {

        return chapterNoFootnoteDataTableRepository.findAll(input);
    }

    /*EXTRA_METHOD*/

}

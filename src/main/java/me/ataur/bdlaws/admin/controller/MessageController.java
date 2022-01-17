package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.Message;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.MessageRepository;
import me.ataur.bdlaws.admin.repository.MessageDataTableRepository;

/*IMPORT_EXTRA_REPOSITORY*/

import me.ataur.bdlaws.admin.validator.MessageValidator;

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
@RequestMapping({"/admin/Message","/admin/message"})
public class MessageController extends MyBaseController{

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    MessageRepository messageRepository;
     @Autowired
    MessageDataTableRepository messageDataTableRepository;
    /*AUTOWIRED_EXTRA_REPOSITORY*/
    @Autowired
    MessageValidator messageValidator;

    @InitBinder
    public void initBinder ( WebDataBinder binder )
    {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @RequestMapping(value={"","/index"},method = RequestMethod.GET)
    public String index(Model model){
        return "admin/pages/Message/datatable";
    }

    @RequestMapping(value={"/create","/create/*"},method = RequestMethod.GET)
    public String create(Model model,HttpServletRequest request){
        
        Message message = new Message();
        model.addAttribute("message", message);
        model.addAttribute("action","/create");
        /*EXTA_PARAMETER*/
        return "admin/pages/Message/create";
    }


    @RequestMapping(value={"/create"},method = RequestMethod.POST)
    public String save(Model model , @Valid @ModelAttribute("message") Message message, BindingResult result,HttpServletRequest request ){
        
        

        messageValidator.validate(message, result);
        if(result.hasErrors()){
            model.addAttribute("message", message);
            model.addAttribute("action","/create");
            /*EXTA_PARAMETER*/
            return "admin/pages/Message/create";
        }
        
        

        

        /*CHILD_TABLE_DATA_CREATE*/

        /*SET_NULL_TO_FOREIN_KEY*/
        message.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        message.setCreatedAt(new Date());
        messageRepository.save(message);
        return "redirect:/admin/message";
    }



    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
      
        Message message = messageRepository.findOne(id);
        /*SET_OTHER_FIELD*/
        model.addAttribute("message", message);
        model.addAttribute("action","/edit/"+id);
        /*EXTA_PARAMETER*/
        return "admin/pages/Message/edit";
    }


    @RequestMapping(value="/edit/{id}",method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id,Model model , @Valid @ModelAttribute("message") Message message, BindingResult result,HttpServletRequest request ){
        
        Message messageOld = messageRepository.findOne(id);
        
        messageValidator.validate(message, result);
        if(result.hasErrors()){
            model.addAttribute("message", message);
            model.addAttribute("action","/edit/"+id);
            /*EXTA_PARAMETER*/
            return "admin/pages/Message/edit";
        }

        
        
        /*CHILD_TABLE_DATA_EDIT*/
        
        /*SET_NULL_TO_FOREIN_KEY*/
        message.setCreatedBy(messageOld.getCreatedBy());
        message.setCreatedAt(messageOld.getCreatedAt());

        message.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        message.setUpdatedAt(new Date());

        messageRepository.save(message);
        return "redirect:/admin/message";
    }



    @RequestMapping(value="/details/{id}",method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
        Message message = messageRepository.findOne(id);
        model.addAttribute("message", message);
        return "admin/pages/Message/details";
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String,Object> delete(@PathVariable("id") Integer id,Model model){
        messageRepository.delete(id);
        Map<String,Object> response = new HashMap();
  
        response.put("success",true);
        response.put("message","You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  Message getById(@PathVariable("id") Integer id,Model model){
        Message message = messageRepository.findOne(id);
        return message;
    }


    @RequestMapping(value="/dataApi",method = RequestMethod.GET)
    @ResponseBody
    public List<Message> data(){
        return messageRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST,headers="Accept=application/json")
    public DataTablesOutput<Message> getMessages(@Valid @RequestBody DataTablesInput input) {

        return messageDataTableRepository.findAll(input);
    }

    /*EXTRA_METHOD*/

}

package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.Hit;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.HitRepository;
import me.ataur.bdlaws.admin.repository.HitDataTableRepository;

/*IMPORT_EXTRA_REPOSITORY*/

import me.ataur.bdlaws.admin.services.HitService;
import me.ataur.bdlaws.admin.validator.HitValidator;

import com.fasterxml.jackson.annotation.JsonView;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by imran hossain
 */

@Controller
@RequestMapping({"/admin/Hit","/admin/hit"})
public class HitController extends MyBaseController{

    private static final Logger logger = LoggerFactory.getLogger(HitController.class);

    @InitBinder
    public void initBinder ( WebDataBinder binder )
    {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    HitRepository hitRepository;
     @Autowired
    HitDataTableRepository hitDataTableRepository;
    @Autowired
    HitService hitService;

    /*AUTOWIRED_EXTRA_REPOSITORY*/
    @Autowired
    HitValidator hitValidator;

    Integer size = new Integer(10);
    @RequestMapping(value={"","/index"},method = RequestMethod.GET)
    public String index(Model model){
        return "admin/pages/Hit/datatable";
    }


    @RequestMapping(value = {"/hitSearch"}, method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {

        Hit hit = new Hit();
        model.addAttribute("hit", hit);
        model.addAttribute("action", "/generatereport");
        return "admin/pages/Hit/hitsearch";
    }



    @RequestMapping(value="/details/{id}",method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        
        Hit hit = hitRepository.findOne(id);
        model.addAttribute("hit", hit);
        return "admin/pages/Hit/details";
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String,Object> delete(@PathVariable("id") Integer id,Model model){
        hitRepository.delete(id);
        Map<String,Object> response = new HashMap();
  
        response.put("success",true);
        response.put("message","You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  Hit getById(@PathVariable("id") Integer id,Model model){
        Hit hit = hitRepository.findOne(id);
        return hit;
    }


    @RequestMapping(value="/dataApi",method = RequestMethod.GET)
    @ResponseBody
    public List<Hit> data(){
        return hitRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST,headers="Accept=application/json")
    public DataTablesOutput<Hit> getHits(@Valid @RequestBody DataTablesInput input) {

        return hitDataTableRepository.findAll(input);
    }

    /*EXTRA_METHOD*/

    @RequestMapping(value = {"/generatereport"}, method = RequestMethod.POST)
    public String getreport(@ModelAttribute("hit") Hit hit, Model model, @RequestParam("fromDate") @DateTimeFormat(pattern = "dd/MM/yyyy") Date fromDate,@RequestParam("toDate")  @DateTimeFormat(pattern = "dd/MM/yyyy") Date toDate ,@RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "size",required = false) Integer size)  {
        if (fromDate==null)
        {
            Hit hit1 = new Hit();
            model.addAttribute("hit", hit1);
            model.addAttribute("action", "/generatereport");
            return "admin/pages/Hit/hitsearch";
        }
        if(page==null){
            page = 0;
        }
        if(size==null){
            size = this.size;
        }

        model.addAttribute("action", "/generatereport");
        SimpleDateFormat sdf=new SimpleDateFormat("E MMM d HH:mm:ss z yyyy");
        SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf3=new SimpleDateFormat("E MMM d HH:mm:ss z yyyy");
        SimpleDateFormat sdf4=new SimpleDateFormat("dd-MM-yyyy");
        try {
            //String fromd= hit.getFromDate().toString();
            String fromd= fromDate.toString();
            System.out.println(fromd);
            Date fd=sdf.parse(fromd);
            System.out.println(sdf2.format(fd));
            //String todate= hit.getToDate().toString();
            String todate= toDate.toString();
            System.out.println(todate);
            Date td=sdf3.parse(todate);

            System.out.println(sdf4.format(td));
            Pageable pageable = new PageRequest(page,size);
            model.addAttribute("hitReport",hitRepository.getAllBetweenDate(sdf2.format(fd),sdf4.format(td)));
            model.addAttribute("fromDate",fromDate);
            model.addAttribute("toDate",toDate);
            model.addAttribute("fromDate1",sdf2.format(fd));
            model.addAttribute("toDate1",sdf4.format(td));
            model.addAttribute("page",page);
            model.addAttribute("size",size);

        } catch(ParseException e) {
        }

System.out.println("PAGE NUMBERS " +page);

      /*  Pageable pageable = new PageRequest(page,size);
        model.addAttribute("approvedRequisitionOrder",equipmentItemRequisitionOrderRepository.findAllApprovedRequisitionOrder(pageable));
        model.addAttribute("page",page);
        model.addAttribute("size",size);*/

        return "admin/pages/Hit/hitsearch";
    }





    @RequestMapping(path = "/pdf/{from}/{to}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getBook(@PathVariable String from, @PathVariable String to)throws JRException, IOException {
        System.out.println(from+" FROM TO TO"+to);
        // code here
        JasperReportsPdfView view=new JasperReportsPdfView();
        view.setUrl("classpath:/report/jsperreport.jrxml");
        view.setApplicationContext(applicationContext);
        Map<String,Object> params =new HashMap<String,Object>();
        params.put("datasource",hitService.report(from,to));
  /*      for (Map<String,Object> hit:hitService.report(from,to)) {
            hit.entrySet().forEach(entry->{
                System.out.println(entry.getKey() + " " + entry.getValue());
            });
        }*/
        return new ModelAndView(view,params);
    }



}

package me.ataur.bdlaws.admin.controller;

import me.ataur.bdlaws.admin.model.Volume;
/*IMPORT_EXTRA_MODEL*/
import me.ataur.bdlaws.admin.repository.VolumeRepository;
import me.ataur.bdlaws.admin.repository.VolumeDataTableRepository;

/*IMPORT_EXTRA_REPOSITORY*/

import me.ataur.bdlaws.admin.validator.VolumeValidator;

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
@RequestMapping({"/admin/Volume", "/admin/volume"})
public class VolumeController extends MyBaseController {

    private static final Logger logger = LoggerFactory.getLogger(VolumeController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    VolumeRepository volumeRepository;
    @Autowired
    VolumeDataTableRepository volumeDataTableRepository;


    /*AUTOWIRED_EXTRA_REPOSITORY*/
    @Autowired
    VolumeValidator volumeValidator;

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "admin/pages/Volume/datatable";
    }

    @RequestMapping(value = {"/create", "/create/*"}, method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {

        Volume volume = new Volume();
        model.addAttribute("volume", volume);
        model.addAttribute("action", "/create");
        /*EXTA_PARAMETER*/
        return "admin/pages/Volume/create";
    }


    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String save(Model model, @Valid @ModelAttribute("volume") Volume volume) {
        model.addAttribute("volume", volume);
        model.addAttribute("action", "/create");
        volume.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        volume.setCreatedAt(new Date());
        volumeRepository.save(volume);
        model.addAttribute("volume", volumeRepository.getNewVolume());
        return "admin/pages/Volume/details";
        //return "redirect:/admin/volume";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        Volume volume = volumeRepository.findOne(id);
        model.addAttribute("volume", volume);
        model.addAttribute("action", "/edit/" + id);
        return "admin/pages/Volume/edit";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id, Model model, @Valid @ModelAttribute("volume") Volume volume, BindingResult result, HttpServletRequest request) {

        Volume volumeOld = volumeRepository.findOne(id);

        model.addAttribute("volume", volume);
        model.addAttribute("action", "/edit/" + id);
        volume.setCreatedBy(volumeOld.getCreatedBy());
        volume.setCreatedAt(volumeOld.getCreatedAt());
        volume.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        volume.setUpdatedAt(new Date());
        volumeRepository.save(volume);
        model.addAttribute("volume", volumeRepository.getNewVolume());
        return "admin/pages/Volume/details";
    }


    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        Volume volume = volumeRepository.findOne(id);
        model.addAttribute("volume", volume);
        return "admin/pages/Volume/details";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id, Model model) {
        volumeRepository.delete(id);
        Map<String, Object> response = new HashMap();

        response.put("success", true);
        response.put("message", "You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Volume getById(@PathVariable("id") Integer id, Model model) {
        Volume volume = volumeRepository.findOne(id);
        return volume;
    }


    @RequestMapping(value = "/dataApi", method = RequestMethod.GET)
    @ResponseBody
    public List<Volume> data() {
        return volumeRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST, headers = "Accept=application/json")
    public DataTablesOutput<Volume> getVolumes(@Valid @RequestBody DataTablesInput input) {
        return volumeDataTableRepository.findAll(input);
    }


}

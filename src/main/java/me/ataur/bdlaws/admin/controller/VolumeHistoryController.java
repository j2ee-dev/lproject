package me.ataur.bdlaws.admin.controller;

import com.fasterxml.jackson.annotation.JsonView;
import me.ataur.bdlaws.admin.model.VolumeHistory;
import me.ataur.bdlaws.admin.repository.VolumeHistoryDataTableRepository;
import me.ataur.bdlaws.admin.repository.VolumeHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*IMPORT_EXTRA_MODEL*/
/*IMPORT_EXTRA_REPOSITORY*/

/**
 * Created by imran hossain
 */

@Controller
@RequestMapping({"/admin/UserLog", "/admin/userlog"})
public class VolumeHistoryController extends MyBaseController {

    private static final Logger logger = LoggerFactory.getLogger(VolumeHistoryController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    VolumeHistoryRepository volumeHistoryRepository;
    @Autowired
    VolumeHistoryDataTableRepository volumeHistoryDataTableRepository;


    /*AUTOWIRED_EXTRA_REPOSITORY*/
  /*  @Autowired
    VolumeValidator volumeValidator;*/

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "admin/pages/UserLog/datatable";
    }

    @RequestMapping(value = "/dataApi", method = RequestMethod.GET)
    @ResponseBody
    public List<VolumeHistory> data() {
        return volumeHistoryRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST, headers = "Accept=application/json")
    public DataTablesOutput<VolumeHistory> getVolumes(@Valid @RequestBody DataTablesInput input) {
        return volumeHistoryDataTableRepository.findAll(input);
    }

   /*
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {


        Volume volume = volumeRepository.findOne(id);
        *//*SET_OTHER_FIELD*//*
        model.addAttribute("volume", volume);
        model.addAttribute("action", "/edit/" + id);
        *//*EXTA_PARAMETER*//*
        return "admin/pages/Volume/create";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id, Model model, @Valid @ModelAttribute("volume") Volume volume, BindingResult result, HttpServletRequest request) {

        Volume volumeOld = volumeRepository.findOne(id);
        volumeValidator.validate(volume, result);
        if (result.hasErrors()) {
            model.addAttribute("volume", volume);
            model.addAttribute("action", "/edit/" + id);
            *//*EXTA_PARAMETER*//*
            return "admin/pages/Volume/create";
        }
        *//*SET_NULL_TO_FOREIN_KEY*//*
        volume.setCreatedBy(volumeOld.getCreatedBy());
        volume.setCreatedAt(volumeOld.getCreatedAt());

        volume.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        volume.setUpdatedAt(new Date());

        volumeRepository.save(volume);
        return "admin/pages/Volume/newDetails";
    }


    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        Volume volume = volumeRepository.findOne(id);
        model.addAttribute("volume", volume);
        return "admin/pages/Volume/details";
    }

    @RequestMapping(value = "/newVolumeDetails", method = RequestMethod.GET)
    public String newVolumeDetails(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        Volume volume = volumeRepository.findOne(id);
        model.addAttribute("volume", volume);
        return "admin/pages/Volume/newvolumedetails";
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
*/



/*
    @RequestMapping(value = "/newDetails")
    public String newDetails(Model model) {
        model.addAttribute("newDetails", volumeRepository.getNewVolume());
        return "admin/pages/Volume/newDetails";
    }
*/

}

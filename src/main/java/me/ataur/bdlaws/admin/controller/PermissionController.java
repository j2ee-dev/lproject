package me.ataur.bdlaws.admin.controller;
import com.fasterxml.jackson.annotation.JsonView;
import me.ataur.bdlaws.admin.model.Permission;
import me.ataur.bdlaws.admin.repository.PermissionDataTableRepository;
import me.ataur.bdlaws.admin.repository.PermissionRepository;
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
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by imran hossain
 */

@Controller
@RequestMapping({"/admin/Permission", "/admin/permission"})
public class PermissionController extends MyBaseController {

    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    PermissionDataTableRepository permissionDataTableRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }



    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "admin/pages/Permission/datatable";
    }

    @RequestMapping(value = {"/create", "/create/*"}, method = RequestMethod.GET)
    public String create(Model model) {

        Permission permission = new Permission();
        model.addAttribute("permission", permission);
        model.addAttribute("action", "/create");
        return "admin/pages/Permission/create";
    }


    @RequestMapping(value = {"/create", "/create/*"}, method = RequestMethod.POST)
    public String save(Model model, @Valid @ModelAttribute("permission") Permission permission) {
        model.addAttribute("permission", permission);
        model.addAttribute("action", "/create");
        permissionRepository.save(permission);
        model.addAttribute("permission",permissionRepository.getPermittedUser());
        return "admin/pages/Permission/details";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        Permission permission = permissionRepository.findOne(id);
        model.addAttribute("permission", permission);
        model.addAttribute("action", "/edit/" + id);
        return "admin/pages/Permission/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id, Model model, @Valid @ModelAttribute("permission") Permission permission) {

        model.addAttribute("permission", permission);
        model.addAttribute("action", "/edit/" + id);
        permissionRepository.save(permission);
        model.addAttribute("permission", permissionRepository.getPermittedUser());
        return "admin/pages/Permission/details";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id, Model model) {
        permissionRepository.delete(id);
        Map<String, Object> response = new HashMap();
        response.put("success", true);
        response.put("message", "You have successfully Deleted");
        return response;
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

        Permission permission = permissionRepository.findOne(id);
        model.addAttribute("permission", permission);
        return "admin/pages/Permission/details";
    }

    @RequestMapping(value = "/dataApi", method = RequestMethod.GET)
    @ResponseBody
    public List<Permission> data() {
        return permissionRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST, headers = "Accept=application/json")
    public DataTablesOutput<Permission> getUsers(@Valid @RequestBody DataTablesInput input) {

        return permissionDataTableRepository.findAll(input);
    }

}

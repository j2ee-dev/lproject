package me.ataur.bdlaws.admin.controller;


import me.ataur.bdlaws.admin.model.User;
import me.ataur.bdlaws.admin.repository.UserRepository;
import me.ataur.bdlaws.admin.repository.UserDataTableRepository;

import me.ataur.bdlaws.admin.validator.UserValidator;

import com.fasterxml.jackson.annotation.JsonView;
import me.ataur.bdlaws.login.service.UserService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by imran hossain
 */

@Controller
@RequestMapping({"/admin/User", "/admin/user"})
public class UserController extends MyBaseController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDataTableRepository userDataTableRepository;


    /*AUTOWIRED_EXTRA_REPOSITORY*/
    @Autowired
    UserValidator userValidator;

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "admin/pages/User/datatable";
    }

    /*@RequestMapping(value = {"/create", "/create*//*"}, method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {

        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("action", "/create");
        return "admin/pages/User/create";
    }


    @RequestMapping(value = {"/create", "/create*//*"}, method = RequestMethod.POST)
    public String save(Model model,@ModelAttribute("user") User user) {

        model.addAttribute("user", user);
        model.addAttribute("action", "/create");
        userService.save(user);
        return "redirect:/admin";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        User user = userRepository.findOne(id);
        *//*SET_OTHER_FIELD*//*
        model.addAttribute("user", user);
        model.addAttribute("action", "/edit/" + id);
        *//*EXTA_PARAMETER*//*
        return "admin/pages/User/create";
    }*/

    @RequestMapping(value = {"/create", "/create/*"}, method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {

        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("action", "/create");
        return "admin/pages/User/create";
    }


    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String save(Model model, @Valid @ModelAttribute("user") User user) {

        //userValidator.validate(user, result);
        /*if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("action", "/create");
            return "admin/pages/User/create";
        }*/

        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        model.addAttribute("user", user);
        model.addAttribute("action", "/create");
        user.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setCreatedAt(new Date());
        //userRepository.save(user);
        userService.save(user);
        model.addAttribute("user", userRepository.getNewUser());
        return "admin/pages/User/details";
    }


    /*@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id, Model model, @Valid @ModelAttribute("user") User user, BindingResult result, HttpServletRequest request) {
        User userOld = userRepository.findOne(id);
        userValidator.validate(user, result);
        model.addAttribute("user", user);
        model.addAttribute("action", "/edit/" + id);
        *//*user.setCreatedBy(userOld.getCreatedBy());
        user.setCreatedAt(userOld.getCreatedAt());
        user.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setUpdatedAt(new Date());*//*
        userRepository.save(user);
        return "redirect:/admin";
    }*/

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        User user = userRepository.findOne(id);
        model.addAttribute("user", user);
        model.addAttribute("action", "/edit/" + id);
        return "admin/pages/User/edit";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id, Model model, @Valid @ModelAttribute("user") User user) {

        User userOld = userRepository.findOne(id);
        model.addAttribute("user", user);
        model.addAttribute("action", "/edit/" + id);
        /*if (user.getPassword() == null) {
            user.setPassword(userOld.getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }*/
        user.setCreatedBy(userOld.getCreatedBy());
        user.setCreatedAt(userOld.getCreatedAt());
        user.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setUpdatedAt(new Date());
        userService.save(user);
        model.addAttribute("user", userRepository.getNewUser());
        return "admin/pages/User/details";
    }


    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

        User user = userRepository.findOne(id);
        model.addAttribute("user", user);
        return "admin/pages/User/details";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id, Model model) {
        userRepository.delete(id);
        Map<String, Object> response = new HashMap();

        response.put("success", true);
        response.put("message", "You have successfully deleted the record");
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getById(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findOne(id);
        return user;
    }


    @RequestMapping(value = "/dataApi", method = RequestMethod.GET)
    @ResponseBody
    public List<User> data() {
        return userRepository.findAll();
    }

    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST, headers = "Accept=application/json")
    public DataTablesOutput<User> getUsers(@Valid @RequestBody DataTablesInput input) {

        return userDataTableRepository.findAll(input);
    }

}

package me.ataur.bdlaws.login.controller;

import me.ataur.bdlaws.admin.model.User;
import me.ataur.bdlaws.login.service.SecurityService;
import me.ataur.bdlaws.login.util.VerifyRecaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amran
 */
@Controller
public class LoginController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    VerifyRecaptcha verifyRecaptcha;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(Model model,HttpServletRequest request) {
        String server=request.getServerName();
        System.out.println("Server Testring"+server);
        if (server.equals("172.22.8.121"))
        {
            model.addAttribute("pages", "login");
            return "login/index";
        }
        else if (server.equals("192.168.10.28"))
        {
            model.addAttribute("pages", "login");
            return "login/index";
        }
        else
        {
            return "redirect:/";
        }
       // model.addAttribute("pages", "login");
        //return "login/index";

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(@ModelAttribute("user") User user, @RequestParam(value = "g-recaptcha-response", required = false) String gCaptchaResponse, BindingResult result) {

        if (!verifyRecaptcha.verify(gCaptchaResponse)) {
            return "redirect:/login?captcha";
        }
        if (result.hasErrors()) {
            return "redirect:/login?error";
        }
        securityService.autologin(user.getUsername(), user.getPassword());
        return "redirect:/admin";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.GET)
    public String doLoginGet() {
        return "redirect:/login?error";
    }

    /*@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(@ModelAttribute("user") User user, @RequestParam(value = "g-recaptcha-response", required = false) String gCaptchaResponse, BindingResult result, Model model) {

        if (!verifyRecaptcha.verify(gCaptchaResponse)) {
            return "redirect:/login?captcha";
        }
        if (result.hasErrors()) {
            return "redirect:/login?error";
        }
        securityService.autologin(user.getUsername(), user.getPassword());
        return "redirect:/welcome";
    }*/


}

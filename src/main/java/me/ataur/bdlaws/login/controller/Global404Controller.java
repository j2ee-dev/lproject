package me.ataur.bdlaws.login.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
/**
 * @author Amran
 */
@ControllerAdvice
public class Global404Controller {
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handlerNoHandlerFoundException() {

        ModelAndView modelAndView = new ModelAndView("404");
        return modelAndView;
    }

}

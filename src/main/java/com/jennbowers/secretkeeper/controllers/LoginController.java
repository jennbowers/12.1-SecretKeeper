package com.jennbowers.secretkeeper.controllers;

import com.jennbowers.secretkeeper.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model, HttpServletRequest request) {
        model.addAttribute("user", new User());
        try {
            Object message = request.getSession().getAttribute("error");
//            adds error message
            model.addAttribute("error", message);
//            removes error message after
            request.getSession().removeAttribute("error");
        } catch(Exception ex) {}
        return "login";
    }
}


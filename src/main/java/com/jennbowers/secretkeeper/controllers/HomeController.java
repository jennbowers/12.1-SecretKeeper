package com.jennbowers.secretkeeper.controllers;

import com.jennbowers.secretkeeper.models.Secret;
import com.jennbowers.secretkeeper.models.User;
import com.jennbowers.secretkeeper.repositories.SecretRepository;
import com.jennbowers.secretkeeper.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
public class HomeController {

    @Autowired
    SecretRepository secretRepo;

    @Autowired
    UserRepository userRepo;

    @RequestMapping("/")
    public String index(Model model){
        Iterable<Secret> allSecrets = secretRepo.findAll();
        model.addAttribute("secrets", allSecrets);
        model.addAttribute("newSecret", new Secret());
        return "index";
    }
}

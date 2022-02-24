package com.bitter.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Controller
public class FrontendController {

    @GetMapping("/signup")
    public String createUser (Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/")
    public String addUser(@ModelAttribute User user, RestTemplate restTemplate){

        User newUser = restTemplate.postForObject("http://localhost:8081/adduserobj",user, User.class);
        System.out.println(newUser.getFirstname());


        return "redirect:/";
    }
    /*
    @PostMapping("/login")
    public String login (@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "signup";
        }

        return "login";

    }
     */

    @PostMapping("/login")
    public String login (@ModelAttribute LoginForm loginForm, RestTemplate restTemplate){
        boolean result = (restTemplate.postForObject("http://localhost:8081/validate",loginForm,Boolean.class));
        System.out.println(result);
        if(result){
            return "home";
        }
        return "redirect:/";


    }
}



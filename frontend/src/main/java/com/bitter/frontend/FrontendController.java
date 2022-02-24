package com.bitter.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class FrontendController {

    @GetMapping("/signup")
    public String createUser (Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/login")
    public String login (@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "signup";
        }

        return "login";

    }

    @PostMapping("/login")
    public String login (@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "login";
        }

        return "home";

    }
}



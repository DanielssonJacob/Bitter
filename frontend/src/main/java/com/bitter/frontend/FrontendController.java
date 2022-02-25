package com.bitter.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.templateparser.markup.HTMLTemplateParser;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class FrontendController {

    @GetMapping("/signup")
    public String createUser (Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @GetMapping("/logout")
    public String logout (HttpSession session) {
        session.setAttribute("currentUser", null);
        return "redirect:/";
    }


    @GetMapping("/")
    public String index(Model model, HttpSession session){
        if(session.getAttribute("currentUser")!=null){
            return "home";
        }

        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/")
    public String addUser(@ModelAttribute User user, RestTemplate restTemplate){

        User newUser = restTemplate.postForObject("http://localhost:8081/adduserobj",user, User.class);
        System.out.println(newUser.getFirstname());


        return "redirect:/";
    }

    @GetMapping("/user/{username}")
    public String getUser(@PathVariable("username") String username, Model model, RestTemplate restTemplate){
        model.addAttribute("user", restTemplate.getForObject("http://localhost:8081/username/"+username,User.class));
        model.addAttribute("userbeets", restTemplate.getForObject(
                "http://localhost:8081/beet/"+username, ArrayList.class));
        return "userpage";
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
    public String login (@ModelAttribute LoginForm loginForm, RestTemplate restTemplate, HttpSession session){
        boolean result = (restTemplate.postForObject("http://localhost:8081/validate",loginForm,Boolean.class));
        System.out.println(result);
        if(result){
            session.setAttribute("currentUser", restTemplate.getForObject("http://localhost:8081/username/"+loginForm.getUsername(),User.class));
            session.setAttribute("beets",  restTemplate.getForObject(
                    "http://localhost:8081/beet/"+((User)session.getAttribute("currentUser")).getUsername(),
                    ArrayList.class));
        }
        return "redirect:/";


    }
}



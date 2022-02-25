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
import java.util.List;

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

    // login
    @GetMapping("/")
    public String index(Model model, HttpSession session){
        if(session.getAttribute("currentUser")!=null){
            model.addAttribute("newbeet", new Beet());
            return "tryHome";
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

    // posting when logged in
    @GetMapping("/home")
    public String level1(){
        return "tryHome";
    }
    */
    @PostMapping("/home")
    public String level1post(HttpSession session, @RequestParam String beet){
        List<String> list = (List<String>)session.getAttribute("beetList");
        if (list == null) {
            list = new ArrayList<>();
            session.setAttribute("beetList", list);
        }
        list.add(beet);

        return "tryHome";
    }

    //when user is the correct user
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

    @PostMapping("/beets/create")
    public String createBeet (@ModelAttribute Beet beet, RestTemplate restTemplate, HttpSession session){
        Beet newBeet = restTemplate.postForObject("http://localhost:8081/beet", beet, Beet.class);
        session.setAttribute("beets",  restTemplate.getForObject(
                "http://localhost:8081/beet/"+((User)session.getAttribute("currentUser")).getUsername(),
                ArrayList.class));

        return "redirect:/";
    }

    @GetMapping("/beets")
    public String getBeets(RestTemplate restTemplate){
        restTemplate.getForObject("http://localhost:8081/beet", ArrayList.class);
        return "beets";
    }

    @GetMapping("/beets/{username}")
    public String getBeetsByUser(@PathVariable("username") String username, RestTemplate restTemplate){
        restTemplate.getForObject("http://localhost:8081/beet/"+ username, ArrayList.class);
        return "beets";
    }

    @GetMapping("/beets/get/{id}")
    public String getBeetsById(@PathVariable("id") long id, RestTemplate restTemplate){
        restTemplate.getForObject("http://localhost:8081/beet/"+id,Beet.class);
        return "beets";
    }

    @PutMapping("/beets/edit/{id}")
    public String editBeet(@PathVariable("id") long id, @RequestParam("message") String message, RestTemplate restTemplate,HttpSession session){

        if(((User)session.getAttribute("currentuser")).getUsername().equals
                (restTemplate.getForObject("http://localhost:8081/beet/"+ id, Beet.class)
                        .getCreatedByUsername()))
        {
            restTemplate.put("http://localhost:8081/beet/"+id, message, Beet.class);
            return "redirect:/";
        }
        return "redirect:/";
    }

    @DeleteMapping("/beets/delete/{id}")
    public String deleteBeet(@PathVariable("id") long id, RestTemplate restTemplate, HttpSession session){
        if(((User)session.getAttribute("currentuser")).getUsername().equals
                (restTemplate.getForObject("http://localhost:8081/beet/"+ id, Beet.class)
                        .getCreatedByUsername())) {
            restTemplate.delete("http://localhost:8081/beet/" + id, Beet.class);
            return "redirect:/";
        }
        return "redirect:/";
    }

}



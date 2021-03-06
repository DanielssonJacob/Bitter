package com.bitter.backendapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepo;

    @GetMapping("/userid/{id}")
    User getUser(@PathVariable("id") long id){
        return userRepo.getUserById(id);
    }

    @GetMapping("/username/{username}")
    User getUser(@PathVariable("username") String username){
        return userRepo.getUserByUsername(username);
    }

    @GetMapping("/users")
    List<User> getUsers(){
        return userRepo.getAllUsers();
    }

    @PostMapping("/adduser")
    void addUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("email") String email){
        userRepo.addUser(userService.createUser(username,password,firstname,lastname,email));
    }


    @PostMapping("/adduserobj")
    User addUserObj(@RequestBody User user){
        System.out.println("Called");
        return userRepo.addUser(userService.createUser(user));
    }

    @PostMapping("/validate")
    boolean loginUser(@RequestBody LoginForm loginForm){
        return userService.validate(userRepo.getUserByUsername(loginForm.getUsername()), loginForm.getPassword());
    }

    @PostMapping("/deleteuser")
    void deleteUser(@RequestBody User user){
        userRepo.deleteUserById(user.getId());
    }





}

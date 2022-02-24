package com.bitter.backendapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BeetController {

    @Autowired BeetRepo repo;
    @Autowired BeetService service;

    @GetMapping("/beet")
    public List<Beet> beets(){ return repo.getBeets(); }

    @PostMapping("/beet")
    public void postBeet(@RequestParam("username") String username, @RequestParam("message") String message){
         service.createBeet(username,message);
    }

    @GetMapping("beet/{id}")
    public Beet getBeetById(@PathVariable long id){
        return repo.getBeetById(id);
    }

    @GetMapping("/beet/{username}")
    public List<Beet> getBeetByUser(@PathVariable String username){
        return repo.getBeetByUser(username);
    }

    @DeleteMapping("/beet/{id}")
    public void deleteBeet(@PathVariable long id){
       repo.deleteBeet(id);
    }

    @PutMapping("/beet/{id}")
    public void editBeet(@PathVariable long id, String message){
        service.editBeet(id, message);
    }

}

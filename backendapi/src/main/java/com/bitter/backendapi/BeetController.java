package com.bitter.backendapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BeetController {

    @Autowired BeetRepo repo;

    @GetMapping("/beet")
    public List<Beet> beets(){ return repo.getBeets(); }

    @PostMapping("/beet")
    public void postBeet(@RequestBody Beet beet){
         repo.createBeet(beet);
    }

    @GetMapping("beet/{id}")
    public Beet getBeetById(@PathVariable long id){
        return repo.getBeetById(id);
    }

    @GetMapping("/beet/{username}")
    public List<Beet> getBeetByUser(@RequestBody String username){
        return repo.getBeetByUser(username);
    }

    @DeleteMapping("/beet/{id}")
    public void deleteBeet(@RequestBody long id){
       repo.deleteBeet(id);
    }

    @PutMapping("/beet/{id}")
    public void editBeet(@RequestBody Beet beet){
        repo.editBeet(beet);
    }

}
